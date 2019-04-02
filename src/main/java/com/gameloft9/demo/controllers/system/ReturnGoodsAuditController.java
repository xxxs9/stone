package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.ReturnGoodsAudit;
import com.gameloft9.demo.dataaccess.model.system.ShipmentOrder;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.ReturnGoodsAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/returnGoods")
public class ReturnGoodsAuditController {

    @Autowired
    ReturnGoodsAuditService returnGoodsAuditService;

    /**
     * 处理时间
     * @param binder
     * @param request
     */
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request){
        //转换日期
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // CustomDateEditor为自定义日期编辑器
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 获取所有发货单信息
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public IResult findAll(String page, String limit, String goodsName){
        List<ReturnGoodsAudit> list = returnGoodsAuditService.findAll(page, limit, goodsName);
        return new PageResultBean<Collection<ReturnGoodsAudit>>(list,returnGoodsAuditService.countGetAll(goodsName));
    }


    /**
     * 修改
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public IResult update(ReturnGoodsAudit returnGoodsAudit){
        return new ResultBean<Boolean>(returnGoodsAuditService.update(returnGoodsAudit));
    }

    /**
     * 获取发货单ID
     * @param id
     * @return
     */
    @RequestMapping(value = "/get",method = RequestMethod.POST)
    @ResponseBody
    public IResult getById(String id){
        return new ResultBean<ReturnGoodsAudit>(returnGoodsAuditService.getById(id));
    }

    /**
     * 财务查收
     * @param shipmentOrder
     * @return
     */
    @RequestMapping(value = "/accept",method = RequestMethod.POST)
    @ResponseBody
    public IResult accept(ShipmentOrder shipmentOrder){
        return new ResultBean<Boolean>(returnGoodsAuditService.accept(shipmentOrder));
    }
}
