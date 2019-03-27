package com.gameloft9.demo.service.api.system;

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
}
