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
        //初始化下拉框
        initGoodsId();
    }

    init();


    function initGoodsId() {
        $api.GetMaterialGoodsId(null,function (res) {
            var data = res.data;
            if (data.length > 0) {
                var html = '<option value="">--请选择--</option>';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i] + '">' + data[i] + '</option>>';
                }
                $('#materialId').append($(html));
                form.render();
            }
        });
        $api.GetProductId(null,function (res) {
            var data = res.data;
            if (data.length > 0) {
                var html = '<option value="">--请选择--</option>';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i] + '">' + data[i] + '</option>>';
                }
                $('#productId').append($(html));
                form.render();
            }
        });
    }

    /**
     * 监听radio选择
     * */
    form.on('radio(goodsTypeFilter)', function (data) {
        //console.log(data.elem); //得到radio原始DOM对象
        var value = data.value;
        if ('原料' === value) {
            $('.product-type').addClass('layui-hide');
            $('.product-type').removeClass('layui-anim-up');
            $('.material-type').removeClass('layui-hide');
            $('.material-type').addClass('layui-anim-up');

        }
        if ('产品' === value) {
            $('.material-type').addClass('layui-hide');
            $('.material-type').removeClass('layui-anim-up');
            $('.product-type').removeClass('layui-hide');
            $('.product-type').addClass('layui-anim-up');
        }
    });


    /**
     * 监听select选择
     * */
    form.on('select(materialIdFilter)', function (data) {
        //console.log(data.elem); //得到select原始DOM对象
        var goodsId = data.value;
        //请求
        var req = {
            goodsId:goodsId
        };

        $api.GetDepotInventoryByGoodsId(req,function (res) {
            var data = res.data;
            if(data == null){
                $("#saleableNumber").html("无库存");
                $("#shipmentsNumber").html("无库存");
            }else{
                $("#saleableNumber").html(data.saleableNumber);
                $("#shipmentsNumber").html(data.shipmentsNumber);
            }

        });
    });

    /**
     * 监听select选择
     * */
    form.on('select(productIdFilter)', function (data) {
        //console.log(data.elem); //得到select原始DOM对象
        var goodsId = data.value;
        //请求
        var req = {
            goodsId:goodsId
        };

        $api.GetDepotInventoryByGoodsId(req,function (res) {
            var data = res.data;
            if(data == null){
                $("#saleableNumber").html("无库存");
                $("#shipmentsNumber").html("无库存");
            }else{
                $("#saleableNumber").html(data.saleableNumber);
                $("#shipmentsNumber").html(data.shipmentsNumber);
            }

        });
    });


    /**
     * 表单提交
     * */
    form.on("submit(addDepotOrderOut)", function (data) {
        var goodsId;
        if(data.field.goodsType === '原料'){
            goodsId = data.field.materialId;
        }
        if(data.field.goodsType === '产品'){
            goodsId = data.field.productId;
        }
        var type = data.field.type;
        var goodsNumber = data.field.goodsNumber;

        //请求
        var req = {
            goodsId:goodsId,
            type: type,
            goodsNumber: goodsNumber
        };

        $api.AddDepotOrderOut(req,function (data) {
            //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
            layer.msg("出库单添加成功！", {time: 1000}, function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });

        return false;
    })

});


