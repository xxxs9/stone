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
            elem: '#checkTime' ,//指定元素
            type:'date',
            format:'yyyy-MM-dd'
        });
    });
    /**
     * 页面初始化
     * */
    function init() {
        //初始化下拉框
      /*  initParentMenu();*/
    }

    init();
    init1();

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
    function init1() {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var id = queryArgs['id'];
        var req = {
            id: id
        }

       $("[name='formulaReachId']").val(id);
        $api.getAllProductWaste(null,function (res) {
            var data = res.data;
            if(data.length > 0){
                var html = '<option value="">--请选择--</option>';
                for(var i=0;i<data.length;i++){
                    html += '<option value="'+data[i].id+'">'+data[i].id+'</option>>';
                }
                $('#wasteId').append($(html));
                form.render();
            }
        });

    }
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
        var formulaReachId = data.field.formulaReachId;
        var producePlanId = data.field.producePlanId;
        var state = data.field.state;
        var checkUser = data.field.checkUser;
        var checkTime = data.field.checkTime;
        var wasteId = data.field.wasteId;


        var checkRemark = data.field.checkRemark;
        var other1 = data.field.other1;
        var other2 = data.field.other2;
        var other3 = data.field.other3;

        //请求
        var req = {
            /*id:id,*/
            formulaReachId:formulaReachId,
            producePlanId:producePlanId,
            state:state,
            checkUser:checkUser,
            checkTime:checkTime,
            wasteId:wasteId,
            checkRemark:checkRemark,
            other1:other1,
            other2:other2,
            other3:other3


        };

        $api.addCheck(req,function (data) {
            //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
            layer.msg("验收单添加成功！", {time: 1000}, function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });

        return false;
    })

});


