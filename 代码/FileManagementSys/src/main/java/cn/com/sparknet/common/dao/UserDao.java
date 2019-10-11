package cn.com.sparknet.common.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.com.sparknet.common.bean.User;
import cn.com.sparknet.common.service.FileService;
import cn.com.sparknet.common.util.InputStreamUtil;
import cn.com.sparknet.common.util.StringUtil;

@Repository ("userDao")
public class UserDao {
	@Resource
    BaseDao baseDao;
	@Autowired
    DataSource defaultDataSource;
	
	
	
	public Page UserRoleList(Map<String, Object> map) throws Exception{
		StringBuffer sql= new StringBuffer();
        List<String> list = new ArrayList<String>();
        String start = StringUtil.nullToEmpty(map.get("page"));
        String end = StringUtil.nullToEmpty(map.get("rows"));
        
        sql.append(" SELECT A.U_ID,A.U_NAME, A.FULLNAME,TO_CHAR(A.CREATE_DATE, 'YYYY-MM-DD HH24:MI:SS') CREATE_DATE, ");
        sql.append(" A.CREATE_PRESON,C.ROLEID,C.ROLENAME FROM T_DA_USER A LEFT JOIN T_DA_Userrole B ON A.U_ID = B.U_ID ");
        sql.append(" LEFT JOIN T_DA_ROLE C ON B.ROLEID = C.ROLEID WHERE 1 = 1 ");
        sql.append(" AND A.STATE='A'  ");
        
        if(StringUtil.isNotEmpty(StringUtil.nullToEmpty(map.get("loginid")))){
            sql.append(" AND A.U_NAME <> ? ");
            list.add(StringUtil.nullToEmpty(map.get("loginid")));
        }
        
        if(StringUtil.isNotEmpty(StringUtil.nullToEmpty(map.get("FULLNAME")))){
            sql.append(" and A.FULLNAME like ? escape '\\' ");
            list.add("%"+StringUtil.nullToEmpty(map.get("FULLNAME"))+"%");
        }
        
        if(StringUtil.isNotEmpty(StringUtil.nullToEmpty(map.get("U_NAME")))){
            sql.append(" and A.U_NAME like ? escape '\\' ");
            list.add("%"+StringUtil.nullToEmpty(map.get("U_NAME"))+"%");
        }
        sql.append(" order by A.CREATE_DATE desc ");
        return baseDao.findPageAllBySql(sql.toString(),start,end,list.toArray());
    }
	
	

    public int Updateuserbyidstart(Map<String, Object> map)throws Exception{
		 StringBuffer sql= new StringBuffer();
		 sql.append(" UPDATE TUSER SET USER_START=? WHERE USER_ID =?");
		 return baseDao.updateBySql(sql.toString(), new Object[] {map.get("startkey"),map.get("userid")});
   }
    
    public int UserIsName(Map<String, Object> map)throws Exception{
		StringBuffer sql=new StringBuffer();
		String uname = StringUtil.nullToEmpty(map.get("uname"));
        sql.append("  SELECT count(*) FROM tuser where lower(USER_NAME)=lower(?)");
		return baseDao.findIntBySql(sql.toString(), new Object[] {uname});
    }
    
  //加载机构下拉菜单
  	public List<Map<String, Object>> mechanismlist(Map<String, Object> map)throws Exception{
  		StringBuffer sql=new StringBuffer();
  		List<String> list = new ArrayList<String>();
	      sql.append(" select DEPT_ID ORG_ID,DEPT_NAME ORG_NAME from t_pt_dept where PARENT_DEPT_ID in( ");
	      sql.append(" SELECT DEPT_ID FROM t_pt_dept where PARENT_DEPT_ID='0') ");
	      return baseDao.findListBySql(sql.toString(),list.toArray());
      }
  	
  	public List<Map<String, Object>> deptorgselet(Map<String, Object> map)throws Exception{
  		StringBuffer sql=new StringBuffer();
  		List<String> list = new ArrayList<String>();
  		String PARDID = StringUtil.nullToEmpty(map.get("PARDID")); 
	      sql.append("  select * from t_pt_dept where dept_id=(select PARENT_DEPT_ID from  t_pt_dept where dept_id='"+PARDID+"') ");
	      return baseDao.findListBySql(sql.toString(),list.toArray());
      }
  	
