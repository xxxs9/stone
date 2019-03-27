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

    /**
     * 方法抽取
     *
     * @param applyType 申请类型
     * @param applyState 申请状态
     * @param startTime 开始时间
     * @param endTime 结束时间
     */
    public void format(String applyType, String applyState, String startTime, String endTime){
        applyType1 = NumberUtil.strToInt(applyType);
        applyState1 = NumberUtil.strToInt(applyState);
        startTime1 = DateUtil.strToDateFinance(startTime);
        endTime1 = DateUtil.strToDateFinance(endTime);
    }

    /**
     *
     * @param page 开始
     * @param limit 结束
     * @param applyType 申请类型
     * @param applyState 审核状态
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    public List<SysFinanceApplyOrder> getAll(String page, String limit, String applyType, String applyState, String startTime, String endTime) {
        PageRange pageRange = new PageRange(page,limit);
        format(applyType,applyState,startTime,endTime);
        return applyOrderMapper.getAll(pageRange.getStart(),pageRange.getEnd(),applyType1,applyState1,startTime1,endTime1);
    }

    /**
     *
     * @param applyType 申请类型
     * @param applyState 审核状态
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    public int getCount(String applyType, String applyState, String startTime, String endTime) {
        format(applyType,applyState,startTime,endTime);
        return applyOrderMapper.getCount(applyType1,applyState1,startTime1,endTime1);
    }

    /**
     * 根据id查找applyOrder
     *
     * @param id id
     * @return
     *  applyOrder
     */
    public SysFinanceApplyOrder getPurchasePayById(String id) {
        return applyOrderMapper.getById1(id);
    }

    public String addPurchasePay(SysFinanceApplyOrder financeApplyOrder) {

        return null;
    }


}
