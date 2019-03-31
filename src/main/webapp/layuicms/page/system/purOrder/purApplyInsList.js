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
        initFinance();//初始化财务审核状态
    }

    init();

    /**
     * 初始化状态查询
     */
    function initState(){
        var html1 = '<option value="">--请选择--</option>';
        html1 += '<option value="未审核">提交审核中</option>>';
        html1 += '<option value="审核通过">审核通过</option>>';
        html1 += '<option value="审核未通过">审核未通过</option>>';

        $('#state').append($(html1));
        form.render();
    }

    /**
     * 初始化下拉框
     * */
    function initGoods() {
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
     * 初始化财务审核状态查询
     */
    function initFinance(){
        var html1 = '<option value="">--请选择--</option>';
        html1 += '<option value="未通过">审核未通过</option>>';
        html1 += '<option value="已付款">已付款</option>>';

        $('#financeState').append($(html1));
        form.render();
    }

    /**
     * 定义表格
     * */
    function defineTable() {
        tableIns = table.render({
            elem: '#pur-data'
            , url: $tool.getContext() + 'purchase_order/listInspect.do' //数据接口
            , method: 'post'
            , height: 415
            , page:true //开启分页
            , cols: [[ //表头
                //{type:'id',field: 'id', title: '采购单号',fixed: 'left', width:100}
                {field: 'orderNumber', title: '订单单号',fixed: 'left',width:180}
                , {field: 'goodsId', title: '商品名称', width:120}
                , {field: 'goodsNumber', title: '商品数量', width:100}
                , {field: 'price', title: '商品单价', width:100}
                , {field: 'totalPrice', title: '商品总价', width:100}
                , {field: 'state', title: '审核状态', width:120}
                , {field: 'applyUser', title: '申请人', width:100}
                , {field: 'applyTime', title: '申请时间', width:200}
                , {field: 'applyDescribe', title: '申请描述', width:220}
                , {field: 'orderAuditUser', title: '审核人', width:80}
                , {field: 'orderAuditTime', title: '审核时间', width:200}
                , {field: 'auditDescribe', title: '审核描述', width:220}
                , {field: 'financeState', title: '财务审核状态', width:120}
                , {fixed: 'right', title: '操作', width: 120, align: 'center', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
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
            if (layEvent === 'inspect') { //审核
                inspectPur(row.id);
            }
        });
    }
    defineTable();

    //查询
    form.on("submit(queryPurchase)", function (data) {
        var state = data.field.state;
        var goodsId = data.field.goodsId;
        var financeState = data.field.financeState;

        //表格重新加载
        tableIns.reload({
            where:{
                state:state,
                goodsId:goodsId,
                financeState:financeState
            }
        });
        return false;
    });

    //审核
    function inspectPur(id){
        var index = layui.layer.open({
            title: "审核",
            type: 2,
            content: "purApplyInspect.html?id="+id,
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回审核列表', '.layui-layer-setwin .layui-layer-close', {
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