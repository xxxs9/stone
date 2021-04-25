package com.gameloft9.demo.dataaccess.model.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * @author : Lennon_Yuan
 * @time : 2019-04-11 - 21:19
 * @description :
 */
@Data
public class LenOperator implements Serializable {
    /**
     * 主键
     */
    private String id;
    /**
     * 操作人
     */
    private String operatorUser;
    /**
     * 操作类型
     */
    private String operatorType;
    /**
     * 操作时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd hh:mm:ss")
    private Date operatorTime;
    /**
     * 操作编号
     */
    private String operatorNo;
    /**
     * 操作备注
     */
    private String operatorRemark;
    private String other1;
    private String other2;
    private String other3;


    /**
     * 构造函数
     * @param operatorNo
     * @param operatorUser
     * @param operatorType
     * @param operatorRemark
     * @param operatorTime
     * @param other1
     * @param other2
     * @param other3
     */
    public LenOperator(String operatorNo, String operatorUser, String operatorType, String operatorRemark, Date operatorTime, String other1, String other2, String other3) {
        this.operatorNo = operatorNo;
        this.operatorUser = operatorUser;
        this.operatorType = operatorType;
        this.operatorRemark = operatorRemark;
        this.operatorTime = operatorTime;
        this.other1 = other1;
        this.other2 = other2;
        this.other3 = other3;

    }

    /**
     * 构造函数
     * @param id
     * @param operatorNo
     * @param operatorUser
     * @param operatorType
     * @param operatorRemark
     * @param operatorTime
     */
    public LenOperator(String id, String operatorNo, String operatorUser, String operatorType, String operatorRemark, Date operatorTime) {
        this.id = id;
        this.operatorNo = operatorNo;
        this.operatorUser = operatorUser;
        this.operatorType = operatorType;
        this.operatorRemark = operatorRemark;
        this.operatorTime = operatorTime;


    }
    public LenOperator(){

    }
}
