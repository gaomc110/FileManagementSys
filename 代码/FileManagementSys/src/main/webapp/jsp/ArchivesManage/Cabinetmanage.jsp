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
<title>柜室管理</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="<%=path%>/common/plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" type="text/css" href="<%=path%>/common/css/font/iconfont.css" media="all">
<link rel="stylesheet" href="<%=path%>/common/css/style.css"/>
<link rel="stylesheet" href="<%=path%>/common/css/ModuleStyle.css"/>
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
<style>
html,body{height:100%;}
</style>
<body>
<h3 class="breadcrumbtilte">
  当前位置：档案管理>柜室管理
</h3>
<div class="layui-tab-content container-fluid" style="border-bottom: 1px solid #f6f6f6;">
<!--头部搜索-->
<section class="panel panel-padding demouTable">
      <div class="layui-row layui-form" style="height: 72px; padding-top:20px;">
	    <div class="layui-col-xs8 layui-col-md8">
	       <div class="layui-form-item fr">
	           <label class="layui-form-label colorf08726 fontsize18" style="width: 90px;">您当前在：</label>
	           <div class="layui-input-inline dagselect" style="width: 310px;">
	                  <select id="Archivesselect" lay-filter="Archivesselect">
				        <option value="D0001">第一档案馆</option>
				        <option value="D0002">第二档案馆</option>
				        <option value="D0003">第三档案馆</option>
				        <option value="D0004">第四档案馆</option>
				      </select>
	           </div>
	       </div>    
	    </div>
	    <div class="layui-col-xs4 layui-col-md4 text-right">
	        <button class="layui-btn" id="carrynewsArchives" style="margin-left:17px;"><i class="iconfont">&#xe75e;</i> 新增档案馆</button>
	        <button class="layui-btn" id="removesArchives" style="margin-left:17px;"><i class="iconfont">&#xe614;</i> 删除档案馆</button>
	    </div>
	  </div>
