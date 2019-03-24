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
        initSupplierInfo();
    }
    init();

    /**
     * 初始化菜单信息
     * */
    function initSupplierInfo() {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var id = queryArgs['id'];
        var url = $tool.getContext()+'supplier/get.do';
        var req = {
            id:id
        };

        $api.GetSupplier(req,function (res) {
            var data = res.data;
            $("[name='supplierName']").val(data.supplierName);
            $("[name='supplierDescribe']").val(data.supplierDescribe);
            $("[name='phone']").val(data.phone);
            form.render();//重新绘制表单，让修改生效
        });
    }


    /**
     * 表单提交
     * */
    form.on("submit(editSupplier)", function (data) {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var supplierName = data.field.supplierName;
        var supplierDescribe = data.field.supplierDescribe;
        var phone = data.field.phone;

        //请求
        var url = $tool.getContext()+'supplier/update.do';
        var req = {
            id:queryArgs['id'],
            supplierName:supplierName,
            supplierDescribe:supplierDescribe,
            phone:phone
        };

        $api.UpdateSupplier(JSON.stringify(req),{contentType:'application/json;charset=utf-8'},function (data) {
            layer.msg("修改成功！",{time:1000},function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });
        return false;
    })

});


