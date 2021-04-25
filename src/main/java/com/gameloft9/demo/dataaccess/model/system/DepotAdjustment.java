package com.gameloft9.demo.dataaccess.model.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: Sxiu
 * @create: 2019/3/21 10:00
 * @description:
 */
@Data
public class DepotAdjustment implements Serializable {

    private String id;

    private String documentNumber;

    private String depotNumber;

    private String adjustmentType;

    private String totalNumber;

    private String originator;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creationTime;

    private String auditUser;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date auditTime;

    private String remarks;
}
