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
                ,{field: "checkNumber", title: "盘点数量", align: "center",edit:'number',type:'number'}
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
            if (layEvent === 'details') { //删除
                detailsInventory(row.goodsId,row.type);
            }
        });

        //监听单元格编辑
        table.on('edit(depotInventoryFilter)', function(obj){
            var value = obj.value //得到修改后的值
                ,data = obj.data //得到所在行所有键值
                ,field = obj.field; //得到字段

            if(!isPositiveNum(value)){
                layer.msg('请输入正确的格式(正整数)');
                return false;
            }

            //向服务端发送删除指令
            var req = {
                id: data.id,
                checkNumber:value
            };

            $api.UpdateDepotInventoryCheckDetail(req,function (data) {
                layer.msg("添加盘点记录成功！", {time: 1000}, function () {
                });
            });

        });

    }
    defineTable();

    function isPositiveNum(s){//是否为正整数
        var re = /^[0-9]*[1-9][0-9]*$/ ;
        return re.test(s)
    }

    //查询
    form.on("submit(queryDepotInventoryCheckDetail)", function (data) {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var type = $("#type").val();
        var goodsId = data.field.goodsId;

        //表格重新加载
        tableIns.reload({
            where:{
                type:type,
                goodsId:goodsId
            }
        });

        return false;
    });

    //结束盘点
    $(".usersEnd_btn").click(function () {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var id = queryArgs['id'];
        layer.confirm('确认盘点结束吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送删除指令
            var req = {
                id: id
            };

            $api.EndDepotInventoryCheck(req,function (data) {
                layer.msg("盘点结束",{time:1000},function(){
                    layer.closeAll("iframe");
                    //刷新父页面
                    parent.location.reload();
                });
            });
        });
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
                success: function (layero, index) {
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

});