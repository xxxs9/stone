package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.LenProductMapper;
import com.gameloft9.demo.dataaccess.model.system.LenProduct;
import com.gameloft9.demo.service.api.system.LenProductService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.UUIDUtil;
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
        product.setProductType(lenProduct.getProductType());
        product.setProductDescribe(lenProduct.getProductDescribe());
        product.setState(lenProduct.getState());
        product.setWasteId(lenProduct.getWasteId());
        if (mapper.insert(product)>0){
            return true;
        }else {
            return  false;
        }
    }

    @Override
    public boolean update(LenProduct lenProduct) {
        if (mapper.update(lenProduct)>0){
            return true;
        }else {
            return  false;
        }
    }

    @Override
    public boolean delete(String id) {
        if (mapper.delete(id)>0){
            return true;
        }else {
            return  false;
        }
    }

    @Override
    public int dataCount(String state) {
        return mapper.dataCount(state);
    }

    @Override
    public boolean changeState() {
        if (mapper.changeState()>0){
            return true;
        }else {
            return  false;
        }
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

    /**
     * 获取产品id
     * */
    @Override
    public List<String> getProductId() {
        return mapper.getProductId();
    }
}
