package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.*;
import com.gameloft9.demo.dataaccess.model.system.*;
import com.gameloft9.demo.service.api.system.FinanceSaleBillPayService;
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
public class FinanceSaleBillPayServiceImpl implements FinanceSaleBillPayService {

    @Autowired
    FinanceSaleBillsPayableMapper saleBillsPayableMapper;
    @Autowired
    FinanceApplyOrderMapper applyOrderMapper;
    @Autowired
    ReturnGoodsOrderMapper returnGoodsOrderMapper;
    @Autowired
    FinancePaymentMapper paymentMapper;
    @Autowired
    FinanceBillMapper billMapper;
    @Autowired
    ShipmentOrderMapper shipmentOrderMapper;

    /**
     *
     * @param page 当前页
     * @param limit 每条条数
     * @param auditState 审核状态
     * @return
     *      销售应付单集合
     */
    public List<SysFinanceSaleBillsPayable> getAll(String page, String limit, String auditState) {
        PageRange pageRange = new PageRange(page,limit);
        Integer auditState1 = NumberUtil.strToInt(auditState);

        return saleBillsPayableMapper.getAll(pageRange.getStart(),pageRange.getEnd(),auditState1);

    }

    /**
     *
     * @param auditState 审核状态
     * @return
     *      条件查询总条数
     */
    public int getCount(String auditState) {
        Integer auditState1 = NumberUtil.strToInt(auditState);
        return saleBillsPayableMapper.getCount(auditState1);
    }

    @Override
    public String generateSalePay(ShipmentOrder shipmentOrder, String id1) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        SysFinanceSaleBillsPayable saleBillsPayable = new SysFinanceSaleBillsPayable();

        saleBillsPayable.setId(UUIDUtil.getUUID());
        saleBillsPayable.setSaleRejectedId(shipmentOrder.getId());
        int auditType = NumberUtil.strToInt(shipmentOrder.getAuditType());
        saleBillsPayable.setAuditType(auditType);
        BigDecimal totalPrice = new BigDecimal(shipmentOrder.getGoodsAmount());
        totalPrice.setScale(2,BigDecimal.ROUND_HALF_UP);
        BigDecimal goodsNumber = new BigDecimal(shipmentOrder.getGoodsNumber());
        saleBillsPayable.setUnitPrice((totalPrice.divide(goodsNumber).setScale(2,BigDecimal.ROUND_HALF_UP).toString()));
        saleBillsPayable.setRejectedNumber(shipmentOrder.getGoodsNumber());
        saleBillsPayable.setTotalPrice(totalPrice.setScale(2,BigDecimal.ROUND_HALF_UP).toString());
        String documentMaker = (String) request.getSession().getAttribute("sysUser");
        saleBillsPayable.setDocumentMaker(documentMaker);
        saleBillsPayable.setDocumentMakeTime(new Date());
        saleBillsPayable.setAuditState(Constants.Finance.APPLY_ORDER_UNAUDIT);
        //根据id获取申请订单
        SysFinanceApplyOrder applyOrder = applyOrderMapper.getById1(id1);
        applyOrder.setApplyState(Constants.Finance.APPLY_ORDER_UNAUDIT);
        //更新订单申请状态
        applyOrderMapper.updateApplyState(applyOrder);
        //更新purchaseOrder的financeState
        shipmentOrder.setState(Constants.FinanceState.APPLY_PASS_WAIT);
        shipmentOrderMapper.updateByIdAndState(shipmentOrder);
        //添加销售应付申请单
        saleBillsPayableMapper.add(saleBillsPayable);
        return saleBillsPayable.getId();
    }

    /**
     *
     * @param attitude purchaseOrder
     * @param id id
     * @param auditType 申请类型
     * @param actualPrice 实际价格
     * @param auditDescribe 审核信息
     *
     * @return
     */
    @Override
    public Boolean saleOrderPayPass(String attitude, String id, String auditType, String actualPrice, String auditDescribe) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Integer auditType1 = NumberUtil.strToInt(auditType);
        //获取ShipmentOrder
        ShipmentOrder shipmentOrder = shipmentOrderMapper.getById(id);
        //获取ApplyOrder
        SysFinanceApplyOrder applyOrder = applyOrderMapper.getByApplyIdAndApplyType(id, auditType1);
        //获取SysFinancePurchaseBillsPayable
        SysFinanceSaleBillsPayable saleBillsPayable = saleBillsPayableMapper.getSalePayBysaleRejectedIdAndAuditType(id, auditType1);
        String agree = "agree";
        if(agree.equals(attitude)){
            shipmentOrder.setState(StateUUtil.APPLY_accept);
            saleBillsPayable.setAuditState(Constants.Finance.APPLY_ORDER_PASS);
            applyOrder.setApplyState(Constants.Finance.APPLY_ORDER_PASS);
        }else{
            shipmentOrder.setState(StateUUtil.APPLY_fina_unpass);
            saleBillsPayable.setAuditState(Constants.Finance.APPLY_ORDER_UNPASS);
            applyOrder.setApplyState(Constants.Finance.APPLY_ORDER_UNPASS);

        }
        String auditUser = (String) request.getSession().getAttribute("sysUser");

        shipmentOrder.setAuditUser(auditUser);
        shipmentOrder.setRemarks(auditDescribe);

        saleBillsPayable.setAuditUser(auditUser);
        saleBillsPayable.setActualBalance(actualPrice);
        saleBillsPayable.setAuditTime(new Date());
        saleBillsPayable.setAuditDescribe(auditDescribe);

        if(agree.equals(attitude)){
            //生成付款单
            SysFinancePayment payment = new SysFinancePayment();
            payment.setId(UUIDUtil.getUUID());
            payment.setBalance(actualPrice);
            payment.setDocumentMaker(shipmentOrder.getApplyUser());
            payment.setDocumentMakeTime(new Date());
            payment.setPayId(saleBillsPayable.getId());
            payment.setPayType(saleBillsPayable.getAuditType());
            //添加付款单
            paymentMapper.add(payment);

            //生成账单
            SysFinanceBill financeBill = new SysFinanceBill();
            financeBill.setId(UUIDUtil.getUUID());
            BigDecimal balance = new BigDecimal(saleBillsPayable.getActualBalance());
            balance = balance.setScale(2,BigDecimal.ROUND_HALF_UP);
            financeBill.setBalance(balance.multiply(new BigDecimal(-1)).toString());
            financeBill.setBillTime(saleBillsPayable.getAuditTime());
            financeBill.setDepartment(Constants.Finance.SALE);
            financeBill.setApplyUser(shipmentOrder.getApplyUser());
            financeBill.setGoodsName(shipmentOrder.getGoodsName());
            financeBill.setBalanceType(4);
            //添加账单
            billMapper.add(financeBill);

        }
        //更新applyOrder
        applyOrderMapper.updateApplyState(applyOrder);
        //更新purchaseORder
        shipmentOrderMapper.update(shipmentOrder);
        //更新FinancePurchaseBillPay
        saleBillsPayableMapper.update(saleBillsPayable);

        return true;
    }

    @Override
    public SysFinanceSaleBillsPayable getSalePayById(String id) {
        return saleBillsPayableMapper.getSalePayById(id);
    }

    @Override
    public List<String> getSalePayChart() {
        return saleBillsPayableMapper.getSalePayChart();
    }
}
