package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.MarkerOrderTest;
import com.gameloft9.demo.dataaccess.model.system.PurchaseOrder;
import com.gameloft9.demo.dataaccess.model.system.ReturnGoodsOrder;
import com.gameloft9.demo.dataaccess.model.system.ShipmentOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReturnGoodsOrderMapper {

    /**
     * 获取所有退货单信息
     * @param start
     * @param end
     * @param goodsName
     * @return
     */
    List<ReturnGoodsOrder> findAll(
            @Param("start") int start,
            @Param("end") int end,
            @Param("goodsName") String goodsName);

    /**
     * 获取所有退货单个数
     * @param goodsName
     * @return
     */
    int countGetAll( @Param("goodsName") String goodsName);

    /**
     * 通过ID删除退货单信息
     * @param id
     * @return
     */
    int deleteById(String id);

    /**
     * 获取退货订单id
     * @param id
     * @return
     */
    ShipmentOrder getById(String id);

    /**
     * 修改退货单信息
     * @param shipmentOrder
     * @return
     */
    Boolean update(ShipmentOrder shipmentOrder);


    /**
     * 增加退货订单信息
     * @param returnGoodsOrder
     * @return
     */
    int add(ReturnGoodsOrder returnGoodsOrder);

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

    /**
     * 啊发包
     * 根据id和财务审核类型更新 returnGoodsOrder
     * @param returnGoodsOrder returnGoodsOrder
     */
    void updateByIdAndState(ReturnGoodsOrder returnGoodsOrder);

    /**
     * 啊发包
     * 根据id和订单类型
     * @param id id
     * @param auditType 订单类型
     * @return
     *      purchaseOrder
     */
    ReturnGoodsOrder findByIdAndAuditType(@Param("id") String id, @Param("auditType") Integer auditType);

    /**
     * 啊发包
     * 更新 returnGoodsOrder 的财务审核状态
     * @param returnGoodsOrder
     */
    void returnGoodsOrderPayPass(ReturnGoodsOrder returnGoodsOrder);


    /**
     * 退货产品入库
     * @param shipmentOrder
     * @return
     */
    Boolean wareh(ShipmentOrder shipmentOrder);

}
