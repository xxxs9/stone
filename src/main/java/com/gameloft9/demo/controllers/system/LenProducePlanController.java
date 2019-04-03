package com.gameloft9.demo.controllers.system;


import com.gameloft9.demo.dataaccess.model.system.LenProducePlan;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.impl.system.LenProducePlanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author : Lennon_Yuan
 * @time : 2019-03-23 - 14:03
 * @description :
 */
@Controller
@RequestMapping("/plan")
public class LenProducePlanController {

    @Autowired
    LenProducePlanServiceImpl service;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectAll(){
        return new ResultBean<List>( service.selectAll());
    }

    @RequestMapping(value = "/pageList",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectAllByPage(String page, String limit, String productId, String state){
        return new PageResultBean<List>(service.selectByPage(page,limit,productId,state),service.dataCount(state));
    }

    @RequestMapping("/add")
    @ResponseBody
    public IResult add(String productId,String planNumber,
                       String realNumber,
                       String state,
                       String goodNumber,
                       String produceDate,
                       String finishDate,
                       String billDate,
                       String billCycle,
                       String planRemark,
                       String other1,
                       String other2,
                       String other3){
        return new ResultBean<Boolean>(service.insert(productId, planNumber, realNumber, state, goodNumber, produceDate, finishDate, billDate, billCycle, planRemark, other1, other2, other3));
    }

    @RequestMapping("/upd")
    @ResponseBody
    public IResult update(String id,String productId,String planNumber,
                          String realNumber,
                          String state,
                          String goodNumber,
                          String produceDate,
                          String finishDate,
                          String billDate,
                          String billCycle,
                          String planRemark,
                          String other1,
                          String other2,
                          String other3){
        return  new ResultBean<Boolean>(service.update(id, productId, planNumber, realNumber, state, goodNumber, produceDate, finishDate, billDate, billCycle, planRemark, other1, other2, other3));
    }

    @RequestMapping("/del")
    @ResponseBody
    public IResult delete(String id){
        return  new ResultBean<Boolean>(service.delete(id));
    }

    @RequestMapping(value = "/get",method = RequestMethod.POST)
    @ResponseBody
    public  IResult getById(String id){
        return  new ResultBean<LenProducePlan>(service.getByPrimaryKey(id));
    }

    @RequestMapping(value = "/chg",method = RequestMethod.POST)
    @ResponseBody
    public  IResult changeState(String id){
        return  new ResultBean<Boolean>(service.changeState(id));
    }

    /**更改other1字段*/
    @RequestMapping(value = "/cho",method = RequestMethod.POST)
    @ResponseBody
    public  IResult chgother(String id){
        return  new ResultBean<Boolean>(service.changeOther(id));
    }

    @RequestMapping(value = "/findId",method = RequestMethod.POST)
    @ResponseBody
    public  IResult findId(String productId){
        return  new ResultBean<LenProducePlan>(service.findId(productId));
    }

}
