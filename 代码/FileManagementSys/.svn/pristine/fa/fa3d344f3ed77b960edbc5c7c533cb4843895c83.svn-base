var ztreeModal=function(o){this.opt={dropDom:null,direction:'down',fun:function(){}};$.extend(this,this.opt,o);this.init();};$.extend(ztreeModal.prototype,{init:function(){var obj=this,direction='';if(obj.direction=='up'){direction='dropup';}else{direction='dropdown';}
this.ztreeId=obj.dropDom.attr('data-ztreeId'),temp='<div class="form-init-drop '+direction+'">'+
'<div class="input-group">'+
'<input name="peopleAddress" type="text" class="form-control" id="input_'+this.ztreeId+'" >'+
'<span  class="input-group-addon checkBtn" data-id="'+this.ztreeId+'">点击选择</span>'+
'</div>'+
'<div class="init-dropdown-menu" data-stopPropagation="true">'+
'<div id="'+this.ztreeId+'" class="ztree" ></div>'+
'</div>'+
'</div>';obj.dropDom.append(temp);this.bindEvent();},bindEvent:function(){var obj=this;$('body').on('click',function(){var $dropMenu=obj.dropDom.find('.init-dropdown-menu');if(!$dropMenu.is(":hidden")){$dropMenu.hide();}})
$("body").on('click','[data-stopPropagation]',function(e){e.stopPropagation();});obj.dropDom.on('click','.checkBtn',function(e){e.stopPropagation();var parent=$(this).parents('.form-init-drop');if(parent.find('.init-dropdown-menu').is(":hidden")){obj.fun(obj.ztreeId);}
parent.find('.init-dropdown-menu').slideToggle(100);})},hideZtree:function(value){this.dropDom.find('.init-dropdown-menu').slideToggle(100);$('#input_'+this.ztreeId).val(value);}});