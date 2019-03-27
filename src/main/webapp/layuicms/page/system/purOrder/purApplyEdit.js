layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention:'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool:'tool',
    $api:'api'
}).use(['form', 'layer','$api', 'jquery','ajaxExtention','$tool'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery,
        $tool = layui.$tool,
        $api = layui.$api;

    var depotIdList = new Array();//所有的角色id列表

    /**
     * 初始化页面
     * */
    function initTestarea(){
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var id = queryArgs['id'];
        var req = {
            id:id
        };

        $api.getPurOrder(req,function (res) {
            /*var data = res.data;
            if(data.state == "未提交"){
                $('#inspectInventory').remove();
                $('#inspectMon').remove();
            }*/
        });
    }

    initTestarea()
    function init() {
        //初始化商品名称下拉框
        initGoodsId();
        initDepotInfo();
    }
    init();

        /**
         * 初始化商品名称下拉框
         * */
        function initGoodsId() {
            $api.getListGoods(null,function (res) {
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
        }

        /**
         * 初始化菜单信息
         * */
        function initDepotInfo() {
            var queryArgs = $tool.getQueryParam();//获取查询参数
            var id = queryArgs['id'];
            var req = {
                id:id
            };

            $api.getPurOrder(req,function (res) {
                var data = res.data;
                console.log(data)
                $("[name='id']").val(data.id);
                $("[name='orderNumber']").val(data.orderNumber);
                $("[name='goodsId']").val(data.goodsId);
                $("[name='goodsNumber']").val(data.goodsNumber);
                $("[name='price']").val(data.price);
                $("[name='totalPrice']").val(data.totalPrice);
                $("[name='applyUser']").val(data.applyUser);
                $("[name='applyTime']").val(data.applyTime);
                $("[name='state']").val(data.state);
                $("[name='applyDescribe']").val(data.applyDescribe);
                //加载orderType，整型转字符串
                //orderTypeChange();
                //加载state状态的内容
                //stateChange();
                //加载角色列表
                loadRoleList();
                form.render();//重新绘制表单，让修改生效
            });
        }

    /**
     * 加载申请单列表
     * */
    function loadRoleList() {
        var url = $tool.getContext()+'purchase_order/list.do';
        var req =  {
            page:1,
            limit:10
        };

        $api.listPurOrder(req,function (res) {
            var data = res.data;
            if(data.length > 0){
                var depotHtml = "";
                for(var i = 0;i<data.length;i++){
                    //是否初始化选中
                    if($.inArray(data[i].id) != -1){
                        depotHtml += '<input type="checkbox" checked name="'+data[i].id+'" title="'+data[i].applyUser+'">';
                    }else{
                        depotHtml += '<input type="checkbox" name="'+data[i].id+'" title="'+data[i].applyUser+'">';
                    }

                    depotIdList.push(data[i].id);//保存id列表
                }

                $('.role-check-list').append($(depotHtml));
                form.render();//重新绘制表单，让修改生效
            }
        });
    }

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
    form.on("submit(submitFilter)", function (data) {
        var queryArgs =  $tool.getQueryParam();//获取查询参数
        var orderNumber = data.field.orderNumber;
        var goodsId = data.field.goodsId;
        var goodsNumber = data.field.goodsNumber;
        var price = data.field.price;
        var totalPrice = data.field.totalPrice;
        var applyUser = data.field.applyUser;
        var applyTime = data.field.applyTime;
        var state = data.field.state;
        var applyDescribe = data.field.applyDescribe;
        var idList = new Array();

        //获取选中的角色列表
        for(var i=0;i<depotIdList.length;i++){
            if(data.field[depotIdList[i]] === 'on'){
                idList.push(depotIdList[i]);
            }
        }

        //请求
        var url = $tool.getContext()+'purchase_order/update.do';
        var req = {
            id:queryArgs['id'],
            orderNumber:orderNumber,
            goodsId:goodsId,
            goodsNumber:goodsNumber,
            price:price,
            totalPrice:totalPrice,
            applyUser:applyUser,
            applyTime:applyTime,
            state:state,
            applyDescribe:applyDescribe,
            depotIdList:idList
        };

        $api.updatePurOrder(req,function (data) {
            layer.msg("修改成功！",{time:1000},function () {
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


