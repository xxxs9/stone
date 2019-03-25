package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.PurchaseOrder;
import com.gameloft9.demo.dataaccess.model.system.SysFinancePurchaseBillsPayable;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.FinancePurchaseBillPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.Request;

import java.util.Collection;

/**
 * @author: 啊发包
 * @Date: 2019/03/19 2019-03-19
 */


@Controller
@RequestMapping(value = "/finance")
public class FinancePurchaseBillPayController {

    @Autowired
    FinancePurchaseBillPayService purchaseBillPayService;

    /**
     *  分页查询销售
     *
     * @param page 当前页
     * @param limit 每页条数
     * @param auditType 订单类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return jason
     */
    @RequestMapping(value = "/purchasePayList" ,method = RequestMethod.POST)
    @ResponseBody
    public IResult billPayList(String page, String limit, String auditType, String startTime, String endTime){

        return new PageResultBean<Collection<SysFinancePurchaseBillsPayable>>(purchaseBillPayService.getAll(page,limit,auditType,startTime,endTime), purchaseBillPayService.getCount(auditType,startTime,endTime));
    }

    /**
     * 添加采购应付单
     *
     * @param purchaseBillsPayable
     * @return
     */
    @RequestMapping(value = "/addPurchasePay", method = RequestMethod.POST)
    @ResponseBody
    public IResult addPurchasePay(SysFinancePurchaseBillsPayable purchaseBillsPayable){
        return new ResultBean<String>(purchaseBillPayService.addPurchasePay(purchaseBillsPayable));
    }

    /**
     * 根据id获取应付单
     *
     * @param purchaseOrderId id
     * @return 应付单信息
     */
    @RequestMapping(value = "/getPurchasePay", method = RequestMethod.POST)
    @ResponseBody
    public IResult getPurchasePay(String purchaseOrderId){
        return new ResultBean<SysFinancePurchaseBillsPayable>(purchaseBillPayService.getPurchasePay(purchaseOrderId));
    }

    @RequestMapping(value = "/generatePurchasePay", method = RequestMethod.POST)
    @ResponseBody
    public IResult generatePurchasePay(PurchaseOrder purchaseOrder,String id1){

        return new ResultBean<String>(purchaseBillPayService.generatePurchasePay(purchaseOrder,id1));
    }
}
