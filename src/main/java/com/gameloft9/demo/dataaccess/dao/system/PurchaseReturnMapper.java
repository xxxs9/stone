package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.PurchaseReturn;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * PurchaseOrder dao
 * 采购订单 dao层
 * @author OliverCH
 * @date 2019/03/24
 */
public interface PurchaseReturnMapper {

    /**
     * 显示所有列表
     * @param start 开始
     * @param end 结束
     * @param goodsId 商品名称
     * @param depotState 审核
     * @return List<PurchaseReturn> 返回内容*/
    List<PurchaseReturn> selectAll(
            @Param("start") int start,
            @Param("end") int end,
            @Param("goodsId") String goodsId,
            @Param("depotState") String depotState);

    /**
     * 分页显示
     * @param goodsId 商品名称
     * @param depotState 审核
     * @return int 返回值*/
    int countGetAll(
            @Param("goodsId") String goodsId,
            @Param("depotState") String depotState);

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




    /**
     * 阿发包 财务主管 审核状态为审核通过
     * @param start 开始
     * @param end 结束
     * @return List<purchaseReturn> 集合*/
    List<PurchaseReturn> selectReturnByFinance(
            @Param("start") int start,
            @Param("end") int end);

    /**华锋 仓库主管 审核状态为提交审核中
     * @param start 开始
     * @param end 结束
     * @return List<purchaseReturn> 集合*/
    List<PurchaseReturn> selectReturnByDepot(
            @Param("start") int start,
            @Param("end") int end);

}
