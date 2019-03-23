package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.DepotPersonnel;
import com.gameloft9.demo.dataaccess.model.system.SysDepot;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepotPersonnelMapper {
    /**
     * 获取所有仓库人员数据
     * @param start                 记录行的偏移量
     * @param end                   记录的最大数目
     * @param depotNumber           仓库编号
     * @param userLoginName         用户登入名
     * */
    List<DepotPersonnel> getAll(@Param("start") int start,
                          @Param("end") int end,
                          @Param("depotNumber") String depotNumber,
                          @Param("userLoginName") String userLoginName);

    /**
     * 获取仓库人员个数
     * @param depotNumber           仓库编号
     * @param userLoginName           用户登入名
     * */
    int countGetAll( @Param("depotNumber") String depotNumber,
                     @Param("userLoginName") String userLoginName);

    /**
     * 新增仓库
     * @param record 仓库人员
     * */
    int insertSelective(DepotPersonnel record);

    /**
     * 根据主键获取仓库人员信息
     * @param id 仓库人员主键
     * */
    DepotPersonnel getById(@Param("id") String id);

    /**
     * 根据更新仓库人员信息
     * @param record 仓库人员
     * */
    int updateByPrimaryKeySelective(DepotPersonnel record);

    /**
     * 根据主键删除仓库人员信息
     * @param id 仓库人员id
     * */
    int deleteByPrimaryKey(String id);

    /**
     * 根据仓库编号获取仓库人员信息
     * @param depotNumber depotNumber
     * */
    DepotPersonnel getByDepotNumber(String depotNumber);

    /**
     * 根据主键批量删除仓库人员信息
     * @param ids 仓库人员ids
     * */
    int delsByIds(@Param("ids") List<String> ids);

    /**
     * 根据用户登入名获取仓库人员信息
     * @param userLoginName 用户登入名
     * */
    DepotPersonnel getByUserLoginName(@Param("userLoginName") String userLoginName);
}
