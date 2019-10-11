package cn.com.sparknet.common.dao;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import cn.com.sparknet.common.util.StringUtil;

@Repository ("menuDao")
public class MenuDao {
	@Resource
    BaseDao baseDao;
	@Autowired
    DataSource defaultDataSource;
	
	public List<Map<String, Object>> rolemenupad(Map<String, Object> map)throws Exception{
		StringBuffer sql= new StringBuffer();
        String parid = StringUtil.nullToEmpty(map.get("parid"));
        String loginid = StringUtil.nullToEmpty(map.get("loginid"));
		sql.append(" select MENU_ID id,MENU_NAME title,MENU_URL href,MENU_ICON icon,");
		sql.append(" PARID from T_DA_MENU WHERE PARID =? ");
		sql.append(" and MENU_ID in(select MENU_ID from T_DA_ROLEMENU ");
		sql.append(" WHERE ROLEID in (select ROLEID from T_DA_USERROLE where U_ID=?)) order by CREATE_DATE desc ");
		
        return baseDao.findListBySql(sql.toString(), new Object[] {parid,loginid});
	}
	
	public List<Map<String, Object>> rolemenuChildren(Map<String, Object> map)throws Exception{
		StringBuffer sql= new StringBuffer();
        String parid = StringUtil.nullToEmpty(map.get("parid"));
        String loginid = StringUtil.nullToEmpty(map.get("loginid"));
        sql.append(" select MENU_ID id,MENU_NAME title,MENU_URL href,MENU_ICON icon,PARID from T_DA_MENU where PARID IN(select MENU_ID from T_DA_MENU WHERE PARID =?) ");
        sql.append(" and MENU_ID in(select menu_id from T_DA_ROLEMENU ");
		sql.append(" WHERE ROLEID in (select ROLEID from T_DA_USERROLE where U_ID=?)) order by CREATE_DATE desc ");
        return baseDao.findListBySql(sql.toString(), new Object[] {parid,loginid});
	}
	
	public List<Map<String, Object>> MenuBtnlist(Map<String, Object> map)throws Exception{
		StringBuffer sql= new StringBuffer();
        String menu_id = StringUtil.nullToEmpty(map.get("menu_id"));
        String loginid = StringUtil.nullToEmpty(map.get("loginid"));
        sql.append(" select MENU_ID,MENU_NAME from t_da_menu where PARID=? and MENU_ID IN( ");
        sql.append(" SELECT MENU_ID FROM t_da_rolemenu WHERE ROLEID= ");
        sql.append(" (SELECT ROLEID FROM t_da_userrole where U_ID=?)) ");
        return baseDao.findListBySql(sql.toString(), new Object[] {menu_id,loginid});
	}
}
