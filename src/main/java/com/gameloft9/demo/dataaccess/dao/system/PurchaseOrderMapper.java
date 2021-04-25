package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.PurchaseOrder;
import org.apache.ibatis.annotations.Param;

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
     * @param id id
     * @return int 返回值
     */
    PurchaseOrder lookSelect(String id);

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
     * @param goodsName 货品名称
     * @param state 订单状态
     * @return List<PurchaseOrder> 返回值
     * */
    List<PurchaseOrder> selectAll(
            @Param("start") int start,
            @Param("end") int end,
            @Param("goodsName") String goodsName,
            @Param("state") String state);

    /**
     * 根据仓管财务状态获取信息
     * @param start 开始
     * @param end 结束
     * @param goodsName 货品名称
     * @param depotState 仓管订单状态
     * @return List<PurchaseOrder> 返回值 */
    List<PurchaseOrder> selectAllByInOrder(
            @Param("start") int start,
            @Param("end") int end,
            @Param("goodsName") String goodsNamge,
            @Param("depotState") String depotState);

    /**
     * 审核页面根据id获取列表
     * @param start 开始
     * @param end 结束
     * @param goodsName 货品名称
     * @param state 订单状态
     * @return List<PurchaseOrder> 返回值
     * */
    List<PurchaseOrder> selectAllByInspect(
            @Param("start") int start,
            @Param("end") int end,
            @Param("goodsName") String goodsName,
            @Param("state") String state,
            @Param("financeState") String financeState);

    /**
     * 分页获取个数
     * @param goodsName 货品名称
     * @param state 订单状态
     * @return int
     * */
    int countGetAll(
            @Param("goodsName") String goodsName,
            @Param("state") String state,
            @Param("financeState") String financeState);

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
     * 采购入库之收货、确认、撤回、提交
     * @param purchaseOrder 实体
     * @return boolean 布尔类型
     * */
    Boolean toolsUpdate(PurchaseOrder purchaseOrder);

    /**
    * 采购入库之编辑
    * @param purchaseOrder 实体
    * @return boolean 布尔类型
    */
    Boolean InUpdate(PurchaseOrder purchaseOrder);

    /**
     * 根据状态为待付款，查出所有
     *
     * @return
     *         啊
     */
    List<PurchaseOrder> getAllPurchaseOrderByState();

    /**
     * 采购收货
     * 分页获取个数
     * @param goodsName 货品名称
     * @param depotState 订单状态
     * @return int
     * */
    int countGetAllByInOrder(
            @Param("goodsName") String goodsName,
            @Param("depotState") String depotState);

    /**
     * 采购收货
     * 查看审核未通过原因
     * @param record 记录
     * @return int 返回值
     * */
    int lookIn(PurchaseOrder record);

    /**
     * 采购
     * 根据goodsId获取price
     * @param goodsName 商品名称
     * @return PurchaseOrder 返回实体类*/
    List<String> selectPriceByGoodsId(String goodsName);

    /**
     * 获取orderNumber下拉框
     * 根据状态为审核通过
     * @return List<PurchaseOrder> 返回类型
     * */
    List<PurchaseOrder> selectAllByOrderNumber();

    /**
     * 查看审核通过的订单详情
     * @param record 记录
     * @return int
     * */
    int selectAllBySearch(PurchaseOrder record);

    /**
     * 根据订单编号获取所有内容
     * @param orderNumber orderNumber
     * @return PurchaseOrder
     * */
    PurchaseOrder selectByOrderNumber(String orderNumber);

    /**
     * 四月份报表
     * */
    List<String> selectChartByApril(String goodsName);

    /**
     * 报表 获取所有goodsName
     * */
    List<String> selectGoodsNameAll();



    /**
     * 根据财务审核状态financeState来获取所有 分页
     * @param start 开始
     * @param end 结束
     * @return List<PurchaseOrder> 返回值
     *         啊
     * */
    List<PurchaseOrder> getAllByFinance(
            @Param("start") int start,
            @Param("end") int end);

    /**
     * 阿发包
     * 通过财务审核状态为申请付款获取列表*/
    List<PurchaseOrder> selectApplyByFinance(
            @Param("start") int start,
            @Param("end") int end);

    /**
     * 华锋
     * 根据仓库审核状态获取列表*/
    List<PurchaseOrder> selectApplyByDepot(
            @Param("start") int start,
            @Param("end") int end);

    /**
     * 啊发包
     * 根据id和财务审核类型更新purchaseOrder
     * @param purchaseOrder purchaseOrder
     */
    void updateByIdAndState(PurchaseOrder purchaseOrder);

    /**
     * 啊发包
     * 根据id和订单类型
     * @param id id
     * @param auditType 订单类型
     * @return
     *      purchaseOrder
     */
    PurchaseOrder findByIdAndAuditType(@Param("id") String id,@Param("auditType") Integer auditType);

    /**
     * 啊发包
     * 更新purchaseOrder的财务审核状态
     * @param purchaseOrder
     */
    void purchaseOrderPayPass(PurchaseOrder purchaseOrder);

    /**
     * 啊发包
     * 根据orderNumber
     * @param orderNumber orderNumber
     * @return
     *      purchaseOrder
     */
    PurchaseOrder findByOrderNumber(@Param("orderNumber") String orderNumber);
}
