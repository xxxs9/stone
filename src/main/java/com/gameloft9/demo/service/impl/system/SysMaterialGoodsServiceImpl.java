package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.SysMaterialGoodsMapper;
import com.gameloft9.demo.dataaccess.dao.system.SysMaterialMapper;
import com.gameloft9.demo.dataaccess.dao.system.SysSupplierMapper;
import com.gameloft9.demo.dataaccess.model.system.SysMaterial;
import com.gameloft9.demo.dataaccess.model.system.SysMaterialGoods;
import com.gameloft9.demo.dataaccess.model.system.SysSupplier;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import com.gameloft9.demo.service.api.system.SysMaterialGoodsService;
import com.gameloft9.demo.service.beans.system.MaterialGoodsResponse;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


/**
 * @Author shizhengyu
 * @Date 2019/3/26 - 20:41
 * @Description:
 **/
@Slf4j
@Service
public class SysMaterialGoodsServiceImpl implements SysMaterialGoodsService {

    @Autowired
    private SysMaterialGoodsMapper sysMaterialGoodsMapper;
    @Autowired
    private SysSupplierMapper sysSupplierMapper;
    @Autowired
    private SysMaterialMapper sysMaterialMapper;
    /**
     * 分页获取供应关系列表
     * @param page                  页序
     * @param limit                 分页大小
     * @param supplierName          供应商名称
     * @param goodsName             货物名称
     * @param goodsType             货物类型
     * @param goodsSpecification    货物规格
     */
    @Override
    public List<MaterialGoodsResponse> getAll(String page, String limit, String supplierName, String goodsName, String goodsType, String goodsSpecification) {
        PageRange pageRange = new PageRange(page, limit);
        List<SysSupplier> supplierAll = sysSupplierMapper.getAll(0, 9999999, supplierName, null, null,null,null);
        List<SysMaterial> materialAll = sysMaterialMapper.getAll(0, 9999999, goodsName, goodsType, goodsSpecification);
        List<String> supplierIds = new ArrayList<String>();
        List<String> materialIds = new ArrayList<String>();
        List<MaterialGoodsResponse> response = new ArrayList<MaterialGoodsResponse>();
        Set<String> Ids = new LinkedHashSet<String>();

        for (SysSupplier sysSupplier : supplierAll) {
            supplierIds.add(sysSupplier.getId());
        }
        for (SysMaterial material : materialAll) {
            materialIds.add(material.getId());
        }
        List<String> Ids1 = sysMaterialGoodsMapper.getBySupplierIds(supplierIds);
        System.out.println(Ids1);
        List<String> Ids2 = sysMaterialGoodsMapper.getByMaterialIds(materialIds);
        System.out.println(Ids2);
        for (String id1 : Ids1) {
            for (String id2 : Ids2) {
                if(id1.equals(id2)){
                    Ids.add(id1);
                }
            }
        }
        List<SysMaterialGoods> materialGoods = sysMaterialGoodsMapper.getByIds(pageRange.getStart(), pageRange.getEnd(), Ids);


        for (SysMaterialGoods goods : materialGoods) {
            MaterialGoodsResponse materialGoodsResponse = new MaterialGoodsResponse();
            materialGoodsResponse.setId(goods.getId());
            materialGoodsResponse.setSupplierName(sysSupplierMapper.getById(goods.getSupplierId()).getSupplierName());
            materialGoodsResponse.setGoodsName(sysMaterialMapper.getById(goods.getMaterialId()).getGoodsName());
            materialGoodsResponse.setGoodsType(sysMaterialMapper.getById(goods.getMaterialId()).getGoodsType());
            materialGoodsResponse.setGoodsSpecification(sysMaterialMapper.getById(goods.getMaterialId()).getGoodsSpecification());
            materialGoodsResponse.setGoodsPrice(goods.getGoodsPrice());
            materialGoodsResponse.setGoodsOriginPlace(goods.getGoodsOriginPlace());
            materialGoodsResponse.setImageUrl(goods.getImageUrl());
            response.add(materialGoodsResponse);
        }

        return response;
    }

    /**
     * 分页获取供应关系列表
     * @param supplierName          供应商名称
     * @param goodsName             货物名称
     * @param goodsType             货物类型
     * @param goodsSpecification    货物规格
     */
    @Override
    public int countGetAll(String supplierName, String goodsName, String goodsType, String goodsSpecification) {
        List<SysSupplier> supplierAll = sysSupplierMapper.getAll(0, 9999999, supplierName, null, null,null,null);
        List<SysMaterial> materialAll = sysMaterialMapper.getAll(0, 9999999, goodsName, goodsType, goodsSpecification);
        List<String> supplierIds = new ArrayList<String>();
        List<String> materialIds = new ArrayList<String>();
        Set<String> Ids = new LinkedHashSet<String>();

        for (SysSupplier sysSupplier : supplierAll) {
            supplierIds.add(sysSupplier.getId());
        }
        for (SysMaterial material : materialAll) {
            materialIds.add(material.getId());
        }
        List<String> Ids1 = sysMaterialGoodsMapper.getBySupplierIds(supplierIds);
        List<String> Ids2 = sysMaterialGoodsMapper.getByMaterialIds(materialIds);
        for (String id1 : Ids1) {
            for (String id2 : Ids2) {
                if(id1.equals(id2)){
                    Ids.add(id1);
                }
            }
        }
        return Ids.size();
    }

