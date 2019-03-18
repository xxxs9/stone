package com.gameloft9.demo.dataaccess.dao.system;


import com.gameloft9.demo.dataaccess.model.system.LenProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @packageName: com.gameloft9.demo.dataaccess.dao.system
 * @author: Lennon_Yuan
 * @time: 2019/3/18 0018 - 下午 3:10
 * @description:
 */
public interface LenProductMapper {
    /**
     *查找所有（无条件）
     *
     * @return
     *
     */
    List<LenProduct> selectAll();

    /**
     * 按照主键查找所有
     *
     * @param id
     * @return
     */
    LenProduct getByPrimaryKey(String id);

    /**
     * 分页查找
     *
     * @param page
     * @param limit
     * @param productName
     * @param state
     * @return
     */
    List<LenProduct> selectByPage(
            @Param("page") String page,
            @Param("limit") String limit,
            String productName,
            String state
    );

    /**
     *增加
     *
     * @param lenProduct
     * @return
     */
    int insert(LenProduct lenProduct);

    /**
     * 修改
     *
     * @param lenProduct
     * @return
     */
    int update(LenProduct lenProduct);

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

    /**
     * 更改state状态码
     *
     * @return
     */
    int changeState();

}
