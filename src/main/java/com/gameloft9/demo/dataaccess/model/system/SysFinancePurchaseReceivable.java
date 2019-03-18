package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

/**
 * @author: jc
 * @description:
 * @create: 2019/03/18 15:09
 */

@Data
public class SysFinancePurchaseReceivable extends SysFinancePublic {

    /**ID*/
    private String id;

    /**采购退货数量*/
    private String rejectedNumber;

    /**采购退货单id*/
    private String purchaseOrderRejectedId;

    /**公共表ID*/
    private String publicId;
}
