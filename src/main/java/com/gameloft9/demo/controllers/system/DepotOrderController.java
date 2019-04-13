package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.DepotOrder;
import com.gameloft9.demo.mgrframework.annotation.BizOperLog;
import com.gameloft9.demo.mgrframework.beans.constant.OperType;
import com.gameloft9.demo.mgrframework.beans.response.AbstractResult;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.mgrframework.exceptions.BizException;
import com.gameloft9.demo.service.api.system.DepotOrderService;
import com.gameloft9.demo.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * @author: Sxiu
 * @create: 2019/3/28 12:20
 * @description:
 */


@Slf4j
@Controller
@RequestMapping("/depotOrder")
public class DepotOrderController {
    @Autowired
    DepotOrderService depotOrderServiceImpl;
    /**
     * 获取仓库单列表
     * @param page                  页序
     * @param limit                 分页大小
     * @param goodsId               原料/成品ID
     * @param applyUser             申请人
     */
    @RequestMapping(value = "/depotOrderInList.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getDepotOrderInList(String page, String limit,String type, String goodsId,String applyUser){
        //返回json至前端的均返回ResultBean或者PageResultBean
        String orderType = Constants.Depot.ORDER_IN;
        return new PageResultBean<Collection<DepotOrder>>(depotOrderServiceImpl.getAll(page,limit,orderType, type,goodsId,applyUser),depotOrderServiceImpl.countGetAll("入库单",type,goodsId,applyUser));
    }
    /**
     * 获取库存列表
     * @param page                  页序
     * @param limit                 分页大小
     * @param goodsId               原料/成品ID
     * @param applyUser             申请人
     */
    @RequestMapping(value = "/depotOrderOutList.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getDepotOrderOutList(String page, String limit,String type, String goodsId,String applyUser){
        //返回json至前端的均返回ResultBean或者PageResultBean
        String orderType = Constants.Depot.ORDER_OUT;
        return new PageResultBean<Collection<DepotOrder>>(depotOrderServiceImpl.getAll(page,limit,orderType,type,goodsId,applyUser),depotOrderServiceImpl.countGetAll("出库单",type,goodsId,applyUser));
    }

