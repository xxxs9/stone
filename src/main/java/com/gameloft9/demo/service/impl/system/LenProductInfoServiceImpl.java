package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.LenMixMapper;
import com.gameloft9.demo.dataaccess.model.system.LenProductInfo;
import com.gameloft9.demo.service.api.system.LenProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Lennon_Yuan
 * @time : 2019-03-25 - 01:16
 * @description :
 */
@Service
public class LenProductInfoServiceImpl implements LenProductInfoService {
    @Autowired
    LenMixMapper mapper;
    @Override
    public List<LenProductInfo> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public LenProductInfo selectById(String id) {
        return mapper.selectById(id);
    }
}
