package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.SysMaterial;
import com.gameloft9.demo.dataaccess.model.system.SysMaterialGoods;
import com.gameloft9.demo.dataaccess.model.system.SysMenuTest;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface SysMaterialGoodsMapper {

    /**
     * @param start                 记录行的偏移量
     * @param end                   记录的最大数目
     * @param ids 主键id集
     */
    List<SysMaterialGoods> getByIds(@Param("start") int start,
                                    @Param("end") int end,
                                    @Param("ids") Set<String> ids);

    /**
     *
     * @param supplierIds 供应商id集
     */
    List<String> getBySupplierIds(@Param("ids") List<String> supplierIds);

    /**
     *
     * @param materialIds 资源id集
     */
    List<String> getByMaterialIds(@Param("ids") List<String> materialIds);


    /**
     * 新增原料商品
     * @param  record 原料商品
     * */
    int insertSelective(SysMaterialGoods record);
    /**
     * 根据更新原料商品信息
     * @param record 原料商品
     * */
    int updateByPrimaryKeySelective(SysMaterialGoods record);
    /**
     * 根据主键删除原料商品信息
     * @param id 原料商品id
     * */
    int deleteByPrimaryKey(String id);
    /**
     * 根据原料id获取原料商品信息
     * @para  materialId    原料id
     */
    SysMaterialGoods selectByMateralId(String materialId);
    /**
     * 根据主键获取原料商品信息
     * @param id 原料商品主键
     * */
    SysMaterialGoods getById(@Param("id") String id);
    /**
     * 根据主键获取原料商品信息
     * @param materialId 原料主键
     * @param supplierId 供应商主键
     * */
    SysMaterialGoods getByOtherId(@Param("materialId") String materialId, @Param("supplierId") String supplierId);


    /**
     * 根据获取原料商品id信息
     * */
    List<String> getMaterialGoodsId();
}
