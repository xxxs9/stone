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

    var tableIns; //表格实例

    /**
     * 业务初始化
     * */
    function init() {
        //显示姓名

       console.log((window.sessionStorage));
    }
    init();

    function selectRoles() {

                        //超级管理员，显示所有表格
                        $('#messageShow').show();
                        $('#supplierShow').show();
                        $('#saleShow').show();
                        $('#proOrderShow').show();
                        $('#depotShow').show();
                        $('#productShow').show();
                        $('#financeShow').show();

                        //通知部门
                        function messageTable() {
                            tableIns = table.render({
                                elem: '#message'
                                , url: $tool.getContext() + 'allRole/notify.do' //数据接口
                                , method: 'post'
                                , cols: [[ //表头
                                    {field: 'receiverId', title: '收件人', width: 150}
                                    , {field: 'sendTime', title: '发送时间', width: 200}
                                    , {field: 'content', title: '邮件内容',width:790}
                                ]]
                                , done: function (res, curr) {//请求完毕后的回调
                                    // 如果是异步请求数据方式，res即为你接口返回的信息.curr：当前页码
                                }
                            });
                        }

                        messageTable();

                        //供应商部门
                        function supplierTable() {
                            tableIns = table.render({
                                elem: '#supplier'
                                , url: $tool.getContext() + 'allRole/supplier.do' //数据接口
                                , method: 'post'
                                , cols: [[ //表头
                                    {field: 'supplierName', title: '供应商', width: 200}
                                    , {field: 'goodsName', title: '货物名称', width: 150}
                                    , {field: 'goodsPrice', title: '货物单格', width: 100}
                                    , {field: 'phone', title: '供应商电话', width: 200}
                                    , {field: 'email', title: '供应商邮箱', width: 488}
                                ]]
                                , done: function (res, curr) {//请求完毕后的回调
                                    // 如果是异步请求数据方式，res即为你接口返回的信息.curr：当前页码
                                }
                            });
                        }

                        supplierTable();

                        //销售部门
                        function saleTable() {
                            tableIns = table.render({
                                elem: '#sale'
                                , url: $tool.getContext() + 'allRole/sale.do' //数据接口
                                , method: 'post'
                                , cols: [[ //表头
                                    {field: 'orderId', title: '订单单号', width: 150}
                                    , {field: 'applyUser', title: '销售员', width: 150}
                                    , {field: 'customer', title: '客户名', width: 150}
                                    , {field: 'saleName', title: '商品名称', width: 150}
                                    , {field: 'orderTime', title: '购买日期', width: 540}
                                ]]
                                , done: function (res, curr) {//请求完毕后的回调
                                    // 如果是异步请求数据方式，res即为你接口返回的信息.curr：当前页码
                                }
                            });
                        }

                        saleTable();

                        //采购部门
                        function proOrderTable() {
                            tableIns = table.render({
                                elem: '#proOrder'
                                , url: $tool.getContext() + 'allRole/proOrder.do' //数据接口
                                , method: 'post'
                                , cols: [[ //表头
                                    {field: 'orderNumber', title: '订单编号', width: 150}
                                    , {field: 'orderAuditUser', title: '采购员', width: 150}
                                    , {field: 'goodsName', title: '采购商品', width: 150}
                                    , {field: 'goodsNumber', title: '采购数量', width: 150}
                                    , {field: 'applyTime', title: '采购时间',width:540}
                                ]]
                                , done: function (res, curr) {//请求完毕后的回调
                                    // 如果是异步请求数据方式，res即为你接口返回的信息.curr：当前页码
                                }
                            });
                        }

                        proOrderTable();

                        //仓库部门
                        function depotTable() {
                            tableIns = table.render({
                                elem: '#depot'
                                , url: $tool.getContext() + 'allRole/depot.do' //数据接口
                                , method: 'post'
                                , cols: [[ //表头
                                    {field: 'type', title: '状态', width: 200}
                                    , {field: 'depotName', title: '货物名称', width: 200}
                                    , {field: 'depotNumber', title: '货物数量', width: 200}
                                    , {field: 'saleableNumber', title: '可销售数量', width: 540}
                                ]]
                                , done: function (res, curr) {//请求完毕后的回调
                                    // 如果是异步请求数据方式，res即为你接口返回的信息.curr：当前页码
                                }
                            });
                        }

                        depotTable();

                        //生产部门
                        function productTable() {
                            tableIns = table.render({
                                elem: '#product'
                                , url: $tool.getContext() + 'allRole/product.do' //数据接口
                                , method: 'post'
                                , cols: [[ //表头
                                    {field: 'productName', title: '产品名称', width: 200}
                                    , {field: 'productNumber', title: '生产数量', width: 200}
                                    , {field: 'productState', title: '生产状态', width: 200,templet:"#productState"}
                                    , {field: 'canSold', title: '来源', width: 540}
                                ]]
                                , done: function (res, curr) {//请求完毕后的回调
                                    // 如果是异步请求数据方式，res即为你接口返回的信息.curr：当前页码
                                }
                            });
                        }

                        productTable();

                        //财务部门
                        function financeTable() {
                            tableIns = table.render({
                                elem: '#finance'
                                , url: $tool.getContext() + 'allRole/finance.do' //数据接口
                                , method: 'post'
                                , cols: [[ //表头
                                    {field: 'financeApplyUser', title: '申请人', width: 200}
                                    , {field: 'applyMoney', title: '申请金额', width: 200}
                                    , {field: 'financeApplyState', title: '审核状态', width: 200, templet: "#trans"}
                                    , {field: 'financeApplyTime', title: '申请时间', width: 540}
                                ]]
                                , done: function (res, curr) {//请求完毕后的回调
                                    // 如果是异步请求数据方式，res即为你接口返回的信息.curr：当前页码
                                }
                            });
                        }

                        financeTable();

                    /*else
                    if (data[i] = '采购部门员工') {
                        //采购部门，只显示采购部和通知部内容
                        $('#messageShow').show();
                        $('#proOrderShow').show();

                        //通知部门
                        function messageTable() {
                            tableIns = table.render({
                                elem: '#message'
                                , url: $tool.getContext() + 'allRole/notify.do' //数据接口
                                , method: 'post'
                                , cols: [[ //表头
                                    {field: 'receiverId', title: '发件人', width: 150}
                                    , {field: 'sendTime', title: '发送时间', width: 200}
                                    , {field: 'content', title: '邮件内容'}
                                ]]
                                , done: function (res, curr) {//请求完毕后的回调
                                    // 如果是异步请求数据方式，res即为你接口返回的信息.curr：当前页码
                                }
                            });
                        }

                        messageTable();

                        //采购部门
                        function proOrderTable() {
                            tableIns = table.render({
                                elem: '#proOrder'
                                , url: $tool.getContext() + 'allRole/proOrder.do' //数据接口
                                , method: 'post'
                                , cols: [[ //表头
                                    {field: 'orderNumber', title: '订单编号', width: 150}
                                    , {field: 'orderAuditUser', title: '采购员', width: 150}
                                    , {field: 'goodsId', title: '采购商品', width: 150}
                                    , {field: 'goodsNumber', title: '采购数量', width: 150}
                                    , {field: 'applyTime', title: '采购时间'}
                                ]]
                                , done: function (res, curr) {//请求完毕后的回调
                                    // 如果是异步请求数据方式，res即为你接口返回的信息.curr：当前页码
                                }
                            });
                        }

                        proOrderTable();
                    }*/


    }
    selectRoles();














});