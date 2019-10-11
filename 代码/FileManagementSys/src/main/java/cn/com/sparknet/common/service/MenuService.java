package cn.com.sparknet.common.service;

import java.util.List;
import java.util.Map;

public interface MenuService {
	List<Map<String,Object>> getMenulist(Map<String,Object> map);
	List<Map<String,Object>> getMenulistChildren(Map<String,Object> map);
	
	List<Map<String,Object>> getMenuBtnlist(Map<String,Object> map);
}
