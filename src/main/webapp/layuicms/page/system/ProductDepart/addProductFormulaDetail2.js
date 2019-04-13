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
//监听事件
        var materialId22;
        var goodsName11;

        /*$api.GetMaterialGoods(req,function (res) {
            var data=res.data;
            console.log(data)
        })*/
     /*  $api.getMaterialGoodsById(req,function (res) {
           var data=res.data;
           alert(data.productId);
       })*/
       /* $api.getProduceMaterialById(req,function (res) {
            var data = res.data;

            $('#productName').val(data.productName);
        });*/
    function initGoodsId() {
        $api.GetGoodsName(null,function (res) {
            var data = res.data;
            if (data.length > 0) {
                var html = '<option value="" selected="selected">--请选择--</option>';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i] + '">' + data[i] + '</option>>';
                }
                $('#goodsName').append($(html));
                form.render();
            }
        });
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
        $api.getAllProduct(null,function (res) {
            var data = res.data;
            if (data.length > 0) {
                var html = '<option value="">--请选择--</option>';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].id + '">' + data[i].productName + '</option>>';
                }
                $('#productName').append($(html));
                form.render();
            }
        });
    }

    /**
     * 监听radio选择
     * */
    form.on('radio(goodsTypeFilter)', function (data) {
        //console.log(data.elem); //得到radio原始DOM对象
        var value = data.value;
        if ('原料' === value) {
            $('.product-type').addClass('layui-hide');
            $('.product-type').removeClass('layui-anim-up');
            $('.material-type').removeClass('layui-hide');
            $('.material-type').addClass('layui-anim-up');

        }
        if ('产品' === value) {
            $('.material-type').addClass('layui-hide');
            $('.material-type').removeClass('layui-anim-up');
            $('.product-type').removeClass('layui-hide');
            $('.product-type').addClass('layui-anim-up');
        }
    });




    /**
     * 监听select选择
     * */
    form.on('select(materialFilter)', function(data){
        console.log(data.elem); //得到select原始DOM对象
        /*console.log(data.value); //得到被选中的值
          console.log(data.othis); //得到美化后的DOM对象*/
        var goodsName = $("#goodsName").val()
        var supplierName = $("#supplierName").val()
        if( goodsName !='' && supplierName!=''){
            var req = {
                goodsName:goodsName,
                supplierName:supplierName,
            }
            $api.GetMaterialGoodsIdByName(req,function (res) {
                var data = res.data;
                if (data.length > 0) {
                    $("#materialId").val(data)
                        materialId22=data[0];

                }else{
                    $("#materialId").val('')
                    $("#supplierName").empty();
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
                    layer.msg("该供应商不提供此原料!");
                }
            });
        }
    });

    /**
     * 监听select选择
     * */
    form.on('select(productNameFilter)', function (data) {
        //console.log(data.elem); //得到radio原始DOM对象
        var productName = data.value;
        if(productName != null){
            $("#productId").val(productName)
        }
    });

    /**
     * 初始化页面
     * */
    var  other33;
    var depotId11;
    form.on("select(abb)",function (res) {
        var value= $('#produceFormulaId option:selected').html();
        console.log(value)
        var str=value.split("---");
        other33 = str[0];
        depotId11= str[1];
    })
    function init() {
        initGoodsId()
        $api.getAllFormula(null,function (res) {
            var data = res.data;
            if(data.length > 0){
                var html = '<option value="">--请选择--</option>';
                for(var i=0;i<data.length;i++){
                    html += '<option value="'+data[i].id+'">'+data[i].other1+'---'+data[i].other3+'</option>';
                }
                $('#produceFormulaId').append($(html));
                form.render();
            }
        });


        /*//获取materialGoods
        $api.produceMaterial(null,function (res) {
            var data2= res.data;
            console.log(data2)
            if(data2.length > 0){
                var html = '<option value="">--请选择--</option>';
                for(var i=0;i<data2.length;i++){
                    html += '<option value="'+data2[i].id+'">'+data2[i].id+'---'+data2[i].materialId+'</option>';
                }
                $('#materialId').append($(html));

                form.render();
            }

        });

*/

    }
    init();

    /**
     * 初始化菜单信息
     * */
    /*function initMenuInfo() {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var wid = queryArgs['id'];

        var url = $tool.getContext()+'/formula/get';
        var req = {
            id:wid
        };

        $api.getFormulaById(req,function (res) {
            var data = res.data;
            select();
            // $("[name='productId']").val(data.productId);
            $("[name='formulaType']").val(data.formulaType);
            $("[name='formulaNumber']").val(data.formulaNumber);
            $("[name='createUser']").val(data.createUser);
            $("[name='createTime']").val(data.createTime);

            form.render();//重新绘制表单，让修改生效
        });


    }*/

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

    form.on("submit(add)", function (data) {

        var produceFormulaId = data.field.produceFormulaId;
        var materialId = materialId22;
        var materialNumber = data.field.materialNumber;
        var depotId = depotId11;
        var other2= data.field.goodsName;

        //配方编号
        var other3 = other33

        //请求
        var url = $tool.getContext()+'detail/add';
        var req = {

            produceFormulaId:produceFormulaId,
            materialId:materialId,
            materialNumber:materialNumber,
            depotId:depotId,
            other2:other2,
            other3:other3

        };

        $api.addProduceFormulaDetail(req,function (data) {
            layer.msg("增加成功！",{time:1000},function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });

        return false;
    })

});


