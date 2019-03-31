package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.LenProductProducePlanMapper;
import com.gameloft9.demo.dataaccess.model.system.LenProductProductPlan;
import com.gameloft9.demo.service.api.system.LenProductProducePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Lennon_Yuan
 * @time : 2019-03-26 - 11:04
 * @description :
 */
@Service
public class LenProductProducePlanServiceImpl implements LenProductProducePlanService {
    @Autowired
    LenProductProducePlanMapper mapper;

    @Override
    public LenProductProductPlan getById(String id) {
        return mapper.getById(id);
    }

    @Override
    public List<LenProductProducePlanMapper> selectAll() {
        return mapper.selectAll();
    }
}
