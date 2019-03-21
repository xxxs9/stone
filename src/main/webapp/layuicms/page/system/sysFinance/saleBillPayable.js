layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api:'api'
}).use(['form', 'laydate', 'layer', 'jquery', 'table', 'laypage', 'ajaxExtention', '$tool','$api'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery,
        laypage = layui.laypage,
        $tool = layui.$tool,
        laydate = layui.laydate,
        table = layui.table,
        $api = layui.$api;

    var tableIns;//表格实例

    /**
     * 页面初始化
     * */
    function init() {
        initDate();
    }
    init();

    /**
     * 初始化日期选择
     * */
    function initDate() {
        laydate.render({
            elem: '#documentMakeTime'
            , type: 'datetime'
            , range: '&'
            , format: 'yyyy-MM-dd HH:mm:ss'
        });
    }


    /**
     * 定义表格
     * */
    function defineTable() {
        tableIns = table.render({
            elem: '#billPay'
            , height: 415
            , url: $tool.getContext() + 'finance/saleBillPayList.do' //数据接口
            , method: 'post'
            , page:true //开启分页
            , cols: [[ //表头
                {type:'numbers',title:'序号',fixed: 'left'},
                {field: 'saleRejectedId', title: '申请编号', width: '10%'}
                , {field: 'unitPrice', title: '单价', width: '10%'}
                , {field: 'rejectedNumber', title: '退回数量', width: '10%'}
                , {field: 'totalPrice', title: '总价', width: '10%'}
                , {field: 'actualBalance', title: '实际价格', width: '10%'}
                , {field: 'documentMaker', title: '制表人', width: '10%'}
                , {field: 'documentMakeTime', title: '制表时间', width: '10%'}
                , {field: 'auditUser', title: '审核人', width: '20%'}
                , {field: 'auditTime', title: '审核时间', width: '10%'}
                , {field: 'auditDescribe', title: '审核描述', width: '20%'}
                , {fixed: 'right', title: '操作', width: 150, align: 'center', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
            ]]
            , done: function (res, curr) {//请求完毕后的回调
                //如果是异步请求数据方式，res即为你接口返回的信息.curr：当前页码
            }
        });

        //为toolbar添加事件响应
        table.on('tool(payFilter)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var row = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            //区分事件
            if (layEvent === 'edit') { //删除
                editPay(row.id);
            } /*else if (layEvent === 'edit') { //编辑
                //do something
                editPay(row.id);
            }*/
        });
    }
    defineTable();


    //查询
    form.on("submit(queryPay)", function (data) {
        var auditType = data.field.auditType;
        var documentMakeTime = data.field.documentMakeTime;

        //表格重新加载
        tableIns.reload({
            where:{
                auditType:auditType,
                documentMakeTime:documentMakeTime
            }
        });

        return false;
    });

    //添加角色
    $(".payAdd_btn").click(function () {
        var index = layui.layer.open({
            title: "添加应付单",
            type: 2,
            content: "addPay.html",
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回菜单列表', '.layui-layer-setwin .layui-layer-close', {
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

    //删除  TODO...应付单逻辑应为无法删除，只能重新编辑
    /*function delPay(id){
        layer.confirm('确认删除吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送删除指令
            var req = {
                menuId: id
            };

            $api.DeletePay(req,function (data) {
                layer.msg("删除成功",{time:1000},function(){
                    //obj.del(); //删除对应行（tr）的DOM结构
                    //重新加载表格
                    tableIns.reload();
                });
            });
        });
    }*/

    //编辑
    function editPay(id){
        var index = layui.layer.open({
            title: "编辑销售应付单",
            type: 2,
            content: "purchaseBillPayableEdit.html?id="+id,
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回应付单列表', '.layui-layer-setwin .layui-layer-close', {
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