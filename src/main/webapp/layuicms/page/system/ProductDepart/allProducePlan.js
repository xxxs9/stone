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
    var wid;
    /**
     * 页面初始化
     * */
    function init() {

       /* //初始化下拉框
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
        return false;*/
       var req = {
           id:wid
        }

       $api.getAllProduct(req,function (res) {
           var data = res.data;
           var arr =new Array();
           if (data.length > 0) {
               var html = '';
               for (var i = 0; i < data.length; i++) {
                   if (arr.indexOf(data[i].productName) == -1) {
                       html += '<option value="' + data[i].productName + '">' + data[i].productName + '</option>';
                   }
                   arr[i]=data[i].formulaBack;
               }
               $('#productName').append($(html));
               /*$('#productName').val(hh);*/
               form.render();
           }
       })

    }
    init();
    function getPro() {
        $.getAllProduct(null,function (res) {
            var data = res.data;
            var arr =new Array();
            if (data.length > 0) {
                var html = '';
                for (var i = 0; i < data.length; i++) {
                    if (arr.indexOf(data[i].productName) == -1) {
                        html += '<option value="' + data[i].productName + '">' + data[i].productName + '</option>';
                    }
                    arr[i]=data[i].formulaBack;
                }
                $('#productName').append($(html));
                /*$('#productName').val(hh);*/
                form.render();
            }

        });
    }

    /**
     * 定义表格
     * */
    function defineTable() {
        tableIns = table.render({
            elem: '#menu-data'

            , url: $tool.getContext() + 'ppp/list' //数据接口
            , method: 'post'
            , page:true //开启分页
            , cols: [[ //表头
                  {type:'numbers',title:'序号',fixed: 'left'}
                  ,{field: 'productName', title: '产品名称',templet:'#productName'}
                  ,{field:'goodsNumber', title: '现有库存'}
                  ,{field: 'billDate', title: '订单日期'}
                  ,{field: 'billCycle', title: '订单周期'/*,templet:'#cd'*/}
                  ,{field: 'planNumber', title: '计划产量'/*,templet:'#tmp'*/}
                  ,{field: 'realNumber', title: '实际产量'/*,templet:'#tmpe'*/}
                  ,{field: 'produceDate', title: '投产日期'}
                  ,{field: 'finishDate', title: '完成日期'}
                  /*,{field: 'state', title: '状态',templet:'#tmp'}*/
                  ,{field: 'planRemark', title: '备注'}
                  ,{fixed: 'right', title: '操作', width: 260, align: 'center', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
            ]]
            , done: function (res, curr) {//请求完毕后的回调
                //如果是异步请求数据方式，res即为你接口返回的信息.curr：当前页码
               /* wid=field.productId;*/

            }
        });

        //为toolbar添加事件响应
        table.on('tool(menuFilter)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var row = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            //区分事件
            if (layEvent === 'del') { //删除
                delMenu(row.id);
            } else if (layEvent === 'edit') { //编辑
                //do something
                editMenu(row.id);

            }else if (layEvent === 'audi') { //编辑
                //do something
                audi(row.id);
            }else if( layEvent==='producing'){
                producing(row.id);
            }
        });
    }
    defineTable();


    //查询
    form.on("submit(queryMenu)", function (data) {
        var productId = data.field.productId;
        var state = data.field.state;


        //表格重新加载
        tableIns.reload({
            where:{
                productId:productId,
                state:state

            }
        });

       /* return false;*/
    });

    //添加
    $(".usersAdd_btn").click(function () {
        var index = layui.layer.open({
            title: "添加生产计划",
            type: 2,
            content: "addProducePlan.html",
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

    //删除
    function delMenu(id){
        layer.confirm('确认作废吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送删除指令
            var req = {
                id: id
            };

            $api.delProducePlan(req,function (data) {
                layer.msg("删除成功",{time:1000},function(){
                    //obj.del(); //删除对应行（tr）的DOM结构
                    //重新加载表格
                    tableIns.reload();
                });
            });
            return false;
        });
    }

    //编辑
    function editMenu(id){
        var index = layui.layer.open({
            title: "修改内容",
            type: 2,
            content: "editProducePlan.html?id="+id,
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
    }

    function audi(id){

        var index = layui.layer.open({

            title: "领料单",
            type: 2,
            content: "jumpFormulaReach.html?id=" + id,
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        });
        $(window).resize(function () {
            layui.layer.full(index);
        });
        layui.layer.full(index);

    }
    //跳转生成生产计划单
    function producing(id) {
        var index = layui.layer.open({
            title: "生产计划单",
            type: 2,
            content: "allProducePlan.html?id=" + id,
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        });
    }
});