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
}
