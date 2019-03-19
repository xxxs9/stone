package com.gameloft9.demo.dataaccess.dao.system;
import com.gameloft9.demo.dataaccess.model.system.SysDepot;
import com.gameloft9.demo.dataaccess.model.system.SysMenuTest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Sxiu
 */
public interface SysDepotMapper {

    /**
     * 获取所有仓库数据
     * @param start                 记录行的偏移量
     * @param end                   记录的最大数目
     * @param depotNumber           仓库编号
     * @param depotName             仓库名称
     * @param depotType             仓库类型
     * */
    List<SysDepot> getAll(@Param("start") int start,
                          @Param("end") int end,
                          @Param("depotNumber") String depotNumber,
                          @Param("depotName") String depotName,
                          @Param("depotType") String depotType);

    /**
     * 获取仓库个数
     * @param depotNumber           仓库编号
     * @param depotName             仓库名称
     * @param depotType             仓库类型
     * */
    int countGetAll( @Param("depotNumber") String depotNumber,
                     @Param("depotName") String depotName,
                     @Param("depotType") String depotType);

    /**
     * 新增仓库
     * @param record 仓库
     * */
    int insertSelective(SysDepot record);

    /**
     * 根据主键获取仓库信息
     * @param id 仓库主键
     * */
    SysDepot getById(@Param("id") String id);

    /**
     * 根据更新原料信息
     * @param record 仓库
     * */
    int updateByPrimaryKeySelective(SysDepot record);

    /**
     * 根据主键删除仓库信息
     * @param id 仓库id
     * */
    int deleteByPrimaryKey(String id);

    /**
     * 根据仓库名获取仓库信息
     * @param depotName 仓库名称depotName
     * */
    SysDepot getByDepotName(String depotName);

    /**
     * 获取仓库类型
     * */
    List<String> getDepotType();

    /**
     * 根据仓库编号获取仓库信息
     * @param depotName 仓库名称depotName
     * */
    SysDepot getByDepotNumber(String depotName);

    /**
     * 根据主键批量删除仓库信息
     * @param ids 仓库ids
     * */
    int delsByIds(@Param("ids") List<String> ids);

}