    /**
     * 添加入库单
     * @param type                  入库类型
     * @param goodsId               原料/成品ID
     * @param goodsNumber           货品数量
     * @param request               请求
     * */
    @RequestMapping(value = "/addIn.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.ADD,memo = "新增入库单")
    @RequiresPermissions("depotOrder:add")
    public IResult addDepotOrderIn(String type, String goodsId, String goodsNumber, HttpServletRequest request){
        //返回json至前端的均返回ResultBean或者PageResultBean
        if(type.equals(Constants.Depot.PURCHASE_IN)||type.equals(Constants.Depot.MARKET_IN)||type.equals(Constants.Depot.PRODUCE_IN)){
            String msg = type+"单只能由"+type.substring(0,2)+"部生成";
            throw new BizException(AbstractResult.CHECK_FAIL,msg);
        }
        String applyUser = (String) request.getSession().getAttribute("sysUser");
        String orderType = Constants.Depot.ORDER_IN;
        return new ResultBean<String>(depotOrderServiceImpl.addDepotOrder(orderType,type,goodsId,goodsNumber,applyUser));
    }

    /**
     * 添加采购入库单
     * @param orderNumber           编号
     * @param goodsId               原料/成品ID
     * @param goodsNumber           货品数量
     * @param applyUser             申请入
     * */
    @RequestMapping(value = "/addPurorder.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.ADD,memo = "新增采购入库单")
    @RequiresPermissions("depotOrder:add")
    public IResult addPurorderDepotOrderIn(String orderNumber,String goodsId, String goodsNumber,String applyUser){
        //返回json至前端的均返回ResultBean或者PageResultBean
        String type = Constants.Depot.PURCHASE_IN;
        String orderType = Constants.Depot.ORDER_IN;
        return new ResultBean<String>(depotOrderServiceImpl.addSysDepotOrder(orderNumber,orderType,type,goodsId,goodsNumber,applyUser));
    }

    /**
     * 添加销售出库单
     * @param orderNumber           编号
     * @param goodsId               原料/成品ID
     * @param goodsNumber           货品数量
     * @param applyUser             申请入
     * */
    @RequestMapping(value = "/addMarket.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.ADD,memo = "新增销售出库单")
    @RequiresPermissions("depotOrder:add")
    public IResult addMarketDepotOrderOut(String orderNumber,String goodsId, String goodsNumber,String applyUser){
        //返回json至前端的均返回ResultBean或者PageResultBean
        String type = Constants.Depot.MARKET_OUT;
        String orderType = Constants.Depot.ORDER_OUT;
        return new ResultBean<String>(depotOrderServiceImpl.addSysDepotOrder(orderNumber,orderType,type,goodsId,goodsNumber,applyUser));
    }


    /**
     * 添加出库单
     * @param type                  出库类型
     * @param goodsId               原料/成品ID
     * @param goodsNumber           货品数量
     * @param request               请求
     * */
    @RequestMapping(value = "/addOut.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.ADD,memo = "新增出库单")
    @RequiresPermissions("depotOrder:add")
    public IResult addDepotOrderOut(String type, String goodsId, String goodsNumber, HttpServletRequest request){
        //返回json至前端的均返回ResultBean或者PageResultBean
        if(type.equals(Constants.Depot.PURCHASE_OUT)||type.equals(Constants.Depot.MARKET_OUT)||type.equals(Constants.Depot.PRODUCE_OUT)){
            String msg = type+"单只能由"+type.substring(0,2)+"部生成";
            throw new BizException(AbstractResult.CHECK_FAIL,msg);
        }
        String applyUser = (String) request.getSession().getAttribute("sysUser");
        String orderType = Constants.Depot.ORDER_OUT;
        return new ResultBean<String>(depotOrderServiceImpl.addDepotOrder(orderType,type,goodsId,goodsNumber,applyUser));
    }

    /**
     * 获取仓库单信息
     * @param id 仓库单主键
     * */
    @RequestMapping(value = "/get.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getDepotOrder(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<DepotOrder>(depotOrderServiceImpl.getById(id));
    }

    /**
     * 获取仓库单信息,判断是否入库成功
     * @param id 仓库单主键
     * */
    @RequestMapping(value = "/isStorageIn.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult isStorageIn(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(depotOrderServiceImpl.isStorageIn(id));
    }

    /**
     * 获取仓库单信息,判断是否出库成功
     * @param id 仓库单主键
     * */
    @RequestMapping(value = "/isStorageOut.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult isStorageOut(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(depotOrderServiceImpl.isStorageOut(id));
    }

    /**
     * 获取入库单信息,判断是否成功
     * @param id 仓库单主键
     * */
    @RequestMapping(value = "/isAuditPassIn.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult isAuditPassIn(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(depotOrderServiceImpl.isAuditPassIn(id));
    }

    /**
     * 获取入库单信息,判断是否成功
     * @param id 仓库单主键
     * */
    @RequestMapping(value = "/isAuditPassOut.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult isAuditPassOut(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(depotOrderServiceImpl.isAuditPassOut(id));
    }

    /**
     * 审核通过,更新入库单
     * @param id                    仓库单id
     * @param state                 仓库单状态
     * @param auditDescribe         审核描述
     * */
    @RequestMapping(value = "/auditPassIn.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.UPDATE,memo = "入库单审核通过")
    @RequiresPermissions("depotOrder:audit")
    public IResult auditPassDepotOrderIn(String id,String state,String auditDescribe,HttpServletRequest request){//传递了数组，前台放在payload里面了，后台通过@RequestBody获取
        //返回json至前端的均返回ResultBean或者PageResultBean
        String orderAuditUser = (String) request.getSession().getAttribute("sysUser");
        return new ResultBean<Boolean>(depotOrderServiceImpl.auditPassDepotOrderIn(id,state,orderAuditUser,auditDescribe));
    }

    /**
     * 审核通过,更新出库单
     * @param id                    仓库单id
     * @param state                 仓库单状态
     * @param auditDescribe         审核描述
     * */
    @RequestMapping(value = "/auditPassOut.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.UPDATE,memo = "出库单审核通过")
    @RequiresPermissions("depotOrder:audit")
    public IResult auditPassDepotOrderOut(String id,String state,String auditDescribe,HttpServletRequest request){//传递了数组，前台放在payload里面了，后台通过@RequestBody获取
        //返回json至前端的均返回ResultBean或者PageResultBean
        String orderAuditUser = (String) request.getSession().getAttribute("sysUser");
        return new ResultBean<Boolean>(depotOrderServiceImpl.auditPassDepotOrderOut(id,state,orderAuditUser,auditDescribe));
    }

    /**
     * 审核驳回,更新入库单
     * @param id                    仓库单id
     * @param state                 仓库单状态
     * @param auditDescribe         审核描述
     * */
    @RequestMapping(value = "/auditRejectIn.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.UPDATE,memo = "驳回入库单")
    @RequiresPermissions("depotOrder:audit")
    public IResult auditRejectDepotOrderIn(String id,String state,String auditDescribe,HttpServletRequest request){//传递了数组，前台放在payload里面了，后台通过@RequestBody获取
        //返回json至前端的均返回ResultBean或者PageResultBean
        String orderAuditUser = (String) request.getSession().getAttribute("sysUser");
        return new ResultBean<Boolean>(depotOrderServiceImpl.auditRejectDepotOrderIn(id,state,orderAuditUser,auditDescribe));
    }

    /**
     * 审核驳回,更新出库单
     * @param id                    仓库单id
     * @param state                 仓库单状态
     * @param auditDescribe         审核描述
     * */
    @RequestMapping(value = "/auditRejectOut.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.UPDATE,memo = "驳回出库单")
    @RequiresPermissions("depotOrder:audit")
    public IResult auditRejectDepotOrderOut(String id,String state,String auditDescribe,HttpServletRequest request){//传递了数组，前台放在payload里面了，后台通过@RequestBody获取
        //返回json至前端的均返回ResultBean或者PageResultBean
        String orderAuditUser = (String) request.getSession().getAttribute("sysUser");
        return new ResultBean<Boolean>(depotOrderServiceImpl.auditRejectDepotOrderOut(id,state,orderAuditUser,auditDescribe));
    }

    /**
     * 入库,更新入库单
     * @param id                    仓库单id
     * @param state                 仓库单状态
     * */
    @RequestMapping(value = "/storageIn.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.UPDATE,memo = "货物入库")
    @RequiresPermissions("depotOrder:storageInOut")
    public IResult storageDepotOrderIn(String id,String state){//传递了数组，前台放在payload里面了，后台通过@RequestBody获取
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(depotOrderServiceImpl.storageInDepotOrderIn(id,state));
    }

    /**
     * 出库,更新出库单
     * @param id                    仓库单id
     * @param state                 仓库单状态
     * */
    @RequestMapping(value = "/storageOut.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.UPDATE,memo = "货物出库")
    @RequiresPermissions("depotOrder:storageInOut")
    public IResult storageDepotOrderOut(String id,String state){//传递了数组，前台放在payload里面了，后台通过@RequestBody获取
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(depotOrderServiceImpl.storageInDepotOrderOut(id,state));
    }

    /**
     * 删除仓库单
     * */
    @RequestMapping(value = "/delete.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.DELETE,memo = "删除仓库单")
    @RequiresPermissions("depotOrder:delete")
    public IResult deleteDepotOrder(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(depotOrderServiceImpl.deleteDepotOrder(id));
    }

    /**
     * 批量删除仓库单
     * */

    @RequestMapping(value = "/dels.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.DELETE,memo = "批量删除仓库单")
    @RequiresPermissions("depotOrder:delete")
    public IResult delsDepotOrder(String ids){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(depotOrderServiceImpl.delsDepotOrder(ids));
    }

    /**
     * 获取入库单类型
     * */
    @RequestMapping(value = "/getDepotOrderInType.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getDepotOrderInType(){
        //返回json至前端的均返回ResultBean或者PageResultBean
        String orderType = Constants.Depot.ORDER_IN;
        return new ResultBean<List<String>>(depotOrderServiceImpl.getDepotOrderInType(orderType));
    }

    /**
     * 获取出库单类型
     * */
    @RequestMapping(value = "/getDepotOrderOutType.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getDepotOrderOutType(){
        //返回json至前端的均返回ResultBean或者PageResultBean
        String orderType = Constants.Depot.ORDER_OUT;
        return new ResultBean<List<String>>(depotOrderServiceImpl.getDepotOrderInType(orderType));
    }

}
