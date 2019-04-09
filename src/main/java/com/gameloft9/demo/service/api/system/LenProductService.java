package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.LenProduct;
import com.gameloft9.demo.dataaccess.model.system.SysMaterial;
import com.gameloft9.demo.dataaccess.model.system.SysMaterialGoods;

import java.util.List;

/**
 * @packageName: com.gameloft9.demo.service.api.system
 * @author: Lennon_Yuan
 * @time: 2019/3/19 0019 - 下午 3:25
 * @description:
 */

public interface LenProductService {

    /**
     * 查找所有（无条件）
     *
     * @return
     *
     */
    List<LenProduct> selectAll();

    /**
     * 按照主键查找所有
     *
     * @param id
     * @return
     */
    LenProduct getByPrimaryKey(String id);

    /**
     * 分页查找
     *
     * @param page
     * @param limit
     * @param productName
     * @param state
     * @return
     */
    List<LenProduct> selectByPage(
           String page,
             String limit,
           String productName,
             String state
    );

    /**
     *增加
     *
     * @param lenProduct
     * @return
     */
    boolean insert(LenProduct lenProduct);

    /**
     * 修改
     *
     * @param lenProduct
     * @return
     */
    boolean update(LenProduct lenProduct);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(String id);

    /**
     * 统计条数
     * @param state
     * @return
     */
    int dataCount(String state);

    /**
     * 更改状态
     * @param id
     * @return
     */
    boolean changeState(String id);

    /**
     * 撤回
     * @param id
     * @return
     */
    boolean stepBack(String id);

    /**
     * 选择修改
     *
     * @param lenProduct
     * @return
     */
    boolean updateByPrimaryKeySelective(LenProduct lenProduct);

    /**
     * 选择插入
     *
     * @param lenProduct
     * @return
     */
    boolean insertSelective(LenProduct lenProduct);

    /**
     * 查询已审核产品订单
     *
     * @return
     */
    List<LenProduct> selectByState();

    /**
     * 更改状态
     * @param state
     * @param id
     * @return
     */
    boolean changeProState(String state,String id);

    /**
     * 更改状态
     * @param state
     * @param id
     * @return
     */
    boolean changeBehindState(String state,String id);
    /**
     * 获取产品id
     * */
    List<String> getProductId();

    /**
     * 插入成本
     * @param supportPrice
     * @param id
     * @return
     */
    boolean insertSupportPrice(String supportPrice, String id);

    /**
     * 华峰货品出库
     * @param id
     * @return
     */
    boolean huaOutDepot(String id);

    /**
     * 仓库已收到货品，将货品入库
     * @param id
     * @return
     */
    boolean huaInDepot(String id);

    boolean productIntoDepot(String id);

    boolean inssrDepot(LenProduct lenProduct);

    /**
     * 查询所有原料
     * @return
     */
    List<SysMaterialGoods> getAllMaterial();

    /**
     * 根据id查询原料
     * @param id
     * @return
     */
    SysMaterial getMaterilaById(String id);

    SysMaterialGoods getGoodsMaterialById(String id);


}
