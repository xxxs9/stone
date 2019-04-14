package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.*;
import com.gameloft9.demo.dataaccess.model.system.DepotInventory;
import com.gameloft9.demo.dataaccess.model.system.DepotInventoryCheckDetail;
import com.gameloft9.demo.dataaccess.model.system.LenProduct;
import com.gameloft9.demo.dataaccess.model.system.SysMaterialGoods;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import com.gameloft9.demo.service.api.system.DepotInventoryService;
import com.gameloft9.demo.service.beans.system.MaterialInventory;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.EJConvertor;
import com.gameloft9.demo.utils.FileUtil;
import com.gameloft9.demo.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private  DepotInventoryCheckDetailMapper depotInventoryCheckDetailMapper;
    @Autowired
    private EJConvertor ejConvertor;
    @Autowired
    private  LenGoodsProductMapper lenGoodsProductMapper;
    /**
     * 获取所有库存数据
     * @param page                  页序
     * @param limit                 分页大小
     * @param type                  货品(原料/成品）
     * @param goodsId               货物编号
     */
    @Override
    public List<DepotInventory> getAll(String page, String limit, String type,String goodsId) {
        PageRange pageRange = new PageRange(page, limit);
        return depotInventoryMapper.getAll(pageRange.getStart(),pageRange.getEnd(),type,goodsId);
    }

    /**
     * 获取所有原料库存数据
     * @param page                  页序
     * @param limit                 分页大小
     * @param type                  货品(原料/成品）
     * @param goodsId               货物编号
     */
    @Override
    public List<MaterialInventory> getMaterialInventory(String page, String limit, String type, String goodsId) {
        PageRange pageRange = new PageRange(page, limit);
        List<DepotInventory> list = depotInventoryMapper.getAll(pageRange.getStart(), pageRange.getEnd(), type, goodsId);
        List<MaterialInventory> materialInventoryList = new ArrayList<MaterialInventory>();
        for (DepotInventory depotInventory : list) {
            MaterialInventory materialInventory = new MaterialInventory();
            materialInventory.setId(depotInventory.getId());
            materialInventory.setGoodsName(depotInventory.getGoodsName());
            materialInventory.setSupplierName(sysMaterialGoodsMapper.getSupplierNameByGoodsId(depotInventory.getGoodsId()));
            materialInventory.setType(depotInventory.getType());
            materialInventory.setGoodsId(depotInventory.getGoodsId());
            materialInventory.setGoodsNumber(depotInventory.getGoodsNumber());
            materialInventory.setSaleableNumber(depotInventory.getSaleableNumber());
            materialInventory.setShipmentsNumber(depotInventory.getShipmentsNumber());
            materialInventoryList.add(materialInventory);
        }
        return materialInventoryList;
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

        if(lenGoodsProductMapper.getByBH(goodsId) !=null){
            goodsName = lenGoodsProductMapper.getByBH(goodsId).getPname();
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

    /**
     * 根据货物id更新库存货物数量信息
     * @param id                    盘点单明细id
     * @param goodsId               原料/成品ID
     * @param goodsNumber           货物数量
     * */
    @Override
    public Boolean updateGoodsNumber(String id,String goodsId, String goodsNumber) {

        CheckUtil.notBlank(id, " 盘点单明细id为空");
        CheckUtil.notBlank(goodsId, "原料/成品ID为空");
        CheckUtil.notBlank(goodsNumber, " 货物数量为空");

        DepotInventory depotInventory = depotInventoryMapper.findOne(goodsId);
        depotInventory.setGoodsNumber(goodsNumber);
        depotInventory.setSaleableNumber(String.valueOf(Integer.parseInt(depotInventory.getGoodsNumber())-Integer.parseInt(depotInventory.getShipmentsNumber())));

        depotInventoryMapper.updateByPrimaryKeySelective(depotInventory);

        DepotInventoryCheckDetail depotInventoryCheckDetail = new  DepotInventoryCheckDetail();
        depotInventoryCheckDetail.setId(id);
        depotInventoryCheckDetail.setGoodsNumber(goodsNumber);
        depotInventoryCheckDetailMapper.updateByPrimaryKeySelective(depotInventoryCheckDetail);
        return true;
    }

    /**
     * 根据ids查询库存信息
     * @param ids 库存ids
     * */
    @Override
    public List<DepotInventory> getByIds(String ids) {
        CheckUtil.notBlank(ids, "库存ids为空");
        List<String> depotInventoryIds = new ArrayList<String>();
        String[] split = ids.split(",");
        for (String s : split) {
            depotInventoryIds.add(s);
        }
        return depotInventoryMapper.getByIds(depotInventoryIds);
    }


    /**
     * 导出库存记录或模版
     *
     * @param inventorys 保存有库存记录的List
     * @return excel 文件
     */
    @Override
    public File exportInventory(List<DepotInventory> inventorys) {
        if (inventorys == null) {
            return null;
        }
        return ejConvertor.excelWriter(DepotInventory.class, inventorys);
    }

    /**
     * 导入库存记录
     *
     * @param file 保存有的库存记录的文件
     * @return 返回一个Map，其中：key为total代表导入的总记录数，key为available代表有效导入的记录数
     */
    @Override
    public Map<String, Object> importInventory(MultipartFile file) {
        // 初始化结果集
        Map<String, Object> resultSet = new HashMap<String, Object>();
        int total = 0;
        int available = 0;

        // 从文件中读取
        try {
            List<DepotInventory> inventorys = ejConvertor.excelReader(DepotInventory.class, FileUtil.convertMultipartFileToFile(file));
            if (inventorys != null) {
                total = inventorys.size();

                boolean isAvailable;
                List<DepotInventory> availableList = new ArrayList<>();
                SysMaterialGoods sysMaterialGoods;
                LenProduct lenProduct;
                for (DepotInventory depotInventory : inventorys) {
                    isAvailable = true;

                    sysMaterialGoods = sysMaterialGoodsMapper.getById(depotInventory.getGoodsId());
                    lenProduct = lenProductMapper.getByPrimaryKey(depotInventory.getGoodsId());
                    if (sysMaterialGoods == null && lenProduct == null) {
                        isAvailable = false;
                    }
                    if (Integer.parseInt(depotInventory.getGoodsNumber()) < 0) {
                        isAvailable = false;
                    }
                    if (Integer.parseInt(depotInventory.getShipmentsNumber()) < 0) {
                        isAvailable = false;
                    }
                    if (Integer.parseInt(depotInventory.getSaleableNumber()) < 0) {
                        isAvailable = false;
                    }
                    if (Integer.parseInt(depotInventory.getSaleableNumber()) + Integer.parseInt(depotInventory.getShipmentsNumber()) !=  Integer.parseInt(depotInventory.getGoodsNumber())){
                        isAvailable = false;
                    }
                    DepotInventory temp = depotInventoryMapper.findOne(depotInventory.getGoodsId());
                    if (temp != null){
                        isAvailable = false;
                    }
                    if (isAvailable) {
                        availableList.add(depotInventory);
                    }
                }
                // 保存到数据库
                available = availableList.size();
                System.out.println(available);
                if (available > 0) {
                    for (DepotInventory depotInventory : availableList) {
                        depotInventory.setId(UUIDUtil.getUUID());
                        depotInventoryMapper.insertSelective(depotInventory);
                    }
                }
            }
        } catch (PersistenceException | IOException  | NumberFormatException e) {

        }

        resultSet.put("total", total);
        resultSet.put("available", available);
        return resultSet;
    }

}
