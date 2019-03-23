package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

/**
 * MaterialGoods 实体类
 * @author OliverCH
 * @date 2019/03/19
 */
@Data
public class MaterialGoods {
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
