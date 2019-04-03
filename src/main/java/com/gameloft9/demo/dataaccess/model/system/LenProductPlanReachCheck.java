package com.gameloft9.demo.dataaccess.model.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author : Lennon_Yuan
 * @time : 2019-03-29 - 09:29
 * @description :产品-领料-验收实体类
 */
@Data
public class LenProductPlanReachCheck {

    /**
     * 产品实体类
     */
    private String productId;
    private String productName;
    private String supportPrice;
    private String productNumber;
    private String productType;
    private String productState;
    private String productDescribe;
    private String canSold;

    /**
     * 生产计划实体类
     */

    private String planId ;
    private String planProductId;
    /**
     * 计划产量
     */
    private String planNumber;
    private String realNumber;

    /**
     * 现有库存
     */
    private String goodsNumber;
    /**
     * 订单周期
     */
    private String billCycle;
    /**
     * 状态（是否外包）
     */
    private String planState;
    /**
     * 备注
     */
    private String planRemark;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date produceDate;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date finishDate;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date billDate;

    /**
     * 领料单实体类
     */

    private String reachId;
    private String reachProductId;
    private String produceFormulaId;
    private String produceFormulaDetailId;
    private String depotAudi;
    private String formulaBack;
    private String reachState;
    private String reachUser;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date reachTime;
    private String other1;
    private String other2;
    private String other3;

    /**
     * 验收单实体类
     */
    private String CheckId;
    private String producePlanId;
    private String formulaReachId;
    private String checkState;
    private String checkUser;
    private String wasteId;
    private String checkRemark;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date checkTime;


}
