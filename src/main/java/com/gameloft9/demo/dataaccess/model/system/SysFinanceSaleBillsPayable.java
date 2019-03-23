package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

/**
 * @author: jc
 * @description:
 * @create: 2019/03/18 15:05
 */

@Data
public class SysFinanceSaleBillsPayable extends SysFinancePublicPurchaseSale {

    /**ID*/
    private String id;

    /**购买数量*/
    private String rejectedNumber;

    /**客户退货单id*/
    private String saleRejectedId;

    /**公共表ID*/
    private String publicId;
}