  //加载上级部门下拉菜单
  	public List<Map<String, Object>> DEPTlist(Map<String, Object> map)throws Exception{
  		StringBuffer sql=new StringBuffer();
  		List<String> list = new ArrayList<String>();
  		String ORGID = StringUtil.nullToEmpty(map.get("ORGID"));  		
	    sql.append(" select DEPT_ID,DEPT_NAME from t_pt_dept where PARENT_DEPT_ID ='"+ORGID+"' ");
	    return baseDao.findListBySql(sql.toString(),list.toArray());
      }
  	
  	
  	
  	
  //加载角色下拉菜单
  	/*public List<Map<String, Object>> SelectRolelist(Map<String, Object> map)throws Exception{
  		StringBuffer sql=new StringBuffer();
  		List<String> list = new ArrayList<String>();
	    sql.append(" select ROLEID,ROLENAME from trole  ");
	    return baseDao.findListBySql(sql.toString(),list.toArray());
      }*/
  	
  	public int AddUser(Map<String, Object> map)throws Exception{
		 StringBuffer sql= new StringBuffer();
		 List<String> list = new ArrayList<String>();
		 sql.append(" insert into tuser(USER_ID,USER_NAME,USER_START,USER_PHONE,USER_GENDER,USER_CREATETIME, ");
		 sql.append(" USER_PARID,USER_PWD,UPDATEPERSON,USER_REMARKS,USERNAMECHK,USER_PORTRAIT) values( ");
		 sql.append("sys_guid(),");
		 sql.append("?,");
		 list.add(StringUtil.nullToEmpty(map.get("TEXTUNAME")));
		 sql.append("?,");
		 list.add(StringUtil.nullToEmpty(map.get("TXTUSTART")));
		 sql.append("?,");
		 list.add(StringUtil.nullToEmpty(map.get("TXTUPHONE")));
		 sql.append("?,");
		 list.add(StringUtil.nullToEmpty(map.get("TXTUSEX")));
		 sql.append("sysdate,");
		 /*if(StringUtil.isNotEmpty(StringUtil.nullToEmpty(map.get("TXTUCREATETIME")))){
			 sql.append("to_date(?,'yyyy-mm-dd hh24:mi:ss'),");
			 list.add(StringUtil.nullToEmpty(map.get("TXTUCREATETIME")));
		 }else{
			 sql.append("'',");
		 }*/
		 sql.append("?,");
		 list.add(StringUtil.nullToEmpty(map.get("TEXTUID")));
		 sql.append("?,");
		 list.add(StringUtil.nullToEmpty(map.get("TXTUPWD")));
		 sql.append("?,");
		 list.add(StringUtil.nullToEmpty(map.get("TXTUUPDATEPERSON")));
		 /*sql.append("?,");
		 list.add(StringUtil.nullToEmpty(map.get("TXTUADDRESS")));*/
		 sql.append("?,");
		 list.add(StringUtil.nullToEmpty(map.get("TXTUREMARKS")));
		 sql.append("?,");
		 list.add(StringUtil.nullToEmpty(map.get("TEXTUUSERNAMECHK")));
		 sql.append("'')");
		 return baseDao.updateBySql(sql.toString(),list.toArray());
   }
  	
  	public void updateimages(String filePath,String loginid)throws Exception{
  		StringBuffer sql= new StringBuffer();
		List<String> list = new ArrayList<String>();
  		
		if(StringUtil.isNotEmpty(filePath)){
    		File file=new File(filePath);
    		if(file.exists()){
    			FileInputStream inputStream = new FileInputStream(file);
    			InputStream[] inputStreams = {inputStream};
    			 sql.append("update TUSER set USER_PORTRAIT=? where USER_ID='"+loginid+"'");
    			 baseDao.saveOrUpdateBlobBySql(sql.toString(), inputStreams);
    		}
    	}
		 
  	}	
  	
  	/**
	 * 查询用户头像
	 */
	public List<InputStream> findUserPhotoById(String id) throws Exception {
		String sql="select USER_PORTRAIT from TUSER where USER_ID=?";
		return baseDao.findBlobBySql(sql, new Object[]{id});
	}
  	
