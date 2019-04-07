package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.Num;
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
     * @param auditState 状态
     * @return
     *      采购应付单集合
     */
    List<SysFinancePurchaseBillsPayable> getAll(
            @Param("start") int start,
            @Param("end") int end,
            @Param("auditState") int auditState
    );

    /**
     * 条件查询总条数
     *
     * @param auditState 状态
     * @return 条件查询总条数
     */
    int getCount(
            @Param("auditState") int auditState
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

    /**
     * 获取采购支出图表数据
     * @return
     */
    List<String> getPurchasePayChart();


}
