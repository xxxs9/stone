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
        //初始化商品名称下拉框
        initGoodsId();
    }
    init();

    /**
     * 初始化商品名称下拉框
     * */
    function initGoodsId() {
        $api.getListGoods(null,function (res) {
            var data = res.data;
            console.log(data);
            if (data.length > 0) {
                var html = '<option value="">--请选择--</option>';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].id + '">' + data[i].goodsName + '</option>>';
                }
                $('#goodsName').append($(html));
                form.render();
            }
        });
    }

    form.on('select(bhs)', function(data){
        console.log(data.elem); //得到checkbox原始DOM对象
        console.log(data.elem.checked); //是否被选中，true或者false
        console.log(data.value); //复选框value值，也可以通过data.elem.value得到
        console.log(data.othis); //得到美化后的DOM对象
        var materialId = $("#goodsName").find("option:selected").text();
        var req = {
            materialId:materialId
        };

        $api.selectPriceByGoodsId(req,function (res) {
            var data = res.data;
            console.log(res);
            $("[name='price']").val(data[0].goodsPrice);
            $("[name='supplierName']").val(data[0].supplierName);
            $("[name='goodsId']").val(data[0].id);
            form.render();//重新绘制表单，让修改生效
        });
    });

    //对数量进行判断，不能为零或负数
    form.verify({
       actualBalance:function (value) {
           if(value<0){
               return '数量不能为负数!';
           } else if (value==0){
               return '请输入数量!';
           }
       }
    });

    //计算总金额 数量goodsNumber*价格price
    $(function(){
        //总结个totalPrice
        $('[name=totalPrice]').bind('click',function(){
            var price = $('[name=price]').val();
            var goodsNumber = $('[name=goodsNumber]').val();
            $("[name = totalPrice]").val(price * goodsNumber);
        })
    });

    /**
     * 表单提交
     * */
    form.on("submit(purAdd)", function (data) {
        //var id = data.field.id;
        var orderNumber = data.field.orderNumber;
        var goodsId = $("#goodsId").val();
        var goodsName = $("#goodsName").find("option:selected").text();
        var supplierName = data.field.supplierName;
        var goodsNumber = data.field.goodsNumber;
        var price = data.field.price;
        var totalPrice = data.field.totalPrice;
        var applyUser = data.field.applyUser;
        var applyTime = data.field.applyTime;
        var state = data.field.state;
        var applyDescribe = data.field.applyDescribe;
        console.log(data);

        //console.log(data)
        /*var parentMenuId = data.field.parentMenuId;
        var requestUrl = data.field.requestUrl;
        var sort = data.field.sort;*/

        //请求
        var req = {
            //id:id,
            orderNumber:orderNumber,
            goodsId:goodsId,
            goodsNumber: goodsNumber,
            price: price,
            totalPrice:totalPrice,
            applyUser: applyUser,
            applyTime: applyTime,
            state:state,
            applyDescribe:applyDescribe
        };

        $api.insertPurOrder(req,function (data) {
            //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
            layer.msg("采购单添加成功！", {time: 1000}, function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });
        return false;
    })
});