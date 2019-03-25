package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.SysFinanceApplyOrder;
import com.gameloft9.demo.dataaccess.model.system.SysFinancePurchaseBillsPayable;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/03/19 2019-03-19
 */
public interface FinancePurchaseBillsPayableMapper extends BaseMapper<SysFinancePurchaseBillsPayable>{

    /**
     * 采购应付单列表
     *
     * @param start 开始
     * @param end 结束
     * @param auditType 单子类型
     * @param startTime 制单时间
     * @param endTime 制单时间
     * @return
     *      采购应付单集合
     */
    List<SysFinancePurchaseBillsPayable> getAll(
            @Param("start") int start,
            @Param("end") int end,
            @Param("auditType") int auditType,
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime
    );

    /**
     * 条件查询总条数
     *
     * @param auditType 单子类型
     * @param startTime 制单时间
     * @param endTime 制单时间
     * @return 条件查询总条数
     */
    int getCount(
            @Param("auditType") int auditType,
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime
    );

    /**
     * 根据申请单id查找应付单
     *
     * @param purchaseOrderId 申请单id
     * @return
     *      采购应付单
     */
    SysFinancePurchaseBillsPayable getPurchasePay(@Param("purchaseOrderId") String purchaseOrderId);


    /**
     * 根据id获取
     *
     * @param id 主键id
     * @return
     *      applyOrder
     */
    SysFinancePurchaseBillsPayable getPurchasePayById(@Param("id") String id);

    /**
     * 根据purchaseOrderId 和auditType
     *
     * @param purchaseOrderId 采购订单编号
     * @param auditType 采购订单类型
     * @return
     *
     */
    SysFinancePurchaseBillsPayable getPurchasePayBypurchaseOrderIdAndAuditType(
            @Param("purchaseOrderId") String purchaseOrderId,
            @Param("auditType") Integer auditType
    );


}
