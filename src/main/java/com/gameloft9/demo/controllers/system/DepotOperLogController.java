package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.dao.system.DepotOperLogMapper;
import com.gameloft9.demo.dataaccess.model.system.DepotOperLog;
import com.gameloft9.demo.dataaccess.model.system.SysOperLogTest;
import com.gameloft9.demo.mgrframework.annotation.BizOperLog;
import com.gameloft9.demo.mgrframework.annotation.UserOperLog;
import com.gameloft9.demo.mgrframework.beans.constant.OperType;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.DepotOperLogService;
import com.gameloft9.demo.service.beans.system.LogBatchDelRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * @author: Sxiu
 * @create: 2019/4/14 20:17
 * @description:
 */
@Slf4j
@Controller
@RequestMapping("/depotLog")
public class DepotOperLogController {
    @Autowired
    DepotOperLogService depotOperLogServiceImpl;
    /**
     * 获取所有日志列表
     * @param page 页序
     * @param limit 分页大小
     * */
    @RequestMapping(value = "/logList.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getLogList(String page, String limit, String loginName, String operType, String startTime, String endTime){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new PageResultBean<Collection<DepotOperLog>>(depotOperLogServiceImpl.getAll(page,limit,loginName,operType,startTime,endTime),depotOperLogServiceImpl.countGetAll(loginName,operType,startTime,endTime));
    }

    /**
     * 批量删除
     * */
    @RequestMapping(value = "/batchDelete.do",method = RequestMethod.POST)
    @ResponseBody
    @UserOperLog(operType = OperType.DELETE,operationName ="批量删除库存日志")
    public IResult batchDelete(String ids){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(depotOperLogServiceImpl.batchDelete(ids));
    }

    /**
     * 删除
     * @param id 日志Id
     * */
    @RequestMapping(value = "/delete.do",method = RequestMethod.POST)
    @ResponseBody
    @UserOperLog(operType = OperType.DELETE,operationName ="删除库存日志")
    public IResult delete(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(depotOperLogServiceImpl.delete(id));
    }
}
