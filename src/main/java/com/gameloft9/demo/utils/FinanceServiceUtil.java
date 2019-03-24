package com.gameloft9.demo.utils;

import com.gameloft9.demo.service.beans.system.PageRange;

import java.util.*;

/**
 * @author: 啊发包
 * @Date: 2019/03/19 2019-03-19
 */
public class FinanceServiceUtil {

    private PageRange pageRange;
    /**
     * s
     */
    private int auditType;
    private Date startTime;
    private Date endTIme;

    public FinanceServiceUtil(String page,String limit,String auditType,String startTime,String endTIme){
        this.pageRange = new PageRange(page,limit);
        this.auditType = NumberUtil.strToInt(auditType);
        this.startTime = DateUtil.ifNull(startTime);
        this.endTIme = DateUtil.ifNull(endTIme);
    }

    public FinanceServiceUtil(String auditType,String startTime,String endTIme){
        this.auditType = NumberUtil.strToInt(auditType);
        this.startTime = DateUtil.ifNull(startTime);
        this.endTIme = DateUtil.ifNull(endTIme);
    }


    public int getAuditType() {
        return auditType;
    }

    public void setAuditType(int auditType) {
        this.auditType = auditType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTIme() {
        return endTIme;
    }

    public void setEndTIme(Date endTIme) {
        this.endTIme = endTIme;
    }

    public PageRange getPageRange() {
        return pageRange;
    }

    public void setPageRange(PageRange pageRange) {
        this.pageRange = pageRange;
    }
}
