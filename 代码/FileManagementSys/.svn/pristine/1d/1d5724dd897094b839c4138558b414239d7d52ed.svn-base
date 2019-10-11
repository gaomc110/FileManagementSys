package cn.com.sparknet.common.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.filter.OncePerRequestFilter;

public class LoginFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		 // 不过滤的uri    
        String[] notFilter = new String[] { "login.jsp" };    
    
        // 请求的uri    
        String uri = request.getRequestURI();    
    
        // uri中包含background时才进行过滤    
        if (uri.indexOf(".jsp") != -1) {    
            // 是否过滤    
            boolean doFilter = true;    
            for (String s : notFilter) {    
                if (uri.indexOf(s) != -1) {    
                    // 如果uri中包含不过滤的uri，则不进行过滤    
                    doFilter = false;    
                    break;    
                }    
            }    
            if (doFilter) {    
                // 执行过滤    
                // 从session中获取登录者实体    
                Object obj = request.getSession().getAttribute("msaCaseSystem");    
                if (null == obj) {    
                    // 如果session中不存在登录者实体，则弹出框提示重新登录    
                	response.sendRedirect(request.getContextPath() + "/login.jsp");    
                } else {    
                    // 如果session中存在登录者实体，则继续    
                    filterChain.doFilter(request, response);    
                }    
            } else {    
                // 如果不执行过滤，则继续    
                filterChain.doFilter(request, response);    
            }    
        } else {    
            // 如果uri中不包含background，则继续    
            filterChain.doFilter(request, response);    
        }    
		
	}

	

}
