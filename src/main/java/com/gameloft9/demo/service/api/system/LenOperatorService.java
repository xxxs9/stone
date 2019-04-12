package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.LenOperator;

import java.util.List;

/**
 * @author : Lennon_Yuan
 * @time : 2019-04-11 - 22:26
 * @description :操作记录service
 */

public interface LenOperatorService {
    /**
     * 分页查询
     * @param page
     * @param limit
     * @param operatorType
     * @param operatorUser
     * @return
     */
    List<LenOperator> selectByPage(String page, String limit, String operatorType, String operatorUser);

    /**
     * 增加记录
     * @param lenOperator
     * @return
     */
    boolean insertSelective(LenOperator lenOperator);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    LenOperator getById(String id);

    /**
     * 数据统计
     * @return
     */
    int dataCount();

    /**
     * 插入记录
     * @param operatorUser
     * @param operatorType
     * @param operatorRemark
     * @param other1
     * @param other2
     * @param other3
     */
    void insertSelective1(String operatorUser,String operatorType,String operatorRemark,String other1,String other2 ,String other3);
}
