package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysMaterial implements Serializable {

    private String id;

    private String goodsName;

    private String goodsType;

    private String goodsDescribe;

    private String goodsSpecification;
}
