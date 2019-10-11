/** common.js 公共类提交或提示*/
layui.define(['layer'], function(exports) {
	"use strict";

	var $ = layui.jquery,
		layer = layui.layer;

	var common = {
		/**
		 * 抛出一个异常错误信息
		 * @param {String} msg
		 */
		throwError: function(msg) {
			throw new Error(msg);
			return;
		},
		/**
		 * 弹出一个错误提示
		 * @param {String} msg
		 */
		msgError: function(msg) {
			layer.msg(msg, {
				icon: 5
			});
			return;
		},
        ajax: function (url, type, dataType, data, callback) {
            $.ajax({
                url: url,
                type: type,
                dataType: dataType,
                data: data,
                success: function (data, startic) {
                    if (data.state == 1) {
                        location.href = location.href;
                        obj.layerAlertS(data.message, '提示');
                    }
                    else {
                        obj.layerAlertE(data.message, '提示');
                    }
                },
                error: function () {

                }
            });
        },
        layerDel: function (title, text, url, type, dataType, data, callback) {
            parent.layer.confirm(text, {
                title: title,
                btnAlign: 'c',
                resize: false,
                icon: 3,
                btn: ['确定删除', '取消'],
                yes: function () {
                    obj.ajax(url, type, dataType, data, callback);
                }
            });
        },
        //成功提示
        layerAlertS: function (text, title) {
            parent.layer.alert(text, { title: title, icon: 1, time: 5000, resize: false, zIndex: layer.zIndex, anim: Math.ceil(Math.random() * 6) });
        },
        //错误提示
        layerAlertE: function (text, title) {
            parent.layer.alert(text, { title: title, icon: 2, time: 5000, resize: false, zIndex: layer.zIndex, anim: Math.ceil(Math.random() * 6) });
        },
        //信息提示
        layerAlertI: function (text) {
            parent.layer.alert(text, { time: 5000, resize: false, zIndex: layer.zIndex, anim: Math.ceil(Math.random() * 6) });
            return;
        },
		
		
        layerPrompt: function () {
        },
        //询问层
        layerConfirm: function () {
        },
        //退出系统
        signOut: function (title, text, url,rturl,type, dataType, data, callback) {
        	
        	layer.alert("<p>"+text+"</p>", { //样式类名  自定义样式
                title:title
                ,skin: 'demo-class2' //样式类名
                ,btn: ['确定', '取消'] //按钮
                ,yes: function () {
                	$.ajax({
                        url: rturl,
                        type: type,
                        dataType: dataType,
                        data: data,
                        success: function (data) {
                        	location.href = url;
                        }
                      })
                }
	    	})
        	
        	
            /*parent.layer.confirm(text, {
                title: title,
                resize: false,
                btn: ['确定', '取消'],
                btnAlign: 'c',
                icon: 3
            }, function () {
            	$.ajax({
                    url: rturl,
                    type: type,
                    dataType: dataType,
                    data: data,
                    success: function (data) {
                    	location.href = url;
                    }
                  })
            }, function () {
                
            });*/
        }		
	};

	exports('common', common);
});