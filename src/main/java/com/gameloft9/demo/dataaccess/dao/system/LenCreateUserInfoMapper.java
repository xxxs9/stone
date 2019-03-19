package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.LenCreateUserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @packageName: com.gameloft9.demo.dataaccess.dao.system
 * @author: Lennon_Yuan
 * @time: 2019/3/18 0018 - 下午 3:10
 * @description:
 */
public interface LenCreateUserInfoMapper {
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
     * @param start
     * @param end
     * @param createUser
     * @param createTime
     * @return
     */
    List<LenCreateUserInfo> selectByPage(
            @Param("start") int start,
            @Param("end") int end,
            @Param("createUser") String createUser,
            @Param("createTime") String createTime
    );

    /**
     * 增加
     *
     * @param lenCreateUserInfo
     * @return
     */
    int insert(LenCreateUserInfo lenCreateUserInfo);

    /**
     * 修改
     *
     * @param lenCreateUserInfo
     * @return
     */
    int update(LenCreateUserInfo lenCreateUserInfo);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int delete(String id);

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
     * @param lenCreateUserInfo
     * @return
     */
    int insertSelective(LenCreateUserInfo lenCreateUserInfo);

    /**
     * 选择修改
     * @param lenCreateUserInfo
     * @return
     */
    int updateByPrimaryKeySelective(LenCreateUserInfo lenCreateUserInfo);


}
