package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.SysFinancePurchaseBillsPayable;
import com.gameloft9.demo.dataaccess.model.system.SysFinanceSaleBillsPayable;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/03/19 2019-03-19
 */

public interface FinanceSaleBillsPayableMapper extends BaseMapper<SysFinanceSaleBillsPayable>{

    /**
     * 销售应付单列表
     *
     * @param start 开始
     * @param end 结束
     * @param auditState 单子类型
     * @return
     *      销售应付单集合
     */
    List<SysFinanceSaleBillsPayable> getAll(
            @Param("start") int start,
            @Param("end") int end,
            @Param("auditState") int auditState
    );

    /**
     * 条件查询总条数
     *
     * @param auditState 单子类型
     * @return 条件查询总条数
     */
    int getCount(
            @Param("auditState") int auditState
    );

    /**
     * 根据purchaseOrderId 和auditType
     *
     * @param saleRejectedId 采购订单编号
     * @param auditType 采购订单类型
     * @return
     *
     */
    SysFinanceSaleBillsPayable getSalePayBysaleRejectedIdAndAuditType(
            @Param("saleRejectedId") String saleRejectedId,
            @Param("auditType") Integer auditType
    );

    /**
     * 根据id获取
     *
     * @param id 主键id
     * @return
     *      SysFinanceSaleBillsPayable
     */
    SysFinanceSaleBillsPayable getSalePayById(@Param("id") String id);
}
