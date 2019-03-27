package com.gameloft9.demo.dataaccess.model.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author : Lennon_Yuan
 * @time : 2019-03-25 - 00:25
 * @description :
 */

@Data
public class LenProductInfo {
    private String id;
    private String productId;
    private String produceFormulaId;
    private String produceFormulaDetailId;
    private String productName;
    private String state;
    private String productType;
    private String formulaId;
    private String formulaType;
    private String formulaNumber;
    private String createUser;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date createTime;
    private String materialId;
    private String materialNumber;
    private String depotId;
    private String supportPrice;
}
