package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.DepotAdjustment;
import com.gameloft9.demo.dataaccess.model.system.DepotInventory;
import com.gameloft9.demo.service.beans.system.MaterialInventory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface DepotInventoryService {
    /**
     * 获取所有库存数据
     * @param page                  页序
     * @param limit                 分页大小
     * @param type                  货品(原料/成品）
     * @param goodsId               货物编号
     */
    List<DepotInventory> getAll(String page, String limit, String type,String goodsId);

    /**
     * 获取所有原料库存数据
     * @param page                  页序
     * @param limit                 分页大小
     * @param type                  货品(原料/成品）
     * @param goodsId               货物编号
     */
    List<MaterialInventory> getMaterialInventory(String page, String limit, String type, String goodsId);


    /**
     * 获取库存个数
     * @param type                  货品(原料/成品）
     * @param goodsId               原料/成品ID
     * */
    int countGetAll( String type, String goodsId);

    /**
     * 新增库存
     * @param type                  仓库编号
     * @param goodsId               原料/成品ID
     * @param goodsNumber           货品数量
     * */
    String addDepotInventory(String type,String goodsId,String goodsNumber);

    /**
     * 根据主键获取库存信息
     * @param id 库存主键
     * */
    DepotInventory getById(String id);

    /**
     * 根据更新库存信息
     * @param id                    库存id
     * @param type                  仓库编号
     * @param goodsId               原料/成品ID
     * @param goodsNumber           货品数量
     * @param shipmentsNumber       待发货数
     * @param saleableNumber        可销售数
     * */
    Boolean updateDepotInventory(String id,String type,String goodsId,String goodsNumber,String shipmentsNumber,String saleableNumber);
    /**
     * 删除库存调整单
     * @param id 库存调整单id
     * */
    Boolean deleteDepotInventory(String id);

    /**
     * 批量删除库存调整单
     * @param ids 库存调整单ids
     * */
    Boolean delsDepotInventory(String ids);

    /**
     * 根据货物id获取库存信息
     * @param goodsId               原料/成品ID
     * */
    DepotInventory findOne(String goodsId);

    /**
     * 根据货物id更新库存货物数量信息
     * @param id                    盘点单明细id
     * @param goodsId               原料/成品ID
     * @param goodsNumber           货物数量
     * */
    Boolean updateGoodsNumber(String id,String goodsId, String goodsNumber);

    /**
     * 根据ids查询库存信息
     * @param ids 库存ids
     * */
    List<DepotInventory> getByIds(String ids);

    /**
     * 导出库存记录或模版
     *
     * @param inventorys 保存有库存记录的List
     * @return excel 文件
     */
    File exportInventory(List<DepotInventory> inventorys);

    /**
     * 导入库存记录
     *
     * @param file 保存有的库存记录的文件
     * @return 返回一个Map，其中：key为total代表导入的总记录数，key为available代表有效导入的记录数
     */
    Map<String, Object> importInventory(MultipartFile file);

}
