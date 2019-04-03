package com.gameloft9.demo.service.impl.system;

import com.alibaba.druid.sql.visitor.functions.If;
import com.gameloft9.demo.dataaccess.dao.system.*;
import com.gameloft9.demo.dataaccess.model.system.*;
import com.gameloft9.demo.mgrframework.annotation.BizOperLog;
import com.gameloft9.demo.mgrframework.beans.constant.OperType;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import com.gameloft9.demo.service.api.system.*;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.Constants;
import com.gameloft9.demo.utils.UUIDUtil;
import com.sun.xml.internal.bind.v2.model.core.ID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: Sxiu
 * @create: 2019/3/28 12:12
 * @description:
 */

@Slf4j
@Service
@Transactional
public class DepotOrderServiceImpl implements DepotOrderService {

    @Autowired
    private DepotOrderMapper depotOrderMapper;
    @Autowired
    private DepotInventoryService depotInventoryServiceImpl;
    @Autowired
    private SysMaterialGoodsMapper sysMaterialGoodsMapper;
    @Autowired
    private LenProductMapper lenProductMapper;
    @Autowired
    private OrderAuditService orderAuditServiceImpl;
    @Autowired
    private OrderAuditMapper orderAuditMapper;
    @Autowired
    private PurchaseOrderService purchaseOrderServiceImpl;

    /**
     * 获取仓库单列表
     * @param page                  页序
     * @param limit                 分页大小
     * @param orderType             仓库单类型
     * @param type                  出入库类型
     * @param goodsId               原料/成品ID
     * @param applyUser             申请人
     */
    @Override
    public List<DepotOrder> getAll(String page, String limit, String orderType, String type, String goodsId, String applyUser) {
        PageRange pageRange = new PageRange(page, limit);
        return depotOrderMapper.getAll(pageRange.getStart(),pageRange.getEnd(),orderType,type,goodsId,applyUser);
    }

    /**
     * 获取仓库单个数
     * @param orderType             仓库单类型
     * @param type                  出入库类型
     * @param goodsId               原料/成品ID
     * @param applyUser             申请人
     * */
    @Override
    public int countGetAll(String orderType, String type, String goodsId, String applyUser) {
        return depotOrderMapper.countGetAll(orderType,type,goodsId,applyUser);
    }

    /**
     * 添加仓库单
     * @param orderType             仓库单类型
     * @param type                  出入库类型
     * @param goodsId               原料/成品ID
     * @param goodsNumber           货品数量
     * @param applyUser             申请人
     * */
    @Override
    public String addDepotOrder(String orderType, String type, String goodsId, String goodsNumber, String applyUser) {

        CheckUtil.notBlank(orderType, "仓库单类型为空");
        CheckUtil.notBlank(type, "出入库类型为空");
        CheckUtil.notBlank(goodsId, "原料/成品ID为空");
        CheckUtil.notBlank(goodsNumber, "货品数量为空");
        CheckUtil.notBlank(applyUser, "申请人为空");

        DepotOrder depotOrder= new DepotOrder();
        depotOrder.setId(UUIDUtil.getUUID());
        depotOrder.setOrderType(orderType);
        depotOrder.setType(type);
        depotOrder.setGoodsId(goodsId);
        depotOrder.setGoodsNumber(goodsNumber);
        depotOrder.setApplyUser(applyUser);
        depotOrder.setApplyTime(new Date());
        if(orderType.equals(Constants.Depot.ORDER_IN)){
            depotOrder.setState(Constants.DepotState.DEPOT_WAITING_IN);
        }
        if(orderType.equals(Constants.Depot.ORDER_OUT)){
            depotOrder.setState(Constants.DepotState.DEPOT_WAITING_OUT);
        }

        depotOrderMapper.insertSelective(depotOrder);

        return depotOrder.getId();
    }


    /**
     * 添加采购入库单
     * @param orderNumber           订单编号
     * @param goodsId               原料/成品ID
     * @param goodsNumber           货品数量
     * @param applyUser             申请入
     * */
    @BizOperLog(operType = OperType.ADD,memo = "新增销售入库单")
    public String addPurorderDepotOrderIn(String orderNumber,String goodsId, String goodsNumber, String applyUser){

        CheckUtil.notBlank(orderNumber, "订单编号为空");
        CheckUtil.notBlank(goodsId, "原料/成品ID为空");
        CheckUtil.notBlank(goodsNumber, "货品数量为空");
        CheckUtil.notBlank(applyUser, "申请人为空");

        DepotOrder depotOrder= new DepotOrder();
        depotOrder.setId(orderNumber);
        depotOrder.setOrderType(Constants.Depot.ORDER_IN);
        depotOrder.setType("采购入库");
        depotOrder.setGoodsId(goodsId);
        depotOrder.setGoodsNumber(goodsNumber);
        depotOrder.setApplyUser(applyUser);
        depotOrder.setApplyTime(new Date());
        depotOrder.setState(Constants.DepotState.DEPOT_WAITING_IN);

        depotOrderMapper.insertSelective(depotOrder);

        return depotOrder.getId();
    }

