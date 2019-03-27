package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.ReturnGoodsOrder;
import com.gameloft9.demo.dataaccess.model.system.ShipmentOrder;
import com.gameloft9.demo.dataaccess.model.system.SysFinancePurchaseBillsPayable;
import com.gameloft9.demo.dataaccess.model.system.SysFinanceSaleBillsPayable;

import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/03/19 2019-03-19
 */

public interface FinanceSaleBillPayService {
    /**
     * 销售应付单列表
     *
     * @param page 当前页
     * @param limit 每条条数
     * @param auditState 审核状态
     * @return
     *      销售应付单集合
     */
    List<SysFinanceSaleBillsPayable> getAll(String page, String limit, String auditState);


    /**
     * 条件查询总条数
     *
     * @param auditState 审核状态
     * @return
     *          条件查询总条数
     */
    int getCount(String auditState);

    /**
     * 添加销售申请应付单
     *
     * @param returnGoodsOrder 销售应付申请单
     * @param id1 id1
     * @return
     *      string
     */
    String generateSalePay(ReturnGoodsOrder returnGoodsOrder, String id1);

    /**
     * 更新purchaseOrder的财务审核状态
     * @param attitude purchaseOrder
     * @param id id
     * @param auditType 申请类型
     * @param actualPrice 实际价格
     * @param auditDescribe 审核信息
     *
     * @return
     *  boolean
     */
    Boolean saleOrderPayPass(String attitude,String id,String auditType,String actualPrice,String auditDescribe);

    /**
     * 根据id获取
     *
     * @param id 主键id
     * @return
     *      SysFinanceSaleBillsPayable
     */
    SysFinanceSaleBillsPayable getSalePayById(String id);
}
