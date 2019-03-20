package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.FinanceGatheringMapper;
import com.gameloft9.demo.dataaccess.dao.system.FinancePurchaseReceivableMapper;
import com.gameloft9.demo.dataaccess.model.system.SysFinanceGathering;
import com.gameloft9.demo.dataaccess.model.system.SysFinancePurchaseReceivable;
import com.gameloft9.demo.service.api.system.FinanceGatheringService;
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

public class FinanceGatheringServiceImpl implements FinanceGatheringService {

    @Autowired
    FinanceGatheringMapper gatheringMapper;

    /**
     *
     * @param page 当前页
     * @param limit 每条条数
     * @param receiveType 单子类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     *      应收款单集合
     */
    public List<SysFinanceGathering> getAll(String page, String limit, String receiveType, String startTime, String endTime) {
        FinanceServiceUtil util = new FinanceServiceUtil(page,limit,receiveType,startTime,endTime);
        return gatheringMapper.getAll(util.getPageRange().getStart(),util.getPageRange().getEnd(),
                util.getAuditType(),util.getStartTime(),util.getEndTIme());
    }

    /**
     *
     * @param receiveType 单子类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     *      条件查询总条数
     */
    public int getCount(String receiveType, String startTime, String endTime) {
        FinanceServiceUtil util = new FinanceServiceUtil(receiveType,startTime,endTime);
        return gatheringMapper.getCount(util.getAuditType(),util.getStartTime(),util.getEndTIme());
    }

}
