package com.gameloft9.demo.controllers.system;


import com.gameloft9.demo.dataaccess.model.system.LenProduceFormula;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.impl.system.LenProduceFormulaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @packageName: com.gameloft9.demo.controllers.system
 * @author: Lennon_Yuan
 * @time: 2019/3/20 0020 - 上午 10:37
 * @description:
 */
@Controller
@RequestMapping("/formula")
public class LenProduceFormulaController {
    @Autowired
    LenProduceFormulaServiceImpl service;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectAll(){
        return new ResultBean<List>( service.selectAll());
    }

    @RequestMapping(value = "/pageList",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectAllByPage(String page, String limit, String createUser, String productId){
        return new PageResultBean<List>(service.selectByPage(page,limit,createUser,productId),service.dataCount(productId));
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public IResult add(String productId,String formulaType ,String formulaNumber,String createUser,String createTime){
        return new ResultBean<Boolean>(service.insert(productId, formulaType, formulaNumber, createUser, createTime));
    }

    @RequestMapping(value = "/upd",method = RequestMethod.POST)
    @ResponseBody
    public IResult update(String productId,String formulaType ,String formulaNumber,String createUser,String createTime){
        return  new ResultBean<Boolean>(service.update(productId, formulaType, formulaNumber, createUser, createTime));
    }

    @RequestMapping("/del")
    @ResponseBody
    public IResult delete(String id){
        return  new ResultBean<Boolean>(service.delete(id));
    }

    @RequestMapping(value = "/get",method = RequestMethod.POST)
    @ResponseBody
    public  IResult getById(String id){
        return  new ResultBean<LenProduceFormula>(service.getByPrimaryKey(id));
    }

    @RequestMapping(value = "/getByProductId",method = RequestMethod.POST)
    @ResponseBody
    public  IResult getByProductId(String id){
        return  new ResultBean<List>(service.getByProductId(id));
    }


}
