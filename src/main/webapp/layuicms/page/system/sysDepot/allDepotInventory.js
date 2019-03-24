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
            , page:true //开启分页
            , cols: [[ //表头
                {type:'numbers',title:'',fixed: 'left',width: '2.5%'}
                , {field: 'type', title: '货物类型', width: '15%'}
                , {field: 'goodsName', title: '货物名称', width: '17.5%'}
                , {field: 'goodsNumber', title: '货品数量', width: '15%'}
                , {field: 'shipmentsNumber', title: '待发货数量', width: '15%'}
                , {field: 'saleableNumber', title: '可销售数量', width: '15%'}
                , {fixed: 'right', title: '操作', width: 200, align: 'center', toolbar: '#barDemo', width: '20%'} //这里的toolbar值是模板元素的选择器
            ]]
            , done: function (res, curr) {//请求完毕后的回调
                //如果是异步请求数据方式，res即为你接口返回的信息.curr：当前页码
            }
        });

    }
    defineTable();


    //查询
    form.on("submit(queryDepotInventory)", function (data) {
        var type = $("#type").val();
        var goodsName = data.field.goodsName;

        //表格重新加载
        tableIns.reload({
            where:{
                type:type,
                depotName:goodsName,
            }
        });

        return false;
    });

});