package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.*;
import com.gameloft9.demo.dataaccess.model.system.*;
import com.gameloft9.demo.service.api.system.FinanceSaleReceivableService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.*;
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
    @Autowired
    MarkerOrderMapper markerOrderMapper;



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
        BigDecimal totalPrice = new BigDecimal(shipmentOrder.getGoodsAmount());
        //BigDecimal goodsNumber = new BigDecimal(shipmentOrder.getGoodsNumber());
        String goodsNumber = shipmentOrder.getGoodsNumber();
        totalPrice.setScale(2,BigDecimal.ROUND_HALF_UP);
        saleReceivable.setUnitPrice(totalPrice.divide(new BigDecimal(goodsNumber))+"");
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
        MarkerOrderTest markerOrderTest = markerOrderMapper.getMaker(id);
        //获取ApplyOrder
        SysFinanceApplyOrder applyOrder = applyOrderMapper.getByApplyIdAndApplyType(id, auditType1);
        //获取SysFinancePurchaseBillsPayable
        SysFinanceSaleReceivable saleReceivable = saleReceivableMapper.getSaleReceiveBysaleIdAndAuditType(id, auditType1);
        String agree = "agree";
        if(agree.equals(attitude)){
            markerOrderTest.setState(StateUUtil.APPLY_fina_pass);
            saleReceivable.setAuditState(Constants.Finance.APPLY_ORDER_PASS);
            applyOrder.setApplyState(Constants.Finance.APPLY_ORDER_PASS);
        }else{
            markerOrderTest.setState(StateUUtil.APPLY_fina_unpass);
            saleReceivable.setAuditState(Constants.Finance.APPLY_ORDER_UNPASS);
            applyOrder.setApplyState(Constants.Finance.APPLY_ORDER_UNPASS);

        }
        String auditUser = (String) request.getSession().getAttribute("sysUser");

        markerOrderTest.setOrderAuditUser(auditUser);
        markerOrderTest.setRemarks(auditDescribe);

        saleReceivable.setAuditUser(auditUser);
        saleReceivable.setActualBalance(actualPrice);
        saleReceivable.setAuditTime(new Date());
        saleReceivable.setAuditDescribe(auditDescribe);

        if(agree.equals(attitude)){
            //生成付款单
            SysFinanceReceipt receipt = new SysFinanceReceipt();
            receipt.setId(UUIDUtil.getUUID());
            receipt.setBalance(actualPrice);
            receipt.setDocumentMaker(markerOrderTest.getApplyUser());
            receipt.setDocumentMakeTime(new Date());
            receipt.setReceiveId(saleReceivable.getId());
            receipt.setReceiveType(saleReceivable.getAuditType());
            //添加付款单
            receiptMapper.add(receipt);

            //生成账单
            SysFinanceBill financeBill = new SysFinanceBill();
            financeBill.setId(UUIDUtil.getUUID());
            BigDecimal balance = new BigDecimal(saleReceivable.getActualBalance());
            balance = balance.setScale(2,BigDecimal.ROUND_HALF_UP);
            financeBill.setBalance(balance.toString());
            financeBill.setBillTime(saleReceivable.getAuditTime());
            financeBill.setDepartment(Constants.Finance.SALE);
            financeBill.setApplyUser(markerOrderTest.getApplyUser());
            financeBill.setGoodsName(markerOrderTest.getProductId());
            financeBill.setBalanceType(2);
            //添加账单
            billMapper.add(financeBill);

        }
        //更新applyOrder
        applyOrderMapper.updateApplyState(applyOrder);
        //更新purchaseORder
        markerOrderMapper.update(markerOrderTest);
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
