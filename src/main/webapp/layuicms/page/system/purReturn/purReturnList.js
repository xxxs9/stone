layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api:'api'
}).use(['form', 'layer', 'laydate', 'jquery', 'table', 'laypage', 'ajaxExtention', '$tool','$api'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery,
        laypage = layui.laypage,
        $tool = layui.$tool,
        laydate = layui.laydate,
        table = layui.table,
        $api = layui.$api;

    var tableIns; //表格实例

    /**
     * 页面初始化
     * */
    function init() {
        initGoods();  //初始化货品名称
        initState();  //初始化订单状态
    }

    init();

    /**
     * 初始化状态查询
     */
    function initState(){
        var html1 = '<option value="">--请选择--</option>';
        html1 += '<option value="未提交">未提交</option>>';
        html1 += '<option value="提交审核中">提交审核中</option>>';
        html1 += '<option value="审核通过">审核通过</option>>';
        html1 += '<option value="审核结束">审核结束</option>>';
        $('#depotState').append($(html1));
        form.render();
    }

    /**
     * 初始化下拉框
     * */
    function initGoods() {
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
    }

    /**
     * 定义表格
     * */
    function defineTable() {
        tableIns = table.render({
            elem: '#pur-data'
            , url: $tool.getContext() + 'purchase_return/list.do' //数据接口
            , method: 'post'
            , height: 415
            , page:true //开启分页
            , cols: [[ //表头
                //{type:'id',field: 'id', title: '采购单号',fixed: 'left', width:100}
                {field: 'orderNumber', title: '订单单号',fixed: 'left',width:180}
                , {field: 'goodsId', title: '商品名称', width:120}
                , {field: 'goodsNumber', title: '商品数量', width:100}
                , {field: 'price', title: '商品价格', width:100}
                , {field: 'applyUser', title: '申请人', width:80}
                , {field: 'applyTime', title: '申请时间', width:200}
                , {field: 'depotState', title: '审核状态', width:100}
                , {fixed: 'right', title: '操作', width: 260, align: 'center', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
            ]]
            , done: function (res, curr) {//请求完毕后的回调
                // 如果是异步请求数据方式，res即为你接口返回的信息.curr：当前页码
            }
        });

        //为toolbar添加事件响应
        table.on('tool(roleFilter)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var row = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象
            //区分事件
            if (layEvent === 'del') { //删除
                delPur(row.id);
            } else if (layEvent === 'edit') { //编辑
                //do something
                editPur(row.id);
            } else if(layEvent === 'commit') { //提交
                commitPut(row.id);
            } else if(layEvent === 'back') { //撤回
                recallPur(row.id);
            }
        });
    }
    defineTable();

    //查询
    form.on("submit(queryPurchase)", function (data) {
        var depotState = data.field.depotState;
        var goodsId = data.field.goodsId;

        //表格重新加载
        tableIns.reload({
            where:{
                depotState:depotState,
                goodsId:goodsId
            }
        });
        return false;
    });

    //添加退货单
    $(".usersAdd_btn").click(function () {
        var index = layui.layer.open({
            title: "申请退货单",
            type: 2,
            content: "purReturnAdd.html",
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回退货列表', '.layui-layer-setwin .layui-layer-close', {
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
    function delPur(id){
        layer.confirm('确认删除吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送删除指令
            var req = {
                id: id
            };

            $api.deletePurchaseReturn(req,function (data) {
                layer.msg("删除成功",{time:1000},function(){
                    //obj.del(); //删除对应行（tr）的DOM结构
                    //重新加载表格
                    tableIns.reload();
                });
            });
        });
    }

    //修改
    function editPur(id){
        var index = layui.layer.open({
            title: "修改退货单",
            type: 2,
            content: "purReturnEdit.html?id="+id,
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回退货列表', '.layui-layer-setwin .layui-layer-close', {
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

    //提交
    function commitPut(id){
        layer.confirm('确认提交吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送删除指令
            var req = {
                id: id
            };

            $api.commitPurchaseReturn(req,function (data) {
                layer.msg("提交成功",{time:1000},function(){
                    //obj.del(); //删除对应行（tr）的DOM结构
                    //重新加载表格
                    tableIns.reload();
                });
            });

        });
    }

    //撤回
    function recallPur(id){
        layer.confirm('确认撤回吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送删除指令
            var req = {
                id: id
            };

            $api.backPurchaseReturn(req,function (data) {
                layer.msg("撤回成功",{time:1000},function(){
                    //obj.del(); //删除对应行（tr）的DOM结构
                    //重新加载表格
                    tableIns.reload();
                });
            });

        });
    }

});