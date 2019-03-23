package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.SysFinanceSaleReceivable;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.service.api.system.FinanceSaleReceivableService;
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
@RequestMapping("/finance")
public class FinanceSaleReceivableController {

    @Autowired
    FinanceSaleReceivableService financeSaleReceivableService;

    /**
     *  分页查询销售应收单
     *
     * @param page 当前页
     * @param limit 每页条数
     * @param auditType 订单类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return jason
     */
    @RequestMapping(value = "/saleReceiveList", method = RequestMethod.POST)
    @ResponseBody
    public IResult saleBillPayList(String page, String limit, String auditType, String startTime, String endTime){
        return new PageResultBean<Collection<SysFinanceSaleReceivable>>(financeSaleReceivableService.getAll(page,limit,auditType,startTime,endTime),
                financeSaleReceivableService.getCount(auditType,startTime,endTime));
    }
}
