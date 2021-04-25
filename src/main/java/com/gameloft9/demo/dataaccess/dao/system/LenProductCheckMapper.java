package com.gameloft9.demo.dataaccess.dao.system;


import com.gameloft9.demo.dataaccess.model.system.LenProductCheck;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : Lennon_Yuan
 * @time : 2019-03-24 - 02:50
 * @description :
 */

public interface LenProductCheckMapper {

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
     * @param start
     * @param end
     * @param checkUser
     * @param state
     * @return
     */
    List<LenProductCheck> selectByPage(
            @Param("start") int start,
            @Param("end") int end,
            @Param("checkUser") String checkUser,
            @Param("state") String state
    );

    /**
     *
     * @param lenProductCheck
     * @return
     */
    int insert(LenProductCheck lenProductCheck);
//    int insert(String id,
//               String producePlanId,
//               String formulaReachId,
//               String state,
//               String checkUser,
//               String checkTime,
//               String wasteId,
//               String checkRemark);

    /**
     *
     * @param lenProductCheck
     * @return
     */
    int update(LenProductCheck lenProductCheck);
//    int update(String id,
//               String producePlanId,
//               String formulaReachId,
//               String state,
//               String checkUser,
//               String checkTime,
//               String wasteId,
//               String checkRemark);

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
    int changeState(@Param("state")String state,@Param("id")String id);

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
    int updateByPrimaryKeySelective(String id,
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
    int insertSelective(String id,
                        String producePlanId,
                        String formulaReachId,
                        String state,
                        String checkUser,
                        String checkTime,
                        String wasteId,
                        String checkRemark);

}
