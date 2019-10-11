//config的设置是全局的
layui.config({
    base: '../../common/js/' //假设这是test.js所在的目录
}).extend({ //设定模块别名

});


layui.use(['laypage', 'table', 'element', 'layer', 'jquery', 'form'], function (exports) {
    var $ = layui.jquery,
        element = layui.element,
        layer = layui.layer,
        form = layui.form,
        laypage = layui.laypage,
        table = layui.table;

    var projId = $("#theme_id_modify", window.parent.document).val();
    if (projId == "" || projId == null || projId == undefined) {
        projId = $("#theme_id_modifytow", window.parent.document).val();
    }

    /* $(function(){
     var myDate = new Date();
     var startYear = 2000;//起始年份
     var endYear = myDate.getFullYear();//结束年份
     var server = document.getElementById("dealDate"); //server为select定义的id
     for (var i = endYear; i >= startYear; i--) {
     var option = document.createElement("option");  // 创建添加option属性
     option.setAttribute("value", ""+i); // 给option的value添加值
     option.innerText = i;     // 打印option对应的纯文本
     server.appendChild(option);         //给select添加option子标签
     form.render("select");
     }
     });*/

    //执行渲染
    table.render({
        elem: '#udateTable' //指定原始表格元素选择器（推荐id选择器）
        , url: getPath() + '/AddressController/getAddressList.do'
        , method: 'post'
        , page: true
        , id: 'iduTest'
        , limits: [10, 20, 50, 100]
        , limit: 10 //默认采用60
        , cellMinWidth: 80
        , height: 'full-230' //容器高度
        , loading: true
        , cols: [[ //标题栏
            {field: 'RN', title: '序号', minWidth: 60, align: 'center', type: 'numbers', width: 60}
            , {
                field: 'BELONGTOADRESS', title: '所属管区', minWidth: 80, align: 'center', templet: function (item) {
                    if (item.BELONGTOADRESS == '1') {
                        return "浦口区";
                    } else if (item.BELONGTOADRESS == '2') {
                        return "江北新区";
                    } else {
                        return "";
                    }
                }
            }
            , {field: 'COMMUNITY_NAME', title: '小区名称', minWidth: 115, align: 'center'}
            , {field: 'HOUSE_ADRESS', title: '房屋坐落地址', minWidth: 145, align: 'center'}
            , {field: 'FILE_ADRESS', title: '档案存储位置', minWidth: 160, align: 'center'}
            , {field: 'CREATE_DATE', title: '创建时间', minWidth: 135, align: 'center'}
            , {field: 'caozhuo', title: '操作', minWidth: 150, align: 'center'}
        ]]
        , request: {
            pageName: 'page' //页码的参数名称，默认：page
            , limitName: 'limit' //每页数据量的参数名，默认：limit
        }
        , where: {loginid: loginid}

    });

    //监听工具条
    table.on('tool(udateTable)', function (obj) {
        var data = obj.data;
        if (obj.event === 'udetail') {

        } else if (obj.event === 'udel') {

            layer.confirm('确定要删除吗？', function (index) {
                var USERID = data.U_ID;
                console.log(USERID);
                layer.close(index);
                if (USERID != loginid && data.USER_NAME != loginName) {
                    $.ajax({
                        url: getPath() + '/UserController/getUserDel.do',
                        type: "post",
                        data: {USERID: USERID},
                        async: true,
                        success: function (data) {
                            if (data.isdel == true) {
                                layer.msg('删除成功', {icon: 6});
                                table.reload('iduTest', {
                                    page: {
                                        curr: 1 //重新从第 1 页开始
                                    }
                                });//重新加载表格数据
                            } else {
                                layer.alert(data.msg);
                            }
                        },
                        error: function () {
                            layer.msg('操作失败！', {icon: 5});
                        }
                    });
                } else {
                    layer.msg('没有该操作权限，删除失败！', {icon: 5});
                }
            });

        } else if (obj.event === 'uedit') {

            $('#parentuserId').val(data.USER_ID);
            layer.ready(function () {
                var bjyh = layer.open({
                    type: 2,
                    title: '编辑用户',
                    area: ['800px', '490px'],
                    maxmin: false,
                    resize: false,
                    shade: 0.6, //遮罩透明度
                    anim: 1, //0-6的动画形式，-1不开启
                    content: getPath() + "/jsp/Permissions/PersonEdit.jsp",
                    success: function (layero, index) {
                        var body = layer.getChildFrame('body', index);
                        body.find('#TXTUID').val(data.USER_ID);
                    }
                });
                layer.full(bjyh);
            });

        }
    });

    var $ = layui.$, active = {
        //搜索
        reload: function () {
            var belongAdd = $('#belongAdd').val();
            var communityName = $('#communityName').val();
            var houseAdress = $('#houseAdress').val();
            table.reload('iduTest', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    belongAdd: belongAdd,
                    communityName: communityName,
                    houseAdress: houseAdress
                }
            });
        }
    };

    $('.demouTable .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    //添加
    $(document).on('click', '#addhouseAdd', function () {

        var index = layer.open({
            type: 2,
            title: false,
            maxmin: false,
            resize: false,
            closeBtn: 0,
            shade: 0.6, //遮罩透明度
            anim: 1, //0-6的动画形式，-1不开启
            content: getPath() + "/jsp/BackstageManage/AddressManagerAdd.jsp"
        });
        layer.full(index);

    });

})