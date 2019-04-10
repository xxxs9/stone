package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.PurchaseOrder;

import java.util.Date;
import java.util.List;

/**
 * PurchaseOrder service接口
 * @author OliverCH
 * @date 2019/03/18
 */
public interface PurchaseOrderService {

    /**
    * 根据id获取
    * @param id id
    * @return PurchaseOrder
    */
    PurchaseOrder selectByPrimaryKey(String id);

    /**
     * 增加
     * @param purchaseOrder 实体类
     * @return PurchaseOrder
     */
    String insert(PurchaseOrder purchaseOrder);

    /**
     * 删除
     * @param id id
     * @return boolean 布尔类型
     */
    boolean deleteByPrimaryKey(String id);

    /**
     * 修改
     * @param purchaseOrder 实体类
     * @return boolean 布尔类型
     */
    boolean updateByPrimaryKey(PurchaseOrder purchaseOrder);

    /**
     * 根据state查看
     * @param id id
     * @return PurchaseOrder 实体
     * */
    PurchaseOrder lookSelect(String id);

    /**
     * 查看 look
     * @param purchaseOrder 实体类
     * @return boolean 布尔类型
     * */
    boolean lookUpdate(PurchaseOrder purchaseOrder);

    /**
     * 审核inspect
     * @param purchaseOrder 实体类
     * @return boolean 布尔类型
     */
    boolean inspectUpdate(PurchaseOrder purchaseOrder);

    /**
     * 获取所有
     * @param page 页数
     * @param limit 限制
     * @param goodsName 商品名称
     * @param state 订单类型
     * @return List<PurchaseOrder> 返回类型
     */
    List<PurchaseOrder> selectAll(String page,String limit,String goodsName,String state);

    /**
     * 根据id获取审核所需的列表
     * @param page 页数
     * @param limit 限制
     * @param goodsName 商品名称
     * @param state 订单类型
     * @return List<PurchaseOrder> 返回类型
     * */
    List<PurchaseOrder> selectAllByInspect(String page,String limit,String goodsName,String state,String financeState);

    /**
     * 获取个数
     * @param goodsName 商品名称
     * @param state 订单类型
     * @return int 返回类型
     */
    int countGetAll(String goodsName,String state,String financeState);

    /**
     * 获取goodsId下拉框
     * @return List<PurchaseOrder> 返回集合类型
     */
    List<PurchaseOrder> getSelectListGoods();

    /**
     * 提交commit
     * @param purchaseOrder 实体
     * @return boolean 布尔类型
     */
    boolean commitUpdate(PurchaseOrder purchaseOrder);

    /**
     * 撤回recall
     * @param id id
     * @return boolean 布尔类型
     */
    boolean recallUpdate(String  id);

    /**
     *采购收货 获取所有
     * @param page 页面
     * @param limit 页面页数
     * @param goodsName 商品名称
     * @param depotState 仓管审核状态
     * @return List<PurchaseOrder> 返回值*/
    List<PurchaseOrder> selectAllByInOrder(String page,String limit,String goodsName,
                                           String depotState);

    /**
     * 采购收货
     * 获取个数
     * @param goodsName 商品名称
     * @param depotState 订单类型
     * @return int 返回类型
     */
    int countGetAllByInOrder(String goodsName,String depotState);

    /**
     * 采购入库之收货bring
     * @param purchaseOrder 实体类
     * @return boolean 布尔类型
     */
    boolean bringInUpdate(PurchaseOrder purchaseOrder);

    /**
     * 采购入库之确认sure
     * @param purchaseOrder 实体类
     * @return boolean 布尔类型
     */
    boolean sureInUpdate(PurchaseOrder purchaseOrder);

    /**
     * 采购入库之提交commit
     * @param purchaseOrder 实体类
     * @return boolean 布尔类型
     */
    boolean commitInUpdate(PurchaseOrder purchaseOrder);

    /**
     * 采购入库之撤回back
     * @param purchaseOrder 实体类
     * @return boolean 布尔类型
     */
    boolean backInUpdate(PurchaseOrder purchaseOrder);

    /**
     * 采购入库之编辑edit
     * @param purchaseOrder 实体类
     * @return boolean 布尔类型
     * */
    boolean InUpdate(PurchaseOrder purchaseOrder);

    /**
     * 采购入库之查看审核未通过原因 look
     * @param purchaseOrder 实体类
     * @return boolean 布尔类型
     */
    boolean lookIn(PurchaseOrder purchaseOrder);

    /**
     * 采购 通过goodsId自动获取price
     * @param materialId 商品名称
     * @return PurchaseOrder 返回实体类型*/
    List<String> selectPriceByGoodsId(String materialId);

    /**
     * 获取orderNumber下拉框
     * 根据state为审核通过
     * @return List<PurchaseOrder> 返回类型
     * */
    List<PurchaseOrder> selectAllByOrderNumber();

    /**
     * 查看审核通过的订单详情
     * @param purchaseOrder 实体类
     * @return boolean 布尔类型
     */
    boolean selectAllBySearch(PurchaseOrder purchaseOrder);

    /**
     * 根据id获取
     * @param orderNumber orderNumber
     * @return PurchaseOrder
     */
    PurchaseOrder selectByOrderNumber(String orderNumber);

    /**
     * 提供接口 华锋 改变depotState
     * */
    boolean depotState(String orderNumber);

    /**
     * 四月份采购图表 柱状图
     * */
    List<String> selectChartByApril(String goodsName);

    /**
     * 报表 获取所有goodsName
     * */
    List<String> selectGoodsNameAll();
}


