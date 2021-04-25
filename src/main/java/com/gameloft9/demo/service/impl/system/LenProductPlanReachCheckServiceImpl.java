package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.LenFormulaReachMapper;
import com.gameloft9.demo.dataaccess.dao.system.LenProductPlanReachCheckMapper;
import com.gameloft9.demo.dataaccess.model.system.LenProductPlanReachCheck;
import com.gameloft9.demo.service.api.system.LenProductPlanReachCheckService;
import com.gameloft9.demo.service.beans.system.PageRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Lennon_Yuan
 * @time : 2019-03-29 - 11:32
 * @description :
 */
@Service
public class LenProductPlanReachCheckServiceImpl implements LenProductPlanReachCheckService {
    @Autowired
    LenProductPlanReachCheckMapper mapper;
    @Autowired
    LenFormulaReachMapper fr;

    @Override
    public List<LenProductPlanReachCheck> selectPCR() {
        return mapper.selectPCR();
    }

    @Override
    public List<LenProductPlanReachCheck> selectReach(String page, String limit, String productId, String reachUser) {
        PageRange pageRange = new PageRange(page, limit);
        List<LenProductPlanReachCheck> list = mapper.selectReach(pageRange.getStart(), pageRange.getEnd(), productId, reachUser);
        /**
         * 仓库的状态为 0 未审核| 1已通过
         * 如果仓库未1 则 开始生产
         */

        return list;
    }


}
