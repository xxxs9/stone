package com.gameloft9.demo.dataaccess.dao.system;


import com.gameloft9.demo.dataaccess.model.system.MarkerOrderTest;
import com.gameloft9.demo.dataaccess.model.system.OrderAudit;
import com.gameloft9.demo.dataaccess.model.system.OrderAuditBean;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface OrderAuditMapper {

    /**
     * 获取所有销售订单信息
     * @param start 开始
     * @param end 结束
     *
     */
    List<OrderAudit> findAll(
            @Param("start") int start,
            @Param("end") int end,
            @Param("markerOrderId") String markerOrderId);

    /**
     * 获取所有销售订单个数
     * */
    //int countGetAll( @Param("markerOrderId") String markerOrderId);

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
}
