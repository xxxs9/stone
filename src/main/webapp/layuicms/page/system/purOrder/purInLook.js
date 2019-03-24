layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention:'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool:'tool',
    $api:'api'
}).use(['form', 'layer','$api', 'jquery','ajaxExtention','$tool'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery,
        $tool = layui.$tool,
        $api = layui.$api;

    var depotIdList = new Array();//所有的角色id列表

    function init() {
        //初始化商品名称下拉框
        initDepotInfo();
    }
    init();

    /**
     * 初始化菜单信息
     * */
    function initDepotInfo() {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var id = queryArgs['id'];
        //var url = $tool.getContext()+'purchase_order/get.do';
        var req = {
            id:id
        };

        $api.lookGetPurOrder(req,function (res) {
            var data = res.data;
            console.log(data)
            $("[name='id']").val(data.id);
            $("[name='depotState']").val(data.depotState);
            $("[name='depotUser']").val(data.depotUser);
            $("[name='depotTime']").val(data.depotTime);
            $("[name='depotDescribe']").val(data.depotDescribe);
            form.render();//重新绘制表单，让修改生效
        });
    }

    /**
     * 表单提交
     * */
    form.on("submit(purLook)", function (data) {
        var queryArgs =  $tool.getQueryParam();//获取查询参数
        var depotState = data.field.depotState;
        var depotUser = data.field.depotUser;
        var depotTime = data.field.depotTime;
        var depotDescribe = data.field.depotDescribe;
        var idList = new Array();

        //获取选中的角色列表
        for(var i=0;i<depotIdList.length;i++){
            if(data.field[depotIdList[i]] === 'on'){
                idList.push(depotIdList[i]);
            }
        }

        //请求
        var req = {
            id:queryArgs['id'],
            depotState:depotState,
            depotUser:depotUser,
            depotTime:depotTime,
            depotDescribe:depotDescribe,
            depotIdList:idList
        };

        $api.lookInPurOrder(req,function (data) {
            layer.msg("即将返回！",{time:1000},function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });
        return false;
    })
});
