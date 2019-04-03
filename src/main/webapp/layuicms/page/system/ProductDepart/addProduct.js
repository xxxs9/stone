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
      /*  initParentMenu();*/

    }

    init();

    /**
     * 初始化下拉框
     * */
   /* function initParentMenu() {
        $api.GetFirstClassMenus(null,function (res) {
            var data = res.data;
            if (data.length > 0) {
                var html = '<option value="">--请选择--</option>';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].id + '">' + data[i].title + '</option>>';
                }
                $('#parentMenu').append($(html));
                form.render();
            }
        });
    }
*/
    $('#canSold').val(window.sessionStorage.getItem('sysUser'));
    /**
     * 监听radio选择
     * */
    /*form.on('radio(menuTypeFilter)', function (data) {
        //console.log(data.elem); //得到radio原始DOM对象
        var value = data.value;
        if ('2' === value) {//二级菜单
            $('.parent-menu').removeClass('layui-hide');
            $('.parent-menu').addClass('layui-anim-up');
        }else{
            $('.parent-menu').addClass('layui-hide');
            $('.parent-menu').removeClass('layui-anim-up');
        }
    });
*/
    /**
     * 表单提交
     * */
    form.on("submit(addMenu)", function (data) {
        /*var id = data.field.id;*/
        var productName = data.field.productName;
        var productType = data.field.productType;
        var state = data.field.state;
        var supportPrice = data.field.supportPrice;
        var canSold = data.field.canSold;
        var productNumber = data.field.productNumber;


        var productDescribe = data.field.productDescribe;
        var other1 = data.field.other1;
        var other2 = data.field.other2;
        var other3 = data.field.other3;

        //请求
        var req = {
            /*id:id,*/
            productName: productName,
            productType: productType,
            state:state,
            supportPrice:supportPrice,
            canSold:canSold,
            productNumber:productNumber,
            productDescribe:productDescribe,
            other1:other1,
            other2:other2,
            other3:other3,

        };

        $api.AddProduct(req,function (data) {
            //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
            layer.msg("工单添加成功！", {time: 1000}, function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });

        return false;
    })

});


