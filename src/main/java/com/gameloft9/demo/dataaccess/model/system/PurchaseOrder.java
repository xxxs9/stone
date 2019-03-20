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
    private String state;
    private Integer auditType;
    private String orderAuditUser;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderAuditTime;
    private String payAuditUser;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payAuditTime;
    private String applyDescribe;
    private String auditDescribe;

    @Override
    public String toString() {
        return "PurchaseOrder{" +
                "id='" + id + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", goodsNumber='" + goodsNumber + '\'' +
                ", price='" + price + '\'' +
                ", applyUser='" + applyUser + '\'' +
                ", applyTime=" + applyTime +
                ", state='" + state + '\'' +
                ", auditType=" + auditType +
                ", orderAuditUser='" + orderAuditUser + '\'' +
                ", orderAuditTime=" + orderAuditTime +
                ", payAuditUser='" + payAuditUser + '\'' +
                ", payAuditTime=" + payAuditTime +
                ", applyDescribe='" + applyDescribe + '\'' +
                ", auditDescribe='" + auditDescribe + '\'' +
                '}';
    }
}
