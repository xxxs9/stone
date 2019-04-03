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
            id:goodsId
        };

        $api.GetMaterialGoods(req,function (res) {
            var data = res.data;
            $("#imageUrl").attr('src', data.imageUrl);
            $("#supplierName").html(data.supplierName);
            $("#goodId").html('编号：'+data.id);
            $('#goodsName').html(data.goodsName);
            $('#goodsType').html(data.goodsType);
            $('#goodsDescribe').html(data.goodsDescribe);
            $('#goodsSpecification').html(data.goodsSpecification);
            $('#goodsPrice').html('￥：'+(data.goodsPrice)+'元');
            $('#goodsOriginPlace').html(data.goodsOriginPlace);

            var req1 = {
                supplierName:data.supplierName
            };

            $api.GetBySupplierName(req1,function (res) {
                var data = res.data;
                $("#supplierDescribe").html(data.supplierDescribe);
                $("#phone").html(data.phone);
            });


            form.render();//重新绘制表单，让修改生效
        });
    }

});


