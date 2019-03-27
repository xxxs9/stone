package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.FinanceSaleBillsPayableMapper;
import com.gameloft9.demo.dataaccess.model.system.SysFinanceSaleBillsPayable;
import com.gameloft9.demo.service.api.system.FinanceSaleBillPayService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.FinanceServiceUtil;
import com.gameloft9.demo.utils.NumberUtil;
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
     * @param auditState 审核状态
     * @return
     *      销售应付单集合
     */
    public List<SysFinanceSaleBillsPayable> getAll(String page, String limit, String auditState) {
        PageRange pageRange = new PageRange(page,limit);
        Integer auditState1 = NumberUtil.strToInt(auditState);

        return saleBillsPayableMapper.getAll(pageRange.getStart(),pageRange.getEnd(),auditState1);

    }

    /**
     *
     * @param auditState 审核状态
     * @return
     *      条件查询总条数
     */
    public int getCount(String auditState) {
        Integer auditState1 = NumberUtil.strToInt(auditState);
        return saleBillsPayableMapper.getCount(auditState1);
    }
}
