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

        //初始化下拉框
      /*  $api. GetFirstProductFormula(null,function (res) {
            var data = res.data;
            if(data.length > 0){
                var html = '<option value="">--请选择--</option>';
                for(var i=0;i<data.length;i++){
                    html += '<option value="'+data[i].id+'">'+data[i].title+'</option>>';
                }
                $('#parentMenu').append($(html));
                form.render();
            }
        });*/


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
                , {field: 'orderId', title: '订单编号' }
                , {field: 'orderTime', title: '订单日期' }
                , {field: 'productId', title: '产品名' }
                , {field: 'customer', title: '购买客户'}
                , {field: 'deliverNumber', title: '销售数量' }

                , {field: 'plannedNumber', title: '产品单价' }
                , {field: 'acceptedAmount', title: '总金额' }

                , {field: 'applyUser', title: '申请人' }
                , {field: 'state', title: '订单状态' }
                , {field: 'orderAuditUser', title: '订单审核人' }
                , {field: 'orderAuditDepot', title: '仓库审核人' }
                , {field: 'remarks', title: '备注' }
                , {field: 'depotRemarks', title: '仓库审核人备注' }
                , {fixed: 'right', title: '操作', align: 'center', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
            ]]
            , done: function (res, curr) {//请求完毕后的回调
                //如果是异步请求数据方式，res即为你接口返回的信息.curr：当前页码
                $("[data-field='id']").css('display','none');
                $("[data-field='orderAuditUser']").css('display','none');
                $("[data-field='applyUser']").css('display','none');
                $("[data-field='orderAuditDepot']").css('display','none');
                $("[data-field='remarks']").css('display','none');
                $("[data-field='depotRemarks']").css('display','none');
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
            }else if (layEvent === 'look') {//撤回
                look(row.id);
            }else if (layEvent === 'submit') {//提交仓库审核
                submit(row.id);
            }else if (layEvent === 'fina') {//提交仓库审核
                fina(row.id);
            }
        });
    }
    defineTable();


    /**
     * 改颜色
     */

   /* table.render({
        cols: [[
            {field:'state', title: '审核失败'
                ,templet: function(d){
                    return 'STATE：'+ d.state +'，审核失败：<span style="color: #c00;">'+ d.state +'</span>'
                }
            }
            ,{field:'state', title:'STATE'}
        ]]
    });*/



    //查询
    form.on("submit(queryUser)", function (data) {
        /*var status = data.field.status;*/
        var id=data.field.id;
        var orderId = data.field.orderId;
        var orderTime = data.field.orderTime;
        var productId = data.field.productId;
        var customer = data.field.customer;
        var deliverNumber = data.field.deliverNumber;
        var plannedNumber = data.field.plannedNumber;
        var acceptedAmount = data.field.acceptedAmount;
        var applyUser = data.field.applyUser;
        var state = data.field.state;
        var orderAuditUser = data.field.orderAuditUser;
        var orderAuditDepot = data.field.orderAuditDepot;
        var remarks = data.field.remarks;
        var depotRemarks = data.field.depotRemarks;
        //表格重新加载
        tableIns.reload({
            where:{
                /*status:status,*/
               id:id,
                orderId:orderId,
                orderTime:orderTime,
                productId:productId,
                customer:customer,
                deliverNumber:deliverNumber,
                plannedNumber:plannedNumber,
                acceptedAmount:acceptedAmount,
                applyUser:applyUser,
                state:state,
                orderAuditUser:orderAuditUser,
                orderAuditDepot:orderAuditDepot,
                remarks:remarks,
                depotRemarks:depotRemarks
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


    //提交财务

    function fina(id){
        layer.confirm('确认提交吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送提交指令
            var req = {
                id: id
            };

            $api.updateFina(req,function (data) {
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


    //提交仓库审核

    function submit(id){
        layer.confirm('提交仓库审核吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送提交指令
            var req = {
                id: id
            };

            $api.updateSubmit(req,function (data) {
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



    //查看
    function look(id) {
        var index = layui.layer.open({
            title: "查看订单",
            type: 2,
            content: "lookMarkerOrder.html?id=" + id,
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




    layui.use('laydate', function(){
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#test1' //指定元素
            ,type: 'datetime'
        });
    });

});