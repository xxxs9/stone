package com.gameloft9.demo.controllers.system;


import com.gameloft9.demo.dataaccess.model.system.PurchaseOrder;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.PurchaseOrderService;
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


/**
 * PurchaseOrder controller控制层
 * @author OliverCH
 * @date 2019/03/18
 */
@Controller
@RequestMapping("/purchase_order")
public class PurchaseOrderController {

    @Autowired
    PurchaseOrderService service;

    /**
     * 根据id获取内容
     */
    @RequestMapping(value = "/get.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectByPrimaryKey(String id){
        return new ResultBean<PurchaseOrder>(service.selectByPrimaryKey(id));
    }

    /***
     * 增加
     */
    @RequestMapping(value = "/insert.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult insert(PurchaseOrder purchaseOrder){
        return new ResultBean<String>(service.insert(purchaseOrder));
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult deleteByPrimaryKey(String id){
        return new ResultBean<Boolean>(service.deleteByPrimaryKey(id));
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult updateByPrimaryKey(PurchaseOrder purchaseOrder){
        return new ResultBean<Boolean>(service.updateByPrimaryKey(purchaseOrder));
    }

    /**
     * 获取所有
     */
    @RequestMapping(value = "/list.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectAll(String page,String limit,String goodsId,String state){
        return new PageResultBean<Collection<PurchaseOrder>>(service.selectAll(page,limit,goodsId,
                state),service.countGetAll(goodsId,state));
    }

    /**
     * 获取goodsId下拉框内容
     */
    @RequestMapping("/goods.do")
    @ResponseBody
    public IResult getListGoods(){
        return new ResultBean<Collection<PurchaseOrder>>(service.getSelectListGoods());
    }

    /**
     * 提交commit
     */
    @RequestMapping(value = "/commit.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult commitPurOrder(PurchaseOrder purchaseOrder){
        return new ResultBean<Boolean>(service.commitUpdate(purchaseOrder));
    }

    /**
     * 撤回recall
     */
    @RequestMapping(value = "/recall.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult recallPurOrder(String id){
        return new ResultBean<Boolean>(service.recallUpdate(id));
    }

    /**
     * 审核inspect
     */
    @RequestMapping(value = "/inspect.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult inspectPurOrder(String id,String auditDescribe,String agree){
        return new ResultBean<Boolean>(service.inspectUpdate(id,auditDescribe,agree));
    }
}
