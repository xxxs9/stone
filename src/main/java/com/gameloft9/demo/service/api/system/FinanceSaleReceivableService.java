package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.PurchaseOrder;
import com.gameloft9.demo.dataaccess.model.system.ShipmentOrder;
import com.gameloft9.demo.dataaccess.model.system.SysFinanceSaleBillsPayable;
import com.gameloft9.demo.dataaccess.model.system.SysFinanceSaleReceivable;

import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/03/19 2019-03-19
 */

public interface FinanceSaleReceivableService {

    /**
     * 销售应收单列表
     *
     * @param page 当前页
     * @param limit 每条条数
     * @param auditState 审核状态
     * @return
     *      销售应收单集合
     */
    List<SysFinanceSaleReceivable> getAll(String page, String limit, String auditState);


    /**
     * 条件查询总条数
     *
     * @param auditState 单子类型
     * @return
     *          条件查询总条数
     */
    int getCount(String auditState);

    /**
     * 添加采购申请应收单
     *
     * @param shipmentOrder 销售应收申请单
     * @param id1 id1
     * @return
     *      string
     */
    String generateSaleReceive(ShipmentOrder shipmentOrder, String id1);

    /**
     * saleOrderReceivePass
     * @param attitude purchaseOrder
     * @param id id
     * @param auditType 申请类型
     * @param actualPrice 实际价格
     * @param auditDescribe 审核信息
     *
     * @return
     *  boolean
     */
    Boolean saleOrderReceivePass(String attitude,String id,String auditType,String actualPrice,String auditDescribe);

    /**
     * 根据id获取
     *
     * @param id 主键id
     * @return
     *      SysFinanceSaleBillsPayable
     */
    SysFinanceSaleReceivable getSaleReceiveById(String id);
}
