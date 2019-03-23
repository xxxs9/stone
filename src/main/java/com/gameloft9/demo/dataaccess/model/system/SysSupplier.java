package com.gameloft9.demo.dataaccess.model.system;

import com.sun.xml.internal.bind.v2.model.core.ID;
import lombok.Data;

import java.io.Serializable;

@Data
public class SysSupplier implements Serializable {

    private String id;

    private String supplierName;

    private String supplierDescribe;

    private String phone;
}

