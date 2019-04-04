package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.SysNotify;
import com.gameloft9.demo.dataaccess.model.system.UserTest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by gameloft9 on 2017/11/28.
 */
public interface SysNotifyMapper {

    SysNotify findMessage(String id);
    int deleteByPrimaryKey(String id);

    int insert(SysNotify sysnotify);

    SysNotify selectByPrimaryKey(String id);

    int updateByPrimaryKey(SysNotify sysnotify);
    int hideForByPrimaryKey(String id);

    List<SysNotify> findOutBox(String loginName);
    List<SysNotify> findSysNofiyForAll(String loginName);
    List<SysNotify> findAllReply();
    SysNotify replyMessage(String id);
    int getNewMessage(String loginName);
    /**
     * 查找用户
     *
     * @param loginName 登录名
     * @param password  密码（为了不增加复杂度，这里不进行加密，使用明文）
     */
    Integer countUserByNameAndPwd(@Param("loginName") String loginName,
                                  @Param("password") String password);

    /**
     * 根据loginName获取用户
     *
     * @param loginName 登录名
     */
    UserTest getByLoginName(@Param("loginName") String loginName);

    /**
     * 分页获取用户列表
     */
    List<SysNotify> getAll(
            @Param("start") int start,
            @Param("end") int end,
            @Param("creater") String creater,
            @Param("receiverId") String receiverId,
            @Param("state") String state);




    /**
     * 获取用户列表大小
     */
    int countGetAll(@Param("creater") String creater,
                    @Param("receiverId") String receiverId,
                    @Param("state") String state);
}
