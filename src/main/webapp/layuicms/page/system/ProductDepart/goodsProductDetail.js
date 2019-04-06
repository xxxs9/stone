layui.config({
    base: $config.resUrl + 'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api: 'api'
}).use(['form', 'layer', 'jquery', 'ajaxExtention', '$tool', '$api'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery,
        $tool = layui.$tool,
        $api = layui.$api;

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

    function select(hh) {
        $api.getAllProduct(null, function (res) {
            var data = res.data;
            if (data.length > 0) {
                var html = '';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].productState + '"></option>';
                }
                $('#productState').append($(html));
                $('#productState').val(hh);
                form.render();
            }

        });

    }






    function select2(hh) {
        $api.getAllProduct(null, function (res) {
            var data3 = res.data;
            if (data3.length > 0) {
                var html = '';
                for (var i = 0; i < data3.length; i++) {
                    html += '<option value="' + data3[i].productType + '"></option>';
                }

                $('#productType').append($(html));
                $('#productType').val(hh);
                form.render();
            }

        });

    }

    function initMenuInfo() {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var wid = queryArgs['id'];

        var url = $tool.getContext() + '/goodsProduct/get';
        var req = {
            id: wid
        };

        $api.getGoodsProduct(req, function (res) {
            var data = res.data;


            $("[name='pname']").val(data.pname);
            $("[name='picihao']").val(data.picihao);
            $("[name='bianhao']").val(data.bianhao);
            $("[name='bianhao']").val(data.xinghao);
            $("[name='caizhi']").val(data.caizhi);
            $("[name='changdi']").val(data.changdi);
            $("[name='yanse']").val(data.yanse);
            $("[name='chichun']").val(data.chichun);
            $("[name='zhuangtai']").val(data.zhuangtai);
            $("[name='pprice']").val(data.pprice);
            //todo(状态)
            //todo(产地)
            select2(data.productType);
            select(data.state);


            form.render();//重新绘制表单，让修改生效
        });


    }


    /**
     * 表单提交
     * */
    form.on("submit(editMenu)", function (data) {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        console.log(queryArgs)
        var pname = data.field.pname;
        var style1 = data.field.style1;
        var picihao = data.field.picihao;
        var caizhi = data.field.caizhi;
        var xinghao = data.field.xinghao;
        var changdi = data.field.changdi;
        var yanse = data.field.yanse;
        var leixing = data.field.leixing;
        var chichun = data.field.chichun;
        var pprice = data.field.pprice;
        var other1 = data.field.other1;
        var other2 = data.field.other2;
        var other3 = data.field.other3;



        //请求
        var url = $tool.getContext() + '/goodsProduct/upd';
        var req = {
            id: queryArgs['id'],
            pname: pname,
            style1: style1,
            state:state,
            picihao:picihao,
            caizhi:caizhi,
            xinghao:xinghao,
            changdi:changdi,
            yanse:yanse,
            leixing:leixing,
            chichun:chichun,
            pprice:pprice,
            other1:other1,
            other2:other2,
            other3:other3,


        };

        $api.updGoodsProduct(req, function (data) {
            layer.msg("修改成功！", {time: 1000}, function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });

        });
        return false;

    })


});


