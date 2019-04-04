package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.DepotOrder;
import com.gameloft9.demo.dataaccess.model.system.SysDepot;
import com.gameloft9.demo.mgrframework.annotation.BizOperLog;
import com.gameloft9.demo.mgrframework.beans.constant.OperType;

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
     * 添加采购入库单
     * @param orderNumber           订单编号
     * @param goodsId               原料/成品ID
     * @param goodsNumber           货品数量
     * @param applyUser             申请入
     * */
    String addPurorderDepotOrderIn(String orderNumber,String goodsId, String goodsNumber, String applyUser);

    /**
     * 添加采购退货单
     * @param orderNumber           订单编号
     * @param goodsId               原料/成品ID
     * @param goodsNumber           货品数量
     * @param applyUser             申请入
     * */
    String addPurorderDepotOrderOut(String orderNumber,String goodsId, String goodsNumber,String applyUser);

    /**
     * 添加销售出库单
     * @param orderNumber           订单编号
     * @param goodsId               原料/成品ID
     * @param goodsNumber           货品数量
     * @param applyUser             申请入
     * */
    String addMarketDepotOrderOut(String orderNumber,String goodsId, String goodsNumber,String applyUser);

    /**
     * 添加生产领料单
     * @param orderNumber           订单编号
     * @param goodsId               原料/成品ID
     * @param goodsNumber           货品数量
     * @param applyUser             申请入
     * */
    String addProduceDepotOrderOut(String orderNumber,String goodsId, String goodsNumber,String applyUser);

    /**
     * 添加生产入库单
     * @param orderNumber           订单编号
     * @param goodsId               原料/成品ID
     * @param goodsNumber           货品数量
     * @param applyUser             申请入
     * */
    String addProduceDepotOrderIn(String orderNumber,String goodsId, String goodsNumber,String applyUser);

    /**
     * 添加仓库单
     * @param id                    仓库单编号
     * @param orderType             仓库单类型
     * @param type                  出入库类型
     * @param goodsId               原料/成品ID
     * @param goodsNumber           货品数量
     * @param applyUser             申请人
     * */
    String addSysDepotOrder(String id,String orderType,String type,String goodsId,String goodsNumber,String applyUser);

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

    /**
     * 获取出库单信息,判断是否出库成功
     * @param id 仓库单主键
     * */
    Boolean isStorageOut(String id);

    /**
     * 获取入库单信息,判断是否入库成功
     * @param id 仓库单主键
     * */
    Boolean isStorageIn(String id);

    /**
     * 获取入库单信息,判断是否成功
     * @param id 仓库单主键
     * */
    Boolean isAuditPassIn(String id);

    /**
     * 获取出库单信息,判断是否审核成功
     * @param id 仓库单主键
     * */
    Boolean isAuditPassOut(String id);
}
