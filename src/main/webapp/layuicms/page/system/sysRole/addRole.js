layui.config({
    base: $config.resUrl+'layuicms/common/js/'//定义基目录
}).extend({
    ajaxExtention:'ajaxExtention',//加载自定义扩展，每个业务js都需要加载这个ajax扩展
    $tool:'tool',
    $api:'api'
}).use(['form', 'layer','tree', 'jquery','ajaxExtention','$tool','$api'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        laypage = layui.laypage,
        $ = layui.jquery,
        $tool = layui.$tool,
        $api = layui.$api,
        layer = layui.layer;

    layui.tree({
        elem: '#demo1' //指定元素
        ,target: '_blank' //是否新选项卡打开（比如节点返回href才有效）
        ,click: function(item){ //点击节点回调
            layer.msg('当前节名称：'+ item.name + '<br>全部参数：'+ JSON.stringify(item));
            console.log(item);
        }
        ,nodes: [ //节点
            {
                name: '常用文件夹'
                ,id: 1
                ,alias: 'changyong'
                ,children: [
                    {
                        name: '所有未读（设置跳转）'
                        ,id: 11
                        ,href: 'http://www.layui.com/'
                        ,alias: 'weidu'
                    }, {
                        name: '置顶邮件'
                        ,id: 12
                    }, {
                        name: '标签邮件'
                        ,id: 13
                    }
                ]
            }, {
                name: '我的邮箱'
                ,id: 2
                ,spread: true
                ,children: [
                    {
                        name: 'QQ邮箱'
                        ,id: 21
                        ,spread: true
                        ,children: [
                            {
                                name: '收件箱'
                                ,id: 211
                                ,children: [
                                    {
                                        name: '所有未读'
                                        ,id: 2111
                                    }, {
                                        name: '置顶邮件'
                                        ,id: 2112
                                    }, {
                                        name: '标签邮件'
                                        ,id: 2113
                                    }
                                ]
                            }, {
                                name: '已发出的邮件'
                                ,id: 212
                            }, {
                                name: '垃圾邮件'
                                ,id: 213
                            }
                        ]
                    }, {
                        name: '阿里云邮'
                        ,id: 22
                        ,children: [
                            {
                                name: '收件箱'
                                ,id: 221
                            }, {
                                name: '已发出的邮件'
                                ,id: 222
                            }, {
                                name: '垃圾邮件'
                                ,id: 223
                            }
                        ]
                    }
                ]
            }
            ,{
                name: '收藏夹'
                ,id: 3
                ,alias: 'changyong'
                ,children: [
                    {
                        name: '爱情动作片'
                        ,id: 31
                        ,alias: 'love'
                    }, {
                        name: '技术栈'
                        ,id: 12
                        ,children: [
                            {
                                name: '前端'
                                ,id: 121
                            }
                            ,{
                                name: '全端'
                                ,id: 122
                            }
                        ]
                    }
                ]
            }
        ]
    });

    /**
     * 表单提交
     * */
    form.on("submit(addRole)", function (data) {
        var roleName = data.field.roleName;
        var isSuper = data.field.isSuper;
        isSuper = $tool.isBlank(isSuper)?'0':isSuper;

        //弹出loading(遮罩层已经统一放在了ajaxExtention里面了)
        //var index = top.layer.msg('数据提交中，请稍候', {icon: 16, time: false, shade: 0.8});

        //请求
        var req = {
            roleName:roleName,
            isSuper:isSuper
        };

        $api.AddRole(req,function (data) {
            //top.layer.close(index);(关闭遮罩已经放在了ajaxExtention里面了)
            layer.msg("角色添加成功！",{time:1000},function () {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            });
        });

        return false;
    })

});


