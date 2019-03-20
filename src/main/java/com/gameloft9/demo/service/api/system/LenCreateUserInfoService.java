package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.LenCreateUserInfo;

import java.util.List;

/**
 * @packageName: com.gameloft9.demo.service.api.system
 * @author: Lennon_Yuan
 * @time: 2019/3/20 0020 - 上午 11:17
 * @description:
 */
public interface LenCreateUserInfoService {

    /**
     * 查找所有
     *
     * @return
     */
    List<LenCreateUserInfo> selectAll();

    /**
     * 主键查找
     *
     * @param id
     * @return
     */
    LenCreateUserInfo getByPrimaryKey(String id);

    /**
     * 分页查找
     *
     * @param page
     * @param limit
     * @param createUser
     * @param createTime
     * @return
     */
    List<LenCreateUserInfo> selectByPage(
           String page,String limit,
            String createUser,
           String createTime
    );

    /**
     * 增加
     *
     * @param len
     * @return
     */
    boolean insert(LenCreateUserInfo len);

    /**
     * 修改
     *
     * @param len
     * @return
     */
    boolean update(LenCreateUserInfo len);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    boolean delete(String id);

    /**
     * 统计条数
     *
     * @param createUser
     * @return
     */
    int dataCount(String createUser);

    /**
     * 选择插入
     *
     * @param len
     * @return
     */
    boolean insertSelective(LenCreateUserInfo len);

    /**
     * 选择修改
     * @param len
     * @return
     */
    boolean updateByPrimaryKeySelective(LenCreateUserInfo len);


}
