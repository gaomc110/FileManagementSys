package cn.com.sparknet.common.util;

import javax.servlet.http.HttpServletRequest;

/*
* @author 作者 xuef
* @version 创建时间：2015年9月9日 下午6:24:04
* 类说明
*/
public class WebUtil {

	/**
	 * 判断是否ajax请求
	 * @param request 
	 * @return
	 */
	public static boolean isAjaxRequest(HttpServletRequest request){
		String head = request.getHeader("X-Requested-With");
		if("XMLHttpRequest".equals(head)){
			return true;
		}else{
			return false;
		}
	}
}
