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
        
      
        var projId = $("#theme_id_modifytow",window.parent.document).val();
        var databtnjosn=JSON.parse(projId);
        $.each(databtnjosn.databtn,function(index,item){
        	var btnname=databtnjosn.databtn[index].MENU_NAME;
            if(btnname=="新增"){
            	$("#contentlisttile").append('<button id="adduser" class="layui-btn fr"><i class="iconfont">&#xe75e;</i>新增</button>');
            }
        });
        
        
        //执行渲染
        table.render({
        	  elem: '#udateTable' //指定原始表格元素选择器（推荐id选择器）
              ,url:getPath()+'/UserController/getUserRList.do'
              ,method: 'post'
        	  ,page: true
        	  ,id: 'iduTest'
        	  ,limits: [10,20,50,100]
        	  ,limit: 10 //默认采用60
        	  ,cellMinWidth: 80
        	  ,height: 'full-230' //容器高度   
        	  ,loading:true
        	  ,cols:  [[ //标题栏
        			 {field: 'RN', title: '序号', minWidth:60,align:'center',type:'numbers',width:60}
                     ,{field: 'U_NAME', title: '用户名/账号', minWidth: 135,align:'center'}
                     ,{field: 'FULLNAME', title: '真实姓名', minWidth: 135,align:'center'}
                     ,{field: 'ROLENAME', title: '所属角色', minWidth: 100,align:'center'}
                     ,{field: 'CREATE_PRESON', title: '创建人', minWidth: 100,align:'center'}
                     ,{field: 'CREATE_DATE', title: '创建时间', minWidth: 100,align:'center'}
        			 ,{field: 'caozhuo', title: '操作',minWidth:150, align:'center',templet:function(d){
        				 var divcontent='';  
        				 $.each(databtnjosn.databtn,function(index,item){
        			        	var btnname=databtnjosn.databtn[index].MENU_NAME;
        			            if(btnname=="编辑"){
        			            	divcontent+='<a class="layui-btn layui-btn-xs" lay-event="uedit">编辑</a>';
        			            }else if(btnname=="删除"){
        			            	divcontent+='<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="udel">删除</a>';
        			            }
        			        	
        			        });
        				   return divcontent;
        			   }
        			 }
        	  ]]
        	  ,request: {
        			pageName: 'page' //页码的参数名称，默认：page
        		   ,limitName: 'limit' //每页数据量的参数名，默认：limit
              }  
        	  ,where: {loginid:loginid}
        	  
        });
          
          //监听表格复选框选择
          table.on('checkbox(udateTable)', function(obj){
          });
          //监听工具条
          table.on('tool(udateTable)', function(obj){
            var data = obj.data;
            if(obj.event === 'udetail'){
            	
            } else if(obj.event === 'udel'){
            				
            	                  layer.confirm('确定要删除吗？', function(index){
			        				    var USERID= data.U_ID;
									    console.log(USERID);
			        					layer.close(index);
				        					if(USERID!=loginid && data.USER_NAME!=loginName){
				        						$.ajax({
				    								url:getPath()+'/UserController/getUserDel.do',
				    								type:"post",
				    								data:{USERID:USERID},
				    								async:true,
				    								success:function(data){
				    								     if(data.isdel==true){
				    									    layer.msg('删除成功', {icon: 6});
				    										table.reload('iduTest',{
				    											page: {
				    							                      curr: 1 //重新从第 1 页开始
				    							                  }
				    										});//重新加载表格数据
				    									}else{
				    										layer.alert(data.msg); 
				    									}
				    								},
				    								error:function(){
				    								  layer.msg('操作失败！', {icon: 5});
				    								}
				    						   });
				        					}else{
				        						layer.msg('没有该操作权限，删除失败！', {icon: 5});
				        					}
        			                 });
	      				  
            } else if(obj.event === 'uedit'){
            	
            	            $('#parentuserId').val(data.USER_ID);
			    			layer.ready(function(){
				    				  var bjyh=layer.open({
					    					type: 2,
					    					title: '编辑用户',
					    					area: ['800px', '490px'],
					  		  		        maxmin: false,
					  		  		        resize:false,
					  		  	            shade: 0.6, //遮罩透明度
					  		  	            anim: 1, //0-6的动画形式，-1不开启
					    					content: getPath()+"/jsp/Permissions/PersonEdit.jsp",
					    					success: function(layero, index){
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
    		reload: function(){
    		  var u_name = $('#u_name').val();
    		  var fullname = $('#fullname').val();
    		  table.reload('iduTest', {
    			  page: {
                      curr: 1 //重新从第 1 页开始
                  },
    			where: {
					u_name: u_name,
					fullname: fullname
    			}
    		  });
    		}
          };
          
          $('.demouTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
          });
        
          //添加
          $(document).on('click','#adduser',function(){
        	  
					  			var index=layer.open({
				        	      type:2,
				        	      title:false,
					  		      maxmin: false,
					  		      resize:false,
					  		      closeBtn :0,
					  	          shade: 0.6, //遮罩透明度
					  	          anim: 1, //0-6的动画形式，-1不开启
				        	      content:getPath()+"/jsp/BackstageManage/UserManageAdd.jsp"
					  			});
					  			layer.full(index);
	      				
          });
        
        
        
})       
       
     
        
  