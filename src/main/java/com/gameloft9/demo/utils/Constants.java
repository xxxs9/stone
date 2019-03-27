package com.gameloft9.demo.utils;

/**
 * 常量
 * Created by gameloft9 on 2017/12/13.
 */
public class Constants<UNtijiao> {

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

}
