layui.config({
    base: $config.resUrl + 'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api: 'api'
}).use(['form', 'layer', 'jquery', 'ajaxExtention', '$tool', '$api','laydate'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery,
        $tool = layui.$tool,
        $api = layui.$api;

    layui.use('laydate', function(){
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#reachTime' ,//指定元素
            type:'date',
            format:'yyyy-MM-dd'
        });
    });

    /**
     * 页面初始化
     * */

    function init() {
        //初始化下拉框


        var queryArgs = $tool.getQueryParam();
        var id = queryArgs['id'];

        var req = {
            id:id
        }
        $api.getBillCheckById(req,function (res) {
            var data = res.data;
            console.log(data);
            $("[name='productId']").val(data.productId);
            $("[name='productName']").val(data.productName);
            /*实际产量*/
            $("[name='checkNumber']").val(data.checkNumber);
            $("[name='checkUser']").val(data.checkUser);
            var planId = data.planId;
            console.log(planId);
            var req={
                id:planId
            }
            $api.getProducePlanById(req,function (res) {
                var data2= res.data;
                console.log(data2)
                /*计划产量*/
                $("[name='planNumber']").val(data2.planNumber);
                console.log(data2.planNumber);
            })






            form.render();
        });



    }

    init();
    stateOk();
    stateNotOk();
       function stateOk(){
           var queryArgs = $tool.getQueryParam();
           var id = queryArgs['id'];

           var req = {
               id:id
           }
           $('#ok').click(function () {
               $api.ok1(req,function (res) {
                   layer.msg("验证通过！", {time: 3000}, function () {
                       layer.closeAll("iframe");
                       //刷新父页面
                       parent.location.reload();
                   });
               });
               return false;
           })
       }
    function stateNotOk(){
        var queryArgs = $tool.getQueryParam();
        var id = queryArgs['id'];

        var req = {
            id:id
        }
        $('#not').click(function () {
            $api.notOk(req,function (res) {
                layer.msg("验证没通过！", {time: 3000}, function () {
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                });
            });
            return false;
        })
    }

    /**
     * 表单提交
     * */
    /*form.on("submit(addMenu)", function (data) {
        /!*var id = data.field.id;*!/
        var planId = data.field.planId;
        var reachId = data.field.reachId;
        var productId = data.field.productId;
        var checkNumber = data.field.checkNumber;
        var checkRemark = data.field.checkRemark;
        var checkUser = data.field.checkUser;
        var productName = data.field.productName;


        //请求
        var req = {
            /!*id:id,*!/
            checkUser:checkUser,
            planId:planId,
            reachId:reachId,
            productName:productName,
            productId:productId,
            checkNumber:checkNumber,
            checkRemark:checkRemark



        };

        $api.addBillCheckNew(req,function (data) {
            //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
            layer.msg("检验单提交成功！", {time: 1000}, function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });

        return false;
    })*/

});


