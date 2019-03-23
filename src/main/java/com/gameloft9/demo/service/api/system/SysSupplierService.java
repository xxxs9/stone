package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.SysMaterial;
import com.gameloft9.demo.dataaccess.model.system.SysSupplier;

import java.util.List;

public interface SysSupplierService {
    /**
     * 获取供应商名称列表
     * */
    List<String> getSupplierName();

    /**
     * 分页获取供应商列表
     * @param page                  页序
     * @param limit                 分页大小
     * @param supplierName          供应商名称
     * @param supplierDescribe      供应商描述
     * @param phone                 电话
     */
    List<SysSupplier> getAll(String page, String limit, String supplierName, String supplierDescribe, String phone);

    /**
     * 获取供应商个数
     * @param supplierName          供应商名称
     * @param supplierDescribe      供应商描述
     * @param phone                 电话
     * */
    int countGetAll(String supplierName, String supplierDescribe, String phone);

    /**
     * 新增供应商
     * @param supplierName          供应商名称
     * @param supplierDescribe      供应商描述
     * @param phone                 电话
     * */
    String addSupplier(String supplierName, String supplierDescribe, String phone);
    /**
     * 根据主键获取供应商信息
     * @param id 供应商主键
     * */
    SysSupplier getById(String id);
    /**
     * 更新供应商信息
     * @param id                    供应商id
     * @param supplierName          供应商名称
     * @param supplierDescribe      供应商描述
     * @param phone                 电话
     * */
    Boolean updateSupplier(String id, String supplierName, String supplierDescribe, String phone);
    /**
     * 根据主键删除供应商信息
     * @param id 供应商id
     * */
    Boolean deleleSupplier(String id);
}
