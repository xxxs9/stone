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
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var checkId = queryArgs['id'];
        tableIns = table.render({
            id: 'testReload'//配置动态表格id以便于执行重载操作
            ,elem: '#depotInventory-data'
            , height: 'full'
            , url: $tool.getContext() + 'depotInventoryCheckDetail/depotInventoryCheckDetailList.do?checkId='+checkId //数据接口
            , method: 'post'
            , page:true //开启分页
            , cols: [[ //表头
                {type:'numbers',title:'',fixed: 'left',width: '2.5%'}
                , {field: 'goodsId', title: '货物ID', width: '15%'}
                , {field: 'type', title: '货物类型', width: '15%'}
                , {field: 'goodsNumber', title: '货品数量', width: '15%'}
                ,{field: "checkNumber", title: "盘点数量", align: "center"}
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
            if (layEvent === 'revise') { //修正库存
                reviseInventory(row.id,row.goodsId,row.checkNumber);
            }
        });

    }
    defineTable();


    //查询
    form.on("submit(queryDepotInventory)", function (data) {
        var queryArgs = $tool.getQueryParam();
        var type = $("#type").val();
        var goodsId = data.field.goodsId;
        var checkId = queryArgs['id'];

        //表格重新加载
        tableIns.reload({
            where:{
                type:type,
                goodsId:goodsId,
            }
        });

        return false;
    });

    //结束审核
    $(".usersEnd_btn").click(function () {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var id = queryArgs['id'];
        var state = "审核通过"
        layer.confirm('确认盘点审核结束吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送删除指令
            var req = {
                id: id,
                state:state
            };

            $api.AuditDepotInventoryCheck(req,function (data) {
                layer.msg("盘点审核结束",{time:1000},function(){
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                });
            });
        });
    });

    //驳回审核
    $(".usersReject_btn").click(function () {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var id = queryArgs['id'];
        var state = "审核未通过"
        layer.confirm('确认驳回吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送删除指令
            var req = {
                id: id,
                state:state
            };

            $api.AuditRejectDepotInventoryCheck(req,function (data) {
                layer.msg("驳回成功",{time:1000},function(){
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                });
            });
        });
    });

    //修正库存
    function reviseInventory(id,goodsId,checkNumber){
        layer.confirm('修正库存吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送删除指令
            var req = {
                id: id,
                goodsId: goodsId,
                goodsNumber: checkNumber
            };

            $api.UpdateDepotInventoryGoodsNumber(req,function (data) {
                layer.msg("修正成功",{time:1000},function(){
                    //obj.del(); //删除对应行（tr）的DOM结构
                    //重新加载表格
                    tableIns.reload();
                });
            });

        });
    }


});