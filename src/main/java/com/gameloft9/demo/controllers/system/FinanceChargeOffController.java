package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.SysFinanceChargeOff;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.service.api.system.FinanceChargeOffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * @author: 啊发包
 * @Date: 2019/03/21 2019-03-21
 */
@Controller
@RequestMapping(value = "/finance")
public class FinanceChargeOffController {

    @Autowired
    FinanceChargeOffService chargeOffService;

    /**
     * 分页显示出账单
     *
     * @param page 当前页
     * @param limit 当前页条数
     * @param payType 支付类型
     * @return
     *      jason
     */
    @RequestMapping(value = "/chargeOffList", method = RequestMethod.POST)
    @ResponseBody
    public IResult chargeOffList(String page,String limit,String payType){
        return new PageResultBean<Collection<SysFinanceChargeOff>>(chargeOffService.getAll(page,limit,payType),
                chargeOffService.getCount(payType));
    }

}
