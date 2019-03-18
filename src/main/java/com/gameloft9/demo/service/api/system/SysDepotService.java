package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.SysDepot;

import java.util.List;

public interface SysDepotService {

    /**
     * 获取仓库列表
     * @param page                  页序
     * @param limit                 分页大小
     * @param depotNumber           仓库编号
     * @param depotName             仓库名称
     * @param depotType             仓库类型
     */
    List<SysDepot> getAll(String page, String limit,String depotNumber,String depotName,String depotType);

    /**
     * 添加仓库种类
     * @param depotNumber           仓库编号
     * @param depotName             仓库名
     * @param depotType             仓库类型
     * @param depotAddress          仓库地址
     * @param depotDescribe         仓库描述
     * */
    String addDepot(String depotNumber,String depotName,String depotType,String depotAddress,String depotDescribe);

    /**
     * 根据主键获取仓库信息
     * @param id 仓库主键
     * */
    SysDepot getById(String id);

    /**
     * 更新仓库种类
     * @param id                    仓库id
     * @param depotNumber           仓库编号
     * @param depotName             仓库名
     * @param depotType             仓库类型
     * @param depotAddress          仓库地址
     * @param depotDescribe         仓库描述
     * */
    Boolean updateDepot(String id,String depotNumber,String depotName, String depotType, String depotAddress, String depotDescribe);
    /**
     * 删除仓库
     * @param id 仓库id
     * */
    Boolean deleteDepot(String id);
}
