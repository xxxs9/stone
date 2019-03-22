package com.gameloft9.demo.service.api.system;


import com.gameloft9.demo.dataaccess.model.system.ShipmentOrder;



import java.util.List;

public interface ShipmentOrderService {

    /**
     * 分页获取所有发货单信息
     * @param page
     * @param limit
     * @param shipmentId
     * @return
     */
    List<ShipmentOrder> findAll(String page, String limit, String shipmentId);

    /**
     * 获取所有发货单单个数
     * @param shipmentId
     * @return
     */
    int countGetAll(String shipmentId);

    /**
     * 通过ID删除发货单信息
     * @param id
     * @return
     */
    int deleteById(String id);

    /**
     * 获取发货单id
     * @param id
     * @return
     */
    ShipmentOrder  getById(String id);

    /**
     * 修改发货单单信息
     * @param shipmentOrder
     * @return
     */
    Boolean update(ShipmentOrder shipmentOrder);


    /**
     * 增加发货单信息
     * @param shipmentOrder
     * @return
     */
    String add(ShipmentOrder shipmentOrder);


}
