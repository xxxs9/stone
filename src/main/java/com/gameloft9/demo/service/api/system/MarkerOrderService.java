package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.MarkerOrderTest;
import com.gameloft9.demo.dataaccess.model.system.OrderAuditBean;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface MarkerOrderService {

    /**
     * 获取所有销售订单信息
     * @param page
     * @param limit
     * @param productId
     * @return
     */
    List<MarkerOrderTest> findAll(String page, String limit, String productId,String applyUser);

    /**
     * 获取所有销售订单个数
     * @param productId
     * @param applyUser
     * @return
     */
    int countGetAll(String productId,String applyUser);

    /**
     * 通过ID删除销售订单信息
     * @param id
     * @return
     */
    int deleteById(String id);

    /**
     * 修改销售订单表信息
     * @param markerOrderTest
     * @return
     */
    Boolean update(MarkerOrderTest markerOrderTest);

    /**
     * 增加销售订单表信息
     */
    String add(MarkerOrderTest markerOrderTest);

    /**
     * 获取销售订单id
     * @param id
     * @return
     */
    MarkerOrderTest getMaker(String id);

    /**
     * 获取所有销售订单列表信息
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
     * 获取productID下来框
     * @return
     */

    List<MarkerOrderTest> getProductId();

    /**
     * 提交仓库审核
     * @param markerOrderTest
     * @return
     */

    Boolean submit(MarkerOrderTest markerOrderTest);

    /**
     * 提交财务
     * @param markerOrderTest
     * @return
     */

    Boolean fina(MarkerOrderTest markerOrderTest);

    /**
     * 啊发包
     * 根据orderNumber
     * @param orderId orderNumber
     * @return
     *      MarkerOrderTest
     */
    MarkerOrderTest findMarkerOrderByOrderNumber(@Param("orderNumber") String orderId);
}
