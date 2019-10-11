package cn.com.sparknet.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.sparknet.common.bean.FileFormInfo;
import cn.com.sparknet.common.bean.User;
import cn.com.sparknet.common.json.EditJson;
import cn.com.sparknet.common.json.ListJson;
import cn.com.sparknet.common.service.FileService;
import cn.com.sparknet.common.service.UserService;
import cn.com.sparknet.common.util.MD5Util;
import cn.com.sparknet.common.util.ParamsUtil;
import cn.com.sparknet.common.util.StringUtil;

@Controller
@RequestMapping(value ="/UserController")
public class UserController extends JsonController{
	@Resource
	UserService userService;
	@Resource
	private FileService fileService;
	/**
	 * 查询当前用户下看到的所有人员
	 * 
	 */
	
	@RequestMapping("/getUserRList")
	@ResponseBody
	public ListJson getRoleList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = ParamsUtil.requestParamMap(request);
        String loginid=request.getParameter("loginid");
        int page = Integer.parseInt(StringUtil.nullToEmpty(map.get("page")));
        int rows = Integer.parseInt(StringUtil.nullToEmpty(map.get("limit")));
        String FULLNAME = StringUtil.nullToEmpty(map.get("fullname"));
        String U_NAME = StringUtil.nullToEmpty(map.get("u_name"));

        map.put("page",  (page-1)*rows);
        map.put("rows", rows);
        map.put("loginid",  loginid);
        map.put("FULLNAME",  FULLNAME);
		map.put("U_NAME",  U_NAME);

