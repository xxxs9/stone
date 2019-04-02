package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.DepotInventoryCheck;
import com.gameloft9.demo.dataaccess.model.system.SysDepot;

import java.util.List;

public interface DepotInventoryCheckService {

    /**
     * 获取盘点单列表
     * @param page                  页序
     * @param limit                 分页大小
     * @param sourceUser            发起人
     * @param checkType             盘点类型
     * @param checkState            盘点状态
     */
    List<DepotInventoryCheck> getAll(String page, String limit, String sourceUser, String checkType, String checkState);


    /**
     * 获取盘点单列表大小
     * @param sourceUser            发起人
     * @param checkType             盘点类型
     * @param checkState            盘点状态
     * */
    int countGetAll(String sourceUser,String checkType,String checkState);

    /**
     * 添加盘点单
     * @param checkType           盘点类型
     * @param sourceUser          发起人
     * @param recordNumber        记录数量
     * */
    String addDepotInventoryCheck(String checkType,String sourceUser,String recordNumber);

    /**
     * 根据主键获取盘点单信息
     * @param id 盘点单主键
     * */
    DepotInventoryCheck getById(String id);

    /**
     * 根据主键删除盘点单信息
     * @param id 盘点单id
     * */
    Boolean deleteDepotInventoryCheck(String id);

    /**
     * 根据主键批量删除盘点单信息
     * @param ids 盘点单ids
     * */
    Boolean delsDepotInventoryCheck(String ids);


    /**
     * 结束盘点单
     * @param id                  盘点单id
     * */
    Boolean endDepotInventoryCheck(String id);

    /**
     * 审核通过,更新出库单
     * @param id                  盘点单id
     * @param state               盘点单状态
     * */
    Boolean audit(String id, String state);


    /**
     * 审核驳回,更新出库单
     * @param id                    仓库单id
     * @param state                 仓库单状态
     * @param auditDescribe         审核描述
     * @param orderAuditUser        审核人
     * */
    Boolean auditReject(String id, String state);
}
