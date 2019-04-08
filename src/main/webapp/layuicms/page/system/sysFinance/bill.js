layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api:'api'
}).use(['laydate','form', 'layer', 'jquery','element', 'table', 'laypage', 'ajaxExtention', '$tool','$api'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery,
        laypage = layui.laypage,
        $tool = layui.$tool,
        table = layui.table,
        $api = layui.$api,
        laydate = layui.laydate;
    var element = layui.element;

    var tableIns;//表格实例

    /**
     * 初始化日期选择
     * */
    function initDate() {
        laydate.render({
            elem: '#billTime'
            , type: 'datetime'
            , range: '&'
            , format: 'yyyy-MM-dd HH:mm:ss'
        });
    }

    //日报表
    var option1 = {

        elem:'#day',
        id:'day',
        height:400,
        //height: 'full-100',
        cols:[[
            {type:'numbers',title:'序号',fixed: 'left'}
            , {field: 'department', title: '部门',width:100}
            , {field: 'balance', title: '款', width:120}
            , {field: 'billTime', title: '时间', width:170}
        ]],
        url:$tool.getContext() + 'bill/dailyReport.do',
        method:'post',
        page:true,
        limit:[10,20,30,40],
        limit:10,
        done:function(res,curr,count){
            // 隐藏列
            $(".layui-table-box").find("[data-field='auditType']").css("display","none");
        }
    };
    tableIns = table.render(option1)
    form.render();
//=====================================================================//

    //周报表
    var option2 = {

        elem:'#week',
        id:'week',
        height:400,
        //height: 'full-100',
        cols:[[
            {type:'numbers',title:'序号',fixed: 'left'}
            , {field: 'department', title: '部门',width:100}
            , {field: 'balance', title: '款', width:120}
            , {field: 'billTime', title: '时间', width:170}
        ]],
        url:$tool.getContext() + 'bill/weeklyReport.do',
        method:'post',
        page:true,
        limit:[10,20,30,40],
        limit:10,
        done:function(res,curr,count){
            // 隐藏列
            $(".layui-table-box").find("[data-field='auditType']").css("display","none");
        }
    };
    tableIns = table.render(option2)
    form.render();
//=====================================================================//
    //月报表
    var option3 = {

        elem:'#month',
        id:'month',
        height:400,
        //height: 'full-100',
        cols:[[
            {type:'numbers',title:'序号',fixed: 'left'}
            , {field: 'department', title: '部门',width:100}
            , {field: 'balance', title: '款', width:120}
            , {field: 'billTime', title: '时间', width:170}
        ]],
        url:$tool.getContext() + 'bill/monthlyReport.do',
        method:'post',
        page:true,
        limit:[10,20,30,40],
        limit:10,
        done:function(res,curr,count){
            // 隐藏列
            $(".layui-table-box").find("[data-field='auditType']").css("display","none");
        }
    };
    tableIns = table.render(option3)
    form.render();
//=====================================================================//
    //年报表
    var option4 = {

        elem:'#year',
        id:'year',
        height:400,
        //height: 'full-100',
        cols:[[
            {type:'numbers',title:'序号',fixed: 'left'}
            , {field: 'department', title: '部门',width:100}
            , {field: 'balance', title: '款', width:120}
            , {field: 'billTime', title: '时间', width:170}
        ]],
        url:$tool.getContext() + 'bill/annualReport.do',
        method:'post',
        page:true,
        limit:[10,20,30,40],
        limit:10,
        done:function(res,curr,count){
            // 隐藏列
            $(".layui-table-box").find("[data-field='auditType']").css("display","none");
        }
    };
    tableIns = table.render(option4)
    form.render();
//=====================================================================//
    //时间报表
    var option5 = {

        elem:'#time',
        id:'time',
        height:400,
        //height: 'full-100',
        cols:[[
            {type:'numbers',title:'序号',fixed: 'left'}
            , {field: 'department', title: '部门',width:100}
            , {field: 'balance', title: '款', width:120}
            , {field: 'billTime', title: '时间', width:170}
        ]],
        url:$tool.getContext() + 'bill/timeReport.do',
        method:'post',
        page:true,
        limit:[10,20,30,40],
        limit:10,
        done:function(res,curr,count){
            // 隐藏列
            $(".layui-table-box").find("[data-field='auditType']").css("display","none");
        }
    };
    tableIns = table.render(option5)
    form.render();
//=====================================================================//
    /*/!**
     * 定义表格
     * *!/
    function defineTable() {
        tableIns = table.render({
            elem: '#bill'
            , height: 370
            , url: $tool.getContext() + 'bill/billList.do' //数据接口
            , method: 'post'
            , page:true //开启分页
            , cols: [[ //表头
                  {type:'numbers',title:'序号',fixed: 'left'}
                , {field: 'department', title: '部门',width:100,}
                , {field: 'balance', title: '款', width:120,}
                , {field: 'billTime', title: '时间', width:170,}
                 //这里的toolbar值是模板元素的选择器
            ]]
            , done: function (res, curr) {//请求完毕后的回调
                //如果是异步请求数据方式，res即为你接口返回的信息.curr：当前页码
            }
        });
        //为toolbar添加事件响应
        table.on('tool(applyFilter)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var row = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象

        });

        table.on()
    }
    defineTable();*/

    //监听tab切换
    element.on('tab(test)', function(data){
        //console.log(this); //当前Tab标题所在的原始DOM元素
        //console.log(data.index); //得到当前Tab的所在下标
        //console.log(data.elem); //得到当前的Tab大容器
        if(data.index == 0){
            $("#exportDayData").show();
            $("#exportWeekData").css("display","none");
            $("#exportMonthData").css("display","none");
            $("#exportYearData").css("display","none");
            $("#myDiv").css("display","none");
        } else if (data.index == 1) {
            $("#exportDayData").css("display","none");
            $("#exportWeekData").show();
            $("#exportMonthData").css("display","none");
            $("#exportYearData").css("display","none");
            $("#myDiv").css("display","none");
        }
        else if (data.index == 2) {
            $("#exportDayData").css("display","none");
            $("#exportWeekData").css("display","none");
            $("#exportMonthData").show();
            $("#exportYearData").css("display","none");
            $("#myDiv").css("display","none");
        }else if (data.index == 3) {
            $("#exportDayData").css("display","none");
            $("#exportWeekData").css("display","none");
            $("#exportMonthData").css("display","none");
            $("#exportYearData").show();
            $("#myDiv").css("display","none");
        }
        else if (data.index == 4) {

            initDate()
            $("#exportDayData").css("display","none");
            $("#exportWeekData").css("display","none");
            $("#exportMonthData").css("display","none");
            $("#exportYearData").css("display","none");
            $("#myDiv").show();
        }

    });
//=====================================================================//
    //导出日报表
    $('#exportDayData').click(function(){
        var curWwwPath=window.document.location.href;
        //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
        var pathName=window.document.location.pathname;
        var pos=curWwwPath.indexOf(pathName);
        //获取主机地址，如： http://localhost:8083
        var localhostPaht=curWwwPath.substring(0,pos);
        //获取带"/"的项目名，如：/uimcardprj
        var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
        //alert(localhostPaht+projectName)
        window.location=localhostPaht+projectName + "//bill/exportDailyReport.do"
    })
//=====================================================================//
    //导出周报表
    $('#exportWeekData').click(function(){
        var curWwwPath=window.document.location.href;
        //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
        var pathName=window.document.location.pathname;
        var pos=curWwwPath.indexOf(pathName);
        //获取主机地址，如： http://localhost:8083
        var localhostPaht=curWwwPath.substring(0,pos);
        //获取带"/"的项目名，如：/uimcardprj
        var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
        //alert(localhostPaht+projectName)
        window.location=localhostPaht+projectName + "//bill/exportWeeklyReport.do"
    })
//=====================================================================//
    //导出月报表
    $('#exportMonthData').click(function(){
        var curWwwPath=window.document.location.href;
        //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
        var pathName=window.document.location.pathname;
        var pos=curWwwPath.indexOf(pathName);
        //获取主机地址，如： http://localhost:8083
        var localhostPaht=curWwwPath.substring(0,pos);
        //获取带"/"的项目名，如：/uimcardprj
        var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
        //alert(localhostPaht+projectName)
        window.location=localhostPaht+projectName + "//bill/exportMonthlyReport.do"
    })
//=====================================================================//
    //导出年报表
    $('#exportYearData').click(function(){
        var curWwwPath=window.document.location.href;
        //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
        var pathName=window.document.location.pathname;
        var pos=curWwwPath.indexOf(pathName);
        //获取主机地址，如： http://localhost:8083
        var localhostPaht=curWwwPath.substring(0,pos);
        //获取带"/"的项目名，如：/uimcardprj
        var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
        //alert(localhostPaht+projectName)
        window.location=localhostPaht+projectName + "//bill/exportAnnualReport.do"
    })


//=====================================================================//

    //查询
    form.on("submit(queryData)", function (data) {

        var time = data.field.billTime;
        var timeArr = time.split("&");
        var startTime = timeArr[0];
        var endTime = timeArr[1];

        //表格重新加载
        tableIns.reload({
            where:{
                startTime:startTime,
                endTime:endTime
            }
        });

        return false;
    });


    //导出年报表
    $('#exportTimeData').click(function(){

        var time = $("#billTime").val();
        if(time == ''){
            var curWwwPath=window.document.location.href;
            //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
            var pathName=window.document.location.pathname;
            var pos=curWwwPath.indexOf(pathName);
            //获取主机地址，如： http://localhost:8083
            var localhostPaht=curWwwPath.substring(0,pos);
            //获取带"/"的项目名，如：/uimcardprj
            var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
            //alert(localhostPaht+projectName)
            window.location=localhostPaht+projectName + "//bill/exportTimeReport.do";
        }else{
            var timeArr = time.split("&");
            var startTime = timeArr[0];
            var endTime = timeArr[1];

            var curWwwPath=window.document.location.href;
            //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
            var pathName=window.document.location.pathname;
            var pos=curWwwPath.indexOf(pathName);
            //获取主机地址，如： http://localhost:8083
            var localhostPaht=curWwwPath.substring(0,pos);
            //获取带"/"的项目名，如：/uimcardprj
            var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
            //alert(localhostPaht+projectName)
            window.location=localhostPaht+projectName + "//bill/exportTimeReport.do?startTime="+startTime+"&endTime="+endTime;
        }




    })




});