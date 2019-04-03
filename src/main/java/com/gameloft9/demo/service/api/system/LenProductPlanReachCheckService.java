package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.LenProductPlanReachCheck;

import java.util.List;

/**
 * @author : Lennon_Yuan
 * @time : 2019-03-29 - 11:30
 * @description :
 */

public interface LenProductPlanReachCheckService {
    /**
     * 查询所有
     * @return
     */
    List<LenProductPlanReachCheck> selectPCR();

    /**
     * 显示formulaReach实体
     * @return
     */
    List<LenProductPlanReachCheck> selectReach(String page,
                                               String limit,
                                               String productId,
                                               String reachUser);
}
