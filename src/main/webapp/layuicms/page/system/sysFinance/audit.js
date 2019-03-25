layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api:'api'
}).use(['form', 'laydate', 'layer', 'jquery', 'table', 'laypage', 'ajaxExtention', '$tool','$api'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery,
        laypage = layui.laypage,
        $tool = layui.$tool,
        laydate = layui.laydate,
        table = layui.table,
        $api = layui.$api;

    var tableIns;//表格实例

    /**
     * 初始化
     */

    function init(){
        initPage();
    }

    function initPage(){
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var id = queryArgs['id'];
        var auditType = queryArgs['auditType'];
        console.log(queryArgs)
        var req={
            id:id,
        }
        if(auditType == 1){
            $api.getPurchasePayById(req,function (res) {

                var data = res.data;
                var auditState = $("[name='auditState']").val(data.auditState);
                $("[name='auditType']").val(data.auditType);
                $("[name='purchaseOrderId']").val(data.purchaseOrderId);
                $("[name='unitPrice']").val(data.unitPrice);
                $("[name='goodsNumber']").val(data.goodsNumber);
                $("[name='totalPrice']").val(data.totalPrice);
                $("[name='actualBalance']").val(data.actualBalance);
                $("[name='documentMaker']").val(data.documentMaker);
                $("[name='documentMakeTime']").val(data.documentMakeTime);
                $("[name='auditDescribe']").val(data.auditDescribe);

                if(auditState.val() != 2){
                    $('#btn').css('display','none');
                }
                form.render();//重新绘制表单，让修改生效
            });
        } else if(auditType ==3){
            $api.getPurchaseReceiveById(req,function (res) {

                var data = res.data;
                console.log(data)
                var auditState = $("[name='auditState']").val(data.auditState);
                $("[name='auditType']").val(data.auditType);
                $("[name='purchaseOrderId']").val(data.purchaseOrderRejectedId);
                $("[name='unitPrice']").val(data.unitPrice);
                $("[name='goodsNumber']").val(data.rejectedNumber);
                $("[name='totalPrice']").val(data.totalPrice);
                $("[name='actualBalance']").val(data.actualBalance);
                $("[name='documentMaker']").val(data.documentMaker);
                $("[name='documentMakeTime']").val(data.documentMakeTime);
                $("[name='auditDescribe']").val(data.auditDescribe);

                if(auditState.val() != 2){
                    $('#btn').css('display','none');
                }
                form.render();//重新绘制表单，让修改生效
            });
        }

    }

    init();

    /**
     * 表单提交 审核通过
     * */
    form.on("submit(pass)", function (data) {//付款
        //var queryArgs = $tool.getQueryParam();//获取查询参数

        var auditType = data.field.auditType;
        var attitude = data.elem.value
        var actualPrice = data.field.actualBalance;
        var auditDescribe = data.field.auditDescribe;

        if(auditType == 1){//采购应付
            var purchaseOrderId = data.field.purchaseOrderId;

            var req = {
                attitude:attitude,
                purchaseOrderId:purchaseOrderId,
                auditType:auditType,
                actualPrice:actualPrice,
                auditDescribe:auditDescribe,
            };

            $api.purchaseOrderPayPass(req,function (data) {
                layer.msg("审核完成！",{time:1000},function () {
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                });
            });
            return false;

        }else if(auditType == 2){//销售应收
            var saleId = data.field.saleId;
            var req = {
                attitude:attitude,
                saleId:saleId,
                auditType:auditType,
                actualPrice:actualPrice,
                auditDescribe:auditDescribe,
            };

            $api.UpdatePay(JSON.stringify(req),{contentType:'application/json;charset=utf-8'},function (data) {
                layer.msg("审核完成！",{time:1000},function () {
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                });
            });
            return false;

        }else if(auditType == 3){//采购应收

            var purchaseOrderRejectedId = data.field.purchaseOrderId;
            var req = {
                attitude:attitude,
                purchaseOrderRejectedId:purchaseOrderRejectedId,
                auditType:auditType,
                actualPrice:actualPrice,
                auditDescribe:auditDescribe,
            };

            $api.purchaseOrderReceivePass(req,function (data) {
                layer.msg("审核完成！",{time:1000},function () {
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                });
            });
            return false;

        }else if(auditType == 4){//销售应付
            var saleRejectedId = data.field.saleRejectedId;
            var req = {
                attitude:attitude,
                saleRejectedId:saleRejectedId,
                auditType:auditType,
                actualPrice:actualPrice,
                auditDescribe:auditDescribe,
            };

            $api.UpdatePay(JSON.stringify(req),{contentType:'application/json;charset=utf-8'},function (data) {
                layer.msg("审核完成！",{time:1000},function () {
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                });
            });
            return false;
        }

    })

    /**
     * 表单提交
     * */
    //TODO....修改应付单
    form.on("submit(unpass)", function (data) {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var menuName = data.field.menuName;
        var menuUrl = data.field.menuUrl;
        var requestUrl = data.field.requestUrl;
        var sort = data.field.sort;
        var idList = new Array();

        //获取选中的角色列表
        for(var i=0;i<roleIdList.length;i++){
            if(data.field[roleIdList[i]] === 'on'){
                idList.push(roleIdList[i]);
            }
        }

        //请求
        var url = $tool.getContext()+'menu/update.do';
        var req = {
            id:queryArgs['id'],
            menuName:menuName,
            menuUrl:menuUrl,
            requestUrl:requestUrl,
            sort:sort,
            roleIdList:idList
        };

        $api.UpdatePay(JSON.stringify(req),{contentType:'application/json;charset=utf-8'},function (data) {
            layer.msg("修改成功！",{time:1000},function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });

        return false;
    })
});