package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.FinanceApplyOrderMapper;
import com.gameloft9.demo.dataaccess.dao.system.PurchaseOrderMapper;
import com.gameloft9.demo.dataaccess.dao.system.PurchaseReturnMapper;
import com.gameloft9.demo.dataaccess.model.system.PurchaseOrder;
import com.gameloft9.demo.dataaccess.model.system.PurchaseReturn;
import com.gameloft9.demo.dataaccess.model.system.SysFinanceApplyOrder;
import com.gameloft9.demo.mgrframework.beans.response.AbstractResult;
import com.gameloft9.demo.mgrframework.exceptions.BizException;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import com.gameloft9.demo.service.api.system.DepotOrderService;
import com.gameloft9.demo.service.api.system.PurchaseReturnService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * PurchaseReturn serviceImpl接口
 * @author OliverCH
 * @date 2019/03/24
 */
@Service
public class PurchaseReturnServiceImpl implements PurchaseReturnService {

    @Autowired
    PurchaseReturnMapper dao;
    @Autowired
    FinanceApplyOrderMapper applyOrderMapper;
    @Autowired
    PurchaseOrderMapper purOrder;
    @Autowired
    DepotOrderService depotOrderServiceImpl ;


    /**查询所有*/
    public List<PurchaseReturn> selectAll(String page, String limit, String goodsNume, String depotState) {
        PageRange pageRange = new PageRange(page, limit);
        return dao.selectAll(pageRange.getStart(),pageRange.getEnd(),goodsNume,depotState);
    }

    /**分页查找*/
    public int countGetAll(String goodsName, String depotState) {
        return dao.countGetAll(goodsName,depotState);
    }

    /**增加*/
    public String insert(PurchaseReturn purchaseReturn) {
        //根据登录账号的名字自动获取
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userName = (String) request.getSession().getAttribute("sysUser");
        purchaseReturn.setApplyUser(userName);
        purchaseReturn.setId(UUIDUtil.getUUID());

        purchaseReturn.setApplyTime(new Date());
        purchaseReturn.setDepotState(Constants.PurchaseState.APPLY_NO_SUBMIT);
        dao.insert(purchaseReturn);
        return purchaseReturn.getId();
    }

    /**删除*/
    public boolean deleteByPrimaryKey(String id) {
        dao.deleteByPrimaryKey(id);
        return true;
    }

    /**根据id获取*/
    public PurchaseReturn selectByPrimaryKey(String id) {
        CheckUtil.notBlank(id,"订单id为空");
        return dao.selectByPrimaryKey(id);
    }

    /**修改*/
    public boolean update(PurchaseReturn purchaseReturn) {
        CheckUtil.notBlank(purchaseReturn.getId(),"订单id为空");
        //purchaseReturn.setApplyTime(new Date());
        dao.update(purchaseReturn);
        return true;
    }

    /**获取goodsNume下拉框*/
    public List<PurchaseReturn> selectAllGoodsId() {
        List<PurchaseReturn> list = new ArrayList<PurchaseReturn>();
        list = dao.selectAllGoodsId();
        return list;
    }

    /**获取orderNumber下拉框*/
    public List<PurchaseReturn> selectAllOrderNumber() {
        List<PurchaseReturn> list = new ArrayList<PurchaseReturn>();
        list = dao.selectAllOrderNumber();
        return list;
    }

    /**根据orderNumber自动获取信息*/
    public PurchaseReturn selectOtherByOrderNumber(String orderNumber){
        return dao.selectOtherByOrderNumber(orderNumber);
    }

    /**采购退货 撤回*/
    public boolean backReUpdate(PurchaseReturn purchaseReturn){
        CheckUtil.notBlank(purchaseReturn.getId(),"订单id为空");
        //添加一个判断，如果仓库已经审核，理应不可撤回。
        String str = purchaseReturn.getDepotState();
        String state = "审核通过";
        if(str.equals(state)){
            throw new BizException(AbstractResult.CHECK_FAIL,"已审核无法撤回,请刷新!");
        }
        purchaseReturn.setDepotState(Constants.PurchaseState.APPLY_NO_SUBMIT);
        dao.updateTools(purchaseReturn);
        return true;
    }

    /**采购退货 提交*/
    public boolean commitReUpdate(PurchaseReturn purchaseReturn){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        CheckUtil.notBlank(purchaseReturn.getId(),"订单id为空");
        purchaseReturn.setDepotState(Constants.PurchaseState.APPLY_WAITING);
        purchaseReturn.setAuditType(Constants.Finance.PURCHASE_RECEIVABLE);
        dao.updateTools(purchaseReturn);
        String p1 = dao.selectByPrimaryKey(purchaseReturn.getId()).getOrderNumber();
        String p2 = dao.selectByPrimaryKey(purchaseReturn.getId()).getGoodsId();
        String p3 = dao.selectByPrimaryKey(purchaseReturn.getId()).getGoodsNumber();
        String p4 = dao.selectByPrimaryKey(purchaseReturn.getId()).getApplyUser();
        depotOrderServiceImpl.addPurorderDepotOrderOut(p1,p2,p3,p4);
        return true;
    }

    /**
     * 采购入库 华锋确认审核
     * */
    public boolean depotState(String orderNumber){
        CheckUtil.notBlank(orderNumber,"订单编号为空");
        PurchaseReturn purchaseReturn = dao.selectByOrderNumber(orderNumber);
        //添加判断，假如在仓库审核前，采购退货撤回了，理应不可以审核
        String str = purchaseReturn.getDepotState();
        String state = "提交审核中";
        if(!str.equals(state)){
            throw new BizException(AbstractResult.CHECK_FAIL,"退货单已撤回,请刷新!");
        }
        purchaseReturn.setDepotState(Constants.DepotState.DEPOT_PASS);
        dao.updateTools(purchaseReturn);
        return true;
    }

    @Override
    public PurchaseReturn selectByOrderNumber(String orderNumber) {
        return dao.selectByOrderNumber(orderNumber);
    }



}
