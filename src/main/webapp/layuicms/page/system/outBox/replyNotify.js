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

    var orgId;
    var orgName;
    var roleIdList = new Array();//所有的角色id列表

    /**
     * 页面初始化
     * */
    function init() {
        //初始化机构树
        initOrgTree();
        //加载角色列表
        loadRoleList();
        finsenderId();
        initselectUsers();

    }



    init();


    function initselectUsers() {
        $api.findAll(null,function (res) {
            var data = res.data;
            if (data.length > 0) {
                var html = '<option value="">--请选择收件人--</option>';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].loginName + '">' + data[i].loginName+ '</option>>';
                }
                $('#selectUsers').append($(html));
                form.render();
            }
        });
    }

    $("[name='senderId']").val(window.sessionStorage.getItem('sysUser'));
    /**
     * 初始化组织机构树
     * */

   function finsenderId(){
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var id = queryArgs['id'];

        var req = {
            id:id
        };
    }
    function initOrgTree() {
        //获取所有组织机构树

        $api.GetAllOrg(null,function (res) {
            renderTree('#org-tree', res.data);
        });

    }

    /**
     * 绘制树
     * @param id dom id
     * @param nodes 树节点数据
     * */
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
            }
        });
    }


    /**
     * 加载角色列表
     * */
    function loadRoleList() {
        var req = {
            page: 1,
            limit: 999
        };


        $api.GetRoleList(req,function (res) {
            var data = res.data;
            if (data.length > 0) {
                var roleHtml = "";
                for (var i = 0; i < data.length; i++) {
                    roleHtml += '<input type="checkbox" name="' + data[i].id + '" title="' + data[i].roleName + '">';
                    roleIdList.push(data[i].id);//保存id列表
                }

                $('.role-check-list').append($(roleHtml));
                form.render();//重新绘制表单，让修改生效
            }
        });
    }


    /**
     * 表单提交
     * */
    form.on("submit(addMessage)", function (data) {
        var creater =data.field.creater
        var senderId =data.field.senderId
        var receiverId =  data.field.receiverId;
        var targetId = data.field.targetId;
        var notifyType = '个人信件';
        var title = data.field.title;
        var content = data.field.content;
        alert(senderId)
        var idList = new Array();

     /*   if($tool.isBlank(orgId)||$tool.isBlank(orgName)){
            layer.msg("请选择所属组织机构");
            return false;
        }*/

        //获取选中的角色列表
        for (var i = 0; i < roleIdList.length; i++) {
            if (data.field[roleIdList[i]] === 'on') {
                idList.push(roleIdList[i]);
            }
        }

        //请求
        var req = {
            creater:creater,
            senderId: senderId,
            receiverId: receiverId,
            targetId: targetId,
            notifyType: notifyType,
            title: title,
            content: content
        };

        $api.addSysNotify(req,function (data) {
            //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
            layer.msg("消息发送成功！", {time: 1000}, function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });

        return false;
    })

});


