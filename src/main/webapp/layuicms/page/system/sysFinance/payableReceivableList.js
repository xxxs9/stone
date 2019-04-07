layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api:'api'
}).use(['form', 'layer', 'element', 'jquery', 'table', 'laypage', 'ajaxExtention', '$tool','$api'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery,
        laypage = layui.laypage,
        $tool = layui.$tool,
        table = layui.table,
        $api = layui.$api;
        var element = layui.element;

    var tableIns;//表格实例

    //采购进货应付单
        var option1 = {

            elem:'#purchasePay',
            id:'purchasePay',
            height:400,
            //height: 'full-100',
            cols:[[
                {fixed:'left',field:'purchaseOrderId',width:120,title:'订单编号',event:'show',style:'cursor:pointer',templet:'#purchaseOrderId'},
                {field:'unitPrice',width:120,title:'单价'},
                {field:'auditType',width:120,title:'订单类型',hide : false},
                {field:'goodsNumber',width:120,title:'采购数量'},
                {field:'totalPrice',width:100,title:'总价'},
                {field:'actualBalance',width:100,title:'实际价格'},
                {field:'documentMaker',width:100,title:'制单人'},
                {field:'documentMakeTime',width:180,title:'制单时间'},
                {field:'auditUser',width:120,title:'审核人'},
                {field:'auditTime',width:180,title:'审核时间'},
                {field:'auditDescribe',width:100,title:'审核信息'},
                {field:'auditState',width:120,title:'审核状态',templet:'#applyState'},
                {fixed:'right',title:'操作',toolbar:'#barDemo',width:100},
            ]],
            url:$tool.getContext() + 'finance/purchasePayList.do',
            method:'post',
            page:true,
            limit:[10,20,30,40],
            limit:10,
            done:function(res,curr,count){
                // 隐藏列
                $(".layui-table-box").find("[data-field='auditType']").css("display","none");
            }
        };
    tableIns = table.render(option1)
    form.render();

    //采购退货应收单
    var option2 = {
        elem:'#purchaseReceive',
        id:'purchaseReceive',
        height:400,
        //height: 'full-100',
        cols:[[
            {fixed:'left',field:'purchaseOrderRejectedId',width:120,title:'订单编号',event:'show',style:'cursor:pointer',templet:'#purchaseOrderRejectedId'},
            {field:'unitPrice',width:120,title:'单价'},
            {field:'rejectedNumber',width:120,title:'退货数量'},
            {field:'auditType',width:120,title:'单价',style:'display:none;'},
            {field:'totalPrice',width:120,title:'总价'},
            {field:'actualBalance',width:120,title:'实际价格'},
            {field:'documentMaker',width:120,title:'制单人'},
            {field:'documentMakeTime',width:180,title:'制单时间'},
            {field:'auditUser',width:120,title:'审核人'},
            {field:'auditTime',width:180,title:'审核时间'},
            {field:'auditDescribe',width:120,title:'审核信息'},
            {field:'auditState',width:120,title:'审核状态',templet:'#applyState'},
            {fixed:'right',title:'操作',toolbar:'#barDemo',width:100},
        ]],
        url:$tool.getContext() + 'finance/purchaseReceiveList.do',
        method:'post',
        page:true,
        limit:[10,20,30,40],
        limit:10
    };
    tableIns = table.render(option2)
    form.render();

    //销售退货应付单
    var option3 = {
        elem:'#salePay',
        id:'salePay',
        height:400,
        //height: 'full-100',
        cols:[[
            {fixed:'left',field:'saleRejectedId',width:120,title:'订单编号',event:'show',style:'cursor:pointer',templet:'#saleRejectedId'},
            {field:'unitPrice',width:120,title:'单价'},
            {field:'rejectedNumber',width:120,title:'退货数量'},
            {field:'auditType',width:120,title:'单价',style:'display:none;'},
            {field:'totalPrice',width:120,title:'总价'},
            {field:'actualBalance',width:120,title:'实际价格'},
            {field:'documentMaker',width:120,title:'制单人'},
            {field:'documentMakeTime',width:180,title:'制单时间'},
            {field:'auditUser',width:120,title:'审核人'},
            {field:'auditTime',width:180,title:'审核时间'},
            {field:'auditDescribe',width:120,title:'审核信息'},
            {field:'auditState',width:120,title:'审核状态',templet:'#applyState'},
            {fixed:'right',title:'操作',toolbar:'#barDemo',width:100},
        ]],
        url:$tool.getContext() + 'finance/salePayList.do',
        method:'post',
        page:true,
        limit:[10,20,30,40],
        limit:10
    };
    tableIns = table.render(option3)
    form.render();

    //销售发货应收单
    var option4 = {
        elem:'#saleReceive',
        id:'saleReceive',
        height:400,
        //height: 'full-100',
        cols:[[
            {fixed:'left',field:'saleId',width:120,title:'订单编号',fixed: 'left',event:'show',style:'cursor:pointer',templet:'#saleId'},
            {field:'unitPrice',width:120,title:'单价'},
            {field:'productNumber',width:120,title:'发货数量'},
            {field:'auditType',width:120,title:'单价',style:'display:none;'},
            {field:'totalPrice',width:120,title:'总价'},
            {field:'actualBalance',width:120,title:'实际价格'},
            {field:'documentMaker',width:120,title:'制单人'},
            {field:'documentMakeTime',width:180,title:'制单时间'},
            {field:'auditUser',width:120,title:'审核人'},
            {field:'auditTime',width:180,title:'审核时间'},
            {field:'auditDescribe',width:120,title:'审核信息'},
            {field:'auditState',width:120,title:'审核状态',templet:'#applyState'},
            {fixed: 'right',title:'操作',toolbar:'#barDemo',width:100},
        ]],
        url:$tool.getContext() + 'finance/saleReceiveList.do',
        method:'post',
        page:true,
        limit:[10,20,30,40],
        limit:10,
    };
    tableIns = table.render(option4)
    form.render();

    //为toolbar添加事件响应,采购应付
    table.on('tool(purchasePay)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var row = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象

        //区分事件
        if (layEvent === 'audit') {
            edit(row.id,row.auditType);
        } else if(layEvent === 'show'){
            show(row.purchaseOrderId,1);
        }
    });
    //为toolbar添加事件响应 //采购应收
    table.on('tool(purchaseReceive)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var row = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象

        //区分事件
        if (layEvent === 'audit') {
            edit(row.id,row.auditType);
        }else if(layEvent === 'show'){
            show(row.purchaseOrderRejectedId,3);
        }
    });

    //为toolbar添加事件响应 //销售应付
    table.on('tool(salePay)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var row = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象

        //区分事件
        if (layEvent === 'audit') {
            edit(row.id,row.auditType);
        }else if(layEvent === 'show'){
            show(row.saleRejectedId,2);
        }
    });

    //为toolbar添加事件响应 //销售应收
    table.on('tool(saleReceive)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var row = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象

        //区分事件
        if (layEvent === 'audit') {
            edit(row.id,row.auditType);

        }else if(layEvent === 'show'){
            show(row.saleId,4);
        }
    });

    //审核
    function edit(id,auditType){
        var index = layui.layer.open({
            title: "订单审核",
            type: 2,
            content: "audit.html?id="+id+"&auditType="+auditType,
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

    //查看订单
    function show(id,applyType){
        var applyType1 = applyType;
        var index = layui.layer.open({
            title: "订单详情",
            type: 2,
            content: "showOrder.html?applyId="+id+"&applyType="+applyType,
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回订单列表', '.layui-layer-setwin .layui-layer-close', {
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

    //查询
    form.on("submit(query)", function (data) {
        var auditState = data.field.applyState;
        //表格重新加载
        console.log(data)
        table.reload('purchasePay',{
            url: $tool.getContext() + 'finance/purchasePayList.do',
            where:{
                auditState:auditState,
            }
        });

        table.reload('purchaseReceive',{
            url:$tool.getContext() + 'finance/purchaseReceiveList.do',
            where:{
                auditState:auditState,
            }
        });

        table.reload('salePay',{
            url:$tool.getContext() + 'finance/salePayList.do',
            where:{
                auditState:auditState,
            }
        });

        table.reload('saleReceive',{
            url:$tool.getContext() + 'finance/saleReceiveList.do',
            where:{
                auditState:auditState,
            }
        });

        return false;
    });

});