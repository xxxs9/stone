package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.LenFormulaReachMapper;
import com.gameloft9.demo.dataaccess.dao.system.LenProductCheckMapper;
import com.gameloft9.demo.dataaccess.model.system.LenProductCheck;
import com.gameloft9.demo.service.api.system.LenProductCheckService;
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
 * @time : 2019-03-24 - 14:20
 * @description :
 */
@Service
public class LenProductCheckServiceImpl implements LenProductCheckService {
    @Autowired
    LenProductCheckMapper mapper;

    @Autowired
    LenFormulaReachMapper reachMapper;
    @Override
    public List<LenProductCheck> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public LenProductCheck getByPrimaryKey(String id) {
        return mapper.getByPrimaryKey(id);
    }

    @Override
    public List<LenProductCheck> selectByPage(String page, String limit, String checkUser, String state) {
        PageRange range = new PageRange(page, limit);
        return mapper.selectByPage(range.getStart(),range.getEnd(),checkUser,state);

    }



    @Override
    public boolean insert(String id, String producePlanId, String formulaReachId, String state, String checkUser, String checkTime, String wasteId, String checkRemark, String other1, String other2, String other3) {
        String uuid = UUIDUtil.getUUID();
        LenProductCheck check = new LenProductCheck();
        check.setId(uuid);
        check.setProducePlanId(producePlanId);
        check.setFormulaReachId(formulaReachId);
        check.setState(state);
        check.setWasteId(wasteId);
        check.setCheckRemark(checkRemark);
        check.setCheckUser(checkUser);
        Date date = DateUtil.str2Date(checkTime, "yyyy-MM-dd");
        check.setCheckTime(date);
        if (mapper.insert(check)>0){
            return true;
        }else{
            return  false;
        }

    }

    @Override
    public boolean update(String id, String producePlanId, String formulaReachId, String state, String checkUser, String checkTime, String wasteId, String checkRemark, String other1, String other2, String other3) {
        LenProductCheck check = new LenProductCheck();
        check.setId(id);
        check.setProducePlanId(producePlanId);
        check.setFormulaReachId(formulaReachId);
        check.setState(state);
        check.setCheckRemark(checkRemark);
        check.setCheckUser(checkUser);
        check.setWasteId(wasteId);
        Date date = DateUtil.str2Date(checkTime, "yyyy-MM-dd");
        check.setCheckTime(date);
        if (mapper.insert(check)>0){
            return true;
        }else{
            return  false;
        }
    }

    @Override
    public boolean delete(String id) {
        if (mapper.delete(id)>0){
            return true;
        }else{
            return  false;
        }
    }

    @Override
    public int dataCount(String state) {
        return mapper.dataCount(state);
    }

    /**
     * 检验费作废
     *
     * @param id
     * @return
     */
    @Override
    public boolean unUse(String id) {
        if (mapper.changeState(Constants.ZUOFEI,id)>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean changeState(String id) {
        /**
         * 1。查处该ID对应的领料单的状态，并更改
         * 2。在验收单中删除该记录
         */

        LenProductCheck check = mapper.getByPrimaryKey(id);

        String reachId = check.getFormulaReachId();

        reachMapper.changeState(Constants.JYWTG,reachId);

        if (mapper.delete(id)>0){
            return true;
        }else{
            return  false;
        }
    }

    @Override
    public boolean updateByPrimaryKeySelective(String id, String producePlanId, String formulaReachId, String state, String checkUser, String checkTime, String wasteId, String checkRemark) {
        return false;
    }

    @Override
    public boolean insertSelective(String id, String producePlanId, String formulaReachId, String state, String checkUser, String checkTime, String wasteId, String checkRemark) {
        return false;
    }
}
