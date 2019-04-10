package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.SysNotify;
import com.gameloft9.demo.dataaccess.model.system.SysNotifyInfo;

import java.util.List;

/**
 * Demo class
 *
 * @author xukailun
 * @date 2019/3/19
 *   信息表的接口方法
 */
public interface SysNotifyInfoService {


    /*List<SysNotify> getAll(String page, String limit, String creater, String receiverId, String state);


     SysNotify selectByPrimaryKey(String id);

    int countGetAll(String creater, String receiverId, String status);*/

    /**
     * 根据id删除用户
     * @param id 用户id
     * */
   /* Boolean deleteById(String id);*/
    /**
     * 根据页面传来的参数转成实体类对象
     * @param sysNotifyInfo 实体类对象
     * */
    int add(SysNotifyInfo sysNotifyInfo,SysNotify sysNotify);
    /**
     * 修改
     * @param sysNotify 对这个对象进行update操作
     * */
 /*   Boolean update(SysNotify sysNotify);*/




}
