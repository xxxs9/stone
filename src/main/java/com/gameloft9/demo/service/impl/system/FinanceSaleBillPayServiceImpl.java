package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.*;
import com.gameloft9.demo.dataaccess.model.system.*;
import com.gameloft9.demo.service.api.system.FinanceSaleBillPayService;
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
    public String generateSalePay(ReturnGoodsOrder returnGoodsOrder, String id1) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        SysFinanceSaleBillsPayable saleBillsPayable = new SysFinanceSaleBillsPayable();

        saleBillsPayable.setId(UUIDUtil.getUUID());
        saleBillsPayable.setSaleRejectedId(returnGoodsOrder.getId());
        int auditType = NumberUtil.strToInt(returnGoodsOrder.getAuditType());
        saleBillsPayable.setAuditType(auditType);
        int totalPrice = NumberUtil.strToInt(returnGoodsOrder.getGoodsAmount());
        int goodsNumber = NumberUtil.strToInt(returnGoodsOrder.getGoodsNumber());
        saleBillsPayable.setUnitPrice(totalPrice/goodsNumber+"");
        saleBillsPayable.setRejectedNumber(returnGoodsOrder.getGoodsNumber());
        saleBillsPayable.setTotalPrice(totalPrice+"");
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
        returnGoodsOrder.setState(Constants.FinanceState.APPLY_PASS_WAIT);
        returnGoodsOrderMapper.updateByIdAndState(returnGoodsOrder);
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
        //获取PurchaseOrder
        ReturnGoodsOrder returnGoodsOrder = returnGoodsOrderMapper.findByIdAndAuditType(id, auditType1);
        //获取ApplyOrder
        SysFinanceApplyOrder applyOrder = applyOrderMapper.getByApplyIdAndApplyType(id, auditType1);
        //获取SysFinancePurchaseBillsPayable
        SysFinanceSaleBillsPayable saleBillsPayable = saleBillsPayableMapper.getSalePayBysaleRejectedIdAndAuditType(id, auditType1);
        String agree = "agree";
        if(agree.equals(attitude)){
            returnGoodsOrder.setState(Constants.FinanceState.APPLY_PASS_PAY);
            saleBillsPayable.setAuditState(Constants.Finance.APPLY_ORDER_PASS);
            applyOrder.setApplyState(Constants.Finance.APPLY_ORDER_PASS);
        }else{
            returnGoodsOrder.setState(Constants.FinanceState.NO_PASS);
            saleBillsPayable.setAuditState(Constants.Finance.APPLY_ORDER_UNPASS);
            applyOrder.setApplyState(Constants.Finance.APPLY_ORDER_UNPASS);

        }
        String auditUser = (String) request.getSession().getAttribute("sysUser");

        returnGoodsOrder.setAuditUser(auditUser);
        returnGoodsOrder.setRemarks(auditDescribe);

        saleBillsPayable.setAuditUser(auditUser);
        saleBillsPayable.setActualBalance(actualPrice);
        saleBillsPayable.setAuditTime(new Date());
        saleBillsPayable.setAuditDescribe(auditDescribe);

        if(agree.equals(attitude)){
            //生成付款单
            SysFinancePayment payment = new SysFinancePayment();
            payment.setId(UUIDUtil.getUUID());
            payment.setBalance(actualPrice);
            payment.setDocumentMaker(returnGoodsOrder.getApplyUser());
            payment.setDocumentMakeTime(new Date());
            payment.setPayId(saleBillsPayable.getId());
            payment.setPayType(saleBillsPayable.getAuditType());
            //添加付款单
            paymentMapper.add(payment);

            //生成账单
            SysFinanceBill financeBill = new SysFinanceBill();
            financeBill.setId(UUIDUtil.getUUID());
            Integer balance = Integer.parseInt(saleBillsPayable.getActualBalance());
            financeBill.setBalance(balance*(-1));
            financeBill.setBillTime(saleBillsPayable.getAuditTime());
            financeBill.setDepartment(Constants.Finance.SALE);
            //添加账单
            billMapper.add(financeBill);

        }
        //更新applyOrder
        applyOrderMapper.updateApplyState(applyOrder);
        //更新purchaseORder
        returnGoodsOrderMapper.returnGoodsOrderPayPass(returnGoodsOrder);
        //更新FinancePurchaseBillPay
        saleBillsPayableMapper.update(saleBillsPayable);

        return true;
    }

    @Override
    public SysFinanceSaleBillsPayable getSalePayById(String id) {
        return saleBillsPayableMapper.getSalePayById(id);
    }
}
