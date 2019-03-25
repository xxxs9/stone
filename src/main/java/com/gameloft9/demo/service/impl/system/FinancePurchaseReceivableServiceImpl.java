package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.FinanceApplyOrderMapper;
import com.gameloft9.demo.dataaccess.dao.system.FinancePurchaseReceivableMapper;
import com.gameloft9.demo.dataaccess.dao.system.PurchaseOrderMapper;
import com.gameloft9.demo.dataaccess.model.system.PurchaseOrder;
import com.gameloft9.demo.dataaccess.model.system.SysFinanceApplyOrder;
import com.gameloft9.demo.dataaccess.model.system.SysFinancePurchaseReceivable;
import com.gameloft9.demo.service.api.system.FinancePurchaseReceivableService;
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
        //根据id获取申请订单
        SysFinanceApplyOrder applyOrder = applyOrderMapper.getById1(id1);
        applyOrder.setApplyState(Constants.Finance.APPLY_ORDER_UNAUDIT);
        //更新订单申请状态
        applyOrderMapper.updateApplyState(applyOrder);
        //更新purchaseOrder的financeState
        purchaseOrder.setFinanceState(Constants.FinanceState.APPLY_PASS_WAIT);

        //添加销售应付申请单
        purchaseReceivableMapper.add(purchaseReceivable);
        return purchaseReceivable.getId();
    }
}
