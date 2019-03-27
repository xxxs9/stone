package com.gameloft9.demo.dataaccess.model.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author : Lennon_Yuan
 * @time : 2019-03-26 - 10:22
 * @description :
 */


@Data
public class LenProductProductPlan {
    private String id;
    private String productName;
    private String productNumber;
    private String productType;
    private String productState;
    private String supportPrice;
    private String productDescribe;
    private String productId;
    private String planNumber;
    private String realNumber;
    private String goodsNumber;
    private String billCycle;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date billDate;
    private Date planState;
    private String planRemark;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date produceDate;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date finishDate;

}
