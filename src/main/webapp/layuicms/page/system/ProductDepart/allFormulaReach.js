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
        $api.getAllProduct(null,function (res) {
            var data = res.data;
            if(data.length > 0){
                var html = '<option value="">--请选择--</option>';
                for(var i=0;i<data.length;i++){
                    html += '<option value="'+data[i].id+'">'+data[i].id+'---'+data[i].productName+'</option>>';
                }
                $('#productId').append($(html));
                form.render();
            }
        });
        $api.getFormulaReach(null,function (res) {
            var data = res.data;
            if(data.length > 0){
                var html = '<option value="">--请选择--</option>';
                for(var i=0;i<data.length;i++){
                    html += '<option value="'+data[i].reachUser+'">'+data[i].reachUser+'</option>>';
                }
                $('#reachUser').append($(html));
                form.render();
            }
        });
        return false;
    }
    init();

    /*function findProductName(res){
        $api.getProductById(res)
    }*/

    /**
     * 定义表格
     * */
    function defineTable() {
        tableIns = table.render({
            elem: '#menu-data'

            /*, url: $tool.getContext() + 'reach/pageList' //数据接口*/
            , url: $tool.getContext() + 'pcr/reachList' //数据接口
            , method: 'post'
            , page:true //开启分页
            , cols: [[ //表头
                  {type:'numbers',title:'序号',fixed: 'left'}
                  ,{field: 'productName', title: '产品名称' }
                  ,{field: 'depotAudi', title: '仓库授权',templet:'#depotA'}
                  ,{field: 'reachState', title: '产品状态',templet:'#temp'}
                  ,{field: 'reachUser', title: '领料人'}
                  ,{field: 'reachTime', title: '领料时间'}
                  ,{field: 'productId', title: '流水号' }
                  ,{field: 'produceFormulaId', title: '配方编号'}
                  ,{field: 'produceFormulaDetailId', title: '配方明细编号'}
                  /*,{field: 'formulaBack', title: '退料'/!*,templet:'#tmp'*!/}*/
                  ,{fixed: 'right', title: '操作', width: 260, align: 'center', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
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
            if (layEvent === 'del') { //删除
                delMenu(row.id);
            } else if (layEvent === 'edit') { //编辑
                //do something
                editMenu(row.reachId);

            }else if (layEvent === 'audi') { //编辑
               /*useless*/
                audi(row.reachId);
            }else if( layEvent==='producing'){
                producing(row.id);
            }else if(layEvent==='yanshou'){
                checkBill(row.id);
            }else if (layEvent==='stepback'){
                stepBack(row.id);
            }else if (layEvent==='depotAudi') {
                depotAudi(row.produceFormulaDetailId);
            }else if (layEvent==='goOn'){
                goOn(row.reachId);
            } else if(layEvent==='stop'){
                stop1(row.reachId);

            }else if(layEvent==='detail'){
                detail(row.id);
            }else if (layEvent==='detail2'){
                /*跳转到addBillCheck*/
                detail2(row.reachId,row.productId);
                console.log(row.reachId,row.productId,row.productName)


            }
        });
    }
    defineTable();


    //查询
    form.on("submit(queryMenu)", function (data) {
        var productName = data.field.productName;
        var reachUser = data.field.reachUser;


        //表格重新加载
        tableIns.reload({
            where:{
                productName:productName,
                reachUser:reachUser

            }
        });

       /* return false;*/
    });

    //添加(已隐藏)
    $(".usersAdd_btn").click(function () {
        var index = layui.layer.open({
            title: "新增领料单",
            type: 2,
            content: "addFormulaReach.html",
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
            });
            return false;
        });
    }

    //编辑
    function editMenu(id){
        var index = layui.layer.open({
            title: "修改内容",
            type: 2,
            content: "editFormulaReach.html?id="+id,
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
        layer.confirm('确定提交仓库审核吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送删除指令
            var req = {
                id: id
            };
            /*等待仓库的审核结果
            * todo(领料单的数据提交给仓库)
            *
            * */
            $api.WWW(req,function (data) {
                layer.msg(">>>>>>>操作成功，等待仓库审核中>>>>>>",{time:1000},function(){
                    //重新加载表格
                    tableIns.reload();
                });
            });
            return false;
        });
    }
    //跳转生成生产计划单
    function producing(id) {
        var index = layui.layer.open({
            title: "生产计划单",
            type: 2,
            content: "producePlan.html?id=" + id,
            success: function (layero, index) {
                setTimeout(function () {
                    layui.layer.tips('点击此处返回', '.layui-layer-setwin .layui-layer-close', {
                        tips: 3
                    });
                }, 500)
            }
        });
    }
    /*跳转验收单*/
    function checkBill(id) {
        var index = layui.layer.open({
            title: "生产验收单",
            type: 2,
            content: "jumpProductCheck.html?id=" + id,
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
    function stepBack(id) {
        /*撤回状态的方法*/

    }

    /*仓库审核*/
    function depotAudi(produceFormulaDetailId){
        layer.confirm('确定提交仓库审核吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送删除指令
            var req = {
                id: produceFormulaDetailId
            };
            /*查询配方明细表*/
            $api.getProduceFormulaDetailById(req,function (res) {
               var data = res.data;
               console.log(data);
               var materialId=data.field.materialId;
               var materialNumber = data.field.materialNumber;
               var  req ={
                   materialId:materialId,
                   materialNumber:materialNumber
               }
               /*fixme(华峰的api)*/
               $api.xxxx(req,function(res){

                });


            });
            return false;
        });
    }
    /*继续生产*/
    function goOn(id) {

        var req= {
            id:id
            }
     $api.goOn(req,function (data) {
         layer.msg("成功重新开始生产！",{time:1000},function(){

             tableIns.reload();
         });

     });
    }
    function stop1(id) {
        var req= {
            id:id
        }
        $api.stop(req,function (data) {
            layer.msg("已暂停生产！",{time:1000},function(){

                tableIns.reload();
            });
        });
    }
    function detail2(id,productId) {
        /*跳转详情页，并有重新开始生产的按钮*/
        var index = layui.layer.open({
            title: "领料单详情",
            type: 2,
            content: "addBillCheck.html?id=" + id+"&productId="+productId,
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
/*todo()*/
});