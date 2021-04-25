package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.PurchaseOrder;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.FinanceCheckPendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * @author: 啊发包
 * @Date: 2019/03/20 2019-03-20
 */
@Controller
@RequestMapping("/finance")
public class FinanceCheckPendingController {

    @Autowired
    FinanceCheckPendingService checkPendingService;


    @RequestMapping(value = "/purchase_checkList",method = RequestMethod.POST)
    @ResponseBody
    public IResult purchaseList(String page,String limit,String goodsId,String state){
        return new PageResultBean< Collection< PurchaseOrder >>(checkPendingService.getPurchaseList(),10);
    }


    @RequestMapping(value = "/auditingPurchaseOrder.do", method = RequestMethod.POST)
    @ResponseBody
    //TODO...
    public IResult auditingPurchaseOrder(String id ,String financeAuditDescribe, String isAgree){
        return new ResultBean<Boolean>(checkPendingService.auditingPurchaseOrder(id,financeAuditDescribe,isAgree));
    }

}
