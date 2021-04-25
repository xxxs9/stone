package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.DepotOperLogMapper;
import com.gameloft9.demo.dataaccess.model.system.DepotOperLog;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import com.gameloft9.demo.service.api.system.DepotOperLogService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.DateUtil;
import com.gameloft9.demo.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: Sxiu
 * @create: 2019/4/14 20:07
 * @dscription:
 */

@Service
@Slf4j
@Transactional
public class DepotOperLogServiceImpl implements DepotOperLogService {
    @Autowired
    DepotOperLogMapper depotOperLogMapper;

    /**
     * 插入库存操作日志
     * @param userId 用户id
     * @param loginName 登录名
     * @param ipAddr ip地址
     * @param operType 操作类型
     * @param operationName 操作描述
     * */
    public int insertOperLog(String userId,String loginName,String ipAddr,String operType,String operationName){
        DepotOperLog log = new DepotOperLog();
        log.setId(UUIDUtil.getUUID());
        log.setCreateDate(new Date());
        log.setUserId(userId);
        log.setIpAddr(ipAddr);
        log.setLoginName(loginName);
        log.setOperType(operType);
        log.setOperationName(operationName);
        return depotOperLogMapper.insertSelective(log);
    }

    /**
     * 获取所有日志
     * @param page 页序
     * @param limit 分页大小
     * @param loginName 登录名
     * @param operType 操作类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * */
    public List<DepotOperLog> getAll(String page, String limit, String loginName, String operType, String startTime, String endTime){
        PageRange pageRange = new PageRange(page,limit);

        //解析日期
        Date startDate = null,endDate=null;
        if(StringUtils.isNotBlank(startTime)){
            startDate = DateUtil.str2Date(startTime);
        }
        if(StringUtils.isNotBlank(endTime)){
            endDate = DateUtil.str2Date(endTime);
        }

        return depotOperLogMapper.getAll(pageRange.getStart(),pageRange.getEnd(),loginName,operType,startDate,endDate);
    }

    /**
     * 获取所有库存操作日志个数
     * @param loginName 登录名
     * @param operType 操作类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * */
    public int countGetAll(String loginName, String operType, String startTime, String endTime){
        //解析日期
        Date startDate = null,endDate=null;
        if(StringUtils.isNotBlank(startTime)){
            startDate = DateUtil.str2Date(startTime);
        }
        if(StringUtils.isNotBlank(endTime)){
            endDate = DateUtil.str2Date(endTime);
        }
        return depotOperLogMapper.countGetAll(loginName,operType,startDate,endDate);
    }

    /**
     * 根据id删除记录
     * @param id 库存操作日志id
     * */
    public boolean delete(String id){
        CheckUtil.notBlank(id,"库存操作日志id为空");

        depotOperLogMapper.deleteByPrimaryKey(id);
        return true;
    }

    /**
     * 批量删除库存操作日志
     * @param delIds 删除ids
     * */
    public boolean batchDelete(String delIds){
        CheckUtil.notBlank(delIds, "库存操作日志ids为空");
        List<String> depotOperLogIds = new ArrayList<String>();
        String[] split = delIds.split(",");
        for (String s : split) {
            depotOperLogIds.add(s);
        }
        depotOperLogMapper.deleteByList(depotOperLogIds);
        return true;
    }
}
