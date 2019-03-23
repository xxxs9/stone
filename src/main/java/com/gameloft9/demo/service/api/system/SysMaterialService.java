package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.SysMaterial;
import com.gameloft9.demo.service.beans.system.MaterialGoodsResponse;

import java.util.List;

public interface SysMaterialService {

    /**
     * 获取货物名称列表
     * */
    List<String> getGoodsName();

    /**
     * 获取货物类型列表
     * */
    List<String> getGoodsType();

    /**
     * 获取货物规格列表
     * */
    List<String> getGoodsSpecification();

    /**
     * 分页获取原料列表
     * @param page                  页序
     * @param limit                 分页大小
     * @param goodsName             货物名称
     * @param goodsType             货物类型
     * @param goodsSpecification    货物规格
     */
    List<SysMaterial> getAll(String page, String limit, String goodsName, String goodsType, String goodsSpecification);

    /**
     * 获取原料列表大小
     * @param goodsName             货物名称
     * @param goodsType             货物类型
     * @param goodsSpecification    货物规格
     * */
    int countGetAll(String goodsName, String goodsType, String goodsSpecification);

    /**
     * 添加原料种类
     * @param goodsName             货物名称
     * @param goodsType             货物类型
     * @param goodsDescribe         货物描述
     * @param goodsSpecification    货物规格
     * */
    String addMaterial(String goodsName, String goodsType, String goodsDescribe, String goodsSpecification);
    /**
     * 根据主键获取原料信息
     * @param id 原料主键
     * */
    SysMaterial getById(String id);
    /**
     * 更新原料种类
     * @param id                    货物ID
     * @param goodsName             货物名称
     * @param goodsType             货物类型
     * @param goodsDescribe         货物描述
     * @param goodsSpecification    货物规格
     * */
    Boolean updateMaterial(String id, String goodsName, String goodsType, String goodsDescribe, String goodsSpecification);
    /**
     * 删除菜单
     * @param materialId 原料materialId
     * */
    Boolean deleteMaterial(String materialId);
    /**
     * 根据货物名称获取原料信息
     * @param goodsName 货物名称
     * */
    SysMaterial getByGoodsName(String goodsName);
}
