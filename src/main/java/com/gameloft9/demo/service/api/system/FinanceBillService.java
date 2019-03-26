package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.SysFinanceBill;

import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/03/26 2019-03-26
 */


public interface FinanceBillService {

    /**
     * 获取集合
     *
     * @param page 当前页
     * @param limit 当前页条数
     * @return
     *      账单集合
     */
    List<SysFinanceBill> getAll(String page, String limit);

    /**
     * 获取总条数
     * @return
     *      总条数
     */
    int getCount();
}
