package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.LenProduceFormula;

import java.util.List;

/**
 * @packageName: com.gameloft9.demo.service.api.system
 * @author: Lennon_Yuan
 * @time: 2019/3/20 0020 - 上午 10:25
 * @description:
 */

public interface LenProduceFormulaService {
    /**
     *查找所有（无条件）
     *
     * @return
     *
     */
    List<LenProduceFormula> selectAll();

    /**
     * 按照主键查找所有
     *
     * @param id
     * @return
     */
    LenProduceFormula getByPrimaryKey(String id);

    /**
     * 分页查找
     *

     * @return
     */
    List<LenProduceFormula> selectByPage(
            String page,String limit,

            String createUser,
             String productId
    );

    /**
     * 增加
     *
     * @param productId
     * @param formulaType
     * @param formulaNumber
     * @param createUser
     * @param createTime
     * @return
     */
    boolean insert(String productId,String formulaType ,String formulaNumber,String createUser,String createTime,String other3);

    /**
     * 增加
     *
     * @param productId
     * @param formulaType
     * @param formulaNumber
     * @param createUser
     * @param createTime
     * @return
     */
    boolean update(String productId,String formulaType ,String formulaNumber,String createUser,String createTime);

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
     * @param productId
     * @return
     */
    int dataCount(String productId);

    /**
     * 选择修改
     * @return
     */
    boolean updateByPrimaryKeySelective(LenProduceFormula lenProduceFormula);

    /**
     * 选择插入
     *
     * @return
     */
    boolean insertSelective(LenProduceFormula lenProduceFormula);
}
