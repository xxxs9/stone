package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.PurchaseOrderMapper;
import com.gameloft9.demo.dataaccess.model.system.PurchaseOrder;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import com.gameloft9.demo.service.api.system.PurchaseOrderService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.StateUtil;
import com.gameloft9.demo.utils.UUIDUtil;
import org.apache.commons.fileupload.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
    public String insert(PurchaseOrder purchaseOrder) {
        //修改jvm时间
        TimeZone tz = TimeZone.getTimeZone("ETC/GMT-8");
        TimeZone.setDefault(tz);
        purchaseOrder.setId(UUIDUtil.getUUID());
        purchaseOrder.setApplyTime(new Date());
        dao.insert(purchaseOrder);
        return purchaseOrder.getId();
    }

    /**删除*/
    public boolean deleteByPrimaryKey(String id) {
        dao.deleteByPrimaryKey(id);
        return true;
    }

    /**修改*/
    public boolean updateByPrimaryKey(PurchaseOrder purchaseOrder) {
        CheckUtil.notBlank(purchaseOrder.getId(),"订单id为空");
        purchaseOrder.setApplyTime(new Date());
        dao.updateByPrimaryKey(purchaseOrder);
        return true;
    }

    /**根据state查看*/
    public PurchaseOrder lookSelect(String state){
        return dao.lookSelect(state);
    }

    /**查看*/
    public boolean lookUpdate(PurchaseOrder purchaseOrder){
        purchaseOrder.setOrderAuditTime(new Date());
        String state = purchaseOrder.getState();
        String str="审核未通过";
        if(str.equals(state)){
            purchaseOrder.setState(StateUtil.APPLY_NO_SUBMIT);
        }else{
            purchaseOrder.setState(StateUtil.APPLY_FAIL);
        }
        dao.lookUpdate(purchaseOrder);
        return true;
    }

    /**审核*/
    public boolean inspectUpdate(PurchaseOrder purchaseOrder){
        CheckUtil.notBlank(purchaseOrder.getId(),"订单id为空");
        purchaseOrder.setOrderAuditTime(new Date());
        String state = purchaseOrder.getState();
        String str="审核通过";
        if(str.equals(state)){
            purchaseOrder.setState(StateUtil.APPLY_PASS);
        }else{
            purchaseOrder.setState(StateUtil.APPLY_FAIL);
        }
        dao.inspectUpdate(purchaseOrder);
        return true;
    }

    /**获取所有*/
    public List<PurchaseOrder> selectAll(String page, String limit, String goodsId, String state) {
        PageRange pageRange = new PageRange(page, limit);
        return dao.selectAll(pageRange.getStart(),pageRange.getEnd(),goodsId,state);
    }

    /**根据id获取审核所需的状态*/
    public List<PurchaseOrder> selectAllByInspect(String page,String limit,String goodsId,String state){
        PageRange pageRange = new PageRange(page,limit);
        return dao.selectAllByInspect(pageRange.getStart(),pageRange.getEnd(),goodsId,state);
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

}