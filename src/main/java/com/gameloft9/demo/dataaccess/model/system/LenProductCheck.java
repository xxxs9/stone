package com.gameloft9.demo.dataaccess.model.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author : Lennon_Yuan
 * @time : 2019-03-24 - 02:45
 * @description :
 */
@Data
public class LenProductCheck {


    private String id;
    private String producePlanId;
    private String formulaReachId;
    private String state;
    private String checkUser;
    private String wasteId;
    private String checkRemark;
    /**生产数量*/
    private String other1;
    private String other2;
    private String other3;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date checkTime;

}