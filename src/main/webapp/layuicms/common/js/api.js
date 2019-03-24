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

        //purOrder 采购订单管理purchase_order
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
        //采购入库之收货 bring
        bringInPurOrder:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'purchase_order/bringIn.do',req,config,successCallback,errorCallback);
        },
        //采购入库之确认 sure
        sureInPurOrder:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'purchase_order/sureIn.do',req,config,successCallback,errorCallback);
        },
        //采购入库之撤回 back
        backInPurOrder:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'purchase_order/backIn.do',req,config,successCallback,errorCallback);
        },
        //采购入库之提交 commit
        commitInPurOrder:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'purchase_order/commitIn.do',req,config,successCallback,errorCallback);
        },
        //采购入库之编辑 edit
        editInPurOrder:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'purchase_order/editIn.do',req,config,successCallback,errorCallback);
        },
        //采购入库之获取所有信息 listIn
        listInPurOrder:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'purchase_order/listIn.do',req,config,successCallback,errorCallback);
        },
        //采购入库之查看未审核通过原因 look
        lookInPurOrder:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'purchase_order/lookIn.do',req,config,successCallback,errorCallback);
        },
        //采购退货管理purchase_return
        //获取所有列表
        listPurchaseReturn:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'purchase_return/list.do',req,config,successCallback,errorCallback);
        },
        //增加
        insertPurchaseReturn:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'purchase_return/insert.do',req,config,successCallback,errorCallback);
        },
        //删除
        deletePurchaseReturn:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'purchase_return/delete.do',req,config,successCallback,errorCallback);
        },
        //根据id获取
        getPurchaseReturn:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'purchase_return/get.do',req,config,successCallback,errorCallback);
        },
        //修改
        updatePurchaseReturn:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'purchase_return/update.do',req,config,successCallback,errorCallback);
        },
        //获取goodsId下拉框
        selectGoodsIdPurchaseReturn:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'purchase_return/goodsId.do',req,config,successCallback,errorCallback);
        },
        //获取orderNumber下拉框
        selectOrderNumberPurchaseReturn:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'purchase_return/orderNumber.do',req,config,successCallback,errorCallback);
        },
        //提交commit
        commitPurchaseReturn:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'purchase_return/commit.do',req,config,successCallback,errorCallback);
        },
        //撤回bacl
        backPurchaseReturn:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'purchase_return/back.do',req,config,successCallback,errorCallback);
        },


        //采购订单管理  接口
        //阿发包 财务审核人员获取财务审核状态为待审核的信息
        financePurOrder:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'purchase_order/finance.do',req,config,successCallback,errorCallback);
        },
        //阿发包 财务审核人员获取财务审核状态为付款申请的信息
        financeApplyPurOrder:function(req,config,successCallback,errorCallback){
            doPost($tool.getContent() + 'purchase_order/financeApply.do',req,config,successCallback,errorCallback);
        },
        //华锋 仓库审核人员获取仓库审核状态为入库单审核中的信息
        depotPurOrder:function(req,config,successCallback,errorCallback){
            doPost($tool.getContent() + 'purchase_order/depot.do',req,config,successCallback,errorCallback);
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

        /*生成采购应付单*/
        generatePurchasePay:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'finance/generatePurchasePay.do',req,successCallback,errorCallback);
        },
        /*生成采购应收单*/
        generatePurchaseReceive:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'finance/generatePurchaseReceive.do',req,successCallback,errorCallback);
        },


        AddProduct:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext() + 'product/add',req,config,successCallback,errorCallback);
        },
        delProduct:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext() + 'product/del',req,config,successCallback,errorCallback);
        },
        getAllProduct:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'product/list',req,successCallback,errorCallback);
        },
        getProductById:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'product/get',req,successCallback,errorCallback);
        },
        updateProduct:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'product/upd',req,successCallback,errorCallback);
        },
        delProduceFormula:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'formula/del',req,config,successCallback,errorCallback);
        },
        udpFormula:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'formula/upd',req,config,successCallback,errorCallback);
        },
        getFormulaById:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'formula/get',req,successCallback,errorCallback);
        },
        //列出配方表所有信息
        getAllFormula:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'formula/list',req,successCallback,errorCallback);
        },
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
        GetDepotNumber:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'depotSet/getDepotNumber.do',req,successCallback,errorCallback);
        },
        GetDepotUserLoginName:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'depotPersonnel/getDepotUserLoginName.do',req,successCallback,errorCallback);
        },
        GetUserByLoginName:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'sysUser/getByLoginName.do',req,successCallback,errorCallback);
        },
        addDepotPersonnel:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'depotPersonnel/add.do',req,successCallback,errorCallback);
        },
        GetDepotPersonnel:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'depotPersonnel/get.do',req,successCallback,errorCallback);
        },
        UpdateDepotPersonnel:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'depotPersonnel/update.do',req,successCallback,errorCallback);
        },
        DeleteDepotPersonnel:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'depotPersonnel/delete.do',req,successCallback,errorCallback);
        },
        DelsDepotPersonnel:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'depotPersonnel/dels.do',req,successCallback,errorCallback);
        },
        GetGoodsType:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'material/getGoodsType.do',req,successCallback,errorCallback);
        },
        GetSupplier:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'supplier/get.do',req,successCallback,errorCallback);
        },
        UpdateSupplier:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext()+'supplier/update.do',req,config,successCallback,errorCallback);
        },
        DeleteSupplier:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'supplier/delete.do',req,successCallback,errorCallback);
        },
        AddSupplier:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'supplier/add.do',req,successCallback,errorCallback);
        },
        GetSupplierName:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'supplier/getSupplierName.do',req,successCallback,errorCallback);
        },
        GetGoodsSpecification:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'material/getGoodsSpecification.do',req,successCallback,errorCallback);
        },
        GetGoodsName:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'material/getGoodsName.do',req,successCallback,errorCallback);
        },
        GetMaterial:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'material/get.do',req,successCallback,errorCallback);
        },
        UpdateMaterial:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext()+'material/update.do',req,config,successCallback,errorCallback);
        },
        DeleteMaterial:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'material/delete.do',req,successCallback,errorCallback);
        },
        AddMaterial:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'material/add.do',req,successCallback,errorCallback);
        },
        DeleteMaterialGoods:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'materialGoods/delete.do',req,successCallback,errorCallback);
        },
        AddMaterialGoods:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'materialGoods/add.do',req,successCallback,errorCallback);
        },
        GetMaterialGoods:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'materialGoods/get.do',req,successCallback,errorCallback);
        },
        UpdateMaterialGoods:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'materialGoods/update.do',req,successCallback,errorCallback);
        },
        GetMaterialByGoodsName:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'material/getByGoodsName.do',req,successCallback,errorCallback);
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

        /*添加采购应付单*/
        insertPurchasePay:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'finance/addPurchasePay.do',req,successCallback,errorCallback);
        },
        /*根据申请单id查找应付单*/
        getPurchasePay:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'finance/getPurchasePay.do',req,successCallback,errorCallback);
        },
        /*根据申请单id查找应付单*/
        getPurchasePay:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'finance/getPurchasePay.do',req,successCallback,errorCallback);
        },



        AddProduct:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext() + 'product/add',req,config,successCallback,errorCallback);
        },
        delProduct:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext() + 'product/del',req,config,successCallback,errorCallback);
        },
        getAllProduct:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'product/list',req,successCallback,errorCallback);
        },
        getProductById:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'product/get',req,successCallback,errorCallback);
        },
        updateProduct:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'product/upd',req,successCallback,errorCallback);
        },
        delProduceFormula:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'formula/del',req,config,successCallback,errorCallback);
        },
        udpFormula:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'formula/upd',req,config,successCallback,errorCallback);
        },
        getFormulaById:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'formula/get',req,successCallback,errorCallback);
        },
        //列出配方表所有信息
        getAllFormula:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'formula/list',req,successCallback,errorCallback);
        },
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
    };




    //输出扩展模块
    exports('$api', API);
});


