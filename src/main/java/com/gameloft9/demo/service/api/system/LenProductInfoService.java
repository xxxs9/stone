package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.LenProductInfo;

import java.util.List;

/**
 * @author : Lennon_Yuan
 * @time : 2019-03-25 - 01:16
 * @description :
 */

public interface LenProductInfoService {
    /**
     * 查找所有
     * @return
     */
    List<LenProductInfo> selectAll();


    /**
     * 按主键查询
     * @param id
     * @return
     */
    LenProductInfo selectById(String id);
}