    /**
     * 添加采购退货单
     * @param orderNumber           订单编号
     * @param goodsId               原料/成品ID
     * @param goodsNumber           货品数量
     * @param applyUser             申请入
     * */
    @BizOperLog(operType = OperType.UPDATE,memo = "新增采购退货单")
    public String addPurorderDepotOrderOut(String orderNumber,String goodsId, String goodsNumber,String applyUser){

        CheckUtil.notBlank(orderNumber, "订单编号为空");
        CheckUtil.notBlank(goodsId, "原料/成品ID为空");
        CheckUtil.notBlank(goodsNumber, "货品数量为空");
        CheckUtil.notBlank(applyUser, "申请人为空");

        DepotOrder depotOrder= depotOrderMapper.getById(orderNumber);
        depotOrder.setOrderType(Constants.Depot.ORDER_OUT);
        depotOrder.setType("采购退货");
        depotOrder.setGoodsId(goodsId);
        depotOrder.setGoodsNumber(goodsNumber);
        depotOrder.setApplyUser(applyUser);
        depotOrder.setApplyTime(new Date());
        depotOrder.setState(Constants.DepotState.DEPOT_WAITING_OUT);

        depotOrderMapper.updateByPrimaryKeySelective(depotOrder);

        return depotOrder.getId();
    }

    /**
     * 添加销售出库单
     * @param orderNumber           订单编号
     * @param goodsId               原料/成品ID
     * @param goodsNumber           货品数量
     * @param applyUser             申请入
     * */
    @BizOperLog(operType = OperType.ADD,memo = "新增销售出库单")
    public String addMarketDepotOrderOut(String orderNumber,String goodsId, String goodsNumber,String applyUser){

        CheckUtil.notBlank(orderNumber, "订单编号为空");
        CheckUtil.notBlank(goodsId, "原料/成品ID为空");
        CheckUtil.notBlank(goodsNumber, "货品数量为空");
        CheckUtil.notBlank(applyUser, "申请人为空");

        DepotOrder depotOrder= new DepotOrder();
        depotOrder.setId(orderNumber);
        depotOrder.setOrderType(Constants.Depot.ORDER_OUT);
        depotOrder.setType("销售出库");
        depotOrder.setGoodsId(goodsId);
        depotOrder.setGoodsNumber(goodsNumber);
        depotOrder.setApplyUser(applyUser);
        depotOrder.setApplyTime(new Date());
        depotOrder.setState(Constants.DepotState.DEPOT_WAITING_OUT);

        depotOrderMapper.insertSelective(depotOrder);

        return depotOrder.getId();
    }


    /**
     * 添加仓库单
     * @param id                    仓库单编号
     * @param orderType             仓库单类型
     * @param type                  出入库类型
     * @param goodsId               原料/成品ID
     * @param goodsNumber           货品数量
     * @param applyUser             申请人
     * */
    @Override
    public String addSysDepotOrder(String id,String orderType, String type, String goodsId, String goodsNumber, String applyUser) {

        CheckUtil.notBlank(id, "仓库单编号为空");
        CheckUtil.notBlank(orderType, "仓库单类型为空");
        CheckUtil.notBlank(type, "出入库类型为空");
        CheckUtil.notBlank(goodsId, "原料/成品ID为空");
        CheckUtil.notBlank(goodsNumber, "货品数量为空");
        CheckUtil.notBlank(applyUser, "申请人为空");

        DepotOrder depotOrder= new DepotOrder();
        depotOrder.setId(id);
        depotOrder.setOrderType(orderType);
        depotOrder.setType(type);
        depotOrder.setGoodsId(goodsId);
        depotOrder.setGoodsNumber(goodsNumber);
        depotOrder.setApplyUser(applyUser);
        depotOrder.setApplyTime(new Date());
        if(orderType.equals(Constants.Depot.ORDER_IN)){
            depotOrder.setState(Constants.DepotState.DEPOT_WAITING_IN);
        }
        if(orderType.equals(Constants.Depot.ORDER_OUT)){
            depotOrder.setState(Constants.DepotState.DEPOT_WAITING_OUT);
        }

        depotOrderMapper.insertSelective(depotOrder);

        return depotOrder.getId();
    }

