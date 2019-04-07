package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.SysFinanceBill;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
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

    /**
     * 导出数据
     */
    void export(HttpServletRequest request, HttpServletResponse response);

    /**
     * 日报表
     */
    List<SysFinanceBill> getDayBill();

    /**
     * 周报表
     */
    List<SysFinanceBill> getWeekBill();

    /**
     * 月报表
     */
    List<SysFinanceBill> getMonthBill();

    /**
     * 年报表
     */
    List<SysFinanceBill> getYearBill();

    /**
     * 导出日报表
     */
    void exportDailyReport(HttpServletRequest request, HttpServletResponse response);

    /**
     * 导出周报表
     */
    void exportWeeklyReport(HttpServletRequest request, HttpServletResponse response);

    /**
     * 导出月报表
     */
    void exportMonthlyReport(HttpServletRequest request, HttpServletResponse response);

    /**
     * 导出年报表
     */
    void exportAnnualReport(HttpServletRequest request, HttpServletResponse response);

    /**
     * 导出时间报表
     */
    void exportTimeReport(HttpServletRequest request, HttpServletResponse response,String startTime,String endTime);

    /**
     * 时间报表
     */
    List<SysFinanceBill> getTimeBill(String startTime, String endTime);


}
