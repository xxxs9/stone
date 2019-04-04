package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.ReturnGoodsAuditMapper;
import com.gameloft9.demo.dataaccess.model.system.ReturnGoodsAudit;
import com.gameloft9.demo.dataaccess.model.system.ShipmentOrder;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import com.gameloft9.demo.service.api.system.ReturnGoodsAuditService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.StateUUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(rollbackFor = Exception.class)
public class ReturnGoodsAuditServiceImpl implements ReturnGoodsAuditService {
    @Autowired
    ReturnGoodsAuditMapper returnGoodsAuditMapper;

    /**
     * 分页查找
     * @param page
     * @param limit
     * @param goodsName
     * @return
     */
    @Override
    public List<ReturnGoodsAudit> findAll(String page, String limit, String goodsName) {
        PageRange pageRange = new PageRange(page,limit);
        return returnGoodsAuditMapper.findAll(pageRange.getStart(),pageRange.getEnd(),goodsName);
    }
    /**
     * 查找条数
     * @param goodsName
     * @return
     */
    @Override
    public int countGetAll(String goodsName) {
        return returnGoodsAuditMapper.countGetAll(goodsName);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public int deleteById(String id) {
        return returnGoodsAuditMapper.deleteById(id);
    }

    /**
     * 获取id
     * @param id
     * @return
     */
    @Override
    public ReturnGoodsAudit getById(String id) {
        return returnGoodsAuditMapper.getById(id);
    }

    /**
     * 修改
     * @param returnGoodsAudit
     * @return
     */
    @Override
    public Boolean update(ReturnGoodsAudit returnGoodsAudit) {
        return returnGoodsAuditMapper.update(returnGoodsAudit);
    }

    /**
     * 增加
     * @param returnGoodsAudit
     * @return
     */
    @Override
    public int add(ReturnGoodsAudit returnGoodsAudit) {
        return returnGoodsAuditMapper.add(returnGoodsAudit);
    }

    /**
     * 财务查收
     * @param shipmentOrder
     * @return
     */
    @Override
    public Boolean accept(ShipmentOrder shipmentOrder) {
        CheckUtil.notBlank(shipmentOrder.getId(),"订单id为空");
        shipmentOrder.setState(StateUUtil.APPLY_accept);
        returnGoodsAuditMapper.accept(shipmentOrder);
        return true;
    }
}
