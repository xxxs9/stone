layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention: 'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool: 'tool',
    $api:'api'
}).use(['form', 'layer', 'jquery', 'ajaxExtention', '$tool','$api','laydate'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery,
        $tool = layui.$tool ,
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
    function initdate(){
        laydate.render({
            elem:'test1',
            type:'datetime',
            format:'yyyy-MM-dd'
        });
    }
     /**
     * 页面初始化
     * */
    function init() {
        //初始化下拉框
      var queryArgs = $tool.getQueryParam();
      var id = queryArgs['id'];
      console.log(id);
      var url = $tool.getContext()+'create/get';
      var  req={
          id:id
      };

      $api.getCreateUserById(req,function (res) {
          var data =res.data;
          $("[name='createUser']").val(data.createUser);
          $("[name='createTime']").val(data.createTime);
          $("[name='employeeId']").val(data.employeeId);
          //fixme(关联员工表你)
          form.render();

      })

    }

    init();



    /**
     * 表单提交
     * */
    form.on("submit(addMenu)", function (data) {
        /*var id = data.field.id;*/
        var createUser = data.field.createUser;
        var createTime = data.field.createTime;
        var employeeId = data.field.employeeId;




        //请求
        var req = {
            /*id:id,*/

            createUser: createUser,
            createTime: createTime,
            employeeId: employeeId,


        };

        $api.editCreateUserInfo(req,function (data) {
            //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
            layer.msg("人员添加成功！", {time: 1000}, function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });

        return false;
    })

});


