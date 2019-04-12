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
    /*function init() {

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

    init();*/


    /**
     * 定义表格
     * */
    function defineTable() {
        tableIns = table.render({
            elem: '#menu-data'

            , url: $tool.getContext() + 'opera/pageList' //数据接口
            , method: 'post'
            , page:true //开启分页
            , cols: [[ //表头
                   {type:'numbers',title:'序号',fixed: 'left'},
                   {field: 'operatorNo', title: '操作编号'}
                  ,{field: 'operatorUser', title: '操作人'}
                  ,{field: 'operatorType', title: '操作类型'}
                  ,{field: 'operatorTime', title: '检验时间'}
                  ,{field: 'operatorRemark', title: '操作记录'}
                  /*,{field: 'state', title: '验收状态',templet:'#tmpe'}*/
                 /* ,{fixed: 'right', title: '操作', width: 260, align: 'center', toolbar: '#barDemo'}*/ //这里的toolbar值是模板元素的选择器
            ]]
            , done: function (res, curr) {//请求完毕后的回调
                //如果是异步请求数据方式，res即为你接口返回的信息.curr：当前页码
            }
        });

        //为toolbar添加事件响应
        table.on('tool(menuFilter)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var row = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            //区分事件
            if (layEvent === 'del') { //做废
                delMenu(row.id);
            } else if (layEvent === 'edit') { //编辑
                //do something
                editMenu(row.id);

            }else if (layEvent === 'audi') { //编辑
                //do something
                audi(row.id);
            }else if( layEvent==='stepBack'){
                stepBack(row.id);
            }else if(layEvent==='intoDepot'){
                intoDepot(row.id);
            }else if(layEvent==='check'){
                /*开始检验*/
                check(row.id);

            }
        });
    }
    defineTable();


    //查询
    form.on("submit(queryMenu)", function (data) {
        var operatorType = data.field.operatorType;
        var operatorUser = data.field.operatorUser;

        //表格重新加载
        tableIns.reload({
            where:{
                operatorType:operatorType,
                operatorUser:operatorUser

            }
        });

       /* return false;*/
    });

    //添加
    $(".usersAdd_btn").click(function () {
        var index = layui.layer.open({
            title: "添加",
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

    //删除
    function delMenu(id){
        layer.confirm('确定作废该验收单？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送删除指令
            var req = {
                id: id
            };

            $api.useless(req,function (data) {
                layer.msg("该验收单已作废！",{time:1000},function(){
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
            content: ".html?id="+id,
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
        layer.confirm('请确认操作', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送删除指令
            var req = {
                id: id
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
    //更改该id的领料单状态
    function stepBack(id) {
        layer.confirm('确定撤回吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送删除指令
            var req = {
                id: id
            };

            $api.billCheckstepBack(req,function (data) {
                layer.msg("撤回成功！",{time:1000},function(){
                    //重新加载表格
                    tableIns.reload();
                });
            });
            return false;
        });


    }
    /**
     * 产品入库
     * 将billCheck表的状态改成入库
     * 将product表的state字段改成是入库
     * */
    function intoDepot(id) {
        layer.confirm('确定将产品入库吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送删除指令
            var req = {
                id: id
            };
            //todo(传产品与华峰仓库对接。。。。。。。)
            $api.billCheckIntoDepot(req,function (res) {
                layer.msg("产品入库成功，请在仓库中查看",{time:2000},function(){
                    //重新加载表格
                    tableIns.reload();
                });
                $api.getBillCheckById(req,function (res) {
                    var data = res.data;
                     var productId = data.productId;
                     var req2 ={
                         id:productId
                     }
                    /*改变产品的状态 */
                    $api.changeProState(req2,function (res) {
                        console.log("产品入库");
                    })
                })
            });

            return false;
            })

    }

    function check(id) {

        var index = layui.layer.open({
            title: "检验报告",
            type: 2,
            content: "compareCheck.html?id="+id,
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
});