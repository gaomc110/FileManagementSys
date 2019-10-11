<%@ page language="java" import="cn.com.sparknet.common.bean.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@ page import="cn.com.sparknet.common.util.CacheUtil"%>
<%@ page import="cn.com.sparknet.common.util.StringUtil"%>
<%
 String path = request.getContextPath();
 String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
 String errorinfo = StringUtil.nullToEmpty((String) session.getAttribute("errorinfo"));
%>
<html>
<head>
<meta charset="utf-8">
<title>浦口房产档案管理</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="<%=path%>/common/plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" type="text/css" href="<%=path%>/common/css/font/iconfont.css" media="all">
<link rel="stylesheet" href="<%=path%>/common/css/style.css"/>
<link rel="stylesheet" href="<%=path%>/common/css/layout.css" media="all" />
<%
User user = (User) session.getAttribute("msaCaseSystem");
String loginName = user.getUSER_NAME();
String oldpwd = user.getUSER_PWD();
String loginid = user.getUSER_ID();
String theme_id_modify=request.getParameter("theme_id_modify");
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
<style type="text/css">
.page-content .layui-tab-content .layui-tab-item{width: 100%;height: 100%;background: #f6f6f6;}
.page-content .layui-tab-content .layui-tab-item iframe{width: 100%;height: 100%;}
#submenu ul li cite{margin-left: 0.5vw;font-size: 0.9vw;}
#submenu ul li dl dd{margin-bottom: 2px;}    
.header .layui-nav .layui-nav-item {margin: 0px;border-left: 0px solid #2b2e37;}
</style>
</head>
<body>
<input type="hidden" id="theme_id_modifytow" value="<%=theme_id_modify %>" />
	<div class="layui-layout layui-layout-admin beg-layout-container">
		<div class="layui-header header beg-layout-header">
			<div class="beg-layout-main beg-layout-logo" >
				<a href="<%=path%>/index.jsp">浦口区新浦房产测绘队档案管理平台</a>
			</div>
			<div class="beg-layout-main beg-layout-panel" id="rightbtninfo">
				<ul class="layui-nav beg-layout-nav bg-headright-menu admin-header-item" lay-filter="user">
					<li class="layui-nav-item">
						<a style="font-size:1.0vw;">欢迎您，<span style="font-weight: bold;font-size: 1.1vw;"><%=loginName%></span></a>
					</li>
					<li class="layui-nav-item">
						<a href="javascript:;" style="padding:0 1.3vw;" data-rturl="login.jsp" data-href="login.jsp" data-type="doLoginOut" class="beg-layou-head do-admin">
							<span><i class="iconfont" style="font-size:1.6vw;">&#xed3d;</i></span>
						</a>
					</li>
				</ul>
			</div>
		</div>
		<!--侧边导航栏-->
		<div class="layui-side beg-layout-side" id="side" >
             <div class="layui-side-scroll">
	             
				<!--子菜单项-->
	                 <p class="jqadmin-home tab-menu">
	                    <a href="javascript:;" data-url="main.jsp" data-title="首页">
	                        <i class="iconfont icon-shouye11" ></i>
	                        <span style="margin-left:6px;">后台首页</span>
	                    </a>
	                </p> 
	                <div id="submenu"  lay-filter="side"></div>
			</div>				
		</div>
		<!--内容区域-->
	<input type="hidden" id="theme_id_modify" value="<%=theme_id_modify %>" />
		<div class="layui-body beg-layout-body page-content" style="bottom: 0;border-left: solid 0px #080f29;">
			<div class="layui-tab admin-nav-card layui-tab-brief layout-nav-card layui-tab-card" lay-filter="admin-tab" lay-allowclose="true">  
		        <ul class="layui-tab-title" style="display: none;">  <!-- tab选项卡标题 -->  
		            <li class="layui-this" lay-id="0">
						<i class="fa fa-home" aria-hidden="true"></i>
						<cite>首页</cite>
					</li>
		        </ul>  
		        <div class="layui-tab-content" style="min-height: 150px; padding: 0;background-image: url(common/images/back.png);background-repeat: no-repeat; background-position: center center;">  <!-- tab选项卡内容 -->  
		            <div class="layui-tab-item layui-show">  
		                <iframe src="main.jsp"></iframe>
		            </div>  
		        </div>  
		    </div>  
		</div>
		<!--页脚-->
		<div class="layui-footer beg-layout-footer">
			<p>© Copyright 2019 南京市浦口区房产档案管理局 All rights reserved.<br />技术支持 ：江苏星网软件有限公司(0514-87329222)</p>
		</div>
	</div>
			
	<script type="text/javascript" src="<%=path%>/common/plugins/jquery/jquery-1.10.2.min.js" charset="utf8"></script>		
	<script src="<%=path%>/common/plugins/layui/layui.js "></script>
	<script src="<%=path%>/common/js/layout.js "></script>	
	</body>
</html>