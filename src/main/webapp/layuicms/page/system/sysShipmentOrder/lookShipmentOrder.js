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

                $api.GetShipmentOrder(req, function (res) {
                    var data = res.data;
                    console.log(data)
                    //$("[name='id']").val(data.id);
                    $("[name='shipmentId']").val(data.shipmentId);
                    $("[name='goodsName']").val(data.goodsName);
                    $("[name='customer']").val(data.customer);
                    $("[name='quantityNumber']").val(data.quantityNumber);
                    $("[name='deliveryTime']").val(data.deliveryTime);
                    $("[name='receivingAddress']").val(data.receivingAddress);
                    $("[name='phone']").val(data.phone);
                    $("[name='consignee']").val(data.consignee);
                    $("[name='receivingTime']").val(data.receivingTime);
                    $("[name='state']").val(data.state);
                    $("[name='remarks']").val(data.remarks)


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
            form.on("submit(look)", function (data) {
                var queryArgs = $tool.getQueryParam();//获取查询参数
                var id = queryArgs['id'];
                console.log(data)
                var id = data.field.id;
                var shipmentId= data.field.shipmentId;
                var goodsName = data.field.goodsName;
                var customer = data.field.customer;
                var quantityNumber = data.field.quantityNumber;
                var deliveryTime = data.field.deliveryTime;
                var receivingAddress = data.field.receivingAddress;
                var phone = data.field.phone;
                var consignee = data.field.consignee;
                var receivingTime= data.field.receivingTime;
                var state = data.field.state;
                var remarks = data.field.remarks


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
                    id: id,
                    shipmentId: shipmentId,
                    goodsName: goodsName,
                    customer: customer,
                    quantityNumber: quantityNumber,
                    deliveryTime: deliveryTime,
                    receivingAddress: receivingAddress,
                    phone: phone,
                    consignee: consignee,
                    receivingTime: receivingTime,
                    state: state,
                    remarks: remarks
                };

                $api.updateShipmentOrder(req, function (data) {
                    //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
                   // layer.msg("用户更新成功！", {time: 1000}, function () {
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
    layui.use('laydate', function(){
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#test2' //指定元素
            ,type: 'datetime'
        });
    });


        });


