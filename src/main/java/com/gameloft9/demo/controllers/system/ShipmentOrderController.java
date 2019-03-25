package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.MarkerOrderTest;
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
     * 分页模糊查找
     * @param page
     * @param limit
     * @param shipmentId
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public IResult findAll(String page, String limit, String shipmentId){
        List<ShipmentOrder> list = shipmentOrderService.findAll(page, limit, shipmentId);
        return new PageResultBean<Collection<ShipmentOrder>>(list,shipmentOrderService.countGetAll(shipmentId));
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public IResult deleteById(String id){
        return new ResultBean(shipmentOrderService.deleteById(id));
    }

    /**
     * 获取id
     * @param id
     * @return
     */
    @RequestMapping(value = "/get",method = RequestMethod.POST)
    @ResponseBody
    public IResult getById(String id){
        return new ResultBean<ShipmentOrder>(shipmentOrderService.getById(id));
    }

    /**
     * 修改
     * @param shipmentOrder
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public IResult update(ShipmentOrder shipmentOrder){
        return new ResultBean<Boolean>(shipmentOrderService.update(shipmentOrder));
    }

    /**
     * 增加
     * @param shipmentOrder
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public IResult add(ShipmentOrder shipmentOrder){
        return new ResultBean<Integer>(shipmentOrderService.add(shipmentOrder));
    }

    /**
     * 确认收货
     */
    @RequestMapping(value = "/confirm",method = RequestMethod.POST)
    @ResponseBody
    public IResult confirmUpdate(ShipmentOrder shipmentOrder){
        return new ResultBean<Boolean>(shipmentOrderService.confirmUpdate(shipmentOrder));
    }
}
