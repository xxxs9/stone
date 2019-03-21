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
     * */
    PurchaseOrder lookSelect(String state);

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
     * @param goodsId 商品名称
     * @param state 订单类型
     * @return List<PurchaseOrder> 返回类型
     */
    List<PurchaseOrder> selectAll(String page,String limit,String goodsId,String state);

    /**
     * 根据id获取审核所需的列表
     * @param page 页数
     * @param limit 限制
     * @param goodsId 商品名称
     * @param state 订单类型
     * @return List<PurchaseOrder> 返回类型
     * */
    List<PurchaseOrder> selectAllByInspect(String page,String limit,String goodsId,String state);

    /**
     * 获取个数
     * @param goodsId 商品名称
     * @param state 订单类型
     * @return int 返回类型
     */
    int countGetAll(String goodsId,String state);

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

}


