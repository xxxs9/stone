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
        $api.GetGoodsType(null,function (res) {
            var data = res.data;
            if(data.length > 0){
                var html = '<option value="">--请选择--</option>';
                for(var i=0;i<data.length;i++){
                    html += '<option value="'+data[i]+'">'+data[i]+'</option>>';
                }
                $('#goodsType').append($(html));
                form.render();
            }
        });
        $api.GetGoodsSpecification(null,function (res) {
            var data = res.data;
            if(data.length > 0){
                var html = '<option value="">--请选择--</option>';
                for(var i=0;i<data.length;i++){
                    html += '<option value="'+data[i]+'">'+data[i]+'</option>>';
                }
                $('#goodsSpecification').append($(html));
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
            elem: '#material-data'
            , height: 415
            , url: $tool.getContext() + 'material/materialList.do' //数据接口
            , method: 'post'
            , page:true //开启分页
            , cols: [[ //表头
                {type:'numbers',title:'序号',fixed: 'left',width: '5%'},
                {field: 'goodsName', title: '货物名称', width: '15%'}
                , {field: 'goodsType', title: '货物类型', width: '15%'}
                , {field: 'goodsDescribe', title: '货物描述', width: '20%'}
                , {field: 'goodsSpecification', title: '货物规格', width: '25%'}
                , {fixed: 'right', title: '操作', width: 200, align: 'center', toolbar: '#barDemo', width: '20%'} //这里的toolbar值是模板元素的选择器
            ]]
            , done: function (res, curr) {//请求完毕后的回调
                //如果是异步请求数据方式，res即为你接口返回的信息.curr：当前页码
            }
        });

        //为toolbar添加事件响应
        table.on('tool(materialFilter)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var row = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            //区分事件
            if (layEvent === 'del') { //删除
                delMaterial(row.id);
            } else if (layEvent === 'edit') { //编辑
                //do something
                editMaterial(row.id);
            }
        });
    }
    defineTable();


    //查询
    form.on("submit(queryMaterial)", function (data) {
        var goodsName = data.field.goodsName;
        var goodsType = data.field.goodsTypeId;
        var goodsSpecification = data.field.goodsSpecificationId;

        //表格重新加载
        tableIns.reload({
            where:{
                goodsName:goodsName,
                goodsType:goodsType,
                goodsSpecification:goodsSpecification
            }
        });

        return false;
    });

    //添加原料
    $(".usersAdd_btn").click(function () {
        var index = layui.layer.open({
            title: "添加原料",
            type: 2,
            content: "addMaterial.html",
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回菜单列表', '.layui-layer-setwin .layui-layer-close', {
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
    function delMaterial(id){
        layer.confirm('确认删除吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送删除指令
            var req = {
                materialId: id
            };

            $api.DeleteMaterial(req,function (data) {
                layer.msg("删除成功",{time:1000},function(){
                    //obj.del(); //删除对应行（tr）的DOM结构
                    //重新加载表格
                    tableIns.reload();
                });
            });
        });
    }

    //编辑
    function editMaterial(id){
        var index = layui.layer.open({
            title: "编辑原料",
            type: 2,
            content: "editMaterial.html?id="+id,
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