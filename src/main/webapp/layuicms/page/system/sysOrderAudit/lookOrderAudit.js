layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api:'api'
}).use(['form', 'layer','$api', 'tree', 'jquery', 'ajaxExtention', '$tool'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery,
        $tool = layui.$tool,
        $api = layui.$api;

    var orgId;
    var orgName;
    var roleIdList = new Array();//所有的角色id列表
    var user_roleIds =[];//用户所属角色列表

    /**
     * 页面初始化
     * */
    function init() {
        //初始化机构树
      //  initOrgTree();
        //初始化用户信息

    }

    init();

    /**
     * 初始化组织机构树
     * */

            /**
             * 初始化用户信息
             * */
            function initUserInfo() {
                var queryArgs = $tool.getQueryParam();//获取查询参数
                var id = queryArgs['id'];

                var url = $tool.getContext()+'marker/get';
                var req = {
                    id: id
                };

                $api.GetOrderAudit(req, function (res) {
                    var data = res.data;
                    console.log(data)
                    //$("[name='id']").val(data.id);
                    $("[name='orderId']").val(data.orderId);
                    $("[name='orderTime']").val(data.orderTime);
                    $("[name='productId']").val(data.productId);
                    $("[name='customer']").val(data.customer);
                    $("[name='deliverNumber']").val(data.deliverNumber);
                    $("[name='plannedNumber']").val(data.plannedNumber);
                    $("[name='acceptedAmount']").val(data.acceptedAmount);
                    $("[name='applyUser']").val(data.applyUser);
                    $("[name='state']").val(data.state);
                    $("[name='orderAuditUser']").val(data.orderAuditUser);
                    $("[name='orderAuditDepot']").val(data.orderAuditDepot);
                    $("[name='remarks']").val(data.remarks);
                    $("[name='depotRemarks']").val(data.depotRemarks);
                    /*orgId = data.orgId;
                    orgName = data.orgName;*/
                   // user_roleIds = data.roleIdList;//保存用户所属角色id列表，初始化选中时用
                    //加载角色列表
                  //  loadRoleList();
                    form.render();//重新绘制表单，让修改生效
                });
            }
                    initUserInfo();
            /**
             * 表单提交
             * */
            form.on("submit(editOrderAudit)", function (data) {
                var queryArgs = $tool.getQueryParam();//获取查询参数
                var id = queryArgs['id'];
                console.log(data)
              //  var id = data.field.id;
                var orderId = data.field.orderId;
                var orderTime = data.field.orderTime;
                var productId = data.field.productId;
                var customer = data.field.customer;
                var deliverNumber = data.field.deliverNumber;

                var plannedNumber = data.field.plannedNumber;
                var acceptedAmount = data.field.acceptedAmount;

                var applyUser = data.field.applyUser;
                var state = data.field.state;
                var orderAuditUser = data.field.orderAuditUser;
                var orderAuditDepot = data.field.orderAuditDepot;
                var remarks = data.field.remarks
                var depotRemarks = data.field.depotRemarks
                /*if ($tool.isBlank(orgId) || $tool.isBlank(orgName)) {
                    layer.msg("请选择所属组织机构");
                    return false;
                }*/

                //获取选中的产品列表
               /* for (var i = 0; i < roleIdList.length; i++) {
                    if (data.field[roleIdList[i]] === 'on') {
                        idList.push(roleIdList[i]);
                    }
                }*/

                //请求
                var req = {
                    id:id,
                    orderId: orderId,
                    orderTime: orderTime,
                    productId: productId,
                    customer: customer,
                    deliverNumber: deliverNumber,
                    plannedNumber: plannedNumber,
                    acceptedAmount: acceptedAmount,
                    applyUser: applyUser,
                    state: state,
                    orderAuditUser: orderAuditUser,
                    orderAuditDepot: orderAuditDepot,
                    remarks: remarks,
                    depotRemarks: depotRemarks
                };

                $api.updateOrderAudit(req, function (data) {
                    //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
                   // layer.msg("审核成功！", {time: 1000}, function () {
                        layer.closeAll("iframe");
                        //刷新父页面
                        parent.location.reload();

                });

                return false;
            })

    layui.use('laydate', function(){
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#test1' //指定元素
            ,type: 'datetime'
        });
    });

        });


