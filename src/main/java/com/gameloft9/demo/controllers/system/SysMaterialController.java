package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.SysMaterial;
import com.gameloft9.demo.mgrframework.annotation.BizOperLog;
import com.gameloft9.demo.mgrframework.beans.constant.OperType;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.SysMaterialService;
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
@RequestMapping("/material")
public class SysMaterialController {

    @Autowired
    SysMaterialService sysMaterialServiceImpl;
    /**
     * 获取货品名称列表
     * */
    @RequestMapping(value = "/getGoodsName.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getGoodsNameList(){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Collection<String>>(sysMaterialServiceImpl.getGoodsName());
    }
    /**
     * 获取货品类型列表
     * */
    @RequestMapping(value = "/getGoodsType.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getGoodsTypeList(){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Collection<String>>(sysMaterialServiceImpl.getGoodsType());
    }
    /**
     * 获取货品规格列表
     * */
    @RequestMapping(value = "/getGoodsSpecification.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getGoodsSpecificationList(){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Collection<String>>(sysMaterialServiceImpl.getGoodsSpecification());
    }
    /**
     * 分页获取原料列表
     * @param page                  页序
     * @param limit                 分页大小
     * @param goodsName             货物名称
     * @param goodsType             货物类型
     * @param goodsSpecification    货物规格
     */
    @RequestMapping(value = "/materialList.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getMaterialList(String page, String limit, String goodsName,String goodsType,String goodsSpecification){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new PageResultBean<Collection<SysMaterial>>(sysMaterialServiceImpl.getAll(page,limit,goodsName,goodsType,goodsSpecification),sysMaterialServiceImpl.countGetAll(goodsName,goodsType,goodsSpecification));
    }

    /**
     * 添加原料
     * */
    @RequestMapping(value = "/add.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.ADD,memo = "添加原料种类")
    public IResult addMaterial(String goodsName,String goodsType,String goodsDescribe,String goodsSpecification){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<String>(sysMaterialServiceImpl.addMaterial(goodsName,goodsType,goodsDescribe,goodsSpecification));
    }

    /**
     * 获取原料
     * @param goodsName 货物名称
     * */
    @RequestMapping(value = "/getByGoodsName.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getByGoodsName(String goodsName){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<SysMaterial>(sysMaterialServiceImpl.getByGoodsName(goodsName));
    }

    /**
     * 获取原料
     * */
    @RequestMapping(value = "/get.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getMaterial(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<SysMaterial>(sysMaterialServiceImpl.getById(id));
    }

    /**
     * 更新原料
     * @param material
     * @return
     */
    @RequestMapping(value = "/update.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.UPDATE,memo = "更新原料")
    public IResult updateMaterial(@RequestBody SysMaterial material){//传递了数组，前台放在payload里面了，后台通过@RequestBody获取
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(sysMaterialServiceImpl.updateMaterial(material.getId(),material.getGoodsName(),material.getGoodsType(),material.getGoodsDescribe(),material.getGoodsSpecification()));
    }

    /**
     * 删除原料
     * */
    @RequestMapping(value = "/delete.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.DELETE,memo = "删除原料")
    public IResult deleteMaterial(String materialId){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(sysMaterialServiceImpl.deleteMaterial(materialId));
    }

}
