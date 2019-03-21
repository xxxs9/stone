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
    //todo(产品类型)
    function select(hh) {
        $api.getAllProduct(null, function (res) {
            var data = res.data;
            if (data.length > 0) {
                var html = '<option value="">--请选择--</option>';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].productType + '">' + data[i].productType + '</option>>';
                }
                $('#parentMenu').append($(html));
                $('#parentMenu').val(hh);
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

            select(data.productType);
            // $("[name='goodsId']").val(data.goodsId);

            $("[name='productDescribe']").val(data.productDescribe);
            $("[name='wasteId']").val(data.wasteId);

            $("[name='state']").val(data.state);

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
        var wasteId = data.field.wasteId;
        var productDescribe = data.field.productDescribe;


        //请求
        var url = $tool.getContext() + '/product/upd';
        var req = {
            id: queryArgs['id'],
            productType: productType,
            productName: productName,
            state: state,
            wasteId: wasteId,
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


