package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.LenProductWaste;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.LenProductService;
import com.gameloft9.demo.service.api.system.LenProductWasteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @packageName: com.gameloft9.demo.controllers.system
 * @author: Lennon_Yuan
 * @time: 2019/3/19 0019 - 下午 4:41
 * @description:
 */
@Controller
@RequestMapping("/productWaste")
public class LenProductWasteController {
    @Autowired
    LenProductWasteService service;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectAll(){
        return new ResultBean<List>( service.selectAll());
    }

    @RequestMapping(value = "/pageList",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectAllByPage(String page, String limit, String state){
        return new PageResultBean<List>(service.selectByPage(page,limit,state),service.dataCount(state));
    }

    @RequestMapping("/add")
    @ResponseBody
    public IResult add(LenProductWaste lenProduct){
        return new ResultBean<Boolean>(service.insert(lenProduct));
    }

    @RequestMapping("/upd")
    @ResponseBody
    public IResult update(LenProductWaste lenProduct){
        return  new ResultBean<Boolean>(service.update(lenProduct));
    }

    @RequestMapping("/del")
    @ResponseBody
    public IResult delete(String id){
        return  new ResultBean<Boolean>(service.delete(id));
    }

    @RequestMapping("/get")
    @ResponseBody
    public IResult getById(String id){
        return  new ResultBean<LenProductWaste>(service.getByPrimaryKey(id));
    }



}
