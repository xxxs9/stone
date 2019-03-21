package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.LenProduceFormulaDetail;

import java.util.List;

/**
 * @packageName: com.gameloft9.demo.service.api.system
 * @author: Lennon_Yuan
 * @time: 2019/3/20 0020 - 上午 10:46
 * @description:
 */
public interface LenProduceFormulaDetailService {
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
            String page,
            String limit,
            String materialId,
           String depotId
    );

    /**
     *增加
     *
     * @param lenProduceFormulaDetail
     * @return
     */
    boolean insert(LenProduceFormulaDetail lenProduceFormulaDetail);

    /**
     * 修改
     *
     * @param lenProduceFormulaDetail
     * @return
     */
    boolean update(LenProduceFormulaDetail lenProduceFormulaDetail);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    boolean delete(String id);

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
    boolean insertSelective(LenProduceFormulaDetail produceFormulaDetail);

    /**
     * 选择修改
     *
     * @param lenProduceFormulaDetail
     * @return
     */
    boolean updateByPrimaryKeySelective(LenProduceFormulaDetail lenProduceFormulaDetail);
}
