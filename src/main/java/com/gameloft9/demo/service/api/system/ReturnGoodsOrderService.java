package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.ReturnGoodsOrder;
import com.gameloft9.demo.dataaccess.model.system.ShipmentOrder;

import java.util.List;

public interface ReturnGoodsOrderService {

    /**
     * 获取所有销售订单信息
     * @param page
     * @param limit
     * @param goodsName
     * @return
     */
    List<ReturnGoodsOrder> findAll(String page, String limit, String goodsName);

    /**
     * 获取所有销售订单个数
     * @param goodsName
     * @return
     */
    int countGetAll(String goodsName);

    /**
     * 通过ID删除销售订单信息
     * @param id
     * @return
     */
    int deleteById(String id);

    /**
     * 获取销售订单id
     * @param id
     * @return
     */
    ReturnGoodsOrder getById(String id);

    /**
     * 修改销售订单表信息
     * @param returnGoodsOrder
     * @return
     */
    Boolean update(ReturnGoodsOrder returnGoodsOrder);

    /**
     * 增加销售订单表信息
     */
    String add(ReturnGoodsOrder returnGoodsOrder);

    /**
     * 提交
     * @param shipmentOrder
     * @return
     */
    Boolean audit(ShipmentOrder shipmentOrder);

    /**
     * 提交仓库
     * @param shipmentOrder
     * @return
     */
    Boolean depot(ShipmentOrder shipmentOrder);

    /**
     * 提交财务
     * @param shipmentOrder
     * @return
     */
    Boolean finance(ShipmentOrder shipmentOrder);
}
