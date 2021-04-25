package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Sxiu
 * @create: 2019/3/21 15:44
 * @description:
 */
@Data
public class DepotInventory implements Serializable {

    private String id;

    private String goodsName;

    private String type;

    private String goodsId;

    private String goodsNumber;

    private String shipmentsNumber;

    private String saleableNumber;
}
