package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

/**
 * @author: jc
 * @description:
 * @create: 2019/03/18 15:45
 */

@Data
public class SysFinanceEnterAnItemInAnAccount extends SysFinancePublicPayReceive {

    /**ID*/
    private String id;

    /**应收单ID*/
    private String receiveId;

    /**公共表ID*/
    private String publicId;
}