</section>
</div>
<div class="container-fluid" style=" height:calc(100% - 160px);">
<!--柜室列表-->
        <div class="dagcontnt">
            <div class="dasleft">
                  <h3 class="dastitle">档案室列表</h3>
		          <ul id="divall">
						<li data-room="ROOM0001">
						  <dl>
						     <dd>101室</dd>
						     <dd>ROOM0001</dd>
						  </dl>
						  <dl>
						     <dd><a href="javascript:void(0)" class="editthisdas" data-room="ROOM0001"><i class="iconfont">&#xec74;</i>编辑</a></dd>
						     <dd><a href="javascript:void(0)" class="removethisdas" data-room="ROOM0001"><i class="iconfont">&#xec8d;</i>删除</a></dd>
						  </dl>
						</li>
						<li data-room="ROOM0002">
						  <dl>
						     <dd>102室</dd>
						     <dd>ROOM0002</dd>
						  </dl>
						  <dl>
						     <dd><a href="javascript:void(0)" class="editthisdas" data-room="ROOM0002"><i class="iconfont">&#xec74;</i>编辑</a></dd>
						     <dd><a href="javascript:void(0)" class="removethisdas" data-room="ROOM0002"><i class="iconfont">&#xec8d;</i>删除</a></dd>
						  </dl>
						</li>
						<li data-room="ROOM0003">
						  <dl>
						     <dd>103室</dd>
						     <dd>ROOM0003</dd>
						  </dl>
						  <dl>
						     <dd><a href="javascript:void(0)" class="editthisdas" data-room="ROOM0003"><i class="iconfont">&#xec74;</i>编辑</a></dd>
						     <dd><a href="javascript:void(0)" class="removethisdas" data-room="ROOM0003"><i class="iconfont">&#xec8d;</i>删除</a></dd>
						  </dl>
						</li>
						<li data-room="ROOM0004">
						  <dl>
						     <dd>104室</dd>
						     <dd>ROOM0004</dd>
						  </dl>
						  <dl>
						     <dd><a href="javascript:void(0)" class="editthisdas" data-room="ROOM0004"><i class="iconfont">&#xec74;</i>编辑</a></dd>
						     <dd><a href="javascript:void(0)" class="removethisdas" data-room="ROOM0004"><i class="iconfont">&#xec8d;</i>删除</a></dd>
						  </dl>
						</li>
						<li data-room="ROOM0005">
						  <dl>
						     <dd>105室</dd>
						     <dd>ROOM0005</dd>
						  </dl>
						  <dl>
						     <dd><a href="javascript:void(0)" class="editthisdas" data-room="ROOM0005"><i class="iconfont">&#xec74;</i>编辑</a></dd>
						     <dd><a href="javascript:void(0)" class="removethisdas" data-room="ROOM0005"><i class="iconfont">&#xec8d;</i>删除</a></dd>
						  </dl>
						</li>
						<li data-room="ROOM0006">
						  <dl>
						     <dd>106室</dd>
						     <dd>ROOM0006</dd>
						  </dl>
						  <dl>
						     <dd><a href="javascript:void(0)" class="editthisdas" data-room="ROOM0006"><i class="iconfont">&#xec74;</i>编辑</a></dd>
						     <dd><a href="javascript:void(0)" class="removethisdas" data-room="ROOM0006"><i class="iconfont">&#xec8d;</i>删除</a></dd>
						  </dl>
						</li>
					</ul>
					<div class="dasadd"><a href="javascript:void(0)" id="carrynewsdas"><i class="iconfont">&#xec7a;</i>新建档案室</a></div>
		     </div>
		     <div class="dasright">
		         <h3 class="dastitle">档案柜列表</h3>
		         <ul id="divdagall">
						<li data-box="BOX001" class="wenjian-y">
						  <dl>
						     <dd>第一档案柜</dd>
						     <dd>BOX001</dd>
						     <dd>
						         <div class="loader">
									  <div class="flash">
									    <div class="gradient"></div>
									  </div>
									  <div class="flair">
									    <div class="hide">
									      <div class="inset"></div>
									    </div>
									  </div>
									  <div class="loaded" style="width: 35%;"></div>
									  <div class="notloaded" style="width: 65%;"></div>
									  <div class="text">占比3/20</div>
								  </div>
						     </dd>
						  </dl>
						  <dl>
						     <dd><a href="javascript:void(0)" data-box="BOX001" class="editthisdag"><i class="iconfont">&#xec74;</i>编辑</a></dd>
						     <dd><a href="javascript:void(0)" data-box="BOX001" class="removethisdag"><i class="iconfont">&#xec8d;</i>删除</a></dd>
						  </dl>
						</li>
						<li data-box="BOX002" class="wenjian-n">
						  <dl>
						     <dd>第二档案柜</dd>
						     <dd>BOX002</dd>
						     <dd>
						         <div class="loader">
									  <div class="flash">
									    <div class="gradient"></div>
									  </div>
									  <div class="flair">
									    <div class="hide">
									      <div class="inset"></div>
									    </div>
									  </div>
									  <div class="loaded" style="width: 0;"></div>
									  <div class="notloaded" style="width: 100%;"></div>
									  <div class="text">占比0/20</div>
								  </div>
						     </dd>
						  </dl>
						  <dl>
						     <dd><a href="javascript:void(0)" data-box="BOX002" class="editthisdag"><i class="iconfont">&#xec74;</i>编辑</a></dd>
						     <dd><a href="javascript:void(0)" data-box="BOX002" class="removethisdag"><i class="iconfont">&#xec8d;</i>删除</a></dd>
						  </dl>
						</li>
						<li data-box="BOX003" class="wenjian-y">
						  <dl>
						     <dd>第三档案柜</dd>
						     <dd>BOX003</dd>
						     <dd>
						         <div class="loader">
									  <div class="flash">
									    <div class="gradient"></div>
									  </div>
									  <div class="flair">
									    <div class="hide">
									      <div class="inset"></div>
									    </div>
									  </div>
									  <div class="loaded" style="width: 100%;"></div>
									  <div class="notloaded" style="width: 0;"></div>
									  <div class="text">占比20/20</div>
								  </div>
						     </dd>
						  </dl>
						  <dl>
						     <dd><a href="javascript:void(0)" data-box="BOX003" class="editthisdag"><i class="iconfont">&#xec74;</i>编辑</a></dd>
						     <dd><a href="javascript:void(0)" data-box="BOX003" class="removethisdag"><i class="iconfont">&#xec8d;</i>删除</a></dd>
						  </dl>
						</li>
				 </ul>
				 <div class="dasadd"><a href="javascript:void(0)" id="carrynewsdag"><i class="iconfont">&#xec7a;</i>新建档案柜</a></div>
		     </div>
        </div>
 </div>
 
 <!-- 档案馆弹出框 -->
 <div id="addArchives-main" style="display: none; padding:30px 88px;">
     <form class="layui-form" id="addArchives-form">
         <div class="layui-form-item">
           <label class="layui-form-label">档案馆代码</label>
           <div class="layui-input-block">
             <input type="text" id="archiveid" name="archiveid" placeholder="请输入档案馆代码" autocomplete="off" disabled class="layui-input layui-disabled2">
           </div>
         </div>
         <div class="layui-form-item">
           <label class="layui-form-label" >档案馆名称</label>
           <div class="layui-input-block">
             <input type="text" name="archivename" id="archivename"  placeholder="请输入档案馆名称" autocomplete="off" class="layui-input">
           </div>
         </div>
         <div class="layui-form-item">
           <label class="layui-form-label" >备注</label>
           <div class="layui-input-block">
             <textarea name="archiveremarks" id="archiveremarks" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
          </div>
          <div class="layui-form-item">
            <div class="layui-input-block" style="margin-top:48px;margin-left: 78px;">
             <button class="layui-btn  layui-btn-normal"style="display: none;" type="reset">重置</button>
              <button class="layui-btn  layui-btn-normal" lay-submit lay-filter="archivessave" >确 定</button>
              <button type="button" style="margin-left: 108px;" class="layui-btn layui-btn-primary closearchivesBtn" >取 消</button>
            </div>
          </div> 
      </form>    
