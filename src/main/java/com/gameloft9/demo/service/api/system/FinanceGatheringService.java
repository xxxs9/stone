package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.SysFinanceGathering;

import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/03/20 2019-03-20
 */

public interface FinanceGatheringService {

    /**
     * 收款单列表
     *
     * @param page 当前页
     * @param limit 每条条数
     * @param receiveType 单子类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     *      收款单集合
     */
    List<SysFinanceGathering> getAll(String page, String limit, String receiveType, String startTime, String endTime);


    /**
     * 条件查询总条数
     *
     * @param receiveType 单子类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     *          条件查询总条数
     */
    int getCount(String receiveType, String startTime, String endTime);
}