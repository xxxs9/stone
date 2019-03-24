package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.MarkerOrderTest;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface MarkerOrderMapper {

    /**
     * 获取所有销售订单信息
     * @param start
     * @param end
     * @param productId
     * @return
     */
    List<MarkerOrderTest> findAll(
            @Param("start") int start,
            @Param("end") int end,
            @Param("productId") String productId);

    /**
     * 获取所有销售订单个数
     * @param productId
     * @return
     */
    int countGetAll( @Param("productId") String productId);

    /**
     * 通过ID删除销售订单信息
     * @param id
     * @return
     */
    int deleteById(String id);

    /**
     * 修改销售订单信息
     * @param markerOrderTest
     * @return
     */
    Boolean update(MarkerOrderTest markerOrderTest);


    /**
     * 增加销售订单信息
     * @param markerOrderTest
     * @return
     */
    int add(MarkerOrderTest markerOrderTest);


    /**
     * 获取销售订单id
     * @param id
     * @return
     */
    MarkerOrderTest getMaker(String id);


    /**
     * 获取所有销售订单类表信息
     */
   // List<MarkerOrderTest> selectAll();

    /**
     * 提交audi
     * @param markerOrderTest
     * @return
     */

    Boolean audiUpdate(MarkerOrderTest markerOrderTest);

    /**
     * 撤回back
     * @param markerOrderTest
     * @return
     */
    Boolean backUpdate(MarkerOrderTest markerOrderTest);


    /**
     * 自动生成订单编号
     * @param markerOrderTest
     * @return
     */
    String orderNum(MarkerOrderTest markerOrderTest);

    /**
     * 获取下拉框
     * @return
     */
    List<MarkerOrderTest> getProductId();
}
