/**
 * Created by gaogao on 2019/8/8 表格选中
 */
//config的设置是全局的
layui.config({
  base: '../../common/js/' //假设这是test.js所在的目录
}).extend({ //设定模块别名

});

layui.use(['laypage', 'table','element','layer','jquery','form','upload','laydate'], function(exports){
        var $ = layui.jquery,
        element = layui.element,
        layer = layui.layer,
        form = layui.form,
        laypage = layui.laypage,
        upload = layui.upload,
        laydate=layui.laydate,
        table = layui.table;
        
      //多文件列表示例
        var demoListView = $('#uploadfileList')
        ,uploadListIns = upload.render({
          elem: '#AddfileList'
          ,url: '/upload/'
          ,accept: 'file'
          ,multiple: true
          ,auto: false
          ,bindAction: '#testListAction'
          ,choose: function(obj){
            var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
            //读取本地文件
            obj.preview(function(index, file, result){
              var tr = $(['<tr id="upload-'+ index +'">'
                ,'<td>'+ index +'</td>'
                ,'<td>'+ file.name +'</td>'
                ,'<td>无</td>'
                ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
                ,'<td>'
                  ,'<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>'
                  ,'<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>'
                ,'</td>'
              ,'</tr>'].join(''));
              
              //单个重传
              tr.find('.demo-reload').on('click', function(){
                obj.upload(index, file);
              });
              
              //删除
              tr.find('.demo-delete').on('click', function(){
                delete files[index]; //删除对应的文件
                tr.remove();
                uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
              });
              
              demoListView.append(tr);
            });
          }
          ,done: function(res, index, upload){
            if(res.code == 0){ //上传成功
              var tr = demoListView.find('tr#upload-'+ index)
              ,tds = tr.children();
              tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
              tds.eq(3).html(''); //清空操作
              return delete this.files[index]; //删除文件队列已经上传成功的文件
            }
            this.error(index, upload);
          }
          ,error: function(index, upload){
            var tr = demoListView.find('tr#upload-'+ index)
            ,tds = tr.children();
            tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
            tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
          }
        });
        
        //添加交易信息弹出框
        $("#addTransactionList").on('click' , function(){
        	laydate.render({
        	    elem: '#transactionDate',
        	    format: 'yyyy-MM-dd',
        	    value: new Date()
        	});
        	
	       	 layer.open({
	                type: 1,
	                title:"添加交易信息",
	                shift: 2,
	                area: ['590px', '450px'],
	                shadeClose: false,
	                content: $("#addTransaction-main")
	            });
	       	 form.render();
	   	});
        
       //关闭交易信息弹出框
        $(".closeTransactionBtn").on('click',function(){
        	$('#addTransaction-form')[0].reset();
        	layui.form.render();
        	layer.closeAll();
        })
        
})       
       
     
        
  