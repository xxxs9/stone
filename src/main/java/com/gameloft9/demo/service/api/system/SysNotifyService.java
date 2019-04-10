package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.SysNotify;
import com.gameloft9.demo.dataaccess.model.system.SysNotifyInfo;
import com.gameloft9.demo.dataaccess.model.system.Whatever;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;

/**
 * Demo class
 *
 * @author xukailun
 * @date 2019/3/19
 *   信息表的接口方法
 */
public interface SysNotifyService {


    List<SysNotify> getAll(String  page, String limit, String creater, String receiverId, String state);
    List<SysNotify>findOutBox(@Param("loginName") String loginName);
    List<SysNotify>findSysNofiyForAll(@Param("loginName") String loginName);
    List<SysNotify>findAllReply();
    int  getNewMessage(@Param("loginName") String loginName);
    SysNotify findMessage(String id);
    SysNotify sendmessage(String id);


     SysNotify selectByPrimaryKey(String id);

    int countGetAll(String creater, String receiverId, String status);

    int hideForByPrimaryKey(String id);

    /**
     * 根据id删除用户
     * @param id 用户id
     * */
    Boolean deleteById(String id);
    /**
     * 根据页面传来的参数转成实体类对象
     * @param whatever 实体类对象
     * */
    int add(Whatever whatever) throws IOException;

    /**
     * 修改
     * @param sysNotify 对这个对象进行update操作
     * */

    Boolean update(SysNotify sysNotify);




}
