package cn.com.sparknet.common.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 安全漏洞过滤器
 * @author chenxy
 *
 */
public class SecurityFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		request.setCharacterEncoding("utf-8");
        HttpServletResponse response = ((HttpServletResponse) servletResponse);  

		// 解决缺少跨帧脚本编制防御漏洞（表示页面可以在相同域名下的frame中展示）
        response.addHeader("X-Frame-Options","SAMEORIGIN");

		// 解决跨站点脚本编制漏洞
		if(this.filterRequestKeys(request)){
			return;
		}else{
			request = this.filterRequestValues(request);
		}
		
		// 解决跨站点请求伪造漏洞
		String referer = request.getHeader("Referer");
		String serverName = request.getServerName();
		if (null != referer && referer.indexOf(serverName) < 0) {
			return;
		}

		chain.doFilter(request, response);
	}

	/**
	 * 判断参数是否存在特殊字符
	 */
	private boolean filterRequestKeys(HttpServletRequest request) {
		String key = "";
		Enumeration requestNames = request.getParameterNames();
		String[] blankStr = { "<", ">", "'", "\"", ":", "alert", "<script>" };
		while (requestNames.hasMoreElements()) {
			key = (String) requestNames.nextElement();
			for (int i = 0; i < blankStr.length; i++) {
				if (key.indexOf(blankStr[i]) > -1) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 过滤参数值中的特殊字符
	 */
	private HttpServletRequest filterRequestValues(HttpServletRequest request) {
		String key = "";
		String value = "";
		String[] values = null;
		//将HttpServletRequest装饰成HttpServletRequestWrapper
		CustomHttpServletRequestWrapper wrapRequest = new CustomHttpServletRequestWrapper(request, new HashMap(request.getParameterMap()));
		Map requestMap = wrapRequest.getParameterMap();
		Enumeration requestNames = request.getParameterNames();
		while (requestNames.hasMoreElements()) {
			key = (String) requestNames.nextElement();
			values = request.getParameterValues(key);
			for (int i = 0; i < values.length; i++) {
				value = values[i];
				value = value.replaceAll("&", "&amp;");
				value = value.replaceAll("<", "&lt;");
				value = value.replaceAll(">", "&gt;");
				//value = value.replaceAll("\"", "&quot;");
				//value = value.replaceAll("'", "‘");
//				value = value.replaceAll("%", "");
				value = value.replaceAll("eval", "");
				value = value.replaceAll("expression", "");
				value = value.replaceAll("unescape", "");
//				value = value.replaceAll("_", "");
//				value = value.replaceAll(",", "，");
//				value = value.replaceAll(":", "：");
//				value = value.replaceAll(";", "；");
				//value = value.replaceAll("\\(", "（");
				//value = value.replaceAll("\\)", "）");
//				value = value.replaceAll("\\+", " ");
//				value = value.replaceAll("=", " ");
				value = value.replaceAll("having", " ");
				value = value.replaceAll("group", " ");
//				value = value.replaceAll(".*([';]+|(--)+).*", " ");
				values[i] = value.trim();
			}
			//把转义后的参数放回wrapRequest中
			requestMap.put(key, values);
		}
		return wrapRequest;
	}

}
