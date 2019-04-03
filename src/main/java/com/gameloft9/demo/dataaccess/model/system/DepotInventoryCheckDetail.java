package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: Sxiu
 * @create: 2019/3/29 14:42
 * @description:
 */
@Data
public class DepotInventoryCheckDetail implements Serializable {

    private String id;

    private String checkId;

    private String type;

    private String goodsId;

    private String goodsNumber;

    private String checkUser;

    private Date checkTime;

    private String checkNumber;

}
