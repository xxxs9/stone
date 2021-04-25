package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Sxiu
 * @create: 2019/3/29 14:44
 * @description:
 */
@Data
public class DepotInventoryCheck implements Serializable {

    private String id;

    private String checkType;

    private String sourceUser;

    private String sourceTime;

    private String recordNumber;

    private String checkState;

    private String state;
}
