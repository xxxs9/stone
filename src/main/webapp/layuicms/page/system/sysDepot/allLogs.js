layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api:'api'
}).use(['laydate', 'form', 'layer', 'jquery', 'table', 'laypage', 'ajaxExtention', '$tool','$api'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery,
        laypage = layui.laypage,
        $tool = layui.$tool,
        laydate = layui.laydate,
        table = layui.table,
        $api = layui.$api;

    var tableIns;//表格实例

    var queryParams = {//查询条件暂存
        loginName: "",
        operType: "",
        startTime: "",
        endTime: ""
    };

    /**
     * 页面初始化
     * */
    function init() {
        initDate();//初始化日期选择框
    }

    init();

    /**
     * 初始化日期选择
     * */
    function initDate() {
        laydate.render({
            elem: '#operDate'
            , type: 'datetime'
            , range: '&'
            , format: 'yyyy-MM-dd HH:mm:ss'
        });
    }

    /**
     * 定义表格
     * */
    function defineTable() {
        tableIns = table.render({
            id: 'testReload'//配置动态表格id以便于执行重载操作
            ,elem: '#log-data'
            , height: 395
            , url: $tool.getContext() + 'depotLog/logList.do' //数据接口
            , method: 'post'
            , page: true //开启分页
            , cols: [[ //表头
                {type: "checkbox"}
                , {field: 'loginName', title: '操作者', width: '10%'}
                , {field: 'ipAddr', title: 'ip地址', width: '15%'}
                , {field: 'operType', title: '操作类型', width: '10%'}
                , {field: 'operationName', title: '备注', width: '20%'}
                , {field: 'createDate', title: '操作日期', width: '15%'}
                , {fixed: 'right', title: '操作', width: 200, align: 'center', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
            ]]
            , done: function (res, curr) {//请求完毕后的回调
                //如果是异步请求数据方式，res即为你接口返回的信息.curr：当前页码
            }
        });

        //为toolbar添加事件响应
        table.on('tool(logFilter)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var row = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            //区分事件
            if (layEvent === 'del') { //删除
                delLog(row.id);
            }
        });
    }

    defineTable();


    //查询
    form.on("submit(queryLog)", function (data) {
        var loginName = data.field.loginName;
        var operType = data.field.operType;
        var operDate = data.field.operDate;
        //拆分出开始结束日期
        var startTime = $.trim((operDate.toString().split("&"))[0]);
        var endTime = $.trim((operDate.toString().split("&"))[1]);

        //保存一份
        queryParams.loginName = loginName;
        queryParams.operType = operType;
        queryParams.startTime = startTime;
        queryParams.endTime = endTime;

        //表格重新加载
        tableIns.reload({
            where: {
                loginName: loginName,
                operType: operType,
                startTime: startTime,
                endTime: endTime
            }
        });

        return false;
    });

    /**
     * 批量删除日志
     * 批量删除
     * @param req
     * */
    $(".logBatchDel_btn").click(function () {
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

                $api.BatchDeleteDepotLog(req,function (data) {
                    layer.msg("删除成功", {time: 1000}, function () {
                        //obj.del(); //删除对应行（tr）的DOM结构
                        clearParams();
                        //重新加载表格
                        tableIns.reload();
                    });
                });
                //最后关闭确认框
                layer.close(index);
            });
        }
    });

    /**
     * 删除日志
     * @param ids 删除id
     * */
    function delLog(id) {
        layer.confirm('确认删除吗？', function (confirmIndex) {
            layer.close(confirmIndex);//关闭confirm
            //向服务端发送删除指令
            var req = {
                id: id
            };

            $api.DeleteDepotLog(req,function (data) {
                layer.msg("删除成功", {time: 1000}, function () {
                    //obj.del(); //删除对应行（tr）的DOM结构
                    //重新加载表格
                    tableIns.reload();
                });
            });
        });
    }

    /**
     * 清空参数
     * */
    function clearParams(){

       queryParams = {//查询条件暂存
            loginName: "",
            operType: "",
            startTime: "",
            endTime: ""
        };
    }


});