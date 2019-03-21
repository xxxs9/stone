package com.gameloft9.demo.controllers.system;
import com.gameloft9.demo.dataaccess.model.system.MarkerOrderTest;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.MarkerOrderService;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/marker")
public class MarkerOrderController {

   @Autowired
   MarkerOrderService markerOrderService;


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
     * 获取所有销售订单信息
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public IResult findAll(String page, String limit, String orderId, Date orderTime){
        List<MarkerOrderTest> list = markerOrderService.findAll(page, limit, orderId, orderTime);
        return new PageResultBean<Collection<MarkerOrderTest>>(list,markerOrderService.countGetAll(orderId,orderTime));
    }
    /**
     * 删除
     */
   @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public IResult deleteById(String id){
       return new ResultBean(markerOrderService.deleteById(id));
   }

    /**
     * 修改
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public IResult update(MarkerOrderTest markerOrderTest){
        return new ResultBean<Boolean>(markerOrderService.update(markerOrderTest));
    }

    /**
     * 获取销售订单ID
     */
    @RequestMapping(value = "/get",method = RequestMethod.POST)
    @ResponseBody
    public IResult getMaker(String id){
        return new ResultBean<MarkerOrderTest>(markerOrderService.getMaker(id));
    }

    /**
     * 增加
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public IResult add(MarkerOrderTest markerOrderTest){
       return new ResultBean<String>(markerOrderService.add(markerOrderTest));
    }
    /**
     * 获取所有类表信息
     */
   /* @RequestMapping(value = "/selectAll",method = RequestMethod.POST)
      @ResponseBody
    public IResult selectAll(){
        List<MarkerOrderTest> list = markerOrderService.selectAll();
        return new ResultBean<Collection<MarkerOrderTest>>(list);
    }*/

    /**
     * 提交
     */
    @RequestMapping(value = "/audi",method = RequestMethod.POST)
    @ResponseBody
    public IResult audiUpdate(MarkerOrderTest markerOrderTest){
        return new ResultBean<Boolean>(markerOrderService.audiUpdate(markerOrderTest));
    }

    /**
     * 撤回
     */
    @RequestMapping(value = "/back",method = RequestMethod.POST)
    @ResponseBody
    public IResult backUpdate(MarkerOrderTest markerOrderTest){
        return new ResultBean<Boolean>(markerOrderService.backUpdate(markerOrderTest));
    }
}
