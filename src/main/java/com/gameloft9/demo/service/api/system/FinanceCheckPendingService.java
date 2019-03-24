package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.PurchaseOrder;

import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/03/20 2019-03-20
 */

public interface FinanceCheckPendingService {

    /**
     * 获取待付款采购订单集合
     *
     * @return
     */
    List<PurchaseOrder> getPurchaseList();

    /**
     * 财务审核采购申请订单
     *
     * @param id id
     * @param financeAuditDescribe 审核内容
     * @param isAgree 是够同意
     * @return
     *      boolean
     */
    Boolean auditingPurchaseOrder(String id, String financeAuditDescribe ,String isAgree);
}
