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
     * 通过formulaId获取实体列表
     * @param formulaId
     * @return
     */
    List<LenProduceFormulaDetail> getByFormulaId(@Param("formulaId") String formulaId);

    /**
     * 分页查找
     *
     * @param start
     * @param end
     * @param materialId 原料ID
     * @param depotId 仓库ID
     * @return
     */
    List<LenProduceFormulaDetail> selectByPage(
            @Param("start") int start,
            @Param("end") int end,
            @Param("materialId") String materialId,
            @Param("depotId") String depotId
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
     * @param depotId 仓库ID
     * @return
     */
    int dataCount(String depotId);

    /**
     * 选择插入
     *
     * @param produceFormulaDetail
     * @return
     */
    int insertSelective(LenProduceFormulaDetail produceFormulaDetail);

    /**
     * 选择修改
     *
     * @param lenProduceFormulaDetail
     * @return
     */
    int updateByPrimaryKeySelective(LenProduceFormulaDetail lenProduceFormulaDetail);



}
