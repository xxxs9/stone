layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api:'api'
}).use(['form', 'layer', 'tree','$api', 'jquery', 'ajaxExtention', '$tool'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery,
        $tool = layui.$tool,
        $api = layui.$api;

    //var orgId;
   // var orgName;
    //var roleIdList = new Array();//所有的角色id列表

    /**
     * 页面初始化
     * */
    function init() {
        //初始化机构树
        //initOrgTree();
        //加载角色列表
        loadProductIdList();
    }

    init();

    /**
     * 初始化下拉框
     */
    function loadProductIdList() {

        $api.getAllProduct(null,function (res) {
            var data = res.data;
            console.log(data)
            if(data.length > 0){
                var html = '<option value="">--请填写--</option>';
                for(var i=0;i<data.length;i++){
                    html += '<option value="'+data[i]+'">'+data[i].productName+'</option>';
                }
                $('#productId').append($(html));
                form.render();
            }
        });
    }

    /**
     * 表单提交
     * */
    form.on("submit(addShipmentOrder)", function (data) {
        var id = data.field.id;
        var goodsId = data.field.goodsId;
        var productId = data.field.productId;
        var goodsName = data.field.goodsName;
        var customer = data.field.customer;
        var goodsNumber = data.field.goodsNumber;
        var goodsAmount = data.field.goodsAmount;
        var applyUser = data.field.applyUser;
        var applyTime = data.field.applyTime;
        var state = data.field.state;
        var auditUser = data.field.auditUser;
        var auditType = data.field.auditType;
        var remarks = data.field.remarks
      /*  var idList = new Array();

        if($tool.isBlank(orgId)||$tool.isBlank(orgName)){
            layer.msg("请选择所属组织机构");
            return false;
        }

        //获取选中的产品列表
        for (var i = 0; i < roleIdList.length; i++) {
            if (data.field[roleIdList[i]] === 'on') {
                idList.push(roleIdList[i]);
            }
        }
*/
        //请求
        var req = {
            id: id,
            goodsId: goodsId,
            productId: productId,
            goodsName: goodsName,
            customer: customer,
            goodsNumber: goodsNumber,
            goodsAmount: goodsAmount,
            applyUser: applyUser,
            applyTime: applyTime,
            state: state,
            auditUser: auditUser,
            auditType: auditType,
            remarks: remarks

        };

        $api.AddShipmentOrder(req,function (data) {
            //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
            layer.msg("用户添加成功！", {time: 1000}, function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
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


