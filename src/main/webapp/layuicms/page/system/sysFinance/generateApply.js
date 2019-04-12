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

        //如果订单类型为3
        if(queryArgs.applyType == 2){
            $api.GetMarkerOrder(req,function (res) {
                var data = res.data;
                var goodsNumber = data.goodsNumber;
                var totalPrice = data.goodsAmount;
                var plannedNumber = totalPrice/goodsNumber;
                var financeState;
                console.log(data)
                $('#id').val(id);
                $("[name='goodsName']").val(data.productId);
                $("[name='goodsId']").val(data.id);
                $("[name='auditType']").val(2);
                $("[name='goodsNumber']").val(data.deliverNumber);
                $("[name='price']").val(data.plannedNumber);
                $("[name='totalPrice']").val(data.acceptedAmount);
                $("[name='applyUser']").val(data.applyUser);
                $("[name='applyTime']").val(data.orderTime);
                $("[name='applyDescribe']").val(data.remarks);

                //alert(data.state())

                //alert(financeState)
                if(data.state != '等待财务审核'){
                    $('#gnrt').css("display",'none');
                }

                if(queryArgs.applyType == 1 ){
                    $('#apply').text('采购订单应付单');
                } else if(queryArgs.applyType == 2){
                    $('#apply').text('销售出货应收单');
                }else if(queryArgs.applyType == 3 ){
                    $('#apply').text('采购退货应收单');
                }else if(queryArgs.applyType == 4){
                    $('#apply').text('销售退货应付单');
                }
                form.render();//重新绘制表单，让修改生效

            });

        }else if(queryArgs.applyType == 4){//如果订单类型为4

            $api.GetReturnGoodsOrder(req,function (res) {
                var data = res.data;
                var goodsNumber = data.goodsNumber;
                var totalPrice = data.goodsAmount;
                var price = totalPrice/goodsNumber;
                var financeState ;
                console.log(data)
                $('#id').val(id);
                $("[name='goodsName']").val(data.goodsId);
                $("[name='goodsId']").val(data.id);
                $("[name='auditType']").val(4);
                $("[name='goodsNumber']").val(goodsNumber);
                $("[name='price']").val(price);
                $("[name='totalPrice']").val(totalPrice);
                $("[name='applyUser']").val(data.applyUser);
                $("[name='applyTime']").val(data.applyTime);
                $("[name='applyDescribe']").val(data.applyDescribe);
                //alert(data.state())
                if(data.state != '等待财务查收'){
                    $('#gnrt').css("display",'none');
                }

                if(queryArgs.applyType == 1 ){
                    $('#apply').text('采购订单应付单');
                } else if(queryArgs.applyType == 2){
                    $('#apply').text('销售出货应收单');
                }else if(queryArgs.applyType == 3 ){
                    $('#apply').text('采购退货应收单');
                }else if(queryArgs.applyType == 4){
                    $('#apply').text('销售退货应付单');
                }
                form.render();//重新绘制表单，让修改生效

            });

        }else if(queryArgs.applyType == 1 ){//如果订单类型为1和2
            //沧海的getPurOrder
            $api.getPurOrder(req,function (res) {
                var data = res.data;
                var price = data.price;
                var goodsNumber = data.goodsNumber;
                var totalPrice = price * goodsNumber;
                console.log(data)
                var financeState = data.financeState;
                $('#id').val(id);
                $("[name='goodsName']").val(data.goodsName);
                $("[name='goodsId']").val(data.id);
                $("[name='auditType']").val(data.auditType);
                $("[name='goodsNumber']").val(goodsNumber);
                $("[name='price']").val(price);
                $("[name='totalPrice']").val(totalPrice);
                $("[name='applyUser']").val(data.applyUser);
                $("[name='applyTime']").val(data.applyTime);
                $("[name='applyDescribe']").val(data.applyDescribe);
                if(financeState != null){
                    $('#gnrt').css("display",'none');
                }
                /*var f  = financeState != '待审核'
                alert(f)*/
                if(queryArgs.applyType == 1 ){
                    $('#apply').text('采购订单应付单');
                } else if(queryArgs.applyType == 2){
                    $('#apply').text('销售出货应收单');
                }else if(queryArgs.applyType == 3 ){
                    $('#apply').text('采购退货应收单');
                }else if(queryArgs.applyType == 4){
                    $('#apply').text('销售退货应付单');
                }
                form.render();//重新绘制表单，让修改生效
            });
        } else if(queryArgs.applyType == 3){
            //沧海的getPurOrder
            $api.getPurchaseReturn(req,function (res) {
                var data = res.data;
                var price = data.price;
                var goodsNumber = data.goodsNumber;
                var totalPrice = price * goodsNumber;
                console.log(data)
                var financeState = data.financeState;
                $('#id').val(id);
                $("[name='goodsName']").val(data.goodsName);
                $("[name='goodsId']").val(data.id);
                $("[name='auditType']").val(data.auditType);
                $("[name='goodsNumber']").val(goodsNumber);
                $("[name='price']").val(price);
                $("[name='totalPrice']").val(totalPrice);
                $("[name='applyUser']").val(data.applyUser);
                $("[name='applyTime']").val(data.applyTime);
                $("[name='applyDescribe']").val(data.applyDescribe);
                if(financeState != null){
                    $('#gnrt').css("display",'none');
                }
                /*var f  = financeState != '待审核'
                alert(f)*/
                if(queryArgs.applyType == 1 ){
                    $('#apply').text('采购订单应付单');
                } else if(queryArgs.applyType == 2){
                    $('#apply').text('销售出货应收单');
                }else if(queryArgs.applyType == 3 ){
                    $('#apply').text('采购退货应收单');
                }else if(queryArgs.applyType == 4){
                    $('#apply').text('销售退货应付单');
                }
                form.render();//重新绘制表单，让修改生效
            });
        }
    }

    init();


    /**
     * 表单提交,审核情况
     * */
    form.on("submit(generate)", function (data) {
        if($('[name=auditType]').val() == 1){

            var queryArgs = $tool.getQueryParam();//获取查询参数
            var id1 = queryArgs['id'];
            //请求
            //var url = $tool.getContext()+'finance/auditingPurchaseOrder.do';
            var payId = $('[name=goodsId]').val();
            var auditType = $('[name=auditType]').val();
            var price = $('[name=price]').val();
            var goodsNumber = $('[name=goodsNumber]').val();
            var req = {
                id1:id1,
                id:payId,
                auditType : auditType,
                price:price,
                goodsNumber:goodsNumber
            };


            $api.generatePurchasePay(req ,function (data) {
                layer.msg("生成成功！",{time:1000},function () {
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                });
            });
            return false;

        }else if($('[name=auditType]').val() == 2){//生成销售应收
            var queryArgs = $tool.getQueryParam();//获取查询参数
            var id1 = queryArgs['id'];
            //请求
            //var url = $tool.getContext()+'finance/auditingPurchaseOrder.do';
            var payId = $('[name=goodsId]').val();
            var auditType = $('[name=auditType]').val();
            var totalPrice = $('[name=totalPrice]').val();
            var goodsNumber = $('[name=goodsNumber]').val();
            var req = {
                id1:id1,
                id:payId,
                auditType : auditType,
                goodsAmount:totalPrice,
                goodsNumber:goodsNumber
            };


            $api.generateSaleReceive(req ,function (data) {
                layer.msg("生成成功！",{time:1000},function () {
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                });
            });
            return false;
        }else if($('[name=auditType]').val() == 3){
            var queryArgs = $tool.getQueryParam();//获取查询参数
            var id1 = queryArgs['id'];
            //请求
            //var url = $tool.getContext()+'finance/auditingPurchaseOrder.do';
            var payId = $('[name=goodsId]').val();
            var auditType = $('[name=auditType]').val();
            var price = $('[name=price]').val();
            var goodsNumber = $('[name=goodsNumber]').val();
            var req = {
                id1:id1,
                id:payId,
                auditType : auditType,
                price:price,
                goodsNumber:goodsNumber
            };
            
            $api.generatePurchaseReceive(req ,function (data) {
                layer.msg("生成成功！",{time:1000},function () {
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                });
            });
            return false;

        }else if($('[name=auditType]').val() == 4){
            var queryArgs = $tool.getQueryParam();//获取查询参数
            var id1 = queryArgs['id'];
            //请求
            //var url = $tool.getContext()+'finance/auditingPurchaseOrder.do';
            var payId = $('[name=goodsId]').val();
            var auditType = $('[name=auditType]').val();
            var totalPrice = $('[name=totalPrice]').val();
            var goodsNumber = $('[name=goodsNumber]').val();
            //alert(totalPrice)
            var req = {
                id1:id1,
                id:payId,
                auditType : auditType,
                goodsAmount:totalPrice,
                goodsNumber:goodsNumber
            };


            $api.generateSalePay(req ,function (data) {
                layer.msg("生成成功！",{time:1000},function () {
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                });
            });
            return false;
        }

    })

});