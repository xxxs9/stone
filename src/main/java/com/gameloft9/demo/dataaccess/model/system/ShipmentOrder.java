package com.gameloft9.demo.dataaccess.model.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ShipmentOrder {

    private String id;
    private String goodsId;
    private String goodsName;
    private String customer;
    private String goodsNumber;
    private String goodsAmount;
    private String applyUser;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date applyTime;
    private String state;
    private String auditUser;
    private String auditType;
    private String remarks;
}
