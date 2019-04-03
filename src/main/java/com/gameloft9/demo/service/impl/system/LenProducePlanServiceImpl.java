package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.LenProducePlanMapper;
import com.gameloft9.demo.dataaccess.model.system.LenProducePlan;
import com.gameloft9.demo.service.api.system.LenProducePlanService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.Constants;
import com.gameloft9.demo.utils.DateUtil;
import com.gameloft9.demo.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author : Lennon_Yuan
 * @time : 2019-03-23 - 14:20
 * @description :
 */

@Service
public class LenProducePlanServiceImpl implements LenProducePlanService {

    @Autowired
    LenProducePlanMapper mapper;

    @Override
    public List<LenProducePlan> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public LenProducePlan getByPrimaryKey(String id) {
        return mapper.getByPrimaryKey(id);
    }

    @Override
    public List<LenProducePlan> selectByPage(String page, String limit, String productId, String state) {
        PageRange pageRange = new PageRange(page,limit);

        return mapper.selectByPage(pageRange.getStart(),pageRange.getEnd(),productId,state);
    }

    @Override
    public boolean insert(String productId, String planNumber, String realNumber, String state, String goodNumber, String produceDate, String finishDate, String billDate, String billCycle, String planRemark, String other1, String other2, String other3) {
        Date pd = DateUtil.str2Date(produceDate, "yyyy-MM-dd");
        Date fd = DateUtil.str2Date(finishDate, "yyyy-MM-dd");
        Date bd = DateUtil.str2Date(billDate, "yyyy-MM-dd");
        LenProducePlan plan = new LenProducePlan();
        String uuid = UUIDUtil.getUUID();
        plan.setId(uuid);
        plan.setProductId(productId);
        plan.setGoodNumber(goodNumber);
        plan.setPlanNumber(planNumber);
        plan.setRealNumber(realNumber);
        plan.setProduceDate(pd);
        plan.setFinishDate(fd);
        plan.setBillDate(bd);
        plan.setBillCycle(billCycle);
        plan.setState(state);
        plan.setPlanRemark(planRemark);
        plan.setOther1(other1);
        plan.setOther2(other2);
        plan.setOther3(other3);
        if (mapper.insert(plan)>0){
            return  true;
        } else {
            return false;
        }
    }

    @Override
    public boolean update(String id,String productId, String planNumber, String realNumber, String state, String goodNumber, String produceDate, String finishDate, String billDate, String billCycle, String planRemark, String other1, String other2, String other3) {
        LenProducePlan plan = new LenProducePlan();
        Date pd = DateUtil.str2Date(produceDate, "yyyy-MM-dd");
        Date fd = DateUtil.str2Date(finishDate, "yyyy-MM-dd");
        Date bd = DateUtil.str2Date(billDate, "yyyy-MM-dd");
        plan.setId(id);
        plan.setProductId(productId);
        plan.setGoodNumber(goodNumber);
        plan.setPlanNumber(planNumber);
        plan.setRealNumber(realNumber);
        plan.setProduceDate(pd);
        plan.setFinishDate(fd);
        plan.setBillDate(bd);
        plan.setBillCycle(billCycle);
        plan.setState(state);
        plan.setPlanRemark(planRemark);
        plan.setOther1(other1);
        plan.setOther2(other2);
        plan.setOther3(other3);
        if (mapper.update(plan)>0){
            return  true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        if (mapper.delete(id)>0){
            return  true;
        } else {
            return false;
        }
    }

    @Override
    public int dataCount(String state) {
        return mapper.dataCount(state);
    }

    /**
     * todo(权限控制模块)
     * @param id
     * @return
     */
    @Override
    public boolean changeState(String id) {
        if (mapper.changeState(null,id)>0){
            return  true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateByPrimaryKeySelective(String productId, String planNumber, String realNumber, String state, String goodNumber, String produceDate, String finishDate, String billDate, String billCycle, String planRemark, String other1, String other2, String other3) {
        return false;
    }

    @Override
    public LenProducePlan findId(String productId) {
        return mapper.findId(productId);
    }

    @Override
    public boolean changeOther(String id) {
        mapper.changeOther1(Constants.TIJIAO,id);
        return false;
    }

    @Override
    public boolean insertSelective(String productId, String planNumber, String realNumber, String state, String goodNumber, String produceDate, String finishDate, String billDate, String billCycle, String planRemark, String other1, String other2, String other3) {
        return false;
    }
}
