package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.LenOperatorMapper;
import com.gameloft9.demo.dataaccess.model.system.LenOperator;
import com.gameloft9.demo.service.api.system.LenOperatorService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.OrderUtil;
import com.gameloft9.demo.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author : Lennon_Yuan
 * @time : 2019-04-11 - 22:31
 * @description :
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LenOperatorServiceImpl implements LenOperatorService {
    @Autowired
    LenOperatorMapper lenOperatorMapper;
    /**
     * 分页查询
     *
     * @param page
     * @param limit
     * @param operatorType
     * @param operatorUser
     * @return
     */
    @Override
    public List<LenOperator> selectByPage(String page, String limit, String operatorType, String operatorUser) {
        PageRange pageRange = new PageRange(page,limit);
        return lenOperatorMapper.selectByPage(pageRange.getStart(),pageRange.getEnd(),operatorType,operatorUser);
    }

    /**
     * 增加记录
     *
     * @param lenOperator
     * @return
     */
    @Override
    public boolean insertSelective(LenOperator lenOperator) {
        String uuid = UUIDUtil.getUUID();
        LenOperator Operator = new LenOperator();
        Operator.setId(uuid);
        String op = OrderUtil.lenOrderNumber("OP");
        Operator.setOperatorNo(op);
        Operator.setOperatorUser(lenOperator.getOperatorUser());
        Operator.setOperatorTime(new Date());
        Operator.setOperatorType(lenOperator.getOperatorType());
        Operator.setOperatorRemark(lenOperator.getOperatorRemark());
        Operator.setOther1(lenOperator.getOther1());
        Operator.setOther2(lenOperator.getOther2());
        Operator.setOther3(lenOperator.getOther3());
        if (lenOperatorMapper.insertSelective(Operator)>0){
            return true;
        }else {
            return false;
        }
    }
    public void insertSelective1(String operatorUser,String operatorType,String operatorRemark,String other1,String other2 ,String other3) {
        String uuid = UUIDUtil.getUUID();
        LenOperator Operator = new LenOperator();
        Operator.setId(uuid);
        String op = OrderUtil.lenOrderNumber("OP");
        Operator.setOperatorNo(op);
        Operator.setOperatorUser(operatorUser);
        Operator.setOperatorTime(new Date());
        Operator.setOperatorType(operatorType);
        Operator.setOperatorRemark(operatorRemark);
        Operator.setOther1(other1);
        Operator.setOther2(other2);
        Operator.setOther3(other3);
        lenOperatorMapper.insertSelective(Operator);

    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Override
    public LenOperator getById(String id) {
        return lenOperatorMapper.getById(id);
    }

    /**
     * 数据统计
     *
     * @return
     */
    @Override
    public int dataCount() {
        return lenOperatorMapper.dataCount();
    }
}
