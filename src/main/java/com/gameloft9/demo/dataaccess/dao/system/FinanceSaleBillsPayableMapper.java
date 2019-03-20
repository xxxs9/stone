package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.SysFinancePurchaseBillsPayable;
import com.gameloft9.demo.dataaccess.model.system.SysFinanceSaleBillsPayable;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/03/19 2019-03-19
 */

public interface FinanceSaleBillsPayableMapper extends BaseMapper<SysFinanceSaleBillsPayable>{

    /**
     * 销售应付单列表
     *
     * @param start 开始
     * @param end 结束
     * @param auditType 单子类型
     * @param startTime 制单时间
     * @param endTime 制单时间
     * @return
     *      销售应付单集合
     */
    List<SysFinanceSaleBillsPayable> getAll(
            @Param("start") int start,
            @Param("end") int end,
            @Param("auditType") int auditType,
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime
    );

    /**
     * 条件查询总条数
     *
     * @param auditType 单子类型
     * @param startTime 制单时间
     * @param endTime 制单时间
     * @return 条件查询总条数
     */
    int getCount(
            @Param("auditType") int auditType,
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime
    );
}
