package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.SysFinanceBill;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.service.api.system.FinanceBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * @author: 啊发包
 * @Date: 2019/03/26 2019-03-26
 */
@Controller
@RequestMapping("/bill")
public class FinanceBillController {

    @Autowired
    FinanceBillService billService;

    /**
     * 分页显示
     * @param page 当前页
     * @param limit 当前页条数
     * @return
     *      json
     */
    @RequestMapping(value = "/billList" ,method = RequestMethod.POST)
    @ResponseBody
    public IResult getAll(String page,String limit){
        return new PageResultBean<Collection<SysFinanceBill>>(billService.getAll(page,limit),billService.getCount());
    }
}
