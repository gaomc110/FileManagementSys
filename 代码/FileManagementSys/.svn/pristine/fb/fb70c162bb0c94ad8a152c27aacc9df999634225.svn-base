package cn.com.sparknet.common.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.com.sparknet.common.dao.BaseDao;
import cn.com.sparknet.common.util.StringUtil;

/**
 * 用户登录
 * @author GAOMC
 *
 */
@Repository ("loginDao")
public class LoginDao {
    
    /**
     * 用户登录
     * @param userStaffCode
     * @param passwd
     * @return
     */
    @Resource
    BaseDao baseDao;
    
    public int contuserlogo(Map<String, Object> map)throws Exception{
   	 StringBuffer sql=new StringBuffer();
   	String yonghuming=StringUtil.nullToEmpty(StringUtil.strFiltrate((String)map.get("yonghuming") ));
    String yonghumima=StringUtil.nullToEmpty(StringUtil.strFiltrate((String)map.get("yonghumima") ));
        sql.append("select count(*) from T_DA_USER where U_NAME = ? AND U_PWD = ?");
        return baseDao.findIntBySql(sql.toString(), new Object[] {yonghuming,yonghumima});
   }
    
    public List<Map<String, Object>> businessLogin(Map<String, Object> map) throws Exception{
        StringBuffer sql = new StringBuffer();
        String yonghuming=StringUtil.nullToEmpty(StringUtil.strFiltrate((String)map.get("yonghuming") ));
        String yonghumima=StringUtil.nullToEmpty(StringUtil.strFiltrate((String)map.get("yonghumima") ));
        sql.append(" select  * from T_DA_USER WHERE");
        sql.append(" U_NAME = ? AND U_PWD = ? AND STATE = 'A' ");
        return baseDao.findListBySql(sql.toString(), new String[]{yonghuming,yonghumima});
    }

    

}
