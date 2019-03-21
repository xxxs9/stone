package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.LenProduceFormulaMapper;
import com.gameloft9.demo.dataaccess.model.system.LenProduceFormula;
import com.gameloft9.demo.service.api.system.LenProduceFormulaService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @packageName: com.gameloft9.demo.service.impl.system
 * @author: Lennon_Yuan
 * @time: 2019/3/20 0020 - 上午 10:27
 * @description:
 */
@Service
public class LenProduceFormulaServiceImpl implements LenProduceFormulaService {
    @Autowired
    LenProduceFormulaMapper mapper;

    @Override
    public List<LenProduceFormula> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public LenProduceFormula getByPrimaryKey(String id) {
        return mapper.getByPrimaryKey(id);
    }

    @Override
    public List<LenProduceFormula> selectByPage(String page, String limit, String productId, String createUserId) {
        PageRange pageRange = new PageRange(page, limit);
        return mapper.selectByPage(pageRange.getStart(), pageRange.getEnd(), productId, createUserId);

    }

    @Override
    public boolean insert(LenProduceFormula lenProduceFormula) {
        String uuid = UUIDUtil.getUUID();
        LenProduceFormula formula = new LenProduceFormula();
        formula.setId(uuid);
        formula.setFormulaNumber(lenProduceFormula.getFormulaNumber());
        formula.setFormulaType(lenProduceFormula.getFormulaType());
        formula.setProductId(lenProduceFormula.getProductId());
        formula.setCreateUserId(lenProduceFormula.getCreateUserId());
        if (mapper.insert(formula) > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean update(LenProduceFormula lenProduceFormula) {
        if (mapper.update(lenProduceFormula) > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        if (mapper.delete(id) > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int dataCount(String productId) {
        return 0;
    }

    @Override
    public boolean updateByPrimaryKeySelective(LenProduceFormula lenProduceFormula) {
        return false;
    }

    @Override
    public boolean insertSelective(LenProduceFormula lenProduceFormula) {
        return false;
    }
}
