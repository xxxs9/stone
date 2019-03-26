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
     * 定义表格
     * */
    function defineTable() {
        tableIns = table.render({
            elem: '#menu-data'
            , height: 415
            , url: $tool.getContext() + 'finance/receiptList.do' //数据接口
            , method: 'post'
            , page: true //开启分页
            , cols: [[ //表头
                {type: 'numbers', title: '序号', fixed: 'left', width: 50}

                , {field: 'receiveId', title: '应收单编号', width: 150}
                , {field: 'receiveType', title: '应收类型', width: 120, templet: '#receiveType'}
                , {field: 'balance', title: '金额', width: 100}
                , {field: 'documentMaker', title: '制单人', width: 150}
                , {field: 'documentMakeTime', title: '制单时间', width: 180}
                //这里的toolbar值是模板元素的选择器
            ]]
            , done: function (res, curr) {//请求完毕后的回调
                //如果是异步请求数据方式，res即为你接口返回的信息.curr：当前页码
            }
        });

    }
    defineTable();

    //查询
    form.on("submit(queryReceive)", function (data) {
        var receiveType = data.field.receiptType;
        console.log(data)
        //表格重新加载
        tableIns.reload({
            where:{
                receiveType:receiveType,
            }
        });

        return false;
    });

});