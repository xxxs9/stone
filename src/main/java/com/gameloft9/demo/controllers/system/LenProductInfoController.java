package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.LenProductInfo;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.impl.system.LenProductInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author : Lennon_Yuan
 * @time : 2019-03-25 - 01:18
 * @description :
 */
@Controller
@RequestMapping("/productInfo")
public class LenProductInfoController {
    @Autowired
    LenProductInfoServiceImpl service;


    @RequestMapping(value = "/list" ,method = RequestMethod.POST)
    @ResponseBody
     public IResult getAll(){
         return  new ResultBean<List>(service.selectAll());
     }

    @RequestMapping(value = "/get" ,method = RequestMethod.POST)
    @ResponseBody
    public IResult getById(String id){
        return  new ResultBean<LenProductInfo>(service.selectById(id));
    }
}
