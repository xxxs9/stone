package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.OrderAuditMapper;
import com.gameloft9.demo.dataaccess.model.system.OrderAudit;
import com.gameloft9.demo.dataaccess.model.system.OrderAuditBean;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import com.gameloft9.demo.mgrframework.utils.StateUtil;
import com.gameloft9.demo.service.api.system.OrderAuditService;
import com.gameloft9.demo.service.beans.system.PageRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class OrderAuditServiceImpl implements OrderAuditService {

    @Autowired
    OrderAuditMapper orderAuditMapper;

    /**
     * 分页模糊查询
     * @param page
     * @param limit
     * @param markerOrderId
     * @return
     */

    @Override
    public List<OrderAudit> findAll(String page, String limit, String markerOrderId) {
        PageRange pageRange = new PageRange(page, limit);

        return orderAuditMapper.findAll(pageRange.getStart(),pageRange.getEnd(),markerOrderId);
    }

    /**
     *
     * @return
     */
    @Override
    public int dataCount() {
        return orderAuditMapper.dataCount();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public int deleteById(String id) {
        return orderAuditMapper.deleteById(id);
    }

    /**
     * 获取订单审核ID
     */
    @Override
    public OrderAuditBean getById(String id) {
        return orderAuditMapper.getById(id);
    }

    /**
     * 修改
     * @param
     * @return
     */
    @Override
    public Boolean update(OrderAuditBean orderAuditBean) {
        orderAuditMapper.update(orderAuditBean);
        return true;
    }

    /**
     * 驳回
     * @param
     * @return
     */
    @Override
    public Boolean backUpdate(OrderAuditBean orderAuditBean) {
        CheckUtil.notBlank(orderAuditBean.getId(),"订单id为空");
        orderAuditBean.setState(StateUtil.APPLY_FAIL);
        orderAuditMapper.backUpdate(orderAuditBean);
        return true;
    }

    /**
     * 审核成功
     * @param orderAuditBean
     * @return
     */
    @Override
    public Boolean passUpdate(OrderAuditBean orderAuditBean) {
        CheckUtil.notBlank(orderAuditBean.getId(),"订单id为空");
        orderAuditBean.setState(StateUtil.APPLY_PASS);
        orderAuditMapper.passUpdate(orderAuditBean);
        return true;
    }


}
