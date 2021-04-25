package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.SysMaterial;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author shizhengyu
 * @Date 2019/3/26 - 20:41
 * @Description:
 **/
public interface SysMaterialService {

    /**
     * 获取货物名称列表
     * @return
     */
    List<String> getGoodsName();

    /**
     * 获取货物类型列表
     * @return
     */
    List<String> getGoodsType();

    /**
     * 获取货物规格列表
     * @return
     */
    List<String> getGoodsSpecification();

    /**
     * 分页获取原料列表
     * @param page
     * @param limit
     * @param goodsName
     * @param goodsType
     * @param goodsSpecification
     * @return
     */
    List<SysMaterial> getAll(String page, String limit, String goodsName, String goodsType, String goodsSpecification);

    /**
     * 获取原料列表大小
     * @param goodsName
     * @param goodsType
     * @param goodsSpecification
     * @return
     */
    int countGetAll(String goodsName, String goodsType, String goodsSpecification);

    /**
     * 添加原料种类
     * @param goodsName
     * @param goodsType
     * @param goodsDescribe
     * @param goodsSpecification
     * @return
     */
    String addMaterial(String goodsName, String goodsType, String goodsDescribe, String goodsSpecification);

    /**
     * 根据主键获取原料信息
     * @param id
     * @return
     */
    SysMaterial getById(String id);

    /**
     * 更新原料种类
     * @param id
     * @param goodsName
     * @param goodsType
     * @param goodsDescribe
     * @param goodsSpecification
     * @return
     */
    Boolean updateMaterial(String id, String goodsName, String goodsType, String goodsDescribe, String goodsSpecification);

    /**
     * 删除原料
     * @param materialId
     * @return
     */
    Boolean deleteMaterial(String materialId);

    /**
     * 根据货物名称获取原料信息
     * @param goodsName
     * @return
     */
    SysMaterial getByGoodsName(String goodsName);
}
