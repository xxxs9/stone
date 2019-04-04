package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.DepotOrder;
import com.gameloft9.demo.dataaccess.model.system.SysDepot;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepotOrderMapper {
    /**
     * 获取所有仓库单数据
     * @param start                 记录行的偏移量
     * @param end                   记录的最大数目
     * @param orderType             仓库单类型
     * @param type                  出入库类型
     * @param goodsId               原料/成品ID
     * @param applyUser             申请人
     * */
    List<DepotOrder> getAll(@Param("start") int start,
                            @Param("end") int end,
                            @Param("orderType") String orderType,
                            @Param("type") String type,
                            @Param("goodsId") String goodsId,
                            @Param("applyUser") String applyUser);

    /**
     * 获取仓库单个数
     * @param orderType             仓库单类型
     * @param type                  出入库类型
     * @param goodsId               原料/成品ID
     * @param applyUser             申请人
     * */
    int countGetAll( @Param("orderType") String orderType,
                     @Param("type") String type,
                     @Param("goodsId") String goodsId,
                     @Param("applyUser") String applyUser);

    /**
     * 新增仓库单
     * @param record 仓库
     * */
    int insertSelective(DepotOrder record);

    /**
     * 根据主键获取仓库单信息
     * @param id 仓库单主键
     * */
    DepotOrder getById(@Param("id") String id);

    /**
     * 根据更新仓库单信息
     * @param record 仓库单
     * */
    int updateByPrimaryKeySelective(DepotOrder record);

    /**
     * 根据主键删除仓库单信息
     * @param id 仓库单id
     * */
    int deleteByPrimaryKey(String id);

    /**
     * 根据主键批量删除仓库单信息
     * @param ids 仓库单ids
     * */
    int delsByIds(@Param("ids") List<String> ids);

    /**
     * 获取入库单类型
     * @param orderType             仓库单类型
     * */
    List<String> getDepotOrderInType(@Param("orderType")String orderType);
}
