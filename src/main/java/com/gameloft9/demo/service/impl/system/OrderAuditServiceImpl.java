package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.OrderAuditMapper;
import com.gameloft9.demo.dataaccess.model.system.OrderAudit;
import com.gameloft9.demo.dataaccess.model.system.OrderAuditBean;
import com.gameloft9.demo.mgrframework.beans.response.AbstractResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.exceptions.BizException;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import com.gameloft9.demo.mgrframework.utils.StateUtil;
import com.gameloft9.demo.service.api.system.OrderAuditService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.Constants;
import com.gameloft9.demo.utils.StateUUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@Service
@Transactional(rollbackFor = Exception.class)
public class OrderAuditServiceImpl implements OrderAuditService {

    @Autowired
    OrderAuditMapper orderAuditMapper;

    /**
     * 分页模糊查询
     * @param page
     * @param limit
     * @param
     * @return
     */

    @Override
    public List<OrderAudit> findAll(String page, String limit, String productId ,String orderId,String applyUser) {
        PageRange pageRange = new PageRange(page, limit);

        return orderAuditMapper.findAll(pageRange.getStart(),pageRange.getEnd(),productId,orderId,applyUser);
    }

    /**
     *
     * @return
     */
    @Override
    public int dataCount() {
        return orderAuditMapper.dataCount();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public int deleteById(String id) {
        return orderAuditMapper.deleteById(id);
    }

    /**
     * 获取订单审核ID
     */
    @Override
    public OrderAuditBean getById(String id) {
        return orderAuditMapper.getById(id);
    }

    /**
     * 修改
     * @param
     * @return
     */
    @Override
    public Boolean update(OrderAuditBean orderAuditBean) {
        orderAuditMapper.update(orderAuditBean);
        return true;
    }

    /**
     * 驳回
     * @param
     * @return
     */
    @Override
    public Boolean backUpdate(OrderAuditBean orderAuditBean) {
        CheckUtil.notBlank(orderAuditBean.getId(),"订单id为空");
        orderAuditBean.setState(StateUUtil.APPLY_back);
        orderAuditMapper.backUpdate(orderAuditBean);
        return true;
    }

    /**
     * 审核成功
     * @param orderAuditBean
     * @return
     */
    @Override
    public Boolean passUpdate(OrderAuditBean orderAuditBean) {
        CheckUtil.notBlank(orderAuditBean.getId(),"订单id为空");
        orderAuditBean.setState(StateUtil.APPLY_PASS);
        orderAuditMapper.passUpdate(orderAuditBean);
        return true;
    }

    /**
     * 审核
     * @param orderAuditBean
     * @return
     */
    @Override
    public Boolean audit(OrderAuditBean orderAuditBean) {


        String st=orderAuditBean.getState();
        String sc="等待审核";
        if (sc!=st){
            throw new BizException(AbstractResult.CHECK_FAIL,"订单已撤回，无法审核");
        }
        CheckUtil.notBlank(orderAuditBean.getId(),"订单id为空");
       // orderAuditBean.setState(StateUtil.APPLY_PASS);

        /*String state=orderAuditBean.getState();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String str="等待审核";
        if (str.equals(state)){
            orderAuditBean.setState(StateUtil.APPLY_PASS);
        }else{orderAuditBean.setState(StateUtil.APPLY_FAIL);
        }*/

        orderAuditMapper.audit(orderAuditBean);
        return true;
    }

    /**
     * 仓库审核
     * @param orderAuditBean
     * @return
     */
    @Override
    public Boolean ware(OrderAuditBean orderAuditBean) {
        CheckUtil.notBlank(orderAuditBean.getId(),"订单id为空");
        orderAuditBean.setState(StateUUtil.APPLY_ware);
        orderAuditMapper.ware(orderAuditBean);
        return true;
    }
    /**
     * 11仓库审核
     * @param orderAuditBean
     * @return
     */
    @Override
    public Boolean depot(OrderAuditBean orderAuditBean) {
        CheckUtil.notBlank(orderAuditBean.getId(),"订单id为空");
        orderAuditBean.setState(StateUUtil.APPLY_pas);
        orderAuditMapper.audit(orderAuditBean);
        String state=orderAuditBean.getState();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String str="仓库通过审核";
        if (str.equals(state)){
            orderAuditBean.setState(StateUUtil.APPLY_pas);
        }else{orderAuditBean.setState(StateUUtil.APPLY_fai);
        }
        return true;
    }
    }