  	public List<Map<String, Object>> UserlistById(Map<String, Object> map)throws Exception{
		StringBuffer sql=new StringBuffer();
		String userid = StringUtil.nullToEmpty(map.get("userid"));
		
		/*sql.append(" SELECT a.USER_ID,a.USER_NAME,a.USER_START,a.USER_PHONE,a.USER_GENDER, ");
		sql.append(" to_char(a.USER_CREATETIME,'yyyy-mm-dd hh24:mi:ss') USER_CREATETIME, a.USER_PARID, a.USER_PWD, a.UPDATEPERSON,a.USER_ADDRESS,USER_REMARKS,");
		sql.append(" a.USERNAMECHK,e.deptid,e.deptname,e.pardeptid,e.pardeptname,e.orgid,e.orgname ");
		sql.append(" FROM TUSER a left join (select ff.dept_id deptid,ff.dept_name deptname,dd.did pardeptid,");
		sql.append(" dd.dname pardeptname,dd.pardid orgid,dd.pardname orgname from t_pt_dept ff, ");
		sql.append(" (select t.dept_id did,t.dept_name dname,b.dept_id pardid,b.dept_name pardname ");
		sql.append(" from t_pt_dept t,t_pt_dept b where t.PARENT_DEPT_ID=b.dept_id(+)) dd ");
		sql.append(" where ff.parent_dept_id=dd.did(+)) e ");
		sql.append(" on a.dept_id=e.deptid where a.USER_ID =? ");*/
		
		sql.append(" SELECT a.USER_ID,a.USER_NAME,a.USER_START,a.USER_PHONE,a.USER_GENDER, ");
		sql.append(" to_char(a.USER_CREATETIME,'yyyy-mm-dd hh24:mi:ss') USER_CREATETIME, ");
		sql.append(" a.USER_PARID, a.USER_PWD, a.UPDATEPERSON,a.USER_REMARKS, ");
		sql.append(" a.USERNAMECHK  FROM TUSER a ");
		sql.append(" where a.USER_ID =? ");
        return baseDao.findListBySql(sql.toString(), new Object[] {userid});
    }
  	
  	public int UserUpdate(Map<String, Object> map)throws Exception{
		 StringBuffer sql= new StringBuffer();
		 List<String> list = new ArrayList<String>();
		 sql.append("update tuser set ");
		 sql.append(" USER_NAME =?,");
		 list.add(StringUtil.nullToEmpty(map.get("TEXTUNAME")));
		 sql.append(" USER_START =?,");
		 list.add(StringUtil.nullToEmpty(map.get("TXTUSTART")));
		 sql.append(" USER_PHONE =?, ");
		 list.add(StringUtil.nullToEmpty(map.get("TXTUPHONE")));
		 sql.append(" USER_GENDER =?, ");
		 list.add(StringUtil.nullToEmpty(map.get("TXTUSEX")));
		 sql.append(" USER_CREATETIME =sysdate, ");
		 /*if(StringUtil.isNotEmpty(StringUtil.nullToEmpty(map.get("TXTUCREATETIME")))){
			 sql.append(" USER_CREATETIME =to_date(?,'yyyy-mm-dd hh24:mi:ss'), ");
			 list.add(StringUtil.nullToEmpty(map.get("TXTUCREATETIME")));
		 }else{
			 sql.append(" USER_CREATETIME ='', ");
		 }*/
		 /*sql.append(" USER_PARID =?, ");
		 list.add(StringUtil.nullToEmpty(map.get("USER_PARID")));*/
		 if(StringUtil.isNotEmpty(StringUtil.nullToEmpty(map.get("TXTUPWD")))){
			 sql.append(" USER_PWD =?, ");
			 list.add(StringUtil.nullToEmpty(map.get("TXTUPWD")));
		 }
		 /*sql.append(" UPDATEPERSON =?, ");
		 list.add(StringUtil.nullToEmpty(map.get("TXTUUPDATEPERSON")));*/
		 sql.append(" USERNAMECHK =?,");
		 list.add(StringUtil.nullToEmpty(map.get("TEXTUUSERNAMECHK")));
		 sql.append(" USER_REMARKS =? ");
		 list.add(StringUtil.nullToEmpty(map.get("TXTUREMARKS")));
		 if(StringUtil.isNotEmpty(StringUtil.nullToEmpty(map.get("TXTUID")))){
			 sql.append(" where  USER_ID =?");
			 list.add(StringUtil.nullToEmpty(map.get("TXTUID")));
		 }
		 return baseDao.updateBySql(sql.toString(),list.toArray());
   }
  	
