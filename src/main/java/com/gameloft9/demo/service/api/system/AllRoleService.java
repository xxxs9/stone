package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.AllRole;

import java.util.List;

/**
 * PurchaseOrder service接口
 * @author OliverCH
 * @date 2019/04/01
 */
public interface AllRoleService {

    /**通知部门
     * @param limit 限制
     * @return List<AllRole> 返回值*/
    List<AllRole> selectNotify(String limit);

    /**供销商部门
     * @param limit 限制
     * @return List<AllRole> 返回值*/
    List<AllRole> selectSupplier(String limit);

    /**销售部门
     * @param limit 限制
     * @return List<AllRole> 返回值*/
    List<AllRole> selectSale(String limit);

    /**采购部门
     * @param limit 限制
     * @return List<AllRole> 返回值*/
    List<AllRole> selectProOrder(String limit);

    /**仓库部门
     * @param limit 限制
     * @return List<AllRole> 返回值*/
    /*List<AllRole> selectDepot(String limit);*/

    /**生产部门
     * @param limit 限制
     * @return List<AllRole> 返回值*/
    /*List<AllRole> selectProduct(String limit);*/

    /**财务部门
     * @param limit 限制
     * @retun List<AllRole> 返回值*/
    List<AllRole> selectFinance(String limit);
}

