package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.PurchaseOrder;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * PurchaseOrder dao
 * 采购订单 dao层
 * @author OliverCH
 * @date 2019/03/18
 */
public interface PurchaseOrderMapper {

    /**
     * 获取id
     * @param id id
     * @return PurchaseOrder
     * */
    PurchaseOrder selectByPrimaryKey(String id);

    /**
     * 增加
     * @param record 记录
     * @return int
     * */
    int insert(PurchaseOrder record);

    /**
     * 删除
     * @param id id
     * @return int
     * */
    int deleteByPrimaryKey(String id);

    /**
     * 修改
     * @param record 记录
     * @return int
     * */
    int updateByPrimaryKey(PurchaseOrder record);

    /**
     * 根据state查看
     * @param record 记录
     * @return int 返回值
     */
    PurchaseOrder lookSelect(String state);

    /**
     * 查看 look
     * @param record 记录
     * @return int 返回值
     * */
    int lookUpdate(PurchaseOrder record);

    /**
     * 审核 inspect
     * @param purchaseOrder 实体类
     * @return int 返回值
     */
    int inspectUpdate(PurchaseOrder purchaseOrder);

    /**
     * 分页获取所有
     * @param start 开始
     * @param end 结束
     * @param goodsId 货品名称
     * @param state 订单状态
     * @return List<PurchaseOrder> 返回值
     * */
    List<PurchaseOrder> selectAll(
            @Param("start") int start,
            @Param("end") int end,
            @Param("goodsId") String goodsId,
            @Param("state") String state);

    /**
     * 审核页面根据id获取列表
     * @param start 开始
     * @param end 结束
     * @param goodsId 货品名称
     * @param state 订单状态
     * @return List<PurchaseOrder> 返回值
     * */
    List<PurchaseOrder> selectAllByInspect(
            @Param("start") int start,
            @Param("end") int end,
            @Param("goodsId") String goodsId,
            @Param("state") String state);

    /**
     * 分页获取个数
     * @param goodsId 货品名称
     * @param state 订单状态
     * @return int
     * */
    int countGetAll(
            @Param("goodsId") String goodsId,
            @Param("state") String state);

    /**
     * 获取goodsId下拉框
     * @return List<PurchaseOrder>
     * */
    List<PurchaseOrder> getSelectListGoods();

    /**
     * 获取state下拉框
     * @return List<PurchaseOrder>
     * */
    List<PurchaseOrder> getSelectListState();

    /**
     * 撤回recall
     * @param purchaseOrder 实体
     * @return boolean 布尔类型
     */
    Boolean recallUpdate(PurchaseOrder purchaseOrder);

    /**
     * 提交commit
     * @param purchaseOrder 实体
     * @return boolean 布尔类型
     */
    Boolean commitUpdate(PurchaseOrder purchaseOrder);

    /**
     * 根据状态为待付款，查出所有
     *
     * @return
     *         啊
     */
    List<PurchaseOrder> getAllPurchaseOrderByState();


}
