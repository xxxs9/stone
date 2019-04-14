package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.SysMaterialGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;


/**
 * @Author shizhengyu
 * @Date 2019/3/26 - 20:41
 * @Description:
 **/
public interface SysMaterialGoodsMapper {

    /**
     * 根据id获取所有原料供应商
     * @param start
     * @param end
     * @param ids
     * @return
     */
    List<SysMaterialGoods> getByIds(@Param("start") int start,
                                    @Param("end") int end,
                                    @Param("ids") Set<String> ids);

    /**
     * 根据id获取所有供应商
     * @param supplierIds
     * @return
     */
    List<String> getBySupplierIds(@Param("ids") List<String> supplierIds);

    /**
     * 根据id获取所有原料商品
     * @param materialIds
     * @return
     */
    List<String> getByMaterialIds(@Param("ids") List<String> materialIds);


    /**
     * 新增原料商品
     * @param record
     * @return
     */
    int insertSelective(SysMaterialGoods record);

    /**
     * 根据更新原料商品信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(SysMaterialGoods record);

    /**
     * 根据主键删除原料商品信息
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

    /**
     * 根据原料id获取原料商品信息
     * @param materialId
     * @return
     */
    SysMaterialGoods selectByMateralId(String materialId);

    /**
     * 根据主键获取原料商品信息
     * @param id
     * @return
     */
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

    /**
     * 查询所有
     * @return
     */
    List<SysMaterialGoods> getMaterialGoodsAll();

    /**
     * 根据供应商名称和原料名称获取原料商品id
     * @param goodsName 原料名称
     * @param supplierName 供应商名称
     */
    List<String> getIdByGoodsNameAndSupplierName(@Param("goodsName") String goodsName,@Param("supplierName")String supplierName);

    /**
     * 根据供应商名称获取该供应商提供原料商品名称
     * @param supplierName 供应商名称
     */
    List<String> getGoodsNameBySupplierName(@Param("supplierName") String supplierName);

    /**
     * 根据原料商品名称获取该原料商品的供应商名称
     * @param goodsName 原料商品名称
     */
    List<String> getSupplierNameByGoodsName(@Param("goodsName") String goodsName);

    /**
     * 根据原料商品id获取该原料商品的供应商名称
     * @param id 原料商品id
     */
    String getSupplierNameByGoodsId(@Param("id") String id);
}
