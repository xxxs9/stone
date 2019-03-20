package com.gameloft9.demo.controllers.system;


import com.gameloft9.demo.dataaccess.model.system.LenCreateUserInfo;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.impl.system.LenCreateUserInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @packageName: com.gameloft9.demo.controllers.system
 * @author: Lennon_Yuan
 * @time: 2019/3/20 0020 - 下午 1:40
 * @description:
 */
@Controller
@RequestMapping("/create")
public class LenCreateUserInfoController {
    @Autowired
    LenCreateUserInfoServiceImpl service;

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectAll(){
        return new ResultBean<List>( service.selectAll());
    }

    @RequestMapping(value = "/pageList",method = RequestMethod.POST)
    @ResponseBody
    public IResult selectAllByPage(String page, String limit, String materialId, String depotId){
        return new PageResultBean<List>(service.selectByPage(page,limit,materialId,depotId),service.dataCount(depotId));
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public IResult add(LenCreateUserInfo len){
        return new ResultBean<Boolean>(service.insert(len));
    }

    @RequestMapping(value = "/upd",method = RequestMethod.POST)
    @ResponseBody
    public IResult update(LenCreateUserInfo len){
        return  new ResultBean<Boolean>(service.update(len));
    }

    @RequestMapping("/del")
    @ResponseBody
    public IResult delete(String id){
        return  new ResultBean<Boolean>(service.delete(id));
    }
}
