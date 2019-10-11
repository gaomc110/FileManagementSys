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
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<title>浦口房产档案管理</title>
<meta name="keywords" content="">
<meta name="description" content="">
<link href="" rel="shortcut icon" />
<link rel="stylesheet" type="text/css" href="<%=path%>/common/plugins/layui/css/layui.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/common/css/login.css" />
<script type="text/javascript" src="<%=path%>/common/plugins/jquery/jquery.min.js"></script>
<meta name="csrf-param" content="authenticity_token" />
<meta name="csrf-token" content="" />
<style type="text/css">
.badbrowser{background:#0c7b71;position:fixed;top:0;left:0;right:0;bottom:0;text-align:center;white-space:nowrap;overflow:auto}
.badbrowser_modal{background:rgba(0,0,0,.25)}
.badbrowser_modal .badbrowser__content{border:1px solid #ccc;border-radius:3px;background:#fff;padding:15px}
.badbrowser__logo{width:50%}.badbrowser__content,.badbrowser__helper{display:inline-block;vertical-align:middle}
.badbrowser__helper{height:100%;margin-right:-.25em}
.badbrowser__content{white-space:normal}
.oldbrowser__browserLink{display:inline-block;width:60px;height:66px;background:url(common/images/browsers.png) no-repeat 0 0;margin:0 10px}
.layui-form-checkbox[lay-skin=primary] span {padding-left: 0;padding-right: 15px; line-height: 18px; background: 0 0;color: #ccc; }
</style>
<script>
function getBrowserInfo() {
	　　var Sys = {};
	　　var ua = navigator.userAgent.toLowerCase();
	　　var s; (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :
	　　(s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :
	　　(s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :
	　　(s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :
	　　(s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;
	　　if(Sys.ie) {
	　　　　return 'IE: ' + Sys.ie;
	　　}
	　　if(Sys.firefox) {
	　　　　return 'Firefox: ' + Sys.firefox;
	　　}
	　　if(Sys.chrome) {
	　　　　return 'Chrome: ' + Sys.chrome;
	　　}
	　　if(Sys.opera) {
	　　　　return 'Opera: ' + Sys.opera;
	　　}
	　　if(Sys.safari) {
	　　　　return 'Safari: ' + Sys.safari;
	　　}
	}

    window.AESKey = '';
    // 判断浏览器是否支持placeholder属性
    function isSupportPlaceholder() {
        var input = document.createElement('input');
        return 'placeholder' in input;
    };
    (function() {
        //判断是否是IE浏览器，包括Edge浏览器
        function IEVersion() {
            //取得浏览器的userAgent字符串
            var userAgent = navigator.userAgent;
            var regStr_ff = /firefox\/[\d.]+/gi;
            var agent = userAgent.toLowerCase();
            //判断是否IE浏览器
            var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1;
           /*  if (isIE) {
                var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
                reIE.test(userAgent);
                var fIEVersion = parseFloat(RegExp["$1"]);
                if (fIEVersion <= 6 || !isSupportPlaceholder()) {
                    return "1";
                }
            }else if (userAgent.indexOf("Firefox") > -1) {
            	var browser=agent.match(regStr_ff);
            	var verinfo = (browser+"").replace(/[^0-9.]/ig,"");
            	if(parseInt(verinfo)<parseInt(45)){
            		return "3";
            	}
            }else {
                return "2";
            } */
        }
      //获取页面body！内容！的高度和宽度
   	 var sHeight=document.documentElement.scrollHeight;
   	 var sWidth=document.documentElement.scrollWidth;
   	 //获取可视区域高度，宽度与页面内容的宽度一样
   	 var wHeight=document.documentElement.clientHeight;
 
		var tpl='<div id="ie-box" style="width:100%;background:url(common/images/ie-bg.png) repeat-x;margin:0;padding:400px 0 0 0;">'+
		'<center>'+
		'<div style="width:814px;height:455px;background:url(common/images/bg.jpg) no-repeat;margin:0 auto;">'+
		'<div style="padding:230px 0 20px 0;display:inline-block; text-align:center;width:100%;color:#fff; font-weight:bold;">'+getBrowserInfo()+'</div>'+
		'<div style="display:inline-block;padding-left:115px;">'+
		'<div class="ie-1-4-box">'+
		'<a class="ie-a" href="https://www.google.cn/chrome/browser/desktop/index.html"  target="_blank">'+
		'<img src="common/images/chrome.jpg" style="width:50%">'+
		'<p>Google Chrome</p>'+
		'</a>'+
		'</div>'+
		'<div class="ie-1-4-box">'+
		'<a class="ie-a" href="http://www.firefox.com.cn/download/" target="_blank">'+
		'<img src="common/images/firefox.jpg" style="width:50%">'+
		'<p>Mozilla Firefox</p>'+
		'</a>'+
		'</div>'+
	    '<div class="ie-1-4-box">'+
	    '<a class="ie-a" href="https://support.microsoft.com/zh-cn/help/17621/internet-explorer-downloads" target="_blank">'+
	    '<img src="common/images/ie.jpg" style="width:50%">'+
	    '<p>Internet Explorer</p>'+
	    '</a>'+
	    '</div>'+
	    '</div>'+
	    '<div style="clear:both"></div>'+
	    '<div style="color:#d9d9d9;font-size:12px;text-align:center;padding-top:20px;">Copyright &copy; 技术支持：江苏星网软件有限公司</div>'+
	    '</div>'+
	    '</center>'+
	    '</div>';

		
        window.onload = function() {
            if (IEVersion()==1 || IEVersion()==3) {
            	document.write("<style type='text/css'>*{margin:0;padding:0;font-family:'Microsoft Yahei';overflow:hidden;}body{}.ie-a{color:#eee;font-size:14px;text-decoration:none;}img{border:0;}.ie-a p{line-height:40px;}.ie-a p{line-height:40px;}.ie-1-4-box{width:25%;margin:0;padding:0;text-align:center;float:left;}</style>");
                document.write(tpl);
                document.write("<script>");
                document.write("var H = document.body.clientHeight;");
                document.write("var paddingTop = (H - 455) * .5;");
                document.write("if(paddingTop < 0){ paddingTop = 0 }");
                document.write('document.getElementById("ie-box").style.cssText = "width:100%;height:"+ H +"px;background:url(common/images/ie-bg.png) repeat-x;margin:0;padding:"+paddingTop+"px 0 0 0;"');
                document.write('<\/script>');

            }
        };
        window.siteName = "提示升级";
    })();
</script>
<script language="JavaScript">
if (top.location != this.location ){
		top.location =this.location;
}
</script> 
<script type="text/javascript">
function getPath()
{
    return '<%=path%>';
}
</script>
</head>
<body>
<div class="flex-container">
   <div class="flex-item">
           <div class="layui-row header">
				<div class="layui-col-sm8 layui-col-md8">
					 <h1 class="log">浦口区新浦房产测绘队档案管理平台</h1>
				</div>
				<div class="layui-col-sm4 layui-col-md4">
					<dl>
					  <dd>服务热线：<font>025-89647821</font></dd>
					  <dd>帮助中心</dd>
					</dl>
				</div>
			</div>
   </div>
   <div class="flex-item">
        <!-- LoginForm -->
		<div class="zyl_lofo_main">
			<div class="layui-row">
				<form class="layui-form zyl_pad_01"  id="loginform" name="loginform">
					<div class="layui-col-sm12 layui-col-md12">
						<div class="layui-form-item">
							<label id="userIcon1" class="userIco1"></label>
							<input type="text" id="yonghuming" name="yonghuming" autocomplete="off" placeholder="请输入用户名" class="layui-input zyl_lofo_iconadmin">
						</div>
					</div>
					<div class="layui-col-sm12 layui-col-md12">
						<div class="layui-form-item">
						    <label id="userIcon2" class="userIco2"></label>
							<input type="password" id="yonghumima1" name="yonghumima1"  onkeypress="javascript:hiddenmima(event)" placeholder="请输入密码" autocomplete="off" class="layui-input zyl_lofo_iconkey" oncopy="return false;" onpaste="return false;" oncut="return false;" ondrop="return false;" onselectstart="return false;" oncontextmenu="return false;" onkeydown="keyDown(event)" onkeyup="keyup()"/>
					        <input type="hidden" id="yonghumima" name="yonghumima"  autocomplete="off" />
						</div>
					</div>
					<div class="layui-col-sm12 layui-col-md12">
						<div class="layui-row">
							<div class="layui-col-xs6 layui-col-sm6 layui-col-md6">
								<div class="layui-form-item">
									<input type="text" name="vercode" id="vercode" style="padding-left: 14px;" autocomplete="off" placeholder="请输入验证码" class="layui-input" maxlength="5">
								</div>
							</div>
							<div class="layui-col-xs6 layui-col-sm6 layui-col-md6">
								<img id="imgVerify" class="yzm" style="cursor: pointer;float: right;border: 1px solid #dcdcdc;height: 2.4vw" onclick="getVerify()"/>
							</div>
						</div>
					</div>
					<div class="layui-col-sm12 layui-col-md12">
						<div class="layui-row" style="color:#333;">
						  <div class="layui-col-xs6 layui-col-sm6 layui-col-md6">
						    <input type="checkbox" name="rememberMe" id="rememberMe" lay-skin="primary" title="自动登录" >
						  </div>
						  <div class="layui-col-xs6 layui-col-sm6 layui-col-md6">
						    <a style="color:#333;float:right;">忘记密码？</a>
						  </div>  
						</div>
				    </div>
					<div class="layui-col-sm12 layui-col-md12" style="    margin-top: 2vw;">
					    <input type="reset" style="display:none;" />
						<button class="layui-btn layui-btn-fluid" style="background:#2f8ff2;font-size: 1.6vw;height:3vw;" lay-submit="" lay-filter="login">登  录</button>
					</div>
				</form>
			</div>
		</div>
		<!-- LoginForm End -->
   </div>
   <div class="flex-item">
       <div class="layui-row">
			<div class="layui-col-sm12 layui-col-md12 beg-footer">
				<p>© Copyright 2019 南京市浦口区房产档案管理局 All rights reserved.<br />技术支持 ：江苏星网软件有限公司(0514-87329222)</p>
			</div>
		</div>
   </div>
</div>
		

<script type="text/javascript" src="<%=path%>/common/plugins/layui/layui.js"></script>
<script type="text/javascript" src="<%=path%>/common/js/jquery.cookie.js"></script>
<script>
function hiddenmima(e){
    e = e ? e : window.event; 
    var kcode = e.which ? e.which : e.keyCode;
    var pass = document.getElementById("yonghumima1");
    var j_pass = document.getElementById("yonghumima");
       if(kcode!=8)
    {
     var keychar=String.fromCharCode(kcode);
     j_pass.value=j_pass.value+keychar;
     j_pass.value=j_pass.value.substring(0,pass.length);
    }
}
function keyDown(event){
	event=event?event:window.event;
	var kcode=event.which?event.which:event.keyCode;
	if(kcode==8){
	var password=document.getElementById("yonghumima");
	password.value=password.value.substring(0,password.value.length-1);
	}
}
    function keyup(e) {
    
    	document.loginform.yonghumima.value = document.loginform.yonghumima1.value;
    } 

    function setCookie(cheeckisoff) {
        var yonghuming = $("#yonghuming").val();
        var yonghumima = $("#yonghumima").val();
        if (cheeckisoff) {
            $.cookie("yonghuming", yonghuming);
            $.cookie("yonghumima", yonghumima);
            $.cookie("yonghumima1", yonghumima);
        } else {
            $.cookie("yonghuming", null);
            $.cookie("yonghumima", null);
            $.cookie("yonghumima1", null);
        }

    }

    function getCookie() {
         var yonghuming = $.cookie("yonghuming");
        var yonghumima = $.cookie("yonghumima");
        if (yonghuming) {
            $("#yonghuming").val(yonghuming);
        }
        if (yonghumima) {
        	$('#rememberMe').attr("checked", true);
            $("#yonghumima").val(yonghumima);
            $("#yonghumima1").val(yonghumima);
            layui.form.render();
        } 
        
    } 
	
    //获取验证码
	function getVerify() {
		$("#imgVerify").attr("src", getPath()+'/LoginController.do?getVerify=true&' + Math.random());
	}
    
    
    
    
	layui.use(['layer','form','element'], function() {
		var layer = layui.layer,
			$ = layui.jquery,
			form = layui.form;
		getVerify();
		getCookie();
		
		$(".zyl_lofo_iconadmin").hover(function(){
		    $("#userIcon1").addClass('userIco1-this');
		},function(){
		    $("#userIcon1").removeClass('userIco1-this');
		})
		
		$(".zyl_lofo_iconkey").hover(function(){
		    $("#userIcon2").addClass('userIco2-this');
		},function(){
		    $("#userIcon2").removeClass('userIco2-this');
		})

		 $(document).keydown(function(event){
             switch(event.keyCode){
                case 13:return false; 
                }
         });
		
	    //提交监听事件	
		form.on('submit(login)',function(data){
			params = data.field;
			if($.trim(params.yonghuming)=="" || $.trim(params.yonghumima1)==""){
		        	layer.alert('<h3>提示</h3><p>账号/密码不能为空!</p>', {
		        		closeBtn: 0    // 是否显示关闭按钮
		                ,title:''
		                , skin: 'demo-class5' //样式类名
		        	});
			        return false;
			}else{
				var yonghumima="";
	            var yonghuming=params.yonghuming;
	            if(params.yonghumima1!="" && params.yonghumima==""){
	            	 if(params.yonghumima1==undefined){
	                 	yonghumima="";
	                 }else{
	                	 yonghumima=params.yonghumima1;
	                 }
	            }else if(params.yonghumima1!="" && params.yonghumima!=""){
	                	  yonghumima=params.yonghumima;
	            }
	            submit($,yonghuming,yonghumima);
			}
			return false;
		});
		
	    
		//提交
		function submit($,yonghuming,yonghumima){
			$.post(getPath()+'/LoginController.do?login=true', {"yonghuming":yonghuming,"yonghumima":yonghumima,"vercode":$('#vercode').val()}, function (res) {
                if (res.errtype==1) {
               	 if (params.rememberMe == undefined || params.rememberMe=="") {
                        setCookie(false);
                    } else {
                        setCookie(params.rememberMe);
                    } 
               	window.location.href=getPath()+'/index.jsp';
               }else if(res.errtype==2){
               	if ($('#rememberMe').attr("checked") == "checked"  || $('#rememberMe').attr("checked") == undefined) { //判断是否选中
		                $('#rememberMe').attr("checked", false); //设置选中 注意这里使用的是prop(), 这里要是使用了attr()是无效的
                   } else {
                   	$('#rememberMe').attr("checked", true);
                   }
                   form.render(); //重新渲染
           	  $("input[type=reset]").trigger("click");//触发reset按钮
           	  $("#yonghumima").val('');
                 layer.msg(res.msg, {icon: 0, time: 3000});
               }else{
               	layer.msg(res.msg, {icon: 0, time: 3000}, function () {
                   	$("input[type=reset]").trigger("click");//触发reset按钮
                   	$("#yonghumima").val('');
                   	$('#rememberMe').attr("checked", "false");
                  	 form.render();
                       location.reload(); // 页面刷新
                       return false
                   })
               }  
           }, 'json');
        }
	});
</script>
</body>
</html>