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
        }
    };




    //输出扩展模块
    exports('$api', API);
});


