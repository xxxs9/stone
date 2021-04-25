package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.SysMaterial;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author shizhengyu
 * @Date 2019/3/26 - 20:06
 * @Description:
 **/
public interface SysMaterialMapper {

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
     * @param goodsName  货物名称
     * @param goodsType     货物类型
     * @param goodsSpecification    货物规格
     * @return
     */
    int countGetAll(@Param("goodsName") String goodsName,
                    @Param("goodsType") String goodsType,
                    @Param("goodsSpecification") String goodsSpecification);

    /**
     * 新增原料种类
     * @param record
     * @return
     */
    int insertSelective(SysMaterial record);


    /**
     * 根据货品名获取原料
     * @param goodsName
     * @return
     */
    SysMaterial getByGoodsName(@Param("goodsName") String goodsName);

    /**
     * 根据主键获取原料信息
     * @param id
     * @return
     */
    SysMaterial getById(@Param("id") String id);

    /**
     * 根据更新原料信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(SysMaterial record);

    /**
     * 根据主键删除原料信息
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

    /**
     * 获取货物名称列表
     * @return
     */
    List<String> getGoodsName();
}
