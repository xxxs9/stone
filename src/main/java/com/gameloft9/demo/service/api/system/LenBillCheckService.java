package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.LenBillCheck;

import java.util.List;

/**
 * @author : Lennon_Yuan
 * @time : 2019-03-29 - 16:42
 * @description :
 */

public interface LenBillCheckService {

    /**
     * 查找所有（无条件）
     *
     * @return
     */
    List<LenBillCheck> selectAll();

    /**
     * 按照主键查找所有
     *
     * @param id
     * @return
     */
    LenBillCheck getByPrimaryKey(String id);

    /**
     * 分页查找
     *
     * @param page
     * @param limit
     * @param checkUser
     * @param state
     * @return
     */
    List<LenBillCheck> selectByPage(
            String page, String limit,
            String checkUser,
            String state
    );

    /**
     *
     * @param id
     * @param state
     * @param checkNumber
     * @param checkDate
     * @param checkUser
     * @param checkRemark
     * @param reachId
     * @param productId
     * @param planId
     * @return
     */
    boolean insert( String id,
                    String productName ,
                    String state,
                    String checkNumber,
                    String checkDate,
                    String checkUser,
                    String checkRemark,
                    String reachId,
                    String productId,
                    String planId
    );

    /**
     * 只插入到billCheck表中
     * @param id
     * @param productName
     * @param state
     * @param checkNumber
     * @param checkDate
     * @param checkUser
     * @param checkRemark
     * @param reachId
     * @param productId
     * @param planId
     * @return
     */
    boolean finalInsert( String id,
                    String productName ,
                    String state,
                    String checkNumber,
                    String checkDate,
                    String checkUser,
                    String checkRemark,
                    String reachId,
                    String productId,
                    String planId
    );

    /**
     *
     * @param id
     * @param state
     * @param checkNumber
     * @param checkDate
     * @param checkUser
     * @param checkRemark
     * @param reachId
     * @param productId
     * @param planId
     * @return
     */
    boolean update( String id,
                    String productName ,
                    String state,
                    String checkNumber,
                    String checkDate,
                    String checkUser,
                    String checkRemark,
                    String reachId,
                    String productId,
                    String planId
    );

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
     * @param
     * @return
     */
    int dataCount(String state);

    /**
     * 更改state状态码
     *
     * @param state
     * @param id
     * @return
     */
    boolean changeState(String state, String id);

    /**
     * 状态筛选
     * @return
     */
    List<LenBillCheck> selectByState();

    /**
     * 主键筛选
     * @param productId
     * @return
     */
    LenBillCheck selectByProductId(String productId);
}
