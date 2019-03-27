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
     * @param auditState 订单类型
     * @return jason
     */
    @RequestMapping(value = "/saleReceiveList", method = RequestMethod.POST)
    @ResponseBody
    public IResult saleBillPayList(String page, String limit, String auditState){
        return new PageResultBean<Collection<SysFinanceSaleReceivable>>(financeSaleReceivableService.getAll(page,limit,auditState),
                financeSaleReceivableService.getCount(auditState));
    }
}
