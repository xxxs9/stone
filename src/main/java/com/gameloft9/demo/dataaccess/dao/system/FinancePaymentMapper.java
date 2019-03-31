package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.SysFinancePayment;
import com.gameloft9.demo.dataaccess.model.system.SysFinancePurchaseBillsPayable;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/03/20 2019-03-20
 */

public interface FinancePaymentMapper extends BaseMapper<SysFinancePayment> {

    /**
     * 付款单列表
     *
     * @param start 开始
     * @param end 结束
     * @param payType 单子类型
     * @return
     *      付款单集合
     */
    List<SysFinancePayment> getAll(
            @Param("start") int start,
            @Param("end") int end,
            @Param("payType") int payType
    );

    /**
     * 条件查询总条数
     *
     * @param payType 单子类型
     * @return 条件查询总条数
     */
    int getCount(
            @Param("payType") int payType
    );
}
