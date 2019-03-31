package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.DepotOrder;
import com.gameloft9.demo.dataaccess.model.system.SysDepot;

import java.util.List;

public interface DepotOrderService {
    /**
     * 获取仓库单列表
     * @param page                  页序
     * @param limit                 分页大小
     * @param orderType             仓库单类型
     * @param type                  出入库类型
     * @param goodsId               原料/成品ID
     * @param applyUser             申请人
     */
    List<DepotOrder> getAll(String page, String limit, String orderType,String type, String goodsId, String applyUser);


    /**
     * 获取仓库单个数
     * @param orderType             仓库单类型
     * @param type                  出入库类型
     * @param goodsId               原料/成品ID
     * @param applyUser             申请人
     * */
    int countGetAll(String orderType,String type,String goodsId,String applyUser);

    /**
     * 添加仓库单
     * @param orderType             仓库单类型
     * @param type                  出入库类型
     * @param goodsId               原料/成品ID
     * @param goodsNumber           货品数量
     * @param applyUser             申请人
     * */
    String addDepotOrder(String orderType,String type,String goodsId,String goodsNumber,String applyUser);

    /**
     * 根据主键获取仓库单信息
     * @param id 仓库单主键
     * */
    DepotOrder getById(String id);

    /**
     * 审核通过,更新出库单
     * @param id                    仓库单id
     * @param state                 仓库单状态
     * @param orderAuditUser        审核人
     * @param auditDescribe         审核描述
     * */
    Boolean auditPassDepotOrderOut(String id,String state,String orderAuditUser, String auditDescribe);

    /**
     * 审核通过,更新入库单
     * @param id                    仓库单id
     * @param state                 仓库单状态
     * @param orderAuditUser        审核人
     * @param auditDescribe         审核描述
     * */
    Boolean auditPassDepotOrderIn(String id,String state,String orderAuditUser, String auditDescribe);

    /**
     * 审核驳回,更新入库单
     * @param id                    仓库单id
     * @param state                 仓库单状态
     * @param orderAuditUser        审核人
     * @param auditDescribe         审核描述
     * */
    Boolean auditRejectDepotOrderIn(String id,String state,String orderAuditUser, String auditDescribe);

    /**
     * 审核驳回,更新出库单
     * @param id                    仓库单id
     * @param state                 仓库单状态
     * @param orderAuditUser        审核人
     * @param auditDescribe         审核描述
     * */
    Boolean auditRejectDepotOrderOut(String id,String state,String orderAuditUser, String auditDescribe);

    /**
     *入库,更新入库单
     * @param id                    仓库单id
     * @param state                 仓库单状态
     * */
    Boolean storageInDepotOrderIn(String id,String state);

    /**
     *出库,更新出库单
     * @param id                    仓库单id
     * @param state                 仓库单状态
     * */
    Boolean storageInDepotOrderOut(String id,String state);

    /**
     * 删除仓库单
     * @param id 仓库id
     * */
    Boolean deleteDepotOrder(String id);

    /**
     * 删除仓库单
     * @param ids 仓库ids
     * */
    Boolean delsDepotOrder(String ids);

    /**
     * 获取入库单类型
     * @param orderType             仓库单类型
     * */
    List<String> getDepotOrderInType(String orderType);

}
