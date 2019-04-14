layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api:'api'
}).use(['form', 'layer', 'jquery', 'table', 'laypage', 'ajaxExtention','$tool','$api'], function () {
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

    }
    init();


    /**
     * 定义表格
     * */
    function defineTable() {
        tableIns = table.render({
            id: 'testReload'//配置动态表格id以便于执行重载操作
            ,elem: '#depotInventory-data'
            , height: 'full'
            , url: $tool.getContext() + 'depotInventory/depotInventoryList.do' //数据接口
            , method: 'post'
            ,toolbar: '#toolbarDemo' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            , page:true //开启分页
            , cols: [[ //表头
                {type:'numbers',title:'',fixed: 'left',width: '2.5%'}
                , {field: 'goodsId', title: '货物编号', width: '15%'}
                , {field: 'type', title: '货物类型', width: '15%'}
                , {field: 'goodsName', title: '货物名称', width: '17.5%'}
                , {field: 'goodsNumber', title: '货品数量', width: '15%'}
                , {field: 'shipmentsNumber', title: '待发货数量', width: '15%'}
                , {field: 'saleableNumber', title: '可适配数量', width: '15%'}
                , {fixed: 'right', title: '操作', width: 200, align: 'center', toolbar: '#barDemo', width: '20%'} //这里的toolbar值是模板元素的选择器
            ]]
            , done: function (res, curr) {//请求完毕后的回调
                //如果是异步请求数据方式，res即为你接口返回的信息.curr：当前页码
            }
        });

        //为toolbar添加事件响应
        table.on('tool(depotInventoryFilter)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var row = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            //区分事件
            if (layEvent === 'details') { //详情
                detailsInventory(row.goodsId,row.type);
            }
        });

    }
    defineTable();


    //查询
    form.on("submit(queryDepotInventory)", function (data) {
        var type = $("#type").val();
        var goodsId = data.field.goodsId;

        //表格重新加载
        tableIns.reload({
            where:{
                type:type,
                goodsId:goodsId,
            }
        });
        bindTableToolbarFunction();
        return false;
    });

    //库存详情
    function detailsInventory(goodsId,type){
        if (type=='原料') {
            var index = layui.layer.open({
                title: "库存详情",
                type: 2,
                content: "detailsMaterialGoodsInventory.html?goodsId="+goodsId,
                success: function (layero, index) {
                    setTimeout(function () {
                        layui.layer.tips('点击此处返回库存列表', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    }, 500)
                }
            });
        }
        if(type=='产品'){
            var index = layui.layer.open({
                title: "库存详情",
                type: 2,
                content: "detailsProductInventory.html?goodsId="+goodsId,
                success: function (layer, index) {
                    setTimeout(function () {
                        layui.layer.tips('点击此处返回库存列表', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    }, 500)
                }
            });
        }
        //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
        $(window).resize(function () {
            layui.layer.full(index);
        });
        layui.layer.full(index);
    }


    function bindTableToolbarFunction() {
        //所有的绑定事件，如：
        //批量导出
        $(".usersExport_btn").click(function () {
            //否则就是有数据则提示用户是否确定导出这些信息
            layer.confirm('确定导出这些?',function(index){
                var type = $("#type").val();
                var goodsId = $("#goodsId").val();
                var url = $tool.getContext() + "depotInventory/export.do?type="+type+"&goodsId="+goodsId;
                window.open(url);
                layer.close(index);
            });
        });
        $(".usersTemplate_btn").click(function () {
            //否则就是有数据则提示用户是否确定删除这些信息
            layer.confirm('确定下载模版?',function(index){
                var type = '无'
                var url = $tool.getContext() + "depotInventory/template.do?type="+type;
                window.open(url);
                layer.close(index);
            });
        });

    }
    bindTableToolbarFunction();

    layui.use(['element','upload'], function() {
        element = layui.element;
        upload = layui.upload;

        //指定允许上传的文件类型
        upload.render({
            elem: '#usersImport_btn'
            ,url: $tool.getContext() + 'depotInventory/import'
            ,accept: 'file' //普通文件
            ,multiple: true
            ,done: function(res){
                console.log(res);
            }
        });
    });

});