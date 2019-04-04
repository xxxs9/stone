layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention:'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool:'tool',
    $api:'api'
}).use(['form', 'layer', 'jquery','ajaxExtention','$tool','$api'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery,
        $tool = layui.$tool,
        $api = layui.$api;


    /**
     * 初始化页面
     * */
    function init() {
        //初始化信息
        initDepotOrder();
    }
    init();

    /**
     * 初始化菜单信息
     * */
    function initDepotOrder() {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var id = queryArgs['id'];
        var goodsId = queryArgs['goodsId'];
        var goodsNumber = queryArgs['goodsNumber'];

        $("#goodsNumber").html(goodsNumber);

        var req1 = {
            id:goodsId
        };

        var req2 = {
            goodsId:goodsId
        };

        $api.GetMaterialGoods(req1,function (res) {
            var data = res.data;
            if(data != null){
                $("#goodsName").html(data.goodsName);
            }
        });

        $api.getProductById(req1,function (res) {
            var data = res.data;
            if(data != null){
                $("#goodsName").html(data.productName);
            }
        });

        $api.GetDepotInventoryByGoodsId(req2,function (res) {
            var data = res.data;
            if(data == null){
                $("#saleableNumber").html("0");
                $("#shipmentsNumber").html("0");
            }else{
                $("#saleableNumber").html(data.saleableNumber);
                $("#shipmentsNumber").html(data.shipmentsNumber);
            }
        });


    }


    /**
     * 表单提交
     * */
    form.on("submit(auditPass)", function (data) {

        var saleableNumber = parseInt($("#saleableNumber").html());
        var goodsNumber = parseInt($("#goodsNumber").html());
        //出库数量小于可销售数量
        if(goodsNumber>=saleableNumber){
            layer.open({
                title: '提示'
                ,content: '库存不足'
            });
            return false;
        }
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var auditDescribe = data.field.auditDescribe;
        var state = "审核通过";

        //请求
        var req = {
            id:queryArgs['id'],
            auditDescribe:auditDescribe,
            state: state
        };

        $api.AuditPassDepotOrderOut(req,function (data) {
            layer.msg("审核成功！",{time:1000},function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });
        return false;
    })

    /**
     * 表单提交
     * */
    form.on("submit(refuse)", function (data) {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var auditDescribe = data.field.auditDescribe;
        var state = "审核未通过";

        //请求
        var req = {
            id:queryArgs['id'],
            auditDescribe:auditDescribe,
            state: state
        };

        $api.AuditRejectDepotOrderOut(req,function (data) {
            layer.msg("驳回成功！",{time:1000},function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });
        return false;
    })

});


