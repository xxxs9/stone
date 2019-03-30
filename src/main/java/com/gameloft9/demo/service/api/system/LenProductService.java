package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.LenProduct;

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
     *
     * @return
     */
    boolean delete(String id);

    /**
     * 统计条数
     *
     * @return
     */
    int dataCount(String state);

    /**
     * 更改state状态码
     *
     * @return
     */
    boolean changeState(String id);

    /**
     * 撤回操作
     *
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

    boolean changeProState(String state,String id);

}
