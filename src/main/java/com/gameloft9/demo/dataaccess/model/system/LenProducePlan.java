package com.gameloft9.demo.dataaccess.model.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author : Lennon_Yuan
 * @time : 2019-03-23 - 10:52
 * @description :生产计划实体类
 */
@Data
public class LenProducePlan {
    private String id ;
    private String productId;
    /**
     * 计划产量
     */
    private String planNumber;
    private String realNumber;

    /**
     * 现有库存
     */
    private String goodNumber;
    /**
     * 订单周期
     */
    private String billCycle;
    /**
     * 状态（是否外包）
     */
    private String state;
    /**
     * 备注
     */
    private String planRemark;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date produceDate;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date finishDate;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date billDate;

    private String other1;
    private String other2;
    private String other3;

}
