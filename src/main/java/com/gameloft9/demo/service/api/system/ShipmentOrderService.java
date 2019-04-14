package com.gameloft9.demo.service.api.system;


import com.gameloft9.demo.dataaccess.model.system.ReturnGoodsOrder;
import com.gameloft9.demo.dataaccess.model.system.ShipmentOrder;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface ShipmentOrderService {

    /**
     * 获取所有销售订单信息
     * @param page
     * @param limit
     * @param goodsName
     * @return
     */

    List<ShipmentOrder> findAll(String page, String limit, String goodsName);

    /**
     * 获取所有订单个数
     * @param goodsName
     * @return
     */
    int countGetAll(String goodsName);

    /**
     * 通过ID删除订单信息
     * @param id
     * @return
     */
    int deleteById(String id);

    /**
     * 获取订单id
     * @param id
     * @return
     */
    ShipmentOrder getById(String id);

    /**
     * 修改订单表信息
     * @param shipmentOrder
     * @return
     */
    Boolean update(ShipmentOrder shipmentOrder);

    /**
     * 增加订单表信息
     */
    String add(ShipmentOrder shipmentOrder);

    /**
     * 确认收货
     * @param shipmentOrder
     * @return
     */
    Boolean confirm(ShipmentOrder shipmentOrder);
    /**
     * 退货
     * @param shipmentOrder
     * @return
     */
    Boolean back(ShipmentOrder shipmentOrder);

    /**
     * 提交仓库发货
     * @param shipmentOrder
     * @return
     */
    Boolean goods(ShipmentOrder shipmentOrder);

    /**
     * 提交财务
     * @param shipmentOrder
     * @return
     */
    Boolean sub(ShipmentOrder shipmentOrder);

    /**
     * 啊发包
     * 根据orderNumber
     * @param goodsId orderNumber
     * @return
     *      MarkerOrderTest
     */
    ShipmentOrder findShipmentOrderByOrderNumber(@Param("goodsId") String goodsId);
}
