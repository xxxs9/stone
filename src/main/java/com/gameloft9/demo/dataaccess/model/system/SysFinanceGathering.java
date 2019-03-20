package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

/**
 * @author: jc
 * @description:
 * @create: 2019/03/18 15:33
 */

@Data
public class SysFinanceGathering {

    /**ID*/
    private String id;

    /**应收单id*/
    private String receiveId;

    /**应收单类型*/
    private int receiveType;

    /**金额*/
    private String balance;

    /**制单人*/
    private String documentMaker;

    /**制单时间*/
    private String documentMakeTime;

}
