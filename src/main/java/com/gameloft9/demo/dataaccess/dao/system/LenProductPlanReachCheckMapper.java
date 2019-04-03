package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.LenProductPlanReachCheck;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : Lennon_Yuan
 * @time : 2019-03-29 - 10:00
 * @description :
 */

public interface LenProductPlanReachCheckMapper {

    List<LenProductPlanReachCheck> selectPCR();

    List<LenProductPlanReachCheck> selectReach(
            @Param("start")int start,
            @Param("end")int end,
            @Param("productId") String productName,
            @Param("reachUser")String reachUser

    );
}
