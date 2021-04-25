package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.FinancePurchaseBillsPayableMapper;
import com.gameloft9.demo.dataaccess.dao.system.PurchaseOrderMapper;
import com.gameloft9.demo.dataaccess.model.system.PurchaseOrder;
import com.gameloft9.demo.dataaccess.model.system.SysFinancePurchaseBillsPayable;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import com.gameloft9.demo.service.api.system.FinanceCheckPendingService;
import com.gameloft9.demo.utils.NumberUtil;
import com.gameloft9.demo.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/03/20 2019-03-20
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FinanceCheckPendingServiceImpl implements FinanceCheckPendingService {

    @Autowired
    /**
     * 沧海提供的接口
     */
    PurchaseOrderMapper purchaseOrderMapper;

    @Autowired
    FinancePurchaseBillsPayableMapper purchaseBillsPayableMapper;

    /**
     * @return
     *      采购订单集合
     */
    public List<PurchaseOrder> getPurchaseList() {
        return purchaseOrderMapper.getAllPurchaseOrderByState();
    }

    /**
     *
     * @param id id
     * @param financeAuditDescribe 审核内容
     * @param isAgree 是够同意
     * @return
     */
    public Boolean auditingPurchaseOrder(String id ,String financeAuditDescribe, String isAgree) {
        CheckUtil.notBlank(id, "id为空");
        //根据id获取订单信息（沧海提供的接口）
        PurchaseOrder purchaseOrder = purchaseOrderMapper.selectByPrimaryKey(id);
        //TODO...等待沧海push
        //purchaseOrder.setFinanceAuditDescribe(financeAuditDescribe);
        String agree = "agree";
        if(agree.equals(isAgree)){
            //修改订单的状态
            //TODO...等待常量
            purchaseOrder.setState("");

            //审核通过后，自动生成应付单（部分字段），需员工填写提交
            SysFinancePurchaseBillsPayable purchaseBillsPayable = new SysFinancePurchaseBillsPayable();

            //应付单id
            purchaseBillsPayable.setId(UUIDUtil.getUUID());

            //订单id
            purchaseBillsPayable.setPurchaseOrderId(purchaseOrder.getId());

            //单价
            purchaseBillsPayable.setUnitPrice(purchaseOrder.getPrice());

            //数量
            purchaseBillsPayable.setGoodsNumber(purchaseOrder.getGoodsNumber());

            //总价
            int totalPrice = (NumberUtil.strToInt(purchaseBillsPayable.getUnitPrice()))*
                    (NumberUtil.strToInt(purchaseBillsPayable.getGoodsNumber()));
            purchaseBillsPayable.setTotalPrice(totalPrice+"");

            //提交表单，其余内容财务员工手动填写
            purchaseBillsPayableMapper.add(purchaseBillsPayable);

        }else{
            //修改订单的状态

            //TODO...等待常量
            purchaseOrder.setState("");
        }

        return true;
    }


}
