package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.FinancePaymentMapper;
import com.gameloft9.demo.dataaccess.model.system.SysFinancePayment;
import com.gameloft9.demo.service.api.system.FinancePaymentService;
import com.gameloft9.demo.utils.FinanceServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/03/20 2019-03-20
 */

@Service
@Transactional(rollbackFor = Exception.class)

public class FinancePaymentServiceImpl implements FinancePaymentService {

    @Autowired
    FinancePaymentMapper paymentMapper;

    /**
     *
     * @param page 当前页
     * @param limit 每条条数
     * @param payType 单子类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     *      付款单集合
     */
    public List<SysFinancePayment> getAll(String page, String limit, String payType, String startTime, String endTime) {
        FinanceServiceUtil util = new FinanceServiceUtil(page,limit,payType,startTime,endTime);
        return paymentMapper.getAll(util.getPageRange().getStart(),util.getPageRange().getEnd(),
                util.getAuditType(),util.getStartTime(),util.getEndTIme());
    }

    /**
     *
     * @param payType 单子类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     *      条件查询总条数
     */
    public int getCount(String payType, String startTime, String endTime) {
        FinanceServiceUtil util = new FinanceServiceUtil(payType,startTime,endTime);
        return paymentMapper.getCount(util.getAuditType(),util.getStartTime(),util.getEndTIme());
    }

}
