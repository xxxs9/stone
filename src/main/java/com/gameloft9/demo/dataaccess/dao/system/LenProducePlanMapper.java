package com.gameloft9.demo.dataaccess.dao.system;


import com.gameloft9.demo.dataaccess.model.system.LenProducePlan;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @packageName: com.gameloft9.demo.dataaccess.dao.system
 * @author: Lennon_Yuan
 * @time: 2019/3/18 0018 - 下午 3:10
 * @description:
 */
public interface LenProducePlanMapper {
    /**
     *查找所有（无条件）
     *
     * @return
     *
     */
    List<LenProducePlan> selectAll();

    /**
     * 按照主键查找所有
     *
     * @param id
     * @return
     */
    LenProducePlan getByPrimaryKey(String id);

    /**
     * 分页查找
     *
     * @param start
     * @param end
     * @param productId
     * @param state
     * @return
     */
    List<LenProducePlan> selectByPage(
            @Param("start") int start,
            @Param("end") int end,
            @Param("productId") String productId,
            @Param("state") String state
    );

    /**
     *增加
     *
     * @param
     * @return
     */
    int insert(LenProducePlan lenProducePlan);

    /**
     * 修改
     *
     * @param
     * @return
     */
    int update(LenProducePlan lenProducePlan);

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
     * @return
     */
    int changeState(String state,String id);

    int changeOther1(@Param("other1") String other1,@Param("id")String id);

    /**
     * 选择修改
     *
     * @param
     * @return
     */
    int updateByPrimaryKeySelective(LenProducePlan lenProducePlan);

    /**
     * 选择插入
     *
     * @param
     * @return
     */
    int insertSelective(LenProducePlan lenProducePlan);

    /**
     * 查询id
     * @param productId
     * @return
     */
    LenProducePlan findId(@Param("productId") String productId);

}
