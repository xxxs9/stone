package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

/**
 * @author: jc
 * @description:
 * @create: 2019/03/18 14:59
 */

@Data
public class SysFinancePurchaseBillsPayable extends SysFinancePublicPurchaseSale {

    /**ID*/
    private String id;

    /**购买数量*/
    private String goodsNumber;

    /**采购订单id*/
    private String purchaseOrderId;

    /**公共表ID*/
    private String publicId;



}
