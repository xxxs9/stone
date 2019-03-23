package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.DepotPersonnel;
import com.gameloft9.demo.dataaccess.model.system.SysDepot;

import java.util.List;

public interface DepotPersonnelService {
    /**
     * 获取仓库人员列表
     * @param page                  页序
     * @param limit                 分页大小
     * @param depotNumber           仓库编号
     * @param userLoginName         用户登入名
     */
    List<DepotPersonnel> getAll(String page, String limit, String depotNumber, String userLoginName);


    /**
     * 获取仓库人员列表大小
     * @param depotNumber           仓库编号
     * @param userLoginName         用户登入名
     * */
    int countGetAll(String depotNumber,String userLoginName);

    /**
     * 添加仓库人员
     * @param depotNumber           仓库编号
     * @param userLoginName         用户登入名
     * @param contactAddress        联系地址
     * @param birthday              出生日期
     * @param personPortrait        人员头像
     * */
    String addDepotPersonnel(String depotNumber,String userLoginName,String contactAddress,String birthday,String personPortrait);

    /**
     * 根据主键获取仓库人员信息
     * @param id 仓库人员主键
     * */
    DepotPersonnel getById(String id);

    /**
     * 更新仓库人员信息
     * @param id                    仓库id
     * @param depotNumber           仓库编号
     * @param userLoginName         用户登入名
     * @param contactAddress        联系地址
     * @param birthday              出生日期
     * @param personPortrait        人员头像
     * */
    Boolean updateDepotPersonnel(String id,String depotNumber,String userLoginName, String contactAddress, String birthday,String personPortrait);
    /**
     * 删除仓库人员
     * @param id 仓库id
     * */
    Boolean deleteDepotPersonnel(String id);

    /**
     * 批量删除仓库人员
     * @param ids 仓库ids
     * */
    Boolean delsDepotPersonnel(String ids);

    /**
     * 获取所有仓库人员的用户登入名
     * */
    List<String> getDepotUserLoginName();
}
