package com.gameloft9.demo.dataaccess.dao.system;


import com.gameloft9.demo.dataaccess.model.system.SysFinanceChargeOff;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: jc
 * @description:
 * @create: 2019/03/18 16:25
 */

public interface SysFinanceChargeOffMapper extends BaseMapper<SysFinanceChargeOff>{

    /**
     * 出账单集合
     *
     * @param start
     * @param end
     * @param payType
     * @return
     *  出账单集合
     */
    List<SysFinanceChargeOff> getAll(
            @Param("start") int start,
            @Param("end") int end,
            @Param("payType") int payType
    );

    /**
     * 条件查询总条数
     *
     * @param payType
     * @return
     *      总条数
     */
    int getCount(
            @Param("payType") int payType
    );

}
