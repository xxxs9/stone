package com.gameloft9.demo.dataaccess.model.system;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * model class
 * @author OliverCH
 * @date 2016/03/24
 */
@Data
public class PurchaseReturn {
    private String id;
    private String orderNumber;
    private String goodsId;
    private String goodsNumber;
    private String price;
    private String applyUser;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date applyTime;
    private String depotState;
    private String financeState;
    private Integer auditType;
    private String depotUser;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date depotTime;
    private String depotDescribe;
    private String financeAuditUser;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date financeAuditTime;
    private String financeAuditDescribe;
}
