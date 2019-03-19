package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.FinancePurchaseBillsPayableMapper;
import com.gameloft9.demo.dataaccess.model.system.SysFinancePurchaseBillsPayable;
import com.gameloft9.demo.service.api.system.FinancePurchaseBillPayService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.DateUtil;
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
public class FinancePurchaseBillPayServiceImpl implements FinancePurchaseBillPayService {

    @Autowired
    FinancePurchaseBillsPayableMapper purchaseBillsPayableMapper;

    /**
     *
     * @param page 当前页
     * @param limit 每条条数
     * @param auditType 单子类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    public List<SysFinancePurchaseBillsPayable> getAll(String page, String limit, int auditType, String startTime, String endTime) {
        PageRange pageRange = new PageRange(page,limit);

        return purchaseBillsPayableMapper.getAll(pageRange.getStart(),
                pageRange.getEnd(), auditType, DateUtil.str2Date(startTime), DateUtil.str2Date(endTime));
    }

    /**
     *
     * @param auditType 单子类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    public int getCount(int auditType, String startTime, String endTime) {
        return purchaseBillsPayableMapper.getCount(auditType,
                DateUtil.str2Date(startTime), DateUtil.str2Date(endTime));
    }
}
