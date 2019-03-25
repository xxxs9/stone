package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.SysFinanceApplyOrder;
import com.gameloft9.demo.dataaccess.model.system.SysFinancePurchaseBillsPayable;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/03/22 2019-03-22
 */

public interface FinanceApplyOrderMapper extends BaseMapper<SysFinanceApplyOrder>{

    /**
     * 待审核申请集合
     *
     * @param start 开始
     * @param end 结束
     * @param applyType 申请类型
     * @param applyState 审核状态
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     *      待审核申请集合
     *
     */
    List<SysFinanceApplyOrder> getAll(
            @Param("start") int start,
            @Param("end") int end,
            @Param("applyType") int applyType,
            @Param("applyState") int applyState,
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime);

    /**
     * 条件查询总条数
     *
     * @param applyType 申请类型
     * @param applyState 审核状态
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    int getCount(
            @Param("applyType") int applyType,
            @Param("applyState") int applyState,
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime);

    /**
     * 根据id获取applyOrder
     *
     * @param id1 主键id
     * @return
     *      applyOrder
     */
    SysFinanceApplyOrder getById1(@Param("id1") String id1);

    /**
     * 更新申请单的审核状态
     *
     * @param financeApplyOrder 申请单
     */
    void updateApplyState(SysFinanceApplyOrder financeApplyOrder);


}
