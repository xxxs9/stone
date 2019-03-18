package com.gameloft9.demo.dataaccess.dao.system;


import com.gameloft9.demo.dataaccess.model.system.LenProduceFormulaDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @packageName: com.gameloft9.demo.dataaccess.dao.system
 * @author: Lennon_Yuan
 * @time: 2019/3/18 0018 - 下午 3:10
 * @description:
 */
public interface LenProduceFormulaDetailMapper {
    /**
     *查找所有（无条件）
     *
     * @return
     *
     */
    List<LenProduceFormulaDetail> selectAll();

    /**
     * 按照主键查找所有
     *
     * @param id
     * @return
     */
    LenProduceFormulaDetail getByPrimaryKey(String id);

    /**
     * 分页查找
     *
     * @param page
     * @param limit
     * @param materialId
     * @param depotId
     * @return
     */
    List<LenProduceFormulaDetail> selectByPage(
            @Param("page") String page,
            @Param("limit") String limit,
            String materialId,
            String depotId
    );

    /**
     *增加
     *
     * @param lenProduceFormulaDetail
     * @return
     */
    int insert(LenProduceFormulaDetail lenProduceFormulaDetail);

    /**
     * 修改
     *
     * @param lenProduceFormulaDetail
     * @return
     */
    int update(LenProduceFormulaDetail lenProduceFormulaDetail);

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
