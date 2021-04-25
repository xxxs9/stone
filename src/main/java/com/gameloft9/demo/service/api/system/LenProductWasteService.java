package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.LenProductWaste;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @packageName: com.gameloft9.demo.service.api.system
 * @author: Lennon_Yuan
 * @time: 2019/3/19 0019 - 下午 4:49
 * @description:
 */

public interface LenProductWasteService {
    /**
     *查找所有（无条件）
     *
     * @return
     *
     */
    List<LenProductWaste> selectAll();

    /**
     * 按照主键查找所有
     *
     * @param id
     * @return
     */
    LenProductWaste getByPrimaryKey(String id);

    /**
     * 分页查找
     *
     * @param page
     * @param limit
     * @param state
     * @return
     */
    List<LenProductWaste> selectByPage(
             String page,
             String limit,
             String state
    );

    /**
     * 增加
     *
     * @param wasteNumber
     * @param produceFormulaId
     * @param wasteTime
     * @param wasteRemark
     * @param state
     * @return
     */
    boolean insert(String wasteNumber,String produceFormulaId,String wasteTime,String wasteRemark,String state);

    /**
     * 修改
     *
     * @param lenProduct
     * @return
     */
    boolean update(LenProductWaste lenProduct);

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
     * @param state
     * @return
     */
    int dataCount(String state);

    /**
     * 更改state状态码
     *
     * @return
     */
    boolean changeState();

    /**
     * 选择修改
     *
     * @param lenProduct
     * @return
     */
    boolean updateByPrimaryKeySelective(LenProductWaste lenProduct);

    /**
     * 选择插入
     *
     * @param lenProduct
     * @return
     */
    boolean insertSelective(LenProductWaste lenProduct);

}
