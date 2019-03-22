package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.MarkerOrderTest;

import java.util.Date;
import java.util.List;

public interface MarkerOrderService {

    /**
     * 获取所有销售订单信息
     */
    List<MarkerOrderTest> findAll(String page, String limit, String productId);

    /**
     * 获取所有销售订单个数
     * */
    int countGetAll(String productId);

    /**
     * 通过ID删除销售订单信息
     */
    int deleteById(String id);

    /**
     * 修改销售订单表信息
     */
    Boolean update(MarkerOrderTest markerOrderTest);

    /**
     * 增加销售订单表信息
     */
    int add(MarkerOrderTest markerOrderTest);

    /**
     * 获取销售订单id
     */
    MarkerOrderTest getMaker(String id);

    /**
     * 获取所有销售订单列表信息
     */
   // List<MarkerOrderTest> selectAll();

    /**
     * 提交audi
     */

    Boolean audiUpdate(MarkerOrderTest markerOrderTest);

    /**
     * 撤回back
     */
    Boolean backUpdate(MarkerOrderTest markerOrderTest);

    /**
     * 自动生成订单编号
     */
    String orderNum(MarkerOrderTest markerOrderTest);

}
