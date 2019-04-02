layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api:'api'
}).use(['form', 'layer', 'jquery', 'table', 'laypage', 'ajaxExtention', '$tool','$api'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery,
        laypage = layui.laypage,
        $tool = layui.$tool,
        table = layui.table,
        $api = layui.$api;

    var tableIns;//表格实例

    /**
     * 页面初始化
     * */
    function init() {

        //初始化下拉框
        $api.GetFirstClassMenus(null,function (res) {
            var data = res.data;
            if(data.length > 0){
                var html = '<option value="">--请选择--</option>';
                for(var i=0;i<data.length;i++){
                    html += '<option value="'+data[i].id+'">'+data[i].title+'</option>>';
                }
                $('#parentMenu').append($(html));
                form.render();
            }
        });
        return false;
    }
    init();


    /**
     * 定义表格
     * */
    function defineTable() {
        tableIns = table.render({
            elem: '#menu-data'

            , url: $tool.getContext() + 'product/pageList' //数据接口
            , method: 'post'
            , page:true //开启分页
            , cols: [[ //表头
                  {type:'numbers',title:'序号',fixed: 'left'}
                  ,{field: 'other1', title: '产品流水号'}
                  ,{field: 'productName', title: '产品名称'}
                  ,{field: 'productNumber', title: '产品数量'}
                  ,{field: 'canSold', title: '订单来源'/*,templet:'#cd'*/}
                  , {field: 'productType', title: '产品类型',templet:'#tmp'}
                  , {field: 'productState', title: '产品状态',templet:'#tmpe'}
                  , {fixed: 'right', title: '操作', width: 260, align: 'center', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
            ]]
            , done: function (res, curr) {//请求完毕后的回调
                //如果是异步请求数据方式，res即为你接口返回的信息.curr：当前页码
                console.log(res)
            }
        });

        //为toolbar添加事件响应
        table.on('tool(menuFilter)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var row = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            //区分事件
            if (layEvent === 'del') { //删除
                delMenu(row.id,row.other2);
            } else if (layEvent === 'edit') {
                //do something
                editMenu(row.id);

            }else if (layEvent === 'audi') { //编辑
                //do something
                audi(row.id);
            }else if(layEvent==='stepBack1'){
                stepBack1(row.id);

            }else if (layEvent==='plan') {
                plan(row.id);
            }
            else if (layEvent === 'ManagerAudi') { //编辑
                //do something
                ManagerAudi(row.id,row.other2);
            }else if(layEvent==='fenPei'){
                fenPei(row.id);
            }else if (layEvent==='stopProduce') {//生产暂停
                stopProduce(row.id);
            }else if (layEvent==='completeProduce') {
                completeProduce(row.id);
            }else if (layEvent === 'JFR') {
                JFR(row.id);
            }else if (layEvent==='continueProduce') {
                continueProduce(row.id)
            }

        });
    }
    defineTable();


    //查询
    form.on("submit(queryMenu)", function (data) {
        var productName = data.field.productName;
        var productState = data.field.productState;


        //表格重新加载
        tableIns.reload({
            where:{
                productName:productName,
                productState:productState

            }
        });

       /* return false;*/
    });

    //添加
    $(".usersAdd_btn").click(function () {
        var index = layui.layer.open({
            title: "添加新工单",
            type: 2,
            content: "addProduct.html",
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回', '.layui-layer-setwin .layui-layer-close', {
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

    function continueProduce(id) {//暂停生产 1生成暂停生产单
        var index = layui.layer.open({
            title: "继续生产记录单",
            type: 2,
            content: "addContinueBillCheck.html?id="+id,
            success: function (layer, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回', '.layui-layer-setwin .layui-layer-close', {
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
    function stopProduce(id) {//暂停生产 1生成暂停生产单

        var index = layui.layer.open({
            title: "暂停生产记录单",
            type: 2,
            content: "addStopBillCheck.html?id="+id,
            success: function (layer, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回', '.layui-layer-setwin .layui-layer-close', {
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
    function completeProduce(id) {
        var index = layui.layer.open({
            title: "生产完成验收单",
            type: 2,
            content: "compareCheck.html?id="+id,
            success: function (layer, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回', '.layui-layer-setwin .layui-layer-close', {
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

    //删除
    function delMenu(id){
        layer.confirm('确认删除吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送删除指令
            var req = {
                id: id
            };

            $api.delProduct(req,function (data) {
                layer.msg("删除成功",{time:1000},function(){
                    //obj.del(); //删除对应行（tr）的DOM结构
                    //重新加载表格
                    tableIns.reload();
                });
                return false;
            });

        });
    }

    //编辑
    function editMenu(id){
        var index = layui.layer.open({
            title: "修改内容",
            type: 2,
            content: "editProduct.html?id="+id,
            success: function (layer, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回', '.layui-layer-setwin .layui-layer-close', {
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

    function JFR(id){
        var index = layui.layer.open({
            title: "填写领料单",
            type: 2,
            content: "jumpFormulaReach2.html?id="+id,
            success: function (layer, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回', '.layui-layer-setwin .layui-layer-close', {
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


    //计划
    function plan(id){
        var index = layui.layer.open({
            title: "生产计划",
            type: 2,
            content: "addProducePlan.html?id="+id,
            success: function (layer, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回', '.layui-layer-setwin .layui-layer-close', {
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


    function audi(id){
        layer.confirm('请确认操作', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送删除指令
            var req = {
                id: id,

            };

            $api.changProductState(req,function (data) {
                layer.msg("操作成功",{time:1000},function(){
                    //重新加载表格
                    tableIns.reload();
                });
            });
            return false;
        });
    }
    /*主管审核*/
    function ManagerAudi(id){
        var index = layui.layer.open({
            title: "主管审核",
            type: 2,
            content: "jumpProductAudi.html?id="+id,
            success: function (layer, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回', '.layui-layer-setwin .layui-layer-close', {
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
        function stepBack1(id) {
            layer.confirm('确定撤回吗？', function (confirmIndex) {
                layer.close(confirmIndex);//关闭confirm
                //向服务端发送删除指令
                var req = {
                    id: id
                };

                $api.stepBack1(req,function (data) {
                    layer.msg("操作成功",{time:1000},function(){
                        //重新加载表格
                        tableIns.reload();
                    });
                });
                return false;
            });

        }
    function fenPei(id){
        var index = layui.layer.open({//添加到生产计划单
            title: "加工单分配",
            type: 2,
            content: "addProducePlan.html?id="+id,
            success: function (layer, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回', '.layui-layer-setwin .layui-layer-close', {
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