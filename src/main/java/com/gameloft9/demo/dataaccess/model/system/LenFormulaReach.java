package com.gameloft9.demo.dataaccess.model.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author : Lennon_Yuan
 * @time : 2019-03-23 - 17:23
 * @description :
 */
@Data
public class LenFormulaReach {
    private String id;
    private String productId;
    private String produceFormulaId;
    private String produceFormulaDetailId;
    private String depotAudi;
    private String formulaBack;
    private String state;
    private String reachUser;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date reachTime;
    private String other1;
    private String other2;
    private String other3;
}
