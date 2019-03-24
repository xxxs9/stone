
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
    /**
     * 页面初始化
     * */
    function init() {
        //初始化下拉框
        initSupplierName();
    }

    init();

    /**
     * 初始化下拉框
     * */
    function initSupplierName() {
        $api.GetSupplierName(null,function (res) {
            var data = res.data;
            if (data.length > 0) {
                var html = '<option value="">--请选择--</option>';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i] + '">' + data[i] + '</option>>';
                }
                $('#supplierName').append($(html));
                form.render();
            }
        });
        $api.GetGoodsName(null,function (res) {
            var data = res.data;
            if (data.length > 0) {
                var html = '<option value="">--请选择--</option>';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i] + '">' + data[i] + '</option>>';
                }
                $('#goodsName').append($(html));
                form.render();
            }
        });
    }

    /**
     * 初始化货物信息
     * */
    function initGoods() {

        var goodsName =  $("#goodsName option:selected").val();
        var req = {
            goodsName:goodsName
        };
        $api.GetMaterialByGoodsName(req,function (res) {
            var data = res.data;
            $('#goodsType').val(data.goodsType);
            $('#goodsDescribe').val(data.goodsDescribe);
            $('#goodsSpecification').val(data.goodsSpecification);
            form.render();
        });
    }

    /**
     * 监听option：select下拉框的选择
     * */

    form.on('select(goodsNameFilter)', function (data) {
        //console.log(data.elem); //得到select原始DOM对象
        var value = data.value;
        if (value==='') {
            $('.goods-type').addClass('layui-hide');
            $('.goods-describe').addClass('layui-hide');
            $('.goods-specification').addClass('layui-hide');
            $('.goods-type').removeClass('layui-anim-up');
            $('.goods-describe').removeClass('layui-anim-up');
            $('.goods-specification').removeClass('layui-anim-up');
        }else{
            initGoods();
            $('.goods-specification').val('bbbbbb');
            $('.goods-type').removeClass('layui-hide');
            $('.goods-describe').removeClass('layui-hide');
            $('.goods-specification').removeClass('layui-hide');
            $('.goods-type').addClass('layui-anim-up');
            $('.goods-describe').addClass('layui-anim-up');
            $('.goods-specification').addClass('layui-anim-up');
        }
    });

    /**
     * 表单提交
     * */
    form.on("submit(addMaterialGoods)", function (data) {

        var supplierName = $('#supplierName option:selected').val()
        var goodsName = $('#goodsName option:selected').val()
        var goodsType = data.field.goodsType;
        var goodsDescribe = data.field.goodsDescribe;
        var goodsSpecification = data.field.goodsSpecification;
        var goodsPrice = data.field.goodsPrice;
        var goodsOriginPlace = data.field.goodsOriginPlace;
        //请求
        var req = {
            supplierName:supplierName,
            goodsName:goodsName,
            goodsType: goodsType,
            goodsDescribe: goodsDescribe,
            goodsSpecification:goodsSpecification,
            goodsPrice:goodsPrice,
            goodsOriginPlace:goodsOriginPlace,
        };

        $api.AddMaterialGoods(req,function (data) {
            //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
            layer.msg("供应关系添加成功！", {time: 1000}, function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });
        return false;
    })

    layui.use('upload', function(){
        var upload = layui.upload;
        var uploadInst = upload.render({
            elem: '#test1'
            ,url: '../../../../materialGoods/uploadHeadImage.do'
            ,before: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                    $('#demo1').attr('src', result); //图片链接（base64）
                    $('#demo1').attr('style','width:120px;height:120px');
                });
            }
            ,done: function(data){
                var res = data.data;
                //如果上传失败
                if(res.code > 0){
                    return layer.msg('上传失败');
                }
                //上传成功
                return layer.msg('上传成功');
            }
            ,error: function(){
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function(){
                    uploadInst.upload();
                });
            }
        });

    });


});


