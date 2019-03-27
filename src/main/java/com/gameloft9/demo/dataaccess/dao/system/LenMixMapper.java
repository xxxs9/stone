package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.LenProductInfo;

import java.util.List;

/**
 * @author : Lennon_Yuan
 * @time : 2019-03-25 - 00:44
 * @description :
 */

public interface LenMixMapper {
    /**
     * 数据库三表注册连差
     * @return
     */
    List<LenProductInfo> selectAll();

    /**
     * 按主建查询
     * @param id
     * @return
     */
    LenProductInfo selectById(String id);
}
