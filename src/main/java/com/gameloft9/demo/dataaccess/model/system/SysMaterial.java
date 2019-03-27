package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author shizhengyu
 * @Date 2019/3/26 - 20:17
 * @Description:
 **/
@Data
public class SysMaterial implements Serializable {

    private String id;

    private String goodsName;

    private String goodsType;

    private String goodsDescribe;

    private String goodsSpecification;
}
