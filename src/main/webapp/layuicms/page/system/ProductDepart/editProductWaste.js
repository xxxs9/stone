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
            elem: '#test2' ,//指定元素
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
    }

    init();


    /**
     * 表单提交
     * */
    //todo(产品类型)
    function select(hh) {
        $api.getAllFormula(null, function (res) {
            var data2 = res.data;
            if (data2.length > 0) {
                var html = '<option value="">--请选择--</option>';
                for (var i = 0; i < data2.length; i++) {
                    html += '<option value="' + data2[i].id + '" selected>' + data2[i].id + '</option>>';
                }
                $('#produceFormulaId1').append($(html));
                $('#produceFormulaId1').val(hh);
                form.render();
            }

        });

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

        var url = $tool.getContext() + '/productWaste/get';
        var req = {
            id: id
        };

        $api.getProductWasteById(req, function (res) {
            var data = res.data;
            console.log(data)

            $("[name='wasteNumber']").val(data.wasteNumber);
            select(data.produceFormulaId)
           // $("[name='produceFormulaId']").val(data.produceFormulaId);
            $("[name='wasteTime']").val(data.wasteTime);
            $("[name='wasteRemark']").val(data.wasteRemark);
           /* select2(data.state)*/
            $("[name='state']").val(data.state);
            //todo(选项的值)

            form.render();//重新绘制表单，让修改生效
        });


    }


    /**
     * 表单提交
     * */
    form.on("submit(editMenu)", function (data) {
        var queryArgs = $tool.getQueryParam();//获取查询参数

        var wasteNumber = data.field.wasteNumber;
        var wasteTime = data.field.wasteTime;
        var state = data.field.state;
        var wasteRemark = data.field.wasteRemark;
        var produceFormulaId = data.field.produceFormulaId;


        //请求
        var url = $tool.getContext() + '/productWaste/upd';
        var req = {
            id: queryArgs['id'],
            wasteNumber:wasteNumber,
            wasteTime:wasteTime,
            wasteRemark:wasteRemark,
            state: state,
            produceFormulaId:produceFormulaId

        };

        $api.updProductWaste(req, function (data) {
            layer.msg("修改成功！", {time: 1000}, function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });

        });
        return false;

    })


});


