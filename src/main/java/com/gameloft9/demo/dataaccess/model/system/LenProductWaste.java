package com.gameloft9.demo.dataaccess.model.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author: Lennon_Yuan
 * @time: 2019/3/18 0018-下午 2:10
 * @description: 生产部废料实体
 */
@Data
public class LenProductWaste  {
    private String id;
    /**
     * 废料数量
     */
    private String wasteNumber;
    /**
     * 废料备注
     */
    private String wasteRemark;
    /**
     * 废料状态
     */
    private String state;
    /**
     * 产品配料ID
     */
    private String produceFormulaId;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date wasteTime;

    private String other1;
    private String other2;
    private String other3;

}

