package com.gameloft9.demo.controllers.system;


import com.gameloft9.demo.dataaccess.model.system.LenProductCheck;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.impl.system.LenProductCheckServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author : Lennon_Yuan
 * @time : 2019-03-24 - 14:14
 * @description :
 */
@Controller
@RequestMapping("/check")
public class LenProductCheckController {

    @Autowired
    LenProductCheckServiceImpl service;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectAll(){
        return new ResultBean<List>( service.selectAll());
    }

    @RequestMapping(value = "/pageList",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectAllByPage(String page, String limit,String checkUser ,String state){
        return new PageResultBean<List>(service.selectByPage(page,limit,checkUser,state),service.dataCount(state));
    }

    @RequestMapping("/add")
    @ResponseBody
    public IResult add(String id,
                       String producePlanId,
                       String formulaReachId,
                       String state,
                       String checkUser,
                       String checkTime,
                       String wasteId,
                       String checkRemark){
        return new ResultBean<Boolean>(service.insert(id, producePlanId, formulaReachId, state, checkUser, checkTime, wasteId, checkRemark));
    }

    @RequestMapping("/upd")
    @ResponseBody
    public IResult update(String id,
                          String producePlanId,
                          String formulaReachId,
                          String state,
                          String checkUser,
                          String checkTime,
                          String wasteId,
                          String checkRemark){
        return  new ResultBean<Boolean>(service.update(id, producePlanId, formulaReachId, state, checkUser, checkTime, wasteId, checkRemark));
    }

    @RequestMapping("/del")
    @ResponseBody
    public IResult delete(String id){
        return  new ResultBean<Boolean>(service.delete(id));
    }

    @RequestMapping(value = "/get",method = RequestMethod.POST)
    @ResponseBody
    public  IResult getById(String id){
        return  new ResultBean<LenProductCheck>(service.getByPrimaryKey(id));
    }

    @RequestMapping(value = "/chg",method = RequestMethod.POST)
    @ResponseBody
    public  IResult changeState(String id){
        return  new ResultBean<Boolean>(service.changeState(id));
    }

    @RequestMapping(value = "/unUse",method = RequestMethod.POST)
    @ResponseBody
    public  IResult unUse(String id){
        if (service.unUse(id)){
            boolean flag=true;
            return new ResultBean<Boolean>(flag);
        }else{
            return new ResultBean<Exception>( new RuntimeException());
        }

    }

}
