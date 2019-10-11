package cn.com.sparknet.common.dao;


import cn.com.sparknet.common.util.StringUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("AuthorityDao")
public class AuthorityDao {

    @Resource
    BaseDao baseDao;

    public Page RoleList(Map<String, Object> map) {
        StringBuffer sql = new StringBuffer();
        List<String> list = new ArrayList<String>();
        String start = StringUtil.nullToEmpty(map.get("page"));
        String end = StringUtil.nullToEmpty(map.get("rows"));

        sql.append(" SELECT A.ROLEID,A.ROLENAME, A.Remarks,TO_CHAR(A.Create_Date, 'YYYY-MM-DD HH24:MI:SS') Create_Date,A.Create_Preson ");
        sql.append(" FROM T_DA_ROLE A ");
        sql.append(" where 1=1 ");

        if (StringUtil.isNotEmpty(StringUtil.nullToEmpty(map.get("roleName")))) {
            sql.append(" and A.ROLENAME like ? escape '\\' ");
            list.add("%" + (StringUtil.nullToEmpty(map.get("roleName"))).replace("_", "\\_").replace("%", "\\%") + "%");
        }

        sql.append(" order by A.Update_Date desc ");
        return baseDao.findPageAllBySql(sql.toString(), start, end, list.toArray());
    }
}
