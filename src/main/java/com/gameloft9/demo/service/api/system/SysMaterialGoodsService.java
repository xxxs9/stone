package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.service.beans.system.MaterialGoodsResponse;

import java.util.List;


/**
 * @Author shizhengyu
 * @Date 2019/3/26 - 20:41
 * @Description:
 **/
public interface SysMaterialGoodsService {


    /**
     * 分页获取供应关系列表
     * @param page      页序
     * @param limit     分页大小
     * @param supplierName      供应商名称
     * @param goodsName     货物名称
     * @param goodsType     货物类型
     * @param goodsSpecification    货物规格
     * @return
     */
    List<MaterialGoodsResponse> getAll(String page, String limit, String supplierName, String goodsName, String goodsType, String goodsSpecification);


    /**
     * 分页获取供应关系列表
     * @param supplierName  供应商名称
     * @param goodsName     货物名称
     * @param goodsType     货物类型
     * @param goodsSpecification    货物规格
     * @return
     */
    int countGetAll(String supplierName, String goodsName, String goodsType, String goodsSpecification);

    /**
     * 添加供应关系种类
     * @param supplierName      供应商名称
     * @param goodsName     货物名称
     * @param goodsPrice    货物价格
     * @param goodsOriginPlace      货物原产地
     * @param imageUrl      货物图片地址
     * @return
     */
    String addMaterialGoods(String supplierName, String goodsName, String goodsPrice, String goodsOriginPlace, String imageUrl);

    /**
     * 根据主键获取供应关系信息
     * @param id
     * @return
     */
    MaterialGoodsResponse getById(String id);

    /**
     * 更新原料种类
     * @param id        供应关系ID
     * @param supplierName      供应商名称
     * @param goodsName     货物名称
     * @param goodsPrice    货物价格
     * @param goodsOriginPlace  货物原产地
     * @param imageUrl      货物图片地址
     * @return
     */
    Boolean updateMaterialGoods(String id, String supplierName, String goodsName, String goodsPrice, String goodsOriginPlace, String imageUrl);

    /**
     * 删除
     * @param id
     * @return
     */
    Boolean deleteMaterialGoods(String id);

    /**
     * 根据获取原料商品id信息
     * */
    List<String> getMaterialGoodsId();
}
