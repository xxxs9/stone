package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.PurchaseOrder;
import com.gameloft9.demo.dataaccess.model.system.SysFinancePurchaseBillsPayable;
import com.gameloft9.demo.dataaccess.model.system.SysFinancePurchaseReceivable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/03/19 2019-03-19
 */

public interface FinancePurchaseReceivableService {

    /**
     * 采购应收单列表
     *
     * @param page 当前页
     * @param limit 每条条数
     * @param auditState 单子类型
     * @return
     *      采购应收单集合
     */
    List<SysFinancePurchaseReceivable> getAll(String page, String limit, String auditState);


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
     * @param purchaseOrder 采购申请单
     * @param id1 id1
     * @return
     *      string
     */
    String generatePurchaseReceive(PurchaseOrder purchaseOrder,String id1);

    /**
     * 根据id获取
     *
     * @param id 主键id
     * @return
     *      SysFinancePurchaseReceivable
     */
    SysFinancePurchaseReceivable getPurchaseReceiveById(String id);

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
    Boolean purchaseOrderReceivePass(String attitude,String id,String auditType,String actualPrice,String auditDescribe);
}
