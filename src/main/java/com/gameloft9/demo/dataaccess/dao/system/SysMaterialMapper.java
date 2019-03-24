package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.SysMaterial;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMaterialMapper {

    /**
     * 获取货物类型列表
     * */
    List<String> getGoodsType();
    /**
     * 获取货物规格列表
     * */
    List<String> getGoodsSpecification();

    /**
     * @param start                 记录行的偏移量
     * @param end                   记录的最大数目
     * @param goodsName             货物名称
     * @param goodsType             货物类型
     * @param goodsSpecification    货物规格
     * */
    List<SysMaterial> getAll(@Param("start") int start,
                             @Param("end") int end,
                             @Param("goodsName") String goodsName,
                             @Param("goodsType") String goodsType,
                             @Param("goodsSpecification") String goodsSpecification);
    /**
     * 获取原料个数
     * @param goodsName             货物名称
     * @param goodsType             货物类型
     * @param goodsSpecification    货物规格
     * */
    int countGetAll(@Param("goodsName") String goodsName,
                    @Param("goodsType") String goodsType,
                    @Param("goodsSpecification") String goodsSpecification);

    /**
     * 新增原料种类
     * */
    int insertSelective(SysMaterial record);

    /**
     * 根据货品名获取原料
     * @param goodsName 货品名称
     */
    SysMaterial getByGoodsName(@Param("goodsName") String goodsName);
    /**
     * 根据主键获取原料信息
     * @param id 原料主键
     * */
    SysMaterial getById(@Param("id") String id);
    /**
     * 根据更新原料信息
     * @param record 原料
     * */
    int updateByPrimaryKeySelective(SysMaterial record);
    /**
     * 根据主键删除原料信息
     * @param id 原料id
     * */
    int deleteByPrimaryKey(String id);

    /**
     * 获取货物名称列表
     * */
    List<String> getGoodsName();
}
