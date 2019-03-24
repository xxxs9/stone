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
        $api.GetDepotType(null,function (res) {
            var data = res.data;
            if(data.length > 0){
                var html = '<option value="">--请填写--</option>';
                for(var i=0;i<data.length;i++){
                    html += '<option value="'+data[i]+'">'+data[i]+'</option>>';
                }
                $('#depotType').append($(html));
                form.render();
            }
        });
    }
    init();


    /**
     * 定义表格
     * */
    function defineTable() {
        tableIns = table.render({
            id: 'testReload'//配置动态表格id以便于执行重载操作
            ,elem: '#depot-data'
            , height: 'full'
            , url: $tool.getContext() + 'depotSet/depotList.do' //数据接口
            , method: 'post'
            , page:true //开启分页
            , cols: [[ //表头
                {type:'numbers',title:'',fixed: 'left',width: '2.5%'}
                ,{type:'checkbox',title:'',fixed: 'left',width: '2.5%'}
                ,{field: 'depotNumber', title: '仓库编号', width: '10%'}
                , {field: 'depotName', title: '仓库名称', width: '10%'}
                , {field: 'depotType', title: '仓库类型', width: '10%'}
                , {field: 'depotAddress', title: '仓库地址', width: '20%'}
                , {field: 'depotDescribe', title: '仓库描述', width: '25%'}
                , {fixed: 'right', title: '操作', width: 200, align: 'center', toolbar: '#barDemo', width: '20%'} //这里的toolbar值是模板元素的选择器
            ]]
            , done: function (res, curr) {//请求完毕后的回调
                //如果是异步请求数据方式，res即为你接口返回的信息.curr：当前页码
            }
        });

        //为toolbar添加事件响应
        table.on('tool(depotFilter)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var row = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            //区分事件
            if (layEvent === 'del') { //删除
                delDepot(row.id);
            } else if (layEvent === 'edit') { //编辑
                //do something
                editDepot(row.id);
            }
        });
    }
    defineTable();


    //查询
    form.on("submit(queryDepot)", function (data) {
        var depotNumber = data.field.depotNumber;
        var depotName = data.field.depotName;
        var depotType = data.field.depotType;

        //表格重新加载
        tableIns.reload({
            where:{
                depotNumber:depotNumber,
                depotName:depotName,
                depotType:depotType
            }
        });

        return false;
    });

    //添加仓库
    $(".usersAdd_btn").click(function () {
        var index = layui.layer.open({
            title: "新增仓库",
            type: 2,
            content: "addDepot.html",
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回仓库列表', '.layui-layer-setwin .layui-layer-close', {
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

    //批量删除
    $(".usersDels_btn").click(function () {
        //利用layui的table组件完成数据的获取
        var check = table.checkStatus('testReload'); //testReload 即为动态table的 id 对应的值
        //如果数据总长度为0说明没有选择数据则提示用户先选择数据
        if(check.data.length==0){
            layer.msg('请先选择数据',{icon:5,time:2000});
        }else{
            //否则就是有数据则提示用户是否确定删除这些信息
            layer.confirm('确定删除这些?',function(index){
                // check.data是获取到所选中行的所有信息,多余信息比较多所以要进行处理只要id
                var ids='';//先定义一个要拼接的字符串
                //遍历所有信息
                for(var i=0;i<check.data.length;i++){
                    ids=ids+check.data[i].id+',';//进行字符串拼接 每个id之间用,隔开
                }
                //向服务端发送批量删除指令
                var req = {
                    ids: ids
                };

                $api.DelsDepot(req,function (data) {
                    layer.msg("删除成功",{time:1000},function(){
                        //obj.del(); //删除对应行（tr）的DOM结构
                        //重新加载表格
                        tableIns.reload();
                    });
                });
                //最后关闭确认框
                layer.close(index);
            });
        }
    });

    //删除
    function delDepot(id){
        layer.confirm('确认删除吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送删除指令
            var req = {
                id: id
            };

            $api.DeleteDepot(req,function (data) {
                layer.msg("删除成功",{time:1000},function(){
                    //obj.del(); //删除对应行（tr）的DOM结构
                    //重新加载表格
                    tableIns.reload();
                });
            });
        });
    }

    //编辑
    function editDepot(id){
        var index = layui.layer.open({
            title: "编辑仓库",
            type: 2,
            content: "editDepot.html?id="+id,
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回原料列表', '.layui-layer-setwin .layui-layer-close', {
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