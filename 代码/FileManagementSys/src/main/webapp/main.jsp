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
<%
User user = (User) session.getAttribute("msaCaseSystem");
String loginName = user.getUSER_NAME();
String oldpwd = user.getUSER_PWD();
String loginid = user.getUSER_ID();
/* String USER_PORTRAIT = user.getUSER_PORTRAIT(); */
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
<body style="padding:20px;">
<button class="layui-btn layui-btn-sm" id="alert_comfirm">
   <i class="iconfont">&#xe7f5;</i> 询问提示
</button>
<button class="layui-btn layui-btn-sm" id="alert_comfirm2">
   <i class="iconfont">&#xe7f5;</i> 删除文件询问提示
</button>
<button  class="layui-btn layui-btn-sm" id="alert-yb">
    <i class="iconfont">&#xe7dc;</i> 一般提示
</button>

<button  class="layui-btn layui-btn-sm" id="alertsuceessfun">
    <i class="iconfont">&#xe7dc;</i> 上传成功操作方法
</button>

<button class="layui-btn layui-btn-sm" id="alert-success">
    <i class="iconfont">&#xe7dc;</i> 成功提示
</button>
<button class="layui-btn layui-btn-sm" id="alert-error">
    <i class="iconfont">&#xe7dc;</i> 失败提示
</button>

<button class="layui-btn layui-btn-sm" id="weixin">
    <i class="iconfont">&#xe7dc;</i> 点击打开弹窗
</button>

<div id="tong" style="display: none;background: #fff;height:100%;">
	<div class="row">
	    <ul class="col-sm-12" id="img-list">
	        <li><a href="javascript:void(0)" data-src="common/images/firefox.png" title="浦口区泰山街道68号-幸福家园-101-10001平面图">浦口区泰山街道68号-幸福家园-101-10001平面图</a></li>
	        <li><a href="javascript:void(0)" data-src="common/images/firefox.png" title="幸福家园-101-10001剖面图">幸福家园-101-10001剖面图</a></li>
	        <li><a href="javascript:void(0)" data-src="common/images/firefox.png" title="浦口区幸福家园-101-10001立面图">浦口区幸福家园-101-10001立面图</a></li>
	    </ul>
	</div>
</div>


<script type="text/javascript" src="<%=path%>/common/plugins/jquery/jquery-1.10.2.min.js" charset="utf8"></script>
<script src="<%=path%>/common/plugins/layui/layui.js "></script>
<script type="text/javascript">
layui.config({
	  base: '../../common/js/' //假设这是test.js所在的目录
	}).extend({ //设定模块别名
	});
	layui.use(['form', 'element','layer','jquery'], function(exports){
	    var $ = layui.jquery,
	    element = layui.element,
	    layer = layui.layer,
	    form = layui.form;
	    
	    $(document).on('click','#alert_comfirm',function(){
	    	layer.alert("<p>您确定退出系统，请先保存重要文件</p>", { //样式类名  自定义样式
                title:'注销退出'
                , skin: 'demo-class2' //样式类名
                , btn: ['确定', '取消'] //按钮
                , yes: function () {
                }
	    	})
        })
        
        $(document).on('click','#alert_comfirm2',function(){
	    	layer.alert("<span>删除相关文件后不可恢复，您要确定删除该文件？</span>", { //样式类名  自定义样式
                title:'删除文件'
                , skin: 'demo-class1' //样式类名
                , btn: ['删除', '取消'] //按钮
                , yes: function () {
                }
	    	})
        })
        
        $(document).on('click','#alert-yb',function(){
        	layer.alert('<h3>无权限</h3><p>您没有查看此报告的权限</p>', {
                 title:'提示'
                ,skin: 'demo-class5' //样式类名
                ,btn:0
                ,cancel: function () {
                   console.log(1);
                }
        	});
        })
        
        
         $(document).on('click','#alertsuceessfun',function(){
        	 layer.alert('<h3>附件上传成功</h3><p>请耐心等待5秒后，点击刷新列表查看结果</p>', {
                 title:'提示'
                 ,skin: 'demo-class6' //样式类名
                 ,btn:0
                 ,yes: function () {
                    console.log(1);
                 }
         	});
         })
         
         $(document).on('click','#alert-success',function(){
        	 layer.alert('<h3>保存成功</h3>', {
                 title:'提示'
                 ,skin: 'demo-class3' //样式类名
                 ,btn:0
                 ,yes: function () {
                  console.log("刷新页面操作");
                 }
         	});
         })
        
         
         $(document).on('click','#alert-error',function(){
        	 layer.alert('<h3>保存失败</h3>', {
                 title:'提示'
                 ,skin: 'demo-class' //样式类名
                 ,btn:0
                 ,yes: function () {
                    
                 }
         	});
         })
         
    $(document).on('click','#weixin',function(){
		    layer.open({
		      type: 1,
		      title: false,
		      closeBtn: 0,
		      area: ['600px','500px'],
		      skin: 'layui-layer-nobg', //没有背景色
		      shadeClose: false,
		      content: $('#tong')
		    });
    })
    
      $(document).on("click","#img-list li a",function(e){
           layer.photos({ photos: {"data": [{"src": $(this).attr("data-src")}]},shift:5,area:['500px'],anim: 0 });
      });
        
})
</script>
</body>
</html>