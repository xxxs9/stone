package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

/**
 * @author: jc
 * @description:
 * @create: 2019/03/18 15:20
 */

@Data
public class SysFinancePayment {

    /**ID*/
    private String id;

    /**应付单id*/
    private String payId;

    /**应付单类型*/
    private int payType;

    /**金额*/
    private String balance;

    /**制单人*/
    private String documentMaker;

    /**制单时间*/
    private String documentMakeTime;
}
