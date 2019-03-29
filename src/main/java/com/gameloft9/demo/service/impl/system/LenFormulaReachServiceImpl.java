package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.LenFormulaReachMapper;
import com.gameloft9.demo.dataaccess.dao.system.LenProducePlanMapper;
import com.gameloft9.demo.dataaccess.model.system.LenFormulaReach;
import com.gameloft9.demo.service.api.system.LenFormulaReachService;
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
 * @time : 2019-03-23 - 17:41
 * @description :
 */

@Service
public class LenFormulaReachServiceImpl implements LenFormulaReachService {

    @Autowired
    LenFormulaReachMapper mapper;

    @Autowired
    LenProducePlanMapper plan;

    @Override
    public List<LenFormulaReach> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public LenFormulaReach getByPrimaryKey(String id) {
        return mapper.getByPrimaryKey(id);
    }

    @Override
    public List<LenFormulaReach> selectByPage(String page,String limit, String productId,String reachUser) {
        /**
         * 1 取出List
         * 2 遍历元素
         * 3 查询仓库授权状态
         * 4 更改state状态
         */
        PageRange pageRange = new PageRange(page, limit);

        List<LenFormulaReach> list = mapper.selectByPage(pageRange.getStart(), pageRange.getEnd(), productId, reachUser);

        for (LenFormulaReach reach:list){


            /**
             * 如果仓库的状态为 1
             * 则state =1
             */
            if (Constants.DEPOT_PASS.equals(reach.getDepotAudi())){
                String id = reach.getId();
                mapper.changeState(Constants.KSSC, id);
                /**
                 * 如果仓库审核是未通过
                 * 则state =0 (仓库未审核则状态未提交)
                 */
            }/*else if (Constants.DEPOT_UN_AUDI.equals(reach.getDepotAudi())){
                String id = reach.getId();
                mapper.changeState(Constants.UN_TIJIAO, id);

            }*/
            else{
                return list;
            }
        }
        List<LenFormulaReach> list2 = mapper.selectByPage(pageRange.getStart(), pageRange.getEnd(), productId, reachUser);
        return list2;
    }

    @Override
    public boolean insert(String id, String productId, String productFormulaId, String produceFormulaDetailId, String depotAudi, String formulaBack, String state, String reachUser, String reachTime) {

        LenFormulaReach reach = new LenFormulaReach();
        Date date = DateUtil.str2Date(reachTime, "yyyy-MM-dd");
        String uuid = UUIDUtil.getUUID();
        reach.setId(uuid);
        reach.setReachTime(date);
        reach.setProductId(productId);
        reach.setProduceFormulaId(productFormulaId);
        reach.setProduceFormulaDetailId(produceFormulaDetailId);
        reach.setDepotAudi(depotAudi);
        reach.setReachUser(reachUser);
        reach.setState(state);
        reach.setFormulaBack(formulaBack);

        if (mapper.insert(reach)>0){
            return true;
        }else{return  false;}
    }

    @Override
    public boolean update(String id, String productId, String productFormulaId, String produceFormulaDetailId, String depotAudi, String formulaBack, String state, String reachUser, String reachTime) {
        Date date = DateUtil.str2Date(reachTime, "yyyy-MM-dd");
        LenFormulaReach reach = new LenFormulaReach();

       reach.setId(id);
        reach.setReachTime(date);
        reach.setProductId(productId);
        reach.setProduceFormulaId(productFormulaId);
        reach.setProduceFormulaDetailId(produceFormulaDetailId);
        reach.setDepotAudi(depotAudi);
        reach.setReachUser(reachUser);
        reach.setState(state);
        reach.setFormulaBack(formulaBack);

        if (mapper.update(reach)>0){
            return true;
        }else {
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
    public int dataCount()
    {
        return mapper.dataCount();
    }


    @Override
    public boolean stopProduct(String id) {
       if (mapper.changeState(Constants.SCZT,id)>0){
           return true;
       }else{
           return false;
       }

    }

    @Override
    public boolean goOn( String id) {
        if (mapper.changeState(Constants.JXSC,id)>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean complete(String id) {
        if (mapper.changeState(Constants.SCWC,id)>0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 更改仓库授权状态
     * @return
     */
    @Override
    public boolean changeDepotAudi(String depot,String id) {
        /**
         * 仓库传来参数进行授权
         */
        if (mapper.changeDepot(depot,id)>0){
            return true;
        }

        return false;
    }

    /**
     * todo(改变状态)
     * @param id
     * @return
     */
    @Override
    public boolean changeState(String id) {
        LenFormulaReach reach = mapper.getByPrimaryKey(id);
        String state = reach.getState();
        /*todo*/

        return false;
    }
/*
    @Override
    public boolean updateByPrimaryKeySelective(String id, String productId, String productFormulaId, String ProductFormulaDetailId, String depotAudi, String formulaBack, String state, String reachUser, String reachTime) {
        if (mapper.updateByPrimaryKeySelective(id, productId, productFormulaId, ProductFormulaDetailId, depotAudi, formulaBack, state, reachUser, reachTime)>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean insertSelective(String id, String productId, String productFormulaId, String ProductFormulaDetailId, String depotAudi, String formulaBack, String state, String reachUser, String reachTime) {
        if (mapper.insert(id, productId, productFormulaId, ProductFormulaDetailId, depotAudi, formulaBack, state, reachUser, reachTime)>0){
            return true;
        }else{
            return false;
        }
    }*/
}
