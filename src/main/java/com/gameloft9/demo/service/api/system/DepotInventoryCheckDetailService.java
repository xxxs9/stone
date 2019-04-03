package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.DepotInventoryCheck;
import com.gameloft9.demo.dataaccess.model.system.DepotInventoryCheckDetail;

import java.util.List;

public interface DepotInventoryCheckDetailService {
    /**
     * 获取盘点单明细记录数据
     * @param page                  页序
     * @param limit                 分页大小
     * @param checkId               盘点单ID
     * @param type                  货品（原料/成品）
     * @param goodsId               原料/成品ID
     */
    List<DepotInventoryCheckDetail> getAll(String page, String limit, String checkId,String type,String goodsId);


    /**
     * 获取盘点单明细记录数据条数
     * @param checkId               盘点单ID
     * @param type                  货品（原料/成品）
     * @param goodsId               原料/成品ID
     * */
    int countGetAll(String checkId,String type,String goodsId);

    /**
     * 新增盘点单明细
     * @param checkId       盘点单ID
     * @param type          货品（原料/成品）
     * @param goodsId       原料/成品ID
     * @param goodsNumber   货品数量
     * */
    String addDepotInventoryCheckDetail(String checkId,String type,String goodsId,String goodsNumber);

    /**
     * 根据主键获取盘点单信息
     * @param id 盘点单主键
     * */
    DepotInventoryCheckDetail getById(String id);

    /**
     * 更新盘点明细
     * @param id            盘点单明细ID
     * @param checkUser     盘点人
     * @param checkNumber   盘点数量
     * */
    Boolean updateDepotInventoryCheckDetail(String id,String checkUser, String checkNumber);

    /**
     * 根据主键删除盘点单明细信息
     * @param id 盘点单明细id
     * */
    Boolean deleteDepotInventoryCheckDetail(String id);

    /**
     * 根据主键批量删除盘点单明细信息
     * @param ids 盘点单明细ids
     * */
    Boolean delsDepotInventoryCheckDetail(String ids);

    /**
     * 批量添加盘点单明细
     * @param checkId           盘点单ID
     * @param types             多个货品（原料/成品）
     * @param goodsIds          多个原料/成品ID
     * @param goodsNumbers      多个货品数量
     * */
    Boolean adds(String checkId, String types, String goodsIds, String goodsNumbers);
}
