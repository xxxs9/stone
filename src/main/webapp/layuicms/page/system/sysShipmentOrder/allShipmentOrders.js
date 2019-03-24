layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api:'api'
}).use(['form', 'layer', '$api','jquery', 'table', 'laypage','laytpl', 'ajaxExtention', '$tool'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery,
        laypage = layui.laypage,
        laytpl = layui.laytpl,
        $tool = layui.$tool,
        table = layui.table,
        $api = layui.$api;

    var tableIns;//表格实例

    /**
     * 页面初始化
     * */
    function init() {
    }
    init();


    /**
     * 定义表格
     * */
    function defineTable() {
        tableIns = table.render({
            elem: '#user-data'

            , url: $tool.getContext() + 'marker/list' //数据接口
            , method: 'post'
            , page:true //开启分页
            , cols: [[ //表头
                 /* {type:'numbers',title:'序号',fixed: 'left'}*/
                {field: 'id', title: 'ID' ,fixed:'left'}
                , {field: 'shipmentId', title: '发货单编号'}
                , {field: 'goodsName', title: '货品名称' }
                , {field: 'customer', title: '购买客户'}
                , {field: 'quantityNumber', title: '发货数量' }
                , {field: 'deliveryTime', title: '发货时间' }
                , {field: 'receivingAddress', title: '收货地址' }
                , {field: 'phone', title: '收货人电话' }
                , {field: 'consignee', title: '收货人' }
                , {field: 'receivingTime', title: '收货时间' }
                , {field: 'state', title: '收货状态' }
                , {field: 'remarks', title: '备注' }
                , {fixed: 'right', title: '操作', width: 280, align: 'center', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
            ]]
            , done: function (res, curr) {//请求完毕后的回调
                //如果是异步请求数据方式，res即为你接口返回的信息.curr：当前页码
            }
        });

        //为toolbar添加事件响应
        table.on('tool(userFilter)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var row = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            //区分事件
            if (layEvent === 'del') { //删除
                delMarkerOrder(row.id);
            } else if (layEvent === 'edit') { //编辑
                //do something
                editMarkerOrder(row.id);
            }else if (layEvent === 'audi'){//提交
                audi(row.id);
            }else if (layEvent === 'back') {//撤回
                back(row.id);
            }
        });
    }
    defineTable();


    //查询
    form.on("submit(queryUser)", function (data) {
        /*var status = data.field.status;*/
        var id=data.field.id;
        var shipmentId = data.field.shipmentId;
        var goodsName = data.field.goodsName;
        var customer = data.field.customer;
        var quantityNumber = data.field.quantityNumber;
        var deliveryTime = data.field.deliveryTime;
        var receivingAddress = data.field.receivingAddress;
        var phone = data.field.phone;
        var consignee = data.field.consignee;
        var receivingTime = data.field.receivingTime;
        var state = data.field.state;
        var remarks = data.field.remarks;
        //表格重新加载
        tableIns.reload({
            where:{
                /*status:status,*/
                id:id,
                shipmentId:shipmentId,
                goodsName:goodsName,
                customer:customer,
                quantityNumber:quantityNumber,
                deliveryTime:deliveryTime,
                receivingAddress:receivingAddress,
                phone:phone,
                consignee:consignee,
                receivingTime:receivingTime,
                state:state,
                remarks:remarks
            }
        });

        return false;
    });

    //添加用户
    $(".add_btn").click(function () {
        var index = layui.layer.open({
            title: "添加订单",
            type: 2,
            content: "addMarkerOrder.html",
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        });

        //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
        $(window).resize(function () {
            layui.layer.full(index);
        });
        layui.layer.full(index);
    });

    //删除
    function delMarkerOrder(id){
        layer.confirm('确认删除吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送删除指令
            var req = {
                id: id
            };

            $api.DeleteMarkerOrder(req,function (data) {
                layer.msg("删除成功",{time:1000},function(){
                    //obj.del(); //删除对应行（tr）的DOM结构
                    //重新加载表格
                    tableIns.reload();
                });
            });
        });
    }



    //提交

    function audi(id){
        layer.confirm('确认提交吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送提交指令
            var req = {
                id: id
            };

            $api.audiUpdate(req,function (data) {
                layer.msg("提交成功",{time:1000},function(){
                    //obj.del(); //提交对应行（tr）的DOM结构
                    //重新加载表格
                    tableIns.reload();
                });
            });
        });
        //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
        $(window).resize(function () {
            layui.layer.full(index);
        });
    }

    //撤回

    function back(id){
        layer.confirm('确认撤回吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送撤回指令
            var req = {
                id: id
            };

            $api.backUpdate(req,function (data) {
                layer.msg("撤回成功",{time:1000},function(){
                    //obj.del(); //撤回对应行（tr）的DOM结构
                    //重新加载表格
                    tableIns.reload();
                });
            });
        });
        //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
        $(window).resize(function () {
            layui.layer.full(index);
        });
        layui.layer.full(index);
    }





    //编辑
    function editMarkerOrder(id) {
        var index = layui.layer.open({
            title: "编辑订单",
            type: 2,
            content: "editMarkerOrder.html?id=" + id,
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回用户列表', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        });

        //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
        $(window).resize(function () {
            layui.layer.full(index);
        });
        layui.layer.full(index);


    }
});