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
        //初始化信息
        initMaterialInfo();
    }
    init();

    /**
     * 初始化下拉框
     *
    function initSupplierName() {
        $api.GetSupplierName(null,function (res) {
            var data = res.data;
            if (data.length > 0) {
                var html = '<option value="">--请选择--</option>';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i] + '">' + data[i] + '</option>>';
                }
                $('#supplierName').append($(html));
                form.render();
            }
        });
    }
     */

    /**
     * 动态设置下拉框
     *

    function setOption(optionValue) {
        var all_options = $('#supplierName').options;
        for (i=0; i<all_options.length; i++){
            if (all_options[i].value == optionValue)  // 根据option标签的ID来进行判断  测试的代码这里是两个等号
            {
                all_options[i].selected = true;
            }
        }
    };
     */
    /**
     * 初始化菜单信息
     * */
    function initMaterialInfo() {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var id = queryArgs['id'];
        var url = $tool.getContext()+'material/get.do';
        var req = {
            id:id
        };

        $api.GetMaterial(req,function (res) {
            var data = res.data;
            $("[name='goodsName']").val(data.goodsName);
            $("[name='goodsType']").val(data.goodsType);
            $("[name='goodsDescribe']").val(data.goodsDescribe);
            $("[name='goodsSpecification']").val(data.goodsSpecification);
            form.render();//重新绘制表单，让修改生效
        });
    }


    /**
     * 表单提交
     * */
    form.on("submit(editMaterial)", function (data) {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var goodsName = data.field.goodsName;
        var goodsType = data.field.goodsType;
        var goodsDescribe = data.field.goodsDescribe;
        var goodsSpecification = data.field.goodsSpecification;

        //请求
        var url = $tool.getContext()+'material/update.do';
        var req = {
            id:queryArgs['id'],
            goodsName:goodsName,
            goodsType: goodsType,
            goodsDescribe: goodsDescribe,
            goodsSpecification:goodsSpecification,
        };

        $api.UpdateMaterial(req),{contentType:'application/json;charset=utf-8'},function (data) {
            layer.msg("修改成功！", {time: 1000}, function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        }
        });

});


