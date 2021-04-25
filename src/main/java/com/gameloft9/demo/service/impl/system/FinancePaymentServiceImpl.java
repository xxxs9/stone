package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.FinancePaymentMapper;
import com.gameloft9.demo.dataaccess.model.system.SysFinancePayment;
import com.gameloft9.demo.service.api.system.FinancePaymentService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.FinanceServiceUtil;
import com.gameloft9.demo.utils.NumberUtil;
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
     * @return
     *      付款单集合
     */
    public List<SysFinancePayment> getAll(String page, String limit, String payType) {
        PageRange pageRange = new PageRange(page,limit);
        int payType1 = NumberUtil.strToInt(payType);
        return paymentMapper.getAll(pageRange.getStart(),pageRange.getEnd(),payType1);
    }

    /**
     *
     * @param payType 单子类型
     * @return
     *      条件查询总条数
     */
    public int getCount(String payType) {
        int payType1 = NumberUtil.strToInt(payType);
        return paymentMapper.getCount(payType1);
    }

}
