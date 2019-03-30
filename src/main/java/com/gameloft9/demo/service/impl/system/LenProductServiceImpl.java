package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.LenProductMapper;
import com.gameloft9.demo.dataaccess.model.system.LenProduct;
import com.gameloft9.demo.service.api.system.LenProductService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.Constants;
import com.gameloft9.demo.utils.UUIDUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @packageName: com.gameloft9.demo.service.impl.system
 * @author: Lennon_Yuan
 * @time: 2019/3/19 0019 - 下午 3:31
 * @description:
 */
@Service
public class LenProductServiceImpl implements LenProductService {

    @Autowired
    LenProductMapper mapper;


    @Override
    public List<LenProduct> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public LenProduct getByPrimaryKey(String id) {
        return mapper.getByPrimaryKey(id);
    }

    @Override
    public List<LenProduct> selectByPage(String page, String limit, String productName, String state) {
        PageRange pageRange = new PageRange(page, limit);
        List<LenProduct> lenProducts = mapper.selectByPage(pageRange.getStart(), pageRange.getEnd(), productName, state);
        return lenProducts;
    }

    @Override
    public boolean insert(LenProduct lenProduct) {
        String uuid = UUIDUtil.getUUID();
        LenProduct product = new LenProduct();
        product.setId(uuid);
        product.setProductName(lenProduct.getProductName());
        product.setProductNumber(lenProduct.getProductNumber());
        product.setCanSold(lenProduct.getCanSold());
        product.setSupportPrice(lenProduct.getSupportPrice());
        product.setProductType(lenProduct.getProductType());
        product.setProductDescribe(lenProduct.getProductDescribe());
        product.setState(lenProduct.getState());

        product.setOther1(lenProduct.getOther1());
        product.setOther2(lenProduct.getOther2());
        product.setOther3(lenProduct.getOther3());
        if (mapper.insert(product) > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean update(LenProduct lenProduct) {
        if (mapper.update(lenProduct) > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole(Constants.PRODUCE_ADMIN)) {
            if (mapper.delete(id) > 0) {
                return true;
            } else {
                return false;
            }
        }else{return false;
        }
    }

    @Override
    public int dataCount(String state) {
        return mapper.dataCount(state);
    }


    @Override
    public boolean stepBack(String id) {
        /**
         * 只有在未审核（包括）之前的状态才能进行撤回操作
         */

        LenProduct len = mapper.getByPrimaryKey(id);
        String state = len.getState();
        if (Constants.TIJIAO.equals(state)){
            if (mapper.changeState(Constants.UN_TIJIAO,id)>0) {
                return true;
            }else{
                return false;

            }
        }else if (Constants.UN_AUDI.equals(state)){
            if (mapper.changeState(Constants.TIJIAO,id)>0){
                return true;
            }else {
                return false;
            }
        }else{
            return false;
        }

    }

    /**
     * 更改产品状态
     *
     * @param
     * @return
     */
    @Override
    public boolean changeState(String id) {
        LenProduct product = mapper.getByPrimaryKey(id);
        String state = product.getState();
        /*获取shiro权限*/
        Subject subject = SecurityUtils.getSubject();
        /*当前状态为 0*/
        if (Constants.UN_TIJIAO.equals(state)) {

            if (mapper.changeState(Constants.TIJIAO, id) > 0) {
                return true;
            } else {

                return false;
            }
            /*当前状态 state：1*/
        } else if (Constants.TIJIAO.equals(state) ) {

            if (mapper.changeState(Constants.UN_AUDI, id) > 0) {
                return true;
            } else {
                return false;
            }

            /*state:2*/
        } else if (Constants.UN_AUDI.equals(state)) {
            if (subject.hasRole(Constants.PRODUCE_ADMIN)) {
                if (mapper.changeState(Constants.ADUI, id) > 0) {
                    return true;
                } else {
                    return false;
                }
            }

        } else if (Constants.ADUI.equals(state)) {
            if (subject.hasRole(Constants.PRODUCE_ADMIN)) {
                if (mapper.changeState(Constants.PRODUCING, id) > 0) {
                    return true;
                } else {
                    return false;
                }
            }
        } else if (Constants.PRODUCING.equals( state)) {
            if (subject.hasRole(Constants.PRODUCE_ADMIN)) {
                if (mapper.changeState(Constants.INTO_DEPOT, id) > 0) {
                    return true;
                } else {
                    return false;
                }
            }


    }else {
        return false;
    }



            return false;

                }



    @Override
    public boolean updateByPrimaryKeySelective(LenProduct lenProduct) {
        if (mapper.updateByPrimaryKeySelective(lenProduct)>0){
            return true;
        }else {
            return  false;
        }
    }

    @Override
    public boolean insertSelective(LenProduct lenProduct) {
        if (mapper.insertSelective(lenProduct)>0){
            return true;
        }else {
            return  false;
        }
    }

    @Override
    public boolean changeProState(String state, String id) {
       if (mapper.changeState(state,id)>0){
           return true;
       }else {
          return false;
       }
    }

    @Override
    public List<LenProduct> selectByState() {
        return mapper.selectByState();
    }
}
