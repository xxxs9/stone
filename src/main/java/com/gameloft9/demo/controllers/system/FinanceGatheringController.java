package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.SysFinanceGathering;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.service.api.system.FinanceGatheringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * @author: 啊发包
 * @Date: 2019/03/20 2019-03-20
 */
@Controller
@RequestMapping(value = "/finance")
public class FinanceGatheringController {

    @Autowired
    FinanceGatheringService gatheringService;

    /**
     *  分页收款项
     *
     * @param page 当前页
     * @param limit 每页条数
     * @param payType 订单类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return jason
     */
    @RequestMapping(value = "/gatheringList" ,method = RequestMethod.POST)
    @ResponseBody
    public IResult gatheringList(String page, String limit, String payType, String startTime, String endTime){
        return new PageResultBean<Collection<SysFinanceGathering>>(gatheringService.getAll(page,limit,payType,startTime,endTime),
                gatheringService.getCount(payType,startTime,endTime));
    }

}
