package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.MarkerOrderMapper;
import com.gameloft9.demo.dataaccess.model.system.MarkerOrderTest;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import com.gameloft9.demo.mgrframework.utils.StateUtil;
import com.gameloft9.demo.service.api.system.MarkerOrderService;
import com.gameloft9.demo.service.beans.system.PageRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
@Transactional
public class MarkerOrderServiceImpl implements MarkerOrderService {

    @Autowired
    MarkerOrderMapper markerOrderMapper;

    /**
     * 获取所有订单信息
     * @param page
     * @param limit
     * @param orderId
     * @param orderTime
     * @return
     */
    @Override
    public List<MarkerOrderTest> findAll(String page, String limit, String orderId, Date orderTime) {
        PageRange pageRange = new PageRange(page,limit);
        return markerOrderMapper.findAll(pageRange.getStart(),pageRange.getEnd(),orderId,orderTime);
    }

    /**
     * 获取所有订单个数
     * @param orderId
     * @param orderTime
     * @return
     */
    @Override
    public int countGetAll(String orderId, Date orderTime) {

        return markerOrderMapper.countGetAll(orderId,orderTime);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public int deleteById(String id) {
        return markerOrderMapper.deleteById(id);
    }

    /**
     * 修改
     * @param markerOrderTest
     * @return
     */
    @Override
    public Boolean update(MarkerOrderTest markerOrderTest) {
        return markerOrderMapper.update(markerOrderTest);
    }

    /**
     * 增加
     * @param markerOrderTest
     * @return
     */
    @Override
    public String add(MarkerOrderTest markerOrderTest) {
        return markerOrderMapper.add(markerOrderTest);
    }

    /**
     * 获取销售订单id
     * @param id
     * @return
     */
    @Override
    public MarkerOrderTest getMaker(String id) {
        return markerOrderMapper.getMaker(id);
    }

    /**
     * 提交
     * @param markerOrderTest
     * @return
     */
    @Override
    public Boolean audiUpdate(MarkerOrderTest markerOrderTest) {

        CheckUtil.notBlank(markerOrderTest.getId(),"订单id为空");
        markerOrderTest.setState(StateUtil.APPLY_WAITING);
        markerOrderMapper.audiUpdate(markerOrderTest);
        return true;
    }

    /**
     * 撤回
     * @param markerOrderTest
     * @return
     */
    @Override
    public Boolean backUpdate(MarkerOrderTest markerOrderTest) {
        CheckUtil.notBlank(markerOrderTest.getId(),"订单id为空");
        markerOrderTest.setState(StateUtil.APPLY_NO_AUDI);
        markerOrderMapper.backUpdate(markerOrderTest);
        return true;
    }


}
