package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.LenGoodsProduct;

import java.util.List;

/**
 * @author : Lennon_Yuan
 * @time : 2019-04-05 - 08:53
 * @description :
 */

public interface LenGoodsProductService {
    /**
     * 查询所有
     * @return
     */
    List<LenGoodsProduct> selectAll();

    /**
     * 主键查询
     * @param id
     * @return
     */
    LenGoodsProduct getByPrimaryKey(String id);

    /**
     * 主键查状态
     * @param id
     * @return
     */
    String getStateById(String id);

    /**
     * 修改状态
     * @param zhuangtai
     * @param id
     * @return
     */
    boolean changeState(String zhuangtai,String id);

    /**
     * 计数
     * @return
     */
    int dataCount();

    List<LenGoodsProduct> selectByPage(String page, String limit, String pname);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(String id);

    /**
     * 修改
     * @param lenGoodsProduct
     * @return
     */
    boolean update(LenGoodsProduct lenGoodsProduct);

    /**
     * 插入
     * @param lenGoodsProduct
     * @return
     */
    boolean insert(LenGoodsProduct lenGoodsProduct);

    /**
     * 根据状态查询
     * @param zhuangtai
     * @return
     */
    List<LenGoodsProduct> selectByState(String zhuangtai);
}
