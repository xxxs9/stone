package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.SysMaterial;
import com.gameloft9.demo.dataaccess.model.system.SysSupplier;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysSupplierMapper {

    /**
     * 获取供应商名称列表
     * */
    List<String> getSupplierName();

    /**
     * 分页获取供应商列表
     * @param start                 记录行的偏移量
     * @param end                   记录的最大数目
     * @param supplierName          供应商名称
     * @param supplierDescribe      供应商描述
     * @param phone                 电话
     * */
    List<SysSupplier> getAll(@Param("start") int start,
                             @Param("end") int end,
                             @Param("supplierName") String supplierName,
                             @Param("supplierDescribe") String supplierDescribe,
                             @Param("phone") String phone);
    /**
     * 获取供应商个数
     * @param supplierName          供应商名称
     * @param supplierDescribe      供应商描述
     * @param phone                 电话
     * */
    int countGetAll(@Param("supplierName") String supplierName,
                    @Param("supplierDescribe") String supplierDescribe,
                    @Param("phone") String phone);

    /**
     * 新增供应商
     * @param record 供应商
     * */
    int insertSelective(SysSupplier record);

    /**
     * 根据供应商名称获取供应商信息
     * @param supplierName 货品名称
     */
    SysSupplier getBySupplierName(@Param("supplierName") String supplierName);
    /**
     * 根据主键获取供应商信息
     * @param id 供应商主键
     * */
    SysSupplier getById(@Param("id") String id);
    /**
     * 更新供应商信息
     * @param record 供应商
     * */
    int updateByPrimaryKeySelective(SysSupplier record);
    /**
     * 根据主键删除供应商信息
     * @param id 供应商id
     * */
    int deleteByPrimaryKey(String id);
}
