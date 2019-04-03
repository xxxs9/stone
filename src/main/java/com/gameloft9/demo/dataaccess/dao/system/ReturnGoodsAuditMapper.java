package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.ReturnGoodsAudit;
import com.gameloft9.demo.dataaccess.model.system.ShipmentOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReturnGoodsAuditMapper {
    /**
     * 获取所有退货单信息
     * @param start
     * @param end
     * @param goodsName
     * @return
     */
    List<ReturnGoodsAudit> findAll(
            @Param("start") int start,
            @Param("end") int end,
            @Param("goodsName") String goodsName);

    /**
     * 获取所有销售订单个数
     * @param goodsName
     * @return
     */
    int countGetAll( @Param("goodsName") String goodsName);

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
     * 修改销售订单信息
     * @param returnGoodsAudit
     * @return
     */
    Boolean update(ReturnGoodsAudit returnGoodsAudit);


    /**
     * 增加销售订单信息
     * @param returnGoodsAudit
     * @return
     */
    int add(ReturnGoodsAudit returnGoodsAudit);

    /**
     * 财务查收
     * @param shipmentOrder
     * @return
     */
    Boolean accept(ShipmentOrder shipmentOrder);
}