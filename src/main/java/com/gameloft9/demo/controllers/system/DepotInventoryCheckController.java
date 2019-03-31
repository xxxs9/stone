package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.DepotInventoryCheck;
import com.gameloft9.demo.dataaccess.model.system.SysDepot;
import com.gameloft9.demo.mgrframework.annotation.BizOperLog;
import com.gameloft9.demo.mgrframework.beans.constant.OperType;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.DepotInventoryCheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * @author: Sxiu
 * @create: 2019/3/29 16:12
 * @description:
 */

@Slf4j
@Service
@RequestMapping("/depotInventoryCheck")
public class DepotInventoryCheckController {

    @Autowired
    private DepotInventoryCheckService depotInventoryCheckServiceImlp;

    /**
     * 获取盘点单列表
     * @param page                  页序
     * @param limit                 分页大小
     * @param sourceUser            发起人
     * @param checkType             盘点类型
     * @param checkState            状态盘点中/结束
     */
    @RequestMapping(value = "/depotInventoryCheckList.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getDepotInventoryCheckList(String page, String limit,String sourceUser,String checkType,String checkState){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new PageResultBean<Collection<DepotInventoryCheck>>(depotInventoryCheckServiceImlp.getAll(page,limit,sourceUser,checkType,checkState),depotInventoryCheckServiceImlp.countGetAll(sourceUser,checkType,checkState));
    }

    /**
     * 添加盘点单
     * @param checkType           盘点类型
     * @param recordNumber        记录数量
     * @param request             请求
     * */
    @RequestMapping(value = "/add.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.ADD,memo = "新增盘点单")
    public IResult addDepotInventoryCheck(String checkType, String recordNumber, HttpServletRequest request){
        //返回json至前端的均返回ResultBean或者PageResultBean
        String sourceUser = (String) request.getSession().getAttribute("sysUser");
        return new ResultBean<String>(depotInventoryCheckServiceImlp.addDepotInventoryCheck(checkType,sourceUser,recordNumber));
    }

    /**
     * 根据主键获取盘点单信息
     * @param id 盘点单主键
     * */
    @RequestMapping(value = "/get.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getDepotInventoryCheck(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<DepotInventoryCheck>(depotInventoryCheckServiceImlp.getById(id));
    }


    /**
     * 删除盘点单
     * */
    @RequestMapping(value = "/delete.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.DELETE,memo = "删除盘点单")
    public IResult deleteDepotInventoryCheck(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(depotInventoryCheckServiceImlp.deleteDepotInventoryCheck(id));
    }


    /**
     * 批量删除盘点单
     * */
    @RequestMapping(value = "/dels.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.DELETE,memo = "批量删除盘点单")
    public IResult delsDepotInventoryCheck(String ids){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(depotInventoryCheckServiceImlp.delsDepotInventoryCheck(ids));
    }

}
