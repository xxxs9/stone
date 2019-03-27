package com.gameloft9.demo.dataaccess.model.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;


/**
 * model class
 * @author OliverCH
 * @date 2016/03/18
 */
@Data
public class PurchaseOrder {
    private String id;
    private String orderNumber;
    private String goodsId;
    private String goodsNumber;
    private String price;
    private String applyUser;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date applyTime;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date applyPayTime;
    private String state;
    private String financeState;
    private String orderAuditUser;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderAuditTime;
    private String applyDescribe;
    private String auditDescribe;
    private String financeAuditUser;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date financeAuditTime;
    private Integer auditType;
    private String financeAuditDescribe;
    private String depotState;
    private String depotUser;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date depotTime;
    private String depotDescribe;
}
