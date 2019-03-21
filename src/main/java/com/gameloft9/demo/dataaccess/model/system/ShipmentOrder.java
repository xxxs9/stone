package com.gameloft9.demo.dataaccess.model.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ShipmentOrder {

   private String id;
   private String shipmentId;
   private String goodsName;
   private String customer;
   private String quantityNumber;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
   private Date deliveryTime;
    private String receivingAddress;
    private String phone;
    private String consignee;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date receivingTime;
    private String state;
    private String remarks;
}
