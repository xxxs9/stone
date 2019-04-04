package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.LenProductProductPlan;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.impl.system.LenProductProducePlanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author : Lennon_Yuan
 * @time : 2019-03-26 - 11:06
 * @description :ppp:(productProducePlan)
 */
@Controller
@RequestMapping("/ppp")
public class LenProductProducePlanController {
    @Autowired
    LenProductProducePlanServiceImpl service;


    @RequestMapping("/list")
    @ResponseBody
    public IResult selectAll(){
        return new ResultBean<List>(service.selectAll());
    }

    @RequestMapping("/get")
    @ResponseBody
    public IResult getById(String id){
        return new ResultBean<LenProductProductPlan>(service.getById(id));
    }

}
