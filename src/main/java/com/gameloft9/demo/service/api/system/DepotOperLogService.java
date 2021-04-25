package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.DepotOperLog;

import java.util.List;

public interface DepotOperLogService {

    /**
     * 插入库存操作日志
     * @param userId 用户id
     * @param loginName 登录名
     * @param ipAddr ip地址
     * @param operType 操作类型
     * @param operationName 操作描述
     * */
    int insertOperLog(String userId,String loginName,String ipAddr,String operType,String operationName);

    /**
     * 获取所有库存操作日志
     * @param page 页序
     * @param limit 分页大小
     * @param loginName 登录名
     * @param operType 操作类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * */
    List<DepotOperLog> getAll(String page, String limit, String loginName, String operType, String startTime, String endTime);

    /**
     * 获取所有库存操作日志个数
     * @param loginName 登录名
     * @param operType 操作类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * */
    int countGetAll(String loginName, String operType, String startTime, String endTime);

    /**
     * 根据id删除记录
     * @param id 库存操作日志id
     * */
    boolean delete(String id);

    /**
     * 批量删除库存操作日志
     * @param delIds 请求
     * */
    boolean batchDelete(String delIds);
}
