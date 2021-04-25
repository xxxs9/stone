package com.gameloft9.demo.dataaccess.model.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author: jc
 * @description:
 * @create: 2019/03/18 15:14
 */

@Data
public class SysFinanceSaleReceivable {

    /**ID*/
    private String id;

    /**收付类型*/
    private int auditType;

    /**单价*/
    public String unitPrice;

    /**总价*/
    public String totalPrice;

    /**实际价格*/
    public String actualBalance;

    /**制单人*/
    public String documentMaker;

    /**制单时间*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    public Date documentMakeTime;

    /**审核人*/
    public String auditUser;

    /**审核时间*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    public Date auditTime;

    /**审核描述*/
    public String auditDescribe;

    /**销售发货成品数量*/
    private String productNumber;

    /**审核状态*/
    public int auditState;

    /**销售发货单id*/
    private String saleId;

}
