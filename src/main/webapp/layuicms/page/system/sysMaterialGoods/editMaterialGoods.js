layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention:'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool:'tool',
    $api:'api'
}).use(['form', 'layer', 'jquery','ajaxExtention','$tool','$api'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery,
        $tool = layui.$tool,
        $api = layui.$api;


    /**
     * 初始化页面
     * */
    function init() {
        //初始化信息
        initMaterialGoodsInfo();
    }
    init();


    /**
     * 初始化菜单信息
     * */
    function initMaterialGoodsInfo() {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var id = queryArgs['id'];
        var url = $tool.getContext()+'materialGoods/get.do';
        var req = {
            id:id
        };

        $api.GetMaterialGoods(req,function (res) {
            var data = res.data;
            $('#supplierName').val(data.supplierName);
            $('#goodsName').val(data.goodsName);
            $('#goodsType').val(data.goodsType);
            $('#goodsDescribe').val(data.goodsDescribe);
            $('#goodsSpecification').val(data.goodsSpecification);
            $('#goodsPrice').val(data.goodsPrice);
            $('#goodsOriginPlace').val(data.goodsOriginPlace);
            $('#demo1').attr('src', data.imageUrl); //图片链接（base64）
            form.render();//重新绘制表单，让修改生效
        });
    }

    /**
     * 表单提交
     * */
    form.on("submit(editMaterialGoods)", function (data) {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var supplierName = data.field.supplierName;
        var goodsName = data.field.goodsName;
        var goodsPrice = data.field.goodsPrice;
        var goodsOriginPlace = data.field.goodsOriginPlace;

        //请求
        var url = $tool.getContext()+'materialGoods/update.do';
        var req = {
            id:queryArgs['id'],
            supplierName:supplierName,
            goodsName: goodsName,
            goodsPrice: goodsPrice,
            goodsOriginPlace:goodsOriginPlace,
        };

        $api.UpdateMaterialGoods(req,function (data) {
            layer.msg("修改成功！",{time:1000},function () {
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
            ,done: function(res){
                //如果上传失败
                var res = res.data;
                if(res.code == 1){
                    return layer.msg('上传成功');
                }
                //上传成功
                return layer.msg('上传失败');
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


