package com.gameloft9.demo.controllers.system;


import com.gameloft9.demo.dataaccess.model.system.MarkerOrderTest;
import com.gameloft9.demo.dataaccess.model.system.ReturnGoodsOrder;
import com.gameloft9.demo.dataaccess.model.system.ShipmentOrder;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.ReturnGoodsOrderService;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Controller
@RequestMapping("/goods")

public class ReturnGoodsOrderController {

    @Autowired
    ReturnGoodsOrderService returnGoodsOrderService;

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
     * @param page
     * @param limit
     * @param goodsName
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public IResult findAll(String page, String limit, String goodsName){
        List<ReturnGoodsOrder> list = returnGoodsOrderService.findAll(page, limit, goodsName);
        return new PageResultBean<Collection<ReturnGoodsOrder>>(list,returnGoodsOrderService.countGetAll(goodsName));
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public IResult deleteById(String id){
        return new ResultBean(returnGoodsOrderService.deleteById(id));
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public IResult update(ShipmentOrder shipmentOrder){
        return new ResultBean<Boolean>(returnGoodsOrderService.update(shipmentOrder));
    }

    /**
     * 获取发货单ID
     * @param id
     * @return
     */
    @RequestMapping(value = "/get",method = RequestMethod.POST)
    @ResponseBody
    public IResult getById(String id){
        return new ResultBean<ShipmentOrder>(returnGoodsOrderService.getById(id));
    }

    /**
     * 增加
     * @param returnGoodsOrder
     * @param request
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public IResult add(ReturnGoodsOrder returnGoodsOrder, HttpServletRequest request){
        request.getSession().getAttribute("sysUser");
        System.out.println(returnGoodsOrder);
        return new ResultBean<String>(returnGoodsOrderService.add(returnGoodsOrder));
    }

    /**
     * 提交
     * @param shipmentOrder
     * @return
     */
    @RequestMapping(value = "/audit",method = RequestMethod.POST)
    @ResponseBody
    public IResult audit(ShipmentOrder shipmentOrder){
       return new ResultBean<Boolean>(returnGoodsOrderService.audit(shipmentOrder));
    }

    /**
     * 提交仓库
     * @param shipmentOrder
     * @return
     */
    @RequestMapping(value = "/depot",method = RequestMethod.POST)
    @ResponseBody
    public IResult depot(ShipmentOrder shipmentOrder){
        return new ResultBean<Boolean>(returnGoodsOrderService.depot(shipmentOrder));
    }
    /**
     * 提交财务
     * @param shipmentOrder
     * @return
     */
    @RequestMapping(value = "/finance",method = RequestMethod.POST)
    @ResponseBody
    public IResult finance(ShipmentOrder shipmentOrder){
        return new ResultBean<Boolean>(returnGoodsOrderService.finance(shipmentOrder));
    }
}
