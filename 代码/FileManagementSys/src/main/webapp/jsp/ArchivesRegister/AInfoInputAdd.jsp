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
<title>档案信息录入新增</title>
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
<style>
.layui-form-label {
    padding: 9px 3px;
    width: 97px;
}
</style>  
</head>
<body>
<h3 class="breadcrumbtilte">
  当前位置：档案登记>档案信息录入
</h3>
<div class="layui-tab-content container-fluid">
<!--头部搜索-->
<section class="panel panel-padding">
  <form class="layui-form daxxlrform">
      <div class="layui-row">
             <h2 class="dalrtitle">基本信息</h2>
      </div>
      <div class="layui-row layui-col-space30" style="border-bottom:1px solid #f3f1f1;    padding: 10px 0;">
         <div class="layui-col-xs2 layui-col-md2">
             <label class="layui-form-label">所属管区</label>
             <div class="layui-input-block">
             <select lay-search="">
		        <option value=""></option>
		      </select>
		      </div>
         </div>
         <div class="layui-col-xs2 layui-col-md2">
             <label class="layui-form-label">房屋类型</label>
             <div class="layui-input-block">
              <select lay-search="">
		        <option value=""></option>
		      </select>
		      </div>
         </div>
         <div class="layui-col-xs2 layui-col-md2">
            <label class="layui-form-label">档案编号</label>
             <div class="layui-input-block">
            <input type="text"  autocomplete="off" placeholder="请输入档案编号" class="layui-input">
            </div>
         </div>
      </div>
      
      <div class="layui-row layui-col-space30" style="border-bottom:1px solid #f3f1f1;padding: 10px 0;">
         <div class="layui-col-xs2 layui-col-md2">
             <label class="layui-form-label">小区名称</label>
             <div class="layui-input-block">
              <input type="text"  autocomplete="off" placeholder="请输入小区名称" class="layui-input">
             </div>
         </div>
         <div class="layui-col-xs2 layui-col-md2">
             <label class="layui-form-label">楼栋/幢号</label>
             <div class="layui-input-block">
              <input type="text"  autocomplete="off" placeholder="请输入楼栋/幢号" class="layui-input">
             </div>
         </div>
         <div class="layui-col-xs2 layui-col-md2">
             <label class="layui-form-label">丘权号</label>
             <div class="layui-input-block">
              <input type="text"  autocomplete="off" placeholder="请输入丘权号" class="layui-input">
             </div>
         </div>
         <div class="layui-col-xs2 layui-col-md2">
             <label class="layui-form-label">产权人</label>
             <div class="layui-input-block">
              <input type="text"  autocomplete="off" placeholder="请输入产权人" class="layui-input">
              </div>
         </div>
         <div class="layui-col-xs3 layui-col-md3">
            <label class="layui-form-label">房屋坐落地址</label>
             <div class="layui-input-block">
              <input type="text"  autocomplete="off" placeholder="请输入房屋坐落地址" class="layui-input">
            </div>
         </div>
      </div>
      <div class="layui-row layui-col-space30" style="    padding: 10px 0;">
         <div class="layui-col-xs2 layui-col-md2">
             <label class="layui-form-label">公安门牌号</label>
             <div class="layui-input-block">
              <input type="text"  autocomplete="off" placeholder="请输入公安门牌号" class="layui-input">
             </div>
         </div>
         <div class="layui-col-xs2 layui-col-md2">
             <label class="layui-form-label">工程编号</label>
             <div class="layui-input-block">
              <input type="text"  autocomplete="off" placeholder="请输入工程编号" class="layui-input">
             </div>
         </div>
       </div>
       <div class="layui-row">
             <h2 class="dalrtitle">文件清单 <button type="button" class="layui-btn layui-btn-sm fr" id="AddfileList"> <i class="iconfont">&#xec08;</i> 上传附件 </button> </h2>
       </div>
       <div class="layui-row">
           <div class="layui-col-md12">
              <div class="layui-upload-list">
				    <table class="layui-table">
				      <thead>
				        <tr>
				        <th>序号</th>
				        <th>文件名称</th>
				        <th>文件类型</th>
				        <th>大小</th>
				        <th>操作</th>
				      </tr></thead>
				      <tbody id="uploadfileList"></tbody>
				    </table>
				  </div>
           </div>
       </div>
       
       <div class="layui-row">
             <h2 class="dalrtitle">房屋交易信息<button type="button" class="layui-btn layui-btn-sm fr" id="addTransactionList"> <i class="iconfont">&#xe77c;</i> 添加交易信息 </button> </h2>
       </div>
       <div class="layui-row">
           <div class="layui-col-md12">
			    <table class="layui-table">
			      <thead>
			        <tr>
			        <th>序号</th>
			        <th>交易合同编号</th>
			        <th>交易类型</th>
			        <th>交易进度</th>
			        <th>交易日期</th>
			        <th>操作</th>
			      </tr></thead>
			      <tbody id="TransactionList"></tbody>
			    </table>
           </div>
       </div>
       <div class="layui-row marTop40">
           <div class="layui-col-xs2 layui-col-md2 layui-col-md-offset5">
                <button class="layui-btn layui-btn-normal" lay-submit="" lay-filter="demo1">确定</button>
                <button type="button" class="layui-btn layui-btn-primary fr">取消</button>
           </div>
       </div>    
</form>      
</section>
</div>

 <!-- 交易信息弹出框 -->
 <div id="addTransaction-main" style="display: none; padding:30px 88px;">
     <form class="layui-form" id="addTransaction-form">
         <div class="layui-form-item">
           <label class="layui-form-label">交易合同编号</label>
           <div class="layui-input-block">
             <input type="text" id="transactionscode" name="transactionscode" placeholder="请输入交易合同编号" autocomplete="off" class="layui-input">
           </div>
         </div>
         <div class="layui-form-item">
           <label class="layui-form-label" >交易类型</label>
           <div class="layui-input-block">
             	      <select  id="transactionstype" name="transactionstype">
				        <option value=""></option>
				      </select>
           </div>
         </div>
         <div class="layui-form-item">
           <label class="layui-form-label" >交易进度</label>
           <div class="layui-input-block">
                      <select  id="transactionschedule" name="transactionschedule">
				        <option value=""></option>
				      </select>
            </div>
          </div>
          <div class="layui-form-item">
           <label class="layui-form-label" >交易日期</label>
           <div class="layui-input-block">
                      <input type="text" id="transactionDate" name="transactionDate" placeholder="请输入交易日期" autocomplete="off"  class="layui-input">
            </div>
          </div>
          <div class="layui-form-item">
            <div class="layui-input-block" style="margin-top:48px;margin-left: 78px;">
             <button class="layui-btn  layui-btn-normal"style="display: none;" type="reset">重置</button>
              <button class="layui-btn  layui-btn-normal" lay-submit lay-filter="Transactionsave" >确 定</button>
              <button type="button" style="margin-left: 108px;" class="layui-btn layui-btn-primary closeTransactionBtn" >取 消</button>
            </div>
          </div> 
      </form>    
</div>
<script type="text/javascript" src="<%=path%>/common/plugins/jquery/jquery-1.10.2.min.js" charset="utf8"></script>
<script type="text/javascript" src="<%=path%>/common/plugins/layui/layui.js"></script>
<script type="text/javascript" src="js/AInfoInputAdd.js"></script>
 </body>
 </html>     