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
        //初始化菜单信息
        initDepotInfo();
    }
    init();

    /**
     * 初始化下拉框
     * */
    function initGoods(hh) {
        $api.getListGoods(null,function (res) {
            var data = res.data;
            if (data.length > 0) {
                var html = '<option value="">--请选择--</option>';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i] + '">' + data[i] + '</option>>';
                }
                $('#parentGoodsId').append($(html));
                $('#parentGoodsId').val(hh);
                form.render();
            }
        });
    }

    /**
     * 初始化菜单信息
     * */
    function initDepotInfo() {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var id = queryArgs['id'];

        var url = $tool.getContext()+'purchaser_order/get.do';
        var req = {
            id:id
        };

        $api.getPurOrder(req,function (res) {
            var data = res.data;
            //console.log(data)
            $("[name='id']").val(data.id);
            $("[name='orderNumber']").val(data.orderNumber);
            initGoods(data.goodsId);
            $("[name='goodsNumber']").val(data.goodsNumber);
            $("[name='price']").val(data.price);
            $("[name='applyUser']").val(data.applyUser);
            //$("[name='applyTime']").val(data.applyTime);
            $("[name='applyDescribe']").val(data.applyDescribe);
            $("[name='state']").val(data.state);
            $("[name='orderAuditUser']").val(data.orderAuditUser);
            //$("[name='orderAuditTime']").val(data.orderAuditTime);
            $("[name='auditDescribe']").val(data.auditDescribe);
            //depotIds = data.depotIds;//保存菜单所属角色id列表，初始化选中时用
            //加载角色列表
            loadRoleList();
            form.render();//重新绘制表单，让修改生效
        });
    }

    /**
     * 加载审核列表
     * */
    function loadRoleList() {
        var url = $tool.getContext()+'purchase_order/listInspect.do';
        var req =  {
            page:1,
            limit:10
        };

        $api.listPurOrder(req,function (res) {
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
    }


    /**
     * 表单提交
     * */
    form.on("submit(inspectPur)", function (data) {
        var id = data.field.id;
        var orderNumber = data.field.orderNumber;
        var goodsId = data.field.goodsId;
        var goodsNumber = data.field.goodsNumber;
        var price = data.field.price;
        var applyUser = data.field.applyUser;
        //var applyTime = data.field.applyTime;
        var applyDescribe = data.field.applyDescribe;
        var state = $(this).html();
        var orderAuditUser = data.field.orderAuditUser;
        //var orderAuditTime = data.field.orderAuditTime;
        var auditDescribe = data.field.auditDescribe;
        var idList = new Array();

        //请求
        var url = $tool.getContext()+'purchase_order/inspect.do';
        var req = {
            id:id,
            orderNumber:orderNumber,
            goodsId:goodsId,
            goodsNumber:goodsNumber,
            price:price,
            applyUser:applyUser,
            //applyTime:applyTime,
            applyDescribe:applyDescribe,
            state:state,
            orderAuditUser:orderAuditUser,
            //orderAuditTime:orderAuditTime,
            auditDescribe:auditDescribe,
            depotIdList:idList
        };

        $api.inspectPurOrder(req,function (data) {
            layer.msg("提交成功！",{time:1000},function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });
        return false;
    })

});
