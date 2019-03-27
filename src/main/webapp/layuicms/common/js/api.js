/**
 * api接口列表
 * Created by gameloft9 on 2018/7/19.
 */
layui.define(['$tool','jquery'], function (exports) {

    var $tool = layui.$tool,
        $ = layui.jquery;// 拿到模块变量

    /**
     * 封装一个post
     * */
    function doPost(url,req,successCallback,errorCallback) {
        $.ajax({
            url:url,
            data:req,
            method:"post",
            success:function (data) {
                successCallback(data);
            },
            error:function (error) {
                errorCallback(error);
            }
        });
    }

    /**
     * 封装一个get
     * */
    function doGet(url,req,successCallback,errorCallback) {
        $.ajax({
            url:url,
            data:req,
            method:"get",
            success:function (data) {
                successCallback(data);
            },
            error:function (error) {
                errorCallback(error);
            }
        });
    }

    /**
     * 封装一个支持更多参数的post
     * */
    function doComplexPost(url,req,config,successCallback,errorCallback) {
        var defaultConfig = {
            url:url,
            data:req,
            method:"post",
            success:function (data) {
                successCallback(data);
            },
            error:function (error) {
                errorCallback(error);
            }
        };
        var ajaxConfig = $.extend({},config,defaultConfig); // 合并参数

        $.ajax(ajaxConfig);
    }

    // API列表,工程庞大臃肿后可以将API拆分到单独的模块中
    var API = {
        Login: function(req,successCallback,errorCallback){ // 登录
            doPost($tool.getContext() + "login",req,successCallback,errorCallback);
        },
        LogOut:function(req,successCallback,errorCallback){ // 登出
            doPost($tool.getContext() + 'logout.do',req,successCallback,errorCallback);
        },
        ChangePwd:function(req,successCallback,errorCallback){ // 更改密码
            doPost($tool.getContext() + 'personCenter/changePwd.do',req,successCallback,errorCallback);
        },
        GetRoleList:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'personCenter/roleList.do',req,successCallback,errorCallback);
        },
        DeleteLog:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'log/delete.do',req,successCallback,errorCallback);
        },
        BatchDeleteLog:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext() + 'log/batchDelete.do',req,config,successCallback,errorCallback);
        },
        GetFirstClassMenus:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'menu/firstClassMenus.do',req,successCallback,errorCallback);
        },
        AddMenu:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'menu/add.do',req,successCallback,errorCallback);
        },
        DeleteMenu:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'menu/delete.do',req,successCallback,errorCallback);
        },
        GetMenu:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'menu/get.do',req,successCallback,errorCallback);
        },
        UpdateMenu:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext()+'menu/update.do',req,config,successCallback,errorCallback);
        },
        GetAllOrg:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'org/getAll.do',req,successCallback,errorCallback);
        },
        GetOrg:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'org/get.do',req,successCallback,errorCallback);
        },
        AddOrg:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'org/add.do',req,successCallback,errorCallback);
        },
        UpdateOrg:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'org/update.do',req,successCallback,errorCallback);
        },
        DeleteOrg:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'org/delete.do',req,successCallback,errorCallback);
        },
        AddRole:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'role/add.do',req,successCallback,errorCallback);
        },
        DeleteRole:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'role/delete.do',req,successCallback,errorCallback);
        },
        GetRole:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'role/get.do',req,successCallback,errorCallback);
        },
        UpdateRole:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'role/update.do',req,successCallback,errorCallback);
        },
        AddUser:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext() + 'sysUser/add.do',req,config,successCallback,errorCallback);
        },
        DeleteUser:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'sysUser/delete.do',req,config,successCallback,errorCallback);
        },
        InitPwd:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'sysUser/initPwd.do',req,successCallback,errorCallback);
        },
        GetUser:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'sysUser/get.do',req,successCallback,errorCallback);
        },
        UpdateUser:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext() + 'sysUser/update.do',req,config,successCallback,errorCallback);
        },
        GetUserInfo:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'personCenter/get.do',req,successCallback,errorCallback);
        },
        UpdateUserInfo:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext() + 'personCenter/update.do',req,config,successCallback,errorCallback);
        },

        //purOrder 采购订单管理
        //初始化GoodsId下拉框
        getListGoods:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'purchase_order/goods.do',req,config,successCallback,errorCallback);
        },
        //删除
        deletePurOrder:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'purchase_order/delete.do',req,config,successCallback,errorCallback);
        },
        //提交
        commitPurOrder:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'purchase_order/commit.do',req,config,successCallback,errorCallback);
        },
        //撤回
        recallPurOrder:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'purchase_order/recall.do',req,config,successCallback,errorCallback);
        },
        //添加
        insertPurOrder:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'purchase_order/insert.do',req,config,successCallback,errorCallback);
        },
        //根据id获取数据
        getPurOrder:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'purchase_order/get.do',req,config,successCallback,errorCallback);
        },
        //查询所有
        listPurOrder:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'purchase_order/list.do',req,config,successCallback,errorCallback);
        },
        //修改
        updatePurOrder:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'purchase_order/update.do',req,config,successCallback,errorCallback);
        },
        //获取查看的id
        lookGetPurOrder:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'purchase_order/lookGet.do',req,config,successCallback,errorCallback);
        },

        //查看
        lookPurOrder:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'purchase_order/look.do',req,config,successCallback,errorCallback);
        },
        //审核
        inspectPurOrder:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'purchase_order/inspect.do',req,config,successCallback,errorCallback);
        },

        GetDepotType:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'depotSet/getDepotType.do',req,successCallback,errorCallback);
        },
        addDepot:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'depotSet/add.do',req,successCallback,errorCallback);
        },
        GetDepot:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'depotSet/get.do',req,successCallback,errorCallback);
        },
        UpdateDepot:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext() + 'depotSet/update.do',req,config,successCallback,errorCallback);
        },
        DeleteDepot:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'depotSet/delete.do',req,successCallback,errorCallback);
        },
        DelsDepot:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'depotSet/dels.do',req,successCallback,errorCallback);

        },//销售订单删除
        DeleteMarkerOrder:function(req,config,successCallback,errorCallback) {
            doPost($tool.getContext() + 'marker/delete', req, config, successCallback, errorCallback);
        },//获取销售订单ID
        GetMarkerOrder:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'marker/get',req,successCallback,errorCallback);
        },//销售订单修改
        updateMarkerOrder:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'marker/update',req,successCallback,errorCallback);
        },//销售订单添加
        AddMarkerOrder:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'marker/add',req,successCallback,errorCallback);
        },//提交
        audiUpdate:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'marker/audi',req,successCallback,errorCallback);
        },//撤回
        backUpdate:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'marker/back',req,successCallback,errorCallback);
        },//订单审核删除
        DeleteOrderAudit:function(req,config,successCallback,errorCallback) {
            doPost($tool.getContext() + 'audit/delete', req, config, successCallback, errorCallback);
        },//获取订单审核ID
        GetOrderAudit:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'audit/get',req,successCallback,errorCallback);
        },//订单审核修改
        updateOrderAudit:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'audit/update',req,successCallback,errorCallback);
        },//驳回
        backUpdate:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'audit/back',req,successCallback,errorCallback);
        },//驳回
        passUpdate:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'audit/pass',req,successCallback,errorCallback);
        },


        <!--财务-->
        //无需删除逻辑
        /*DeletePay:function(req,successCallback,errorCallback){
            doComplexPost($tool.getContext() + 'finance/delete.do',req,successCallback,errorCallback);
        },*/
        /*更新应付单内容*/
        UpdatePay:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext() + 'finance/update.do',req,config,successCallback,errorCallback);
        },
        /*------lennon---------*/
        /*主键查询product的所有信息*/
        ProductInfoById:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'productInfo/get',req,successCallback,errorCallback);
        },

        /*显示和poduct所有信息F*/
        AllProductInfo:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'productInfo/list',req,successCallback,errorCallback);
        },
        AddProduct:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'product/add',req,successCallback,errorCallback);
        },
        delProduct:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'product/del',req,successCallback,errorCallback);
        },
        getAllProduct:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'product/list',req,successCallback,errorCallback);
        },
        getProductById:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'product/get',req,successCallback,errorCallback);
        },
        getProductByState:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'product/gbs',req,successCallback,errorCallback);
        },
        //更改产品状态
        changProductState:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'product/chg',req,successCallback,errorCallback);
        },
        stepBack1:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'product/sb',req,successCallback,errorCallback);
        },
        updateProduct:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'product/upd',req,successCallback,errorCallback);
        },
        delProduceFormula:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'formula/del',req,successCallback,errorCallback);
        },
        udpFormula:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'formula/upd',req,successCallback,errorCallback);
        },
        getFormulaById:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'formula/get',req,successCallback,errorCallback);
        },
        addFormula:function(req,successCallback,errorCallback){
        doPost($tool.getContext() + 'formula/add',req,successCallback,errorCallback);
        },
        //列出配方表所有信息
        getAllFormula:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'formula/list',req,successCallback,errorCallback);
        },
        //生产计划_lennon
        addProducePlan:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'plan/add',req,successCallback,errorCallback);
        },
        changeOther1:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'plan/cho',req,successCallback,errorCallback);
        },
        updProdcePlan:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'plan/upd',req,successCallback,errorCallback);
        },
        delProducePlan:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'plan/del',req,successCallback,errorCallback);
        },
        getProducePlanById:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'plan/get',req,successCallback,errorCallback);
        },
        getProductProducePlan:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'ppp/list',req,successCallback,errorCallback);
        },
        getproductProducePlanById:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'ppp/get',req,successCallback,errorCallback);
        },
        getAllProducePlan:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'plan/list',req,successCallback,errorCallback);
        },
        //连料单api_lennon
        addFormulaReach:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'reach/add',req,successCallback,errorCallback);
        },
        updFormulaReach:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'reach/upd',req,successCallback,errorCallback);
        },

        delFormulaReach:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'reach/del',req,successCallback,errorCallback);
        },
        getFormulaReachById:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'reach/get',req,successCallback,errorCallback);
        },
        getFormulaReach:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'reach/list',req,successCallback,errorCallback);
        },
        goOn:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'reach/goOn',req,successCallback,errorCallback);
        },
        stop:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'reach/stop',req,successCallback,errorCallback);
        },
        completeProduce:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'reach/complete',req,successCallback,errorCallback);
        },

        //验收单api_lennon
        addCheck:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'check/add',req,successCallback,errorCallback);
        },
        updCheck:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'check/upd',req,successCallback,errorCallback);
        },
        delCheck:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'check/del',req,successCallback,errorCallback);
        },
        getCheckById:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'check/get',req,successCallback,errorCallback);
        },
        getCheck:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'check/list',req,successCallback,errorCallback);
        },
        changeCheckState:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'check/chg',req,successCallback,errorCallback);
        },
        useless:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'check/unUse',req,successCallback,errorCallback);
        },
        //-------------------------
        delCreateUserInfo:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'create/del',req,successCallback,errorCallback);
        },
        addCreateUserInfo:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'create/add',req,successCallback,errorCallback);
        },
        getCreateUserById:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'create/get',req,successCallback,errorCallback);
        },
        editCreateUserInfo:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'create/upd',req,successCallback,errorCallback);
        },
        addProductWaste1:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'productWaste/add',req,successCallback,errorCallback);
        },
        delProductWaste:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'productWaste/del',req,successCallback,errorCallback);
        },
        updProductWaste:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'productWaste/upd',req,successCallback,errorCallback);
        },
        getProductWasteById:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'productWaste/get',req,successCallback,errorCallback);
        },

        getAllProductWaste:function(req,successCallback,errorCallback){
        doPost($tool.getContext() + 'productWaste/list',req,successCallback,errorCallback);
        },
        delProduceFormulaDetail:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'detail/del',req,successCallback,errorCallback);
        },
        getProduceFormulaDetail:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'detail/list',req,successCallback,errorCallback);
        },
        getProduceFormulaDetailById:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'detail/get',req,successCallback,errorCallback);
        }

    };




    //输出扩展模块
    exports('$api', API);
});


