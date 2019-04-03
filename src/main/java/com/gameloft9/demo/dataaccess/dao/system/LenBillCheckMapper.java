package com.gameloft9.demo.dataaccess.dao.system;


import com.gameloft9.demo.dataaccess.model.system.LenBillCheck;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : Lennon_Yuan
 * @time : 2019-03-29 - 16:37
 * @description :
 */

public interface LenBillCheckMapper {
    /**
     *查找所有（无条件）
     *
     * @return
     *
     */
    List<LenBillCheck> selectAll();

    /**
     * 按照主键查找所有
     *
     * @param id
     * @return
     */
    LenBillCheck getByPrimaryKey(@Param("id") String id);

    /**
     * 分页查找
     *
     * @param start
     * @param end
     * @param checkUser
     * @param state
     * @return
     */
    List<LenBillCheck> selectByPage(
            @Param("start") int start,
            @Param("end") int end,
            @Param("checkUser") String checkUser,
            @Param("state") String state
    );

    /**
     *增加
     *
     * @param lenProduct
     * @return
     */
    int insert(LenBillCheck lenProduct);

    /**
     * 修改
     *
     * @param lenProduct
     * @return
     */
    int update(LenBillCheck lenProduct);

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
     * @param
     * @return
     */
    int dataCount(@Param("state") String state);

    /**
     * 更改state状态码
     *
     * @param state
     * @param id
     * @return
     */
    int changeState(@Param("state")String state,@Param("id")String id);

    /**
     * 选择修改
     *
     * @param lenProduct
     * @return
     */
    int updateByPrimaryKeySelective(LenBillCheck lenProduct);

    /**
     * 选择插入
     *
     * @param lenProduct
     * @return
     */
    int insertSelective(LenBillCheck lenProduct);

    /**
     * 查找已审核的产品
     *
     * @return List
     */
    List<LenBillCheck> selectByState();

    /**
     * 通过product主键查找billCheck实体
     * @param productId
     * @return
     */
    LenBillCheck selectByProductId(@Param("productId")String productId);
}
