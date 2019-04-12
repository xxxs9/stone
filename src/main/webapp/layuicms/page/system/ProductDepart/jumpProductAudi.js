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
    var cs;
    var ot1;
    function opera() {
        req={
            operatorUser:cs,
            operatorRemark:ot1
        }
        $api.prodSHOperator(req,function (res) {

        })
    }
    function initMenuInfo() {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var wid = queryArgs['id'];

        var url = $tool.getContext() + '/product/get';
        var req = {
            id: wid
        };

        $api.getProductById(req, function (res) {
            var data6 = res.data;
            cs=data6.canSold;
            ot1= data6.other1;
            $("[name='productName']").val(data6.productName);
            $("[name='productNumber']").val(data6.productNumber);
            $('#canSold').val(data6.canSold);
            select2(data6.productType);
            select(data6.productState);
            $("[name='productDescribe']").val(data6.productDescribe);
            form.render();//重新绘制表单，让修改生效
        });


    }
    var queryArgs = $tool.getQueryParam();//获取查询参数
    var wid = queryArgs['id'];
    var req={
        id:wid
    }
    $('#audiOk').click(function () {

        $api.managerAudi(req,function (res) {
            opera();
            if(res.code==='401'){
                window.location.href = $tool.getResUrl()+"layuicms/page/system/401.html";

            }

            layer.msg(">>>>>>>审核完成<<<<<<<", {time: 1000}, function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
            return false;
        });
    });
    var queryArgs = $tool.getQueryParam();//获取查询参数
    var wid = queryArgs['id'];
    var req={
        id:wid
    }
    $('#audiNot').click(function () {
        $api.audiNot(req,function (res) {
            alert(req);
            layer.msg(">>>审核未通过，已驳回<<<", {time: 1000}, function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
            return false;

        })
    });

    /**
     * 表单提交
     * */
/*
    form.on("submit(editMenu)", function (data) {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        console.log(queryArgs)
        var productType = data.field.productType;
        var productName = data.field.productName;
        var productState = data.field.productState;
        var productNumber = data.field.productNumber;
        var supportPrice = data.field.supportPrice;
        var canSold = data.field.canSold;
        var other2 = window.sessionStorage.getItem('sysUser');
        var productDescribe = data.field.productDescribe;


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
            other2:other2

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
*/


});


