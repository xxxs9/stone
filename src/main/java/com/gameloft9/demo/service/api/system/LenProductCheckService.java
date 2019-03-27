package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.LenProductCheck;

import java.util.List;

/**
 * @author : Lennon_Yuan
 * @time : 2019-03-24 - 14:18
 * @description :
 */

public interface LenProductCheckService {

    /**
     *查找所有（无条件）
     *
     * @return
     *
     */
    List<LenProductCheck> selectAll();

    /**
     * 按照主键查找所有
     *
     * @param id
     * @return
     */
    LenProductCheck getByPrimaryKey(String id);

    /**
     * 分页查找
     *
     * @param page
     * @param limit
     * @param checkUser
     * @param state
     * @return
     */
    List<LenProductCheck> selectByPage(
           String page,String limit, String checkUser, String state
    );

    /**
     * 增加
     *
     * @param id
     * @param producePlanId
     * @param formulaReachId
     * @param state
     * @param checkUser
     * @param checkTime
     * @param wasteId
     * @param checkRemark
     * @return
     */
    boolean insert(String id,
               String producePlanId,
               String formulaReachId,
               String state,
               String checkUser,
               String checkTime,
               String wasteId,
               String checkRemark);

    /**
     *
     * 修改
     *
     * @param id
     * @param producePlanId
     * @param formulaReachId
     * @param state
     * @param checkUser
     * @param checkTime
     * @param wasteId
     * @param checkRemark
     * @return
     */
    boolean update(String id,
               String producePlanId,
               String formulaReachId,
               String state,
               String checkUser,
               String checkTime,
               String wasteId,
               String checkRemark);

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
     * @return
     */
    boolean changeState(String id);

    /**
     * 验收单作废
     * @param id
     * @return
     */
    boolean unUse(String id);

    /**
     * 选择修改
     *
     * @param id
     * @param producePlanId
     * @param formulaReachId
     * @param state
     * @param checkUser
     * @param checkTime
     * @param wasteId
     * @param checkRemark
     * @return
     */
    boolean updateByPrimaryKeySelective(String id,
                                    String producePlanId,
                                    String formulaReachId,
                                    String state,
                                    String checkUser,
                                    String checkTime,
                                    String wasteId,
                                    String checkRemark);

    /**
     * 选择插入
     *
     * @param id
     * @param producePlanId
     * @param formulaReachId
     * @param state
     * @param checkUser
     * @param checkTime
     * @param wasteId
     * @param checkRemark
     * @return
     */
    boolean insertSelective(String id,
                        String producePlanId,
                        String formulaReachId,
                        String state,
                        String checkUser,
                        String checkTime,
                        String wasteId,
                        String checkRemark);
}
