layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api:'api'
}).use(['form', 'layer', 'jquery', 'ajaxExtention', '$tool','$api'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery,
        $tool = layui.$tool ,
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
        initParentMenu();
    }

    init();

    /**
     * 初始化下拉框
     * */
   function initParentMenu() {
        $api.getProductByState(null,function (res) {
            var data = res.data;
            if (data.length > 0) {
                var html = '<option value="">--请选择--</option>';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].id + '">' + data[i].productName + '</option>>';
                }
                $('#productId').append($(html));
                form.render();
            }
        });
        $api.getAllFormula(null,function (res) {
            var data = res.data;
            if (data.length > 0) {
                var html = '<option value="">--请选择--</option>';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].formulaBack + '">' + data[i].formulaBack + '</option>>';
                }
                $('#formulaBack').append($(html));
                form.render();
            }
        });
        $api.getAllFormula(null,function (res) {
            var data = res.data;
            if (data.length > 0) {
                var html = '<option value="">--请选择--</option>';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].id + '">' + data[i].id + '</option>>';
                }
                $('#produceFormulaId').append($(html));
                form.render();
            }
        });
        $api.getProduceFormulaDetail(null,function (res) {
            var data = res.data;
            if (data.length > 0) {
                var html = '<option value="">--请选择--</option>';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].id + '">' + data[i].id + '</option>>';
                }
                $('#produceFormulaDetailId').append($(html));
                form.render();
            }
        });
    }


    /**
     * 表单提交
     * */
    form.on("submit(addMenu)", function (data) {
        /*var id = data.field.id;*/
        var productId = data.field.productId;
        var produceFormulaId = data.field.produceFormulaId;
        var state = data.field.state;
        var produceFormulaDetailId = data.field.produceFormulaDetailId;
        var depotAudi = data.field.depotAudi;
        var formulaBack = data.field.formulaBack;


        var reachUser = data.field.reachUser;
        var reachTime = data.field.reachTime;
        var other1 = data.field.other1;
        var other2 = data.field.other2;
        var other3 = data.field.other3;

        //请求
        var req = {
            /*id:id,*/
            productId: productId,
            produceFormulaId: produceFormulaId,
            state:state,
            produceFormulaDetailId:produceFormulaDetailId,
            depotAudi:depotAudi,
            formulaBack:formulaBack,
            reachUser:reachUser,
            reachTime:reachTime,
            other1:other1,
            other2:other2,
            other3:other3,

        };

        $api.addFormulaReach(req,function (data) {
            //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
            layer.msg("领料单新增成功！", {time: 1000}, function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });

        return false;
    })

});


