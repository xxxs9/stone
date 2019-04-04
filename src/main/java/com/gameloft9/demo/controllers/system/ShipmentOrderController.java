package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.ShipmentOrder;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.ShipmentOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
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
import java.util.List;

@Controller
@RequestMapping("/shipment")
public class ShipmentOrderController {

    @Autowired
    ShipmentOrderService shipmentOrderService;


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
        List<ShipmentOrder> list = shipmentOrderService.findAll(page, limit, goodsName);
        return new PageResultBean<Collection<ShipmentOrder>>(list,shipmentOrderService.countGetAll(goodsName));
    }
    /**
     * 删除
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public IResult deleteById(String id){
        return new ResultBean(shipmentOrderService.deleteById(id));
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public IResult update(ShipmentOrder shipmentOrder){
        return new ResultBean<Boolean>(shipmentOrderService.update(shipmentOrder));
    }

    /**
     * 获取发货单ID
     */
    @RequestMapping(value = "/get",method = RequestMethod.POST)
    @ResponseBody
    public IResult getById(String id){
        return new ResultBean<ShipmentOrder>(shipmentOrderService.getById(id));
    }

    /**
     * 增加
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public IResult add(ShipmentOrder shipmentOrder, HttpServletRequest request){
        request.getSession().getAttribute("sysUser");
        System.out.println(shipmentOrder);
        return new ResultBean<String>(shipmentOrderService.add(shipmentOrder));
    }

    /**
     * 确认收货
     * @param shipmentOrder
     * @return
     */
    @RequestMapping(value = "/confirm",method = RequestMethod.POST)
    @ResponseBody
    public IResult confirm(ShipmentOrder shipmentOrder){
        return new ResultBean<Boolean>(shipmentOrderService.confirm(shipmentOrder));
    }

    /**
     * 退货
     * @param shipmentOrder
     * @return
     */
    @RequestMapping(value = "/back",method = RequestMethod.POST)
    @ResponseBody
    public IResult back(ShipmentOrder shipmentOrder){
        return new ResultBean<Boolean>(shipmentOrderService.back(shipmentOrder));
    }

    /**
     * 提交仓库
     * @param shipmentOrder
     * @return
     */
    @RequestMapping(value = "/goods",method = RequestMethod.POST)
    @ResponseBody
    public IResult goods(ShipmentOrder shipmentOrder){
        return new ResultBean<Boolean>(shipmentOrderService.goods(shipmentOrder));
    }

    /**
     * 提交财务
     * @param shipmentOrder
     * @return
     */
    @RequestMapping(value = "/sub",method = RequestMethod.POST)
    @ResponseBody
    public IResult sub(ShipmentOrder shipmentOrder){
        return new ResultBean<Boolean>(shipmentOrderService.sub(shipmentOrder));
    }

}
