package com.gameloft9.demo.dataaccess.model.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author: 啊发包
 * @Date: 2019/03/26 2019-03-26
 * 账单
 */
@Data
public class SysFinanceBill {

    /**ID*/
    private String id;

    /**部门*/
    private String department;

    /**款*/
    private String balance;

    /**时间*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date billTime;

    /**姓名*/
    private String applyUser;

    /**货品名称*/
    private String goodsName;

    /**货款类型*/
    private Integer balanceType;

}
