package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.dao.system.PurchaseOrderMapper;
import com.gameloft9.demo.dataaccess.model.system.PurchaseOrder;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.service.api.system.FinanceCheckPendingService;
import com.gameloft9.demo.service.api.system.PurchaseOrderService;
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
    FinanceCheckPendingService purchaseOrderService;

    @RequestMapping(value = "/purchase_checkList",method = RequestMethod.POST)
    @ResponseBody
    public IResult purchaseList(String page,String limit,String goodsId,String state){
        return new PageResultBean< Collection< PurchaseOrder >>(purchaseOrderService.getPurchaseList(),10);
    }

}
