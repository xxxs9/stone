package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysDepot implements Serializable {
    /**
     *  id 主键
     */
    private String id;
    /**
     * depotName 仓库名
     */
    private String depotName;
    /**
     * depotNumber 仓库编号
     */
    private String depotNumber;
    /**
     * depotType 仓库类型
     */
    private String depotType;
    /**
     * depotAddress 仓库地址
     */
    private String depotAddress;
    /**
     * depotDescribe 仓库描述
     */
    private String depotDescribe;
}
