package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.FinanceBillMapper;
import com.gameloft9.demo.dataaccess.model.system.SysFinanceBill;
import com.gameloft9.demo.service.api.system.FinanceBillService;
import com.gameloft9.demo.service.beans.system.PageRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


}
