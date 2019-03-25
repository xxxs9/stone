package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.FinanceApplyOrderMapper;
import com.gameloft9.demo.dataaccess.dao.system.FinancePurchaseBillsPayableMapper;
import com.gameloft9.demo.dataaccess.model.system.PurchaseOrder;
import com.gameloft9.demo.dataaccess.model.system.SysFinanceApplyOrder;
import com.gameloft9.demo.dataaccess.model.system.SysFinancePurchaseBillsPayable;
import com.gameloft9.demo.service.api.system.FinancePurchaseBillPayService;
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
public class FinancePurchaseBillPayServiceImpl implements FinancePurchaseBillPayService {

    @Autowired
    FinancePurchaseBillsPayableMapper purchaseBillsPayableMapper;
    @Autowired
    FinanceApplyOrderMapper applyOrderMapper;

    /**
     *
     * @param page 当前页
     * @param limit 每条条数
     * @param auditType 单子类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     *      采购应付单集合
     */
    public List<SysFinancePurchaseBillsPayable> getAll(String page, String limit, String auditType, String startTime, String endTime) {
        FinanceServiceUtil util = new FinanceServiceUtil(page,limit,auditType,startTime,endTime);
        return purchaseBillsPayableMapper.getAll(util.getPageRange().getStart(),util.getPageRange().getEnd(),
                util.getAuditType(),util.getStartTime(),util.getEndTIme());
    }

    /**
     *
     * @param auditType 单子类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     *      条件查询总条数
     */
    public int getCount(String auditType, String startTime, String endTime) {
        FinanceServiceUtil util = new FinanceServiceUtil(auditType,startTime,endTime);
        return purchaseBillsPayableMapper.getCount(util.getAuditType(), util.getStartTime(), util.getEndTIme());
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
        purchaseBillsPayable.setId(UUIDUtil.getUUID());
        purchaseBillsPayable.setPurchaseOrderId(purchaseOrder.getId());
        purchaseBillsPayable.setAuditType(purchaseOrder.getAuditType());
        int price = NumberUtil.strToInt(purchaseOrder.getPrice());
        int goodsNumber = NumberUtil.strToInt(purchaseOrder.getGoodsNumber());
        purchaseBillsPayable.setUnitPrice(purchaseOrder.getPrice());
        purchaseBillsPayable.setGoodsNumber(purchaseOrder.getGoodsNumber());
        purchaseBillsPayable.setTotalPrice(price*goodsNumber+"");
        String documentMaker = (String) request.getSession().getAttribute("sysUser");
        purchaseBillsPayable.setDocumentMaker(documentMaker);
        purchaseBillsPayable.setDocumentMakeTime(new Date());
        //根据id获取applyOrder
        SysFinanceApplyOrder applyOrder = applyOrderMapper.getById1(id1);
        applyOrder.setApplyState(Constants.Finance.APPLY_ORDER_UNAUDIT);
        //更新申请订单的状态
        applyOrderMapper.updateApplyState(applyOrder);

        //添加应付单
        purchaseBillsPayableMapper.add(purchaseBillsPayable);
        return purchaseBillsPayable.getId();
    }


}
