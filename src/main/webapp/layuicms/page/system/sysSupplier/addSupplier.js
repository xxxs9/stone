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
    }

    init();



    /**
     * 表单提交
     * */
    form.on("submit(addSupplier)", function (data) {
        var supplierName = data.field.supplierName;
        var supplierDescribe = data.field.supplierDescribe;
        var chargeName = data.field.chargeName;
        var phone = data.field.phone;
        var email = data.field.email;

        //请求
        var req = {
            supplierName:supplierName,
            supplierDescribe: supplierDescribe,
            chargeName:chargeName,
            phone: phone,
            email:email
        };

        $api.AddSupplier(req,function (data) {
            //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
            layer.msg("供应商添加成功！", {time: 1000}, function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });

        return false;
    })

});