    /**
     * 根据主键获取仓库单信息
     * @param id 仓库单主键
     * */
    @Override
    public DepotOrder getById(String id) {
        return depotOrderMapper.getById(id);
    }

    /**
     * 审核通过，更新出库单
     * @param id                    仓库单id
     * @param state                 仓库单状态
     * @param orderAuditUser        审核人
     * @param auditDescribe         审核描述
     * */
    @Override
    public Boolean auditPassDepotOrderOut(String id,String state, String orderAuditUser, String auditDescribe) {

        CheckUtil.notBlank(id, "仓库单id为空");
        CheckUtil.notBlank(orderAuditUser, "审核人为空");
        CheckUtil.notBlank(auditDescribe, "审核描述为空");
        DepotOrder depotOrder= new DepotOrder();
        if(depotOrderMapper.getById(id).getState().equals(Constants.DepotState.DEPOT_WAITING_OUT)){
            if(state.equals(Constants.DepotState.DEPOT_PASS)){
                depotOrder.setState(Constants.DepotState.DEPOT_PASS);
            }else{
                CheckUtil.notBlank(null, "未传入正确的状态信息");
            }
        }else {
            CheckUtil.notBlank(null, "已审核或已入库");
        }

        if(depotOrderMapper.getById(id).getType().equals("销售出库")){

            OrderAuditBean orderAuditBean = orderAuditMapper.getByOrderId(id);
            orderAuditBean.setState("仓库通过审核");
            orderAuditServiceImpl.depot(orderAuditBean);

        }


        //出库单要出库的数量
        String goodsNumberOut = depotOrderMapper.getById(id).getGoodsNumber();
        //库存中的数量
        String goodsNumberInventory = depotInventoryServiceImpl.findOne(depotOrderMapper.getById(id).getGoodsId()).getGoodsNumber();
        //待发货数量
        String shipmentsNumber = depotInventoryServiceImpl.findOne(depotOrderMapper.getById(id).getGoodsId()).getShipmentsNumber();
        //可销售数量
        String saleableNumber = depotInventoryServiceImpl.findOne(depotOrderMapper.getById(id).getGoodsId()).getSaleableNumber();
        if(goodsNumberInventory != null){
            if(Integer.parseInt(goodsNumberOut) > Integer.parseInt(saleableNumber)){
                CheckUtil.notBlank(null, "库存不足");
                return false;
            }
        }else{
            CheckUtil.notBlank(null, "货物不存在");
            return false;
        }
        depotOrder.setId(id);
        depotOrder.setOrderAuditUser(orderAuditUser);
        depotOrder.setOrderAuditTime(new Date());
        depotOrder.setAuditDescribe(auditDescribe);
        depotOrderMapper.updateByPrimaryKeySelective(depotOrder);

        DepotInventory depotInventory = depotInventoryServiceImpl.findOne(depotOrderMapper.getById(id).getGoodsId());
        depotInventory.setSaleableNumber(String.valueOf(Integer.parseInt(goodsNumberInventory)-Integer.parseInt(shipmentsNumber)-Integer.parseInt(goodsNumberOut)));
        depotInventory.setShipmentsNumber(String.valueOf(Integer.parseInt(shipmentsNumber)+Integer.parseInt(goodsNumberOut)));
        depotInventoryServiceImpl.updateDepotInventory(depotInventory.getId(),depotInventory.getType(),depotInventory.getGoodsId(),depotInventory.getGoodsNumber(),depotInventory.getShipmentsNumber(),depotInventory.getSaleableNumber());
        return true;
    }

