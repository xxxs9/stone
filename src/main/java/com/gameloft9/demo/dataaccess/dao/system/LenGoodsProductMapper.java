package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.LenGoodsProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : Lennon_Yuan
 * @time : 2019-04-05 - 01:18
 * @description :
 */

public interface LenGoodsProductMapper {
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
    LenGoodsProduct getByPrimaryKey(@Param("id") String id);

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
    int changeState(@Param("zhuangtai")String zhuangtai,@Param("id")String id);

    /**
     * 计数
     * @return
     */
    int dataCount();

    List<LenGoodsProduct> selectByPage(
            @Param("start")int start,
            @Param("end") int end,
            @Param("pname")String pname
    );

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(String id);

    /**
     * 修改
     * @param lenGoodsProduct
     * @return
     */
     int update(LenGoodsProduct lenGoodsProduct);

    /**
     * 插入
     * @param lenGoodsProduct
     * @return
     */
     int insert(LenGoodsProduct lenGoodsProduct);

    /**
     * 根据状态查询
     * @param zhuangtai
     * @return
     */
     List<LenGoodsProduct> selectByState(@Param("zhuangtai")String zhuangtai);

    /**
     * 显示未生产
     * @return
     */
     List<LenGoodsProduct> selectByUnProduce();


}
