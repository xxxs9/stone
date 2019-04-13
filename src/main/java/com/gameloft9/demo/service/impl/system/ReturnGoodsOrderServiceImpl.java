package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.FinanceApplyOrderMapper;
import com.gameloft9.demo.dataaccess.dao.system.ReturnGoodsOrderMapper;
import com.gameloft9.demo.dataaccess.model.system.ReturnGoodsOrder;
import com.gameloft9.demo.dataaccess.model.system.ShipmentOrder;
import com.gameloft9.demo.dataaccess.model.system.SysFinanceApplyOrder;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import com.gameloft9.demo.service.api.system.DepotOrderService;
import com.gameloft9.demo.service.api.system.ReturnGoodsOrderService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.Constants;
import com.gameloft9.demo.utils.OrderUtil;
import com.gameloft9.demo.utils.StateUUtil;
import com.gameloft9.demo.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
@Transactional(rollbackFor = Exception.class)
public class ReturnGoodsOrderServiceImpl implements ReturnGoodsOrderService {

    @Autowired
    ReturnGoodsOrderMapper returnGoodsOrderMapper;
    @Autowired
    FinanceApplyOrderMapper applyOrderMapper;
    @Autowired
    DepotOrderService depotOrderService;



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
    public ShipmentOrder getById(String id) {
        return returnGoodsOrderMapper.getById(id);
    }

    /**
     * 修改
     * @param shipmentOrder
     * @return
     */
    @Override
    public Boolean update(ShipmentOrder shipmentOrder) {
        return returnGoodsOrderMapper.update(shipmentOrder);
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
        returnGoodsOrder.setGoodsId("TF"+ OrderUtil.createOrderNumber());
        returnGoodsOrder.setApplyTime(new Date());
        returnGoodsOrderMapper.add(returnGoodsOrder);
        return returnGoodsOrder.getId();
    }
    /**
     * 提交主管审核
     * @param shipmentOrder
     * @return
     */
    @Override
    public Boolean audit(ShipmentOrder shipmentOrder) {
        HttpServletRequest request1 = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String username = (String) request1.getSession().getAttribute("sysUser");
        CheckUtil.notBlank(shipmentOrder.getId(),"订单id为空");
        shipmentOrder.setState(StateUUtil.APPLY_director);
        shipmentOrder.setApplyUser(username);
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
        ShipmentOrder returnGoods = returnGoodsOrderMapper.getById(shipmentOrder.getId());
        depotOrderService.addMarketDepotOrderIn(returnGoods.getGoodsId(),returnGoods.getProductId(),returnGoods.getGoodsNumber(),returnGoods.getApplyUser());
        return true;
    }

    /**
     * 提交财务
     * @param shipmentOrder
     * @return
     */
    @Override
    public Boolean finance(ShipmentOrder shipmentOrder) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        CheckUtil.notBlank(shipmentOrder.getId(),"订单id为空");
        shipmentOrder.setState(StateUUtil.APPLY_finance);
        returnGoodsOrderMapper.finance(shipmentOrder);
        SysFinanceApplyOrder applyOrder = new SysFinanceApplyOrder();
        applyOrder.setId(UUIDUtil.getUUID());
        applyOrder.setApplyTime(new Date());
        String auditUser = (String) request.getSession().getAttribute("sysUser");
        applyOrder.setApplyUser(auditUser);
        applyOrder.setApplyState(Constants.Finance.APPLY_ORDER_UNCOMMIT);
        applyOrder.setApplyType(Constants.Finance.SALE_PAYABLE);
        //订单id
        applyOrder.setApplyId(shipmentOrder.getId());
        //订单总价
        applyOrder.setApplyMoney(shipmentOrder.getGoodsAmount());
        //插入申请单
        applyOrderMapper.add(applyOrder);
        return true;
    }

    /**
     * 退货产品入库
     * @param shipmentOrder
     * @return
     */
    @Override
    public Boolean wareh(ShipmentOrder shipmentOrder) {
        CheckUtil.notBlank(shipmentOrder.getGoodsId(),"订单编号为空");
        shipmentOrder.setState(StateUUtil.APPLY_pas);
        returnGoodsOrderMapper.wareh(shipmentOrder);
        return true;
    }
}
