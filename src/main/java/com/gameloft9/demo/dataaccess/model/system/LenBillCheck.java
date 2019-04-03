package com.gameloft9.demo.dataaccess.model.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author : Lennon_Yuan
 * @time : 2019-03-29 - 16:32
 * @description :
 */
@Data
public class LenBillCheck {

    private String id;
    private String state;
    private String checkNumber;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date checkDate;
    private String checkUser;
    private String checkRemark;
    private String reachId;
    private String productId;
    private String planId;
    private String productName;
}
