package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.FinancePurchaseReceivableMapper;
import com.gameloft9.demo.dataaccess.model.system.PurchaseOrder;
import com.gameloft9.demo.dataaccess.model.system.SysFinancePurchaseReceivable;
import com.gameloft9.demo.service.api.system.FinancePurchaseReceivableService;
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

    /**
     *
     * @param page 当前页
     * @param limit 每条条数
     * @param auditType 单子类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     *      采购应收单集合
     */
    public List<SysFinancePurchaseReceivable> getAll(String page, String limit, String auditType, String startTime, String endTime) {
        FinanceServiceUtil util = new FinanceServiceUtil(page,limit,auditType,startTime,endTime);
        return purchaseReceivableMapper.getAll(util.getPageRange().getStart(),util.getPageRange().getEnd(),
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
        return purchaseReceivableMapper.getCount(util.getAuditType(),util.getStartTime(),util.getEndTIme());
    }

    /**
     *
     * @param purchaseOrder 采购申请单
     * @return
     *      string
     */
    public String generatePurchaseReceive(PurchaseOrder purchaseOrder) {
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

        purchaseReceivableMapper.add(purchaseReceivable);
        return purchaseReceivable.getId();
    }
}
