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
        $api.selectGoodsProduct(null, function (res) {
            var data = res.data;
            if (data.length > 0) {
                var html = '';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].leixing + '"></option>';
                }
                $('#leixing').append($(html));
                $('#leixing').val(hh);
                form.render();
            }

        });

    }






    function select2(hh) {
        $api.selectGoodsProduct(null, function (res) {
            var data3 = res.data;
            if (data3.length > 0) {
                var html = '';
                for (var i = 0; i < data3.length; i++) {
                    html += '<option value="' + data3[i].changdi + '"></option>';
                }

                $('#changdi').append($(html));
                $('#changdi').val(hh);
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
            var data7 = res.data;
            $("[name='pname']").val(data7.pname);
            $("[name='xinghao']").val(data7.xinghao);
            $("[name='style1']").val(data7.style1);
            $("[name='picihao']").val(data7.picihao);
            $("[name='bianhao']").val(data7.xinghao);
            $("[name='caizhi']").val(data7.caizhi);
            $("[name='changdi']").val(data7.changdi);
            $("[name='yanse']").val(data7.yanse);
            $("[name='chichun']").val(data7.chichun);
            $("[name='pprice']").val(data7.pprice);
            $("[name='zhuangtai']").val(data7.zhuangtai);
            $("[name='bianhao']").val(data7.bianhao);
            select(data7.leixing);
            select2(data7.changdi);

            //todo(产地)
            console.log(data);

            form.render();//重新绘制表单，让修改生效
        });


    }


    /**
     * 表单提交
     * */
    form.on("submit(editMenu)", function (data) {
        var queryArgs = $tool.getQueryParam();//获取查询参数

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
        var zhuangtai = data.field.zhuangtai;
        var bianhao = data.field.bianhao;
        var other1 = data.field.other1;
        var other2 = data.field.other2;
        var other3 = data.field.other3;



        //请求
        var url = $tool.getContext() + '/goodsProduct/upd';
        var req = {
            id: queryArgs['id'],
            pname: pname,
            style1: style1,
            picihao:picihao,
            caizhi:caizhi,
            xinghao:xinghao,
            changdi:changdi,
            yanse:yanse,
            leixing:leixing,
            chichun:chichun,
            pprice:pprice,
            zhuangtai:zhuangtai,
            bianhao:bianhao,
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


