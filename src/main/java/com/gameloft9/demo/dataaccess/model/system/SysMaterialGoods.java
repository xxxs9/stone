package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysMaterialGoods implements Serializable {

    private String  id;

    private String  supplierId;

    private String materialId;

    private String goodsPrice;

    private String goodsOriginPlace;

    private String imageUrl;
}