		return  userService.getUserRoleList(map);
	}
	
	/**
	 * 修改用户状态权限
	 * 
	 */
	@RequestMapping("/getUpdateuserbyidstart")
	@ResponseBody
	//@UserBehaviorMonitor(module="基础管理",describe="修改用户状态权限")
	public String getUpdateuserbyidstart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = ParamsUtil.requestParamMap(request);
    	String userid =new String(StringUtil.nullToEmpty(map.get("userid")));
    	String startkey =new String(StringUtil.nullToEmpty(map.get("startkey")));
		map.put("userid", userid);
		map.put("startkey", startkey);
		boolean bool=userService.getUpdateuserbyidstart(map);
		serialize(request,response,bool);
		return null;
	}
	
	/**
	 * 验证用户名是否已存在
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getUserIsName")
	@ResponseBody
	//@UserBehaviorMonitor(module="基础管理",describe="验证用户名是否已存在")
	public String getUserIsName(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = ParamsUtil.requestParamMap(request);
		String uname = StringUtil.nullToEmpty(map.get("uname"));
		map.put("uname", uname);
        boolean bool = userService.getUserIsName(map);
        ObjectMapper result = new ObjectMapper();
        String end = result.writeValueAsString(bool);
        serialize(request,response,end,true);
		return null;
	}
	
	/**
	 * 加载机构下拉菜单
	 * 
	 */
	@RequestMapping("/getmechanismlist")
	@ResponseBody
	public String getmechanismlist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = ParamsUtil.requestParamMap(request);
		/*String mechaparid = StringUtil.nullToEmpty(map.get("mechaparid"));*/
		/*map.put("mechaparid", mechaparid); */
		List list = userService.getmechanismlist(map);
		ObjectMapper mapper = new ObjectMapper(); // 返回json数据的类型
		String end = mapper.writeValueAsString(list);
		serialize(request,response,end,true);
		return null;
	}
	
	/**
	 * 加载机构下拉菜单
	 * 
	 */
	@RequestMapping("/getdeptorgselet")
	@ResponseBody
	public String getdeptorgselet(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = ParamsUtil.requestParamMap(request);
		String PARDID = StringUtil.nullToEmpty(map.get("PARDID"));
		map.put("PARDID", PARDID); 
		List list = userService.getdeptorgselet(map);
		ObjectMapper mapper = new ObjectMapper(); // 返回json数据的类型
		String end = mapper.writeValueAsString(list);
		serialize(request,response,end,true);
		return null;
	}
	
	/**
	 * 点击加载上级部门下拉菜单
	 * 
	 */
	@RequestMapping("/getDEPTlist")
	@ResponseBody
	public String getDEPTlist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = ParamsUtil.requestParamMap(request);
		String ORGID = StringUtil.nullToEmpty(map.get("ORGID"));
		map.put("ORGID", ORGID); 
		List list = userService.getDEPTlist(map);
		ObjectMapper mapper = new ObjectMapper(); // 返回json数据的类型
		String end = mapper.writeValueAsString(list);
		serialize(request,response,end,true);
		return null;
	}
	
	
	
	/**
	 * 加载角色下拉菜单
	 * 
	 */
	/*@RequestMapping("/getSelectRolelist")
	@ResponseBody
	public String getSelectRolelist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = ParamsUtil.requestParamMap(request);
		List list = userService.getSelectRolelist(map);
		ObjectMapper mapper = new ObjectMapper(); // 返回json数据的类型
		String end = mapper.writeValueAsString(list);
		serialize(request,response,end,true);
		return null;
	}*/
	
	/**
	 * 新增用户
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getAddUser")
	@ResponseBody
	//@UserBehaviorMonitor(module="基础管理",describe="新增用户")
	public String getAddUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = ParamsUtil.requestParamMap(request);
		String TEXTUID=request.getParameter("TEXTUID");
		String TEXTUNAME = StringUtil.nullToEmpty(map.get("TEXTUNAME"));
		String TEXTUUSERNAMECHK = StringUtil.nullToEmpty(map.get("TEXTUUSERNAMECHK"));
		String TXTUPWD = StringUtil.nullToEmpty(map.get("TXTUPWD"));
		/*String TXTDEPTchild = StringUtil.nullToEmpty(map.get("TXTDEPTchild"));*/
		String TXTUSEX = StringUtil.nullToEmpty(map.get("TXTUSEX"));
		String TXTUSTART = StringUtil.nullToEmpty(map.get("TXTUSTART"));
		String TXTUPHONE = StringUtil.nullToEmpty(map.get("TXTUPHONE"));
		/*String province = StringUtil.nullToEmpty(map.get("province"));
		String city = StringUtil.nullToEmpty(map.get("city"));
		String area = StringUtil.nullToEmpty(map.get("area"));*/
		/*String file = StringUtil.nullToEmpty(map.get("file"));*/
		String TXTUREMARKS = StringUtil.nullToEmpty(map.get("TXTUREMARKS"));
		String TXTUUPDATEPERSON = StringUtil.nullToEmpty(map.get("TXTUUPDATEPERSON"));
		String TXTUCREATETIME = StringUtil.nullToEmpty(map.get("TXTUCREATETIME"));
		if(TXTUSTART.equals("1")){
			map.put("TXTUSTART", TXTUSTART);
		}else{
			map.put("TXTUSTART", 2);
		}
		map.put("TEXTUID", TEXTUID);
		map.put("TEXTUNAME", TEXTUNAME);
		map.put("TEXTUUSERNAMECHK", TEXTUUSERNAMECHK);
		map.put("TXTUPWD", MD5Util.encrypt(TXTUPWD));
		map.put("TXTUSEX", TXTUSEX);
		map.put("TXTUPHONE", TXTUPHONE);
		//map.put("TXTUADDRESS", province+","+city+","+area);
		/*map.put("file", file);*/
		map.put("TXTUREMARKS", TXTUREMARKS);
		map.put("TXTUUPDATEPERSON", TXTUUPDATEPERSON);
		map.put("TXTUCREATETIME", TXTUCREATETIME);
		
		boolean bool = userService.getAddUser(map);
		if(bool){
			//userService.getupdateimages(map,request,response); //上传头像
		}
		
		ObjectMapper result = new ObjectMapper();
		String end = result.writeValueAsString(bool);
		serialize(request,response,end,true);
		return null;
	}
	
	
	
	/**
	 * 根据id查询用于修改用户
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getUserlistById")
	@ResponseBody
	//@UserBehaviorMonitor(module="基础管理",describe="根据id查询用于修改用户")
	public String getUserlistById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = ParamsUtil.requestParamMap(request);
		String userid = StringUtil.nullToEmpty(map.get("userid")); //系统名称
		map.put("userid", userid);
		List list = userService.getUserlistById(map);
		ObjectMapper mapper = new ObjectMapper(); // 返回json数据的类型
		String end = mapper.writeValueAsString(list);
		//end = end.replaceAll("[\\[\\]]", ""); 
		serialize(request,response,end,true);
		return null;
	}
	
	/**
	 * 修改用户
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getUpdateUser")
	@ResponseBody
	public String getUserUpdate(HttpServletRequest request, HttpServletResponse response,User user) throws Exception {
		Map<String, Object> map = ParamsUtil.requestParamMap(request);
		
		String TXTUID=request.getParameter("TXTUID");
		/*String loginid=request.getParameter("loginid");*/
		String TEXTUNAME = StringUtil.nullToEmpty(map.get("TEXTUNAME"));
		String TEXTUUSERNAMECHK = StringUtil.nullToEmpty(map.get("TEXTUUSERNAMECHK"));
		String TXTUPWD = StringUtil.nullToEmpty(map.get("TXTUPWD"));
		//String TXTDEPTchild = StringUtil.nullToEmpty(map.get("TXTDEPTchild")); //部门
		String TXTUSEX = StringUtil.nullToEmpty(map.get("TXTUSEX"));
		String TXTUSTART = StringUtil.nullToEmpty(map.get("TXTUSTART"));
		String TXTUPHONE = StringUtil.nullToEmpty(map.get("TXTUPHONE"));
		/*String province = StringUtil.nullToEmpty(map.get("province"));
		String city = StringUtil.nullToEmpty(map.get("city"));
		String area = StringUtil.nullToEmpty(map.get("area"));*/
		/*String file = StringUtil.nullToEmpty(map.get("file"));*/
		String TXTUREMARKS = StringUtil.nullToEmpty(map.get("TXTUREMARKS"));
		/*String TXTUUPDATEPERSON = StringUtil.nullToEmpty(map.get("TXTUUPDATEPERSON"));*/ //创建人无需修改
		/*String TXTUCREATETIME = StringUtil.nullToEmpty(map.get("TXTUCREATETIME"));*/ //创建时间通过获取系统时间，无需传
		if(TXTUSTART.equals("1")){
			map.put("TXTUSTART", TXTUSTART);
		}else{
			map.put("TXTUSTART", 2);
		}
		map.put("TXTUID", TXTUID);
		
		/*if(TXTUID.equals("1")){
			map.put("USER_PARID","");
		}else{
			map.put("USER_PARID", loginid);
		}*/
		
		map.put("TEXTUNAME", TEXTUNAME);
		map.put("TEXTUUSERNAMECHK", TEXTUUSERNAMECHK);
		if(TXTUPWD.equals("NT888888")){
			map.put("TXTUPWD", MD5Util.encrypt(TXTUPWD));
		}else{
			map.put("TXTUPWD", TXTUPWD);
		}
		
		map.put("TXTUSEX", TXTUSEX);
		map.put("TXTUPHONE", TXTUPHONE);
		/*map.put("TXTUADDRESS", province+","+city+","+area);*/
		/*map.put("photoPath", file);*/
		map.put("TXTUREMARKS", TXTUREMARKS);
		/*map.put("TXTUUPDATEPERSON", TXTUUPDATEPERSON);*/
		/*map.put("TXTUCREATETIME", TXTUCREATETIME);*/
		boolean bool = userService.getUserUpdate(map);
		ObjectMapper result = new ObjectMapper();
		String end = result.writeValueAsString(bool);
		serialize(request,response,end,true);
		return null;
	}
	
	
	/**
	 * 上传头像
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getupdateimages")
	@ResponseBody
	public EditJson getupdateimages(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = ParamsUtil.requestParamMap(request);
		/*String fileName=file.getOriginalFilename();*///获取文件名加后缀
		String loginid = StringUtil.nullToEmpty(map.get("loginid"));
		FileFormInfo fileFormInfo = fileService.uploadFileWithParams(request);
		String filePath =fileFormInfo.getFileInfos().get(0).getFilePath();
		return userService.getupdateimages(filePath,loginid);//上传头像
	}
	
	/**
	 * 查询用户头像
	 */
	@ResponseBody
	@RequestMapping("/findUserPhotoById")
	//@UserBehaviorMonitor(module="基础管理",describe="查询用户头像")
	public void findUserPhotoById(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id=StringUtil.nullToEmpty(request.getParameter("loginid"));
		userService.findUserPhotoById(id,response);
	}
	
	/**
	 * 删除用户
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getUserDel")
	@ResponseBody
	//@UserBehaviorMonitor(module="基础管理",describe="删除用户")
	public Map<String,Object> getUserDel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = ParamsUtil.requestParamMap(request);
		String USERID = StringUtil.nullToEmpty(map.get("USERID"));
	    int userxjcont=0;  //查看删除的用户是否有下级
		int contid=0;
		Map<String,Object> result = new HashMap<String,Object>();
		
	    if (!USERID.equals("")) {
	    	if(!USERID.equals("1")){
	    		userxjcont=userService.getUserxjcount(USERID);
		    	if(userxjcont>0){
			    	result.put("isdel", false);
			    	result.put("msg", "该用户正在使用，删除失败！");
			    }else{
		            userService.getUserRoleDel(USERID);
				    contid=userService.getUserDel(USERID);
					if(contid>0){
						result.put("isdel", true);
					}else{
						result.put("isdel", false);
					}
			    }
	    	}else{
	    		result.put("isdel", false);
		    	result.put("msg", "无法对管理员进行删除！");
	    	}
	    	
	    }else{
    		result.put("isdel", false);
	    	result.put("msg", "删除过程中出现错误！");
    	}
	    
		return result;
	}
	
	
	/**
	 * 修改密码时验证输入的密码是否正确
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getUserPWdbyId")
	@ResponseBody
	//@UserBehaviorMonitor(module="基础管理",describe="修改密码时验证输入的密码是否正确")
	public String getUserPWdbyId(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = ParamsUtil.requestParamMap(request);
		String upwd = StringUtil.nullToEmpty(map.get("upwd"));
		String loginid = StringUtil.nullToEmpty(map.get("loginid"));
		map.put("upwd", MD5Util.encrypt(upwd));
		map.put("loginid", loginid);
        boolean bool = userService.getUserPWdbyId(map);
        ObjectMapper result = new ObjectMapper();
        String end = result.writeValueAsString(bool);
        serialize(request,response,end,true);
		return null;
	}
	
	/**
	 * 修改用户密码
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getUpdateUserpwd")
	@ResponseBody
	//@UserBehaviorMonitor(module="基础管理",describe="修改用户密码")
	public String getUpdateUserpwd(HttpServletRequest request, HttpServletResponse response,User user) throws Exception {
		Map<String, Object> map = ParamsUtil.requestParamMap(request);
		
		String loginid=request.getParameter("loginid");
		String txtuname = StringUtil.nullToEmpty(map.get("txtuname"));
		String txtunewpwd = StringUtil.nullToEmpty(map.get("txtunewpwd"));
		map.put("loginid", loginid);
		map.put("txtuname", txtuname);
		map.put("txtunewpwd", MD5Util.encrypt(txtunewpwd));
		
		boolean bool = userService.getUpdateUserpwd(map);
		ObjectMapper result = new ObjectMapper();
		String end = result.writeValueAsString(bool);
		serialize(request,response,end,true);
		return null;
	}
	
	
	/**
	 * 加载admin添加用户可以选择下拉菜单
	 * 
	 */
	@RequestMapping("/getuserschjekglist")
	@ResponseBody
	public String getuserschjekglist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = ParamsUtil.requestParamMap(request);
		List list = userService.getuserschjekglist(map);
		ObjectMapper mapper = new ObjectMapper(); // 返回json数据的类型
		String end = mapper.writeValueAsString(list);
		serialize(request,response,end,true);
		return null;
	}
	
	
	/**
	 * 查看当前用户是管理员，是管理员就显示初始化密码按钮
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getloadByIdUseradmin")
	@ResponseBody
	public String getloadByIdUseradmin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = ParamsUtil.requestParamMap(request);
        boolean bool = userService.getloadByIdUseradmin(map);
        ObjectMapper result = new ObjectMapper();
        String end = result.writeValueAsString(bool);
        serialize(request,response,end,true);
		return null;
	}
	
	/** 
     * 获得一个UUID 
     * @return String UUID 
     */ 
    public static String getUUID(){ 
        String s = UUID.randomUUID().toString(); 
        //去掉“-”符号 
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
    } 
	
}
