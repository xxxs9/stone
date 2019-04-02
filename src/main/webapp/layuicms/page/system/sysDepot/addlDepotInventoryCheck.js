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
            id: 'testReload1'//配置动态表格id以便于执行重载操作
            ,elem: '#depotInventory-data1'
            , height: '250'
            , limit: '5'
            , url: $tool.getContext() + 'depotInventory/depotInventoryList.do' //数据接口
            , method: 'post'
            , page:true //开启分页
            , cols: [[ //表头
                {type:'numbers',title:'',fixed: 'left',width: '2.5%'}
                ,{field: 'check',type:'checkbox',title:'',fixed: 'left',width: '2.5%',LAY_CHECKED:true,style:'display:none;'}
                , {field: 'goodsId', title: '货物ID', width: '15%'}
                , {field: 'type', title: '货物类型', width: '15%'}
                , {field: 'goodsName', title: '货物名称', width: '17.5%'}
                , {field: 'goodsNumber', title: '货品数量', width: '15%'}
                , {field: 'shipmentsNumber', title: '待发货数量', width: '15%'}
                , {field: 'saleableNumber', title: '可销售数量', width: '15%'}
            ]]
            , done: function (res, curr) {//请求完毕后的回调
                //如果是异步请求数据方式，res即为你接口返回的信息.curr：当前页码
                $(".layui-table-box").find("[data-field='check']").css("display","none");
            }
        });


        tableIns2 = table.render({
            id: 'testReload2'//配置动态表格id以便于执行重载操作
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
                , {field: 'goodsName', title: '货物名称', width: '17.5%'}
                , {field: 'goodsNumber', title: '货品数量', width: '15%'}
                , {field: 'shipmentsNumber', title: '待发货数量', width: '15%'}
                , {field: 'saleableNumber', title: '可销售数量', width: '15%'}
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
            /*if (layEvent === 'add') { //删除
                addaddlDepotInventoryCheckDetail(tr);
            }*/
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


    /**
     * 表单提交
     * */
    form.on("submit(addDepotInventoryCheck)", function (data) {

        //利用layui的table组件完成数据的获取
        var check1 = table.checkStatus('testReload1'); //testReload 即为动态table的 id 对应的值
        var check2 = table.checkStatus('testReload2'); //testReload 即为动态table的 id 对应的值

        var check;
        var checkType = data.field.checkType;
        var recordNumber;
        if(data.field.checkType === '全部盘点'){
            check = check1;
            recordNumber = check1.data.length;
        }
        if(data.field.checkType === '部分盘点'){
            check = check2;
            recordNumber = check2.data.length;
        }

        //请求
        var req = {
            checkType:checkType,
            recordNumber: recordNumber
        };

        var checkId;  //盘点单id

        $api.AddDepotInventoryCheck(req,function (res) {
            //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
            var data = res.data;
            checkId = data;
            //如果数据总长度为0说明没有选择数据则提示用户先选择数据
            if(recordNumber ==0){
                layer.msg('请先选择数据',{icon:5,time:2000});
            }else{
                //否则就是有数据则提示用户是否确定删除这些信息
                layer.confirm('确定盘点这些?',function(index){
                    // check.data是获取到所选中行的所有信息,多余信息比较多所以要进行处理只要id

                    var types = '';//多个货物类型
                    var goodsIds = '';//多个货物编号
                    var goodsNumbers = '';//多个货物类型
                    //遍历所有信息
                    for(var i=0;i<recordNumber;i++){
                        types=types+check.data[i].type+',';//进行字符串拼接 每个type之间用,隔开
                        goodsIds=goodsIds+check.data[i].goodsId+',';//进行字符串拼接 每个goodsId之间用,隔开
                        goodsNumbers=goodsNumbers+check.data[i].goodsNumber+',';//进行字符串拼接 每个goodsNumber之间用,隔开
                    }


                    //向服务端发送批量删除指令
                    var req2 = {
                        checkId: checkId,
                        types: types,
                        goodsIds: goodsIds,
                        goodsNumbers: goodsNumbers
                    };

                    $api.AddsDepotInventoryCheckDetail(req2,function (data) {
                        layer.msg("盘点单添加成功！", {time: 1000}, function () {
                            layer.closeAll("iframe");
                            //刷新父页面
                            parent.location.reload();
                        });
                    });
                    //最后关闭确认框
                    layer.close(index);
                });
            }
        });
        return false;


    })

});


