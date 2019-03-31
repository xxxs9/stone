package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.DepotInventoryCheck;
import com.gameloft9.demo.dataaccess.model.system.SysDepot;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepotInventoryCheckMapper {

    /**
     * 获取所有盘点单数据
     * @param start                 记录行的偏移量
     * @param end                   记录的最大数目
     * @param sourceUser            发起人
     * @param checkType             盘点类型
     * @param checkState            盘点状态
     * */
    List<DepotInventoryCheck> getAll(@Param("start") int start,
                                     @Param("end") int end,
                                     @Param("sourceUser") String sourceUser,
                                     @Param("checkType") String checkType,
                                     @Param("checkState") String checkState);

    /**
     * 获取盘点单个数
     * @param sourceUser            发起人
     * @param checkType             盘点类型
     * @param checkState            盘点状态
     * */
    int countGetAll( @Param("sourceUser") String sourceUser,
                     @Param("checkType") String checkType,
                     @Param("checkState") String checkState);

    /**
     * 新增盘点单
     * @param record 盘点单
     * */
    int insertSelective(DepotInventoryCheck record);

    /**
     * 根据主键获取盘点单信息
     * @param id 盘点单主键
     * */
    DepotInventoryCheck getById(@Param("id") String id);

    /**
     * 根据更新盘点单信息
     * @param record 盘点单
     * */
    int updateByPrimaryKeySelective(DepotInventoryCheck record);

    /**
     * 根据主键删除盘点单信息
     * @param id 盘点单id
     * */
    int deleteByPrimaryKey(@Param("id")String id);

    /**
     * 根据主键批量删除盘点单信息
     * @param ids 盘点单ids
     * */
    int delsByIds(@Param("ids") List<String> ids);
}
