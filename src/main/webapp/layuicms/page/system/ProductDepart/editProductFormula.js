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



    layui.use('laydate', function(){
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#test1' ,//指定元素
            type:'date',

            format:'yyyy-MM-dd'
        });
    });



    /**
     * 初始化页面
     * */
    var other32;
    var productId1;
    function init() {
       /* $api.getAllProduct(null,function (res) {
            var data = res.data;
            if(data.length > 0){
                var html = '<option value="">--请选择--</option>';
                for(var i=0;i<data.length;i++){
                    html += '<option value="'+data[i].id+'">'+data[i].other1+'---'+data[i].productName+'</option>';
                }
                $('#productId').append($(html));
                form.render();
            }
        });*/

        $api.selectGoodsProduct(null,function (res) {
            var data = res.data;
            if(data.length > 0){
                var html = '<option value="">--请选择--</option>';
                for(var i=0;i<data.length;i++){
                    html += '<option value="'+data[i].bianhao+'">'+data[i].bianhao+'---'+data[i].pname+'</option>';
                }
                $('#productId').append($(html));

                form.render();
            }
        });
        $('#creatUser').val(window.sessionStorage.getItem('sysUser'));

    }
    init();
    initMenuInfo();
        form.on('select(aa)',function qqq() {
            var value =$('#productId option:selected').html();
            var str=value.split("---");
            other32 = str[1];
            productId1=str[0];
            console.log(productId1);
    });

    function selectFormulaType(hh) {
        $api.getAllFormula(null, function (res) {
            var data3 = res.data;
            if (data3.length > 0) {
                var html = '';
                for (var i = 0; i < data3.length; i++) {
                    html += '<option value="' + data3[i].formulaType + '"></option>';
                }

                $('#formulaType').append($(html));
                $('#formulaType').val(hh);
                form.render();
            }

        });

    }



    /**
     * 初始化菜单信息
     * */
    var id1;
    function initMenuInfo() {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var wid = queryArgs['id'];
        id1=wid;
        var url = $tool.getContext()+'/formula/get';
        var req = {
            id:wid
        };

        $api.getFormulaById(req,function (res) {
            var data = res.data;
            $("[name='productId']").val(data.productId);
            $("[name='other1']").val(data.other1);

            selectFormulaType(data.formulaType);

            $("[name='formulaNumber']").val(data.formulaNumber);
            $("[name='createUser']").val(data.createUser);
            form.render();//重新绘制表单，让修改生效
        });


    }


    /**
     * 表单提交
     * */

    form.on("submit(add)", function (data) {
        var id=id1;
        var productId = productId1;
        var formulaType = data.field.formulaType;
        var formulaNumber = data.field.formulaNumber;
        var createUser = data.field.createUser;
        var other3 = other32;
        var other1= data.field.other1;


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
        var url = $tool.getContext()+'/formula/add';
        var req = {
            id:id,
            productId:productId,
            formulaType:formulaType,
            formulaNumber:formulaNumber,
            createUser:createUser,
            other3:other3,
            other1:other1

        };

        $api.udpFormula(req,function (data) {
            layer.msg("更改成功！",{time:1500},function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });

        return false;
    })

});


