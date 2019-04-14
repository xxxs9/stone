package com.gameloft9.demo.dataaccess.model.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: Lennon_Yuan
 * @time: 2019/3/18 0018-下午 2:01
 * @description: 产品的实体类
 */
@Data
public class LenProduct implements Serializable {
    private String id;
    private String productName;
    private String supportPrice;
    private String productNumber;
    private String productType;
    private String productState;
    private String productDescribe;
    private String canSold;
    private String other1;
    private String other2;
    private String other3;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd hh:mm:ss")
    private Date other4;
}
