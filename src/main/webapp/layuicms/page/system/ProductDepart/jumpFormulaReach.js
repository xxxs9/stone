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
        initMenuInfo()
        selectProduct();
        select4();
        select5();
        // selectFormula()

    }

    init();
    /**
     * 挑选成品配方
     */
    /* function selectFormula(){
         $api.getAllFormula(null,function (res) {
             var html = '<input type="checkbox" name="" title="写作" checked>';
             $('#myform').append($(html))
            var data4 = res.data;
            if (data4.length>0){
                for (var i=0;i<data4.length ;i++){

                }
            }


         });
     }
 */



    /**
     * 表单提交
     * */
        //todo(产品类型)
    var wid = 0;
    function selectProduct() {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var id = queryArgs['id'];
        var req={
            id:id
        }


        $api.getProducePlanById(req, function (res) {

            var data2 = res.data;
            wid=data2.productId;
            //alert( wid);


            $('#productId').val(data2.productId)
            // $('#produceFormulaId1').val(hh);


            function aa(wid){
                var reqs={
                    id:wid
                }
                console.log(reqs)
                $api.getProductById(reqs,function(res2){
                    var data3= res2.data

                    $('#productName').val(data3.productName)
                });

              /*  $api.ProductInfoById(reqs,function(res2){
                    var data4= res2.data
                    console.log(data4);
                    $('#productName').val(data4.productName)

                });*/
            }

            aa(wid)

        });

                //console.log($(wid))
        form.render();

    }


    /* function select2(hh) {
         $api.getProductWasteById(null, function (res) {
             var data3 = res.data;
             if (data3.length > 0) {
                 var html = '<option value="">--请选择--</option>';
                 for (var i = 0; i < data3.length; i++) {
                     html += '<option value="' + data3[i].state + '">' + data3[i].state + '</option>>';
                 }
                 $('#state1').append($(html));
                 $('#state1').val(hh);
                 form.render();
             }

         });

     }*/
    function initMenuInfo() {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var id = queryArgs['id'];

        var url = $tool.getContext() + 'ProductInfoById/get';
        var req = {
            id: id
        };

        $api.getProducePlanById(req, function (res) {
            var data = res.data;

            $("[name='state']").val(data.state);

            //todo(选项的值)

            form.render();//重新绘制表单，让修改生效
        });


    }
    function select4() {
        $api.getAllFormula(null, function (res) {
            var data = res.data;
            if (data.length > 0) {
                var html = '<option value="">--请选择--</option>';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].id + '">'+data[i].id+'</option>';

                }
                $('#produceFormulaId').append($(html));
               // $('#state').val(hh);
                form.render();
            }

        });

    }
    function select5() {
        $api.getProduceFormulaDetail(null, function (res) {
            var data = res.data;
            if (data.length > 0) {
                var html = '<option value="">--请选择--</option>';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].id + '">'+data[i].id+'</option>';
                }
                $('#produceFormulaDetailId').append($(html));
                // $('#state').val(hh);
                form.render();
            }

        });

    }


    /**
     * 表单提交
     * */
    form.on("submit(addMenu)", function (data) {
        var queryArgs = $tool.getQueryParam();//获取查询参数

        var productId = data.field.productId;
        var produceFormulaId = data.field.produceFormulaId;
        var produceFormulaDetailId = data.field.produceFormulaDetailId;
        var depotAudi = data.field.depotAudi;
        var state = data.field.state;
        var formulaBack = data.field.formulaBack;
        var reachUser = data.field.reachUser;
        var reachTime = data.field.reachTime


        //请求
        var url = $tool.getContext() + '/reach/upd';
        var req = {
            id: queryArgs['id'],
            productId:productId,
            produceFormulaDetailId:produceFormulaDetailId,
            depotAudi:depotAudi,
            state: state,
            produceFormulaId:produceFormulaId,
            formulaBack:formulaBack,
            reachUser:reachUser,
            reachTime:reachTime


        };

        $api.addFormulaReach(req, function (data) {

            layer.msg("领料单填写成功！等待仓库审核", {time: 1000}, function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });

        });

        return false;

    })


});


