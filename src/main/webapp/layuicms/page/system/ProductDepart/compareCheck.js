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
    var pn;
    var rn
    var reachId1;
    var planId1;
    function init() {
        //初始化下拉框


        var queryArgs = $tool.getQueryParam();
        var id = queryArgs['id'];

        var req = {
            id:id
        }
        $api.getProductById(req,function (res) {
            var data = res.data;
            console.log(data);
            $("[name='productId']").val(id);
            $("[name='productName']").val(data.productName);
            /*实际产量*/
            $("[name='checkNumber']").val(data.checkNumber);

            $("[name='checkUser']").val(window.sessionStorage.getItem('sysUser'));
            var planId = data.planId;
            console.log(planId);
            var req={
                productId:id
            }
            $api.getAllProducePlanId(req,function (res) {
                var data2= res.data;
                console.log(data2)
                /*计划产量*/
                $("[name='planNumber']").val(data2.planNumber);
                pn= data2.planNumber;
                console.log(data2.planNumber);
            });
            $api.getReachByProductId(req,function (res) {
                var data2 = res.data;
                reachId1=data2.id;
            });
            $api.getAllProducePlanId(req,function (res) {
                var data3 = res.data;
                planId1 = data3.id;
            });


            form.render();
        });



    }

    init();
    stateOk();
    stateNotOk();



    function insertSupport() {
        var queryArgs = $tool.getQueryParam();
        var id = queryArgs['id'];
       var supportPrice= $("#supportPrice").val()
        var req5={
            id:id,
            supportPrice:supportPrice
        }
        $api.insertSupportPrice(req5,function (res) {

        })

    }
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
    function changeProductState(id){
           var req ={
               id:id
           }
        //检查合格则到未入库的状态
           if ($("#state").val()==='合格'){

               $api.unIntodepot(req,function (res) {

               })
           }

           if ($("#state").val()==='不合格'){
               $api.continueProduce1(req,function (res) {

               })
           }

    }

    /**
     * 表单提交
     * */
    form.on("submit(addMenu)", function (data) {
        /*var id = data.field.id;*/
        var planId = planId1;
        var reachId = reachId1;
        var productId = data.field.productId;
        var checkNumber = data.field.checkNumber;
        var checkRemark = data.field.checkRemark;
        var checkUser = data.field.checkUser;
        var productName = data.field.productName;
        var state= data.field.state;


        //请求
        var req = {
            /*id:id,*/
            checkUser:checkUser,
            planId:planId,
            reachId:reachId,
            productName:productName,
            productId:productId,
            checkNumber:checkNumber,
            checkRemark:checkRemark,
            state:state
        };

        $api.addBillCheckNew(req,function (data) {
            changeProductState(productId);
            insertSupport();
            //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
            layer.msg("检验单提交成功！", {time: 1000}, function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });

        return false;
    })

});


