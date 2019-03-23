package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

/**
 * @author: jc
 * @description:
 * @create: 2019/03/18 15:45
 */

@Data
public class SysFinanceReceipt {

    /**ID*/
    private String id;

    /**应收单ID*/
    private String receiveId;

    /**应收款*/
    private String amountReceivable;

    /**实际收款*/
    private String actualAmount;

    /**欠款*/
    private String arrears;
}
