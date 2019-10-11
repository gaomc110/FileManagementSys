package cn.com.sparknet.common.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import cn.com.sparknet.common.bean.User;
import cn.com.sparknet.common.dao.Page;
import cn.com.sparknet.common.dao.UserDao;
import cn.com.sparknet.common.json.EditJson;
import cn.com.sparknet.common.json.ListJson;
import cn.com.sparknet.common.service.FileService;
import cn.com.sparknet.common.service.UserService;
import cn.com.sparknet.common.util.InputStreamUtil;
import cn.com.sparknet.common.util.StringUtil;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Resource
    private UserDao userDao;
	@Resource
	private FileService fileService;
	
	/**
     * 用户管理操作板块
     */
	public ListJson getUserRoleList(Map<String, Object> map) {
		ListJson listJson=new ListJson();
        try{
            Page page=userDao.UserRoleList(map);
            if(page.getCount()>0){
            	listJson.setData(page.getList());
                listJson.setCount(page.getCount());
                listJson.setCode(0);
                listJson.setMsg("");
            }else{
            	listJson.setData(page.getList());
                listJson.setCount(page.getCount());
                listJson.setCode(1);
                listJson.setMsg("暂无数据可查");
            }
            
        }catch(Exception e){
            throw new RuntimeException(e.getMessage(),e);
        }
        return listJson;
	}
	
	/**
     * 修改用户状态
     */

	@Override
	public Boolean getUpdateuserbyidstart(Map<String, Object> map) {
		try{
			Integer i = userDao.Updateuserbyidstart(map);
			int a = i.intValue();
			return a>0;
        }catch(Exception e){
            throw new RuntimeException(e.getMessage(),e);
        }
		
	}
	
	public boolean getUserIsName(Map<String, Object> map) {
		try{
			Integer i = userDao.UserIsName(map);
			int a = i.intValue();
			return a>0;
        }catch(Exception e){
            throw new RuntimeException(e.getMessage(),e);
        }
	}

	@Override
	public List<Map<String, Object>> getmechanismlist(Map<String, Object> map) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try{
            list=userDao.mechanismlist(map);
        }catch(Exception e){
            throw new RuntimeException(e.getMessage(),e);
        }
        return list;
	}
	
	@Override
	public List<Map<String, Object>> getdeptorgselet(Map<String, Object> map) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try{
            list=userDao.deptorgselet(map);
        }catch(Exception e){
            throw new RuntimeException(e.getMessage(),e);
        }
        return list;
	}
	
	@Override
	public List<Map<String, Object>> getDEPTlist(Map<String, Object> map) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try{
            list=userDao.DEPTlist(map);
        }catch(Exception e){
            throw new RuntimeException(e.getMessage(),e);
        }
        return list;
	}
	
	
	
	/*@Override
	public List<Map<String, Object>> getSelectRolelist(Map<String, Object> map) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try{
            list=userDao.SelectRolelist(map);
        }catch(Exception e){
            throw new RuntimeException(e.getMessage(),e);
        }
        return list;
	}*/
	
	public boolean getAddUser(Map<String, Object> map) {
		try{
			Integer i = userDao.AddUser(map);
			int a = i.intValue();
			return a>0;
        }catch(Exception e){
            throw new RuntimeException(e.getMessage(),e);
        }
	}

	@Override
	public EditJson getupdateimages(String filePath,String loginid) {
		EditJson editJson=new EditJson();
		try{
			userDao.updateimages(filePath,loginid);
			editJson.setSuccess(true);
        }catch(Exception e){
        	editJson.setSuccess(false);
            //throw new RuntimeException(e.getMessage(),e);
        }
		return editJson;
	}
	
	/**
	 * 查询用户头像
	 */
	public void findUserPhotoById(String id,HttpServletResponse response){
        try{
        	List<InputStream> list=userDao.findUserPhotoById(id);
        	for(InputStream is:list){
        		if(is != null){
        			fileService.showPic(is, response);
            	}
        	}
        }catch(Exception e){
        	throw new RuntimeException(e.getMessage(),e);
        }
    }
	
	public List<Map<String, Object>> getUserlistById(Map<String, Object> map) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try{
            list=userDao.UserlistById(map);
        }catch(Exception e){
            throw new RuntimeException(e.getMessage(),e);
        }
        return list;
	}
	
	public boolean getUserUpdate(Map<String, Object> map) {
		try{
			Integer i = userDao.UserUpdate(map);
			int a = i.intValue();
			return a>0;
        }catch(Exception e){
            throw new RuntimeException(e.getMessage(),e);
        }
	}
	
	public int getUserxjcount(String userid) {
		try{
			return userDao.Userxjcount(userid);
        }catch(Exception e){
            throw new RuntimeException(e.getMessage(),e);
        }
		
	}
	public int getUserRoleDel(String userid) {
		try{
			return userDao.UserRoleDel(userid);
        }catch(Exception e){
            throw new RuntimeException(e.getMessage(),e);
        }
	}
	public int getUserDel(String userid) {
		try{
			return userDao.UserDel(userid);
        }catch(Exception e){
            throw new RuntimeException(e.getMessage(),e);
        }
	}

	public boolean getUserPWdbyId(Map<String, Object> map) {
		try{
			Integer i = userDao.UserPWdbyId(map);
			int a = i.intValue();
			return a>0;
        }catch(Exception e){
            throw new RuntimeException(e.getMessage(),e);
        }
	}
	
	public boolean getUpdateUserpwd(Map<String, Object> map) {
		try{
			Integer i = userDao.UpdateUserpwd(map);
			int a = i.intValue();
			return a>0;
        }catch(Exception e){
            throw new RuntimeException(e.getMessage(),e);
        }
	}
	
	@Override
	public List<Map<String, Object>> getuserschjekglist(Map<String, Object> map) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try{
            list=userDao.userschjekglist(map);
        }catch(Exception e){
            throw new RuntimeException(e.getMessage(),e);
        }
        return list;
	}
	
	public boolean getloadByIdUseradmin(Map<String, Object> map) {
		try{
			Integer i = userDao.loadByIdUseradmin(map);
			int a = i.intValue();
			return a>0;
        }catch(Exception e){
            throw new RuntimeException(e.getMessage(),e);
        }
	}
	
}
