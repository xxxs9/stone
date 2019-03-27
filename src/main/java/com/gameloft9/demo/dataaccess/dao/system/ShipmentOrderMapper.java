package com.gameloft9.demo.dataaccess.dao.system;


import com.gameloft9.demo.dataaccess.model.system.PurchaseOrder;
import com.gameloft9.demo.dataaccess.model.system.ReturnGoodsOrder;
import com.gameloft9.demo.dataaccess.model.system.ShipmentOrder;
import org.apache.ibatis.annotations.Param;


import java.util.List;


public interface ShipmentOrderMapper {

    /**
     * 获取所有退货单信息
     * @param start
     * @param end
     * @param goodsName
     * @return
     */
    List<ShipmentOrder> findAll(
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
    ShipmentOrder getById(String id);

    /**
     * 修改销售订单信息
     * @param shipmentOrder
     * @return
     */
    Boolean update(ShipmentOrder shipmentOrder);


    /**
     * 增加销售订单信息
     * @param shipmentOrder
     * @return
     */
    int add(ShipmentOrder shipmentOrder);

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
     * 啊发包
     * 根据id和财务审核类型更新 shipmentOrder
     * @param shipmentOrder shipmentOrder
     */
    void updateByIdAndState(ShipmentOrder shipmentOrder);

    /**
     * 啊发包
     * 根据id和订单类型
     * @param id id
     * @param auditType 订单类型
     * @return
     *      purchaseOrder
     */
    ShipmentOrder findByIdAndAuditType(@Param("id") String id,@Param("auditType") Integer auditType);

    /**
     * 啊发包
     * 更新 returnGoodsOrder 的财务审核状态
     * @param shipmentOrder
     */
    void shipmentOrderPayPass(ShipmentOrder shipmentOrder);
}
