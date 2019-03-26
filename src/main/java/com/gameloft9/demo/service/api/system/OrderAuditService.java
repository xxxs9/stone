package com.gameloft9.demo.service.api.system;


import com.gameloft9.demo.dataaccess.model.system.OrderAudit;
import com.gameloft9.demo.dataaccess.model.system.OrderAuditBean;


import java.util.List;

public interface OrderAuditService {

    /**
     * 获取所有销售订单信息
     */
    List<OrderAudit> findAll(String page, String limit, String productId);

    /**
     * 获取所有销售订单个数
     * */
    //int countGetAll(String markerOrderId);

    int dataCount();

    /**
     * 删除
     */
    int deleteById (String id);

    /**
     * 获取订单审核ID
     */
    OrderAuditBean getById(String id);

    /**
     * 修改
     * @param
     * @return
     */
    Boolean update(OrderAuditBean orderAuditBean);

    /**
     * 驳回back
     */
    Boolean backUpdate(OrderAuditBean orderAuditBean);

    /**
     * 审核成功
     */
    Boolean passUpdate(OrderAuditBean orderAuditBean);

    /**
     * 审核
     * @param orderAuditBean
     * @return
     */
    Boolean audit(OrderAuditBean orderAuditBean);
}
