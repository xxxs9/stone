package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

/**
 * @author: jc
 * @description:
 * @create: 2019/03/18 15:14
 */

@Data
public class SysFinanceSaleReceivable extends SysFinancePublicPurchaseSale {

    /**ID*/
    private String id;

    /**销售发货成品数量*/
    private String productNumber;

    /**销售发货单id*/
    private String saleId;

    /**公共表ID*/
    private String publicId;
}
