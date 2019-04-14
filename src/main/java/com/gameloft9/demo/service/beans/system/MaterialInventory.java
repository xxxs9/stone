package com.gameloft9.demo.service.beans.system;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Sxiu
 * @create: 2019/4/14 08:56
 * @description:
 */
@Data
public class MaterialInventory implements Serializable {
    private String id;

    private String goodsName;

    private String supplierName;

    private String type;

    private String goodsId;

    private String goodsNumber;

    private String shipmentsNumber;

    private String saleableNumber;
}
