package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.*;
import com.gameloft9.demo.dataaccess.model.system.DepotAdjustment;
import com.gameloft9.demo.dataaccess.model.system.DepotInventory;
import com.gameloft9.demo.dataaccess.model.system.SysDepot;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import com.gameloft9.demo.service.api.system.DepotInventoryService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.Constants;
import com.gameloft9.demo.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Sxiu
 * @create: 2019/3/21 19:10
 * @description:
 */
@Slf4j
@Service
public class DepotInventoryServiceImpl implements DepotInventoryService {

    @Autowired
    private DepotInventoryMapper depotInventoryMapper;
    @Autowired
    private LenProductMapper lenProductMapper;
    @Autowired
    private SysMaterialGoodsMapper sysMaterialGoodsMapper;
    @Autowired
    private SysMaterialMapper sysMaterialMapper;

    /**
     * 获取所有库存数据
     * @param page                  页序
     * @param limit                 分页大小
     * @param type                  货品(原料/成品）
     */
    @Override
    public List<DepotInventory> getAll(String page, String limit, String type,String goodsId) {
        PageRange pageRange = new PageRange(page, limit);
        return depotInventoryMapper.getAll(pageRange.getStart(),pageRange.getEnd(),type,goodsId);
    }

    /**
     * 获取库存个数
     * @param type                  货品(原料/成品）
     * @param goodsId               原料/成品ID
     * */
    @Override
    public int countGetAll( String type, String goodsId) {

        return depotInventoryMapper.countGetAll(type,goodsId);
    }

    /**
     * 新增库存
     * @param type                  货品(原料/成品）
     * @param goodsId               原料/成品ID
     * @param goodsNumber           货品数量
     * */
    @Override
    public String addDepotInventory(String type, String goodsId, String goodsNumber) {

        CheckUtil.notBlank(type, "货物类型为空");
        CheckUtil.notBlank(goodsId, " 原料/成品ID为空");

        //库存不能重复
        DepotInventory menuTest = depotInventoryMapper.findOne(goodsId);
        CheckUtil.check(menuTest == null, "该库存已经存在");

        String goodsName = null;

        if(lenProductMapper.getByPrimaryKey(goodsId) !=null){
            goodsName = lenProductMapper.getByPrimaryKey(goodsId).getProductName();
        }
        if(sysMaterialGoodsMapper.getById(goodsId) !=null){
            goodsName = sysMaterialMapper.getById(sysMaterialGoodsMapper.getById(goodsId).getMaterialId()).getGoodsName();
        }

        DepotInventory depotInventory = new DepotInventory();
        depotInventory.setId(UUIDUtil.getUUID());
        depotInventory.setGoodsName(goodsName);
        depotInventory.setType(type);
        depotInventory.setGoodsId(goodsId);
        depotInventory.setGoodsNumber(goodsNumber);
        depotInventory.setSaleableNumber(goodsNumber);

        depotInventoryMapper.insertSelective(depotInventory);
        return depotInventory.getId();
    }




    @Override
    public DepotInventory getById(String id) {
        return depotInventoryMapper.getById(id);
    }

    @Override
    public Boolean updateDepotInventory(String id, String type, String goodsId, String goodsNumber, String shipmentsNumber, String saleableNumber) {

        CheckUtil.notBlank(String.valueOf(type), "货物类型为空");
        CheckUtil.notBlank(goodsId, " 原料/成品ID为空");


        DepotInventory depotInventory = new DepotInventory();
        depotInventory.setId(id);
        depotInventory.setType(type);
        depotInventory.setGoodsId(goodsId);
        depotInventory.setGoodsNumber(goodsNumber);
        depotInventory.setShipmentsNumber(shipmentsNumber);
        depotInventory.setSaleableNumber(saleableNumber);

        depotInventoryMapper.updateByPrimaryKeySelective(depotInventory);

        return true;
    }

    @Override
    public Boolean deleteDepotInventory(String id) {
        CheckUtil.notBlank(id, "仓库人员id为空");
        depotInventoryMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public Boolean delsDepotInventory(String ids) {
        CheckUtil.notBlank(ids, "库存ids为空");
        List<String> depotInventoryIds = new ArrayList<String>();
        String[] split = ids.split(",");
        for (String s : split) {
            depotInventoryIds.add(s);
        }
        depotInventoryMapper.delsByIds(depotInventoryIds);
        return true;
    }

    @Override
    public DepotInventory findOne( String goodsId) {
        return depotInventoryMapper.findOne(goodsId);
    }

}
