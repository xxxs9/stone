package com.gameloft9.demo.dataaccess.model.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;


@Data
public class MarkerOrderTest {

    private String id;
    private String orderId;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderTime;
    private String customer;
    private String deliverNumber;
    private String currentNumber;
    private String plannedNumber;
    private String acceptedAmount;
    private String unpaidAmount;
    private String applyUser;
    private String state;
    private String orderAuditUser;
    private String remarks;

    @Override
    public String toString() {
        return "MarkerOrderTest{" +
                "id='" + id + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderTime=" + orderTime +
                ", customer='" + customer + '\'' +
                ", deliverNumber='" + deliverNumber + '\'' +
                ", currentNumber='" + currentNumber + '\'' +
                ", plannedNumber='" + plannedNumber + '\'' +
                ", acceptedAmount='" + acceptedAmount + '\'' +
                ", unpaidAmount='" + unpaidAmount + '\'' +
                ", applyUser='" + applyUser + '\'' +

                ", state='" + state + '\'' +
                ", orderAuditUser='" + orderAuditUser + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