</div>
 
 <!-- 档案室弹出框 -->
 <div id="add-main" style="display: none; padding:30px 88px;">
     <form class="layui-form" id="add-form">
         <div class="layui-form-item">
           <label class="layui-form-label">档案室代码</label>
           <div class="layui-input-block">
             <input type="text" id="dasid" name="dasid" placeholder="请输入档案室代码" autocomplete="off" disabled class="layui-input layui-disabled2">
           </div>
         </div>
         <div class="layui-form-item">
           <label class="layui-form-label" >档案室名称</label>
           <div class="layui-input-block">
             <input type="text" name="dasname" id="dasname"  placeholder="请输入档案室名称" autocomplete="off" class="layui-input">
           </div>
         </div>
         <div class="layui-form-item">
           <label class="layui-form-label" >备注</label>
           <div class="layui-input-block">
             <textarea name="dasremarks" id="dasremarks" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
          </div>
          <div class="layui-form-item">
            <div class="layui-input-block" style="margin-top:48px;margin-left: 78px;">
             <button class="layui-btn  layui-btn-normal"style="display: none;" id="restbtn" type="reset">重置</button>
              <button class="layui-btn  layui-btn-normal" lay-submit lay-filter="dassave" >确 定</button>
              <button type="button" style="margin-left: 108px;" class="layui-btn layui-btn-primary closeBtn" >取 消</button>
            </div>
          </div> 
      </form>    
</div>


<!-- 档案柜弹出框 -->
 <div id="adddag-main" style="display: none; padding:30px 88px;">
     <form class="layui-form" id="adddag-form">
         <div class="layui-form-item">
           <label class="layui-form-label">档案柜代码</label>
           <div class="layui-input-block">
             <input type="text" id="dagid" name="dagid" placeholder="请输入档案柜代码" autocomplete="off" disabled class="layui-input layui-disabled2">
           </div>
         </div>
         <div class="layui-form-item">
           <label class="layui-form-label" >档案柜名称</label>
           <div class="layui-input-block">
             <input type="text" name="dagname" id="dagname"  placeholder="请输入档案柜名称" autocomplete="off" class="layui-input">
           </div>
         </div>
         <div class="layui-form-item">
           <label class="layui-form-label" >备注</label>
           <div class="layui-input-block">
             <textarea name="dagremarks" id="dagremarks" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
          </div>
          <div class="layui-form-item">
            <div class="layui-input-block" style="margin-top:48px;margin-left: 78px;">
              <button class="layui-btn  layui-btn-normal"style="display: none;" id="restdagbtn" type="reset">重置</button>
              <button class="layui-btn  layui-btn-normal" lay-submit lay-filter="dagsave" >确 定</button>
              <button type="button" style="margin-left: 108px;" class="layui-btn layui-btn-primary closedagBtn" >取 消</button>
            </div>
          </div> 
      </form>    
</div>
<script type="text/javascript" src="<%=path%>/common/plugins/jquery/jquery-1.10.2.min.js" charset="utf8"></script>
<script type="text/javascript" src="<%=path%>/common/plugins/layui/layui.js"></script>
<script type="text/javascript" src="<%=path%>/common/js/menu.js"></script>
<script type="text/javascript" src="js/Cabinetmanage.js"></script>
</body>
</html>