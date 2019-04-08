package com.gameloft9.demo.dataaccess.model.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class AllRole {
    /**通知部门*/
    private String notifyId;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;
    private String notifyInfoId;
    private String receiverId;
    private String content;
    /**供应商部门*/
    private String supplierId;
    private String supplierName;
    private String materialGoodsId;
    private String goodsPrice;
    private String materialId;
    private String goodsName;
    private String phone;
    private String email;
    /**销售部门*/
    private String orderId;
    private String saleId;
    private String applyUser;
    private String customer;
    private String saleName;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderTime;
    /**采购部门*/
    private String orderNumber;
    private String proOrderId;
    private String orderAuditUser;
    private String goodsId;
    private String goodsNumber;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date applyTime;
    /**仓库部门*/
    private String depotId;
    private String type;
    private String depotName;
    private String depotNumber;
    private String saleableNumber;
    /**生产部门*/
    private String productPlanId;
    private String productName;
    private String productNumber;
    private String productState;
    private String canSold;
    /**财务部门*/
    private String financeApplyUser;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private String financeApplyTime;
    private String applyMoney;
    private Integer financeApplyState;

}
