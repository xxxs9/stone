package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.ShipmentOrderMapper;
import com.gameloft9.demo.dataaccess.model.system.ShipmentOrder;
import com.gameloft9.demo.service.api.system.ShipmentOrderService;
import com.gameloft9.demo.service.beans.system.PageRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(rollbackFor = Exception.class)

public class ShipmentOrderServiceImpl implements ShipmentOrderService {

    @Autowired
    ShipmentOrderMapper shipmentOrderMapper;

    /**
     * 分页模糊查找
     * @param page
     * @param limit
     * @param shipmentId
     * @return
     */
    public List<ShipmentOrder> findAll(String page, String limit, String shipmentId) {

        PageRange pageRange = new PageRange(page, limit);

        return shipmentOrderMapper.findAll(pageRange.getStart(),pageRange.getEnd(),shipmentId);
    }

    /**
     * 查找条数
     * @param shipmentId
     * @return
     */
    public int countGetAll(String shipmentId) {
        return shipmentOrderMapper.countGetAll(shipmentId);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int deleteById(String id) {
        return shipmentOrderMapper.deleteById(id);
    }

    /**
     * 获取id
     * @param id
     * @return
     */
    public ShipmentOrder getById(String id) {
        return shipmentOrderMapper.getById(id);
    }

    /**
     * 修改
     * @param shipmentOrder
     * @return
     */
    public Boolean update(ShipmentOrder shipmentOrder) {
        return shipmentOrderMapper.update(shipmentOrder);
    }

    /**
     * 增加
     * @param shipmentOrder
     * @return
     */
    public String add(ShipmentOrder shipmentOrder) {
        return shipmentOrderMapper.add(shipmentOrder);
    }
}
