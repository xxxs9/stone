package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.*;
import com.gameloft9.demo.dataaccess.model.system.*;
import com.gameloft9.demo.service.api.system.FinanceSaleReceivableService;
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
@Transactional(rollbackFor = Exception.class)
public class FinanceSaleReceivableServiceImpl implements FinanceSaleReceivableService {

    @Autowired
    FinanceSaleReceivableMapper saleReceivableMapper;
    @Autowired
    FinanceApplyOrderMapper applyOrderMapper;
    @Autowired
    ShipmentOrderMapper shipmentOrderMapper;
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
     *      销售应付单集合
     */
    public List<SysFinanceSaleReceivable> getAll(String page, String limit, String auditState) {
        PageRange pageRange = new PageRange(page,limit);
        Integer auditState1 = NumberUtil.strToInt(auditState);
        return saleReceivableMapper.getAll(pageRange.getStart(),pageRange.getEnd(),auditState1);

    }

    /**
     *
     * @param auditState 审核状态
     * @return
     *      条件查询总条数
     */
    public int getCount(String auditState) {
        Integer auditState1 = NumberUtil.strToInt(auditState);
        return saleReceivableMapper.getCount(auditState1);
    }

    @Override
    public String generateSaleReceive(ShipmentOrder shipmentOrder, String id1) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        SysFinanceSaleReceivable saleReceivable = new SysFinanceSaleReceivable();

        saleReceivable.setId(UUIDUtil.getUUID());
        saleReceivable.setSaleId(shipmentOrder.getId());
        int auditType = NumberUtil.strToInt(shipmentOrder.getAuditType());
        saleReceivable.setAuditType(auditType);
        int totalPrice = NumberUtil.strToInt(shipmentOrder.getGoodsAmount());
        int goodsNumber = NumberUtil.strToInt(shipmentOrder.getGoodsNumber());
        saleReceivable.setUnitPrice(totalPrice/goodsNumber+"");
        saleReceivable.setProductNumber(shipmentOrder.getGoodsNumber());
        saleReceivable.setTotalPrice(totalPrice+"");
        String documentMaker = (String) request.getSession().getAttribute("sysUser");
        saleReceivable.setDocumentMaker(documentMaker);
        saleReceivable.setDocumentMakeTime(new Date());
        saleReceivable.setAuditState(Constants.Finance.APPLY_ORDER_UNAUDIT);
        //根据id获取申请订单
        SysFinanceApplyOrder applyOrder = applyOrderMapper.getById1(id1);
        applyOrder.setApplyState(Constants.Finance.APPLY_ORDER_UNAUDIT);
        //更新订单申请状态
        applyOrderMapper.updateApplyState(applyOrder);
        //更新purchaseOrder的financeState
        shipmentOrder.setState(Constants.FinanceState.APPLY_PASS_WAIT);
        shipmentOrderMapper.updateByIdAndState(shipmentOrder);
        //添加销售应付申请单
        saleReceivableMapper.add(saleReceivable);
        return saleReceivable.getId();

    }

    @Override
    public Boolean saleOrderReceivePass(String attitude, String id, String auditType, String actualPrice, String auditDescribe) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Integer auditType1 = NumberUtil.strToInt(auditType);
        //获取PurchaseOrder
        ShipmentOrder shipmentOrder = shipmentOrderMapper.findByIdAndAuditType(id, auditType1);
        //获取ApplyOrder
        SysFinanceApplyOrder applyOrder = applyOrderMapper.getByApplyIdAndApplyType(id, auditType1);
        //获取SysFinancePurchaseBillsPayable
        SysFinanceSaleReceivable saleReceivable = saleReceivableMapper.getSaleReceiveBysaleIdAndAuditType(id, auditType1);
        String agree = "agree";
        if(agree.equals(attitude)){
            shipmentOrder.setState(Constants.FinanceState.APPLY_PASS_PAY);
            saleReceivable.setAuditState(Constants.Finance.APPLY_ORDER_PASS);
            applyOrder.setApplyState(Constants.Finance.APPLY_ORDER_PASS);
        }else{
            shipmentOrder.setState(Constants.FinanceState.NO_PASS);
            saleReceivable.setAuditState(Constants.Finance.APPLY_ORDER_UNPASS);
            applyOrder.setApplyState(Constants.Finance.APPLY_ORDER_UNPASS);

        }
        String auditUser = (String) request.getSession().getAttribute("sysUser");

        shipmentOrder.setAuditUser(auditUser);
        shipmentOrder.setRemarks(auditDescribe);

        saleReceivable.setAuditUser(auditUser);
        saleReceivable.setActualBalance(actualPrice);
        saleReceivable.setAuditTime(new Date());
        saleReceivable.setAuditDescribe(auditDescribe);

        if(agree.equals(attitude)){
            //生成付款单
            SysFinanceReceipt receipt = new SysFinanceReceipt();
            receipt.setId(UUIDUtil.getUUID());
            receipt.setBalance(actualPrice);
            receipt.setDocumentMaker(shipmentOrder.getApplyUser());
            receipt.setDocumentMakeTime(new Date());
            receipt.setReceiveId(saleReceivable.getId());
            receipt.setReceiveType(saleReceivable.getAuditType());
            //添加付款单
            receiptMapper.add(receipt);

            //生成账单
            SysFinanceBill financeBill = new SysFinanceBill();
            financeBill.setId(UUIDUtil.getUUID());
            Integer balance = Integer.parseInt(saleReceivable.getActualBalance());
            financeBill.setBalance(balance*(-1));
            financeBill.setBillTime(saleReceivable.getAuditTime());
            financeBill.setDepartment(Constants.Finance.SALE);
            //添加账单
            billMapper.add(financeBill);

        }
        //更新applyOrder
        applyOrderMapper.updateApplyState(applyOrder);
        //更新purchaseORder
        shipmentOrderMapper.shipmentOrderPayPass(shipmentOrder);
        //更新FinancePurchaseBillPay
        saleReceivableMapper.update(saleReceivable);

        return true;
    }

    @Override
    public SysFinanceSaleReceivable getSaleReceiveById(String id) {
        return saleReceivableMapper.getSaleReceiveById(id);
    }

    @Override
    public List<String> getSaleReceiveChart() {
        return saleReceivableMapper.getSaleReceiveChart();
    }
}
