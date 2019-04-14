package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.ShipmentOrder;
import com.gameloft9.demo.dataaccess.model.system.SysFinanceSaleReceivable;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.FinanceSaleReceivableService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * @author: 啊发包
 * @Date: 2019/03/19 2019-03-19
 */
@Controller
@RequestMapping("/finance")
public class FinanceSaleReceivableController {

    @Autowired
    FinanceSaleReceivableService financeSaleReceivableService;

    /**
     *  分页查询销售应收单
     *
     * @param page 当前页
     * @param limit 每页条数
     * @param auditState 订单类型
     * @return jason
     */
    @RequestMapping(value = "/saleReceiveList", method = RequestMethod.POST)
    @ResponseBody
    public IResult saleBillPayList(String page, String limit, String auditState){
        return new PageResultBean<Collection<SysFinanceSaleReceivable>>(financeSaleReceivableService.getAll(page,limit,auditState),
                financeSaleReceivableService.getCount(auditState));
    }

    @RequestMapping(value = "/generateSaleReceive",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("finance:generate")
    public IResult generateSalePay(ShipmentOrder shipmentOrder, String id1){
        return new ResultBean<String>(financeSaleReceivableService.generateSaleReceive(shipmentOrder,id1));
    }

    /**
     * 审核销售应收单
     *
     * @param attitude 态度
     * @param saleId ID
     * @param auditType 申请类型
     * @param actualPrice 实际金额
     * @param auditDescribe 审核信息
     * @return
     */
    @RequestMapping(value = "/saleOrderReceivePass",method = RequestMethod.POST)
    @ResponseBody
    public IResult saleOrderPayPass(String attitude,String saleId,String auditType,String actualPrice,String auditDescribe,String totalPrice){
        return new ResultBean<Boolean>(financeSaleReceivableService.saleOrderReceivePass(attitude,
                saleId,auditType,actualPrice,auditDescribe,totalPrice));
    }

    /**
     * 根据ID获取
     * @param id id
     * @return
     *  json
     */
    @RequestMapping(value = "/getSaleReceiveById",method = RequestMethod.POST)
    @ResponseBody
    public IResult getSaleReceiveById(String id){
        return new ResultBean<SysFinanceSaleReceivable>(financeSaleReceivableService.getSaleReceiveById(id));
    }

    @RequestMapping(value = "/getSaleReceiveChart",method = RequestMethod.POST)
    @ResponseBody
    public IResult getSaleReceiveChart(){
        return new ResultBean<Collection<String>>(financeSaleReceivableService.getSaleReceiveChart());
    }



}
