layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api:'api'
}).use(['form', 'layer', 'jquery', 'ajaxExtention', '$tool','$api'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery,
        $tool = layui.$tool ,
        $api = layui.$api;
    /**
     * 页面初始化
     * */
    function init() {
        //初始化供应商名称下拉框
        initGoodsId();
        initOrderNumber();
    }
    init();

    /**
     * 初始化供应商名称下拉框
     * */
    function initGoodsId() {
        $api.selectGoodsIdPurchaseReturn(null,function (res) {
            var data = res.data;
            if (data.length > 0) {
                var html = '<option value="">--请选择--</option>';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i] + '">' + data[i] + '</option>>';
                }
                $('#goodsId').append($(html));
                form.render();
            }
        });
    }

    /**
     * 初始化订单编号下拉框
     * */
    function initOrderNumber() {
        $api.selectOrderNumberPurchaseReturn(null,function (res) {
            var data = res.data;
            if (data.length > 0) {
                var html = '<option value="">--请选择--</option>';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i] + '">' + data[i] + '</option>>';
                }
                $('#orderNumber').append($(html));
                form.render();
            }
        });
    }

    /**
     * 表单提交
     * */
    form.on("submit(purAdd)", function (data) {
        var orderNumber = data.field.orderNumber;
        var goodsId = data.field.goodsId;
        var goodsNumber = data.field.goodsNumber;
        var price = data.field.price;
        var applyUser = data.field.applyUser;
        var depotState = data.field.depotState;


        //请求
        var req = {
            orderNumber:orderNumber,
            goodsId:goodsId,
            goodsNumber: goodsNumber,
            price: price,
            applyUser: applyUser,
            depotState:depotState
        };

        $api.insertPurchaseReturn(req,function (data) {
            //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
            layer.msg("采购单添加成功！", {time: 1000}, function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });
        return false;
    })

});