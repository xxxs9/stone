package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.LenProduceFormula;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @packageName: com.gameloft9.demo.dataaccess.dao.system
 * @author: Lennon_Yuan
 * @time: 2019/3/18 0018 - 下午 3:10
 * @description:
 */
public interface LenProduceFormulaMapper {
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
     * @param start
     * @param end
     * @param productId
     * @param createUserId
     * @return
     */
    List<LenProduceFormula> selectByPage(
            @Param("start") int start,
            @Param("end") int end,
            @Param("productId") String productId,
            @Param("createUserId") String createUserId
    );

    /**
     *增加
     *
     * @param lenProduceFormula
     * @return
     */
    int insert(LenProduceFormula lenProduceFormula);

    /**
     * 修改
     *
     * @param lenProduceFormula
     * @return
     */
    int update(LenProduceFormula lenProduceFormula);

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
     * @param productId
     * @return
     */
    int dataCount(String productId);

    /**
     * 选择修改
     * @return
     */
    int updateByPrimaryKeySelective(LenProduceFormula lenProduceFormula);

    /**
     * 选择插入
     *
     * @return
     */
    int insertSelective(LenProduceFormula lenProduceFormula);
}
