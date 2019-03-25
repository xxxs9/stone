package com.gameloft9.demo.dataaccess.model.system;


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
    private Date applyTime;
    private String depotState;
    private String financeState;
    private Integer auditType;
    private String depotUser;
    private Date depotTime;
    private String depotDescribe;
    private String financeAuditUser;
    private Date financeAuditTime;
    private String financeAuditDescribe;
}
