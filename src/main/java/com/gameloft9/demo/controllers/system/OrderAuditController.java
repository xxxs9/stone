package com.gameloft9.demo.controllers.system;


import com.gameloft9.demo.dataaccess.model.system.OrderAudit;
import com.gameloft9.demo.dataaccess.model.system.OrderAuditBean;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.OrderAuditService;
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
@RequestMapping("/audit")
public class OrderAuditController {

    @Autowired
    OrderAuditService orderAuditService;

    /**
     * 处理时间
     *
     * @param binder
     * @param request
     */
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {
        //转换日期
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // CustomDateEditor为自定义日期编辑器
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }


    /**
     * 分页查询
     */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public IResult findAll(String page, String limit, String productId) {

        List<OrderAudit> list = orderAuditService.findAll(page, limit, productId);
        return new PageResultBean<Collection<OrderAudit>>(list, orderAuditService.dataCount());
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody

    public IResult deleteById(String id) {

        return new ResultBean<Integer>(orderAuditService.deleteById(id));
    }

    /**
     * 获取订单审核ID
     */
    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public IResult getById(String id) {
        OrderAuditBean byId = orderAuditService.getById(id);
        return new ResultBean(byId);
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody

    public IResult update(OrderAuditBean orderAuditBean) {

        return new ResultBean<Boolean>(orderAuditService.update(orderAuditBean));
    }

    /**
     * 驳回
     */
    @RequestMapping(value = "/back", method = RequestMethod.POST)
    @ResponseBody
    public IResult backUpdate(OrderAuditBean orderAuditBean) {

        return new ResultBean<Boolean>(orderAuditService.backUpdate(orderAuditBean));
    }

    /**
     * 审核成功
     */
    @RequestMapping(value = "/pass", method = RequestMethod.POST)
    @ResponseBody
    public IResult passUpdate(OrderAuditBean orderAuditBean) {
        return new ResultBean<Boolean>(orderAuditService.passUpdate(orderAuditBean));
    }

    /**
     * 审核
     */
    @RequestMapping(value = "/audit", method = RequestMethod.POST)
    @ResponseBody
    public IResult audit(OrderAuditBean orderAuditBean) {
        return new ResultBean<Boolean>(orderAuditService.passUpdate(orderAuditBean));
    }
}