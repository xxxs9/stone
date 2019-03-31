package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.SysSupplier;

import java.util.List;

/**
 * @Author shizhengyu
 * @Date 2019/3/26 - 20:41
 * @Description:
 **/
public interface SysSupplierService {
    /**
     * 获取供应商名称列表
     * @return
     */
    List<String> getSupplierName();

    /**
     * 分页获取供应商列表
     * @param page
     * @param limit
     * @param supplierName
     * @param supplierDescribe
     * @param chargeName
     * @param phone
     * @param email
     * @return
     */
    List<SysSupplier> getAll(String page, String limit, String supplierName, String supplierDescribe, String chargeName, String phone, String email);

    /**
     * 获取供应商个数
     * @param supplierName
     * @param supplierDescribe
     * @param chargeName
     * @param phone
     * @param email
     * @return
     */
    int countGetAll(String supplierName, String supplierDescribe, String chargeName, String phone, String email);

    /**
     * 新增供应商
     * @param supplierName
     * @param supplierDescribe
     * @param chargeName
     * @param phone
     * @param email
     * @return
     */
    String addSupplier(String supplierName, String supplierDescribe, String chargeName, String phone, String email);

    /**
     * 根据主键获取供应商信息
     * @param id
     * @return
     */
    SysSupplier getById(String id);

    /**
     * 更新供应商信息
     * @param id
     * @param supplierName
     * @param supplierDescribe
     * @param chargeName
     * @param phone
     * @param email
     * @return
     */
    Boolean updateSupplier(String id, String supplierName, String supplierDescribe, String chargeName, String phone, String email);

    /**
     * 根据主键删除供应商信息
     * @param id 供应商id
     * @return
     */
    Boolean deleleSupplier(String id);

    /**
     * 根据供应商名称获取供应商信息
     * @param supplierName 供应商名称
     * */
    SysSupplier getBySupplierName(String supplierName);

}
