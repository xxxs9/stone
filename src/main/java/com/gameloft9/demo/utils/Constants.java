package com.gameloft9.demo.utils;

import org.apache.shiro.SecurityUtils;

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

        /**采购部*/
        public static final String PURCHASE="采购部";

        /**销售部*/
        public static final String SALE="销售部";

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


    //    TODO(stateCode)
    /**
     * lennon_product_STATECODE
     */


    /**
     * 产品未提交
     */
    public static final String UN_TIJIAO = "0";
    /**
     * 产品提交
     */
    public static final String TIJIAO = "1";
    /**
     * 产品未审核
     */
    public static final String UN_AUDI = "2";
    /**
     * 产品审核
     */
    public static final String ADUI = "3";
    /**
     * 产品生产中
     */
    public static final String PRODUCING = "4";
    /**
     * 产品入库
     */
    public static final String INTO_DEPOT = "5";

    public static final String PRODUCE_ADMIN="生产部主管";

    public static final String PRODUCE_STADFF="生产部员工";

    /**
     * 仓库审核
     */
    public static final String UN_PASS="2";

    public static final String DEPOT_PASS="1";

    public static final String DEPOT_UN_AUDI="0";

    /**
     * 领料单状态码
     */
    public static  final String JYWTG="-1";
    /**
     * 检验未通过
     */

    /**
     * 等待审核
     */
    public static  final String DDSH="0";

    /**
     * 开始生产
     */
    public static final String KSSC="1";
    /**
     * 生产暂停
     */
    public static final String SCZT="2";

    /**
     * 生产完成
     */
    public static final String SCWC="3";
    /**
     * 继续生产
     */
    public static final String JXSC="1-1";


    public static final String ZUOFEI="-1";


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
     * 生产部授权情况
     * @return
     */
    public static int lennonPDAudi(){
        if (SecurityUtils.getSubject().hasRole(PRODUCE_ADMIN)){
            return 1;
        }else if (SecurityUtils.getSubject().hasRole(PRODUCE_STADFF)){
            return 0;
        }else{
            return -1;
        }

    }
    public static class productState{
        public static String UN_TIJIAO="1";
        public static String TIJIAO_UNAUDI="2";
        public static String AUDI_UNREACH="3";
        public static String REACH_UNFENPEI="4";
        public static String FENPEI_START_PRODUCE="5";
        public static String STOP_PRODUCE="6";
        public static String CONTINUE_PRODUCE="7";
        public static String COMPLETE_PRODUCE="8";
        public static String CHECK_GOOD="9";
        public static String CHECK_BAD="10";
        public static String UN_INTO_DEPOT="11";
        public static String INTO_DEPOT="12";

    }
    public static class productType{
        public static String BANCHENGPING_UNSOLD="1";
        public static String BANCHENGPING_SOLD="2";
        public static String CHENGPING_UNSOLD="3";
        public static String CHENGPING_SOLD="4";
    }
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
        public static final String CHECK_AUIDT_IN="盘点单审核中";
        public static final String CHECK_PASS="审核通过";
        public static final String CHECK_FAIL="审核未通过";




        /**kdsfjsldjflkdsjfldsf**/




    }

    public static final String MATERIAL = "原料";
    public static final String PRODUCT = "产品";

}
