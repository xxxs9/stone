package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.PurchaseReturn;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PurchaseReturnMapper {

    /**
     * 显示所有列表
     * @param start 开始
     * @param end 结束
     * @param goodsId 商品名称
     * @param state 审核
     * @return List<PurchaseReturn> 返回内容*/
    List<PurchaseReturn> selectAll(
            @Param("start") int start,
            @Param("end") int end,
            @Param("goodsId") String goodsId,
            @Param("state") String state);

    /**
     * 分页显示
     * @param goodsId 商品名称
     * @param state 审核
     * @return int 返回值*/
    int countGetAll(
            @Param("goodsId") String goodsId,
            @Param("state") String state);

    /**
     * 增加
     * @param record 记录
     * @return int 返回值*/
    int insert(PurchaseReturn record);

    /**
     * 删除
     * @param id id
     * @return int 返回值*/
    int deleteByPrimaryKey(String id);

    /**
     * 根据id获取信息
     * @param id id
     * @return int 返回值*/
    PurchaseReturn selectByPrimaryKey(String id);

    /**
     * 修改
     * @param record 记录
     * @return int 返回值*/
    int update(PurchaseReturn record);

    /**
     * 初始化goodsId下拉框
     * @return  List<PurchaseReturn> 返回值*/
    List<PurchaseReturn> selectAllGoodsId();

    /**
     * 初始化orderNumber下拉框
     * @return List<PurchaseReturn> 返回值
     * */
    List<PurchaseReturn> selectAllOrderNumber();

    /**
     * 采购退货
     * 提交、撤回
     * @param purchaseReturn 实体
     * @return Boolean 布尔类型
     * */
    Boolean updateTools(PurchaseReturn purchaseReturn);
    
}
