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

            , url: $tool.getContext() + 'shipment/list' //数据接口
            , method: 'post'
            , page:true //开启分页
            , cols: [[ //表头
                /* {type:'numbers',title:'序号',fixed: 'left'}*/
                {field: 'id', title: 'ID' ,fixed:'left',width:140}
                , {field: 'goodsId', title: '发货单号',width:140}
                , {field: 'productId', title: '货品名称',width:140}

                , {field: 'customer', title: '购买客户' ,width:140}
                , {field: 'goodsNumber', title: '发货数量',width:140}
                , {field: 'goodsAmount', title: '货品金额' ,width:140}
                , {field: 'auditType', title: '收货地址' ,width:140}
                , {field: 'goodsName', title: '收货电话' ,width:140}
                , {field: 'applyUser', title: '申请人' ,width:140}
                , {field: 'applyTime', title: '申请时间' ,width:140}
                , {field: 'state', title: '订单状态 ',width:140}
                , {field: 'auditUser', title: '审核人' ,width:140}
                , {field: 'remarks', title: '备注' ,width:140}
                , {fixed: 'right', title: '操作', width: 280, align: 'center', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
            ]]
            , done: function (res, curr) {//请求完毕后的回调
                //如果是异步请求数据方式，res即为你接口返回的信息.curr：当前页码
                $("[data-field='id']").css('display','none');

                $("[data-field='auditUser']").css('display','none');
                $("[data-field='applyUser']").css('display','none');
            }
        });

        //为toolbar添加事件响应
        table.on('tool(userFilter)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var row = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            //区分事件
            if (layEvent === 'del') { //删除
                delReturnGoodsOrder(row.id);
            } else if (layEvent === 'edit') { //编辑
                //do something
                editMarkerOrder(row.id);
            }else if (layEvent === 'audit'){//提交
                audit(row.id,row.goodsAmount);
            }else if (layEvent === 'depot') {//提交仓库
                depot(row.id);
            }else if (layEvent === 'look') {//撤回
                look(row.id);
            }else if (layEvent === 'finance') {//撤回
                finance(row.id,row.goodsAmount);
            }else if (layEvent === 'return') {//撤回
                return(row.id);
            }
        });
    }
    defineTable();


    //查询
    form.on("submit(queryUser)", function (data) {
        /*var status = data.field.status;*/
        var id=data.field.id;
        var goodsId = data.field.goodsId;
        var productId = data.field.productId;
        var goodsName = data.field.goodsName;
        var customer = data.field.customer;
        var goodsNumber = data.field.goodsNumber;
        var goodsAmount = data.field.goodsAmount;
        var applyUser = data.field.applyUser;
        var applyTime = data.field.applyTime;
        var state = data.field.state;
        var auditUser = data.field.auditUser;
        var auditType = data.field.auditType;
        var remarks = data.field.remarks;
        //表格重新加载
        tableIns.reload({
            where:{
                /*status:status,*/
                id:id,
                goodsId:goodsId,
                productId:productId,
                goodsName:goodsName,
                customer:customer,
                goodsNumber:goodsNumber,
                goodsAmount:goodsAmount,
                applyUser:applyUser,
                applyTime:applyTime,
                state :state ,
                auditUser:auditUser,
                auditType:auditType,

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
            content: "addReturnGoodsOrder.html",
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
    function delReturnGoodsOrder(id){
        layer.confirm('确认删除吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送删除指令
            var req = {
                id: id
            };

            $api.DeleteReturnOrder(req,function (data) {
                layer.msg("删除成功",{time:1000},function(){
                    //obj.del(); //删除对应行（tr）的DOM结构
                    //重新加载表格
                    tableIns.reload();
                });
            });
        });
    }



    //提交主管审核

    function audit(id,goodsAmount){
        layer.confirm('提交主管审核吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送提交指令
            var req = {
                id: id,
                goodsAmount:goodsAmount,
            };

            $api.updateAudit(req,function (data) {
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

    //提交仓库

    function depot(id){
        layer.confirm('提交仓库审核吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送撤回指令
            var req = {
                id: id
            };

            $api.updateDepot(req,function (data) {
                layer.msg("提交成功",{time:1000},function(){
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

    //提交财务

    function finance(id,goodsAmount){
        layer.confirm('提交财务确认吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送撤回指令
            var req = {
                id: id,
                goodsAmount:goodsAmount
            };
            $api.updateFinance(req,function (data) {
                layer.msg("提交成功",{time:1000},function(){
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
            content: "editReturnGoodsOrder.html?id=" + id,
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
            title: "查看发货单",
            type: 2,
            content: "lookReturnGoodsOrder.html?id=" + id,
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