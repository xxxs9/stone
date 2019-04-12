package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.LenGoodsProductMapper;
import com.gameloft9.demo.dataaccess.model.system.LenGoodsProduct;
import com.gameloft9.demo.service.api.system.LenGoodsProductService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.Constants;
import com.gameloft9.demo.utils.OrderUtil;
import com.gameloft9.demo.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : Lennon_Yuan
 * @time : 2019-04-05 - 08:56
 * @description :
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LenGoodsProductServiceImpl implements LenGoodsProductService {

    @Autowired
    LenGoodsProductMapper mapper;

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<LenGoodsProduct> selectAll() {
        return mapper.selectAll();
    }

    /**
     * 主键查询
     *
     * @param id
     * @return
     */
    @Override
    public LenGoodsProduct getByPrimaryKey(String id) {
        return mapper.getByPrimaryKey(id);
    }

    /**
     * 主键查状态
     *
     * @param id
     * @return
     */
    @Override
    public String getStateById(String id) {
        return null;
    }

    /**
     * 修改状态
     *
     * @param zhuangtai
     * @param id
     * @return
     */
    @Override
    public boolean changeState(String zhuangtai, String id) {
        if (mapper.changeState(zhuangtai,id)>0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 计数
     *
     * @return
     */
    @Override
    public int dataCount() {
        return mapper.dataCount();
    }

    @Override
    public List<LenGoodsProduct> selectByPage(String page, String limit, String pname) {
        PageRange pageRange = new PageRange(page, limit);
        return mapper.selectByPage(pageRange.getStart(),pageRange.getEnd(),pname);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(String id) {
        if (mapper.delete(id)>0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 修改
     *
     * @param lenGoodsProduct
     * @return
     */
    @Override
    public boolean update(LenGoodsProduct lenGoodsProduct) {
        if (mapper.update(lenGoodsProduct)>0){

            return true;
        }else {
            return false;
        }
    }

    /**
     * 插入
     *
     * @param lenGoodsProduct
     * @return
     */
    @Override
    public boolean insert(LenGoodsProduct lenGoodsProduct) {
        //判断价格是否为负数
        int anInt = Integer.parseInt(lenGoodsProduct.getPprice());
        if(anInt>0) {
            String uuid = UUIDUtil.getUUID();
            LenGoodsProduct product = new LenGoodsProduct();
            product.setId(uuid);
            product.setZhuangtai(Constants.productState.UNYISHENGCHANG);
            product.setBianhao(OrderUtil.lenOrderNumber("L"));
            product.setCaizhi(lenGoodsProduct.getCaizhi());
            product.setChichun(lenGoodsProduct.getChichun());
            product.setChangdi(lenGoodsProduct.getChangdi());
            product.setLeixing(lenGoodsProduct.getLeixing());
            product.setPname(lenGoodsProduct.getPname());
            product.setPprice(lenGoodsProduct.getPprice());
            product.setStyle1(lenGoodsProduct.getStyle1());
            product.setPicihao(lenGoodsProduct.getPicihao());
            product.setXinghao(lenGoodsProduct.getXinghao());
            product.setYanse(lenGoodsProduct.getYanse());
            product.setOther1(lenGoodsProduct.getOther1());
            product.setOther3(lenGoodsProduct.getOther2());
            product.setOther1(lenGoodsProduct.getOther3());
            if (mapper.insert(product) > 0) {
                return true;
            } else {
                return false;
            }
        }else{
            return false;
        }
    }

    /**
     * 根据状态查询
     *
     * @param zhuangtai
     * @return
     */
    @Override
    public List<LenGoodsProduct> selectByState(String zhuangtai) {
        return mapper.selectByState(zhuangtai);
    }

    /**
     * 显示未生产
     *
     * @return
     */
    @Override
    public List<LenGoodsProduct> selectByUnProduce() {
        return mapper.selectByUnProduce();
    }
}
