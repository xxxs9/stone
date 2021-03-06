package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.SysDepot;
import com.gameloft9.demo.mgrframework.annotation.BizOperLog;
import com.gameloft9.demo.mgrframework.beans.constant.OperType;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.SysDepotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

@Slf4j
@Service
@RequestMapping("/depotSet")
public class SysDepotController {
    @Autowired
    private SysDepotService sysDepotServiceImpl;

    /**
     * 获取仓库列表
     * @param page                  页序
     * @param limit                 分页大小
     * @param depotNumber           仓库编号
     * @param depotName             仓库名称
     * @param depotType             仓库类型
     */
    @RequestMapping(value = "/depotList.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getDepotList(String page, String limit,String depotNumber,String depotName,String depotType){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new PageResultBean<Collection<SysDepot>>(sysDepotServiceImpl.getAll(page,limit,depotNumber,depotName,depotType),sysDepotServiceImpl.getAll(page,limit,depotNumber,depotName,depotType).size());
    }

    /**
     * 添加仓库
     * @param depotName             仓库名
     * @param depotType             仓库类型
     * @param depotAddress          仓库地址
     * @param depotDescribe         仓库描述
     * */
    @RequestMapping(value = "/add.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.ADD,memo = "添加仓库")
    public IResult addDepot(String depotNumber,String depotName,String depotType,String depotAddress,String depotDescribe){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<String>(sysDepotServiceImpl.addDepot(depotNumber,depotName,depotType,depotAddress,depotDescribe));
    }

    /**
     * 获取仓库信息
     * @param id 仓库主键
     * */
    @RequestMapping(value = "/get.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getDepot(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<SysDepot>(sysDepotServiceImpl.getById(id));
    }

    /**
     * 更新仓库
     * @param depot
     * @return
     */
    @RequestMapping(value = "/update.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.UPDATE,memo = "更新仓库")
    public IResult updatDepot(@RequestBody SysDepot depot){//传递了数组，前台放在payload里面了，后台通过@RequestBody获取
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(sysDepotServiceImpl.updateDepot(depot.getId(),depot.getDepotNumber(),depot.getDepotName(),depot.getDepotType(),depot.getDepotAddress(),depot.getDepotDescribe()));
    }

    /**
     * 删除原料
     * */
    @RequestMapping(value = "/delete.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.DELETE,memo = "删除仓库")
    public IResult deleteDepot(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(sysDepotServiceImpl.deleteDepot(id));
    }
}
