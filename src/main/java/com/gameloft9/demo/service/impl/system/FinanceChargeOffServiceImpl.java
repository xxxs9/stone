package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.FinanceChargeOffMapper;
import com.gameloft9.demo.dataaccess.model.system.SysFinanceChargeOff;
import com.gameloft9.demo.service.api.system.FinanceChargeOffService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/03/21 2019-03-21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FinanceChargeOffServiceImpl implements FinanceChargeOffService {

    @Autowired
    FinanceChargeOffMapper chargeOffMapper;

    /**
     *
     * @param page 当前页
     * @param limit 页面条数
     * @param payType 支付单类型
     * @return
     *      出账单集合
     */
    public List<SysFinanceChargeOff> getAll(String page, String limit, String payType) {
        PageRange pageRange = new PageRange(page,limit);
        int payType1 = NumberUtil.strToInt(payType);
        return chargeOffMapper.getAll(pageRange.getStart(),pageRange.getEnd(),payType1);
    }

    /**
     *
     *
     * @param payType 支付单类型
     * @return
     *      总条数
     */
    public int getCount(String payType) {
        int payType1 = NumberUtil.strToInt(payType);
        return chargeOffMapper.getCount(payType1);
    }
}
