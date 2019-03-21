package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.FinanceSaleBillsPayableMapper;
import com.gameloft9.demo.dataaccess.model.system.SysFinanceSaleBillsPayable;
import com.gameloft9.demo.service.api.system.FinanceSaleBillPayService;
import com.gameloft9.demo.utils.FinanceServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/03/19 2019-03-19
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FinanceSaleBillPayServiceImpl implements FinanceSaleBillPayService {

    @Autowired
    FinanceSaleBillsPayableMapper saleBillsPayableMapper;

    /**
     *
     * @param page 当前页
     * @param limit 每条条数
     * @param auditType 单子类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     *      销售应付单集合
     */
    public List<SysFinanceSaleBillsPayable> getAll(String page, String limit, String auditType, String startTime, String endTime) {
        FinanceServiceUtil util = new FinanceServiceUtil(page,limit,auditType,startTime,endTime);
        return saleBillsPayableMapper.getAll(util.getPageRange().getStart(),util.getPageRange().getEnd(),util.getAuditType(),util.getStartTime(), util.getEndTIme());

    }

    /**
     *
     * @param auditType 单子类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     *      条件查询总条数
     */
    public int getCount(String auditType, String startTime, String endTime) {
        FinanceServiceUtil util = new FinanceServiceUtil(auditType,startTime,endTime);
        return saleBillsPayableMapper.getCount(util.getAuditType(), util.getStartTime(), util.getEndTIme());
    }
}