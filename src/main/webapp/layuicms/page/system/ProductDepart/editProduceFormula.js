layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention:'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool:'tool',
    $api:'api'
}).use(['form', 'layer', 'jquery','ajaxExtention','$tool','$api'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery,
        $tool = layui.$tool,
        $api = layui.$api;



    /**
     * 初始化页面
     * */
    function init() {
        //初始化菜单信息
        initMenuInfo();

    }
    init();

    /**
     * 初始化菜单信息
     * */
    function initMenuInfo() {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var wid = queryArgs['id'];

        var url = $tool.getContext()+'/formula/get';
        var req = {
            id:wid
        };

        $api.getFormulaById(req,function (res) {
            var data = res.data;
            select(data.productId);
            // $("[name='productId']").val(data.productId);
            $("[name='formulaType']").val(data.formulaType);
            $("[name='formulaNumber']").val(data.formulaNumber);
            $("[name='createUserId']").val(data.createUserId);
            form.render();//重新绘制表单，让修改生效
        });

        function select() {
            $api.getAllProduct(null, function (res) {
                var data2 = res.data;
                if (data2.length > 0) {
                    var html = '<option value="">--请选择--</option>';
                    for (var i = 0; i < data2.length; i++) {
                        html += '<option value="' + data2[i].id + '">' + data2[i].id + '-------' + data2[i].productName+ '</option>>';
                    }
                    $('#parentMenu').append($(html));
                    //$('#parentMenu').val(hh);
                    form.render();
                }

            });

        }
    }

    /**
     * 加载角色列表
     * */
   /* function loadRoleList() {
        var url = $tool.getContext()+'role/roleList.do';
        var req =  {
            page:1,
            limit:999
        };

        $api.GetRoleList(req,function (res) {
            var data = res.data;
            if(data.length > 0){
                var roleHtml = "";
                for(var i = 0;i<data.length;i++){
                    //是否初始化选中
                    if($.inArray(data[i].id, menu_roleIds) != -1){
                        roleHtml += '<input type="checkbox" checked name="'+data[i].id+'" title="'+data[i].roleName+'">';
                    }else{
                        roleHtml += '<input type="checkbox" name="'+data[i].id+'" title="'+data[i].roleName+'">';
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
     * 表单提交
     * */
    form.on("submit(editMenu)", function (data) {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        console.log(queryArgs)
        var productId = data.field.productId;
        var formulaType = data.field.formulaType;
        var formulaNumber = data.field.formulaNumber;
        var createUserId = data.field.createUserId;

        /*var sort = data.field.sort;
        var idList = new Array();*/

        //获取选中的角色列表
        /*for(var i=0;i<roleIdList.length;i++){
            if(data.field[roleIdList[i]] === 'on'){
               idList.push(roleIdList[i]);
            }
        }
*/
        //请求
        var url = $tool.getContext()+'/formula/update';
        var req = {
            id:queryArgs['id'],
            productId:productId,
            formulaType:formulaType,
            formulaNumber:formulaNumber,
            createUserId:createUserId

        };

        $api.udpFormula(req,function (data) {
            layer.msg("修改成功！",{time:1000},function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });

        return false;
    })

});


