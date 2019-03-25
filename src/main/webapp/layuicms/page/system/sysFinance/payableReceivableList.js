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
            id:'11',
            height:500,
            //height: 'full-100',
            cols:[[
                {field:'purchaseOrderId',width:120,title:'订单编号'},
                {field:'unitPrice',width:120,title:'单价'},
                {field:'goodsNumber',width:120,title:'采购数量'},
                {field:'totalPrice',width:120,title:'总价'},
                {field:'actualBalance',width:120,title:'实际价格'},
                {field:'documentMaker',width:120,title:'制单人'},
                {field:'documentMakeTime',width:180,title:'制单时间'},
                {field:'auditUser',width:120,title:'审核人'},
                {field:'auditTime',width:180,title:'审核时间'},
                {field:'auditDescribe',width:120,title:'审核信息'},
                {fixed:'right',title:'操作',toolbar:'#barDemo',width:180},
            ]],
            url:$tool.getContext() + 'finance/purchasePayList.do',
            method:'post',
            page:true,
            limit:[10,20,30,40],
            limit:10
        };
    table.render(option1)

    //采购退货应收单
    var option2 = {
        elem:'#purchaseReceive',
        //id:'22',
        height:500,
        //height: 'full-100',
        cols:[[
            {field:'purchaseOrderRejectedId',width:120,title:'订单编号'},
            {field:'unitPrice',width:120,title:'单价'},
            {field:'rejectedNumber',width:120,title:'退货数量'},
            {field:'totalPrice',width:120,title:'总价'},
            {field:'actualBalance',width:120,title:'实际价格'},
            {field:'documentMaker',width:120,title:'制单人'},
            {field:'documentMakeTime',width:180,title:'制单时间'},
            {field:'auditUser',width:120,title:'审核人'},
            {field:'auditTime',width:180,title:'审核时间'},
            {field:'auditDescribe',width:120,title:'审核信息'},
            {fixed:'right',title:'操作',toolbar:'#barDemo',width:180},
        ]],
        url:$tool.getContext() + 'finance/purchaseReceiveList.do',
        method:'post',
        page:true,
        limit:[10,20,30,40],
        limit:10
    };
    table.render(option2)

    //销售退货应付单
    var option3 = {
        elem:'#salePay',
        //id:''
        height:500,
        //height: 'full-100',
        cols:[[
            {field:'saleRejectedId',width:120,title:'订单编号'},
            {field:'unitPrice',width:120,title:'单价'},
            {field:'rejectedNumber',width:120,title:'退货数量'},
            {field:'totalPrice',width:120,title:'总价'},
            {field:'actualBalance',width:120,title:'实际价格'},
            {field:'documentMaker',width:120,title:'制单人'},
            {field:'documentMakeTime',width:180,title:'制单时间'},
            {field:'auditUser',width:120,title:'审核人'},
            {field:'auditTime',width:180,title:'审核时间'},
            {field:'auditDescribe',width:120,title:'审核信息'},
            {fixed:'right',title:'操作',toolbar:'#barDemo',width:180},
        ]],
        url:$tool.getContext() + 'finance/salePayList.do',
        method:'post',
        page:true,
        limit:[10,20,30,40],
        limit:10
    };
    table.render(option3)

    //销售发货应收单
    var option4 = {
        elem:'#saleReceive',
        //id:''
        height:500,
        //height: 'full-100',
        cols:[[
            {field:'saleId',width:120,title:'订单编号',fixed: 'left',},
            {field:'unitPrice',width:120,title:'单价'},
            {field:'productNumber',width:120,title:'发货数量'},
            {field:'totalPrice',width:120,title:'总价'},
            {field:'actualBalance',width:120,title:'实际价格'},
            {field:'documentMaker',width:120,title:'制单人'},
            {field:'documentMakeTime',width:180,title:'制单时间'},
            {field:'auditUser',width:120,title:'审核人'},
            {field:'auditTime',width:180,title:'审核时间'},
            {field:'auditDescribe',width:120,title:'审核信息'},
            {fixed: 'right',title:'操作',toolbar:'#barDemo',width:180},
        ]],
        url:$tool.getContext() + 'finance/saleReceiveList.do',
        method:'post',
        page:true,
        limit:[10,20,30,40],
        limit:10,
    };
    table.render(option4)
    form.render();


    /*element.on('tab(test)',function(data){
        if(data.index == '1'){
            showOption1(this.getAttribute("lay_id"));
        }
    })*/


});