package cn.com.sparknet.common.dao;

import cn.com.sparknet.common.util.StringUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("ArchivesQueryDao")
public class ArchivesQueryDao {
    @Resource
    BaseDao baseDao;


    public Page FilelocationList(Map<String, Object> map) {
        StringBuffer sql= new StringBuffer();
        List<String> list = new ArrayList<String>();
        String start = StringUtil.nullToEmpty(map.get("page"));
        String end = StringUtil.nullToEmpty(map.get("rows"));

        sql.append(" select t.id,t.community_name,t.qiuquan_no,t.building_no,t.property_owner,t.house_adress,t.file_location, ");
        sql.append(" to_char(t. create_date, 'YYYY-MM-DD HH24:MI:SS') create_date ");
        sql.append(" from t_da_archives_info t ");
        sql.append(" where 1=1 ");
        sql.append(" and t.state = 'A' ");

        if(StringUtil.isNotEmpty(StringUtil.nullToEmpty(map.get("communityName")))){
            sql.append(" and t.community_name like ? escape '\\' ");
            list.add("%"+(StringUtil.nullToEmpty(map.get("communityName"))).replace("_", "\\_").replace("%", "\\%")+"%");
        }
        if(StringUtil.isNotEmpty(StringUtil.nullToEmpty(map.get("houseAddress")))){
            sql.append(" and t.house_adress like ? escape '\\' ");
            list.add("%"+(StringUtil.nullToEmpty(map.get("houseAddress"))).replace("_", "\\_").replace("%", "\\%")+"%");
        }
        if(StringUtil.isNotEmpty(StringUtil.nullToEmpty(map.get("qiuquanNo")))){
            sql.append(" and t.qiuquan_no like ? escape '\\' ");
            list.add("%"+(StringUtil.nullToEmpty(map.get("qiuquanNo"))).replace("_", "\\_").replace("%", "\\%")+"%");
        }
        if(StringUtil.isNotEmpty(StringUtil.nullToEmpty(map.get("propertyOwner")))){
            sql.append(" and t.property_owner like ? escape '\\' ");
            list.add("%"+(StringUtil.nullToEmpty(map.get("propertyOwner"))).replace("_", "\\_").replace("%", "\\%")+"%");
        }

        sql.append(" order by t.create_date desc ");
        return baseDao.findPageAllBySql(sql.toString(),start,end,list.toArray());
    }
}
