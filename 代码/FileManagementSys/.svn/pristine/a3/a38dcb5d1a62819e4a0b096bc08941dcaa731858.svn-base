
//config的设置是全局的
layui.config({
    base: '../../common/js/' //假设这是test.js所在的目录
}).extend({ //设定模块别名

});

layui.use(['laypage', 'table','laydate','element', 'layer', 'jquery', 'form'], function (exports) {
    var $ = layui.jquery,
        element = layui.element,
        layer = layui.layer,
        form = layui.form,
        laypage = layui.laypage,
        table = layui.table,
        laydate = layui.laydate;;

    var projId = $("#theme_id_modify", window.parent.document).val();
    if (projId == "" || projId == null || projId == undefined) {
        projId = $("#theme_id_modifytow", window.parent.document).val();
    }


    //执行渲染
    table.render({
        elem: '#udateTable' //指定原始表格元素选择器（推荐id选择器）
        , url: getPath() + '/OperationalController/getOperationalList.do'
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
            , {field: 'ACCOUNT', title: '操作人员账号', minWidth: 120, align: 'center'}
            , {field: 'PERSON', title: '操作人员姓名', minWidth: 135, align: 'center'}
            , {field: 'COMMUNITY_NAME', title: '小区名称', minWidth: 135, align: 'center'}
            , {field: 'QIUQUAN_NO', title: '丘权号', minWidth: 140, align: 'center'}
            , {field: 'TYPE', title: '操作类型', minWidth: 80, align: 'center',templet: function (item){
                if (item.TYPE == '1') {
                    return "档案查询";
                }else if (item.TYPE == '2') {
                    return "档案新增";
                }else if (item.TYPE == '3') {
                    return "档案修改";
                }else if (item.TYPE == '4') {
                    return "档案删除";
                } else {
                    return "";
                }
            }}
            , {field: 'CREATE_DATE', title: '操作时间', minWidth: 80, align: 'center'}
        ]]
        , request: {
            pageName: 'page' //页码的参数名称，默认：page
            , limitName: 'limit' //每页数据量的参数名，默认：limit
        }
        , where: {loginid: loginid}

    });

    var $ = layui.$, active = {
        //搜索
        reload: function () {
            var type = $('#type').val();
            var ustart_date = $('#ustart_date').val();
            var uend_date = $('#uend_date').val();
            var account = $('#account').val();
            var person = $('#person').val();
            table.reload('iduTest', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    type: type,
                    ustart_date: ustart_date,
                    uend_date: uend_date,
                    account: account,
                    person: person
                }
            });
        }
    };

    //时间控件
    var start = laydate.render({
        elem: '#ustart_date',
        type: 'datetime',
        btns: ['clear', 'confirm'],
        done: function (value, date) {
            if ($.trim(value) == '') {
                var curDate = new Date();
                date = {'date': curDate.getDate(), 'month': curDate.getMonth() + 1, 'year': curDate.getFullYear()};
                end.config.min = {'date': '31', 'month': '12', 'year': '1900'};
            } else {
                endMax = end.config.max;
                end.config.min = date;
                end.config.min.month = date.month - 1;
            }

        }
    });
    var end = laydate.render({
        elem: '#uend_date',
        type: 'datetime',
        done: function (value, date) {
            if ($.trim(value) == '') {
                var curDate = new Date();
                date = {'date': curDate.getDate(), 'month': curDate.getMonth() + 1, 'year': curDate.getFullYear()};
                start.config.max = {'date': '31', 'month': '12', 'year': '9999'};
            } else {
                start.config.max = date;
                start.config.max.month = date.month - 1;
            }
        }
    });

    $('.demouTable .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

})



