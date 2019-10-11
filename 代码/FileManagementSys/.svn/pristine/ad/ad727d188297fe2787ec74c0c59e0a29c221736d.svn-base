/**
 * Created by gaogao on 2019/8/23 表格选中
 */
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

    $(function(){
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
    });

    //执行渲染
    table.render({
        elem: '#udateTable' //指定原始表格元素选择器（推荐id选择器）
        , url: getPath() + '/DealInformationController/getDealInformationList.do'
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
            , {field: 'QIUQUAN_NO', title: '丘权号', minWidth: 120, align: 'center'}
            , {field: 'COMMUNITY_NAME', title: '小区名称', minWidth: 135, align: 'center'}
            , {field: 'BUILDING_NO', title: '楼栋号', minWidth: 135, align: 'center'}
            , {field: 'DEAL_NO', title: '交易合同编号', minWidth: 140, align: 'center'}
            , {field: 'DEAL_TYPE', title: '交易类型', minWidth: 80, align: 'center',templet: function (item){
                if (item.DEAL_TYPE == '1') {
                    return "房屋租赁";
                }else if (item.DEAL_TYPE == '2') {
                    return "房屋转让";
                }else if (item.DEAL_TYPE == '3') {
                    return "房屋抵押";
                }else if (item.DEAL_TYPE == '0') {
                    return "其他";
                } else {
                    return "";
                }
            }}
            , {field: 'DEAL_STATE', title: '交易进度', minWidth: 80, align: 'center',templet: function (item){
                if (item.DEAL_STATE == '1') {
                    return "待交易";
                }else if (item.DEAL_STATE == '2') {
                    return "交易中";
                }else if (item.DEAL_STATE == '3') {
                    return "已完成";
                }else {
                    return "";
                }
            }}
            , {field: 'DEAL_DATE', title: '交易时间', minWidth: 135, align: 'center'}
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
            var dealDate = $('#dealDate').val();
            var communityName = $('#communityName').val();
            var qiuquanNo = $('#qiuquanNo').val();
            table.reload('iduTest', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    dealDate: dealDate,
                    communityName: communityName,
                    qiuquanNo: qiuquanNo
                }
            });
        }
    };

    $('.demouTable .layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

})       
       
     
        
  