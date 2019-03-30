package com.gameloft9.demo.dataaccess.model.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

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
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date applyTime;

    /** 申请类型 */
    private Integer applyType;

    /** 申请款 */
    private String applyMoney;

    /** 申请状态 */
    private Integer applyState;

}
