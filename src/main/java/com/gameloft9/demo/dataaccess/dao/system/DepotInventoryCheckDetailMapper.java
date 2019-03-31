package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.DepotInventoryCheck;
import com.gameloft9.demo.dataaccess.model.system.DepotInventoryCheckDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepotInventoryCheckDetailMapper {

    /**
     * 获取盘点单明细记录数据
     * @param start                 记录行的偏移量
     * @param end                   记录的最大数目
     * @param checkId               盘点单ID
     * */
    List<DepotInventoryCheckDetail> getAll(@Param("start") int start,
                                           @Param("end") int end,
                                           @Param("checkId") String checkId);

    /**
     * 获取盘点单明细记录数据条数
     * @param checkId               盘点单ID
     * */
    int countGetAll( @Param("checkId") String checkId);

    /**
     * 新增盘点单明细
     * @param record 盘点单明细
     * */
    int insertSelective(DepotInventoryCheckDetail record);

    /**
     * 根据更新盘点单信息
     * @param record 盘点单
     * */
    int updateByPrimaryKeySelective(DepotInventoryCheckDetail record);

    /**
     * 根据主键获取盘点单明细信息
     * @param id 盘点单明细主键
     * */
    DepotInventoryCheckDetail getById(@Param("id") String id);

    /**
     * 根据主键删除盘点单明细信息
     * @param id 盘点单明细id
     * */
    int deleteByPrimaryKey(@Param("id")String id);

    /**
     * 根据主键批量删除盘点单明细信息
     * @param ids 盘点单ids
     * */
    int delsByIds(@Param("ids") List<String> ids);
}
