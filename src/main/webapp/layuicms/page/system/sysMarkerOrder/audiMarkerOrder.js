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
   /* function initOrgTree() {
        //获取所有组织机构树

          $api.GetAllOrg(null,function (res) {
              renderTree('#org-tree', res.data);
          });

      }

        /!**
         * 绘制树
         * @param id dom id
         * @param nodes 树节点数据
         * *!/
        function renderTree(id, nodes) {
            //绘制前先清空
            $(id).empty();

            //绘制
             layui.tree({
                 elem: id
                 , nodes: nodes
                 , click: function (node) {//显示组织机构数据
                     console.log(node); //node即为当前点击的节点数据
                     orgId = node.id;//保存机构id
                     orgName = node.name;

                     $('[name="orgName"]').val(orgName);//显示机构名称
                 }
             });
         }
            /!**
             * 加载产品列表
             * *!/
            function loadRoleList() {
                var req = {
                    page: 1,
                    limit: 999
                };

                $api.GetProduct(req, function (res) {
                    var data = res.data;
                    if (data.length > 0) {
                        var roleHtml = "";
                        for (var i = 0; i < data.length; i++) {
                            //是否初始化选中
                            if ($.inArray(data[i].id, user_roleIds) != -1) {
                                roleHtml += '<input type="checkbox" checked name="' + data[i].id + '" title="' + data[i].roleName + '">';
                            } else {
                                roleHtml += '<input type="checkbox" name="' + data[i].id + '" title="' + data[i].roleName + '">';
                            }
                            roleIdList.push(data[i].id);//保存id列表
                        }

                        $('.role-check-list').append($(roleHtml));
                        form.render();//重新绘制表单，让修改生效
                    }
                });
            }
*/
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

                $api.GetMarkerOrder(req, function (res) {
                    var data = res.data;
                    console.log(data)
                    //$("[name='id']").val(data.id);
                    $("[name='orderId']").val(data.orderId);
                    $("[name='orderTime']").val(data.orderTime);
                    $("[name='customer']").val(data.customer);
                    $("[name='deliverNumber']").val(data.deliverNumber);
                    $("[name='currentNumber']").val(data.currentNumber);
                    $("[name='plannedNumber']").val(data.plannedNumber);
                    $("[name='acceptedAmount']").val(data.acceptedAmount);
                    $("[name='unpaidAmount']").val(data.unpaidAmount);
                    $("[name='applyUser']").val(data.applyUser);
                    $("[name='state']").val(data.state);
                    $("[name='orderAuditUser']").val(data.orderAuditUser);
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
            form.on("submit(editMarkerOrder)", function (data) {
                var queryArgs = $tool.getQueryParam();//获取查询参数
                var id = queryArgs['id'];
                console.log(data)

                var orderId = data.field.orderId;
                var orderTime = data.field.orderTime;
                var customer = data.field.customer;
                var deliverNumber = data.field.deliverNumber;
                var currentNumber = data.field.currentNumber;
                var plannedNumber = data.field.plannedNumber;
                var acceptedAmount = data.field.acceptedAmount;
                var unpaidAmount = data.field.unpaidAmount;
                var applyUser = data.field.applyUser;

                var state = data.field.state;
                var orderAuditUser = data.field.orderAuditUser;
                var remarks = data.field.remarks


                //请求
                var req = {
                    id:id,
                    orderId: orderId,
                    orderTime: orderTime,
                    customer: customer,
                    deliverNumber: deliverNumber,
                    currentNumber: currentNumber,
                    plannedNumber: plannedNumber,
                    acceptedAmount: acceptedAmount,
                    unpaidAmount: unpaidAmount,
                    applyUser: applyUser,

                    state: state,
                    orderAuditUser: orderAuditUser,
                    remarks: remarks
                };

                $api.updateMarkerOrder(req, function (data) {
                    //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
                    layer.msg("提交成功！", {time: 1000}, function () {
                        layer.closeAll("iframe");
                        //刷新父页面
                        parent.location.reload();
                    });
                });

                return false;
            })

        });


