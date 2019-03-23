package com.gameloft9.demo.service.api.system;

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
     * @param auditType 单子类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     *      销售应收单集合
     */
    List<SysFinanceSaleReceivable> getAll(String page, String limit, String auditType, String startTime, String endTime);


    /**
     * 条件查询总条数
     *
     * @param auditType 单子类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     *          条件查询总条数
     */
    int getCount(String auditType, String startTime, String endTime);
}
