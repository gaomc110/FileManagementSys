package cn.com.sparknet.common.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import cn.com.sparknet.common.service.MenuService;
import cn.com.sparknet.common.util.ParamsUtil;
import cn.com.sparknet.common.util.StringUtil;

@Controller
@RequestMapping(value ="/MenuController")
public class MenuController extends JsonController{
	private static Logger log = Logger.getLogger(MenuController.class);
	@Resource
	MenuService menuService;
	
	
	/**
	 * 
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getMenulist")
	public String getMenulist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = ParamsUtil.requestParamMap(request);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
        int parid = Integer.parseInt(StringUtil.nullToEmpty(map.get("parid")));
        String loginid = StringUtil.nullToEmpty(map.get("loginid"));
        map.put("parid",  parid);
        map.put("loginid",  loginid);
		list = menuService.getMenulist(map);
		list2 = menuService.getMenulistChildren(map);
		
		for (Map<String, Object> m : list) {
			List<Map<String,Object>> children = new ArrayList<Map<String,Object>>();
			for (Map<String, Object> m2 : list2) {
				if(!StringUtils.isEmpty(m.get("ID"))&&!StringUtils.isEmpty(m2.get("PARID"))){
					if(m.get("ID").toString().equals(m2.get("PARID").toString())){
						children.add(m2);
					}
				}
			}
			m.put("children",children);
		}

		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writeValueAsString(list);
//		result = result.toLowerCase();
		serialize(request, response, result);
		return null;
	}
	
	/**
	 * 加载操作按钮权限
	 * 
	 */
	@RequestMapping("/getMenuBtnlist")
	@ResponseBody
	public String getMenuBtnlist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = ParamsUtil.requestParamMap(request);
		List list = menuService.getMenuBtnlist(map);
		ObjectMapper mapper = new ObjectMapper(); // 返回json数据的类型
		String end = mapper.writeValueAsString(list);
		serialize(request,response,end,true);
		return null;
	}
	
	
	
}
