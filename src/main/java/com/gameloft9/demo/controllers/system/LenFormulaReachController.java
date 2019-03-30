package com.gameloft9.demo.controllers.system;


import com.gameloft9.demo.dataaccess.model.system.LenFormulaReach;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.impl.system.LenFormulaReachServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author : Lennon_Yuan
 * @time : 2019-03-23 - 23:22
 * @description :
 */

@Controller
@RequestMapping("/reach")
public class LenFormulaReachController {


    @Autowired
    LenFormulaReachServiceImpl service;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectAll(){
        return new ResultBean<List>( service.selectAll());
    }

    @RequestMapping(value = "/pageList",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectAllByPage(String page, String limit, String productId, String reachUser){
        return new PageResultBean<List>(service.selectByPage(page,limit,productId,reachUser),service.dataCount());
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public IResult add(String id, String productId, String produceFormulaId, String produceFormulaDetailId, String depotAudi, String formulaBack, String state, String reachUser, String reachTime){

        return new ResultBean<Boolean>(service.insert(id, productId, produceFormulaId, produceFormulaDetailId, depotAudi, formulaBack, state, reachUser, reachTime));
    }

    @RequestMapping(value = "/upd",method = RequestMethod.POST)
    @ResponseBody
    public IResult update(String id, String productId, String produceFormulaId, String produceFormulaDetailId, String depotAudi, String formulaBack, String state, String reachUser, String reachTime){
        return  new ResultBean<Boolean>(service.update(id, productId, produceFormulaId, produceFormulaDetailId, depotAudi, formulaBack, state, reachUser, reachTime));
    }

    @RequestMapping("/del")
    @ResponseBody
    public IResult delete(String id){
        return  new ResultBean<Boolean>(service.delete(id));
    }

    @RequestMapping(value = "/get",method = RequestMethod.POST)
    @ResponseBody
    public  IResult getById(String id){
        return  new ResultBean<LenFormulaReach>(service.getByPrimaryKey(id));
    }

    @RequestMapping(value = "/chg",method = RequestMethod.POST)
    @ResponseBody
    public  IResult changeState(String id){
        return  new ResultBean<Boolean>(service.changeState(id));
    }

    @RequestMapping(value = "/stop",method = RequestMethod.POST)
    @ResponseBody
    public  IResult stopstate(String id){
        boolean flag= true;
        if(service.stopProduct(id)){
            return new ResultBean<Boolean>(flag);
        }else{
          new RuntimeException("操作失败");
        }
        return new ResultBean<Boolean>(flag);
    }

    @RequestMapping(value = "/goOn",method = RequestMethod.POST)
    @ResponseBody
    public  IResult goOn(String id){
        boolean flag= true;
        if(service.goOn(id)){
            return new ResultBean<Boolean>(flag);
        }else{
            new RuntimeException("操作失败");
        }
        return new ResultBean<Boolean>(flag);
    }
    @RequestMapping(value = "/complete",method = RequestMethod.POST)
    @ResponseBody
    public  IResult complete(String id){
        boolean flag= true;
        if(service.complete(id)){
            return new ResultBean<Boolean>(flag);
        }else{
            new RuntimeException("操作失败");
        }
        return new ResultBean<Boolean>(flag);
    }
}
