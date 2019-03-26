package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.SysFinancePurchaseReceivable;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/03/19 2019-03-19
 */

public interface FinancePurchaseReceivableMapper extends BaseMapper<SysFinancePurchaseReceivable> {

    /**
     * 采购应收单列表
     *
     * @param start 开始
     * @param end 结束
     * @param auditState 审核状态
     * @return
     *      采购应收单集合
     */
    List<SysFinancePurchaseReceivable> getAll(
            @Param("start") int start,
            @Param("end") int end,
            @Param("auditState") int auditState
    );

    /**
     * 条件查询总条数
     *
     * @param auditState 审核状态
     * @return 条件查询总条数
     */
    int getCount(
            @Param("auditState") int auditState
    );

    /**
     * 根据id获取
     *
     * @param id 主键id
     * @return
     *      SysFinancePurchaseReceivable
     */
    SysFinancePurchaseReceivable getPurchaseReceiveById(@Param("id") String id);

    /**
     * 根据id和订单类型查找
     * @param purchaseOrderRejectedId id
     * @param auditType 订单类型
     * @return
     *      SysFinancePurchaseReceivable
     */
    SysFinancePurchaseReceivable getPurchaseReceiveBypurchaseOrderIdAndAuditType(
            @Param("purchaseOrderRejectedId") String purchaseOrderRejectedId,
            @Param("auditType") Integer auditType);


}
