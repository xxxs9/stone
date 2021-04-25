package com.gameloft9.demo.controllers.system;


import com.gameloft9.demo.dataaccess.model.system.LenCreateUserInfo;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.impl.system.LenCreateUserInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public IResult selectAllByPage(String page, String limit, String createUser, String createTime){
        return new PageResultBean<List>(service.selectByPage(page, limit, createUser, createTime),service.dataCount(createUser));
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public IResult add(String createUser,String createTime ,String employeeId){
        return new ResultBean<Boolean>(service.insert(createUser,createTime,employeeId));
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

    @RequestMapping("/get")
    @ResponseBody
    public IResult getCreateById(String id){
        return  new ResultBean<LenCreateUserInfo>(service.getByPrimaryKey(id));
    }
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {

        //转换日期
        // CustomDateEditor为自定义日期编辑器
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
