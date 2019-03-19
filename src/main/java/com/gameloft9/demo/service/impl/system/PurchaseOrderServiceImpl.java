package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.PurchaseOrderMapper;
import com.gameloft9.demo.dataaccess.model.system.PurchaseOrder;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import com.gameloft9.demo.service.api.system.PurchaseOrderService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.StateUtil;
import org.apache.commons.fileupload.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * PurchaseOrder service
 * @author OliverCH
 * @date 2019/03/18
 */
@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    @Autowired
    PurchaseOrderMapper dao;

    /**根据id获取*/
    public PurchaseOrder selectByPrimaryKey(String id) {
        CheckUtil.notBlank(id,"角色id为空");
        return dao.selectByPrimaryKey(id);
    }

    /**增加*/
    public PurchaseOrder insert(PurchaseOrder purchaseOrder) {
        PurchaseOrder pur = new PurchaseOrder();
        dao.insert(pur);
        return pur;
    }

    /**删除*/
    public boolean deleteByPrimaryKey(String id) {
        dao.deleteByPrimaryKey(id);
        return true;
    }

    /**修改*/
    public boolean updateByPrimaryKey(PurchaseOrder purchaseOrder) {
        PurchaseOrder pur = new PurchaseOrder();
        dao.updateByPrimaryKey(pur);
        return true;
    }

    /**获取所有*/
    public List<PurchaseOrder> selectAll(String page, String limit, String goodsId, String state) {
        PageRange pageRange = new PageRange(page, limit);
        return dao.selectAll(pageRange.getStart(),pageRange.getEnd(),goodsId,state);
    }

    /**获取分页*/
    public int countGetAll(String goodsId, String state) {
        return dao.countGetAll(goodsId,state);
    }

    /**获取下拉框goodsId商品名称*/
    public List<PurchaseOrder> getSelectListGoods() {
        List<PurchaseOrder> list = new ArrayList<PurchaseOrder>();
        list = dao.getSelectListGoods();
        return list;
    }

    /**获取下拉框state订单状态*/
    public List<PurchaseOrder> getSelectListState() {
        List<PurchaseOrder> list = new ArrayList<PurchaseOrder>();
        list = dao.getSelectListState();
        return list;
    }

    /**提交*/
    public boolean commitUpdate(PurchaseOrder purchaseOrder) {
        CheckUtil.notBlank(purchaseOrder.getId(),"订单id为空");
        purchaseOrder.setState(StateUtil.APPLY_WAITING);
        dao.commitUpdate(purchaseOrder);
        return true;
    }

    /**撤回*/
    public boolean recallUpdate(String  id) {
        CheckUtil.notBlank(id,"订单id为空");
        PurchaseOrder purchaseOrder = dao.selectByPrimaryKey(id);
        //调用工具类StateUtil  APPLY_NO_SUBMIT定义'未提交'
        purchaseOrder.setState(StateUtil.APPLY_NO_SUBMIT);
        dao.recallUpdate(purchaseOrder);
        return true;
    }

    /**审核*/
    public boolean inspectUpdate(String id,String auditDescribe,String agree){
        CheckUtil.notBlank(id,"订单id为空");
        //获取订单信息
        PurchaseOrder purchaseOrder = dao.selectByPrimaryKey(id);
        //设置审核人orderAuditUser
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String orderAuditUser = (String) request.getSession().getAttribute("sysUser");
        purchaseOrder.setOrderAuditUser(orderAuditUser);
        //根据状态来判断
        //魔法值不允许中文直接放入，需要String
        String str="审核通过";
        if(str.equals(agree)){
            purchaseOrder.setState(StateUtil.APPLY_PASS);
        }else{
            purchaseOrder.setState(StateUtil.APPLY_FAIL);
        }
        dao.inspectUpdate(purchaseOrder);
        return true;
    }
}
