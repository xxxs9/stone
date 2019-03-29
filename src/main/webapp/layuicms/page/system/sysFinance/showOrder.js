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
        var id = queryArgs['applyId','applyType'];
        var url = $tool.getContext()+'purchase_order/get.do';
        var req = {
            id:queryArgs.applyId,
            applyType:queryArgs.applyType
        };

        //沧海的getPurOrder
        if(queryArgs.applyType == 1 || queryArgs.applyType==3){
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


                if(queryArgs.applyType == 1 || queryArgs.applyType==3){
                    $('#apply').text('采购订单申请');
                } else if(queryArgs.applyType == 2 || queryArgs.applyType==4){
                    $('#apply').text('销售订单申请');
                }
                form.render();//重新绘制表单，让修改生效
            });
        } else if(queryArgs.applyType == 2){
            $api.GetShipmentOrder(req,function (res) {
                var data = res.data;
                var goodsAmount = data.goodsAmount;
                var goodsNumber = data.goodsNumber;
                var unitPrice = goodsAmount/goodsNumber;
                $('#id').val(id);
                $("[name='goodsId']").val(data.goodsName);
                $("[name='goodsNumber']").val(data.goodsNumber);
                $("[name='price']").val(unitPrice);
                $("[name='applyUser']").val(data.applyUser);
                $("[name='applyTime']").val(data.applyTime);
                $("[name='applyDescribe']").val(data.applyDescribe);
                //menu_roleIds = data.roleIdList;//保存菜单所属角色id列表，初始化选中时用
                //加载角色列表
                //loadRoleList();


                if(queryArgs.applyType == 1 || queryArgs.applyType==3){
                    $('#apply').text('采购订单申请');
                } else if(queryArgs.applyType == 2 || queryArgs.applyType==4){
                    $('#apply').text('销售订单申请');
                }
                form.render();//重新绘制表单，让修改生效
            });
        }else if(queryArgs.applyType == 4){
            $api.GetReturnGoodsOrder(req,function (res) {
                var data = res.data;
                var goodsAmount = data.goodsAmount;
                var goodsNumber = data.goodsNumber;
                var unitPrice = goodsAmount/goodsNumber;
                $('#id').val(id);
                $("[name='goodsId']").val(data.goodsName);
                $("[name='goodsNumber']").val(data.goodsNumber);
                $("[name='price']").val(unitPrice);
                $("[name='applyUser']").val(data.applyUser);
                $("[name='applyTime']").val(data.applyTime);
                $("[name='applyDescribe']").val(data.applyDescribe);
                //menu_roleIds = data.roleIdList;//保存菜单所属角色id列表，初始化选中时用
                //加载角色列表
                //loadRoleList();


                if(queryArgs.applyType == 1 || queryArgs.applyType==3){
                    $('#apply').text('采购订单申请');
                } else if(queryArgs.applyType == 2 || queryArgs.applyType==4){
                    $('#apply').text('销售订单申请');
                }
                form.render();//重新绘制表单，让修改生效
            });
        }

    }

    init();

    /**
     * 监听生成应付单按钮
     */
    $(document).on('click','#show',function(data){

        var purchaseOrderId = $('#id').val();
        var number = $('[name=goodsNumber]').val();
        var price = $('[name=price]').val();
        var totalPrice = price * number;
        var req={
            auditType : 1,
            purchaseOrderId:purchaseOrderId,
            goodsNumber : number,
            unitPrice : price,
            totalPrice : totalPrice
        }

        var req1={
            purchaseOrderId:purchaseOrderId
        }
        $api.getPurchasePay(req1,function(res){
            var data = res.data;
            if(data != null){
                $('#show').text('查看应付单');
                $('#addPurchasePay').show()
                $("[name='purchaseOrderId']").val(data.purchaseOrderId);
                $("[name='auditType']").val(data.auditType);
                $("[name='unitPrice']").val(data.unitPrice);
                $("[name='number']").val(data.goodsNumber);
                $("[name='totalPrice']").val(data.totalPrice);
                form.render();//重新绘制表单，让修改生效
            } else{
                $api.insertPurchasePay(req,function(data) {
                    //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
                    $('#addPurchasePay').show()
                    $("[name='purchaseOrderId']").val(data.purchaseOrderId);
                    $("[name='unitPrice']").val(data.unitPrice);
                    $("[name='price']").val(data.price);
                    $("[name='number']").val(data.number);
                    $("[name='totalPrice']").val(data.totalPrice);
                });
            }

            form.render();//重新绘制表单，让修改生效
        })
    })

    /**
     * 表单提交,审核情况
     * */
    form.on("submit(auditing)", function (data) {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        //请求
        //var url = $tool.getContext()+'finance/auditingPurchaseOrder.do';
        var payId = $('[name=purchaseOrderId]').val();
        var req = {
            payId:payId,
        };
        //TODO...需要沧海提供一个更新财务审核状态接口
        $api.generatePurchasePay(req ,function (data) {
            layer.msg("修改成功！",{time:1000},function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });
        return false;
    })

});