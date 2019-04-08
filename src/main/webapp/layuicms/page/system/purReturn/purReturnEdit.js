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

        $api.getPurchaseReturn(req,function (res) {

        });
    }
    initTestarea()
    function init() {
        //初始化商品名称下拉框
        initGoodsId();
        initDepotInfo();
        initOrderNumber();
    }
    init();

    /**
     * 初始化商品名称下拉框
     * */
    function initGoodsId() {
        $api.selectGoodsIdPurchaseReturn(null,function (res) {
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
     * 初始化订单编号下拉框
     * */
    function initOrderNumber(hh) {
        $api.selectOrderNumberPurchaseReturn(null,function (res) {
            var data = res.data;
            if (data.length > 0) {
                var html = '<option value="">--请选择--</option>';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i] + '">' + data[i] + '</option>>';
                }
                $('#orderNumber').append($(html));
                $('#orderNumber').val(hh)
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

        $api.getPurchaseReturn(req,function (res) {
            var data = res.data;
            console.log(data)
            $("[name='id']").val(data.id);
            $("[name='goodsId']").val(data.goodsId);
            $("[name='supplierName']").val(data.supplierName);
            $("[name='orderNumber']").val(data.orderNumber);
            initOrderNumber(data.orderNumber);
            $("[name='goodsName']").val(data.goodsName);
            $("[name='goodsNumber']").val(data.goodsNumber);
            $("[name='price']").val(data.price);
            $("[name='totalPrice']").val(data.totalPrice);
            $("[name='depotState']").val(data.depotState);
            $("[name='applyUser']").val(data.applyUser);
            $("[name='applyTime']").val(data.applyTime);
            loadRoleList();
            form.render();//重新绘制表单，让修改生效
        });
    }

    /**
     * 加载申请单列表
     * */
    function loadRoleList() {
        var req =  {
            page:1,
            limit:10
        };

        $api.listPurchaseReturn(req,function (res) {
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

    //对数量、价格进行判断，不能为零或负数
    form.verify({
        //数量goodsNumber
        actualBalance:function (value) {
            //对数量进行判断，只能整数
            var number =/^[1-9]\d*$/;
            var btn = document.getElementById("number").value;
            if(!number.test(btn)){
                return ("输入有误，请重新输入!")
            }

            //对数量进行判断，只允许输入正数。
           /* if(value==0){
                return '请输入数量!';
            } else if (value<0){
                return '数量不能为负数!';
            }*/
        },

        //价格price
        actualPrice:function (value) {
            //对单价进行判断，只能有数字并且保留两位小数
            //reg是判断只能输入数字，不能输入中文、英文或其他符号
            //reg2是判断小数点只能两位
            var reg =/^[0-9]+([.]{1}[0-9]+){0,1}$/;
            var reg2 = /^\d{0,8}\.{0,1}(\d{1,2})?$/;
            var btn = document.getElementById("price").value;
            if(!reg.test(btn)){
                return ("请输入数字!")
            } else if(!reg2.test(btn)){
                return ("小数只能两位!")
            }

            //对输入的数字进行判断，只允许输入正数。
            if(value==0){
                return '请输入价格!';
            } else if (value<0){
                return '价格不能为负数!';
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
    form.on("submit(submitFilter)", function (data) {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var orderNumber = data.field.orderNumber;
        var goodsId = data.field.goodsId;
        var supplierName = data.field.supplierName;
        var goodsName = data.field.goodsName;
        var goodsNumber = data.field.goodsNumber;
        var price = data.field.price;
        var totalPrice = data.field.totalPrice;
        var depotState = data.field.depotState;
        var applyUser = data.field.applyUser;
        var applyTime = data.field.applyTime;
        var idList = new Array();
        console.log(data.field);
        //获取选中的角色列表
        for(var i=0;i<depotIdList.length;i++){
            if(data.field[depotIdList[i]] === 'on'){
                idList.push(depotIdList[i]);
            }
        }

        //请求
        var req = {
            id:queryArgs['id'],
            orderNumber:orderNumber,
            goodsId:goodsId,
            supplierName:supplierName,
            goodsName:goodsName,
            goodsNumber:goodsNumber,
            price:price,
            totalPrice:totalPrice,
            depotState:depotState,
            applyUser:applyUser,
            applyTime:applyTime,
            depotIdList:idList
        };

        $api.updatePurchaseReturn(req,function (data) {
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


