package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.ReturnGoodsAudit;
import com.gameloft9.demo.dataaccess.model.system.ShipmentOrder;

import java.util.List;

public interface ReturnGoodsAuditService {
    /**
     * 获取所有销售订单信息
     * @param page
     * @param limit
     * @param goodsName
     * @return
     */

    List<ReturnGoodsAudit> findAll(String page, String limit, String goodsName);

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
    ReturnGoodsAudit getById(String id);

    /**
     * 修改销售订单表信息
     * @param returnGoodsAudit
     * @return
     */
    Boolean update(ReturnGoodsAudit returnGoodsAudit);

    /**
     * 增加销售订单表信息
     */
    int add(ReturnGoodsAudit returnGoodsAudit);

    /**
     * 财务查收
     * @param shipmentOrder
     * @return
     */
    Boolean accept(ShipmentOrder shipmentOrder);
}
