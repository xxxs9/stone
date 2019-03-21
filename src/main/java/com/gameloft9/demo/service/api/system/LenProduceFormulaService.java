package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.LenProduceFormula;
import org.apache.ibatis.annotations.Param;

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
            String productId,
            String createUserId
    );

    /**
     *增加
     *
     * @param lenProduceFormula
     * @return
     */
    boolean insert(LenProduceFormula lenProduceFormula);

    /**
     * 修改
     *
     * @param lenProduceFormula
     * @return
     */
    boolean update(LenProduceFormula lenProduceFormula);

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
