package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.SysSupplierMapper;
import com.gameloft9.demo.dataaccess.model.system.SysSupplier;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import com.gameloft9.demo.service.api.system.SysSupplierService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author shizhengyu
 * @Date 2019/3/26 - 20:41
 * @Description:
 **/
@Service
@Slf4j
public class SysSupplierServiceImpl implements SysSupplierService {

    @Autowired
    private SysSupplierMapper sysSupplierMapper;

    /**
     * 获取供应商名称列表
     * */
    @Override
    public List<String> getSupplierName() {
        return sysSupplierMapper.getSupplierName();
    }

    /**
     * 分页获取供应商列表
     * @param page      页序
     * @param limit     分页大小
     * @param supplierName      供应商名称
     * @param supplierDescribe  供应商描述
     * @param chargeName        联系人
     * @param phone     电话
     * @param email     邮箱
     * @return
     */
    @Override
    public List<SysSupplier> getAll(String page, String limit, String supplierName, String supplierDescribe, String chargeName, String phone, String email) {
        PageRange pageRange = new PageRange(page, limit);
        return sysSupplierMapper.getAll(pageRange.getStart(),pageRange.getEnd(),supplierName,supplierDescribe,chargeName,phone,email);
    }

    /**
     * 获取供应商个数
     * @param supplierName      供应商名称
     * @param supplierDescribe  供应商描述
     * @param chargeName        联系人
     * @param phone     电话
     * @param email     邮箱
     * @return
     */
    @Override
    public int countGetAll(String supplierName, String supplierDescribe, String chargeName, String phone, String email) {
        return sysSupplierMapper.countGetAll(supplierName,supplierDescribe,chargeName,phone,email);
    }

    /**
     * 新增供应商
     * @param supplierName      供应商名称
     * @param supplierDescribe  供应商描述
     * @param chargeName        联系人
     * @param phone     电话
     * @param email     邮箱
     * @return
     */
    @Override
    public String addSupplier(String supplierName, String supplierDescribe, String chargeName, String phone, String email) {
        CheckUtil.notBlank(supplierName, "供应商名称为空");
        CheckUtil.notBlank(phone, "电话号为空");

        //供应商名称不能重复
        SysSupplier menuTest = sysSupplierMapper.getBySupplierName(supplierName);
        CheckUtil.check(menuTest == null, "该供应商已经存在");

        SysSupplier supplier = new SysSupplier();
        supplier.setSupplierName(supplierName);
        supplier.setSupplierDescribe(supplierDescribe);
        supplier.setChargeName(chargeName);
        supplier.setPhone(phone);
        supplier.setEmail(email);
        supplier.setId(UUIDUtil.getUUID());
        sysSupplierMapper.insertSelective(supplier);
        return supplier.getId();
    }
    /**
     * 根据主键获取供应商信息
     * @param id 供应商主键
     * */
    @Override
    public SysSupplier getById(String id) {
        return sysSupplierMapper.getById(id);
    }

    /**
     * 更新供应商信息
     *      * @param id
     *      * @param supplierName
     *      * @param supplierDescribe
     *      * @param phone
     * @param id        供应商id
     * @param supplierName      供应商名称
     * @param supplierDescribe  供应商描述
     * @param chargeName    联系人
     * @param phone     电话
     * @param email     邮箱
     * @return
     */
    @Override
    public Boolean updateSupplier(String id, String supplierName, String supplierDescribe, String chargeName, String phone, String email) {
        CheckUtil.notBlank(supplierName, "供应商名称为空");
        CheckUtil.notBlank(phone, "电话号为空");

        //更新菜单
        SysSupplier newSupplier = new SysSupplier();
        newSupplier.setId(id);
        newSupplier.setSupplierName(supplierName);
        newSupplier.setSupplierDescribe(supplierDescribe);
        newSupplier.setChargeName(chargeName);
        newSupplier.setPhone(phone);
        newSupplier.setEmail(email);
        sysSupplierMapper.updateByPrimaryKeySelective(newSupplier);
        return true;
    }

    /**
     * 根据主键删除供应商信息
     * @param id
     * @return
     */
    @Override
    public Boolean deleteSupplier(String id) {
        CheckUtil.notBlank(id, "供应商id为空");
        //删除供应商
        sysSupplierMapper.deleteByPrimaryKey(id);
        return true;
    }

    /**
     * 根据供应商名称获取供应商信息
     * @param supplierName 供应商名称
     * */
    @Override
    public SysSupplier getBySupplierName(String supplierName) {
        return sysSupplierMapper.getBySupplierName(supplierName);
    }
}
