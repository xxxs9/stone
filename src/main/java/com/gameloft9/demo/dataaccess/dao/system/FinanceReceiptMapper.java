package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.SysFinanceChargeOff;
import com.gameloft9.demo.dataaccess.model.system.SysFinanceReceipt;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/03/21 2019-03-21
 */

public interface FinanceReceiptMapper extends BaseMapper<SysFinanceReceipt>{

    /**
     * 出账单集合
     *
     * @param start
     * @param end
     * @param receiveType
     * @return
     *  出账单集合
     */
    List<SysFinanceReceipt> getAll(
            @Param("start") int start,
            @Param("end") int end,
            @Param("receiveType") int receiveType
    );

    /**
     * 条件查询总条数
     *
     * @param receiveType
     * @return
     *      总条数
     */
    int getCount(
            @Param("receiveType") int receiveType
    );

}
