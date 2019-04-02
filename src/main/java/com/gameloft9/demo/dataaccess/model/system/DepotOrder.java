package com.gameloft9.demo.dataaccess.model.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: Sxiu
 * @create: 2019/3/27 16:06
 * @description:
 */

@Data
public class DepotOrder implements Serializable {

    private String id;

    private String orderType;

    private String type;

    private String goodsId;

    private String goodsNumber;

    private String applyUser;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date applyTime;

    private String state;

    private String orderAuditUser;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderAuditTime;

    private String auditDescribe;
}
