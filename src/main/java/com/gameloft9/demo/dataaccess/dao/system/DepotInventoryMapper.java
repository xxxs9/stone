package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.DepotInventory;
import com.gameloft9.demo.dataaccess.model.system.SysDepot;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepotInventoryMapper {

    /**
     * 获取所有库存数据
     * @param start                 记录行的偏移量
     * @param end                   记录的最大数目
     * @param type                  货品(原料/成品）
     * @param goodsId               原料/成品ID
     * */
    List<DepotInventory> getAll(@Param("start") int start,
                                @Param("end") int end,
                                @Param("type") String type,
                                @Param("goodsId") String goodsId);

    /**
     * 获取库存个数
     * @param type                  货品(原料/成品）
     * @param goodsId               原料/成品ID
     * */
    int countGetAll(@Param("type") String type,
                     @Param("goodsId") String goodsId);

    /**
     * 新增库存
     * @param record 库存
     * */
    int insertSelective(DepotInventory record);

    /**
     * 根据主键获取库存信息
     * @param id 库存主键
     * */
    DepotInventory getById(@Param("id") String id);

    /**
     * 根据更新库存信息
     * @param record 库存
     * */
    int updateByPrimaryKeySelective(DepotInventory record);

    /**
     * 根据主键删除库存信息
     * @param id 库存id
     * */
    int deleteByPrimaryKey(String id);


    /**
     * 根据主键批量删除库存信息
     * @param ids 库存ids
     * */
    int delsByIds(@Param("ids") List<String> ids);

    /**
     * 查询是否有匹配库存信息
     * @param goodsId               原料/成品ID
     * */
    DepotInventory findOne(@Param("goodsId")String goodsId);

    /**
     * 根据ids查询库存信息
     * @param ids 库存ids
     * */
    List<DepotInventory> getByIds(@Param("ids")List<String> ids);
}
