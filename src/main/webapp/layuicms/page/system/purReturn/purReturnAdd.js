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
        //初始化供应商名称下拉框
        //initGoodsId();
        initOrderNumber();
    }
    init();

    /*/!**
     * 初始化供应商名称下拉框
     * *!/
    function initGoodsId() {
        $api.selectGoodsIdPurchaseReturn(null,function (res) {
            var data = res.data;
            if (data.length > 0) {
                var html = '<option value="">--请选择--</option>';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i] + '">' + data[i] + '</option>>';
                }
                $('#goodsId').append($(html));
                form.render();
            }
        });
    }*/

    /**
     * 初始化订单编号下拉框
     * */
    function initOrderNumber() {
        $api.selectOrderNumberPurchaseReturn(null,function (res) {
            var data = res.data;
            if (data.length > 0) {
                var html = '<option value="">--请选择--</option>';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i] + '">' + data[i] + '</option>>';
                }
                $('#orderNumber').append($(html));
                form.render();
            }
        });
    }

    form.on('select(bhs)', function(data){
        console.log(data.elem); //得到checkbox原始DOM对象
        console.log(data.elem.checked); //是否被选中，true或者false
        console.log(data.value); //复选框value值，也可以通过data.elem.value得到
        console.log(data.othis); //得到美化后的DOM对象
        var req = {
            orderNumber:data.value
        };

        $api.selectOtherByOrderNumber(req,function (res) {
            var data = res.data;
            console.log(data);
            //$("[name='id']").val(data.id);
            //$("[name='orderNumber']").val(data.orderNumber);
            $("[name='goodsId']").val(data.goodsId);
            $("[name='goodsNumber']").val(data.goodsNumber);
            $("[name='price']").val(data.price);
            //$("[name='depotState']").val(data.depotState);
            //$("[name='applyUser']").val(data.applyUser);
            //$("[name='applyTime']").val(data.applyTime);
            form.render();//重新绘制表单，让修改生效
        });
    });

    /**
     * 表单提交
     * */
    form.on("submit(purAdd)", function (data) {
        var orderNumber = data.field.orderNumber;
        var goodsId = data.field.goodsId;
        var goodsNumber = data.field.goodsNumber;
        var price = data.field.price;
        var applyUser = data.field.applyUser;
        var applyTime = data.field.applyTime;
        var depotState = data.field.depotState;


        //请求
        var req = {
            orderNumber:orderNumber,
            goodsId:goodsId,
            goodsNumber: goodsNumber,
            price: price,
            applyUser: applyUser,
            applyTime:applyTime,
            depotState:depotState
        };

        $api.insertPurchaseReturn(req,function (data) {
            //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
            layer.msg("采购单添加成功！", {time: 1000}, function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });
        return false;
    })

    /*layui.use('laydate', function(){
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#time' //指定元素
            ,type: 'datetime'
        });
    });*/
});