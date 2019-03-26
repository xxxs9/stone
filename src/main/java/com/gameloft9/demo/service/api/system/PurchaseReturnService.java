package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.PurchaseReturn;

import java.util.List;

/**
 * PurchaseReturn service接口
 * @author OliverCH
 * @date 2019/03/24
 */
public interface PurchaseReturnService {

    /**
     * 获取所有信息
     * @param page 页数
     * @param limit 显示的条数
     * @param goodsId 商品名称
     * @param depotState 审核
     * @return List<PurchaseReturn> 返回值*/
    List<PurchaseReturn> selectAll(String page,String limit,String goodsId,String depotState);

    /**
     * 分页查找
     * @param goodsId 商品名称
     * @param depotState 审核
     * @return int 返回值*/
    int countGetAll(String goodsId,String depotState);

    /**
     * 增加
     * @param purchaseReturn 实体类
     * @return String 返回类型*/
    String insert(PurchaseReturn purchaseReturn);

    /**
     * 删除
     * @param id id
     * @return boolean 布尔类型*/
    boolean deleteByPrimaryKey(String id);

    /**
     * 根据id获取信息
     * @param id id
     * @return PurchaseReturn 返回类型*/
    PurchaseReturn selectByPrimaryKey(String id);

    /**
     * 修改
     * @param purchaseReturn 实体类
     * @return boolean 返回类型*/
    boolean update(PurchaseReturn purchaseReturn);

    /**
     * 获取goodsId下拉框
     * @return List<PurchaseReturn> 返回类型*/
    List<PurchaseReturn> selectAllGoodsId();

    /**
     * 获取orderNumber下拉框
     * @return List<PurchaseReturn> 返回类型*/
    List<PurchaseReturn> selectAllOrderNumber();

    /**根据orderNumber下拉框自动获取信息*/
    PurchaseReturn selectOtherByOrderNumber(String orderNumber);

    /**
     * 采购退货 提交
     * @param purchaseReturn 实体
     * @return boolean 布尔类型*/
    boolean commitReUpdate(PurchaseReturn purchaseReturn);

    /**
     * 采购退货 撤回
     * @param purchaseReturn 实体
     * @return boolean 布尔类型*/
    boolean backReUpdate(PurchaseReturn purchaseReturn);

}
