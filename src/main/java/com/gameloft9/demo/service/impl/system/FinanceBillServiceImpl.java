package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.FinanceBillMapper;
import com.gameloft9.demo.dataaccess.model.system.SysFinanceBill;
import com.gameloft9.demo.service.api.system.FinanceBillService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.DateUtil;
import com.gameloft9.demo.utils.ExportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/03/26 2019-03-26
 */
@Service
@Transactional(rollbackFor = Exception.class)

public class FinanceBillServiceImpl implements FinanceBillService {

    @Autowired
    FinanceBillMapper billMapper;

    /**
     *
     * @param page 当前页
     * @param limit 当前页条数
     * @return
     *      账单集合
     */
    public List<SysFinanceBill> getAll(String page, String limit) {
        PageRange pageRange = new PageRange(page,limit);
        return billMapper.getAll(pageRange.getStart(),pageRange.getEnd());
    }

    /**
     *
     * @return
     *      总条数
     */
    public int getCount() {
        return billMapper.getCount();
    }

    /**
     * 导出数据
     */
    public void export(HttpServletRequest request, HttpServletResponse response) {
        //查找所有的对账
        List<SysFinanceBill> billList = billMapper.getAllBill();
        ExportUtil.exprotData(request,response,billList,"财务报表.xls");

    }

    @Override
    public List<SysFinanceBill> getDayBill() {
        return billMapper.getDayBill();
    }

    @Override
    public List<SysFinanceBill> getWeekBill() {
        return billMapper.getWeekBill();
    }

    @Override
    public List<SysFinanceBill> getMonthBill() {
        return billMapper.getMonthBill();
    }

    @Override
    public List<SysFinanceBill> getYearBill() {
        return billMapper.getYearBill();
    }

    @Override
    public List<SysFinanceBill> getTimeBill(String startTime, String endTime) {
        Date startTime1 = DateUtil.ifNull(startTime);
        Date endTime1 = DateUtil.ifNull(endTime);
        return billMapper.getTimeBill(startTime1,endTime1);
    }

    @Override
    public List<String> getTotalReceive() {
        return billMapper.getTotalReceive();
    }

    @Override
    public List<String> getTotalPay() {
        return billMapper.getTotalPay();
    }

    /**
     * 导出日报表
     * @param request
     * @param response
     */
    @Override
    public void exportDailyReport(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "财务日报表.xls";
        List<SysFinanceBill> billList = getDayBill();
        ExportUtil.exprotData(request,response,billList,fileName);
    }

    /**
     * 导出周报表
     * @param request
     * @param response
     */
    @Override
    public void exportWeeklyReport(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "财务周报表.xls";
        List<SysFinanceBill> billList = getWeekBill();
        ExportUtil.exprotData(request,response,billList,fileName);
    }

    /**
     * 导出月报表
     * @param request
     * @param response
     */
    @Override
    public void exportMonthlyReport(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "财务月报表.xls";
        List<SysFinanceBill> billList = getMonthBill();
        ExportUtil.exprotData(request,response,billList,fileName);
    }

    /**
     * 导出年报表
     * @param request
     * @param response
     */
    @Override
    public void exportAnnualReport(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "财务年报表.xls";
        List<SysFinanceBill> billList = getYearBill();
        ExportUtil.exprotData(request,response,billList,fileName);
    }

    /**
     * 导出时间报表
     * @param request
     * @param response
     * @param startTime
     * @param endTime
     */
    @Override
    public void exportTimeReport(HttpServletRequest request, HttpServletResponse response, String startTime, String endTime) {
        String fileName = "财务时间报表.xls";
        List<SysFinanceBill> billList = getTimeBill(startTime,endTime);
        ExportUtil.exprotData(request,response,billList,fileName);
    }




}
