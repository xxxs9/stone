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
            elem: '#produceDate' ,//指定元素
            type:'date',

            format:'yyyy-MM-dd'
        });
    });
    layui.use('laydate', function(){
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#billDate' ,//指定元素
            type:'date',

            format:'yyyy-MM-dd'
        });
    });
    layui.use('laydate', function(){
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#finishDate' ,//指定元素
            type:'date',

            format:'yyyy-MM-dd'
        });
    });
    /**
     * 页面初始化
     * */
    form.verify({
        pi: [
            /^[0-9]*[1-9][0-9]*$/  //正则表达式
            ,'数量必须为大于0的正整数'  //提示信息
        ]
    });
    function init() {
        //初始化下拉框
       initParentMenu();

    }


    init();
    function changeProductState(id) {
        var req ={
            id:id
        }
        $api.productReachDo(req,function (res) {
            //var data = res.data;
            console.log(data)
        })
    }
    /**
     * 初始化下拉框
     * */
    var id2;
    function initParentMenu() {
        var queryArgs = $tool.getQueryParam();
        var id= queryArgs['id'];
        id2=id;

        var req={
            id:id
        }
        $api.getProductById(req,function (res) {
            var data = res.data;
            $('[name="productName"]').val(data.productName)
            $('[name="productId"]').val(id)
            /*if (data.length > 0) {
                var html = '<option value="">---请选择---</option>';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].id + '">'+data[i].productName+'</option>';
                }
                $('#productId').append($(html));
                form.render();
            }*/
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
        var productId = data.field.productId;
        var planNumber = data.field.planNumber;
        var realNumber = data.field.realNumber;
        var state = data.field.state;
        var goodNumber = data.field.goodNumber;
        var produceDate = data.field.produceDate;
        var finishDate = data.field.finishDate;
        var billDate = data.field.billDate;
        var billCycle = data.field.billCycle;
        var planRemark = data.field.planRemark;


        //请求
        var req = {
            /*id:id,*/
            productId: productId,
            planNumber: planNumber,
            state:state,
            realNumber:realNumber,
            goodNumber:goodNumber,
            produceDate:produceDate,
            finishDate:finishDate,
            billDate:billDate,
            billCycle:billCycle,
            planRemark:planRemark,

        };

        $api.addProducePlan(req,function (data) {
            var data2 = data.data;
            console.log(data2)
            changeProductState(id2);
            console.log(changeProductState(id2))
            //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
            layer.msg("生产计划添加成功！", {time: 1000}, function () {
                layer.closeAll("iframe");
                //刷新父页面

                parent.location.reload();
            });
        });

        return false;
    })

});


