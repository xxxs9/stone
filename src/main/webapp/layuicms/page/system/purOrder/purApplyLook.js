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

    /**
     * 初始化页面
     * */
    function initTestarea(){
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var id = queryArgs['id'];
        var req = {
            id:id
        };

        $api.getPurOrder(req,function (res) {
            /*var data = res.data;
            if(data.state == '审核未通过'){
                $('#lookState').remove();
            }*/
        });
    }

    initTestarea();
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
            $("[name='state']").val(data.state);
            $("[name='orderAuditUser']").val(data.orderAuditUser);
            $("[name='orderAuditTime']").val(data.orderAuditTime);
            $("[name='auditDescribe']").val(data.auditDescribe);
            form.render();//重新绘制表单，让修改生效
        });
    }

    /**
     * 加载查看列表
     * */
    /*function loadRoleList() {
        var url = $tool.getContext()+'purchase_order/look.do';
        var req =  {
            page:1,
            limit:10
        };*/

        /*$api.listPurOrder(req,function (res) {
            var data = res.data;
            if(data.length > 0){
                var depotHtml = "";
                for(var i = 0;i<data.length;i++){
                    //是否初始化选中
                    if($.inArray(data[i].id) != -1){
                        depotHtml += '<input type="checkbox" checked name="'+data[i].id+'" title="'+data[i].applyUser+'">';
                    }else{
                        depotHtml += '<input type="checkbox" name="'+data[i].id+'" title="'+data[i].applyUser+'">';
                    }

                    depotIdList.push(data[i].id);//保存id列表
                }

                $('.role-check-list').append($(depotHtml));
                form.render();//重新绘制表单，让修改生效
            }
        });
    }*/

    /**
     * 表单提交
     * */
    form.on("submit(purLook)", function (data) {
        var queryArgs =  $tool.getQueryParam();//获取查询参数
        var state = data.field.state;
        var orderAuditUser = data.field.orderAuditUser;
        var orderAuditTime = data.field.orderAuditTime;
        var auditDescribe = data.field.auditDescribe;
        var idList = new Array();

        //获取选中的角色列表
        for(var i=0;i<depotIdList.length;i++){
            if(data.field[depotIdList[i]] === 'on'){
                idList.push(depotIdList[i]);
            }
        }

        //请求
        //var url = $tool.getContext()+'purchase_order/update.do';
        var req = {
            id:queryArgs['id'],
            state:state,
            orderAuditUser:orderAuditUser,
            orderAuditTime:orderAuditTime,
            auditDescribe:auditDescribe,
            depotIdList:idList
        };

        $api.lookPurOrder(req,function (data) {
            layer.msg("即将返回！",{time:1000},function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });
        return false;
    })
});
