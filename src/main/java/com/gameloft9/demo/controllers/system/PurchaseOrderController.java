package com.gameloft9.demo.controllers.system;


import com.gameloft9.demo.dataaccess.model.system.PurchaseOrder;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.PurchaseOrderService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;


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
    @RequiresPermissions("purOrder:add")
    public IResult insert(PurchaseOrder purchaseOrder){
        return new ResultBean<String>(service.insert(purchaseOrder));
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete.do",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("purOrder:add")
    public IResult deleteByPrimaryKey(String id){
        return new ResultBean<Boolean>(service.deleteByPrimaryKey(id));
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update.do",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("purOrder:add")
    public IResult updateByPrimaryKey(PurchaseOrder purchaseOrder){
        return new ResultBean<Boolean>(service.updateByPrimaryKey(purchaseOrder));
    }

    /**
     * 审核 inspet
     */
    @RequestMapping(value ="/inspect.do",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("manager:add")
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
     * 查看审核失败原因 look
     */
    @RequestMapping(value = "/look.do",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("purOrder:add")
    public IResult lookPurOrder(PurchaseOrder purchaseOrder){
        return new ResultBean<Boolean>(service.lookUpdate(purchaseOrder));
    }

    /**
     * 获取所有
     */
    @RequestMapping(value = "/list.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectAll(String page,String limit,String goodsName,String state,String financeState){
        return new PageResultBean<Collection<PurchaseOrder>>(service.selectAll(page,limit,goodsName,
                state),service.countGetAll(goodsName,state,financeState));
    }

    /**
     * 根据id获取审核所需的状态列表
     */
    @RequestMapping(value = "/listInspect.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectAllByInspect(String page,String limit,String goodsName,String state,String financeState){
        return new PageResultBean<Collection<PurchaseOrder>>(service.selectAllByInspect(page,
                limit,goodsName,state,financeState),service.countGetAll(goodsName,state,financeState));
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
    @RequiresPermissions("purOrder:add")
    public IResult commitPurOrder(PurchaseOrder purchaseOrder){
        return new ResultBean<Boolean>(service.commitUpdate(purchaseOrder));
    }

    /**
     * 撤回recall
     */
    @RequestMapping(value = "/recall.do",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("purOrder:add")
    public IResult recallPurOrder(String id){
        return new ResultBean<Boolean>(service.recallUpdate(id));
    }

    /**
     * 采购入库之收货 获取所有信息
     */
    @RequestMapping(value = "/listIn.do",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("purOrder:add")
    public IResult selectAllByInOrder(String page,String limit,String goodsName,String depotState){
        return new PageResultBean<Collection<PurchaseOrder>>(service.selectAllByInOrder(page,limit,
                goodsName,depotState),service.countGetAllByInOrder(goodsName,depotState));
    }

    /**
     * 采购入库之收货bring
     */
    @RequestMapping(value = "/bringIn.do",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("purOrder:add")
    public IResult bringInUpdate(PurchaseOrder purchaseOrder){
        return new ResultBean<Boolean>(service.bringInUpdate(purchaseOrder));
    }

    /**
     * 采购入库之确认sure
     */
    @RequestMapping(value = "/sureIn.do",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("purOrder:add")
    public IResult sureInUpdate(PurchaseOrder purchaseOrder){
        return new ResultBean<Boolean>(service.sureInUpdate(purchaseOrder));
    }

    /**
     * 采购入库之提交commit
     */
    @RequestMapping(value = "/commitIn.do",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("purOrder:add")
    public IResult commitInUpdate(PurchaseOrder purchaseOrder){
        return new ResultBean<Boolean>(service.commitInUpdate(purchaseOrder));
    }

    /**
     * 采购入库之撤回back
     */
    @RequestMapping(value = "/backIn.do",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("purOrder:add")
    public IResult backInUpdate(PurchaseOrder purchaseOrder){
        return new ResultBean<Boolean>(service.backInUpdate(purchaseOrder));
    }

    /**
     * 采购入库之编辑edit
     */
    @RequestMapping(value = "/editIn.do",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("purOrder:add")
    public IResult editInUpdate(PurchaseOrder purchaseOrder){
        return new ResultBean<Boolean>(service.InUpdate(purchaseOrder));
    }

    /**
     * 采购入库之查看look
     */
    @RequestMapping(value = "/lookIn.do")
    @ResponseBody
    @RequiresPermissions("purOrder:add")
    public IResult lookInUpdate(PurchaseOrder purchaseOrder){
        return new ResultBean<Boolean>(service.lookUpdate(purchaseOrder));
    }

    /**
     * 根据goodsId获取price
     * */
    @RequestMapping(value = "/price.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectPriceByGoodsId(String materialId){
        return new ResultBean<List<String>>(service.selectPriceByGoodsId(materialId));
    }

    /**
     * 根据state状态为审核通过
     * 获取orderNumber下拉框
     * */
    @RequestMapping(value = "/orderNumber.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectAllByOrderNumber(){
        return new ResultBean<Collection<PurchaseOrder>>(service.selectAllByOrderNumber());
    }

    /**
     * 查看审核通过的订单详情
     */
    @RequestMapping(value = "/search.do",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("purOrder:all")
    public IResult selectAllBySearch(PurchaseOrder purchaseOrder){
        return new ResultBean<Boolean>(service.selectAllBySearch(purchaseOrder));
    }

    /**
     * 根据orderNumber获取内容
     */
    @RequestMapping(value = "/getOrderNumber.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectByOrderNumber(String orderNumber){
        return new ResultBean<PurchaseOrder>(service.selectByOrderNumber(orderNumber));
    }

    /**
     * 四月份采购报表 柱状图
     * */
    @RequestMapping(value = "/selectChartByApril.do",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("purOrder:all")
    public IResult selectChartByApril(String goodsName){
        return new ResultBean<Collection<String>>(service.selectChartByApril(goodsName));
    }

    /**
     * 报表 获取所有goodsName
     * */
    @RequestMapping(value = "/selectGoodsNameAll",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectGoodsNameAll(){
        return new ResultBean<Collection<String>>(service.selectGoodsNameAll());
    }

    /**
     * 啊发包
     */
    @RequestMapping(value = "/getByOrderNumber",method = RequestMethod.POST)
    @ResponseBody
    public IResult getByOrderNumber(String id){
        return new ResultBean<PurchaseOrder>(service.findByOrderNumber(id));
    }

}
