package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

/**
 * @author: 啊发包
 * @Date: 2019/03/22 2019-03-22
 */
@Data
public class SysFinanceApplyOrder {

    /** ID */
    private String id;

    /** 申请ID */
    private String applyId;

    /** 申请人 */
    private String applyUser;

    /** 申请时间 */
    private String applyTime;

    /** 申请类型 */
    private Integer applyType;

    /** 申请款 */
    private String applyMoney;

    /** 申请状态 */
    private Integer applyState;

}
