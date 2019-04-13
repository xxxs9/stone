
package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.PurchaseOrder;
import com.gameloft9.demo.dataaccess.model.system.SysFinancePurchaseBillsPayable;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.FinancePurchaseBillPayService;
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
@RequestMapping(value = "/finance")
public class FinancePurchaseBillPayController {

    @Autowired
    FinancePurchaseBillPayService purchaseBillPayService;


    /**
     *  分页查询销售
     *
     * @param page 当前页
     * @param limit 每页条数
     * @param auditState 申请状态
     * @return jason
     */

    @RequestMapping(value = "/purchasePayList" ,method = RequestMethod.POST)
    @ResponseBody
    public IResult billPayList(String page, String limit, String auditState){

        return new PageResultBean<Collection<SysFinancePurchaseBillsPayable>>(purchaseBillPayService.getAll(page,limit,auditState), purchaseBillPayService.getCount(auditState));
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
    @RequiresPermissions("finance:generate")
    public IResult generatePurchasePay(PurchaseOrder purchaseOrder,String id1){

        return new ResultBean<String>(purchaseBillPayService.generatePurchasePay(purchaseOrder,id1));
    }


    /**
     * 根据ID获取
     * @param id id
     * @return
     *  json
     */

    @RequestMapping(value = "/getPurchasePayById",method = RequestMethod.POST)
    @ResponseBody
    public IResult getPurchasePayById(String id){
        return new ResultBean<SysFinancePurchaseBillsPayable>(purchaseBillPayService.getPurchasePayById(id));
    }


    /**
     * 审核
     * @param attitude q
     * @param purchaseOrderId q
     * @param auditType q
     * @param actualPrice q
     * @param auditDescribe q
     * @return q
     */

    @RequestMapping(value = "/purchaseOrderPayPass", method = RequestMethod.POST)
    @ResponseBody
    public IResult purchaseOrderPayPass(String attitude,String purchaseOrderId,String auditType,String actualPrice,String auditDescribe){
        return new ResultBean<Boolean>(purchaseBillPayService.purchaseOrderPayPass(attitude,purchaseOrderId,auditType,actualPrice,auditDescribe));
    }

    @RequestMapping(value = "/getPurchasePayChart",method = RequestMethod.POST)
    @ResponseBody
    public IResult getPurchasePayChart(){
        return new ResultBean<Collection<String>>(purchaseBillPayService.getPurchasePayChart());
    }

}
