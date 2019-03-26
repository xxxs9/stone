package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.ShipmentOrderMapper;
import com.gameloft9.demo.dataaccess.model.system.ReturnGoodsOrder;
import com.gameloft9.demo.dataaccess.model.system.ShipmentOrder;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import com.gameloft9.demo.mgrframework.utils.StateUtil;
import com.gameloft9.demo.service.api.system.ShipmentOrderService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.OrderUtil;
import com.gameloft9.demo.utils.StateUUtil;
import com.gameloft9.demo.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
@Transactional(rollbackFor = Exception.class)

public class ShipmentOrderServiceImpl implements ShipmentOrderService {

    @Autowired
    ShipmentOrderMapper shipmentOrderMapper;


    /**
     * 分页查找
     * @param page
     * @param limit
     * @param goodsName
     * @return
     */
    @Override
    public List<ShipmentOrder> findAll(String page, String limit, String goodsName) {
        PageRange pageRange = new PageRange(page,limit);
        return shipmentOrderMapper.findAll(pageRange.getStart(),pageRange.getEnd(),goodsName);
    }

    /**
     * 查找条数
     * @param goodsName
     * @return
     */
    @Override
    public int countGetAll(String goodsName) {
        return shipmentOrderMapper.countGetAll(goodsName);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public int deleteById(String id) {
        return shipmentOrderMapper.deleteById(id);
    }

    /**
     * 获取id
     * @param id
     * @return
     */
    @Override
    public ShipmentOrder getById(String id) {
        return shipmentOrderMapper.getById(id);
    }

    /**
     * 修改
     * @param shipmentOrder
     * @return
     */
    @Override
    public Boolean update(ShipmentOrder shipmentOrder) {
        return shipmentOrderMapper.update(shipmentOrder);
    }

    /**
     * 添加
     * @param shipmentOrder
     * @return
     */
    @Override
    public String add(ShipmentOrder shipmentOrder) {
        TimeZone zone = TimeZone.getTimeZone("ETC/GMT-8");
        TimeZone.setDefault(zone);
        shipmentOrder.setId(UUIDUtil.getUUID());
        //设置固定格式生成订单编号
        shipmentOrder.setGoodsId("xs"+ OrderUtil.createOrderNumber());
        shipmentOrder.setApplyTime(new Date());
        shipmentOrderMapper.add(shipmentOrder);
        return shipmentOrder.getId();
    }

    /**
     * 确认收货
     * @param shipmentOrder
     * @return
     */
    @Override
    public Boolean confirm(ShipmentOrder shipmentOrder) {
        CheckUtil.notBlank(shipmentOrder.getId(),"订单id为空");
        shipmentOrder.setState(StateUUtil.APPLY_confirm);
        shipmentOrderMapper.confirm(shipmentOrder);
        return true;
    }
    /**
     * 退货
     * @param shipmentOrder
     * @return
     */
    @Override
    public Boolean back(ShipmentOrder shipmentOrder) {
        CheckUtil.notBlank(shipmentOrder.getId(),"订单id为空");
        shipmentOrder.setState(StateUUtil.APPLY_end);
        shipmentOrderMapper.back(shipmentOrder);
        return true;
    }
}
