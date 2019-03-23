package com.gameloft9.demo.dataaccess.model.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: Sxiu
 * @create: 2019/3/20 13:58
 * @description:仓库人员的实体类
 */
@Data
public class DepotPersonnel implements Serializable {

    private String id;

    private String depotNumber;

    private String userLoginName;

    private String contactAddress;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date birthday;

    private String personPortrait;
}
