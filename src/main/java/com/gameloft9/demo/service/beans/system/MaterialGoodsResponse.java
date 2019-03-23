package com.gameloft9.demo.service.beans.system;

import lombok.Data;

import java.io.Serializable;

@Data
public class MaterialGoodsResponse implements Serializable {

    private String  id;

    private String supplierName;

    private String goodsName;

    private String goodsType;

    private String goodsDescribe;

    private String goodsSpecification;

    private String goodsPrice;

    private String goodsOriginPlace;

    private String imageUrl;

}
