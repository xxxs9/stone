package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.DepotPersonnel;
import com.gameloft9.demo.dataaccess.model.system.SysDepot;
import com.gameloft9.demo.mgrframework.annotation.BizOperLog;
import com.gameloft9.demo.mgrframework.beans.constant.OperType;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.DepotPersonnelService;
import com.gameloft9.demo.service.api.system.SysDepotService;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

/**
 * @author: Sxiu
 * @create: 2019/3/19 13:58
 * @description:
 */

@Slf4j
@Service
@RequestMapping("/depotPersonnel")
public class DepotPersonnelController {
    @Autowired
    private DepotPersonnelService depotPersonnelServiceImpl;

    /**
     * 获取仓库人员列表
     * @param page                  页序
     * @param limit                 分页大小
     * @param depotNumber           仓库编号
     * @param userLoginName         用户登入名
     */
    @RequestMapping(value = "/depotPersonnelList.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getDepotPersonnelList(String page, String limit,String depotNumber,String userLoginName){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new PageResultBean<Collection<DepotPersonnel>>(depotPersonnelServiceImpl.getAll(page,limit,depotNumber,userLoginName),depotPersonnelServiceImpl.countGetAll(depotNumber,userLoginName));
    }

    /**
     * 添加仓库人员
     * @param depotNumber           仓库编号
     * @param userLoginName         用户登入名
     * @param contactAddress        联系地址
     * @param birthday              出生日期
     * @param personPortrait        人员头像
     * */
    @RequestMapping(value = "/add.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.ADD,memo = "新增仓库人员信息")
    public IResult addDepotPersonnel(String depotNumber,String userLoginName,String contactAddress,String birthday,String personPortrait){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<String>(depotPersonnelServiceImpl.addDepotPersonnel(depotNumber,userLoginName,contactAddress,birthday,personPortrait));
    }

    /**
     * 获取仓库信息
     * @param id 仓库主键
     * */
    @RequestMapping(value = "/get.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getDepotPersonnel(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<DepotPersonnel>(depotPersonnelServiceImpl.getById(id));
    }

    /**
     * 更新仓库
     * @param id                    仓库人员主键
     * @param depotNumber           仓库编号
     * @param userLoginName         用户登入名
     * @param contactAddress        联系地址
     * @param birthday              出生日期
     * @param personPortrait        人员头像
     * @return
     */
    @RequestMapping(value = "/update.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.UPDATE,memo = "更新仓库人员信息")
    public IResult updatDepotPersonnel(String id,String depotNumber,String userLoginName,String contactAddress,String birthday,String personPortrait){//传递了数组，前台放在payload里面了，后台通过@RequestBody获取
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(depotPersonnelServiceImpl.updateDepotPersonnel(id,depotNumber,userLoginName,contactAddress,birthday,personPortrait));
    }

    /**
     * 删除仓库人员信息
     * */
    @RequestMapping(value = "/delete.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.DELETE,memo = "删除仓库人员信息")
    public IResult deleteDepotPersonnel(String id){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(depotPersonnelServiceImpl.deleteDepotPersonnel(id));
    }

    /**
     * 批量删除仓库人员信息
     * */
    @RequestMapping(value = "/dels.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.DELETE,memo = "批量删除仓库人员信息")
    public IResult delsDepotPersonnel(String ids){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(depotPersonnelServiceImpl.delsDepotPersonnel(ids));
    }

    /**
     * 获取所有仓库人员的用户登入名
     * */
    @RequestMapping(value = "/getDepotUserLoginName.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getDepotUserLoginName(){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<List<String>>(depotPersonnelServiceImpl.getDepotUserLoginName());
    }
}
