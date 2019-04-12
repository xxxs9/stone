package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.SysFinanceBill;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.FinanceBillService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * @author: 啊发包
 * @Date: 2019/03/26 2019-03-26
 */
@Controller
@RequestMapping("/bill")
public class FinanceBillController {

    @Autowired
    FinanceBillService billService;

    /**
     * 分页显示
     * @param page 当前页
     * @param limit 当前页条数
     * @return
     *      json
     */
    @RequestMapping(value = "/billList" ,method = RequestMethod.POST)
    @ResponseBody
    public IResult getAll(String page,String limit){
        return new PageResultBean<Collection<SysFinanceBill>>(billService.getAll(page,limit),billService.getCount());
    }


    @RequestMapping(value = "/export" ,method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse response){
        billService.export(request,response);
    }

    @RequestMapping(value = "/dailyReport",method = RequestMethod.POST)
    @ResponseBody
    public IResult dailyReport(){
        return new ResultBean<Collection<SysFinanceBill>>(billService.getDayBill());
    }

    @RequestMapping(value = "/weeklyReport",method = RequestMethod.POST)
    @ResponseBody
    public IResult weeklyReport(){
        return new ResultBean<Collection<SysFinanceBill>>(billService.getWeekBill());
    }

    @RequestMapping(value = "/monthlyReport",method = RequestMethod.POST)
    @ResponseBody
    public IResult monthlyReport(){
        return new ResultBean<Collection<SysFinanceBill>>(billService.getMonthBill());
    }

    @RequestMapping(value = "/annualReport",method = RequestMethod.POST)
    @ResponseBody
    public IResult annualReport(){
        return new ResultBean<Collection<SysFinanceBill>>(billService.getYearBill());
    }

    @RequestMapping(value = "/timeReport",method = RequestMethod.POST)
    @ResponseBody
    public IResult timeReport(String startTime,String endTime){
        return new ResultBean<Collection<SysFinanceBill>>(billService.getTimeBill(startTime,endTime));
    }



    /**
     * 导出日报表
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportDailyReport" ,method = RequestMethod.GET)
    public void exportDailyReport(HttpServletRequest request, HttpServletResponse response){
        billService.exportDailyReport(request,response);
    }

    /**
     * 导出周报表
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportWeeklyReport" ,method = RequestMethod.GET)
    public void exportWeeklyReport(HttpServletRequest request, HttpServletResponse response){
        billService.exportWeeklyReport(request,response);
    }

    /**
     * 导出月报表
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportMonthlyReport" ,method = RequestMethod.GET)
    public void exportMonthlyReport(HttpServletRequest request, HttpServletResponse response){
        billService.exportMonthlyReport(request,response);
    }

    /**
     * 导出年报表
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportAnnualReport" ,method = RequestMethod.GET)
    public void exportAnnualReport(HttpServletRequest request, HttpServletResponse response){
        billService.exportAnnualReport(request,response);
    }

    /**
     * 导出年报表
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportTimeReport" ,method = RequestMethod.GET)
    public void exportTimeReport(HttpServletRequest request, HttpServletResponse response,String startTime,String endTime){
        billService.exportTimeReport(request,response,startTime,endTime);
    }

    /**
     * 当年总收入
     *
     */
    @RequestMapping(value = "/getTotalReceive" ,method = RequestMethod.POST)
    @ResponseBody
    public IResult getTotalReceive(){
        return new ResultBean<Collection<String>>(billService.getTotalReceive());
    }

    /**
     * 当年总收入
     *
     */
    @RequestMapping(value = "/getTotalPay" ,method = RequestMethod.POST)
    @ResponseBody
    public IResult getTotalPay(){
        return new ResultBean<Collection<String>>(billService.getTotalPay());
    }
}
