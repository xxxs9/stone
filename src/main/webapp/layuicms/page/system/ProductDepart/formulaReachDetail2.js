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
        initMenuInfo();
       // productName();

    }

    init();
    /**
     * 挑选成品配方
     */
    function formulaId(hh) {
        $api.getFormulaReach(null, function (res) {
            var data = res.data;
            var arr = new Array();

            if (data.length > 0) {

                var html = '';
                for (var i = 0; i < data.length; i++) {
                    if(arr.indexOf(data[i].produceFormulaId) == -1){
                        html += '<option value="' + data[i].produceFormulaId + '">' + data[i].produceFormulaId + '</option>';
                    }
                    arr[i]=data[i].produceFormulaId;
                }
            }
            $('#produceFormulaId').append($(html));
            $('#produceFormulaId').val(hh);
            form.render();

        });

    }
    function formulaId2(hh) {
        $api.getFormulaReach(null, function (res) {
            var arr = new Array();
            var data = res.data;
            if (data.length > 0) {
                var html = '';
                for (var i = 0; i < data.length; i++) {
                    if (arr.indexOf(data[i].produceFormulaDetailId) == -1) {
                        html += '<option value="' + data[i].produceFormulaDetailId + '">' + data[i].produceFormulaDetailId + '</option>';
                    }
                    arr[i]=data[i].produceFormulaDetailId;
                }

                $('#produceFormulaDetailId').append($(html));
                $('#produceFormulaDetailId').val(hh);
                form.render();
            }

        });

    }

    function depotAudi(hh) {
        $api.getFormulaReach(null, function (res) {
            var data = res.data;
            if (data.length > 0) {
                var html = '';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].depotAudi + '">' + data[i].depotAudi + '</option>';
                }
                $('#depotAudi').append($(html));
                $('#depotAudi').val(hh);
                form.render();
            }

        });

    }
    function productId(hh) {
        $api.getFormulaReach(null, function (res) {
            var data = res.data;
            if (data.length > 0) {
                var html = '';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].productId + '">' + data[i].productId + '</option>';
                }
                $('#productId').append($(html));
                $('#productId').val(hh);
                form.render();
            }

        });

    }
    function depotAudi(hh) {
        $api.getFormulaReach(null, function (res) {
            var arr= new  Array();
            var data = res.data;
            if (data.length > 0) {
                var html = '';
                for (var i = 0; i < data.length; i++) {
                    if (arr.indexOf(data[i].depotAudi) == -1) {
                        html += '<option value="' + data[i].depotAudi + '"></option>';
                    }
                    arr[i]=data[i].depotAudi;
                }
                $('#depotAudi').append($(html));
                $('#depotAudi').val(hh);
                form.render();
            }

        });

    }
    function state(hh) {
        $api.getFormulaReach(null, function (res) {
            var data = res.data;
            if (data.length > 0) {
                var html = '';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].state + '"></option>';
                }
                $('#state').append($(html));
                $('#state').val(hh);
                form.render();
            }

        });

    }
    function fb(hh) {
        $api.getFormulaReach(null, function (res) {
            var data = res.data;
            var arr =new Array();
            if (data.length > 0) {
                var html = '';
                for (var i = 0; i < data.length; i++) {
                    if (arr.indexOf(data[i].formulaBack) == -1) {
                        html += '<option value="' + data[i].formulaBack + '">' + data[i].formulaBack + '</option>';
                    }
                    arr[i]=data[i].formulaBack;
                }
                $('#formulaBack').append($(html));
                $('#formulaBack').val(hh);
                form.render();
            }

        });

    }

    /**
     * 表单提交
     * */
        var id3;
    function initMenuInfo() {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var id = queryArgs['id'];

        var url = $tool.getContext() + 'reach/get';
        var req = {
            id: id
        };
        var id2 ;
        id3=id;
        $api.getFormulaReachById(req, function (res) {
            var data = res.data;
            productId(data.productId);
            id2=data.productId;

            /*$("[name='productId']").val(data.productId);*/
            /*$("[name='productName']").val(data.productName);*/
            depotAudi(data.depotAudi);
            /*$("[name='depotAudi']").val(data.depotAudi);*/
            state(data.state);
            /*$("[name='state']").val(data.state);*/
            $("[name='reachUser']").val(data.reachUser);
            $("[name='reachTime']").val(data.reachTime);
            formulaId(data.produceFormulaId);
            formulaId2(data.produceFormulaDetailId);
            fb(data.formulaBack);

            function productName(id2) {
                var req2 = {
                    id: id2
                }

                $api.getProductById(req2, function (res) {

                    var data2 = res.data;

                    $("[name='productName']").val(data2.productName);
                    /*$('#productName').val(hh);*/

                });
            }
            productName(id2);
            form.render();//重新绘制表单，让修改生效
        });



    }
/***********************************************************/

    $('#completeProduce').click(function () {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var id = queryArgs['id'];

            var req= {
                id:id
            }
          
        $api.completeProduce(req,function (data) {
            layer.msg("产品已生产完成！", {time: 1000}, function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });
    })
    $('#produceReport').click(function () {


        var index = layui.layer.open({
            title: "生产报告",
            type: 2,
            content: "produceReport.html?id=" + id,
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        });
        $(window).resize(function () {
            layui.layer.full(index);
        });
        layui.layer.full(index);
        });



});


