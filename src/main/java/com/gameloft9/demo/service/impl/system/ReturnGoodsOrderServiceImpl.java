package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.ReturnGoodsOrderMapper;
import com.gameloft9.demo.dataaccess.model.system.ReturnGoodsOrder;
import com.gameloft9.demo.dataaccess.model.system.ShipmentOrder;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import com.gameloft9.demo.service.api.system.ReturnGoodsOrderService;
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
public class ReturnGoodsOrderServiceImpl implements ReturnGoodsOrderService {

    @Autowired
    ReturnGoodsOrderMapper returnGoodsOrderMapper;


    /**
     * 分页查找
     * @param page
     * @param limit
     * @param goodsName
     * @return
     */
    @Override
    public List<ReturnGoodsOrder> findAll(String page, String limit, String goodsName) {
        PageRange pageRange = new PageRange(page,limit);
        return returnGoodsOrderMapper.findAll(pageRange.getStart(),pageRange.getEnd(),goodsName);
    }

    /**
     * 查找条数
     * @param goodsName
     * @return
     */
    @Override
    public int countGetAll(String goodsName) {
        return returnGoodsOrderMapper.countGetAll(goodsName);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public int deleteById(String id) {
        return returnGoodsOrderMapper.deleteById(id);
    }

    /**
     * 获取id
     * @param id
     * @return
     */
    @Override
    public ReturnGoodsOrder getById(String id) {
        return returnGoodsOrderMapper.getById(id);
    }

    /**
     * 修改
     * @param returnGoodsOrder
     * @return
     */
    @Override
    public Boolean update(ReturnGoodsOrder returnGoodsOrder) {
        return returnGoodsOrderMapper.update(returnGoodsOrder);
    }

    /**
     * 添加
     * @param returnGoodsOrder
     * @return
     */
    @Override
    public String add(ReturnGoodsOrder returnGoodsOrder) {
        TimeZone zone = TimeZone.getTimeZone("ETC/GMT-8");
        TimeZone.setDefault(zone);
        returnGoodsOrder.setId(UUIDUtil.getUUID());
        //设置固定格式生成订单编号
        returnGoodsOrder.setGoodsId("xs"+ OrderUtil.createOrderNumber());
        returnGoodsOrder.setApplyTime(new Date());
        returnGoodsOrderMapper.add(returnGoodsOrder);
        return returnGoodsOrder.getId();
    }
    /**
     * 提交
     * @param shipmentOrder
     * @return
     */
    @Override
    public Boolean audit(ShipmentOrder shipmentOrder) {
        CheckUtil.notBlank(shipmentOrder.getId(),"订单id为空");
        shipmentOrder.setState(StateUUtil.APPLY_pass);
        returnGoodsOrderMapper.audit(shipmentOrder);
        return true;
    }
    /**
     * 提交仓库
     * @param shipmentOrder
     * @return
     */
    @Override
    public Boolean depot(ShipmentOrder shipmentOrder) {
        CheckUtil.notBlank(shipmentOrder.getId(),"订单id为空");
        shipmentOrder.setState(StateUUtil.APPLY_depot);
        returnGoodsOrderMapper.depot(shipmentOrder);
        return true;
    }
}