    /**
     * 审核通过，更新入库单
     * @param id                    仓库单id
     * @param state                 仓库单状态
     * @param orderAuditUser        审核人
     * @param auditDescribe         审核描述
     * */
    @Override
    public Boolean auditPassDepotOrderIn(String id,String state, String orderAuditUser, String auditDescribe) {

        CheckUtil.notBlank(id, "仓库单id为空");
        CheckUtil.notBlank(orderAuditUser, "审核人为空");
        CheckUtil.notBlank(auditDescribe, "审核描述为空");
        DepotOrder depotOrder= new DepotOrder();
        if(depotOrderMapper.getById(id).getState().equals(Constants.DepotState.DEPOT_WAITING_IN)){
            if(state.equals(Constants.DepotState.DEPOT_PASS)){
                depotOrder.setState(Constants.DepotState.DEPOT_PASS);
            }else{
                CheckUtil.notBlank(null, "未传入正确的状态信息");
            }
        }else {
            CheckUtil.notBlank(null, "已审核或已入库");
        }

        depotOrder.setId(id);
        depotOrder.setOrderAuditUser(orderAuditUser);
        depotOrder.setOrderAuditTime(new Date());
        depotOrder.setAuditDescribe(auditDescribe);
        depotOrderMapper.updateByPrimaryKeySelective(depotOrder);
        return true;
    }

    /**
     * 审核驳回，更新入库单
     * @param id                    仓库单id
     * @param state                 仓库单状态
     * @param orderAuditUser        审核人
     * @param auditDescribe         审核描述
     * */
    @Override
    public Boolean auditRejectDepotOrderIn(String id,String state, String orderAuditUser, String auditDescribe) {


        CheckUtil.notBlank(id, "仓库单id为空");
        CheckUtil.notBlank(orderAuditUser, "审核人为空");
        CheckUtil.notBlank(auditDescribe, "审核描述为空");
        DepotOrder depotOrder= new DepotOrder();
        if(depotOrderMapper.getById(id).getState().equals(Constants.DepotState.DEPOT_WAITING_IN)){
            if(state.equals(Constants.DepotState.DEPOT_FAIL)){
                depotOrder.setState(Constants.DepotState.DEPOT_FAIL);
            }else{
                CheckUtil.notBlank(null, "未传入正确的状态信息");
            }
        }else {
            CheckUtil.notBlank(null, "审核通过或已入库");
        }

        depotOrder.setId(id);
        depotOrder.setOrderAuditUser(orderAuditUser);
        depotOrder.setOrderAuditTime(new Date());
        depotOrder.setAuditDescribe(auditDescribe);

        depotOrderMapper.updateByPrimaryKeySelective(depotOrder);
        return true;
    }

    /**
     * 审核驳回，更新出库单
     * @param id                    仓库单id
     * @param state                 仓库单状态
     * @param orderAuditUser        审核人
     * @param auditDescribe         审核描述
     * */
    @Override
    public Boolean auditRejectDepotOrderOut(String id,String state, String orderAuditUser, String auditDescribe) {


        CheckUtil.notBlank(id, "仓库单id为空");
        CheckUtil.notBlank(orderAuditUser, "审核人为空");
        CheckUtil.notBlank(auditDescribe, "审核描述为空");
        DepotOrder depotOrder= new DepotOrder();
        if(depotOrderMapper.getById(id).getState().equals(Constants.DepotState.DEPOT_WAITING_OUT)){
            if(state.equals(Constants.DepotState.DEPOT_FAIL)){
                depotOrder.setState(Constants.DepotState.DEPOT_FAIL);
            }else{
                CheckUtil.notBlank(null, "未传入正确的状态信息");
            }
        }else {
            CheckUtil.notBlank(null, "审核通过或已出库");
        }

        depotOrder.setId(id);
        depotOrder.setOrderAuditUser(orderAuditUser);
        depotOrder.setOrderAuditTime(new Date());
        depotOrder.setAuditDescribe(auditDescribe);

        depotOrderMapper.updateByPrimaryKeySelective(depotOrder);
        return true;
    }

