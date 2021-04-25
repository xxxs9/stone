package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.PurchaseOrder;
import com.gameloft9.demo.dataaccess.model.system.SysFinancePurchaseReceivable;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.FinancePurchaseReceivableService;
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
public class FinancePurchaseReceivableController {

    @Autowired
    FinancePurchaseReceivableService purchaseReceivableService;

    /**
     *  分页查询采购营收单
     *
     * @param page 当前页
     * @param limit 每页条数
     * @param auditState 申请类型
     * @return jason
     */
    @RequestMapping(value = "/purchaseReceiveList" ,method = RequestMethod.POST)
    @ResponseBody
    public IResult billPayList(String page, String limit, String auditState){

        return new PageResultBean<Collection<SysFinancePurchaseReceivable>>(purchaseReceivableService.getAll(page,limit,auditState), purchaseReceivableService.getCount(auditState));
    }

    /**
     * s生成采购应收单
     * @param purchaseOrder 订单
     * @param id1 ID
     *
     * @return
     *      json
     */
    @RequestMapping(value = "/generatePurchaseReceive",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("finance:generate")
    public IResult generatePurchaseReceive(PurchaseOrder purchaseOrder,String id1){

        return new ResultBean<String>(purchaseReceivableService.generatePurchaseReceive(purchaseOrder,id1));
    }

    /**
     * 根据id获取
     * @param id id
     * @return
     *      json
     */
    @RequestMapping(value = "/getPurchaseReceiveById",method = RequestMethod.POST)
    @ResponseBody
    public IResult getPurchaseReceiveById(String id){
        return new ResultBean<SysFinancePurchaseReceivable>(purchaseReceivableService.getPurchaseReceiveById(id));
    }

    /**
     * 审核采购应收单
     * @param attitude q
     * @param purchaseOrderRejectedId q
     * @param auditType q
     * @param actualPrice q
     * @param auditDescribe q
     * @return q
     */
    @RequestMapping(value = "/purchaseOrderReceivePass",method = RequestMethod.POST)
    @ResponseBody
    public IResult purchaseOrderReceivePass(String attitude,String purchaseOrderRejectedId,String auditType,String actualPrice,String auditDescribe,String totalPrice){
        return new ResultBean<Boolean>(purchaseReceivableService.purchaseOrderReceivePass(attitude,purchaseOrderRejectedId,auditType,actualPrice,auditDescribe,totalPrice));
    }

    @RequestMapping(value = "/getPurchaseReceiveChart",method = RequestMethod.POST)
    @ResponseBody
    public IResult getPurchaseReceiveChart(){
        return new ResultBean<Collection<String>>(purchaseReceivableService.getPurchasePayChart());
    }


}
