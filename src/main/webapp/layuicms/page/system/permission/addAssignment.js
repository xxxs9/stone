layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api:'api'
}).use(['form', 'layer', 'jquery', 'ajaxExtention', '$tool','$api'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery,
        $tool = layui.$tool ,
        $api = layui.$api;

    var roleIdList = new Array();//所有的角色id列表
    /**
     * 页面初始化
     * */
    function init() {
        //初始化下拉框
        initParentMenu();
        //加载角色列表
        loadRoleList()
    }

    init();

    /**
     * 初始化下拉框
     * */
    function initParentMenu() {
        $api.initBelongTo(null,function (res) {
            var data = res.data;
            if (data.length > 0) {
                var html = '<option value="">--请选择--</option>';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].id + '">' + data[i].name + '</option>>';
                }
                $('#belongToName').append($(html));
                form.render();
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
     * 监听radio选择
     * */
    form.on('radio(menuTypeFilter)', function (data) {
        //console.log(data.elem); //得到radio原始DOM对象
        var value = data.value;
        if ('2' === value) {//二级菜单
            $('.parent-menu').removeClass('layui-hide');
            $('.parent-menu').addClass('layui-anim-up');
        }else{
            $('.parent-menu').addClass('layui-hide');
            $('.parent-menu').removeClass('layui-anim-up');
        }
    });

    /**
     * 表单提交
     * */
    form.on("submit(addPerms)", function (data) {

        var parentId = data.field.belongToName;
        var name = data.field.name;
        var perms = data.field.perms;
        var idList = new Array();

        //获取选中的角色列表
        for (var i = 0; i < roleIdList.length; i++) {
            if (data.field[roleIdList[i]] === 'on') {
                idList.push(roleIdList[i]);
            }
        }

        //请求
        var belongToName = $("#belongToName").find("option:selected").text();;
        var req = {
            parentId:parentId,
            name: name,
            perms: perms,
            roleIdList: idList,
            belongToName:belongToName
        };
        $api.addPerms(JSON.stringify(req),{contentType:"application/json;charset=UTF-8"},function (data) {
            //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)

            layer.msg("权限添加成功！", {time: 1000}, function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });

        return false;
    })

});


