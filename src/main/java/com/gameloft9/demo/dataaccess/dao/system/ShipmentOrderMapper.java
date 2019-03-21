package com.gameloft9.demo.dataaccess.dao.system;


import com.gameloft9.demo.dataaccess.model.system.ShipmentOrder;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface ShipmentOrderMapper {

    /**
     * 分页获取所有发货单信息
     * @param start 开始
     * @param end 结束
     *
     */
    List<ShipmentOrder> findAll(
            @Param("start") int start,
            @Param("end") int end,
            @Param("shipmentId") String shipmentId);

    /**
     * 获取所有发货单单个数
     * */
    int countGetAll( @Param("shipmentId") String shipmentId);

    /**
     * 通过ID删除发货单信息
     */
    int deleteById(String id);

    /**
     * 获取发货单id
     */
    ShipmentOrder  getById(String id);

    /**
     * 修改发货单单信息
     */
    Boolean update(ShipmentOrder shipmentOrder);


    /**
     * 增加发货单信息
     */
    String add(ShipmentOrder shipmentOrder);








}
