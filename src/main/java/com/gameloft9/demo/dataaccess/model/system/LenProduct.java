package com.gameloft9.demo.dataaccess.model.system;

import com.gameloft9.demo.utils.lennonUtils.LenSuperClass;
import lombok.Data;

/**
 * @author: Lennon_Yuan
 * @time: 2019/3/18 0018-下午 2:01
 * @description: 产品的实体类
 */
@Data
public class LenProduct extends LenSuperClass {
    private String id;
    private String productName;
    private String productType;
    private String state;
    private String productDescribe;
    private String wasteId;

}
