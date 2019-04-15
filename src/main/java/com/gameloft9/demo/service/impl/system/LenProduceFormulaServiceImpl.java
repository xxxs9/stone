package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.LenProduceFormulaMapper;
import com.gameloft9.demo.dataaccess.model.system.LenProduceFormula;
import com.gameloft9.demo.service.api.system.LenProduceFormulaService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.OrderUtil;
import com.gameloft9.demo.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public List<LenProduceFormula> selectByPage(String page, String limit,String createUser ,String productId ) {
        PageRange pageRange = new PageRange(page, limit);
        return mapper.selectByPage(pageRange.getStart(), pageRange.getEnd(), createUser, productId );

    }

    @Override
    public boolean insert(String productId,String formulaType ,String formulaNumber,String createUser,String createTime,String other3) {
        String uuid = UUIDUtil.getUUID();
        LenProduceFormula formula = new LenProduceFormula();
        formula.setId(uuid);
        formula.setProductId(productId);
        formula.setFormulaType(formulaType);
        formula.setFormulaNumber(formulaNumber);
        formula.setCreateUser(createUser);
        formula.setCreateTime(new Date());
        formula.setOther3(other3);
        String fm = OrderUtil.lenOrderNumber("FM");
        formula.setOther1(fm);
        if (mapper.insert(formula) > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean update(String id,String productId,String formulaType ,String formulaNumber,String createUser,String createTime,String other1,String other3) {
        LenProduceFormula formula = new LenProduceFormula();
        formula.setId(id);
        formula.setCreateTime(new Date());
        formula.setProductId(productId);
        formula.setFormulaType(formulaType);
        formula.setFormulaNumber(formulaNumber);
        formula.setCreateUser(createUser);
        formula.setOther1(other1);
        formula.setOther3(other3);
        if (mapper.update(formula) > 0) {
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
        return mapper.dataCount(productId);
    }

    @Override
    public boolean updateByPrimaryKeySelective(LenProduceFormula lenProduceFormula) {
        return false;
    }

    @Override
    public boolean insertSelective(LenProduceFormula lenProduceFormula) {
        return false;
    }

    /**
     * 由productId查找
     * @param productId
     * @return
     */
    public List<LenProduceFormula> getByProductId(String productId){
        return mapper.getByProductId(productId);
    }
}
