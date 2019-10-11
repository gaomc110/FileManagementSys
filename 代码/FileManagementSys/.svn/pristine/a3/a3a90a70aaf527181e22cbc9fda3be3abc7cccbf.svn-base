layui.define(function(exports){
	var objectUtil;
	objectUtil = {	
			dataCover:function(count) {
				 var n = count;
				    var converArr = [];
				    if (n < 10000) {
				        converArr.push(n);
				        converArr.push("");
				        return converArr;

				    } else if (n >= 10000) {
				        n = n / 10000;
				        if (n < 10000) {
				            converArr.push(n.toFixed(2));
				            converArr.push("（万）");
				            return converArr
				        } else {
				            n = (n / 10000) ;
				            converArr.push(n.toFixed(2));
				            converArr.push("（亿）");
				            return converArr
				        }
				    }
			},
			//每隔三位小数加逗号
			addCommas:function(nStr)
			{
				var kk="";
				nStr+='';
				if(nStr.length<9){
					kk= (Array(9).join('0') + nStr).slice(-9);
				}else{
					kk=nStr;
				}
				x = kk.split('.');
				x1 = x[0];
				x2 = x.length > 1 ? '.' + x[1] : '';
				var rgx = /(\d+)(\d{3})/;
				while (rgx.test(x1)) {
					x1 = x1.replace(rgx, '$1' + ',' + '$2');
				}
				return x1 + x2;
			},
			//单位换算不加括号
			dataCover2:function(count) {
				 var n = count;
				    var converArr = [];
				    if (n < 10000) {
				        converArr.push(n);
				        converArr.push("");
				        return converArr;

				    } else if (n >= 10000) {
				        n = Math.round((n / 10000) * 100) / 100;
				        if (n < 10000) {
				            converArr.push(n);
				            converArr.push("万");
				            return converArr
				        } else {
				            n = Math.round((n / 10000) * 100) / 100;
				            converArr.push(n);
				            converArr.push("亿");
				            return converArr
				        }
				    }
			},
			//存储量M,G单位转换
			dataCover1:function(count) {
			    var n = count;
			    var converArr = [];
			    n = Math.round(((Math.round((n / 1024) * 100) / 100) / 1024) * 100) / 100;    
			    if (n < 1024) {
			        converArr.push(n);
			        converArr.push("（M）");
			        return converArr;
			    } else{
			        n = Math.round((n / 1024) * 100) / 100;
			            converArr.push(n);
			            converArr.push("（G）");
			            return converArr
			    }
			},
			//不加0每隔3位加逗号
			add_comma_toThousands:function(num) {
			    var num = (num || 0).toString();
			    var result = '';
			    while (num.length > 3) {
			        result = ',' + num.slice(-3) + result;
			        num = num.slice(0, num.length - 3);
			    }
			    if (num) { result = num + result; }
			    return result;
			}
	
	}		
	//输出test接口
	exports('objectUtil', objectUtil);
}); 	