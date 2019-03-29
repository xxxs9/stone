package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.SysSupplier;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author shizhengyu
 * @Date 2019/3/26 - 17:17
 * @Description:
 **/

public interface SysSupplierMapper {

    /**
     * 获取供应商名称列表
     * @return
     */
    List<String> getSupplierName();

    /**
     *  分页获取供应商列表
     * @param start     记录行的偏移量
     * @param end       记录的最大数目
     * @param supplierName      供应商名称
     * @param supplierDescribe      供应商描述
     * @param chargeName        联系人
     * @param email     邮箱
     * @param phone     电话
     * @return
     */
    List<SysSupplier> getAll(@Param("start") int start,
                             @Param("end") int end,
                             @Param("supplierName") String supplierName,
                             @Param("supplierDescribe") String supplierDescribe,
                             @Param("chargeName") String chargeName,
                             @Param("email") String email,
                             @Param("phone") String phone);

    /**
     * 获取供应商个数
     * @param supplierName      供应商名称
     * @param supplierDescribe  供应商描述
     * @param chargeName    联系人
     * @param email     邮箱
     * @param phone     电话
     * @return
     */
    int countGetAll(@Param("supplierName") String supplierName,
                    @Param("supplierDescribe") String supplierDescribe,
                    @Param("chargeName") String chargeName,
                    @Param("email") String email,
                    @Param("phone") String phone);


    /**
     * 新增供应商
     * @param record    供应商
     * @return
     */
    int insertSelective(SysSupplier record);

    /**
     * 根据供应商名称获取供应商信息
     * @param supplierName  供应商名称
     * @return
     */
    SysSupplier getBySupplierName(@Param("supplierName") String supplierName);

    /**
     * 根据主键获取供应商信息
     * @param id
     * @return
     */
    SysSupplier getById(@Param("id") String id);

    /**
     * 更新供应商信息
     * @param record    供应商
     * @return
     */
    int updateByPrimaryKeySelective(SysSupplier record);

    /**
     * 根据主键删除供应商信息
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);
}
