package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.SysMaterialMapper;
import com.gameloft9.demo.dataaccess.model.system.*;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import com.gameloft9.demo.service.api.system.SysMaterialService;
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
@Slf4j
@Service
public class SysMaterialServiceImpl implements SysMaterialService {

    @Autowired
    private SysMaterialMapper sysMaterialMapper;

    /**
     * 获取货物名称列表
     * */
    @Override
    public List<String> getGoodsName() {
        return sysMaterialMapper.getGoodsName();
    }

    /**
     * 获取货物类型列表
     * */
    @Override
    public List<String> getGoodsType() {
        return sysMaterialMapper.getGoodsType();
    }

    /**
     * 获取货物规格列表
     * */
    @Override
    public List<String> getGoodsSpecification() {
        return sysMaterialMapper.getGoodsSpecification();
    }

    /**
     * 分页获取菜单列表
     * @param page                  页序
     * @param limit                 分页大小
     * @param goodsName             货物名称
     * @param goodsType             货物类型
     * @param goodsSpecification    货物规格
     */
    @Override
    public List<SysMaterial> getAll(String page, String limit, String goodsName, String goodsType, String goodsSpecification) {
        PageRange pageRange = new PageRange(page, limit);
        return sysMaterialMapper.getAll(pageRange.getStart(),pageRange.getEnd(),goodsName,goodsType,goodsSpecification);
    }

    /**
     * 获取原料种类个数
     * @param goodsName             货物名称
     * @param goodsType             货物类型
     * @param goodsSpecification    货物规格
     * */
    @Override
    public int countGetAll(String goodsName, String goodsType, String goodsSpecification) {
        return sysMaterialMapper.countGetAll(goodsName,goodsType,goodsSpecification);
    }

    /**
     * 添加原料种类
     * @param goodsName             货物名称
     * @param goodsType             货物类型
     * @param goodsDescribe         货物描述
     * @param goodsSpecification    货物规格
     * */
    @Override
    public String addMaterial(String goodsName, String goodsType, String goodsDescribe, String goodsSpecification) {
        CheckUtil.notBlank(goodsType, "货物类型为空");
        CheckUtil.notBlank(goodsDescribe, "货物描述为空");
        CheckUtil.notBlank(goodsSpecification, "货物规格为空");


        //货物名称不能重复
        SysMaterial menuTest = sysMaterialMapper.getByGoodsName(goodsName);
        CheckUtil.check(menuTest == null, "该货物已经存在");

        SysMaterial material = new SysMaterial();
        material.setGoodsName(goodsName);
        material.setGoodsType(goodsType);
        material.setGoodsDescribe(goodsDescribe);
        material.setGoodsSpecification(goodsSpecification);
        material.setId(UUIDUtil.getUUID());
        sysMaterialMapper.insertSelective(material);
        return material.getId();
    }
    /**
     * 根据主键获取原料信息
     *
     * @param id 原料主键
     */
    public SysMaterial getById(String id) {
        return sysMaterialMapper.getById(id);
    }

    /**
     * 更新原料种类
     * @param id                    货物ID
     * @param goodsName             货物名称
     * @param goodsType             货物类型
     * @param goodsDescribe         货物描述
     * @param goodsSpecification    货物规格
     * */
    @Override
    public Boolean updateMaterial(String id,String goodsName, String goodsType, String goodsDescribe, String goodsSpecification) {

        CheckUtil.notBlank(goodsName, "货物名称为空");
        CheckUtil.notBlank(goodsType, "货物类型为空");
        CheckUtil.notBlank(goodsDescribe, "货物描述为空");
        CheckUtil.notBlank(goodsSpecification, "货物规格为空");

        //更新菜单
        SysMaterial newMaterial = new SysMaterial();
        newMaterial.setId(id);
        newMaterial.setGoodsName(goodsName);
        newMaterial.setGoodsType(goodsType);
        newMaterial.setGoodsDescribe(goodsDescribe);
        newMaterial.setGoodsSpecification(goodsSpecification);
        sysMaterialMapper.updateByPrimaryKeySelective(newMaterial);
        return true;
    }

    /**
     * 删除原料
     *
     * @param materialId 原料materialId
     */
    @Override
    public Boolean deleteMaterial(String materialId) {
        CheckUtil.notBlank(materialId, "原料id为空");
        //删除菜单
        sysMaterialMapper.deleteByPrimaryKey(materialId);
        //删除菜单角色表中对应记录
        return true;
    }
    /**
     * 根据货物名称获取原料信息
     * @param goodsName 货物名称
     * */
    @Override
    public SysMaterial getByGoodsName(String goodsName) {
        return sysMaterialMapper.getByGoodsName(goodsName);
    }
}