    /**
     * 添加供应关系种类
     * @param supplierName          供应商名称
     * @param goodsName             货物名称
     * @param goodsPrice            货物价格
     * @param goodsOriginPlace      货物原产地
     * @param imageUrl              货物图片地址
     * */
    @Override
    public String addMaterialGoods(String supplierName, String goodsName, String goodsPrice, String goodsOriginPlace,String imageUrl) {


        CheckUtil.notBlank(supplierName, "供应商名称为空");
        CheckUtil.notBlank(goodsName, "货物名称为空");
        CheckUtil.notBlank(goodsPrice, "货物价格为空");
        CheckUtil.notBlank(goodsOriginPlace, "货物原产地为空");
        CheckUtil.notBlank(imageUrl,"无上传图片");

        String materialId = sysMaterialMapper.getByGoodsName(goodsName).getId();
        String supplierId = sysSupplierMapper.getBySupplierName(supplierName).getId();

        //供应关系不能重复
        SysMaterialGoods menuTest = sysMaterialGoodsMapper.getByOtherId(materialId,supplierId);
        CheckUtil.check(menuTest == null, "该供应关系已经存在");

        SysMaterialGoods materialGoods = new SysMaterialGoods();
        materialGoods.setId(UUIDUtil.getUUID());
        materialGoods.setSupplierId(sysSupplierMapper.getBySupplierName(supplierName).getId());
        materialGoods.setMaterialId(sysMaterialMapper.getByGoodsName(goodsName).getId());
        materialGoods.setGoodsPrice(goodsPrice);
        materialGoods.setGoodsOriginPlace(goodsOriginPlace);
        materialGoods.setImageUrl(imageUrl);
        sysMaterialGoodsMapper.insertSelective(materialGoods);
        return materialGoods.getId();
    }

    /**
     * 根据主键获取供应关系信息
     * @param id 供应关系主键
     * */
    @Override
    public MaterialGoodsResponse getById(String id) {
        SysMaterialGoods materialGoods = sysMaterialGoodsMapper.getById(id);
        MaterialGoodsResponse materialGoodsResponse = new MaterialGoodsResponse();
        materialGoodsResponse.setId(materialGoods.getId());
        materialGoodsResponse.setSupplierName(sysSupplierMapper.getById(materialGoods.getSupplierId()).getSupplierName());
        materialGoodsResponse.setGoodsName(sysMaterialMapper.getById(materialGoods.getMaterialId()).getGoodsName());
        materialGoodsResponse.setGoodsType(sysMaterialMapper.getById(materialGoods.getMaterialId()).getGoodsType());
        materialGoodsResponse.setGoodsSpecification(sysMaterialMapper.getById(materialGoods.getMaterialId()).getGoodsSpecification());
        materialGoodsResponse.setGoodsDescribe(sysMaterialMapper.getById(materialGoods.getMaterialId()).getGoodsDescribe());
        materialGoodsResponse.setGoodsPrice(materialGoods.getGoodsPrice());
        materialGoodsResponse.setGoodsOriginPlace(materialGoods.getGoodsOriginPlace());
        materialGoodsResponse.setImageUrl(materialGoods.getImageUrl());
        return materialGoodsResponse;
    }

    /**
     * 更新原料种类
     * @param id                    供应关系ID
     * @param supplierName          供应商名称
     * @param goodsName             货物名称
     * @param goodsPrice            货物价格
     * @param goodsOriginPlace      货物原产地
     * @param imageUrl              货物图片地址
     * */
    @Override
    public Boolean updateMaterialGoods(String id, String supplierName, String goodsName, String goodsPrice, String goodsOriginPlace,String imageUrl) {


        CheckUtil.notBlank(supplierName, "供应商名称为空");
        CheckUtil.notBlank(goodsName, "货物名称为空");
        CheckUtil.notBlank(goodsPrice, "货物价格为空");
        CheckUtil.notBlank(goodsOriginPlace, "货物原产地为空");
        CheckUtil.notBlank(imageUrl,"无上传图片");

        //更新菜单
        SysMaterialGoods newMaterialGoods = new SysMaterialGoods();
        newMaterialGoods.setId(id);
        newMaterialGoods.setSupplierId(sysSupplierMapper.getBySupplierName(supplierName).getId());
        newMaterialGoods.setMaterialId(sysMaterialMapper.getByGoodsName(goodsName).getId());
        newMaterialGoods.setGoodsPrice(goodsPrice);
        newMaterialGoods.setGoodsOriginPlace(goodsOriginPlace);
        newMaterialGoods.setImageUrl(imageUrl);
        sysMaterialGoodsMapper.updateByPrimaryKeySelective(newMaterialGoods);
        return true;
    }

    /**
     * 删除
     * @param id 供应关系id
     * */
    @Override
    public Boolean deleteMaterialGoods(String id) {
        CheckUtil.notBlank(id, "主键id为空");
        sysMaterialGoodsMapper.deleteByPrimaryKey(id);
        return true;
    }


    /**
     * 根据获取原料商品id信息
     * */
    @Override
    public List<String> getMaterialGoodsId() {
        return sysMaterialGoodsMapper.getMaterialGoodsId();
    }
}
