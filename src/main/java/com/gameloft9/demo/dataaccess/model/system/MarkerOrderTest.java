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
    private String productId;
    private String customer;
    private String deliverNumber;
    private String currentNumber;
    private String plannedNumber;
    private String acceptedAmount;
    private String unpaidAmount;
    private String applyUser;
    private String state;
    private String orderAuditUser;
    private String orderAuditDepot;
    private String remarks;
    private String depotRemarks;


}
