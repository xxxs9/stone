package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.AllRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * PurchaseOrder dao层
 * @author OliverCH
 * @date 2019/04/01
 */
public interface AllRoleMapper {

    /**通知部门
     * @param limit 限制
     * @return List<AllRole> 返回值类型*/
    List<AllRole> selectNotify(
            @Param("limit") String limit);

    /**供销商部门
     * @param limit 限制
     * @return List<AllRole> 返回值类型*/
    List<AllRole> selectSupplier(
            @Param("limit") String limit);

    /**销售部门
     * @param limit 限制
     * @return List<AllRole> 返回值类型*/
    List<AllRole> selectSale(
            @Param("limit") String limit);

    /**采购部门
     * @param limit 限制
     * @return List<AllRole> 返回值类型*/
    List<AllRole> selectProOrder(
            @Param("limit") String limit);

    /**仓库部门
     * @param limit 限制
     * @return List<AllRoleMapper> 返回值类型*/
    List<AllRole> selectDepot(
            @Param("limit") String limit);

    /**生产部门
     * @param limit 限制
     * @return List<AllRole> 返回值类型*/
    List<AllRole> selectProduct(
            @Param("limit") String limit);

    /**财务部门
     * @param limit 限制
     * @return List<AllRole> 返回值类型*/
    List<AllRole> selectFinance(
            @Param("limit") String limit);

}
