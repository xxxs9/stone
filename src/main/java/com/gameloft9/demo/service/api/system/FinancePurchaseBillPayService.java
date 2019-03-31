package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.dao.system.FinancePurchaseBillsPayableMapper;
import com.gameloft9.demo.dataaccess.model.system.PurchaseOrder;
import com.gameloft9.demo.dataaccess.model.system.SysFinanceApplyOrder;
import com.gameloft9.demo.dataaccess.model.system.SysFinancePurchaseBillsPayable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/03/19 2019-03-19
 */

public interface FinancePurchaseBillPayService {

    /**
     * 采购应付单列表
     *
     * @param page 当前页
     * @param limit 每条条数
     * @param auditState 申请状态
     * @return
     *      采购应付单集合
     */
    List<SysFinancePurchaseBillsPayable> getAll(String page, String limit, String auditState);


    /**
     * 条件查询总条数
     *
     * @param auditState 申请状态
     * @return
     *          条件查询总条数
     */
    int getCount(String auditState);

    /**
     * 添加申请订单
     *
     * @param financePurchaseBillsPayable 申请订单
     * @return
     *      String
     */
    String addPurchasePay(SysFinancePurchaseBillsPayable financePurchaseBillsPayable);

    /**
     * 根据申请单id查找应付单
     *
     * @param purchaseOrderId 申请单id
     * @return
     *      采购应付单
     */
    SysFinancePurchaseBillsPayable getPurchasePay(String purchaseOrderId);

    /**
     * 添加采购申请应付单
     *
     * @param purchaseOrder 采购申请单
     * @param id1 id
     * @return
     *      string
     */
    String generatePurchasePay(PurchaseOrder purchaseOrder,String id1);

    /**
     * 根据id获取
     *
     * @param id 主键id
     * @return
     *      applyOrder
     */
    SysFinancePurchaseBillsPayable getPurchasePayById(String id);

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
    Boolean purchaseOrderPayPass(String attitude,String id,String auditType,String actualPrice,String auditDescribe);
}
