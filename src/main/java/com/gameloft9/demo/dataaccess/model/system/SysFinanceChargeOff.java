package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

import java.util.Date;

/**
 * @author: jc
 * @description:
 * @create: 2019/03/18 15:46
 * 出账
 */

@Data
public class SysFinanceChargeOff {

    /**ID*/
    private String id;

    /**部门*/
    private String department;

    /**款*/
    private String balance;

    /**时间*/
    private Date time;
}
