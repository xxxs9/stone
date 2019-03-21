package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.PurchaseOrderMapper;
import com.gameloft9.demo.dataaccess.model.system.PurchaseOrder;
import com.gameloft9.demo.service.api.system.FinanceCheckPendingService;
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
    PurchaseOrderMapper purchaseOrderMapper;


    public List<PurchaseOrder> getPurchaseList() {
        return purchaseOrderMapper.getAllPurchaseOrderByState();
    }
}
