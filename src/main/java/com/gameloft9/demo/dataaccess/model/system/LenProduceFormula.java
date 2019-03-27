package com.gameloft9.demo.dataaccess.model.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gameloft9.demo.utils.DateUtil;
import com.gameloft9.demo.utils.lennonUtils.LenSuperClass;
import lombok.Data;

import java.util.Date;

/**
 * @author: Lennon_Yuan
 * @time: 2019/3/18 0018-下午 2:20
 * @description: 生产配方
 */
@Data
public class LenProduceFormula  {
    /***/
    private String id;
    private String productId;
    private String formulaType;
    private String formulaNumber;
    private String createUser;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createTime;
    private String other1;
    private String other2;
    private String other3;

}
