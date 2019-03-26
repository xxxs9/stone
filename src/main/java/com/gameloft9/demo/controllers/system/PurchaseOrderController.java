package com.gameloft9.demo.controllers.system;


import com.gameloft9.demo.dataaccess.model.system.PurchaseOrder;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
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

    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

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
     * 审核 inspet
     */
    @RequestMapping(value ="/inspect.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult inspectPurOrder(PurchaseOrder purchaseOrder){
        return new ResultBean<Boolean>(service.inspectUpdate(purchaseOrder));
    }

    /**
     * 根据id来查看
     */
    @RequestMapping(value = "/lookGet.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult lookSelect(String id){
        return new ResultBean<PurchaseOrder>(service.lookSelect(id));
    }

    /**
     * 查看 look
     */
    @RequestMapping(value = "/look.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult lookPurOrder(PurchaseOrder purchaseOrder){
        return new ResultBean<Boolean>(service.lookUpdate(purchaseOrder));
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
     * 根据id获取审核所需的状态列表
     */
    @RequestMapping(value = "/listInspect.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectAllByInspect(String page,String limit,String goodsId,String state){
        return new PageResultBean<Collection<PurchaseOrder>>(service.selectAllByInspect(page,
                limit,goodsId,state),service.countGetAll(goodsId,state));
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
     * 采购入库之收货 获取所有信息
     */
    @RequestMapping(value = "/listIn.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectAllByInOrder(String page,String limit,String goodsId,String depotState){
        return new PageResultBean<Collection<PurchaseOrder>>(service.selectAllByInOrder(page,limit,
                goodsId,depotState),service.countGetAllByInOrder(goodsId,depotState));
    }

    /**
     * 采购入库之收货bring
     */
    @RequestMapping(value = "/bringIn.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult bringInUpdate(PurchaseOrder purchaseOrder){
        return new ResultBean<Boolean>(service.bringInUpdate(purchaseOrder));
    }

    /**
     * 采购入库之确认sure
     */
    @RequestMapping(value = "/sureIn.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult sureInUpdate(PurchaseOrder purchaseOrder){
        return new ResultBean<Boolean>(service.sureInUpdate(purchaseOrder));
    }

    /**
     * 采购入库之提交commit
     */
    @RequestMapping(value = "/commitIn.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult commitInUpdate(PurchaseOrder purchaseOrder){
        return new ResultBean<Boolean>(service.commitInUpdate(purchaseOrder));
    }

    /**
     * 采购入库之撤回back
     */
    @RequestMapping(value = "/backIn.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult backInUpdate(PurchaseOrder purchaseOrder){
        return new ResultBean<Boolean>(service.backInUpdate(purchaseOrder));
    }

    /**
     * 采购入库之编辑edit
     */
    @RequestMapping(value = "/editIn.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult editInUpdate(PurchaseOrder purchaseOrder){
        return new ResultBean<Boolean>(service.InUpdate(purchaseOrder));
    }

    /**
     * 采购入库之查看look
     */
    @RequestMapping(value = "/lookIn.do")
    @ResponseBody
    public IResult lookInUpdate(PurchaseOrder purchaseOrder){
        return new ResultBean<Boolean>(service.lookUpdate(purchaseOrder));
    }

}
