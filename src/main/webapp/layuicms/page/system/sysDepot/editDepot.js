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
        initDepotInfo();
    }
    init();

    /**
     * 初始化菜单信息
     * */
    function initDepotInfo() {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var id = queryArgs['id'];
        var url = $tool.getContext()+'depotSet/get.do';
        var req = {
            id:id
        };

        $api.GetDepot(req,function (res) {
            var data = res.data;
            $("[name='depotNumber']").val(data.depotNumber);
            $("[name='depotName']").val(data.depotName);
            $("[name='depotType']").val(data.depotType);
            $("[name='depotAddress']").val(data.depotAddress);
            $("[name='depotDescribe']").val(data.depotDescribe);
            form.render();//重新绘制表单，让修改生效
        });
    }


    /**
     * 表单提交
     * */
    form.on("submit(editDepot)", function (data) {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var depotNumber = data.field.depotNumber;
        var depotName = data.field.depotName;
        var depotType = data.field.depotType;
        var depotAddress = data.field.depotAddress;
        var depotDescribe = data.field.depotDescribe;

        //请求
        var url = $tool.getContext()+'depot/update.do';
        var req = {
            id:queryArgs['id'],
            depotNumber:depotNumber,
            depotName: depotName,
            depotType: depotType,
            depotAddress:depotAddress,
            depotDescribe:depotDescribe,
        };

        $api.UpdateDepot(JSON.stringify(req),{contentType:'application/json;charset=utf-8'},function (data) {
            layer.msg("修改成功！",{time:1000},function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });
        return false;
    })

});


