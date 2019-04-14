package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.*;
import com.gameloft9.demo.dataaccess.model.system.*;
import com.gameloft9.demo.mgrframework.beans.response.AbstractResult;
import com.gameloft9.demo.mgrframework.exceptions.BizException;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import com.gameloft9.demo.service.api.system.FinancePurchaseBillPayService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/03/19 2019-03-19
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class FinancePurchaseBillPayServiceImpl implements FinancePurchaseBillPayService {

    @Autowired
    FinancePurchaseBillsPayableMapper purchaseBillsPayableMapper;
    @Autowired
    FinanceApplyOrderMapper applyOrderMapper;
    @Autowired
    PurchaseOrderMapper purchaseOrderMapper;
    @Autowired
    FinancePaymentMapper paymentMapper;
    @Autowired
    FinanceBillMapper billMapper;

    /**
     *
     * @param page 当前页
     * @param limit 每条条数
     * @param auditState 申请类型
     * @return
     *      采购应付单集合
     */
    public List<SysFinancePurchaseBillsPayable> getAll(String page, String limit, String auditState) {
        PageRange pageRange = new PageRange(page,limit);
        Integer auditState1 = NumberUtil.strToInt(auditState);
        return purchaseBillsPayableMapper.getAll(pageRange.getStart(),pageRange.getEnd(),
                auditState1);
    }

    /**
     *
     * @param auditState 单子类型
     * @return
     *      条件查询总条数
     */
    public int getCount(String auditState) {
        Integer auditState1 = NumberUtil.strToInt(auditState);
        return purchaseBillsPayableMapper.getCount(auditState1);
    }

    /**
     *
     * @param purchaseBillsPayable 申请订单
     * @return string
     */
    public String addPurchasePay(SysFinancePurchaseBillsPayable purchaseBillsPayable) {
        purchaseBillsPayable.setId(UUIDUtil.getUUID());
        purchaseBillsPayableMapper.add(purchaseBillsPayable);
        return purchaseBillsPayable.getId();
    }

    /**
     *
     * @param purchaseOrderId 申请单id
     * @return
     */
    public SysFinancePurchaseBillsPayable getPurchasePay(String purchaseOrderId) {
        return purchaseBillsPayableMapper.getPurchasePay(purchaseOrderId);
    }

    /**
     * 添加采购申请应付单
     * @param purchaseOrder 采购申请单
     * @return
     *  string
     */
    public String generatePurchasePay(PurchaseOrder purchaseOrder,String id1) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        SysFinancePurchaseBillsPayable  purchaseBillsPayable = new SysFinancePurchaseBillsPayable();
        purchaseBillsPayable.setId("CWO" + OrderUtil.createOrderNumber());
        purchaseBillsPayable.setPurchaseOrderId(purchaseOrder.getId());
        purchaseBillsPayable.setAuditType(purchaseOrder.getAuditType());
        BigDecimal price = new BigDecimal(purchaseOrder.getPrice());
        price = price.setScale(2,BigDecimal.ROUND_HALF_UP);
        BigDecimal goodsNumber = new BigDecimal(purchaseOrder.getGoodsNumber());
        goodsNumber = goodsNumber.setScale(2,BigDecimal.ROUND_HALF_UP);
        purchaseBillsPayable.setUnitPrice(price.toString());
        purchaseBillsPayable.setGoodsNumber(purchaseOrder.getGoodsNumber());
        purchaseBillsPayable.setTotalPrice(price.multiply(goodsNumber).setScale(2,BigDecimal.ROUND_HALF_UP).toString());
        String documentMaker = (String) request.getSession().getAttribute("sysUser");
        purchaseBillsPayable.setDocumentMaker(documentMaker);
        purchaseBillsPayable.setDocumentMakeTime(new Date());
        purchaseBillsPayable.setAuditState(Constants.Finance.APPLY_ORDER_UNAUDIT);
        //根据id获取applyOrder
        SysFinanceApplyOrder applyOrder = applyOrderMapper.getById1(id1);
        applyOrder.setApplyState(Constants.Finance.APPLY_ORDER_UNAUDIT);
        //更新申请订单的状态
        applyOrderMapper.updateApplyState(applyOrder);
        //更新purchaseOrder的financeState
        purchaseOrder.setFinanceState(Constants.FinanceState.APPLY_PASS_WAIT);
        purchaseOrderMapper.updateByIdAndState(purchaseOrder);
        //添加应付单
        purchaseBillsPayableMapper.add(purchaseBillsPayable);
        return purchaseBillsPayable.getId();
    }

    /**
     * 根据ID获取
     * @param id 主键id
     * @return
     *      lei
     */
    public SysFinancePurchaseBillsPayable getPurchasePayById(String id) {
        return purchaseBillsPayableMapper.getPurchasePayById(id);
    }

    /**
     * a
     * @param attitude purchaseOrder
     * @param id id
     * @param auditType 申请类型
     * @param actualPrice 实际价格
     * @param auditDescribe 审核信息
     *
     * @return a
     */
    public Boolean purchaseOrderPayPass(String attitude ,String id, String auditType,String actualPrice,String auditDescribe,String totalPrice) {
        if(actualPrice == null || "".equals(actualPrice)){
            actualPrice = totalPrice;
        }
        if(auditDescribe == null || "".equals(auditDescribe)){
            throw new BizException(AbstractResult.BIZ_FAIL,"审核内容为空");
        }
        BigDecimal actualPrice1 = new BigDecimal(actualPrice);
        actualPrice1 = actualPrice1.setScale(2,BigDecimal.ROUND_HALF_UP);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Integer auditType1 = NumberUtil.strToInt(auditType);
        //获取PurchaseOrder
        PurchaseOrder purchaseOrder = purchaseOrderMapper.findByOrderNumber(id);
        //获取ApplyOrder
        SysFinanceApplyOrder applyOrder = applyOrderMapper.getByApplyIdAndApplyType(id, auditType1);
        //获取SysFinancePurchaseBillsPayable
        SysFinancePurchaseBillsPayable financePurchaseBillsPayable = purchaseBillsPayableMapper.getPurchasePayBypurchaseOrderIdAndAuditType(id, auditType1);
        String agree = "agree";
        if(agree.equals(attitude)){
            purchaseOrder.setFinanceState(Constants.FinanceState.APPLY_PASS_PAY);
            financePurchaseBillsPayable.setAuditState(Constants.Finance.APPLY_ORDER_PASS);
            applyOrder.setApplyState(Constants.Finance.APPLY_ORDER_PASS);
        }else{
            purchaseOrder.setFinanceState(Constants.FinanceState.NO_PASS);
            financePurchaseBillsPayable.setAuditState(Constants.Finance.APPLY_ORDER_UNPASS);
            applyOrder.setApplyState(Constants.Finance.APPLY_ORDER_UNPASS);

        }
        String auditUser = (String) request.getSession().getAttribute("sysUser");

        purchaseOrder.setFinanceAuditUser(auditUser);
        purchaseOrder.setFinanceAuditTime(new Date());
        purchaseOrder.setFinanceAuditDescribe(auditDescribe);
        purchaseOrder.setDepotState(Constants.DepotState.DEPOT_CHECK);

        financePurchaseBillsPayable.setAuditUser(auditUser);
        financePurchaseBillsPayable.setActualBalance(actualPrice1.toString());
        financePurchaseBillsPayable.setAuditTime(new Date());
        financePurchaseBillsPayable.setAuditDescribe(auditDescribe);

        if(agree.equals(attitude)){
            //生成付款单
            SysFinancePayment payment = new SysFinancePayment();
            payment.setId(UUIDUtil.getUUID());
            payment.setBalance(actualPrice1.toString());
            payment.setDocumentMaker(purchaseOrder.getFinanceAuditUser());
            payment.setDocumentMakeTime(purchaseOrder.getFinanceAuditTime());
            payment.setPayId(financePurchaseBillsPayable.getId());
            payment.setPayType(financePurchaseBillsPayable.getAuditType());
            //添加付款单
            paymentMapper.add(payment);

            //生成账单
            SysFinanceBill financeBill = new SysFinanceBill();
            financeBill.setId(UUIDUtil.getUUID());
            BigDecimal balance = new BigDecimal(financePurchaseBillsPayable.getActualBalance());
            balance = balance.setScale(2,BigDecimal.ROUND_HALF_UP);
            financeBill.setBalance((balance.multiply(new BigDecimal(-1))).toString());
            financeBill.setBillTime(financePurchaseBillsPayable.getAuditTime());
            financeBill.setDepartment(Constants.Finance.PURCHASE);
            financeBill.setApplyUser(purchaseOrder.getApplyUser());
            financeBill.setGoodsName(purchaseOrder.getGoodsName());
            financeBill.setBalanceType(purchaseOrder.getAuditType());
            //添加账单
            billMapper.add(financeBill);

        }
        //更新applyOrder
        applyOrderMapper.updateApplyState(applyOrder);
        //更新purchaseORder
        purchaseOrderMapper.purchaseOrderPayPass(purchaseOrder);
        //更新FinancePurchaseBillPay
        purchaseBillsPayableMapper.update(financePurchaseBillsPayable);

        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public List<String> getPurchasePayChart() {
        return purchaseBillsPayableMapper.getPurchasePayChart();
    }


}
