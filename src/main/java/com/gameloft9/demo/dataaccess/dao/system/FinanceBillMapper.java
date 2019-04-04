package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.SysFinanceBill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/03/26 2019-03-26
 */
public interface FinanceBillMapper extends BaseMapper<SysFinanceBill>{

    /**
     * 账单列表
     *
     * @param start 开始
     * @param end 结束
     * @return
     *      列表
     */
    List<SysFinanceBill> getAll(@Param("start") int start, @Param("end") int end);

    /**
     * 获取总条数
     *
     * @return
     *      总条数
     */
    int getCount();

    /**
     * 获取所有的bill
     * @return
     *      bill集合
     */
    List<SysFinanceBill> getAllBill();

    /**
     * 日报表
     */
    List<SysFinanceBill> getDayBill();

    /**
     * 周报表
     */
    List<SysFinanceBill> getWeekBill();

    /**
     * 月报表
     */
    List<SysFinanceBill> getMonthBill();

}
