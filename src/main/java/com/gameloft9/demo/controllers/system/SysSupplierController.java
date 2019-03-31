package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.SysMaterial;
import com.gameloft9.demo.dataaccess.model.system.SysSupplier;
import com.gameloft9.demo.mgrframework.annotation.BizOperLog;
import com.gameloft9.demo.mgrframework.beans.constant.OperType;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.SysMaterialService;
import com.gameloft9.demo.service.api.system.SysSupplierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

@Controller
@Slf4j
@RequestMapping("/supplier")
public class SysSupplierController {
    @Autowired
    SysSupplierService sysSupplierServiceImpl;
    /**
     * 获取供应商名称列表
     * */
    @RequestMapping(value = "/getSupplierName.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getSupplierNameList(){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Collection<String>>(sysSupplierServiceImpl.getSupplierName());
    }
    /**
     * 分页获取供应商列表
     * @param page                  页序
     * @param limit                 分页大小
     * @param supplierName          供应商名称
     * @param supplierDescribe      供应商描述
     * @param phone                 电话
     */
    @RequestMapping(value = "/supplierList.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getSupplierList(String page, String limit, String supplierName, String supplierDescribe, String phone){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new PageResultBean<Collection<SysSupplier>>(sysSupplierServiceImpl.getAll(page,limit,supplierName,supplierDescribe,phone),sysSupplierServiceImpl.countGetAll(supplierName,supplierDescribe,phone));
    }

    /**
     * 新增供应商
     * @param supplierName          供应商名称
     * @param supplierDescribe      供应商描述
     * @param phone                 电话
     * */
    @RequestMapping(value = "/add.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.ADD,memo = "添加供应商")
    public IResult addSupplier(String supplierName,String supplierDescribe,String phone){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<String>(sysSupplierServiceImpl.addSupplier(supplierName,supplierDescribe,phone));
    }

    /**
     * 根据主键获取供应商信息
     * @param id 供应商主键
     * */
    @RequestMapping(value = "/get.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getSupplier(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<SysSupplier>(sysSupplierServiceImpl.getById(id));
    }

    /**
     * 更新供应商信息
     * */
    @RequestMapping(value = "/update.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.UPDATE,memo = "更新供应商")
    public IResult updateSupplier(@RequestBody SysSupplier supplier){//传递了数组，前台放在payload里面了，后台通过@RequestBody获取
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(sysSupplierServiceImpl.updateSupplier(supplier.getId(),supplier.getSupplierName(),supplier.getSupplierDescribe(),supplier.getPhone()));
    }

    /**
     * 根据主键删除供应商信息
     * @param id 供应商id
     * */
    @RequestMapping(value = "/delete.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.DELETE,memo = "删除供应商")
    public IResult deleteSupplier(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(sysSupplierServiceImpl.deleleSupplier(id));
    }

    /**
     * 根据供应商名称获取供应商信息
     * @param supplierName 供应商名称
     * */
    @RequestMapping(value = "/getBySupplierName.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getBySupplierName(String supplierName){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<SysSupplier>(sysSupplierServiceImpl.getBySupplierName(supplierName));
    }

}
