layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api:'api'
}).use(['form', 'layer', 'jquery', 'table', 'laypage', 'ajaxExtention', '$tool','$api'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery,
        laypage = layui.laypage,
        $tool = layui.$tool,
        table = layui.table,
        $api = layui.$api;

    var tableIns;//表格实例
    /**
     * 页面初始化
     * */
    function init() {

        //初始化采购订单申请信息
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var id = queryArgs['id'];
        var url = $tool.getContext()+'purchase_order/get.do';
        var req = {
            id:id
        };

        //沧海的getPurOrder
        $api.getPurOrder(req,function (res) {

            var data = res.data;
            $('#id').val(id);
            $("[name='goodsId']").val(data.goodsId);
            $("[name='goodsNumber']").val(data.goodsNumber);
            $("[name='price']").val(data.price);
            $("[name='applyUser']").val(data.applyUser);
            $("[name='applyTime']").val(data.applyTime);
            $("[name='applyDescribe']").val(data.applyDescribe);

            //menu_roleIds = data.roleIdList;//保存菜单所属角色id列表，初始化选中时用
            //加载角色列表
            //loadRoleList();
            form.render();//重新绘制表单，让修改生效
        });
        $api.getPurOrder(req,function (res) {
            //alert($('#id').val())  申请订单id
            var data = res.data;
            $("[name='goodsId']").val(data.goodsId);
            $("[name='goodsNumber']").val(data.goodsNumber);
            $("[name='price']").val(data.price);
            $("[name='applyUser']").val(data.applyUser);
            $("[name='applyTime']").val(data.applyTime);
            $("[name='applyDescribe']").val(data.applyDescribe);

            //menu_roleIds = data.roleIdList;//保存菜单所属角色id列表，初始化选中时用
            //加载角色列表
            //loadRoleList();
            form.render();//重新绘制表单，让修改生效
        });
    }

    init();

    /**
     * 表单提交,审核情况
     * */
    form.on("submit(auditing)", function (data) {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var financeAuditDescribe = data.field.financeAuditDescribe;
        var isAgree = data.elem.getAttribute('value')

        //请求
        //var url = $tool.getContext()+'finance/auditingPurchaseOrder.do';
        var req = {
            id:queryArgs['id'],
            financeAuditDescribe:financeAuditDescribe,
            isAgree:isAgree
        };

        //TODO...需要沧海提供一个更新财务审核状态接口
        $api.auditingPurchase(req ,function (data) {
            layer.msg("修改成功！",{time:1000},function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });
        return false;
    })

});