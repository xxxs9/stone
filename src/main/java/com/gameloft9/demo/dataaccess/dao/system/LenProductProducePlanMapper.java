package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.LenProductProductPlan;

import java.util.List;

/**
 * @author : Lennon_Yuan
 * @time : 2019-03-26 - 10:36
 * @description :
 */

public interface LenProductProducePlanMapper {

    LenProductProductPlan getById(String id);

    List<LenProductProducePlanMapper> selectAll();
}