  	public int Userxjcount(String userid)throws Exception{
		StringBuffer sql=new StringBuffer();
		
		/*sql.append("select count(*) from  ");
		sql.append("(select * from tuser start with USER_ID=?  ");
		sql.append("connect by prior USER_ID=USER_PARID) a left join tuserole b  ");
		sql.append("on a.USER_ID=b.USER_ID left join trole c on b.ROLEID=c.ROLEID  ");*/
		
		/*sql.append(" select count(*) from tuserole where USER_ID in( ");
		sql.append(" select USER_ID from tuser start with USER_ID=? "); 
		sql.append(" connect by prior USER_ID=USER_PARID) ");*/
		
		sql.append(" select COUNT(*) from tuser where USER_ID =? and USER_START='1'"); 
        return baseDao.findIntBySql(sql.toString(), new Object[] {StringUtil.nullToEmpty(userid)});
    }
  	
  	public int UserRoleDel(String userid)throws Exception{
		if(StringUtil.isNotEmpty(StringUtil.nullToEmpty(userid))){
			 String sql=" delete from tuserole where USER_ID= ?";	
			 return baseDao.updateBySql(sql.toString(),new Object[] {StringUtil.nullToEmpty(userid)});
		 }else{
			 return 0;
		 }
    }
  	
  	public int UserDel(String userid)throws Exception{
		if(StringUtil.isNotEmpty(StringUtil.nullToEmpty(userid))){
			 String sql=" delete from tuser where USER_ID= ?";	
			 return baseDao.updateBySql(sql.toString(),new Object[] {StringUtil.nullToEmpty(userid)});
		 }else{
			 return 0;
		 }
    }
  	
  	public int UserPWdbyId(Map<String, Object> map)throws Exception{
		StringBuffer sql=new StringBuffer();
		String upwd = StringUtil.nullToEmpty(map.get("upwd"));
		String loginid = StringUtil.nullToEmpty(map.get("loginid"));
        sql.append(" SELECT count(*) FROM TUSER where USER_ID=? and USER_PWD=?");
		return baseDao.findIntBySql(sql.toString(), new Object[] {loginid,upwd});
    }
  	
  	public int UpdateUserpwd(Map<String, Object> map)throws Exception{
		 StringBuffer sql= new StringBuffer();
		 List<String> list = new ArrayList<String>();
		 sql.append("update tuser set ");
		 sql.append(" USER_PWD =? ");
		 list.add(StringUtil.nullToEmpty(map.get("txtunewpwd")));
		 if(StringUtil.isNotEmpty(StringUtil.nullToEmpty(map.get("loginid")))){
			 sql.append(" where  USER_ID =? ");
			 list.add(StringUtil.nullToEmpty(map.get("loginid")));
		 }
		 if(StringUtil.isNotEmpty(StringUtil.nullToEmpty(map.get("txtuname")))){
			 sql.append(" and  USER_NAME =? ");
			 list.add(StringUtil.nullToEmpty(map.get("txtuname")));
		 }
		 return baseDao.updateBySql(sql.toString(),list.toArray());
  }
  	
  	public List<Map<String, Object>> userschjekglist(Map<String, Object> map)throws Exception{
  		StringBuffer sql=new StringBuffer();
  		List<String> list = new ArrayList<String>();
	    sql.append(" select USER_ID,USERNAMECHK from tuser ");
	    return baseDao.findListBySql(sql.toString(),list.toArray());
      }
  	
  	public int loadByIdUseradmin(Map<String, Object> map)throws Exception{
		StringBuffer sql=new StringBuffer();
		String loginid = StringUtil.nullToEmpty(map.get("loginid"));
        sql.append(" select count(*) from tuserole where ROLEID = ");
        sql.append(" (select ROLEID from trole where ROLENAME='管理员') and USER_ID =? ");
		return baseDao.findIntBySql(sql.toString(), new Object[] {loginid});
    }
  	
}
