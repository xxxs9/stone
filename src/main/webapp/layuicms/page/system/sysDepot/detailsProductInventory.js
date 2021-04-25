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
        initDepotInventory();
    }
    init();

    /**
     * 初始化菜单信息
     * */
    function initDepotInventory() {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var goodsId = queryArgs['goodsId'];
        var req = {
            bianhao:goodsId
        };

        $api.getGoodsProductByBH(req,function (res) {
            var data = res.data;
            $("[name='productName']").html(data.pname);
            $("[name='productType']").html(data.leixing);
            $("[name='productPrice']").html(data.pprice);
            form.render();//重新绘制表单，让修改生效
        });
    }

});


