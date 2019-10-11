/**
 * Created by gaogao on 2019/8/8 表格选中
 */
//config的设置是全局的
layui.config({
  base: '../../common/js/' //假设这是test.js所在的目录
}).extend({ //设定模块别名

});

layui.use(['laypage', 'table','element','layer','jquery','form'], function(exports){
        var $ = layui.jquery,
        element = layui.element,
        layer = layui.layer,
        form = layui.form,
        laypage = layui.laypage,
        table = layui.table;
        
        var projId = $("#theme_id_modify",window.parent.document).val();
        if(projId == "" || projId == null || projId == undefined){
        	projId = $("#theme_id_modifytow",window.parent.document).val();
        }
        
       //关闭档案馆弹出框
        $(".closearchivesBtn").on('click',function(){
        	$('#addArchives-form')[0].reset();
        	layui.form.render();
        	layer.closeAll();
        })
        
        //关闭档案室弹出框
        $(".closeBtn").on('click',function(){
        	$('#add-form')[0].reset();
        	layui.form.render();
        	layer.closeAll();
        }) 
        
        //关闭档案室弹出框
        $(".closedagBtn").on('click',function(){
        	$('#adddag-form')[0].reset();
        	layui.form.render();
        	layer.closeAll();
        })
        
        //加载档案馆下拉菜单
        /*$.ajax({
            type : "post",
            url : "",
            success : function(resp) {
	            if (resp.code == 0) {
	                var data=resp.data;
	                $.each(data, function (index, item) {
	                    $('#Archivesselect').append(new Option(item.sta_name, item.id));
	                });
	                layui.form.render("select"); //刷新表单select选择框渲染
	            }
            }
        })*/
        
        
        var $carrynewsArchives = $('#carrynewsArchives'),$removesArchives=$("#removesArchives");
       //新建档案馆
        $carrynewsArchives.on('click' , function(){
        	 layer.open({
                 type: 1,
                 title:"新增档案馆",
                 shift: 2,
                 area: ['590px', '450px'],
                 shadeClose: false,
                 content: $("#addArchives-main")
             });
        	 
        	 
        	 form.render();
    	});
        
        //选择档案馆加载档案室
        form.on('select(Archivesselect)', function(data){
             console.log(data.value);
             //加载档案室列表
             $.ajax({
                 type : "post",
                 url : "",
                 success : function(resp) {
     	            if (resp.code == 0) {
     	                var data=resp.data;
     	               //先清空档案室和档案柜列表
     	                $("#divall").empty();
     	                $("#divdagall").empty();
     	                $.each(data, function (index, item) {
     	                   var li='<li data-room="ROOM0001">'+
			 						  '<dl>'+
									     '<dd>101室</dd>'+
									     '<dd>ROOM0001</dd>'+
									  '</dl>'+
									  '<dl>'+
									     '<dd><a href="javascript:void(0)" class="editthisdas" data-room="ROOM0001"><i class="iconfont">&#xec74;</i>编辑</a></dd>'+
									     '<dd><a href="javascript:void(0)" class="removethisdas" data-room="ROOM0001"><i class="iconfont">&#xec8d;</i>删除</a></dd>'+
									  '</dl>'+
									'</li>';
     	                   $("#divall").append(li);
     	                });
     	                layui.form.render("select"); //刷新表单select选择框渲染
     	            }
                 }
             })
        })
        
        
//////////////////////////////////////        
        //左边档案室
        var $parentdas = $('#divall'),$bgcolordas = $('#divall li'),$carrynewsdas = $('#carrynewsdas'),
        $removethisdas = $('.removethisdas'),$editthisdas=$(".editthisdas");
    	//新建档案室
        $carrynewsdas.on('click' , function(){
        	var ROOMID=$('#divall li:last').attr('data-room');
        	var num=parseInt(ROOMID.substring(4));
        	var numstr=num.toString();
        	var num = num + 1;
        	ROOMID = ROOMID.substring(ROOMID,ROOMID.length-numstr.length,ROOMID.length-4) + num;
        	$("#dasid").val(ROOMID);
        	 layer.open({
                 type: 1,
                 title:"新增档案室",
                 shift: 2,
                 area: ['590px', '450px'],
                 shadeClose: false,
                 content: $("#add-main")
             });
        	 
        	 
        	 form.render();
    	});
        
        
        //档案室表单提交监听事件
        form.on('submit(dassave)', function (data) {
              params = data.field;
              
              //验证档案室名称是否存在
              $.post('', {dasname:params.dasname}, function (res) {
                  if (res.status==1) {
                       //存在
                  }else{
                      //不存在执行添加档案室操作
                	  $.ajax({
                  		        type:"POST",
                  		        url:"",
                  	            data:params,
                  		        dataType:"json",
                  		        success:function(data){
                  		           if(data.success){
                  		               //执行添加操作
                  		           }else{
                  		               
                  		           }
                  		        },
                  		        error:function(jqXHR){
                  		           console.log("发生错误："+ jqXHR.status);
                  		        }
                  		});
                  } 
              }, 'json');
              
              return false;
        }) 
           
        
        
        //编辑档案室
        $editthisdas.on('click' , function(){
        	  $.ajax({
        		        type:"POST",
        		        url:"",
        	            data:{roomid:$(this).attr("data-room")},
        		        dataType:"json",
        		        success:function(data){
        		           if(data.success){
        		               $("#dasid").val(data[0].id);
        		               $("#dasname").val(data[0].name);
        		               $("#dasremarks").val(data[0].remark);
        		           }else{
        		               
        		           }
        		        },
        		        error:function(jqXHR){
        		           console.log("发生错误："+ jqXHR.status);
        		        }
        		});
        	
        	layer.open({
                type: 1,
                title:"编辑档案室",
                shift: 2,
                area: ['590px', '450px'],
                shadeClose: false,
                content: $("#add-main")
            });
        })

    	//档案室选择变色并执行查询档案柜列表
        $bgcolordas.on('click' , function(){ 
           $(this).addClass('bgclocrc').siblings().removeClass('bgclocrc');
        	var roomid=$(this).attr("data-room");
        	$.ajax({
        		        type:"POST",
        		        url:"",
        	            data:{roomid:roomid},
        		        dataType:"json",
        		        success:function(data){
        		           if(data.success){
        			           //先清空档案柜列表
        			           $("#divdagall").empty();
        			           var data=[{id:"BOX001",name:"",zb:"3/20"}];
        			           //遍历插入
        			           for (var i = 0; i < data.length; i++) {
        			        	   var li = '<li data-box="BOX001" class="wenjian-y">'+
        							  '<dl>'+
        							     '<dd>第一档案柜</dd>'+
        							     '<dd>BOX001</dd>'+
        							     '<dd>'+
        							         '<div class="loader">'+
        										  '<div class="flash">'+
        										    '<div class="gradient"></div>'+
        										  '</div>'+
        										  '<div class="flair">'+
        										    '<div class="hide">'+
        										      '<div class="inset"></div>'+
        										    '</div>'+
        										  '</div>'+
        										  '<div class="loaded" style="width: 35%;"></div>'+
        										  '<div class="notloaded" style="width: 65%;"></div>'+
        										  '<div class="text">占比3/20</div>'+
        									  '</div>'+
        							     '</dd>'+
        							  '</dl>'+
        							  '<dl>'+
        							     '<dd><a href="javascript:void(0)" data-box="BOX001"><i class="iconfont">&#xec74;</i>编辑</a></dd>'+
        							     '<dd><a href="javascript:void(0)" data-box="BOX001" class="removethisdag"><i class="iconfont">&#xec8d;</i>删除</a></dd>'+
        							  '</dl>'+
        							'</li>';
        			        	     $("#divdagall").append(li);
        			           }
        			           
        		           }else{
        		               layer.alert('<h3>操作失败</h3><p>档案柜列表加载失败！</p>', {
	                            title:'提示'
	                                ,skin: 'demo-class5' //样式类名
	                                ,btn:0
	                                ,cancel: function () {
	                                   console.log(1);
	                                }
	                        	});
        		                 return false;
        		           }
        		        },
        		        error:function(jqXHR){
        		           console.log("发生错误："+ jqXHR.status);
        		        }
        		});
        	
        	
	    	  
    	});
 
    	
    	//删除档案室
    	$removethisdas.on('click' , function(){
    		 var roomid=$(this).attr("data-room");
    		 layer.alert("<span>删除相关文件后不可恢复，您要确定删除该文件？</span>", { //样式类名  自定义样式
                title:'删除档案室'
                , skin: 'demo-class1' //样式类名
                , btn: ['删除', '取消'] //按钮
                , yes: function () {
                	//执行删除档案室操作
                	$.ajax({
                		        type:"POST",
                		        url:"",
                	            data:{roomid:roomid},
                		        dataType:"json",
                		        success:function(data){
                		           if(data.success){
                			           //成功后页面执行删除操作
                		               $("ul#divall li[data-room="+roomid+"]").remove();
				                    	layer.closeAll('dialog');
				                    	return false;
                		           }else{
                		               layer.alert('<h3>操作失败</h3><p>删除档案室失败！</p>', {
			                            title:'提示'
			                                ,skin: 'demo-class5' //样式类名
			                                ,btn:0
			                                ,cancel: function () {
			                                   console.log(1);
			                                }
			                        	});
                		                 return false;
                		           }
                		        },
                		        error:function(jqXHR){
                		           console.log("发生错误："+ jqXHR.status);
                		        }
                		});
                	
                	
                }
	    	})
    		
    			
    	})	

    	
/////////////////////////////////    	
    	//右边档案柜
    	var $parentdag = $('#divdagall'),$bgcolordag = $('#divdagall li'),$carrynewsdag = $('#carrynewsdag'),
        $removethisdag = $('.removethisdag'),$editthisdag=$(".editthisdag");
    	//新建档案柜
    	$carrynewsdag.on('click' , function(){
    		var BOXID=$('#divdagall li:last').attr('data-box');
        	var num=parseInt(BOXID.substring(3));
        	var numstr=num.toString();
        	var num = num + 1;
        	BOXID = BOXID.substring(BOXID,BOXID.length-numstr.length,BOXID.length-3) + num;
        	$("#dagid").val(BOXID);
        	 layer.open({
                 type: 1,
                 title:"新增档案柜",
                 shift: 2,
                 area: ['590px', '450px'],
                 shadeClose: false,
                 content: $("#adddag-main")
             });
        	 
        	 form.render();
    		
    	});
    	
    	
    	 //编辑档案柜
        $editthisdag.on('click' , function(){
        	  $.ajax({
        		        type:"POST",
        		        url:"",
        	            data:{boxid:$(this).attr("data-box")},
        		        dataType:"json",
        		        success:function(data){
        		           if(data.success){
        		               $("#dagid").val(data[0].id);
        		               $("#dagname").val(data[0].name);
        		               $("#dagremarks").val(data[0].remark);
        		           }else{
        		               
        		           }
        		        },
        		        error:function(jqXHR){
        		           console.log("发生错误："+ jqXHR.status);
        		        }
        		});
        	
        	layer.open({
                type: 1,
                title:"编辑档案柜",
                shift: 2,
                area: ['590px', '450px'],
                shadeClose: false,
                content: $("#adddag-main")
            });
        })

    	//变色
    	$bgcolordag.on('click' , function(){ 
	    	   $(this).addClass('bgclocrc').siblings().removeClass('bgclocrc');
    	});
 
    	
    	//删除档案柜
    	$removethisdag.on('click' , function(){
    			$("ul#divdagall li[data-box="+$(this).attr("data-box")+"]").remove();
    			return false;
    	})	
        
})