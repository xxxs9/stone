package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.AllRoleMapper;
import com.gameloft9.demo.dataaccess.model.system.AllRole;
import com.gameloft9.demo.service.api.system.AllRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PurchaseOrder serviceImpl接口
 * @author OliverCH
 * @date 2019/04/01
 */
@Service
public class AllRoleImpl implements AllRoleService {

    @Autowired
    AllRoleMapper dao;

    /**通知部门*/
    @Override
    public List<AllRole> selectNotify(String limit) {
        return dao.selectNotify(limit);
    }

    /**供销商部门*/
    @Override
    public List<AllRole> selectSupplier(String limit) {
        return dao.selectSupplier(limit);
    }

    /**销售部门*/
    @Override
    public List<AllRole> selectSale(String limit) {
        return dao.selectSale(limit);
    }

    /**采购部门*/
    @Override
    public List<AllRole> selectProOrder(String limit) {
        return dao.selectProOrder(limit);
    }

    /**仓库部门*/
    @Override
    public List<AllRole> selectDepot(String limit) {
        return dao.selectDepot(limit);
    }

    /**生产部门*/
    @Override
    public List<AllRole> selectProduct(String limit) {
        return dao.selectProduct(limit);
    }

    /**财务部门*/
    public List<AllRole> selectFinance(String limit){
        return dao.selectFinance(limit);
    }
}
