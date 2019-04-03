package com.gameloft9.demo.dataaccess.model.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;

/**
 * @author: Lennon_Yuan
 * @time: 2019/3/18 0018-下午 2:30
 * @description:
 */
@Data
public class LenCreateUserInfo {
    private String id;
    private String createUser;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createTime;

    private String employeeId;
    private String other1;
    private String other2;
    private String other3;

}

