package cn.com.sparknet.common.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.sparknet.common.bean.User;
import cn.com.sparknet.common.json.EditJson;
import cn.com.sparknet.common.json.ListJson;

public interface UserService {
	/**
     * 用户管理列表查询
     * @param params
     * @return
     */
    ListJson getUserRoleList(Map<String,Object> map);
    
    public Boolean getUpdateuserbyidstart(Map<String,Object> map);
    
    /**
	 * 新增用户时查看用户是否存在
	 * @param params
	 * @return
	 */
	boolean getUserIsName(Map<String,Object> map);
	
	/**
     * 加载机构下拉菜单
     * @param params
     * @return
     */
    List<Map<String,Object>> getmechanismlist(Map<String,Object> map);
    
    List<Map<String,Object>> getdeptorgselet(Map<String,Object> map);
    /**
     * 加载部门
     * @param params
     * @return
     */
    List<Map<String,Object>> getDEPTlist(Map<String,Object> map);
   
    /**
     * 加载角色
     * @param params
     * @return
     */
    /*List<Map<String,Object>> getSelectRolelist(Map<String,Object> map);*/
    /**
	 * 新增用户
	 * @param params
	 * @return
	 */
	boolean getAddUser(Map<String,Object> map);
	
	public EditJson getupdateimages(String filePath,String loginid);
	
	/**
	 * 查询用户头像
	 */
	public void findUserPhotoById(String id,HttpServletResponse response);
	
	/**
     * 根据id查询用于修改用户
     * @param params
     * @return
     */
	List<Map<String,Object>> getUserlistById(Map<String,Object> map);
	
	/**
	 * 修改用户
	 * @param params
	 * @return
	 */
	boolean getUserUpdate(Map<String,Object> map);
	
	/**
	 * 删除用户
	 * @param params
	 * @return
	 */
	int getUserxjcount(String userid);
	int getUserRoleDel(String userid);
	int getUserDel(String userid);
	
	boolean getUserPWdbyId(Map<String,Object> map);
	boolean getUpdateUserpwd(Map<String,Object> map);
	
	List<Map<String,Object>> getuserschjekglist(Map<String,Object> map);
	
	boolean getloadByIdUseradmin(Map<String,Object> map);
}
