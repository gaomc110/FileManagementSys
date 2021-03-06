var $,tab,active;
layui.config({
	base: 'common/js/'
}).use(['element', 'layer', 'navbar', 'tab','common'], function() {
	var layer = layui.layer,
		element = layui.element,
		common = layui.common,
		navbar = layui.navbar();
	    $ = layui.jquery;
		tab = layui.tab({
			elem: '.layout-nav-card', //设置选项卡容器
			contextMenu:true
		});
	
	 //初始化动态元素，一些动态生成的元素如果不设置初始化，将不会有默认的动态效果
    element.init();
	
	$.ajax({
        type: "post",
        url: getPath()+'/MenuController/getMenulist.do',
        data: {parid:1,loginid:loginid},
        async : false,
        success : function (data) {
        	var subnavs=[];
        	subnavs=data.data;
        	if(subnavs.length>0){
        		//设置navbar
        		navbar.set({
        				spreadOne: false,
        				elem: '#submenu',//存在navbar数据的容器ID
        				cached: false,
        				data: subnavs
        			});
        		//渲染navbar
        		navbar.render();
        		
        		$("#submenu ul").children("li").siblings('li').removeClass("layui-nav-itemed");
        	    //菜单只展开一个
        		$("#submenu ul li").bind('click',function(){
        			$(this).siblings().removeClass('layui-nav-itemed');
        		})
        		
        		//监听点击事件
        		navbar.on('click(side)', function(data) {
        			//先删除所有菜单
        			var tabtitle = $(".layui-tab-title li");
        	        var ids = new Array();
        	        $.each(tabtitle, function (i) {
        	            ids[i] = $(this).attr("lay-id");
        	        })
        	        active.tabDeleteAll(ids);
        	        
        	        var menuid=data.field.id;
        	        
        	        //加载点击菜单对应按钮操作权限
        	        $.ajax({
    	        	        type:"POST",
    	        	        url:getPath()+'/MenuController/getMenuBtnlist.do',
    	        	        data:{loginid:loginid,menu_id:menuid},
    	        	        dataType:"json",
    	        	        success:function(result){
		    	        		var jsonarr = {};
		     	                jsonarr["menu_id"] =menuid;
		     	                jsonarr["databtn"]= result;
		     	                jsonarr = JSON.stringify(jsonarr);
			     	            //再添加新菜单
			           			$("#theme_id_modifytow").val(jsonarr);
    	        	        },
    	        	        error:function(jqXHR){
    	        	           layer.alert('<h3>执行失败</h3><p>操作过程出现错误，请联系管理员！</p>', {
		    	                 title:'提示'
		    	                     ,skin: 'demo-class5' //样式类名
		    	                     ,btn:0
		    	             	});
    	        	        }
    	        	});
        	        
        			
        			var href= data.field.href+"?TABPROID="+menuid;
        	        var title = data.field.title;
        	        var id = menuid;
        	      
        	        data = {
        	            href: data.field.href+"?TABPROID="+menuid,
        	            icon: data.field.icon,
        	            title: data.field.title,
        	            id:menuid
        	        };
        	        tab.tabAdd(data);
        		});
        	}
        }
    });
	
	
	
	/*$('#submenu li dd').bind("click", function() {
		var parid= $(this).find("a").attr("data-id");
		$("#theme_id_modify").val(parid);
		console.log(2);
    })*/
    
    //点击左边菜单的首页添加tab
	$('.tab-menu').bind("click", function() {
			var obj = $(this);
			var _o = new jqmenu();  
	         _o.menuSetOption(obj);
	})

	function jqmenu(){
	   jqmenu.prototype.menuSetOption = function(obj) {
	        var $a = obj.children('a'),
	            href = $a.data('url'),
	            icon = $a.children('i:first').data('icon'),
	            title = $a.data('title'),
	            data = {
	                href: href,
	                icon: 'fa-home',
	                title: title,
	                id:'0'
	            }
	        tab.tabAdd(data);
	        element.tabChange('admin-tab', '0'); //切换到：首页

	    }
	}
	
	//iframe自适应  
	function resize(){  
	    var $content = $('.admin-nav-card .layui-tab-content');  
	    $content.height($(this).height() - 120);  
	    $content.find('iframe').each(function() {  
	        $(this).height($content.height());  
	    });  
	}  
	$(window).on('resize', function() {  
	    var $content = $('.admin-nav-card .layui-tab-content');  
	    $content.height($(this).height() - 120);  
	    $content.find('iframe').each(function() {  
	        $(this).height($content.height());  
	    });  
	}).resize(); 
	
	
	
	//触发事件  
	active = {
            tabAdd: function (url,title,id) {
                //新增一个Tab项
                element.tabAdd('admin-tab', {
                    title: title 
                  , content: '<iframe data-frameid="'+id+'" frameborder="0" name="content" scrolling="no" width="100%" src="' + url + '"></iframe>'
                  , id: id //实际使用一般是规定好的id，这里以时间戳模拟下
                })
                
            }
          , tabChange: function (id) {
              //切换到指定Tab项
              element.tabChange('admin-tab', id); //切换到：用户管理
              $("iframe[data-frameid='"+id+"']").attr("src",$("iframe[data-frameid='"+id+"']").attr("src"))//切换后刷新框架
          }
            , tabDelete: function (id) {
                element.tabDelete("admin-tab", id);//删除
            }
            , tabDeleteAll: function (ids) {//删除所有
                $.each(ids, function (i,item) {
                    element.tabDelete("admin-tab", item);
                })
            }
        };
	
    
    $(".rightmenu li").click(function () {
        if ($(this).attr("data-type") == "closethis") {
            active.tabDelete($(this).attr("data-id"))
        } else if ($(this).attr("data-type") == "closeall") {
            var tabtitle = $(".layui-tab-title li");
            var ids = new Array();
            $.each(tabtitle, function (i) {
                ids[i] = $(this).attr("lay-id");
            })

            active.tabDeleteAll(ids);
        }

        $('.rightmenu').hide();
    })
	

//退出系统
    var adminActive = {
        doLoginOut: function () {
            var url = $(this).data('href');
            //var rturl = $(this).data('rturl');
            if (url) {
                rturl = getPath()+'/LoginController.do?cleanSession=true';
                common.signOut('确认退出系统？', '请再次确认是否要退出系统！', url, rturl, 'post', 'json', {"loginName":loginName});
            }
            else {
                common.layerAlertE('链接错误！', '提示');
            }
			
        }

    };
    //注销
   $('.do-admin').on('click', function (event) {
        var type = $(this).data('type');
        adminActive[type] ? adminActive[type].call(this) : '';
        return false;
    });


	
	// 添加新窗口
	$("body").on("click","#rightbtninfo .layui-nav .layui-nav-item a",function(){
		if($(this).attr("data-id")!="" && $(this).attr("data-id") !=null ){
			//如果不存在子级
			if($(this).siblings().length == 0){
				   var data = {
		                href: $(this).attr("data-url"),
		                icon: $(this).attr("data-icon"),
		                title: $(this).text().replace(/(^\s*)|(\s*$)/g, ""),
		                id:$(this).attr("data-id")
		            };
				 //打开新窗口
				 tab.tabAdd(data);
				$('body').removeClass('site-mobile');  //移动端点击菜单关闭菜单层
			}
			$(this).parent("li").siblings().removeClass("layui-nav-itemed");
		}
		
	})
});


