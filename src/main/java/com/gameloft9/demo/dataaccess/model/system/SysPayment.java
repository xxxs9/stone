package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

/**
 * @author: jc
 * @description:
 * @create: 2019/03/18 15:20
 */

@Data
public class SysPayment extends SysFinancePublic {

    /**ID*/
    private String id;

    /**应付单id*/
    private String payId;

    /**金额*/
    private String balance;

    /**公共表ID*/
    private String publicId;
}
