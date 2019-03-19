package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

/**
 * @author: jc
 * @description:
 * @create: 2019/03/18 15:46
 */

@Data
public class SysFinanceChargeOff extends SysFinancePublicPayReceive {

    /**ID*/
    private String id;

    /**应付单ID*/
    private String payId;

    /**公共表ID*/
    private String publicId;
}
