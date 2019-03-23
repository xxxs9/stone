package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.SysFinanceGathering;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/03/20 2019-03-20
 */
public interface FinanceGatheringMapper extends BaseMapper<SysFinanceGathering> {

    /**
     * 收款单列表
     *
     * @param start 开始
     * @param end 结束
     * @param receiveType 单子类型
     * @param startTime 制单时间
     * @param endTime 制单时间
     * @return
     *      收款单集合
     */
    List<SysFinanceGathering> getAll(
            @Param("start") int start,
            @Param("end") int end,
            @Param("receiveType") int receiveType,
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime
    );

    /**
     * 条件查询总条数
     *
     * @param receiveType 单子类型
     * @param startTime 制单时间
     * @param endTime 制单时间
     * @return 条件查询总条数
     */
    int getCount(
            @Param("receiveType") int receiveType,
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime
    );

}
