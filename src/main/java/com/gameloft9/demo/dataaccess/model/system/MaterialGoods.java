package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author shizhengyu
 * @Date 2019/3/26 - 16:44
 * @Description:
 **/

@Data
public class MaterialGoods implements Serializable {

    private String id;
    private String supplierId;
    private String materialId;
    private String goodsPrice;

    @Override
    public String toString() {
        return "MaterialGoods{" +
                "id='" + id + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", materialId='" + materialId + '\'' +
                ", goodsPrice='" + goodsPrice + '\'' +
                '}';
    }
}
