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
                    html += '<option value="' + data[i].productState + '"></option>';
                }
                $('#productState').append($(html));
                $('#productState').val(hh);
                form.render();
            }

        });

    }




    form.verify({
        pi: [
            /^[0-9]*[1-9][0-9]*$/  //正则表达式
            ,'数量必须为大于0的正整数'  //提示信息
        ]
    });

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
    var other44;
    var other33;

    function initMenuInfo() {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var wid = queryArgs['id'];

        var url = $tool.getContext() + '/product/get';
        var req = {
            id: wid
        };

        $api.getProductById(req, function (res) {
            var data = res.data;
            console.log(data);
            $("[name='productName']").val(data.productName);
            $("[name='productNumber']").val(data.productNumber);

            $("[name='other1']").val(data.other1);
            $("[name='productState']").val(data.productState);
            $("[name='supportPrice']").val(data.supportPrice);
            $('#canSold').val(data.canSold);
            select2(data.productType);
            select(data.productState);
            $("[name='productDescribe']").val(data.productDescribe);
            other44 = data.other4;
            other33 = data.other3;

            form.render();//重新绘制表单，让修改生效
        });


    }


    /**
     * 表单提交
     * */
    var cs;
    var oth1;
    function editOpera(){
        var req={
            operatorUser:cs,
            operatorRemark:oth1
        }
        $api.prodEditOperator(req,function (res) {

        });
    }
    form.on("submit(editMenu)", function (data) {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        console.log(queryArgs)
        var productType = data.field.productType;
        var productName = data.field.productName;
        var productState = data.field.productState;
        var productNumber = data.field.productNumber;
        var supportPrice = data.field.supportPrice;
        var canSold = data.field.canSold;
         cs = data.field.canSold;
        var other1 = data.field.other1;
         oth1 = data.field.other1;
        var productDescribe = data.field.productDescribe;
        var other4=other44;
        var other3=other33;



        //请求
        var url = $tool.getContext() + '/product/upd';
        var req = {
            id: queryArgs['id'],
            productType: productType,
            productName: productName,
            productState: productState,
            canSold:canSold,
            supportPrice:supportPrice,
            productNumber:productNumber,
            productDescribe: productDescribe,
            other1:other1,
            other4:other4,
            other3:other3,



        };

        $api.updateProduct(req, function (data) {
            editOpera();
            layer.msg("修改成功！", {time: 1000}, function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });

        });
        return false;

    })


});


