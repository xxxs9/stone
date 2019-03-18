package com.gameloft9.demo.dataaccess.dao.system;



import com.gameloft9.demo.dataaccess.model.system.LenProductWaste;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @packageName: com.gameloft9.demo.dataaccess.dao.system
 * @author: Lennon_Yuan
 * @time: 2019/3/18 0018 - 下午 3:10
 * @description:
 */
public interface LenProductWasteMapper {
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
            @Param("page") String page,
            @Param("limit") String limit,
            @Param("state") String state
    );

    /**
     *增加
     *
     * @param lenProductWaste
     * @return
     */
    int insert(LenProductWaste lenProductWaste);

    /**
     * 修改
     *
     * @param lenProductWaste
     * @return
     */
    int update(LenProductWaste lenProductWaste);

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
    int dataCount(@Param("state") String state);

    /**
     * 更改state状态码
     *
     * @return
     */
    int changeState();

    /**
     *选择更新
     *
     * @param lenProductWaste
     * @return
     */
    int updateByPrimaryKeySelective(LenProductWaste lenProductWaste);

    /**
     * 选择插入
     *
     * @param lenProductWaste
     * @return
     */
    int insertSelective(LenProductWaste lenProductWaste);

}
