package cn.com.sparknet.common.dao;


import cn.com.sparknet.common.util.StringUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("OperationalDao")
public class OperationalDao {

    @Resource
    BaseDao baseDao;

    public Page OperationalList(Map<String, Object> map) {
        StringBuffer sql= new StringBuffer();
        List<String> list = new ArrayList<String>();
        String start = StringUtil.nullToEmpty(map.get("page"));
        String end = StringUtil.nullToEmpty(map.get("rows"));

        sql.append(" select t.logid,t.account,t.person,t.community_name,t.qiuquan_no,t.type,t.create_date ");
        sql.append(" from t_da_operation_log t ");
        sql.append(" where 1=1 ");
        sql.append(" and t.state = 'A' ");

        if(StringUtil.isNotEmpty(StringUtil.nullToEmpty(map.get("type")))){
            sql.append(" and t.type = ? ");
            list.add(StringUtil.nullToEmpty(map.get("type")));
        }
        if(StringUtil.isNotEmpty(StringUtil.nullToEmpty(map.get("ustart_date")))){
            sql.append(" and t.create_date >= TO_TIMESTAMP(?,'yyyy-mm-dd hh24:mi:ss') ");
            list.add(StringUtil.nullToEmpty(map.get("ustart_date")));
        }
        if(StringUtil.isNotEmpty(StringUtil.nullToEmpty(map.get("uend_date")))){
            sql.append(" and t.create_date >= TO_TIMESTAMP(?,'yyyy-mm-dd hh24:mi:ss') ");
            list.add(StringUtil.nullToEmpty(map.get("uend_date")));
        }
        if(StringUtil.isNotEmpty(StringUtil.nullToEmpty(map.get("account")))){
            sql.append(" and t.account like ? escape '\\' ");
            list.add("%"+(StringUtil.nullToEmpty(map.get("account"))).replace("_", "\\_").replace("%", "\\%")+"%");
        }
        if(StringUtil.isNotEmpty(StringUtil.nullToEmpty(map.get("person")))){
            sql.append(" and t.person like ? escape '\\' ");
            list.add("%"+(StringUtil.nullToEmpty(map.get("person"))).replace("_", "\\_").replace("%", "\\%")+"%");
        }

        sql.append(" order by t.create_date desc ");
        return baseDao.findPageAllBySql(sql.toString(),start,end,list.toArray());
    }
}
