layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api:'api'
}).use(['table', 'laypage','form', 'layer', 'jquery', 'ajaxExtention', '$tool','$api'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery,
        laypage = layui.laypage,
        $tool = layui.$tool,
        table = layui.table,
        $api = layui.$api;

    var tableIns1;//表格实例
    var tableIns2;//表格实例
    /**
     * 页面初始化
     * */
    function init() {
        //初始化下拉框
        initTable();
    }

    init();


    function initTable() {
        //库存表1
        tableIns1 = table.render({
            id: 'testReload'//配置动态表格id以便于执行重载操作
            ,elem: '#depotInventory-data1'
            , height: '250'
            , limit: '5'
            , url: $tool.getContext() + 'depotInventory/depotInventoryList.do' //数据接口
            , method: 'post'
            , page:true //开启分页
            , cols: [[ //表头
                {type:'numbers',title:'',fixed: 'left',width: '2.5%'}
                ,{type:'checkbox',title:'',fixed: 'left',width: '2.5%',LAY_CHECKED:true}
                , {field: 'goodsId', title: '货物ID', width: '15%'}
                , {field: 'type', title: '货物类型', width: '15%'}
                , {field: 'goodsName', title: '货物名称', width: '17.5%'}
                , {field: 'goodsNumber', title: '货品数量', width: '15%'}
                , {field: 'shipmentsNumber', title: '待发货数量', width: '15%'}
                , {field: 'saleableNumber', title: '可销售数量', width: '15%'}
            ]]
            , done: function (res, curr) {//请求完毕后的回调
                //如果是异步请求数据方式，res即为你接口返回的信息.curr：当前页码
            }
        });


        tableIns2 = table.render({
            id: 'testReload'//配置动态表格id以便于执行重载操作
            ,elem: '#depotInventory-data2'
            , height: '250'
            , limit: '5'
            , url: $tool.getContext() + 'depotInventory/depotInventoryList.do' //数据接口
            , method: 'post'
            , page:true //开启分页
            , cols: [[ //表头
                {type:'numbers',title:'',fixed: 'left',width: '2.5%'}
                ,{type:'checkbox',title:'',fixed: 'left',width: '2.5%'}
                , {field: 'goodsId', title: '货物ID', width: '15%'}
                , {field: 'type', title: '货物类型', width: '15%'}
                , {field: 'goodsName', title: '货物名称', width: '15%'}
                , {field: 'goodsNumber', title: '货品数量', width: '10%'}
                , {field: 'shipmentsNumber', title: '待发货数量', width: '10%'}
                , {field: 'saleableNumber', title: '可销售数量', width: '10%'}
                , {fixed: 'right', title: '操作', width: 200, align: 'center', toolbar: '#barDemo', width: '20%'} //这里的toolbar值是模板元素的选择器
            ]]
            , done: function (res, curr) {//请求完毕后的回调
                //如果是异步请求数据方式，res即为你接口返回的信息.curr：当前页码
            }
        });

        //为toolbar添加事件响应
        table.on('tool(partDepotInventoryFilter)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var row = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象
            //区分事件
            if (layEvent === 'add') { //删除
                addaddlDepotInventoryCheckDetail(tr);
            }
        });
    }

    /**
     * 监听radio选择
     * */
    form.on('radio(checkTypeFilter)', function (data) {
        //console.log(data.elem); //得到radio原始DOM对象
        var value = data.value;
        if ('全部盘点' === value) {
            $('.addDepotInventory-table').addClass('layui-hide');
            $('.addDepotInventory-table').removeClass('layui-anim-up');
            $('.allDepotInventory-table').removeClass('layui-hide');
            $('.allDepotInventory-table').addClass('layui-anim-up');

        }
        if ('部分盘点' === value) {
            $('.allDepotInventory-table').addClass('layui-hide');
            $('.allDepotInventory-table').removeClass('layui-anim-up');
            $('.addDepotInventory-table').removeClass('layui-hide');
            $('.addDepotInventory-table').addClass('layui-anim-up');
        }
    });


    //选中盘点库存
    function addaddlDepotInventoryCheckDetail(tr){
        var checkbox = $(tr).find("td div.laytable-cell-checkbox div.layui-form-checkbox I");
        checkbox.click();
    }


    /**
     * 表单提交
     * */
    form.on("submit(addDepotOrderIn)", function (data) {
        var goodsId;
        if(data.field.goodsType === '原料'){
            goodsId = data.field.materialId;
        }
        if(data.field.goodsType === '产品'){
            goodsId = data.field.productId;
        }
        var type = data.field.type;
        var goodsNumber = data.field.goodsNumber;

        //请求
        var req = {
            goodsId:goodsId,
            type: type,
            goodsNumber: goodsNumber
        };

        $api.AddDepotOrderIn(req,function (data) {
            //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
            layer.msg("入库单添加成功！", {time: 1000}, function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });

        return false;
    })

});


