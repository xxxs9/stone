package com.gameloft9.demo.controllers.system;
/*

import com.gameloft9.demo.dataaccess.model.system.SysFinancePayment;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.service.api.system.FinancePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

*/
/**
 * @author: 啊发包
 * @Date: 2019/03/20 2019-03-20
 *//*

@Controller
@RequestMapping(value = "/finance")
public class FinancePaymentController {

    @Autowired
    FinancePaymentService paymentService;

    */
/**
     *  分页付款项
     *
     * @param page 当前页
     * @param limit 每页条数
     * @param payType 订单类型
     * @return jason
     *//*

    @RequestMapping(value = "/paymentList" ,method = RequestMethod.POST)
    @ResponseBody
    public IResult paymentList(String page, String limit, String payType){
        return new PageResultBean<Collection<SysFinancePayment>>(paymentService.getAll(page,limit,payType),
                paymentService.getCount(payType));
    }
}
*/
