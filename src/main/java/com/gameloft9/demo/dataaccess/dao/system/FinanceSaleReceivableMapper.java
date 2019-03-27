package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.SysFinanceSaleBillsPayable;
import com.gameloft9.demo.dataaccess.model.system.SysFinanceSaleReceivable;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/03/19 2019-03-19
 */

public interface FinanceSaleReceivableMapper extends BaseMapper<SysFinanceSaleReceivable> {
    /**
     * 销售应收单列表
     *
     * @param start 开始
     * @param end 结束
     * @param auditState 单子类型
     * @return
     *      销售应收单集合
     */
    List<SysFinanceSaleReceivable> getAll(
            @Param("start") int start,
            @Param("end") int end,
            @Param("auditState") int auditState
    );

    /**
     * 条件查询总条数
     *
     * @param auditState 单子类型
     * @return 条件查询总条数
     */
    int getCount(
            @Param("auditState") int auditState
    );
}
