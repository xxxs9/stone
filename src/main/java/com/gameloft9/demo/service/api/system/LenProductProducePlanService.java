package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.dao.system.LenProductProducePlanMapper;
import com.gameloft9.demo.dataaccess.model.system.LenProductProductPlan;

import java.util.List;

/**
 * @author : Lennon_Yuan
 * @time : 2019-03-26 - 11:04
 * @description :
 */

public interface LenProductProducePlanService {

    LenProductProductPlan getById(String id);

    List<LenProductProducePlanMapper> selectAll();

}
