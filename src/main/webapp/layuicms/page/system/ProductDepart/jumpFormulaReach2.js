layui.config({
    base: $config.resUrl + 'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api: 'api'
}).use(['form', 'layer', 'jquery', 'ajaxExtention', '$tool', '$api','laydate'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery,
        $tool = layui.$tool,
        $api = layui.$api;

    layui.use('laydate', function () {
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#reachTime',//指定元素
            type: 'date',
            format: 'yyyy-MM-dd'
        });
    });

    /**
     * 页面初始化
     * */

    form.verify({
        pi: [
            /^[0-9]*[1-9][0-9]*$/  //正则表达式
            ,'数量必须为大于0的正整数'  //提示信息
        ]
    });
    function init() {
        //初始化下拉框
        initMenuInfo();
        selectProduct();
       // select4();
        select7(oth);

        //select5();

        //select6( $("#produceFormulaId").val());

        // selectFormula()
        $("[name='reachUser']").val(window.sessionStorage.getItem('sysUser'));
    }

    init();
    /**
     * 挑选成品配方
     */
    /* function selectFormula(){
         $api.getAllFormula(null,function (res) {
             var html = '<input type="checkbox" name="" title="写作" checked>';
             $('#myform').append($(html))
            var data4 = res.data;
            if (data4.length>0){
                for (var i=0;i<data4.length ;i++){

                }
            }


         });
     }
 */
    form.on('select(menuTypeFilter1)', function (id4) {
        //alert(id4.value);
        console.log(id4.value);
        var req = {
            id: id4.value
        }

        $api.getProduceFormulaDetailByFormulaId(req, function (res) {
            var data = res.data;
            if (data.length > 0) {
                var html = '<option value="">--请选择--</option>';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].id + '">' + data[i].other1 + '</option>';
                    html2 = data[i].materialId;
                }
                $('#produceFormulaDetailId').append($(html));
                // $('#produceFormulaDetailId').val(formulaId);
                $('#other2').val(html2);
                // $('#state').val(hh);
                form.render();
            }
        });

    });


    /**
     * 表单提交
     * */


    var wid = 0;
    var productId;
    function selectProduct() {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var id = queryArgs['id'];
        var req = {
            id: id
        }
        productId=id;

        $('#productId').val(id)
        $api.getProducePlanById(req, function (res) {

            var data2 = res.data;
            wid = data2.productId;


            // $('#produceFormulaId1').val(hh);



            function aa(wid) {
                var reqs = {
                    id: wid
                }

                $api.getProductById(reqs, function (res2) {
                    var data3 = res2.data

                    $('#productName').val(data3.productName)

                });

                /*  $api.ProductInfoById(reqs,function(res2){
                      var data4= res2.data
                      console.log(data4);
                      $('#productName').val(data4.productName)

                  });*/
            }

            aa(wid)

        });

        //console.log($(wid))
        form.render();

    }


    /* function select2(hh) {
         $api.getProductWasteById(null, function (res) {
             var data3 = res.data;
             if (data3.length > 0) {
                 var html = '<option value="">--请选择--</option>';
                 for (var i = 0; i < data3.length; i++) {
                     html += '<option value="' + data3[i].state + '">' + data3[i].state + '</option>>';
                 }
                 $('#state1').append($(html));
                 $('#state1').val(hh);
                 form.render();
             }

         });

     }*/
    var oth;

    function initMenuInfo() {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var id = queryArgs['id'];

        var url = $tool.getContext() + 'product/get';
        var req = {
            id: id
        };

        $api.getProductById(req, function (res) {
            var data = res.data;
            $("[name='state']").val(data.productState);
            $("[name='productName']").val(data.productName);
            $("[name='pId']").val(data.other1);

            oth=data.other3;




            form.render();//重新绘制表单，让修改生效
        });


    }
   //获取produceFormulaId
   // select7(oth);


        function func(id4) {
        alert(id4)
            var req = {
                id: id4
            }

            $api.getProduceFormulaDetailByFormulaId(req, function (res) {
                var data = res.data;
                if (data.length > 0) {
                    var html = '<option value="">--请选择--</option>';
                    for (var i = 0; i < data.length; i++) {
                        html += '<option value="' + data[i].id + '">' + data[i].id + '</option>';
                        html2 = data[i].materialId;
                    }
                    $('#produceFormulaDetailId').append($(html));
                    // $('#produceFormulaDetailId').val(formulaId);
                    $('#other2').val(html2);
                    // $('#state').val(hh);
                    form.render();
                }
            });
        }

    function select4() {
        $api.getAllFormula(null, function (res) {
            var data = res.data;
            if (data.length > 0) {
                var html = '<option value="">--请选择--</option>';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].id + '">' + data[i].other1 + '</option>';

                }
                $('#produceFormulaId').append($(html));
                // $('#state').val(hh);
                form.render();
            }

        });

    }

    function select5() {
        $api.getProduceFormulaDetail(null, function (res) {
            var data = res.data;
            if (data.length > 0) {
                var html = '<option value="">--请选择--</option>';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].id + '">' + data[i].other1 + '</option>';
                    html2 = data[i].materialId;


                }
                $('#produceFormulaDetailId').append($(html));
                $('#other2').val(html2);
                // $('#state').val(hh);
                form.render();
            }

        });
    }

    //通过formulaId查找formulaDetail
    function select6(formulaId) {
        var req = {
            id: formulaId
        }

        $api.getProduceFormulaDetailByFormulaId(req, function (res) {
            var data = res.data;
            if (data.length > 0) {
                var html = '<option value="">--请选择--</option>';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].id + '">' + data[i].id + '</option>';
                    html2 = data[i].materialId;
                }
                $('#produceFormulaDetailId').append($(html));
               // $('#produceFormulaDetailId').val(formulaId);
                $('#other2').val(html2);
                // $('#state').val(hh);
                form.render();
            }
        });
    }
    function select7(id) {

        var req={
            id:id
        }
        $api.getByProductId(req,function (res) {
            var data = res.data;

            if (data.length > 0) {
                var html = '<option value="">--请选择--</option>';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].id + '">' + data[i].other1 + '</option>';

                }
                $('#produceFormulaId').append($(html));
                // $('#state').val(hh);
                form.render();
            }

        });

    }

    /**
     * 表单提交
     * */
    var re;
    var re1;
    function checkReachId() {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var id = queryArgs['id'];
        re=id;
        var req={
            productId:id
        }
        $api.getReachByProductId1(req,function (res) {
            var data = res.data;
            console.log(res);
            re1=data.productId;
        })

    }
    var id2;



    form.on("submit(addMenu)", function (data) {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var id = queryArgs['id'];
        var other2 = data.field.other2;
        var productId = data.field.productId;
        var produceFormulaId = data.field.produceFormulaId;
        var produceFormulaDetailId = data.field.produceFormulaDetailId;

        var depotAudi = data.field.depotAudi;
        var state = data.field.state;
        var formulaBack = data.field.formulaBack;
        var reachUser = data.field.reachUser;
        var reachTime = data.field.reachTime;
        var reachNumber = $("#reachNumber").val();


        //请求
        var url = $tool.getContext() + '/reach/add';
        var req = {
            id: id,
            productId: productId,
            produceFormulaDetailId: produceFormulaDetailId,
            depotAudi: depotAudi,
            state: state,
            produceFormulaId: produceFormulaId,
            formulaBack: formulaBack,
            reachUser: reachUser,
            reachTime: reachTime,
            other1: reachNumber,
            other2:other2
        };

        id2 = productId;

        $api.addFormulaReach(req, function (data) {
            /* startProduce(id2);*/
            layer.msg(">>>>>>>领料单已生,等待仓库审核<<<<<<<", {time: 1500}, function () {

                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });

        });


        return false;

    });



    //判断是否出库
    var  flag;
    function chuKu() {
        var queryArgs = $tool.getQueryParam();//获取查询参数
        var id =queryArgs['id'];
       var req ={
            productId:id
        }
        $api.getReachByProductId(req,function (res) {
            var data  = res.data;
            var reachId = data.id;

            /*flag = res.data;
            console.log('flag:'+flag);*/
            isStorgeOut(reachId);
        });



    }
    function isStorgeOut(id) {
        var req0={
            id:id
        }
        $api.IsStorageOut(req0,function (res) {
            flag=res.data;
            return flag;

        })

    }

    function startProduce(id) {

        var req = {
            id:id
        }
        $api.startProduce(req,function (res) {

        })
    }
});


