package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.SysFinanceChargeOff;
import com.gameloft9.demo.dataaccess.model.system.SysFinanceReceipt;

import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/03/21 2019-03-21
 */

public interface FinanceReceiptService {
    /**
     * 入账单集合
     *
     * @param page 当前页
     * @param limit 页面条数
     * @param receiveType 支付单类型
     * @return
     *  入账单集合
     */
    List<SysFinanceReceipt> getAll(String page, String limit, String receiveType);

    /**
     * 条件查询总条数
     *
     * @param receiveType 支付单类型
     * @return
     *      总条数
     */
    int getCount(String receiveType);
}
