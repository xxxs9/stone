package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.LenBillCheckMapper;
import com.gameloft9.demo.dataaccess.model.system.LenBillCheck;
import com.gameloft9.demo.service.api.system.LenBillCheckService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.DateUtil;
import com.gameloft9.demo.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author : Lennon_Yuan
 * @time : 2019-03-29 - 16:45
 * @description :
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class LenBillCheckServiceImpl implements LenBillCheckService {

    @Autowired
    LenBillCheckMapper mapper;

    @Override
    public List<LenBillCheck> selectAll() {

        return mapper.selectAll();
    }

    @Override
    public LenBillCheck getByPrimaryKey(String id) {
        return mapper.getByPrimaryKey(id);
    }

    @Override
    public List<LenBillCheck> selectByPage(String page, String limit, String checkUser, String state) {
        PageRange range = new PageRange(page,limit);
        return mapper.selectByPage(range.getStart(),range.getEnd(),checkUser,state);
    }

    /**
     *
     * @param id
     * @param productName
     * @param state
     * @param checkNumber
     * @param checkDate
     * @param checkUser
     * @param checkRemark
     * @param reachId
     * @param productId
     * @param planId
     * @return
     */
    @Override
    public boolean finalInsert(String id, String productName, String state, String checkNumber, String checkDate, String checkUser, String checkRemark, String reachId, String productId, String planId) {
        String uuid = UUIDUtil.getUUID();
        Date date = DateUtil.str2Date(checkDate, "yyyy-MM-dd");
        LenBillCheck billCheck = new LenBillCheck();
        billCheck.setId(uuid);
        billCheck.setProductName(productName);
        billCheck.setState(state);
        billCheck.setCheckNumber(checkNumber);
        billCheck.setCheckDate(date);

        if (mapper.insert(billCheck)>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean insert(String id,String productName , String state, String checkNumber, String checkDate, String checkUser, String checkRemark, String reachId, String productId, String planId) {
        String uuid = UUIDUtil.getUUID();
        LenBillCheck billCheck = new LenBillCheck();
        billCheck.setId(uuid);
       // billCheck.setState(Constants.productState.STOP_PRODUCE);
        billCheck.setState(state);
        billCheck.setCheckNumber(checkNumber);
        billCheck.setCheckDate(new Date());
        billCheck.setCheckUser(checkUser);
        billCheck.setCheckRemark(checkRemark);
        billCheck.setReachId(reachId);
        billCheck.setPlanId(planId);
        billCheck.setProductId(productId);
        billCheck.setProductName(productName);
        if (mapper.insert(billCheck)>0){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public boolean update(String id, String productName ,String state, String checkNumber, String checkDate, String checkUser, String checkRemark, String reachId, String productId, String planId) {
        Date date = DateUtil.str2Date(checkDate, "yyyy-MM-dd");
        LenBillCheck billCheck = new LenBillCheck();
        billCheck.setId(id);
        billCheck.setState(state);
        billCheck.setProductName(productName);
        billCheck.setCheckNumber(checkNumber);
        billCheck.setCheckDate(date);
        billCheck.setCheckUser(checkUser);
        billCheck.setCheckRemark(checkRemark);
        billCheck.setReachId(reachId);
        billCheck.setPlanId(planId);
        billCheck.setProductId(productId);
        if (mapper.update(billCheck)>0){
            return true;
        }else{
            return false;
        }
    }


    @Override
    public boolean delete(String id) {
        if (mapper.delete(id)>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int dataCount(String state) {
        return mapper.dataCount(state);
    }

    @Override
    public boolean changeState(String state, String id) {

        if (mapper.changeState(state,id)>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<LenBillCheck> selectByState() {
        return mapper.selectByState();
    }

    @Override
    public LenBillCheck selectByProductId(String productId) {
        return mapper.selectByProductId(productId);
    }
}