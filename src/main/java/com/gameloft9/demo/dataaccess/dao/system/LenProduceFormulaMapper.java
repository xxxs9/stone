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
     * @param page
     * @param limit
     * @param productId
     * @param createUserId
     * @return
     */
    List<LenProduceFormula> selectByPage(
            @Param("page") String page,
            @Param("limit") String limit,
            String productId,
            String createUserId
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
     * 查询条数
     *
     * @return
     */
    int dataCount();

}
