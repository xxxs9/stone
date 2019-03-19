package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.SysFinancePurchaseBillsPayable;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.service.api.system.FinancePurchaseBillPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * @author: 啊发包
 * @Date: 2019/03/19 2019-03-19
 */


@Controller
@RequestMapping(value = "/finance")
public class FinancePurchaseBillPayController {

    @Autowired
    FinancePurchaseBillPayService purchaseBillPayService;

    /**
     *  分页查询销售
     *
     * @param page 当前页
     * @param limit 每页条数
     * @param auditType 订单类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return jason
     */
    @RequestMapping(value = "/purchaseBillPayList" ,method = RequestMethod.POST)
    @ResponseBody
    public IResult billPayList(String page, String limit, String auditType, String startTime, String endTime){

        return new PageResultBean<Collection<SysFinancePurchaseBillsPayable>>(purchaseBillPayService.getAll(page,limit,auditType,startTime,endTime), purchaseBillPayService.getCount(auditType,startTime,endTime));
    }
}
