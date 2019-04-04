layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api:'api'
}).use(['form', 'layer', 'jquery','element', 'table', 'laypage', 'ajaxExtention', '$tool','$api'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery,
        laypage = layui.laypage,
        $tool = layui.$tool,
        table = layui.table,
        $api = layui.$api;
    var element = layui.element;

    var tableIns;//表格实例

    //日报表
    var option1 = {

        elem:'#day',
        id:'day',
        height:400,
        //height: 'full-100',
        cols:[[
            {type:'numbers',title:'序号',fixed: 'left'}
            , {field: 'department', title: '部门',width:100}
            , {field: 'balance', title: '款', width:120}
            , {field: 'billTime', title: '时间', width:170}
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
//=====================================================================//

    //周报表
    var option2 = {

        elem:'#week',
        id:'week',
        height:400,
        //height: 'full-100',
        cols:[[
            {type:'numbers',title:'序号',fixed: 'left'}
            , {field: 'department', title: '部门',width:100}
            , {field: 'balance', title: '款', width:120}
            , {field: 'billTime', title: '时间', width:170}
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
    tableIns = table.render(option2)
    form.render();
//=====================================================================//
    //周报表
    var option3 = {

        elem:'#month',
        id:'month',
        height:400,
        //height: 'full-100',
        cols:[[
            {type:'numbers',title:'序号',fixed: 'left'}
            , {field: 'department', title: '部门',width:100}
            , {field: 'balance', title: '款', width:120}
            , {field: 'billTime', title: '时间', width:170}
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
    tableIns = table.render(option3)
    form.render();
//=====================================================================//
    /**
     * 定义表格
     * */
    function defineTable() {
        tableIns = table.render({
            elem: '#bill'
            , height: 370
            , url: $tool.getContext() + 'bill/billList.do' //数据接口
            , method: 'post'
            , page:true //开启分页
            , cols: [[ //表头
                  {type:'numbers',title:'序号',fixed: 'left'}
                , {field: 'department', title: '部门',width:100,}
                , {field: 'balance', title: '款', width:120,}
                , {field: 'billTime', title: '时间', width:170,}
                 //这里的toolbar值是模板元素的选择器
            ]]
            , done: function (res, curr) {//请求完毕后的回调
                //如果是异步请求数据方式，res即为你接口返回的信息.curr：当前页码
            }
        });
        //为toolbar添加事件响应
        table.on('tool(applyFilter)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var row = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            //区分事件
            if (layEvent === 'generateApply') { //生成应付或应收单
                checkPurchaseOrder1(row.id,row.applyId,row.applyType);

            } else if (layEvent === 'show') { //点击行出发事件
                //do something
                checkPurchaseOrder(row.applyId,row.applyType);
            }
        });

        table.on()
    }
    defineTable();


    //查询
    form.on("submit(queryState)", function (data) {

        var financeState = data.field.financeState;

        //表格重新加载
        tableIns.reload({
            where:{
                financeState:financeState,
            }
        });

        return false;
    });


    //查看订单
    function checkPurchaseOrder(applyId,applyType){
        var applyType1 = applyType;
        var index = layui.layer.open({
            title: "订单详情",
            type: 2,
            content: "showOrder.html?applyId="+applyId+"&applyType="+applyType,
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

    //申请单
    function checkPurchaseOrder1(id,applyId,applyType){
        var applyType1 = applyType;
        var index = layui.layer.open({
            title: "订单详情",
            type: 2,
            content: "generateApply.html?applyId="+applyId+"&applyType="+applyType+"&id="+id,
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

    //导出数据
    $('#export').click(function(){
        var curWwwPath=window.document.location.href;
        //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
        var pathName=window.document.location.pathname;
        var pos=curWwwPath.indexOf(pathName);
        //获取主机地址，如： http://localhost:8083
        var localhostPaht=curWwwPath.substring(0,pos);
        //获取带"/"的项目名，如：/uimcardprj
        var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
        //alert(localhostPaht+projectName)
        window.location=localhostPaht+projectName + "//bill/export.do"
    })
});