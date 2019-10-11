/**
 * Created by gaogao on 2019/8/23 表格选中
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
        
        //执行渲染
        table.render({
        	  elem: '#udateTable' //指定原始表格元素选择器（推荐id选择器）
              ,url:getPath()+'/ArchivesQueryController/getFilelocationList.do'
              ,method: 'post'
    		  /*,data: [{
		                "COMMUNITY_NAME": "新福家园",
		                "QIUQUAN_ID": "A001-10001",
		                "BUILDINGNIMBER":"1",
		                "PROPERTYOWNER":"张爱明",
		                "HOUSLOCATIONADRESS":"南京市浦口区泰山街道67号",
		                "FILESTORAGELOCATION":"第一档案馆-101室-第一档案柜",
		                "FILINGTIME": "2017-11-10 11:34"
		            },
		            {
		            	"COMMUNITY_NAME": "新福家园",
		                "QIUQUAN_ID": "A001-10002",
		                "BUILDINGNIMBER":"2",
		                "PROPERTYOWNER":"赵亮",
		                "HOUSLOCATIONADRESS":"南京市浦口区泰山街道201号",
		                "FILESTORAGELOCATION":"第一档案馆-101室-第一档案柜",
		                "FILINGTIME": "2017-11-10 11:34"
		            }]  */
        	  ,page: true
        	  ,id: 'iduTest'
        	  ,limits: [10,20,50,100]
        	  ,limit: 10 //默认采用60
        	  ,cellMinWidth: 80
        	  ,height: 'full-230' //容器高度
        	  ,loading:true
        	  ,cols:  [[ //标题栏
        			 {field: 'RN', title: '序号', minWidth:60,align:'center',type:'numbers',width:60}
                     ,{field: 'COMMUNITY_NAME', title: '小区名称', minWidth: 120,align:'center'}
                     ,{field: 'QIUQUAN_NO', title: '丘权号', minWidth: 120,align:'center'}
                     ,{field: 'BUILDING_NO', title: '楼栋号', minWidth: 80,align:'center'}
                     ,{field: 'PROPERTY_OWNER', title: '产权人', minWidth: 80,align:'center'}
                     ,{field: 'HOUSE_ADRESS', title: '房屋坐落地址', width: 250,align:'center'}
                     ,{field: 'FILE_LOCATION', title: '档案存储位置', width: 250,align:'center'}
                     ,{field: 'CREATE_DATE', title: '归档时间', minWidth: 100,align:'center'}
        	  ]]
        	  ,request: {
        			pageName: 'page' //页码的参数名称，默认：page
        		   ,limitName: 'limit' //每页数据量的参数名，默认：limit
              }  
        	  ,where: {loginid:loginid}
        	  
        });
          
          var $ = layui.$, active = {
            //搜索
    		reload: function(){
    		  var communityName = $('#communityName').val();
    		  var houseAddress = $('#houseAddress').val();
    		  var qiuquanNo = $('#qiuquanNo').val();
    		  var propertyOwner = $('#propertyOwner').val();
    		  table.reload('iduTest', {
    			  page: {
                      curr: 1 //重新从第 1 页开始
                  },
    			where: {
					communityName: communityName,
					houseAddress: houseAddress,
					qiuquanNo: qiuquanNo,
					propertyOwner: propertyOwner
    			}
    		  });
    		}
          };
          
          $('.demouTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
          });
        
})       
       
     
        
  