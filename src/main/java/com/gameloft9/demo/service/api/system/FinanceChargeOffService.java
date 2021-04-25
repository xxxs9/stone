package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.SysFinanceChargeOff;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/03/21 2019-03-21
 */

public interface FinanceChargeOffService {

    /**
     * 出账单集合
     *
     * @param page 当前页
     * @param limit 页面条数
     * @param payType 支付单类型
     * @return
     *  出账单集合
     */
    List<SysFinanceChargeOff> getAll(String page, String limit, String payType);

    /**
     * 条件查询总条数
     *
     * @param payType 支付单类型
     * @return
     *      总条数
     */
    int getCount(String payType);
}
