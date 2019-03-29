package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.LenProducePlan;

import java.util.List;

/**
 * @author : Lennon_Yuan
 * @time : 2019-03-23 - 14:05
 * @description :
 */

public interface LenProducePlanService {

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
     * @param page
     * @param limit
     * @param productId
     * @param state
     * @return
     */
    List<LenProducePlan> selectByPage(
            String page, String limit,
            String productId,
           String state
    );

    /**
     * 增加
     *
     * @param productId
     * @param planNumber
     * @param realNumber
     * @param state
     * @param goodNumber
     * @param produceDate
     * @param finishDate
     * @param billDate
     * @param billCycle
     * @param planRemark
     * @param other1
     * @param other2
     * @param other3
     * @return
     */
    boolean insert(String productId,String planNumber,
               String realNumber,
               String state,
               String goodNumber,
               String produceDate,
               String finishDate,
               String billDate,
               String billCycle,
               String planRemark,
               String other1,
               String other2,
               String other3
    );

    /**
     * 修改
     *
     * @param productId
     * @param planNumber
     * @param realNumber
     * @param state
     * @param goodNumber
     * @param produceDate
     * @param finishDate
     * @param billDate
     * @param billCycle
     * @param planRemark
     * @param other1
     * @param other2
     * @param other3
     * @return
     */
    boolean update(String id,String productId,String planNumber,
               String realNumber,
               String state,
               String goodNumber,
               String produceDate,
               String finishDate,
               String billDate,
               String billCycle,
               String planRemark,
               String other1,
               String other2,
               String other3
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
     * @return
     */
    boolean changeState(String state);

    /**
     * 选择修改
     * @param productId
     * @param planNumber
     * @param realNumber
     * @param state
     * @param goodNumber
     * @param produceDate
     * @param finishDate
     * @param billDate
     * @param billCycle
     * @param planRemark
     * @param other1
     * @param other2
     * @param other3
     * @return
     */
    boolean updateByPrimaryKeySelective(String productId,String planNumber,
                                    String realNumber,
                                    String state,
                                    String goodNumber,
                                    String produceDate,
                                    String finishDate,
                                    String billDate,
                                    String billCycle,
                                    String planRemark,
                                    String other1,
                                    String other2,
                                    String other3
    );

    /**
     * 选择增加
     *
     * @param productId
     * @param planNumber
     * @param realNumber
     * @param state
     * @param goodNumber
     * @param produceDate
     * @param finishDate
     * @param billDate
     * @param billCycle
     * @param planRemark
     * @param other1
     * @param other2
     * @param other3
     * @return
     */
    boolean insertSelective(String productId,String planNumber,
                        String realNumber,
                        String state,
                        String goodNumber,
                        String produceDate,
                        String finishDate,
                        String billDate,
                        String billCycle,
                        String planRemark,
                        String other1,
                        String other2,
                        String other3
    );

    boolean changeOther(String id);
}
