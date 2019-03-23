package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.SysFinanceApplyOrder;
import com.gameloft9.demo.dataaccess.model.system.SysFinancePurchaseBillsPayable;

import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/03/19 2019-03-19
 */

public interface FinancePurchaseBillPayService {

    /**
     * 采购应付单列表
     *
     * @param page 当前页
     * @param limit 每条条数
     * @param auditType 单子类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     *      采购应付单集合
     */
    List<SysFinancePurchaseBillsPayable> getAll(String page, String limit, String auditType, String startTime,String endTime);


    /**
     * 条件查询总条数
     *
     * @param auditType 单子类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     *          条件查询总条数
     */
    int getCount(String auditType, String startTime, String endTime);

    /**
     * 添加申请订单
     *
     * @param financePurchaseBillsPayable 申请订单
     * @return
     *      String
     */
    String addPurchasePay(SysFinancePurchaseBillsPayable financePurchaseBillsPayable);

    /**
     * 根据申请单id查找应付单
     *
     * @param purchaseOrderId 申请单id
     * @return
     *      采购应付单
     */
    SysFinancePurchaseBillsPayable getPurchasePay(String purchaseOrderId);
}
