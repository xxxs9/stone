layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api:'api'
}).use(['form', 'layer', 'jquery', 'table', 'laypage', 'ajaxExtention','$tool','$api'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery,
        laypage = layui.laypage,
        $tool = layui.$tool,
        table = layui.table,
        $api = layui.$api;

    var tableIns1;//表格实例
    var tableIns2;//表格实例


    var myChart1 = echarts.init(document.getElementById('chartmain1'));
    var myChart2 = echarts.init(document.getElementById('chartmain2'));

    function init() {
        //初始化表
        initChart();
        initTable();
    }
    init();


    function initChart() {
        myChart1.setOption({
            title : {
                text: '产品库存',
                subtext: 'Product Inventory'
            },
            tooltip : {
                trigger : 'axis',
                showDelay : 0, // 显示延迟，添加显示延迟可以避免频繁切换，单位ms
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                },
                formatter: function (params){
                    return params[0].name + '<br/>'
                        + params[0].seriesName + ' : ' + params[0].value + '<br/>'
                        + params[1].seriesName + ' : ' + params[1].value + '<br/>'
                        +'库存总数'+' : ' +(parseInt(params[1].value) + parseInt(params[0].value)) + '<br/>'                              ;
                }
            },
            legend: {
                data:['可适配数量', '待发货数量']
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            grid: {y: 70, y2:30, x2:20},

            xAxis : [{
                type : 'category',
                data : [],
                axisLabel:{
                    textStyle:{
                        color:"#222"
                    }
                }
            }],
            yAxis : [{
                type : 'value'
            }],
            series : [
                {
                    name:'可适配数量',
                    type:'bar',
                    stack: '总量',
                    itemStyle: {
                        normal: {
                            color: function(params) {
                                // build a color map as your need.
                                var colorList = [
                                    '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
                                    '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
                                    '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
                                ];
                                return colorList[params.dataIndex]
                            }
                        }
                    },
                    data:[]
                },
                {
                    name:'待发货数量',
                    type:'bar',
                    stack: '总量',
                    itemStyle: {
                        normal: {
                            color: '#fff',
                            barBorderColor: 'tomato',
                            barBorderWidth: 1,
                            barBorderRadius:0,
                        }
                    },
                    /*itemStyle : { normal: {label : {show: true, position: 'insideTop',textStyle:{color:'#000'}}}},*/
                    data:[]
                }
            ]
        })
        myChart2.setOption({
            title : {
                text: '原料库存',
                subtext: 'Material Inventory'
            },
            tooltip : {
                trigger : 'axis',
                showDelay : 0, // 显示延迟，添加显示延迟可以避免频繁切换，单位ms
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                },
                formatter: function (params){
                    return params[0].name + '<br/>'
                        + params[0].seriesName + ' : ' + params[0].value + '<br/>'
                        + params[1].seriesName + ' : ' + params[1].value + '<br/>'
                        +'库存总数'+' : ' +(parseInt(params[1].value) + parseInt(params[0].value)) + '<br/>'                              ;
                }
            },
            legend: {
                data:['可适配数量', '待发货数量']
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            grid: {y: 70, y2:30, x2:20},

            xAxis : [{
                type : 'category',
                data : [],
                axisLabel:{
                    textStyle:{
                        color:"#222"
                    }
                }
            }],
            yAxis : [{
                type : 'value'
            }],
            series : [
                {
                    name:'可适配数量',
                    type:'bar',
                    stack: '总量',
                    itemStyle: {
                        normal: {
                            color: function(params) {
                                // build a color map as your need.
                                var colorList = [
                                    '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
                                    '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
                                    '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
                                ];
                                return colorList[params.dataIndex]
                            }
                        }
                    },
                    data:[]
                },
                {
                    name:'待发货数量',
                    type:'bar',
                    stack: '总量',
                    itemStyle: {
                        normal: {
                            color: '#fff',
                            barBorderColor: 'tomato',
                            barBorderWidth: 1,
                            barBorderRadius:0,
                        }
                    },
                    /*itemStyle : { normal: {label : {show: true, position: 'insideTop',textStyle:{color:'#000'}}}},*/
                    data:[]
                }
            ]
        })
    }

    function initTable() {
        //库存表1
        var type1 = '产品';

        tableIns1 = table.render({
            id: 'testReload1'//配置动态表格id以便于执行重载操作
            ,elem: '#depotInventory-data1'
            , height: 'auto'
            , limit: '5'
            , url: $tool.getContext() + 'depotInventory/depotInventoryList.do?type='+type1 //数据接口
            , method: 'post'
            , page:true //开启分页
            , cols: [[ //表头
                {type:'numbers',title:'',fixed: 'left',width: '2.5%'}
                , {field: 'goodsId', title: '货物ID', width: '19.5%'}
                , {field: 'goodsName', title: '货物名称', width: '19.5%'}
                , {field: 'goodsNumber', title: '货品数量', width: '19.5%'}
                , {field: 'shipmentsNumber', title: '待发货数量', width: '19.5%'}
                , {field: 'saleableNumber', title: '可销售数量', width: '19.5%'}
            ]]
            , done: function (res, curr,count) {//请求完毕后的回调
                var data = res.data;
                var productNameList = new Array();
                var shipmentsNumberList = new Array();
                var saleableNumberList = new Array();
                for(var i in data) {
                    productNameList.push(data[i].goodsName);
                    shipmentsNumberList.push(data[i].shipmentsNumber)
                    saleableNumberList.push(data[i].saleableNumber)
                }
                myChart1.setOption({
                    xAxis:[
                        {
                            data:productNameList
                        }
                    ],
                    series:[
                        {
                            data:saleableNumberList
                        },
                        {
                            data:shipmentsNumberList
                        }
                    ]
                })
            }
        });


        tableIns2 = table.render({
            id: 'testReload2'//配置动态表格id以便于执行重载操作
            ,elem: '#depotInventory-data2'
            , height: 'auto'
            , limit: '5'
            , url: $tool.getContext() + 'depotInventory/materialInventorylList.do' //数据接口
            , method: 'post'
            , page:true //开启分页
            , cols: [[ //表头
                {type:'numbers',title:'',fixed: 'left',width: '2.5%'}
                , {field: 'goodsId', title: '货物ID', width: '16%'}
                , {field: 'goodsName', title: '货物名称', width: '16%'}
                , {field: 'supplierName', title: '供应商名称', width: '16%'}
                , {field: 'goodsNumber', title: '货品数量', width: '16%'}
                , {field: 'shipmentsNumber', title: '待发货数量', width: '16%'}
                , {field: 'saleableNumber', title: '可销售数量', width: '16%'}
            ]]
            , done: function (res, curr) {//请求完毕后的回调
                var data = res.data;
                var materialNameList = new Array();
                var shipmentsNumberList = new Array();
                var saleableNumberList = new Array();
                for(var i in data) {
                    materialNameList.push(data[i].supplierName.substring(0,4)+':'+data[i].goodsName);
                    shipmentsNumberList.push(data[i].shipmentsNumber)
                    saleableNumberList.push(data[i].saleableNumber)
                }
                myChart2.setOption({
                    xAxis:[
                        {
                            data:materialNameList
                        }
                    ],
                    series:[
                        {
                            data:saleableNumberList
                        },
                        {
                            data:shipmentsNumberList
                        }
                    ]
                })
            }
        });

        //为toolbar添加事件响应
        table.on('tool(partDepotInventoryFilter)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var row = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象
            //区分事件
            /*if (layEvent === 'add') { //删除
                addaddlDepotInventoryCheckDetail(tr);
            }*/
        });
    }

    //库存详情
    function detailsInventory(goodsId,type){
        if (type=='原料') {
            var index = layui.layer.open({
                title: "库存详情",
                type: 2,
                content: "detailsMaterialGoodsInventory.html?goodsId="+goodsId,
                success: function (layero, index) {
                    setTimeout(function () {
                        layui.layer.tips('点击此处返回库存列表', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    }, 500)
                }
            });
        }
        if(type=='产品'){
            var index = layui.layer.open({
                title: "库存详情",
                type: 2,
                content: "detailsProductInventory.html?goodsId="+goodsId,
                success: function (layer, index) {
                    setTimeout(function () {
                        layui.layer.tips('点击此处返回库存列表', '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    }, 500)
                }
            });
        }
        //改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
        $(window).resize(function () {
            layui.layer.full(index);
        });
        layui.layer.full(index);
    }

});