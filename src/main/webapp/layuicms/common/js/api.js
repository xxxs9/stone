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

    function chart(url,req,successCallback,errorCallback) {
        $.ajax({
            url:url,
            data:req,
            method:"post",
            async:false,
            success:function (data) {
                successCallback(data);
            },
            error:function (error) {
                errorCallback(error);
            }
        });
    }

    function treeNode(url,req,successCallback,errorCallback) {
        $.ajax({
            url:url,
            data:req,
            method:"post",
            async:false,
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
       /* //获取roles
        selectRole:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'role/role.do',req,successCallback,errorCallback);
        },*/

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

        addSysNotify:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'sysNotify/add.do',req,successCallback,errorCallback);
        },
        getmessage:function(req,successCallback,errorCallback) {
            doPost($tool.getContext() + 'sysNotify/getmessage.do', req, successCallback, errorCallback);
        },
        getSendMessageInfo:function(req,successCallback,errorCallback) {
            doPost($tool.getContext() + 'sysNotify/getSendMessageInfo.do', req, successCallback, errorCallback);
        },
        getNewMessage:function(req,successCallback,errorCallback) {
            doPost($tool.getContext() + 'sysNotify/getNewMessage.do', req, successCallback, errorCallback);
        },
        updatestate:function(req,successCallback,errorCallback) {
            doPost($tool.getContext() + 'sysNotify/updatestate.do', req, successCallback, errorCallback);
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
        //采购入库之根据goodsId获取price
        selectPriceByGoodsId:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'purchase_order/price.do',req,config,successCallback,errorCallback);
        },
        //采购申请 查看审核通过的订单详情  根据orderNumber订单编号查看
        selectAllByOrderNumber:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'purchase_order/orderNumber.do',req,successCallback,errorCallback);
        },
        //采购申请 查看审核通过的订单详情
        selectAllBySearch:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'purchase_order/search.do',req,config,successCallback,errorCallback);
        },
        //根据orderNumber获取数据
        getOrderNumberPurOrder:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'purchase_order/getOrderNumber.do',req,config,successCallback,errorCallback);
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
        getPurchaseReturn:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'purchase_return/get.do',req,successCallback,errorCallback);
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
        //根据orderNumber下拉框自动获取信息
        selectOtherByOrderNumber:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'purchase_return/byOrderNumber.do',req,config,successCallback,errorCallback);
        },
        //提交commit
        commitPurchaseReturn:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'purchase_return/commit.do',req,config,successCallback,errorCallback);
        },
        //撤回back
        backPurchaseReturn:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'purchase_return/back.do',req,config,successCallback,errorCallback);
        },
        //四月采购图表-柱状图
        selectChartByApril:function(req,successCallback,errorCallback){
            chart($tool.getContext() + 'purchase_order/selectChartByApril.do',req,successCallback,errorCallback);
        },
        //四月采购图表-柱状图 获取所有goodsName
        selectGoodsNameAll:function(req,successCallback,errorCallback){
            chart($tool.getContext() + 'purchase_order/selectGoodsNameAll.do',req,successCallback,errorCallback);
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
        },
        GetDepotOrderInType:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'depotOrder/getDepotOrderInType.do',req,successCallback,errorCallback);
        },GetDepotOrderOutType:function(req,successCallback,errorCallback){
        doPost($tool.getContext()+'depotOrder/getDepotOrderOutType.do',req,successCallback,errorCallback);
         },
        AddDepotOrderIn:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'depotOrder/addIn.do',req,successCallback,errorCallback);
        },
        AddDepotOrderOut:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'depotOrder/addOut.do',req,successCallback,errorCallback);
        },
        AuditPassDepotOrderIn:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'depotOrder/auditPassIn.do',req,successCallback,errorCallback);
        },
        AuditPassDepotOrderOut:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'depotOrder/auditPassOut.do',req,successCallback,errorCallback);
        },
        AuditRejectDepotOrderIn:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'depotOrder/auditRejectIn.do',req,successCallback,errorCallback);
        },
        AuditRejectDepotOrderOut:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'depotOrder/auditRejectOut.do',req,successCallback,errorCallback);
        },
        StorageInDepotOrderIn:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'depotOrder/storageIn.do',req,successCallback,errorCallback);
        },
        StorageInDepotOrderOut:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'depotOrder/storageOut.do',req,successCallback,errorCallback);
        },
        DeleteDepotOrder:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'depotOrder/delete.do',req,successCallback,errorCallback);
        },
        DelsDepotOrder:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'depotOrder/dels.do',req,successCallback,errorCallback);
        },
        GetDepotInventoryByGoodsId:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'depotInventory/getByGoodsId.do',req,successCallback,errorCallback);
        },
        AddDepotInventoryCheck:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'depotInventoryCheck/add.do',req,successCallback,errorCallback);
        },
        EndDepotInventoryCheck:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'depotInventoryCheck/end.do',req,successCallback,errorCallback);
        },
        AuditDepotInventoryCheck:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'depotInventoryCheck/audit.do',req,successCallback,errorCallback);
        },
        AddsDepotInventoryCheckDetail:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'depotInventoryCheckDetail/adds.do',req,successCallback,errorCallback);
        },
        UpdateDepotInventoryCheckDetail:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'depotInventoryCheckDetail/update.do',req,successCallback,errorCallback);
        },
        AuditRejectDepotInventoryCheck:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'depotInventoryCheck/auditReject.do',req,successCallback,errorCallback);
        },
        UpdateDepotInventoryGoodsNumber:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'depotInventory/update.do',req,successCallback,errorCallback);
        },
        //入库单是否入库成功,返回Boolean值
        IsStorageIn:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'depotOrder/isStorageIn.do',req,successCallback,errorCallback);
        },//出库单是否入库成功,返回Boolean值
        IsStorageOut:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'depotOrder/isStorageOut.do',req,successCallback,errorCallback);
        },//入库单是否审核成功,返回Boolean值
        isAuditPassIn:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'depotOrder/isAuditPassIn.do',req,successCallback,errorCallback);
        },//出库单是否审核成功,返回Boolean值
        isAuditPassOut:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'depotOrder/isAuditPassOut.do',req,successCallback,errorCallback);
        },
        DeleteCheck:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'depotInventoryCheck/delete.do',req,successCallback,errorCallback);
        },



        //销售订单删除
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
        AllProductInfo:function(req,successCallback,errorCallback) {
            doPost($tool.getContext() + 'productInfo/list', req, successCallback, errorCallback);
        },
        /*生成采购应付单*/
        generatePurchasePay:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'finance/generatePurchasePay.do',req,successCallback,errorCallback);
        },
        /*生成采购应收单*/
        generatePurchaseReceive:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'finance/generatePurchaseReceive.do',req,successCallback,errorCallback);
        },
        /*啊发包-锦祥*/
        generateSaleReceive:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'finance/generateSaleReceive',req,successCallback,errorCallback);
        },
        /*啊发包-锦祥*/
        generateSalePay:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'finance/generateSalePay',req,successCallback,errorCallback);
        },
        /*销售采购应收*/
        saleOrderReceivePass:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'finance/saleOrderReceivePass',req,successCallback,errorCallback);
        },
        /*销售采购应付*/
        saleOrderPayPass:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'finance/saleOrderPayPass',req,successCallback,errorCallback);
        },
        /*采购支出图表数据*/
        getPurchasePayChart:function(req,successCallback,errorCallback){
            chart($tool.getContext() + 'finance/getPurchasePayChart',req,successCallback,errorCallback);
        },
        /*采购收入图表数据*/
        getPurchaseReceiveChart:function(req,successCallback,errorCallback){
            chart($tool.getContext() + 'finance/getPurchaseReceiveChart',req,successCallback,errorCallback);
        },
        /*销售收入图表数据*/
        getSaleReceiveChart:function(req,successCallback,errorCallback){
            chart($tool.getContext() + 'finance/getSaleReceiveChart',req,successCallback,errorCallback);
        },
        /*销售支出图表数据*/
        getSalePayChart:function(req,successCallback,errorCallback){
            chart($tool.getContext() + 'finance/getSalePayChart',req,successCallback,errorCallback);
        },
        /*采购、销售总收入*/
        getTotalReceive:function(req,successCallback,errorCallback){
            chart($tool.getContext() + 'bill/getTotalReceive',req,successCallback,errorCallback);
        },
        /*采购、销售总支出*/
        getTotalPay:function(req,successCallback,errorCallback){
            chart($tool.getContext() + 'bill/getTotalPay',req,successCallback,errorCallback);
        },


        /*啊发包-锦祥*/
        generateSaleReceive:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'finance/generateSaleReceive',req,successCallback,errorCallback);
        },
        /*啊发包-锦祥*/
        generateSalePay:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'finance/generateSalePay',req,successCallback,errorCallback);
        },
        /*销售采购应收*/
        saleOrderReceivePass:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'finance/saleOrderReceivePass',req,successCallback,errorCallback);
        },
        /*销售采购应付*/
        saleOrderPayPass:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'finance/saleOrderPayPass',req,successCallback,errorCallback);
        },
        AddProduct:function(req,successCallback,errorCallback) {
            doPost($tool.getContext() + 'product/add', req, successCallback, errorCallback);
        },


        delProduct:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'product/del',req,successCallback,errorCallback);
        },
        getAllProduct:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'product/list',req,successCallback,errorCallback);
        },
        getProductById:function(req,successCallback,errorCallback){
            chart($tool.getContext() + 'product/get',req,successCallback,errorCallback);
        },
        getProductByState:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'product/gbs',req,successCallback,errorCallback);
        },
        //添加产品api
        addGoodsProduct:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'goodsProduct/add',req,successCallback,errorCallback);
        },
        getGoodsProduct:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'goodsProduct/get',req,successCallback,errorCallback);
        },
        updGoodsProduct:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'goodsProduct/upd',req,successCallback,errorCallback);
        },
        delGoodsProduct:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'goodsProduct/del',req,successCallback,errorCallback);
        },
        selectGoodsProduct:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'goodsProduct/list',req,successCallback,errorCallback);
        },
        getUnProduce:function(req,successCallback,errorCallback){
        doPost($tool.getContext() + 'goodsProduct/getUnProduce',req,successCallback,errorCallback);
        },




        //领料reach.do audiNot
        audiNot:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'product/audiNot',req,successCallback,errorCallback);
        },
        productReachDo:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'product/reach.do',req,successCallback,errorCallback);
        },
        startProduce:function(req,successCallback,errorCallback){
        doPost($tool.getContext() + 'product/startProduce',req,successCallback,errorCallback);
             },
        stopProduce:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'product/stopProduce',req,successCallback,errorCallback);
        },
        continueProduce1:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'product/continueProduce',req,successCallback,errorCallback);
        },
        //检验时增加价格
        insertSupportPrice:function(req,successCallback,errorCallback){
        doPost($tool.getContext() + 'product/insertSupportPrice',req,successCallback,errorCallback);
    },
        //更改产品状态
        changProductState:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'product/chg',req,successCallback,errorCallback);
        },
        managerAudi:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'product/managerAudi',req,successCallback,errorCallback);
        },
        jumpAudi:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'product/jumpAudi',req,successCallback,errorCallback);
        },
        stepBack1:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'product/sb',req,successCallback,errorCallback);
        },
        completeProduce1:function(req,successCallback,errorCallback){
        doPost($tool.getContext() + 'product/completeProduce',req,successCallback,errorCallback);
        },
        produceMaterial:function(req,successCallback,errorCallback){
        doPost($tool.getContext() + 'product/produceMaterial',req,successCallback,errorCallback);
        },
        getProduceMaterialById:function(req,successCallback,errorCallback){
        chart($tool.getContext() + 'product/getProduceMaterialById',req,successCallback,errorCallback);
        },
        getMaterialGoodsById:function(req,successCallback,errorCallback){
        doPost($tool.getContext() + 'product/getMaterialGoodsById',req,successCallback,errorCallback);
    },
        unIntodepot:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'product/unIntoDepot',req,successCallback,errorCallback);
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
        getByProductId:function(req,successCallback,errorCallback){
        doPost($tool.getContext() + 'formula/getByProductId',req,successCallback,errorCallback);
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
        getAllProducePlanId:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'plan/findId',req,successCallback,errorCallback);
        },
        //billCheck
        addBillCheckNew:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'billCheck/add',req,successCallback,errorCallback);
        },
        producingAdd:function(req,successCallback,errorCallback){
        doPost($tool.getContext() + 'billCheck/producingAdd',req,successCallback,errorCallback);
        },
        getBillCheckById:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'billCheck/get',req,successCallback,errorCallback);
        },
        ok1:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'billCheck/ok',req,successCallback,errorCallback);
        },
        notOk:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'billCheck/notOk',req,successCallback,errorCallback);
        },
        billCheckstepBack:function(req,successCallback,errorCallback){
        doPost($tool.getContext() + 'billCheck/stepBack',req,successCallback,errorCallback);
        },
        selectByProductId:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'billCheck/selectByProductId',req,successCallback,errorCallback);
        },
        billCheckIntoDepot:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'billCheck/intoDepot',req,successCallback,errorCallback);
        },
        addFormulaReach:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + '/reach/add',req,successCallback,errorCallback);
        },
        updFormulaReach:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'reach/upd',req,successCallback,errorCallback);
        },
        getReachByProductId:function(req,successCallback,errorCallback){
        doPost($tool.getContext() + 'reach/getByProductId',req,successCallback,errorCallback);
         },
        getReachByProductId1:function(req,successCallback,errorCallback){
            chart($tool.getContext() + 'reach/getByProductId',req,successCallback,errorCallback);
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
        getPCR:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'pcr/list',req,successCallback,errorCallback);
        },
        getreachList:function(req,successCallback,errorCallback){
        doPost($tool.getContext() + 'pcr/reachList',req,successCallback,errorCallback);
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
        },
        addProduceFormulaDetail:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'detail/add',req,successCallback,errorCallback);
        },
        updProduceFormulaDetail:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'detail/upd',req,successCallback,errorCallback);
        },
        getProduceFormulaDetailByFormulaId:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'detail/getDetailByFormulaId',req,successCallback,errorCallback);
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
        addSysNotify:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'sysNotify/add.do',req,successCallback,errorCallback);
        },
        getmessage:function(req,successCallback,errorCallback) {
            doPost($tool.getContext() + 'sysNotify/getmessage.do', req, successCallback, errorCallback);
        },
        getSendMessageInfo:function(req,successCallback,errorCallback) {
            doPost($tool.getContext() + 'sysNotify/getSendMessageInfo.do', req, successCallback, errorCallback);
        },
        getNewMessage:function(req,successCallback,errorCallback) {
            doPost($tool.getContext() + 'sysNotify/getNewMessage.do', req, successCallback, errorCallback);
        },
        updatestate:function(req,successCallback,errorCallback) {
            doPost($tool.getContext() + 'sysNotify/updatestate.do', req, successCallback, errorCallback);
        },
        findAll:function(req,successCallback,errorCallback) {
            doPost($tool.getContext() + 'sysUser/findAll.do', req, successCallback, errorCallback);
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
        GetBySupplierName:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'supplier/getBySupplierName.do',req,successCallback,errorCallback);
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
        GetMaterialGoodsId:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'materialGoods/getId.do',req,successCallback,errorCallback);
        },
        GetMaterialGoodsIdByName:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'materialGoods/getByName.do',req,successCallback,errorCallback);
        },
        UpdateMaterialGoods:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'materialGoods/update.do',req,successCallback,errorCallback);
        },
        GetMaterialByGoodsName:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'material/getByGoodsName.do',req,successCallback,errorCallback);
        },
        //销售订单删除
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
        /*根据订单id查找应付单*/
        getPurchasePay:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'finance/getPurchasePay.do',req,successCallback,errorCallback);
        },
        /*根据申请单id查找应付单*/
        getPurchasePayById:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'finance/getPurchasePayById.do',req,successCallback,errorCallback);
        },
        /*根据申请单id查找应收单*/
        getPurchaseReceiveById:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'finance/getPurchaseReceiveById.do',req,successCallback,errorCallback);
        },
        /*审核采购应付单*/
        purchaseOrderPayPass:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'finance/purchaseOrderPayPass.do',req,successCallback,errorCallback);
        },
        /*审核采购应收单*/
        purchaseOrderReceivePass:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'finance/purchaseOrderReceivePass.do',req,successCallback,errorCallback);
        },
        /*根据id获取销售发货未审核单*/
        getSaleReceiveById:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'finance/getSaleReceiveById.do',req,successCallback,errorCallback);
        },
        /*根据id获取销售发货未审核单*/
        getSalePayById:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'finance/getSalePayById.do',req,successCallback,errorCallback);
        },
        /*导出时间报表*/
        timeReport:function(req,successCallback,errorCallback){
            doGet($tool.getContext() + 'bill/exportTimeReport.do',req,successCallback,errorCallback);
        },

        findAll:function(req,successCallback,errorCallback) {
            doPost($tool.getContext() + 'sysUser/findAll.do', req, successCallback, errorCallback);
        },
        getAllProduct:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'product/list',req,successCallback,errorCallback);
        },
        getProductId:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'product/getId',req,successCallback,errorCallback);
        },
        GetProductId:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'product/getId.do',req,successCallback,errorCallback);
        },

        updateProduct:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'product/upd',req,successCallback,errorCallback);
        },
        changeProState:function(req,config,successCallback,errorCallback){
        doPost($tool.getContext() + 'product/intoDepot',req,successCallback,errorCallback);
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
        },//发货单删除
        DeleteShipmentOrder:function(req,config,successCallback,errorCallback) {
        doPost($tool.getContext() + 'shipment/delete', req, config, successCallback, errorCallback);
    },//发货单添加
        AddShipmentOrder:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'shipment /add',req,successCallback,errorCallback);
        },//发货单ID
        GetShipmentOrder:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'shipment/get',req,successCallback,errorCallback);
        },//销售订单修改
        updateShipmentOrder:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'shipment/update',req,successCallback,errorCallback);
        },//获取订单审核ID
        GetOrderAudit:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'audit/get',req,successCallback,errorCallback);
        },//订单审核修改
        updateOrderAudit:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'audit/update',req,successCallback,errorCallback);
        },//收货成功
        confirmUpdate:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'shipment/confirm',req,successCallback,errorCallback);
        }, //初始化productId下拉框
        getProductId:function(req,config,successCallback,errorCallback){
            doPost($tool.getContext() + 'marker/getprocutid',req,config,successCallback,errorCallback);
        },//发货单删除
        DeleteReturnOrder:function(req,config,successCallback,errorCallback) {
            doPost($tool.getContext() + 'goods/delete', req, config, successCallback, errorCallback);
        },//获取发货单ID
        GetReturnGoodsOrder:function(req,successCallback,errorCallback){
            doPost($tool.getContext()+'shipment/get',req,successCallback,errorCallback);
        },//发货单添加
        AddReturnGoodsOrder:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'goods/add',req,successCallback,errorCallback);
        },//发货单修改
        updateReturnGoodsOrder:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'goods/update',req,successCallback,errorCallback);
        },//确认收货
        updateConfirm:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'shipment/confirm',req,successCallback,errorCallback);
        },//退货
        updateBack:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'shipment/back',req,successCallback,errorCallback);
        },//提交
        updateAudit:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'goods/audit',req,successCallback,errorCallback);
        },//提交仓库
        updateDepot:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'goods/depot',req,successCallback,errorCallback);
        },//提交财务
        updateFinance:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'goods/finance',req,successCallback,errorCallback);
        },//提交仓库发货
        updateGoods:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'shipment/goods',req,successCallback,errorCallback);
        },//财务查收
        updateAccept:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'returnGoods/accept',req,successCallback,errorCallback);
        },//提交仓库审核
        updateSubmit:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'marker/submit',req,successCallback,errorCallback);
        },//提交仓库审核
        updateWare:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'audit/ware',req,successCallback,errorCallback);
        },//销售订单提交财务
        updateFina:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'marker/fina',req,successCallback,errorCallback);
        },//提交财务
        updateSub:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'shipment/sub',req,successCallback,errorCallback);
        },//提交仓库审核
        updateSubmit:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'marker/submit',req,successCallback,errorCallback);
        },//提交仓库审核
        updateWare:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'audit/ware',req,successCallback,errorCallback);
        },//销售订单提交财务
        updateFina:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'marker/fina',req,successCallback,errorCallback);
        },

        //======权限=====//
        //初始化所属权限
        initBelongTo:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'node/initBelongTo',req,successCallback,errorCallback);
        },
        //添加权限
        addPerms:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext() + 'node/addPerms.do',req,config,successCallback,errorCallback);
        },
        //根据id获取treeNode
        getNode:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'node/get',req,successCallback,errorCallback);
        },
        //更改权限
        UpdatePerms:function(req,config,successCallback,errorCallback){
            doComplexPost($tool.getContext() + 'node/UpdatePerms.do',req,config,successCallback,errorCallback);
        },
        //删除权限
        deleteNode:function(req,successCallback,errorCallback){
            doPost($tool.getContext() + 'node/delete',req,successCallback,errorCallback);
        },

    };




    //输出扩展模块
    exports('$api', API);
});


