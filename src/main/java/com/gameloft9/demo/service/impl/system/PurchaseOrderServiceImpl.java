package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.FinanceApplyOrderMapper;
import com.gameloft9.demo.dataaccess.dao.system.PurchaseOrderMapper;
import com.gameloft9.demo.dataaccess.model.system.PurchaseOrder;
import com.gameloft9.demo.dataaccess.model.system.SysFinanceApplyOrder;
import com.gameloft9.demo.mgrframework.beans.response.AbstractResult;
import com.gameloft9.demo.mgrframework.exceptions.BizException;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import com.gameloft9.demo.service.api.system.PurchaseOrderService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    FinanceApplyOrderMapper applyOrderMapper;

    /**根据id获取*/
    public PurchaseOrder selectByPrimaryKey(String id) {
        CheckUtil.notBlank(id,"订单id为空");
        return dao.selectByPrimaryKey(id);
    }

    /**增加*/
    public String insert(PurchaseOrder purchaseOrder) {
        purchaseOrder.setId(UUIDUtil.getUUID());
        //根据登录账号的名字自动获取
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userName = (String) request.getSession().getAttribute("sysUser");
        //申请人名称
        purchaseOrder.setApplyUser(userName);
        //按固定格式生成订单编号
        purchaseOrder.setOrderNumber("CG" + OrderUtil.createOrderNumber());
        purchaseOrder.setApplyTime(new Date());
        purchaseOrder.setAuditType(Constants.Finance.PURCHASE_PAYABLE);

        //BigDecimal计算总价格，保留两位小数,Scale保留几位小数
        String price = purchaseOrder.getPrice();
        String goodsNumber = purchaseOrder.getGoodsNumber();
        BigDecimal aBD = new BigDecimal(price).setScale(2);
        BigDecimal bBD = new BigDecimal(goodsNumber).setScale(2);
        BigDecimal resultBD = aBD.multiply(bBD).setScale(2,
                java.math.BigDecimal.ROUND_HALF_UP);
        purchaseOrder.setTotalPrice(resultBD.toString());

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
        //BigDecimal计算总价格，保留两位小数,Scale保留几位小数
        String price = purchaseOrder.getPrice();
        String goodsNumber = purchaseOrder.getGoodsNumber();
        BigDecimal aBD = new BigDecimal(price).setScale(2);
        BigDecimal bBD = new BigDecimal(goodsNumber).setScale(2);
        BigDecimal resultBD = aBD.multiply(bBD).setScale(2,
                java.math.BigDecimal.ROUND_HALF_UP);
        purchaseOrder.setTotalPrice(resultBD.toString());
        //对单价进行判断，价格不能为零或负数，字符串类型需要用长度来判断(length)
        int r = resultBD.compareTo(BigDecimal.ZERO);
        if(r == 0){
            throw new BizException(AbstractResult.CHECK_FAIL,"单价不能零！");
        }else if(r == -1){
            throw new BizException(AbstractResult.CHECK_FAIL,"单价不能为负数！");
        }else if(resultBD.toString() == null){
            throw new BizException(AbstractResult.CHECK_FAIL,"单价不能空！");
        }
        /*if("".equals(str) || str == null){
            throw new BizException(AbstractResult.CHECK_FAIL,"单价不能空！");
        }else if(Integer.parseInt(str) < 0){

        }else if(Integer.parseInt(str) == 0){
            throw new BizException(AbstractResult.CHECK_FAIL,"单价不能为零！");
        }*/
        dao.updateByPrimaryKey(purchaseOrder);
        return true;
    }

    /**根据id获取查看的行*/
    public PurchaseOrder lookSelect(String id){
        return dao.lookSelect(id);
    }

    /**查看完，点击确定*/
    public boolean lookUpdate(PurchaseOrder purchaseOrder){
        String state = purchaseOrder.getState();
        String str="审核未通过";
        if(str.equals(state)){
            purchaseOrder.setState(Constants.PurchaseState.APPLY_NO_SUBMIT);
        }else{
            purchaseOrder.setState(Constants.PurchaseState.APPLY_FAIL);
        }
        dao.lookUpdate(purchaseOrder);
        return true;
    }

    /**采购部门经理审核*/
    public boolean inspectUpdate(PurchaseOrder purchaseOrder){
        //审核前判断state是否为提交审核中（避免撤回后还能审核）
        String st = purchaseOrder.getState();
        String s = "提交审核中";
        if(st != s) {
            throw new BizException(AbstractResult.CHECK_FAIL,"订单被撤回，无法审核");
        }
        CheckUtil.notBlank(purchaseOrder.getId(),"订单id为空");
        purchaseOrder.setOrderAuditTime(new Date());
        /*purchaseOrder.setFinanceState(Constants.FinanceState.APPLY_PASS_WAIT);*/
        String state = purchaseOrder.getState();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //根据登录账号的名字自动获取
        String userName = (String) request.getSession().getAttribute("sysUser");
        //审核人名称
        purchaseOrder.setOrderAuditUser(userName);
        //按固定格式生成订单编号
        String str="审核通过";
        if(str.equals(state)){
            purchaseOrder.setState(Constants.PurchaseState.APPLY_PASS);
            //插入订单
            SysFinanceApplyOrder financeApplyOrder = new SysFinanceApplyOrder();
            financeApplyOrder.setId(UUIDUtil.getUUID());
            financeApplyOrder.setApplyId(purchaseOrder.getId());
            BigDecimal decimal1 = new BigDecimal(purchaseOrder.getGoodsNumber());
            decimal1 = decimal1.setScale(2,BigDecimal.ROUND_HALF_UP);
            BigDecimal decimal2 = new BigDecimal(purchaseOrder.getGoodsNumber());
            decimal2.setScale(0);
            BigDecimal applyMoney = decimal1.multiply(decimal2);
            financeApplyOrder.setApplyMoney(applyMoney.toString());
            financeApplyOrder.setApplyType(1);
            financeApplyOrder.setApplyState(1);
            financeApplyOrder.setApplyTime(new Date());
            String applyUser = (String) request.getSession().getAttribute("sysUser");
            financeApplyOrder.setApplyUser(applyUser);
            applyOrderMapper.add(financeApplyOrder);
        }else{
            purchaseOrder.setState(Constants.PurchaseState.APPLY_FAIL);
        }
        //BigDecimal计算总价格，保留两位小数,Scale保留几位小数
        String price01 = purchaseOrder.getPrice();
        String goodsNumber01 = purchaseOrder.getGoodsNumber();
        BigDecimal aBD = new BigDecimal(price01).setScale(2);
        BigDecimal bBD = new BigDecimal(goodsNumber01).setScale(2);
        BigDecimal resultBD = aBD.multiply(bBD).setScale(2,
                java.math.BigDecimal.ROUND_HALF_UP);
        purchaseOrder.setTotalPrice(resultBD.toString());
        dao.inspectUpdate(purchaseOrder);
        return true;
    }

    /**获取所有*/
    public List<PurchaseOrder> selectAll(String page, String limit, String goodsId, String state) {
        PageRange pageRange = new PageRange(page, limit);
        return dao.selectAll(pageRange.getStart(),pageRange.getEnd(),goodsId,state);
    }


    /**根据id获取审核所需的状态*/
    public List<PurchaseOrder> selectAllByInspect(String page,String limit,String goodsId,String state,String financeState){
        PageRange pageRange = new PageRange(page,limit);
        return dao.selectAllByInspect(pageRange.getStart(),pageRange.getEnd(),goodsId,state,financeState);
    }


    /**获取分页*/
    public int countGetAll(String goodsId, String state,String financeState) {
        return dao.countGetAll(goodsId,state,financeState);
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
        purchaseOrder.setState(Constants.PurchaseState.APPLY_WAITING);
        /*purchaseOrder.setAuditType(Constants.Finance.PURCHASE_PAYABLE);*/
        dao.commitUpdate(purchaseOrder);
        return true;
    }

    /**撤回*/
    public boolean recallUpdate(String  id) {
        CheckUtil.notBlank(id,"订单id为空");
        PurchaseOrder purchaseOrder = dao.selectByPrimaryKey(id);
        //Constants  APPLY_NO_SUBMIT定义'未提交'
        purchaseOrder.setState(Constants.PurchaseState.APPLY_NO_SUBMIT);
        dao.recallUpdate(purchaseOrder);
        return true;
    }

    /**
     * 采购收货 获取所有
     */
    public List<PurchaseOrder> selectAllByInOrder(String page,String limit,String goodsId,String depotState){
        PageRange pageRange = new PageRange(page,limit);
        return dao.selectAllByInOrder(pageRange.getStart(),pageRange.getEnd(),goodsId,depotState);
    }

    /**采购收货 获取分页*/
    public int countGetAllByInOrder(String goodsId, String depotState) {
        return dao.countGetAllByInOrder(goodsId,depotState);
    }

    /**采购入库之收货 bring*/
    public boolean bringInUpdate(PurchaseOrder purchaseOrder){
        CheckUtil.notBlank(purchaseOrder.getId(),"订单id为空");
        purchaseOrder.setDepotState(Constants.DepotState.DEPOT_SURE);
        dao.toolsUpdate(purchaseOrder);
        return true;
    }

    /**采购入库之确认 sure*/
    public boolean sureInUpdate(PurchaseOrder purchaseOrder){
        CheckUtil.notBlank(purchaseOrder.getId(),"订单id为空");
        purchaseOrder.setDepotState(Constants.DepotState.DEPOT_NO_SUNMIT);
        dao.toolsUpdate(purchaseOrder);
        return true;
    }

    /**采购入库之提交*/
    public boolean commitInUpdate(PurchaseOrder purchaseOrder){
        CheckUtil.notBlank(purchaseOrder.getId(),"订单id为空");
        purchaseOrder.setDepotState(Constants.DepotState.DEPOT_WAITING_IN);
        dao.toolsUpdate(purchaseOrder);
        return true;
    }

    /**采购入库之撤回*/
    public boolean backInUpdate(PurchaseOrder purchaseOrder){
        CheckUtil.notBlank(purchaseOrder.getId(),"订单id为空");
        purchaseOrder.setDepotState(Constants.DepotState.DEPOT_SURE);
        dao.toolsUpdate(purchaseOrder);
        return true;
    }

    /**采购入库之编辑*/
    public boolean InUpdate(PurchaseOrder purchaseOrder){
        CheckUtil.notBlank(purchaseOrder.getId(),"订单id为空");
        dao.InUpdate(purchaseOrder);
        return true;
    }

    /**采购入库之查看审核未通过原因*/
    public boolean lookIn(PurchaseOrder purchaseOrder){
        String depotState = purchaseOrder.getDepotState();
        String str ="审核未通过";
        if(str.equals(depotState)){
            purchaseOrder.setDepotState(Constants.DepotState.DEPOT_SURE);
        }else{
            purchaseOrder.setDepotState(Constants.DepotState.DEPOT_FAIL);
        }
        dao.lookIn(purchaseOrder);
        return true;
    }

    /**根据goodsId自动获取pruce信息*/
    public String selectPriceByGoodsId(String materialId){
        return dao.selectPriceByGoodsId(materialId);
    }

    /**根据state状态为审核通过，获取下拉框orderNumber内容*/
    public List<PurchaseOrder> selectAllByOrderNumber() {
        List<PurchaseOrder> list = new ArrayList<PurchaseOrder>();
        list = dao.selectAllByOrderNumber();
        return list;
    }

    /**查看审核通过的订单详情*/
    public boolean selectAllBySearch(PurchaseOrder purchaseOrder) {
        CheckUtil.notBlank(purchaseOrder.getId(),"订单id为空");
        dao.selectAllBySearch(purchaseOrder);
        return true;
    }

    /**根据id获取*/
    public PurchaseOrder selectByOrderNumber(String orderNumber) {
        return dao.selectByOrderNumber(orderNumber);
    }
}
