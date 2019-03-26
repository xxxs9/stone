layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api:'api'
}).use(['form', 'layer', 'laydate', 'jquery', 'table', 'laypage', 'ajaxExtention', '$tool','$api'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery,
        laypage = layui.laypage,
        $tool = layui.$tool,
        laydate = layui.laydate,
        table = layui.table,
        $api = layui.$api;

    var tableIns;//表格实例


    /**
     * 定义表格
     * */
    function defineTable() {
        tableIns = table.render({
            elem: '#payment'
            , height: 415
            , url: $tool.getContext() + 'finance/paymentList.do' //数据接口
            , method: 'post'
            , page:true //开启分页
            , cols: [[ //表头
                  {type:'numbers',title:'序号',fixed: 'left', width: 50}
                , {field: 'payId', title: '应付单编号', width: 150}
                , {field: 'payType', title: '应付类型', width: 120,templet:'#payType'}
                , {field: 'balance', title: '金额', width: 100}
                , {field: 'documentMaker', title: '制单人', width: 150}
                , {field: 'documentMakeTime', title: '制单时间', width: 180}
                , {fixed: 'right', title: '操作', width: 150, align: 'center', toolbar: '#barDemo'} //这里的toolbar值是模板元素的选择器
            ]]
            , done: function (res, curr) {//请求完毕后的回调
                //如果是异步请求数据方式，res即为你接口返回的信息.curr：当前页码
            }
        });

    }
    defineTable();


    //查询
    form.on("submit(queryPay)", function (data) {
        var payType = data.field.paymentType;
        //表格重新加载
        tableIns.reload({
            where:{
                payType:payType,
            }
        });

        return false;
    });
});