package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.controllers.system.DepotInventoryCheckController;
import com.gameloft9.demo.controllers.system.DepotOrderController;
import com.gameloft9.demo.dataaccess.dao.system.FinanceApplyOrderMapper;
import com.gameloft9.demo.dataaccess.dao.system.MarkerOrderMapper;
import com.gameloft9.demo.dataaccess.model.system.DepotInventoryCheck;
import com.gameloft9.demo.dataaccess.model.system.MarkerOrderTest;
import com.gameloft9.demo.dataaccess.model.system.SysFinanceApplyOrder;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import com.gameloft9.demo.mgrframework.utils.StateUtil;
import com.gameloft9.demo.service.api.system.DepotOrderService;
import com.gameloft9.demo.service.api.system.MarkerOrderService;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
@Transactional
public class MarkerOrderServiceImpl implements MarkerOrderService {

    @Autowired
    MarkerOrderMapper markerOrderMapper;
    @Autowired
    DepotOrderService depotOrderServiceImpl;
    @Autowired
    FinanceApplyOrderMapper applyOrderMapper;

    /**
     * 获取所有订单信息
     * @param page
     * @param limit
     * @param productId
     * @return
     */
    @Override
    public List<MarkerOrderTest> findAll(String page, String limit, String productId,String applyUser) {
        PageRange pageRange = new PageRange(page,limit);
        return markerOrderMapper.findAll(pageRange.getStart(),pageRange.getEnd(),productId,applyUser);
    }

    /**
     * 获取所有订单个数
     * @param productId
     * @return
     */
    @Override
    public int countGetAll(String productId,String applyUser ) {
        return markerOrderMapper.countGetAll(productId,applyUser);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public int deleteById(String id) {
        return markerOrderMapper.deleteById(id);
    }

    /**
     * 修改
     * @param markerOrderTest
     * @return
     */
    @Override
    public Boolean update(MarkerOrderTest markerOrderTest) {
        return markerOrderMapper.update(markerOrderTest);
    }

    /**
     * 增加
     * @param markerOrderTest
     * @return
     */
    @Override
    public String add(MarkerOrderTest markerOrderTest) {
        //根据登入账号自动获取名字
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String username = (String) request.getSession().getAttribute("sysUser");
        //自动生成时间
        TimeZone zone = TimeZone.getTimeZone("ETC/GMT-8");
        TimeZone.setDefault(zone);
        markerOrderTest.setId(UUIDUtil.getUUID());
        markerOrderTest.setOrderTime(new Date());
        //设置固定格式生成订单编号
        markerOrderTest.setOrderId("xs"+OrderUtil.createOrderNumber());
        //根据登入账号名获取申请人名字
        markerOrderTest.setApplyUser(username);
        //设置审核人名字
        markerOrderTest.setOrderAuditUser("销售主管");
        markerOrderTest.setOrderAuditDepot("仓库主管");
        //设置状态
        markerOrderTest.setState("未提交");

        //BigDecimal计算总价格，保留小数，Scale()保留几位小数
        String deliverNumber = markerOrderTest.getDeliverNumber();
        String plannedNumber = markerOrderTest.getPlannedNumber();
        String acceptedAmount = markerOrderTest.getAcceptedAmount();
        BigDecimal aig = new BigDecimal(deliverNumber).setScale(2);
        BigDecimal big = new BigDecimal(plannedNumber).setScale(2);
        BigDecimal scale = aig.multiply(big).setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
        markerOrderTest.setAcceptedAmount(scale.toString());

        markerOrderMapper.add(markerOrderTest);
        return markerOrderTest.getId();
    }

    /**
     * 获取销售订单id
     * @param id
     * @return
     */
    @Override
    public MarkerOrderTest getMaker(String id) {
        return markerOrderMapper.getMaker(id);
    }

    /**
     * 提交
     * @param markerOrderTest
     * @return
     */
    @Override
    public Boolean audiUpdate(MarkerOrderTest markerOrderTest) {

        CheckUtil.notBlank(markerOrderTest.getId(),"订单id为空");
        markerOrderTest.setState(StateUtil.APPLY_WAITING);
        markerOrderMapper.audiUpdate(markerOrderTest);
        return true;
    }

    /**
     * 撤回
     * @param markerOrderTest
     * @return
     */
    @Override
    public Boolean backUpdate(MarkerOrderTest markerOrderTest) {
        CheckUtil.notBlank(markerOrderTest.getId(),"订单id为空");
        markerOrderTest.setState(StateUUtil.APPLY_back);
        markerOrderMapper.backUpdate(markerOrderTest);
        return true;
    }




    /**
     *获取productid下拉框
     * @return
     */
    @Override
    public List<MarkerOrderTest> getProductId() {
        List<MarkerOrderTest> list = new ArrayList<MarkerOrderTest>();
        list=markerOrderMapper.getProductId();
        return list;
    }

    /**
     * 提交仓库审核
     * @param markerOrderTest
     * @return
     */
    @Override
    public Boolean submit(MarkerOrderTest markerOrderTest) {
        CheckUtil.notBlank(markerOrderTest.getId(),"订单id为空");
        markerOrderTest.setState(StateUUtil.APPLY_submit);
        markerOrderMapper.submit(markerOrderTest);
        MarkerOrderTest markerOrder = markerOrderMapper.getMaker(markerOrderTest.getId());
        depotOrderServiceImpl.addMarketDepotOrderOut(markerOrder.getOrderId(),markerOrder.getProductId(),markerOrder.getDeliverNumber(),markerOrder.getApplyUser());
        return true;
    }

    /**
     * 提交财务
     * @param markerOrderTest
     * @return
     */
    @Override
    public Boolean fina(MarkerOrderTest markerOrderTest) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        CheckUtil.notBlank(markerOrderTest.getId(),"订单id为空");
        markerOrderTest.setState(StateUUtil.APPLY_finacc);
        markerOrderMapper.submit(markerOrderTest);
        SysFinanceApplyOrder applyOrder = new SysFinanceApplyOrder();
        applyOrder.setId("CWI" + OrderUtil.createOrderNumber());
        applyOrder.setApplyTime(new Date());
        String auditUser = (String) request.getSession().getAttribute("sysUser");
        applyOrder.setApplyUser(auditUser);
        applyOrder.setApplyState(Constants.Finance.APPLY_ORDER_UNCOMMIT);
        applyOrder.setApplyType(Constants.Finance.SALE_RECEIVABLE);
        //订单id
        applyOrder.setApplyId(markerOrderTest.getOrderId());
        //订单总价
        applyOrder.setApplyMoney(markerOrderTest.getAcceptedAmount());
        //插入申请单
        applyOrderMapper.add(applyOrder);
        return true;
    }

    /**
     * 啊发包
     * @param orderId orderNumber
     * @return
     */
    @Override
    public MarkerOrderTest findMarkerOrderByOrderNumber(String orderId) {
        return markerOrderMapper.findMarkerOrderByOrderNumber(orderId);
    }


}
