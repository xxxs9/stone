package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

import java.io.Serializable;

/**
 * 供应商实体类
 * @Author shizhengyu
 * @Date 2019/3/26 - 16:50
 * @Description:
 **/

@Data
public class SysSupplier implements Serializable {

    private String id;

    private String supplierName;

    private String supplierDescribe;

    private String chargeName;

    private String phone;

    private String email;


}
