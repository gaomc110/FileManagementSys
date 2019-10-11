<%@ page language="java" import="cn.com.sparknet.common.bean.*"
         contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <meta charset="utf-8">
    <title>档案信息录入</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="<%=path%>/common/plugins/layui/css/layui.css" media="all" />
    <link rel="stylesheet" type="text/css" href="<%=path%>/common/css/font/iconfont.css" media="all">
    <link rel="stylesheet" href="<%=path%>/common/css/style.css"/>
    <%
        User user = (User) session.getAttribute("msaCaseSystem");
        String loginName = user.getUSER_NAME();
        String oldpwd = user.getUSER_PWD();
        String loginid = user.getUSER_ID();
    %>
    <script type="text/javascript">
        document.oncontextmenu=new Function("event.returnValue=false");
        document.onselectstart=new Function("event.returnValue=false");
        var loginName = '<%=loginName%>';
        var loginid='<%= loginid%>';
        function getPath()
        {
            return '<%=path%>';
        }
    </script>
</head>
<body>
<h3 class="breadcrumbtilte">
    当前位置：后台管理>地址管理
</h3>
<div class="layui-tab-content container-fluid">
    <!--头部搜索-->
    <section class="panel panel-padding layui-form demouTable">
        <div class="layui-row">
            <div class="layui-col-xs3 layui-col-md3">
                <div class="layui-form-item">
                    <label class="layui-form-label">所属管区</label>
                    <div class="layui-input-block">
                        <select name="belongAdd" id="belongAdd" lay-filter="" autocomplete="off">
                            <option value="" >请选择所属管区</option>
                            <option value="1">浦口区</option>
                            <option value="2">江北新区</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="layui-col-xs3 layui-col-md3">
                <div class="layui-form-item">
                    <label class="layui-form-label" >小区名称</label>
                    <div class="layui-input-block">
                        <input class="layui-input" autocomplete="off" id="communityName" name="communityName" placeholder="请输入小区名称">
                    </div>
                </div>
            </div>
            <div class="layui-col-xs3 layui-col-md3">
                <div class="layui-form-item">
                    <label class="layui-form-label">房屋坐落地址</label>
                    <div class="layui-input-block">
                        <input class="layui-input" autocomplete="off" id="houseAdress" name="houseAdress" placeholder="请输入房屋坐落地址">
                    </div>
                </div>
            </div>
            <div class="layui-col-xs3 layui-col-md3">
                <button class="layui-btn" data-type="reload" style="margin-left:17px;"><i class="iconfont">&#xe7ff;</i> 查询</button>
            </div>
        </div>

    </section>

    <!--列表-->
    <section class="panel panel-padding" style="margin-bottom:0;">
        <div class="group-button">
            <span class="tableheadtile">房屋地址管理列表</span>
            <button id="addhouseAdd" class="layui-btn fr" >
                <i class="iconfont">&#xe75e;</i> 新增
            </button>
        </div>
        <div class="table-list">
            <!-- 表格数据 -->
            <table class="layui-table" id="udateTable" lay-filter="udateTable"></table>
            <script type="text/html" id="ubarDemo">
                <a class="layui-btn layui-btn-xs" lay-event="uedit">编辑</a>
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="udel">删除</a>
            </script>
        </div>

    </section>
</div>
<script type="text/javascript" src="<%=path%>/common/plugins/jquery/jquery-1.10.2.min.js" charset="utf8"></script>
<script type="text/javascript" src="<%=path%>/common/plugins/layui/layui.js"></script>
<script type="text/javascript" src="js/AddressManagerList.js"></script>
</body>
</html>