package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.FinanceReceiptMapper;
import com.gameloft9.demo.dataaccess.model.system.SysFinanceReceipt;
import com.gameloft9.demo.service.api.system.FinanceReceiptService;
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
public class FinanceReceiptServiceImpl implements FinanceReceiptService {

    @Autowired
    FinanceReceiptMapper receiptMapper;

    /**
     *
     * @param page 当前页
     * @param limit 页面条数
     * @param receiveType 支付单类型
     * @return
     *      入账单集合
     */
    public List<SysFinanceReceipt> getAll(String page, String limit, String receiveType) {
        PageRange pageRange = new PageRange(page,limit);
        int receiveType1 = NumberUtil.strToInt(receiveType);
        return receiptMapper.getAll(pageRange.getStart(),pageRange.getEnd(),receiveType1);
    }

    /**
     *
     * @param receiveType 支付单类型
     * @return
     *      总条数
     */
    public int getCount(String receiveType) {
        int receiveType1 = NumberUtil.strToInt(receiveType);
        return receiptMapper.getCount(receiveType1);
    }
}
