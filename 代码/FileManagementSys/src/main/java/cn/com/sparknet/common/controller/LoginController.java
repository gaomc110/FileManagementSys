package cn.com.sparknet.common.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.sparknet.common.bean.User;
import cn.com.sparknet.common.bean.UserBehavior;
import cn.com.sparknet.common.filter.SessionUserListener;
import cn.com.sparknet.common.json.EditJson;
import cn.com.sparknet.common.service.LoginService;
import cn.com.sparknet.common.util.JsonUtil;
import cn.com.sparknet.common.util.MD5Util;
import cn.com.sparknet.common.util.ParamsUtil;
import cn.com.sparknet.common.util.RandomValidateCodeUtil;
import cn.com.sparknet.common.util.StringUtil;
import cn.com.sparknet.common.util.UUIDUtil;
import nl.bitwalker.useragentutils.UserAgent;



/**
 * 系统登录
 * @author GAOMC
 *
 */
@Controller
@RequestMapping(value ="/LoginController.do")
public class LoginController {
    private static Logger log = Logger.getLogger(LoginController.class);
    @Resource
    LoginService loginService;
    
    /**
     * 用户登录
     * @param request
     * @param response
     */
    @ResponseBody
    @RequestMapping (params = "login")
    public void businessLogin(HttpServletRequest request, HttpServletResponse response) {
    	response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
    	response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    	response.setHeader("Access-Control-Allow-Credentials", "true");
        PrintWriter out = null;
        Map<String, Object> resultMap = null;
        Object object = null;
        try {
            out = response.getWriter();
            HttpSession session = request.getSession(true);
            String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
            String verify = request.getParameter("vercode");
            resultMap = new HashMap<String, Object>();
            Map<String, Object> map = ParamsUtil.requestParamMap(request);//统一取值
            String loginSystem = StringUtil.nullToEmpty(map.get("yonghuming").toString());
            String yonghuming =new String(map.get("yonghuming").toString());
            String yonghumima =new String(map.get("yonghumima").toString());
            if(loginSystem!=null && loginSystem!=""){
                map.put("yonghuming", StringUtil.nullToEmpty(yonghuming)); 
                map.put("yonghumima", StringUtil.nullToEmpty(MD5Util.encrypt(yonghumima))); 
            }
            
           if (random == null) {
       		 resultMap.put("msg", "验证码不存在！");
	             resultMap.put("errtype", 2);
           }else if (!random.equals(verify.toUpperCase())) {
      		     resultMap.put("msg", "验证码错误！");
	             resultMap.put("errtype", 2);
           }else if(!yonghuming.equals("") && !yonghumima.equals("")){
			            int usercont=loginService.userLogincout(map);
			            if(usercont>0){
			            	//1在数据库查找用户 
				            User user = loginService.businessLogin(map);
				            if (user != null && !StringUtil.nullToEmpty(user.getUSER_ID()).equals("")) {
				            	//验证该用户ID，是否已经登录。当前用户比较已登录到系统的静态变量中的值，是否存在。  
				                    Boolean hasLogin = SessionUserListener.checkIfHasLogin(user);  
				                    //如果重复登录了，则注销之前已登录的用户 
				                    if (hasLogin) {
				                    	System.out.println(user.getUSER_NAME()+"已经注销之前该账号登录的用户 。");
				                	    SessionUserListener.removeUserSession(user.getUSER_ID()); 
				                    }
				                    	 //如果没有重复登录，则将该登录的用户信息添加入session中  
				                    	request.getSession().setAttribute("msaCaseSystem", user);
				                    	// 比较保存所有用户session的静态变量中，是否含有当前session的键值映射，如果含有就删除  
					                    if (SessionUserListener.containsKey(request.getSession().getId())) {  
					                        SessionUserListener.removeSession(request.getSession().getId());  
					                    } 
					                    //把当前用户封装的session按，sessionID和session进行键值封装，添加到静态变量map中。  
					                    SessionUserListener.addUserSession(request.getSession());
					        		
					        		//获取浏览器类型及版本
					        		UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
					        		String operateBrowserType=userAgent.getBrowser().toString();
					        		
					        		//保存用户行为
					        		UserBehavior userBehavior=new UserBehavior();
					        		userBehavior.setUserBehaviorId(UUIDUtil.getNextValue());
					        		userBehavior.setUser(user);
					        		userBehavior.setModuleName("用户登录");
					        		userBehavior.setOperateIp(StringUtil.getIpAddress(request));
					        		userBehavior.setOperateDescribe("查询用户信息操作");
					        		userBehavior.setOperateBrowserType(operateBrowserType);
					        		userBehavior.setClassName("cn.com.sparknet.common.controller.LoginController");
					        		userBehavior.setMethodName("businessLogin");
				                    resultMap.put("errtype", 1);
				            } else {
				                resultMap.put("msg", "该账户状态已关闭，无法使用");
				                resultMap.put("errtype", 2);
				            }
			            }else{
			            	 resultMap.put("msg", "输入的账号或密码错误！");
				             resultMap.put("errtype", 2);
			            }
            }else{
            	 resultMap.put("msg", "账号/密码不能为空！");
	             resultMap.put("errtype", 2);
            }
            out.print(JsonUtil.mapToJson(resultMap));
        } catch ( Exception e ) {
            resultMap.put("errtype", 0);
            resultMap.put("msg", "登录失败，请重试！");
            log.error(e.getMessage(), e);
        } finally {
            if (out != null) {
                out.flush();
                out.close();
                out = null;
            }
        }
    }
   
    
    @Autowired
	private RandomValidateCodeUtil randomValidateCodeUtil;
	/**
	 * 生成验证码
	 */
	@RequestMapping(params = "getVerify", method = RequestMethod.GET)
	public EditJson getVerify(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
			response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expire", 0);
			randomValidateCodeUtil.getRandcode(request, response);// 输出验证码图片方法
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
    
    /**
     * 点击退出系统，清除缓存。
     */
    @ResponseBody
    @RequestMapping(params="cleanSession")
    public boolean cleanSession(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	Map<String, Object> map = ParamsUtil.requestParamMap(request);
    	
        HttpSession session=request.getSession();
        if(null!=session){
        	// 删除单一登录中记录的变量  
            if(session.getAttribute( "msaCaseSystem" ) != null)  
            {  
            	
                User user = (User) session.getAttribute( "msaCaseSystem");
                SessionUserListener.removeUserSession(user.getUSER_ID());
                SessionUserListener.removeSession(StringUtil.nullToEmpty(map.get("loginName").toString()));
                
            } 
        }
        /*response.sendRedirect(request.getContextPath()+"/");*/
        return true;
    }
}
