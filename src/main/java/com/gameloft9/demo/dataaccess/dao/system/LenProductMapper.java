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
    LenProduct getByPrimaryKey(@Param("id") String id);

    /**
     * 分页查找
     *
     * @param start
     * @param end
     * @param productName
     * @param productState
     * @return
     */
    List<LenProduct> selectByPage(
            @Param("start") int start,
            @Param("end") int end,
            @Param("productName") String productName,
            @Param("productState") String productState
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
     * 统计条数
     *
     * @param state
     * @return
     */
    int dataCount(@Param("productState") String productState);

    /**
     * 更改state状态码
     *
     * @param productState
     * @param id
     * @return
     */
    int changeState(@Param("productState")String productState,@Param("id")String id);

    /**
     * 选择修改
     *
     * @param lenProduct
     * @return
     */
    int updateByPrimaryKeySelective(LenProduct lenProduct);

    /**
     * 选择插入
     *
     * @param lenProduct
     * @return
     */
    int insertSelective(LenProduct lenProduct);


    /**
     * 获取产品id
     * */
    List<String> getProductId();
    /**
     * 查找已审核的产品
     *
     * @return List
     */
    List<LenProduct> selectByState();

}
