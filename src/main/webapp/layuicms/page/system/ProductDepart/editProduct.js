layui.config({
    base: $config.resUrl + 'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api: 'api'
}).use(['form', 'layer', 'jquery', 'ajaxExtention', '$tool', '$api'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery,
        $tool = layui.$tool,
        $api = layui.$api;

    /**
     * 页面初始化
     * */
    function init() {
        //初始化下拉框
        initMenuInfo()
    }

    init();


    /**
     * 表单提交
     * */

    function select(hh) {
        $api.getAllProduct(null, function (res) {
            var data = res.data;
            if (data.length > 0) {
                var html = '';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].state + '"></option>';
                }
                $('#state').append($(html));
                $('#state').val(hh);
                form.render();
            }

        });

    }


    function select1(hh) {
        $api.getAllProduct(null, function (res) {
            var data2 = res.data;
            if (data2.length > 0) {
                var html = '';
                for (var i = 0; i < data2.length; i++) {
                    html += '<option value="' + data2[i].canSold + '"></option>';
                }
                $('#canSold').append($(html));
                $('#canSold').val(hh);
                form.render();
            }

        });

    }

    function select2(hh) {
        $api.getAllProduct(null, function (res) {
            var data3 = res.data;
            if (data3.length > 0) {
                var html = '';
                for (var i = 0; i < data3.length; i++) {
                    html += '<option value="' + data3[i].productType + '"></option>';
                }

                $('#productType').append($(html));
                $('#productType').val(hh);
                form.render();
            }

        });

    }

    function initMenuInfo() {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var wid = queryArgs['id'];

        var url = $tool.getContext() + '/product/get';
        var req = {
            id: wid
        };

        $api.getProductById(req, function (res) {
            var data = res.data;


            $("[name='productName']").val(data.productName);
            $("[name='productNumber']").val(data.productNumber);
            $("[name='supportPrice']").val(data.supportPrice);

            select2(data.productType);
            select(data.state);
            select1(data.canSold);


            $("[name='productDescribe']").val(data.productDescribe);




            form.render();//重新绘制表单，让修改生效
        });


    }


    /**
     * 表单提交
     * */
    form.on("submit(editMenu)", function (data) {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        console.log(queryArgs)
        var productType = data.field.productType;
        var productName = data.field.productName;
        var state = data.field.state;
        var productNumber = data.field.productNumber;
        var supportPrice = data.field.supportPrice;
        var canSold = data.field.canSold;

        var productDescribe = data.field.productDescribe;


        //请求
        var url = $tool.getContext() + '/product/upd';
        var req = {
            id: queryArgs['id'],
            productType: productType,
            productName: productName,
            state: state,
            canSold:canSold,
            supportPrice:supportPrice,
            productNumber:productNumber,
            productDescribe: productDescribe
        };

        $api.updateProduct(req, function (data) {
            layer.msg("修改成功！", {time: 1000}, function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });

        });
        return false;

    })


});


