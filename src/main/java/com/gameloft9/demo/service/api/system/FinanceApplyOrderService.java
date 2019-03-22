package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.SysFinanceApplyOrder;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/03/22 2019-03-22
 */

public interface FinanceApplyOrderService {

    /**
     * 待审核申请集合
     *
     * @param page 开始
     * @param limit 结束
     * @param applyType 申请类型
     * @param applyState 审核状态
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     *      待审核申请集合
     *
     */
    List<SysFinanceApplyOrder> getAll(
            String page,
            String limit,
            String applyType,
            String applyState,
            String startTime,
            String endTime);

    /**
     * 条件查询总条数
     *
     * @param page 申请类型
     * @param limit 审核状态
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    int getCount(
            String applyType,
            String applyState,
            String startTime,
            String endTime);
}
