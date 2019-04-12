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
    /**
     * 页面初始化
     * */
    function init() {
        //初始化下拉框
        initGoodsId();
    }

    init();


    function initGoodsId() {
        $api.GetGoodsName(null,function (res) {
            var data = res.data;
            if (data.length > 0) {
                var html = '<option value="">--请选择--</option>';
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
        $api.selectGoodsProduct(null,function (res) {
            var data = res.data;
            if (data.length > 0) {
                var html = '<option value="">--请选择--</option>';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].bianhao + '">' + data[i].pname + '</option>>';
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
                    $("#materialId").val(data[0])
                    var goodsId = data[0]
                    var req1 = {
                        goodsId:goodsId,
                    }
                    $api.GetDepotInventoryByGoodsId(req1,function (res) {
                        var result = res.data;
                        if(result == null){
                            $("#saleableNumber").html("无库存");
                            $("#shipmentsNumber").html("无库存");
                        }else{
                            $("#saleableNumber").html(result.saleableNumber);
                            $("#shipmentsNumber").html(result.shipmentsNumber);
                        }
                    });
                }else{
                    $("#materialId").val('')
                    $("#saleableNumber").html("");
                    $("#shipmentsNumber").html("");
                    $("#supplierName").empty();
                    $("#goodsName").empty();
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
                    layer.msg("该供应关系不存在!请重新选择！");
                }
            });
        }else if(goodsName !='' && supplierName ==''){
            $("#saleableNumber").html('');
            $("#shipmentsNumber").html('');
            $("#supplierName").empty();
            var req ={
                goodsName:goodsName,
            }
            $api.GetSupplierNameByGoodsName(req,function (res) {
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
        }else if(goodsName =='' && supplierName !=''){
            $("#saleableNumber").html('');
            $("#shipmentsNumber").html('');
            $("#goodsName").empty();
            var req ={
                supplierName:supplierName,
            }
            $api.GetGoodsNameBySupplierName(req,function (res) {
                var data = res.data;
                if (data.length > 0) {
                    var html = '<option value="">--请选择--</option>';
                    for (var i = 0; i < data.length; i++) {
                        html += '<option value="' + data[i] + '">' + data[i] + '</option>>';
                    }
                    $('#goodsName').append($(html));
                    form.render();
                }
            });
        }else{
            $("#saleableNumber").html('');
            $("#shipmentsNumber").html('');
            $("#supplierName").empty();
            $("#goodsName").empty();
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
        }
    });

    /**
     * 监听select选择
     * */
    form.on('select(productNameFilter)', function (data) {
        //console.log(data.elem); //得到radio原始DOM对象
        var goodsId = data.value;
        if(goodsId != null){
            $("#productId").val(goodsId)
        }
        //请求
        var req = {
            goodsId:goodsId
        };

        $api.GetDepotInventoryByGoodsId(req,function (res) {
            var data = res.data;
            if(data == null){
                $("#saleableNumber").html("无库存");
                $("#shipmentsNumber").html("无库存");
            }else{
                $("#saleableNumber").html(data.saleableNumber);
                $("#shipmentsNumber").html(data.shipmentsNumber);
            }

        });

    });

    /**
     * 表单提交
     * */
    form.on("submit(addDepotOrderOut)", function (data) {
        var goodsId;
        if(data.field.goodsType === '原料'){
            goodsId = data.field.materialId;
        }
        if(data.field.goodsType === '产品'){
            goodsId = data.field.productId;
        }
        var type = data.field.type;
        var goodsNumber = data.field.goodsNumber;

        //请求
        var req = {
            goodsId:goodsId,
            type: type,
            goodsNumber: goodsNumber
        };

        $api.AddDepotOrderOut(req,function (data) {
            //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
            layer.msg("出库单添加成功！", {time: 1000}, function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });

        return false;
    })

});


