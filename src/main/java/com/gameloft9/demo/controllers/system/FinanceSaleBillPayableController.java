package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.ShipmentOrder;
import com.gameloft9.demo.dataaccess.model.system.SysFinanceSaleBillsPayable;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.FinanceSaleBillPayService;
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
@RequestMapping("finance")
public class FinanceSaleBillPayableController {

    @Autowired
    FinanceSaleBillPayService saleBillPayService;

    /**
     *  分页查询销售
     *
     * @param page 当前页
     * @param limit 每页条数
     * @param auditState 订单类型
     * @return jason
     */
    @RequestMapping(value = "/salePayList", method = RequestMethod.POST)
    @ResponseBody
    public IResult saleBillPayList(String page, String limit, String auditState){
        return new PageResultBean<Collection<SysFinanceSaleBillsPayable>>(saleBillPayService.getAll(page,limit,auditState),
                saleBillPayService.getCount(auditState));
    }

    /**
     *
     * @param shipmentOrder
     * @param id1
     * @return
     */
    @RequestMapping(value = "/generateSalePay",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("finance:generate")
    public IResult generateSalePay(ShipmentOrder shipmentOrder, String id1){
        return new ResultBean<String>(saleBillPayService.generateSalePay(shipmentOrder, id1));
    }

    /**
     *审核销售应付单
     *
     * @param attitude 态度
     * @param saleRejectedId ID
     * @param auditType 申请类型
     * @param actualPrice 实际金额
     * @param auditDescribe 审核信息
     * @return json
     *
     *  json
     */
    @RequestMapping(value = "/saleOrderPayPass",method = RequestMethod.POST)
    @ResponseBody
    public IResult saleOrderPayPass(String attitude,String saleRejectedId,String auditType,String actualPrice,String auditDescribe){
        return new ResultBean<Boolean>(saleBillPayService.saleOrderPayPass(attitude,saleRejectedId,auditType,actualPrice,auditDescribe));
    }

    /**
     * 根据ID获取
     * @param id id
     * @return
     *  json
     */
    @RequestMapping(value = "/getSalePayById",method = RequestMethod.POST)
    @ResponseBody
    public IResult getSalePayById(String id){
        return new ResultBean<SysFinanceSaleBillsPayable>(saleBillPayService.getSalePayById(id));
    }

    @RequestMapping(value = "/getSalePayChart",method = RequestMethod.POST)
    @ResponseBody
    public IResult getSalePayChart(){
        return new ResultBean<Collection<String>>(saleBillPayService.getSalePayChart());
    }

}
