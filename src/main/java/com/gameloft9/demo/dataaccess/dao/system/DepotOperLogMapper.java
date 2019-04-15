package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.DepotInventory;
import com.gameloft9.demo.dataaccess.model.system.DepotOperLog;
import com.gameloft9.demo.dataaccess.model.system.SysOperLogTest;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface DepotOperLogMapper {

    /**
     * 根据主键删除库存日志信息
     * @param id 库存日志id
     * */
    int deleteByPrimaryKey(@Param("id") String id);

    /**
     * 新增库存日志信息
     * @param record 库存日志
     * */
    int insertSelective(DepotOperLog record);

    /**
     * 根据主键获取库存日志信息
     * @param id 库存日志主键
     * */
    DepotOperLog getById(@Param("id") String id);

    /**
     * 根据更新库存日志信息
     * @param record 库存日志
     * */
    int updateByPrimaryKeySelective(SysOperLogTest record);

    /**
     * 获取所有日志
     * @param start 开始序号
     * @param end 结束序号
     * @param loginName 登录名
     * @param operType 操作类型
     * @param startDate 开始时间
     * @param endDate 结束时间
     * */
    List<DepotOperLog> getAll(@Param("start") int start,
                                   @Param("end") int end,
                                   @Param("loginName") String loginName,
                                   @Param("operType") String operType,
                                   @Param("startDate") Date startDate,
                                   @Param("endDate") Date endDate);

    /**
     * 获取所有库存日志数量
     * @param loginName 登录名
     * @param operType 操作类型
     * @param startDate 开始时间
     * @param endDate 结束时间
     * */
    int countGetAll( @Param("loginName") String loginName,
                        @Param("operType") String operType,
                        @Param("startDate") Date startDate,
                        @Param("endDate") Date endDate);

    /**
     * 根据列表删除
     * @param delIds 删除ids
     * */
    boolean deleteByList(@Param("ids") List<String> delIds);
}