    /**
     *入库,更新入库单
     * @param id                    仓库单id
     * @param state                 仓库单状态
     * */
    @Override
    public Boolean storageInDepotOrderIn(String id,String state) {


        CheckUtil.notBlank(id, "仓库单id为空");

        DepotOrder depotOrder= new DepotOrder();
        if(depotOrderMapper.getById(id).getState().equals(Constants.DepotState.DEPOT_PASS)){
            if(state.equals(Constants.DepotState.DEPOT_PASS_IN)){
                depotOrder.setState(Constants.DepotState.DEPOT_PASS_IN);
            }else{
                CheckUtil.notBlank(null, "未传入正确的状态信息");
            }
        }else {
            CheckUtil.notBlank(null, "已入库");
        }

        depotOrder.setId(id);
        depotOrderMapper.updateByPrimaryKeySelective(depotOrder);

        //采购入库,更新沧海采购单状态
        purchaseOrderServiceImpl.depotState(id);

        //入库单
        DepotOrder current = depotOrderMapper.getById(id);
        //货物类型
        String type = null;
        String goodsId = current.getGoodsId();

        if(lenProductMapper.getByPrimaryKey(goodsId) !=null){
            type = Constants.PRODUCT;
        }
        if(sysMaterialGoodsMapper.getById(goodsId) !=null){
            type = Constants.MATERIAL;
        }

        DepotInventory depotInventory = depotInventoryServiceImpl.findOne(depotOrderMapper.getById(id).getGoodsId());

        //判断库存中是否存在该货物,不存在创建
        if( depotInventory == null){
            depotInventoryServiceImpl.addDepotInventory(type,current.getGoodsId(),current.getGoodsNumber());
            return true;
        }
        //存在累加
        depotInventory.setGoodsNumber(String.valueOf(Integer.parseInt(current.getGoodsNumber())+Integer.parseInt(depotInventory.getGoodsNumber())));
        depotInventory.setSaleableNumber(String.valueOf(Integer.parseInt(current.getGoodsNumber())+Integer.parseInt(depotInventory.getSaleableNumber())));
        depotInventoryServiceImpl.updateDepotInventory(depotInventory.getId(),type,goodsId,depotInventory.getGoodsNumber(),null,depotInventory.getSaleableNumber());



        return true;
    }


    /**
     *出库,更新出库单
     * @param id                    仓库单id
     * @param state                 仓库单状态
     * */
    @Override
    public Boolean storageInDepotOrderOut(String id,String state) {


        CheckUtil.notBlank(id, "仓库单id为空");

        DepotOrder depotOrder= new DepotOrder();
        if(depotOrderMapper.getById(id).getState().equals(Constants.DepotState.DEPOT_PASS)){
            if(state.equals(Constants.DepotState.DEPOT_PASS_OUT)){
                depotOrder.setState(Constants.DepotState.DEPOT_PASS_OUT);
            }else{
                CheckUtil.notBlank(null, "未传入正确的状态信息");
            }
        }else {
            CheckUtil.notBlank(null, "已出库");
        }

        depotOrder.setId(id);
        depotOrderMapper.updateByPrimaryKeySelective(depotOrder);

        //出库单要出库的数量
        String goodsNumberOut = depotOrderMapper.getById(id).getGoodsNumber();
        //库存中的数量
        String goodsNumberInventory = depotInventoryServiceImpl.findOne(depotOrderMapper.getById(id).getGoodsId()).getGoodsNumber();
        //待发货数量
        String shipmentsNumber = depotInventoryServiceImpl.findOne(depotOrderMapper.getById(id).getGoodsId()).getShipmentsNumber();

        DepotInventory depotInventory = depotInventoryServiceImpl.findOne(depotOrderMapper.getById(id).getGoodsId());
        //库存数量=实际库存数- 出库数
        depotInventory.setGoodsNumber(String.valueOf(Integer.parseInt(goodsNumberInventory)-Integer.parseInt(goodsNumberOut)));
        //待发货数量 =  待发货数量 - 出库数
        depotInventory.setShipmentsNumber(String.valueOf(Integer.valueOf(shipmentsNumber)-Integer.parseInt(goodsNumberOut)));
        System.out.println(depotInventory);
        depotInventoryServiceImpl.updateDepotInventory(depotInventory.getId(),depotInventory.getType(),depotInventory.getGoodsId(),depotInventory.getGoodsNumber(),depotInventory.getShipmentsNumber(),depotInventory.getSaleableNumber());
        return true;
    }

    /**
     * 删除仓库单
     * @param id 仓库id
     * */
    @Override
    public Boolean deleteDepotOrder(String id) {
        CheckUtil.notBlank(id, "仓库单id为空");
        depotOrderMapper.deleteByPrimaryKey(id);
        return true;
    }


    /**
     * 删除仓库单
     * @param ids 仓库ids
     * */
    @Override
    public Boolean delsDepotOrder(String ids) {
        CheckUtil.notBlank(ids, "仓库单ids为空");
        List<String> depotOrderIds = new ArrayList<String>();
        String[] split = ids.split(",");
        for (String s : split) {
            depotOrderIds.add(s);
        }
        depotOrderMapper.delsByIds(depotOrderIds);
        return true;
    }

    /**
     * 获取入库单类型
     * @param orderType             仓库单类型
     * */
    @Override
    public List<String> getDepotOrderInType(String orderType) {
        return depotOrderMapper.getDepotOrderInType(orderType);
    }
}
