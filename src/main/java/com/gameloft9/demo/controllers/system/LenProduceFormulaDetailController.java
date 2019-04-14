package com.gameloft9.demo.controllers.system;


import com.gameloft9.demo.dataaccess.model.system.LenProduceFormulaDetail;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.impl.system.LenProduceFormulaDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @packageName: com.gameloft9.demo.controllers.system
 * @author: Lennon_Yuan
 * @time: 2019/3/20 0020 - 上午 11:08
 * @description:
 */
@Controller
@RequestMapping("/detail")
public class LenProduceFormulaDetailController {


    @Autowired
    LenProduceFormulaDetailServiceImpl service;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectAll(){
        return new ResultBean<List>( service.selectAll());
    }

    @RequestMapping(value = "/pageList",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectAllByPage(String page, String limit, String other2, String depotId){
        return new PageResultBean<List>(service.selectByPage(page,limit,other2,depotId),service.dataCount());
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public IResult add(LenProduceFormulaDetail len){
        return new ResultBean<Boolean>(service.insert(len));
    }

    @RequestMapping(value = "/upd",method = RequestMethod.POST)
    @ResponseBody
    public IResult update(LenProduceFormulaDetail len){
        return  new ResultBean<Boolean>(service.update(len));
    }

    @RequestMapping("/del")
    @ResponseBody
    public IResult delete(String id){
        return  new ResultBean<Boolean>(service.delete(id));
    }


    @RequestMapping("/get")
    @ResponseBody
    public IResult getById(String id){
        return  new ResultBean<LenProduceFormulaDetail>(service.getByPrimaryKey(id));
    }

    @RequestMapping("/getDetailByFormulaId")
    @ResponseBody
    public IResult getByFormulaId(String id){
        return  new ResultBean<List>(service.getByFormulaId(id));
    }


}
