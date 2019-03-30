package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.impl.system.LenFormulaReachServiceImpl;
import com.gameloft9.demo.service.impl.system.LenProductPlanReachCheckServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author : Lennon_Yuan
 * @time : 2019-03-29 - 11:34
 * @description :pcr(product-check-reach)
 */
@Controller
@RequestMapping("/pcr")
public class LenProductPlanReachCheckController {
    @Autowired
    LenProductPlanReachCheckServiceImpl service;
    @Autowired
    LenFormulaReachServiceImpl frService;
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectAll(){
        return new ResultBean<List>( service.selectPCR());
    }
    @RequestMapping(value = "/reachList",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectReachAll(String page,String limit,String productName,String reachUser){
        return new PageResultBean<List>( service.selectReach(page, limit, productName, reachUser),frService.dataCount());
    }
}
