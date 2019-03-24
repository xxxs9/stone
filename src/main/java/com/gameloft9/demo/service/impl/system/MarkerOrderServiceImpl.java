package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.MarkerOrderMapper;
import com.gameloft9.demo.dataaccess.model.system.MarkerOrderTest;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import com.gameloft9.demo.mgrframework.utils.StateUtil;
import com.gameloft9.demo.service.api.system.MarkerOrderService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.OrderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
     * @param productId
     * @return
     */

    public List<MarkerOrderTest> findAll(String page, String limit, String productId) {
        PageRange pageRange = new PageRange(page,limit);
        return markerOrderMapper.findAll(pageRange.getStart(),pageRange.getEnd(),productId);
    }

    /**
     * 获取所有订单个数
     * @param productId
     * @return
     */

    public int countGetAll(String productId) {
        return markerOrderMapper.countGetAll(productId);
    }

    /**
     * 删除
     * @param id
     * @return
     */

    public int deleteById(String id) {
        return markerOrderMapper.deleteById(id);
    }

    /**
     * 修改
     * @param markerOrderTest
     * @return
     */

    public Boolean update(MarkerOrderTest markerOrderTest) {
        return markerOrderMapper.update(markerOrderTest);
    }

    /**
     * 增加
     * @param markerOrderTest
     * @return
     */

    public int add(MarkerOrderTest markerOrderTest) {
        return markerOrderMapper.add(markerOrderTest);
    }

    /**
     * 获取销售订单id
     * @param id
     * @return
     */

    public MarkerOrderTest getMaker(String id) {
        return markerOrderMapper.getMaker(id);
    }

    /**
     * 提交
     * @param markerOrderTest
     * @return
     */

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

    public Boolean backUpdate(MarkerOrderTest markerOrderTest) {
        CheckUtil.notBlank(markerOrderTest.getId(),"订单id为空");
        markerOrderTest.setState(StateUtil.APPLY_NO_AUDI);
        markerOrderMapper.backUpdate(markerOrderTest);
        return true;
    }

    /**
     * 自动生成订单编号
     * @param
     * @return
     */
    public String orderNum(MarkerOrderTest markerOrderTest) {
        markerOrderTest.setOrderId(OrderUtil.getLocalTrmSeqNum());
        return markerOrderMapper.orderNum(markerOrderTest);
    }

    /**
     *获取productid下拉框
     * @return
     */
    public List<MarkerOrderTest> getProductId() {
        List<MarkerOrderTest> list = new ArrayList<MarkerOrderTest>();
        list=markerOrderMapper.getProductId();
        return list;
    }


}
