package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

/**
 * @author: jc
 * @description:
 * @create: 2019/03/18 15:46
 */

@Data
public class SysFinanceChargeOff {

    /**ID*/
    private String id;

    /**应付单ID*/
    private String payId;

    /**应付款*/
    private String accountPayable;

    /**实际付款*/
    private String actualPayment;

    /**欠款*/
    private String arrears;
}
