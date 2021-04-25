package com.gameloft9.demo.dataaccess.dao.system;


import com.gameloft9.demo.dataaccess.model.system.LenOperator;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: Lennon_Yuan
 * @time: 2019/3/18 0018 - 下午 3:10
 * @description:
 */
public interface LenOperatorMapper {
    /**
     * 分页查询
     * @param start
     * @param end
     * @param operatorType
     * @return
     */
    List<LenOperator> selectByPage(@Param("start") int start, @Param("end") int end, @Param("operatorType") String operatorType,@Param("operatorUser")String operatorUser);

    /**
     * 增加记录
     * @param lenOperator
     * @return
     */
    int insertSelective(LenOperator lenOperator);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    LenOperator getById(@Param("id")String id);

    /**
     * 数据统计
     * @return
     */
    int dataCount();
}
