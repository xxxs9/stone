package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.FinanceApplyOrderMapper;
import com.gameloft9.demo.dataaccess.dao.system.PurchaseReturnMapper;
import com.gameloft9.demo.dataaccess.model.system.PurchaseReturn;
import com.gameloft9.demo.dataaccess.model.system.SysFinanceApplyOrder;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
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

    /**查询所有*/
    public List<PurchaseReturn> selectAll(String page, String limit, String goodsId, String depotState) {
        PageRange pageRange = new PageRange(page, limit);
        return dao.selectAll(pageRange.getStart(),pageRange.getEnd(),goodsId,depotState);
    }

    /**分页查找*/
    public int countGetAll(String goodsId, String depotState) {
        return dao.countGetAll(goodsId,depotState);
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

    /**获取goodsId下拉框*/
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
        purchaseReturn.setDepotState(Constants.PurchaseState.APPLY_NO_SUBMIT);
        dao.updateTools(purchaseReturn);
        return true;
    }

    /**采购退货 提交*/
    public boolean commitReUpdate(PurchaseReturn purchaseReturn){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        CheckUtil.notBlank(purchaseReturn.getId(),"订单id为空");
        purchaseReturn.setDepotState(Constants.DepotState.DEPOT_WAITING_IN);
        /*String state = purchaseReturn.getDepotState();
        //按固定格式生成订单编号
        String str="审核通过";
        if(str.equals(state)){
            purchaseReturn.setDepotState(Constants.PurchaseState.APPLY_PASS);
            //插入订单
            SysFinanceApplyOrder financeApplyOrder = new SysFinanceApplyOrder();
            financeApplyOrder.setId(UUIDUtil.getUUID());
            financeApplyOrder.setApplyId(purchaseReturn.getId());
            int price = NumberUtil.strToInt(purchaseReturn.getPrice());
            int goodsNumber = NumberUtil.strToInt(purchaseReturn.getGoodsNumber());
            int applyMoney = price * goodsNumber;
            financeApplyOrder.setApplyMoney(applyMoney+"");
            financeApplyOrder.setApplyType(1);
            financeApplyOrder.setApplyState(1);
            financeApplyOrder.setApplyTime(new Date());
            String applyUser = (String) request.getSession().getAttribute("sysUser");
            financeApplyOrder.setApplyUser(applyUser);
            applyOrderMapper.add(financeApplyOrder);
        }else{
            purchaseReturn.setDepotState(Constants.PurchaseState.APPLY_FAIL);
        }*/

        dao.updateTools(purchaseReturn);
        return true;
    }
}
