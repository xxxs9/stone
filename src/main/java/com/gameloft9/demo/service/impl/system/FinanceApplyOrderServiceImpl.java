package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.FinanceApplyOrderMapper;
import com.gameloft9.demo.dataaccess.model.system.SysFinanceApplyOrder;
import com.gameloft9.demo.service.api.system.FinanceApplyOrderService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.DateUtil;
import com.gameloft9.demo.utils.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/03/22 2019-03-22
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FinanceApplyOrderServiceImpl implements FinanceApplyOrderService {

    private int applyType1;
    private int applyState1;
    private Date startTime1;
    private Date endTime1;

    @Autowired
    FinanceApplyOrderMapper applyOrderMapper;

    public void format(String applyType, String applyState, String startTime, String endTime){
        int applyType1 = NumberUtil.strToInt(applyState);
        int applyState1 = NumberUtil.strToInt(applyType);
        Date startTime1 = DateUtil.ifNull(startTime);
        Date endTime1 = DateUtil.ifNull(endTime);
    }

    public List<SysFinanceApplyOrder> getAll(String page, String limit, String applyType, String applyState, String startTime, String endTime) {
        PageRange pageRange = new PageRange(page,limit);
        format(applyType,applyState,startTime,endTime);
        return applyOrderMapper.getAll(pageRange.getStart(),pageRange.getEnd(),applyType1,applyState1,startTime1,endTime1);
    }

    public int getCount(String applyType, String applyState, String startTime, String endTime) {
        format(applyType,applyState,startTime,endTime);
        return applyOrderMapper.getCount(applyType1,applyState1,startTime1,endTime1);
    }




}
