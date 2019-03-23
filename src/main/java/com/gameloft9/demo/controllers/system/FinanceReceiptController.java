package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.SysFinanceReceipt;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.service.api.system.FinanceReceiptService;
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
public class FinanceReceiptController {

    @Autowired
    FinanceReceiptService receiptService;

    /**
     * 分页显示入账单列表
     *
     * @param page 当前页
     * @param limit 当前页记录数
     * @param receiveType 收款单类型
     * @return
     *      jason
     */
    @RequestMapping(value = "/receiptList", method = RequestMethod.POST)
    @ResponseBody
    public IResult receiptList(String page,String limit,String receiveType){
        return new PageResultBean<Collection<SysFinanceReceipt>>(receiptService.getAll(page,limit,receiveType),
                receiptService.getCount(receiveType));
    }
}
