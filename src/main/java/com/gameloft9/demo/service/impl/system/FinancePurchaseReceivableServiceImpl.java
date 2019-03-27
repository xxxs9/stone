package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.*;
import com.gameloft9.demo.dataaccess.model.system.*;
import com.gameloft9.demo.service.api.system.FinancePurchaseReceivableService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.Constants;
import com.gameloft9.demo.utils.FinanceServiceUtil;
import com.gameloft9.demo.utils.NumberUtil;
import com.gameloft9.demo.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/03/19 2019-03-19
 */
@Service
@Transactional(rollbackFor =  Exception.class)
public class FinancePurchaseReceivableServiceImpl implements FinancePurchaseReceivableService {

    @Autowired
    FinancePurchaseReceivableMapper purchaseReceivableMapper;
    @Autowired
    FinanceApplyOrderMapper applyOrderMapper;
    @Autowired
    PurchaseOrderMapper purchaseOrderMapper;
    @Autowired
    FinanceReceiptMapper receiptMapper;
    @Autowired
    FinanceBillMapper billMapper;

    /**
     *
     * @param page 当前页
     * @param limit 每条条数
     * @param auditState 审核状态
     * @return
     *      采购应收单集合
     */
    public List<SysFinancePurchaseReceivable> getAll(String page, String limit, String auditState) {
        PageRange pageRange = new PageRange(page,limit);
        Integer auditState1 = NumberUtil.strToInt(auditState);
        return purchaseReceivableMapper.getAll(pageRange.getStart(),pageRange.getEnd(),
                auditState1);
    }

    /**
     *
     * @param auditState 审核状态
     * @return
     *      条件查询总条数
     */
    public int getCount(String auditState) {
        Integer auditState1 = NumberUtil.strToInt(auditState);
        return purchaseReceivableMapper.getCount(auditState1);
    }

    /**
     *
     * @param purchaseOrder 采购申请单
     * @return
     *      string
     */
    public String generatePurchaseReceive(PurchaseOrder purchaseOrder,String id1) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        SysFinancePurchaseReceivable purchaseReceivable = new SysFinancePurchaseReceivable();
        purchaseReceivable.setId(UUIDUtil.getUUID());
        purchaseReceivable.setPurchaseOrderRejectedId(purchaseOrder.getId());
        purchaseReceivable.setAuditType(purchaseOrder.getAuditType());
        int price = NumberUtil.strToInt(purchaseOrder.getPrice());
        int goodsNumber = NumberUtil.strToInt(purchaseOrder.getGoodsNumber());
        purchaseReceivable.setUnitPrice(purchaseOrder.getPrice());
        purchaseReceivable.setRejectedNumber(purchaseOrder.getGoodsNumber());
        purchaseReceivable.setTotalPrice(price*goodsNumber+"");
        String documentMaker = (String) request.getSession().getAttribute("sysUser");
        purchaseReceivable.setDocumentMaker(documentMaker);
        purchaseReceivable.setDocumentMakeTime(new Date());
        purchaseReceivable.setAuditState(Constants.Finance.APPLY_ORDER_UNAUDIT);
        //根据id获取申请订单
        SysFinanceApplyOrder applyOrder = applyOrderMapper.getById1(id1);
        applyOrder.setApplyState(Constants.Finance.APPLY_ORDER_UNAUDIT);
        //更新订单申请状态
        applyOrderMapper.updateApplyState(applyOrder);
        //更新purchaseOrder的financeState
        purchaseOrder.setFinanceState(Constants.FinanceState.APPLY_PASS_WAIT);
        purchaseOrderMapper.updateByIdAndState(purchaseOrder);
        //添加销售应付申请单
        purchaseReceivableMapper.add(purchaseReceivable);
        return purchaseReceivable.getId();
    }

    /**
     *
     * @param id 主键id
     * @return q
     */
    public SysFinancePurchaseReceivable getPurchaseReceiveById(String id) {
        return purchaseReceivableMapper.getPurchaseReceiveById(id);
    }

    public Boolean purchaseOrderReceivePass(String attitude, String id, String auditType, String actualPrice, String auditDescribe) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Integer auditType1 = NumberUtil.strToInt(auditType);
        //获取PurchaseOrder
        PurchaseOrder purchaseOrder = purchaseOrderMapper.findByIdAndAuditType(id, auditType1);
        //获取ApplyOrder
        SysFinanceApplyOrder applyOrder = applyOrderMapper.getByApplyIdAndApplyType(id, auditType1);
        //purchaseReceivable
        SysFinancePurchaseReceivable purchaseReceivable = purchaseReceivableMapper.getPurchaseReceiveBypurchaseOrderIdAndAuditType(id, auditType1);
        String agree = "agree";
        if(agree.equals(attitude)){
            purchaseOrder.setFinanceState(Constants.FinanceState.APPLY_PASS_PAY);
            purchaseReceivable.setAuditState(Constants.Finance.APPLY_ORDER_PASS);
            applyOrder.setApplyState(Constants.Finance.APPLY_ORDER_PASS);
        }else{
            purchaseOrder.setFinanceState(Constants.FinanceState.NO_PASS);
            purchaseReceivable.setAuditState(Constants.Finance.APPLY_ORDER_UNPASS);
            applyOrder.setApplyState(Constants.Finance.APPLY_ORDER_UNPASS);

        }
        String auditUser = (String) request.getSession().getAttribute("sysUser");

        purchaseOrder.setFinanceAuditUser(auditUser);
        purchaseOrder.setFinanceAuditTime(new Date());
        purchaseOrder.setFinanceAuditDescribe(auditDescribe);

        purchaseReceivable.setAuditUser(auditUser);
        purchaseReceivable.setActualBalance(actualPrice);
        purchaseReceivable.setAuditTime(new Date());
        purchaseReceivable.setAuditDescribe(auditDescribe);

        //审核通过才生成付款单和账单记录
        if(agree.equals(attitude)){
            //生成付款单
            SysFinanceReceipt receipt = new SysFinanceReceipt();
            receipt.setId(UUIDUtil.getUUID());
            receipt.setBalance(actualPrice);
            receipt.setDocumentMaker(purchaseOrder.getFinanceAuditUser());
            receipt.setDocumentMakeTime(purchaseOrder.getFinanceAuditTime());
            receipt.setReceiveId(purchaseReceivable.getId());
            receipt.setReceiveType(purchaseReceivable.getAuditType());
            //添加付款单
            receiptMapper.add(receipt);
            //生成账单
            SysFinanceBill financeBill = new SysFinanceBill();
            Integer balance = Integer.parseInt(purchaseReceivable.getActualBalance());
            financeBill.setId(UUIDUtil.getUUID());
            financeBill.setBalance(balance);
            financeBill.setBillTime(purchaseReceivable.getAuditTime());
            financeBill.setDepartment(Constants.Finance.PURCHASE);
            //添加账单
            billMapper.add(financeBill);
        }


        //更新applyOrder
        applyOrderMapper.updateApplyState(applyOrder);
        //更新purchaseORder
        purchaseOrderMapper.purchaseOrderPayPass(purchaseOrder);
        //purchaseReceivable
        purchaseReceivableMapper.update(purchaseReceivable);

        return true;
    }
}
