package com.gameloft9.demo.utils;

import lombok.Data;

/**
 * 常量
 * Created by gameloft9 on 2017/12/13.
 */
public class Constants {

    /**
     * 菜单类型
     * */
    public enum MenuType{
        FIRST_CLASS("1","一级菜单"),
        SECOND_CLASS("2","二级菜单");

        public String value;
        public String name;

        MenuType(String value,String name){
            this.value = value;
            this.name = name;
        }
    }

    /**
     * 菜单图标类型,具体值请参考阿里iconfont
     * */
    public enum MenuIcon{

        SHE_ZHI("icon-shezhi1","设置"),
        ZHANG_HU("icon-zhanghu","账户"),
        BIAN_JI("icon-edit","编辑"),
        GONG_GAO("icon-gonggao","公告"),
        WEN_BEN("icon-text","文本");

        public String value;
        public String name;

        MenuIcon(String value,String name){
            this.value = value;
            this.name = name;
        }
    }

    /**
     * 上传附件业务类型
     * */
    public enum AttachmentType{

        USER_FACE("userFace");//用户头像

        public String value;

        AttachmentType(String name){
            this.value = name;
        }
    }

    /**
     * 一级菜单初始CODE
     * */
    public static final String INIT_FIRST_CLASS_MENU_CODE = "1";

    /**
     * 二级菜单初始CODE
     * */
    public static final String INIT_SECOND_CLASS_MENU_CODE = "1";

    /**
     * 组织机构编码规则
     * */
    public static class OrgCodeRule{
        /**根机构编码*/
        public static final String ROOT_CODE = "010001";
        /**初始序号*/
        public static final String INIT_SORT = "0001";
        /**初始前缀*/
        public static final String INIT_LEVEL = "01";
    }

    /**
     * 初始化密码
     * */
    public static final String INIT_PWD = "123456";

    /**
     * 财务须知应付、收款单类型
     */
    public static class Finance{
        /**采购订单应付单类型*/
        public static final Integer PURCHASE_PAYABLE=1;

        /**销售出货应收单类型*/
        public static final Integer SALE_RECEIVABLE=2;

        /**采购订单应收单类型*/
        public static final Integer PURCHASE_RECEIVABLE=3;

        /**销售（客户退货）应付单类型*/
        public static final Integer SALE_PAYABLE=4;

        /**未生成对应申请单*/
        public static final Integer APPLY_ORDER_UNCOMMIT=1;

        /**待审核*/
        public static final Integer APPLY_ORDER_UNAUDIT=2;

        /**审核通过*/
        public static final Integer APPLY_ORDER_PASS=3;

        /**审核不通过*/
        public static final Integer APPLY_ORDER_UNPASS=4;

    }

    /**
     * 审核状态：采购人员
     */
    public static class PurchaseState{
        public static final String APPLY_NO_SUBMIT="未提交";
        public static final String APPLY_WAITING="提交审核中";
        public static final String APPLY_PASS="审核通过";
        public static final String APPLY_FAIL="审核未通过";
    }

    /**
     * 审核状态：财务
     */
    public static class FinanceState{
        public static final String APPLY_PASS_WAIT="待审核";
        public static final String APPLY_PAY="待付款";
        public static final String APPLY_PASS_PAY="已付款";
        public static final String NO_PASS="未通过";

    }

    /**
     * 审核状态：仓库管理人员
     */
    public static class DepotState{
        public static final String DEPOT_PASS_OUT="已出库";
        public static final String DEPOT_PASS_IN="已入库";
        public static final String DEPOT_SURE="确认中";
        public static final String DEPOT_NO_SUNMIT="未提交";
        public static final String DEPOT_WAITING_IN="入库单审核中";
        public static final String DEPOT_WAITING_OUT="出库单审核中";
        public static final String DEPOT_PASS="审核通过";
        public static final String DEPOT_FAIL="审核未通过";
    }
    /**
     *仓库人员的机构名
     */
    public static final String DPOT_ORG = "供应部";

    /**
     * 仓库单分类
     */
    public static class Depot{
        public static final String ORDER_OUT="出库单";
        public static final String ORDER_IN="入库单";

    }

    /**
     * 盘点单状态
     */
    public static class DepotInventoryCheck{
        public static final String CHECK_IN="盘点中";
        public static final String CHECK_OUT="盘点结束";

    }

    public static final String MATERIAL = "原料";
    public static final String PRODUCT = "产品";

}
