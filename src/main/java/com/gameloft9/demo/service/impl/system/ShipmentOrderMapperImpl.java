package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.ShipmentOrderMapper;
import com.gameloft9.demo.dataaccess.model.system.ShipmentOrder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ShipmentOrderMapperImpl implements ShipmentOrderMapper {


    /**
     * 分页发货单
     * @param start 开始
     * @param end 结束
     * @param shipmentId
     * @return
     */
    @Override
    public List<ShipmentOrder> findAll(int start, int end, String shipmentId) {
        return null;
    }
    /**
     * 获取所有发货单单个数
     * */
    @Override
    public int countGetAll(String shipmentId) {
        return 0;
    }
    /**
     * 通过ID删除发货单信息
     */
    @Override
    public int deleteById(String id) {
        return 0;
    }
    /**
     * 获取发货单id
     */
    @Override
    public ShipmentOrder getById(String id) {
        return null;
    }
    /**
     * 修改发货单单信息
     */
    @Override
    public Boolean update(ShipmentOrder shipmentOrder) {
        return null;
    }
    /**
     * 增加发货单信息
     */
    @Override
    public String add(ShipmentOrder shipmentOrder) {
        return null;
    }
}
