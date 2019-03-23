package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.SysFinanceApplyOrder;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.service.api.system.FinanceApplyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * @author: 啊发包
 * @Date: 2019/03/22 2019-03-22
 */
@Controller
@RequestMapping("/finance")
public class FinanceApplyOrderController {

    @Autowired
    FinanceApplyOrderService applyOrderService;

    @RequestMapping(value = "applyList", method = RequestMethod.POST)
    @ResponseBody
    public IResult applyList(String page,String limit,String applyType,String applyState,String startTime,String endTime){
        return new PageResultBean<Collection<SysFinanceApplyOrder>>(applyOrderService.getAll(page,limit,applyType,applyState,startTime,endTime),
                applyOrderService.getCount(applyType,applyState,startTime,endTime));
    }


}
