package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

/**
 * @author: Lennon_Yuan
 * @time: 2019/3/18 0018-下午 2:01
 * @description: 产品的实体类
 */
@Data
public class LenProduct {
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
}
