package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.SysNotify;
import com.gameloft9.demo.dataaccess.model.system.Whatever;
import com.gameloft9.demo.mgrframework.annotation.BizOperLog;
import com.gameloft9.demo.mgrframework.beans.constant.OperType;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.SysNotifyInfoService;
import com.gameloft9.demo.service.api.system.SysNotifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collection;

/**
 * 系统用户
 * Created by gameloft9 on 2017/12/20.
 */
@Slf4j
@Service
@RequestMapping("/sysNotify")
public class SysNotifyController {

    @Autowired
    SysNotifyService SysNotifyServiceImpl;
    @Autowired
    SysNotifyInfoService SysNotifyInfoServiceImpl;

    /**
     * 获取所有用户列表

     * */

    /*获取新的消息提示*/
    @RequestMapping(value = "/getNewMessage.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getNewMessage() throws Exception{

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String loginName =(String) request.getSession().getAttribute("sysUser");
        System.out.println(loginName);
        return new ResultBean<Integer>(SysNotifyServiceImpl.getNewMessage(loginName));
    }


    @RequestMapping(value = "/NotifyList.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getUserList(String page, String limit, String creater, String receiverId, String state){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new PageResultBean<Collection<SysNotify>>(SysNotifyServiceImpl.getAll(page,limit,creater,receiverId,state),SysNotifyServiceImpl.countGetAll(creater,receiverId,state));
    }
    /**
     * 查询收件箱信息列表

     * */
    @RequestMapping(value = "/allNotifyForAll.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult findSysNofiyForAll(SysNotify sysNotify ){
        //返回json至前端的均返回ResultBean或者PageResultBean

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String loginName =(String) request.getSession().getAttribute("sysUser");
        System.out.println(loginName);
        return new ResultBean<Collection<SysNotify>>(SysNotifyServiceImpl.findSysNofiyForAll(loginName));
    }

/* 发件箱的控制层*/
@RequestMapping(value = "/allOutBox.do",method = RequestMethod.POST)
@ResponseBody
    public IResult findBoxOut(SysNotify sysNotify ){
        //返回json至前端的均返回ResultBean或者PageResultBean
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String loginName =(String) request.getSession().getAttribute("sysUser");
        System.out.println(loginName);
        return new ResultBean<Collection<SysNotify>>(SysNotifyServiceImpl.findOutBox(loginName));
    }
    /**
     * 添加用户
     * */
    @RequestMapping(value = "/add.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.ADD,memo = "添加用户")
    public IResult add(Whatever whatever) throws IOException {
        //返回json至前端的均返回ResultBean或者PageResultBean





        return new ResultBean<Integer>(SysNotifyServiceImpl.add(whatever)
        );
    }

    /**
     * 删除用户
     * */
    @RequestMapping(value = "/delete.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.DELETE,memo = "删除用户")
    public IResult delete(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(SysNotifyServiceImpl.deleteById(id));
    }

    /*
    * 状态变更
    * */
    @RequestMapping(value = "/updatestate.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.DELETE,memo = "状态变更")
    public IResult updatestate(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Integer>(SysNotifyServiceImpl.hideForByPrimaryKey(id));
    }

    /**
     * 获取用户
     * */
    @RequestMapping(value = "/get.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getNotify(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<SysNotify>(SysNotifyServiceImpl.selectByPrimaryKey(id));
    }

    @RequestMapping(value = "/getmessage.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getmessage(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<SysNotify>(SysNotifyServiceImpl.findMessage(id));
    }

    /* 回复时 获取发送信息额对方资料 */
    @RequestMapping(value = "/getSendMessageInfo.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getSendMessageInfo(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<SysNotify>(SysNotifyServiceImpl.sendmessage(id));
    }
/*
    *//**
     * 更新用户
     * *//*
    @RequestMapping(value = "/update.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.UPDATE,memo = "更新用户")
    public IResult updateUser(@RequestBody @Valid SysNotify sysNotify, BindingResult result){//传递了数组，前台放在payload里面了，后台通过@RequestBody获取
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Collection<SysNotify>>(SysNotifyServiceImpl.findSysNofiyForAll());
    }*/

    @RequestMapping(value = "/findAllReply.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult findAllReply (SysNotify sysNotify){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Collection<SysNotify>>(SysNotifyServiceImpl.findAllReply());
    }


}
