package cn.com.sparknet.common.dao;

import cn.com.sparknet.common.util.StringUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("dealInformationDao")
public class DealInformationDao {
    @Resource
    BaseDao baseDao;


    public Page DealInformationList(Map<String, Object> map) {
        StringBuffer sql= new StringBuffer();
        List<String> list = new ArrayList<String>();
        String start = StringUtil.nullToEmpty(map.get("page"));
        String end = StringUtil.nullToEmpty(map.get("rows"));

        sql.append(" select a.id,a.qiuquan_no,a.community_name,a.building_no,b.deal_no,b.deal_type,b.deal_state,to_char(b.deal_date, 'YYYY-MM-DD HH24:MI:SS') deal_date ");
        sql.append(" from t_da_archives_info a, t_da_deal_info b ");
        sql.append(" where a.archives_no = b.archives_no ");

        if(StringUtil.isNotEmpty(StringUtil.nullToEmpty(map.get("StartdealDate")))){
            sql.append(" and b.deal_date >= TO_DATE( ? , 'YYYY-MM-DD HH24:MI:SS') ");
            list.add(StringUtil.nullToEmpty(map.get("StartdealDate")));
        }
        if(StringUtil.isNotEmpty(StringUtil.nullToEmpty(map.get("EnddealDate")))) {
            sql.append(" and b.deal_date < TO_DATE( ? , 'YYYY-MM-DD HH24:MI:SS') ");
            list.add(StringUtil.nullToEmpty(map.get("EnddealDate")));
        }
        if(StringUtil.isNotEmpty(StringUtil.nullToEmpty(map.get("communityName")))){
            sql.append(" and a.community_name like ? escape '\\' ");
            list.add("%"+(StringUtil.nullToEmpty(map.get("communityName"))).replace("_", "\\_").replace("%", "\\%")+"%");
        }
        if(StringUtil.isNotEmpty(StringUtil.nullToEmpty(map.get("qiuquanNo")))){
            sql.append(" and a.qiuquan_no like ? escape '\\' ");
            list.add("%"+(StringUtil.nullToEmpty(map.get("qiuquanNo"))).replace("_", "\\_").replace("%", "\\%")+"%");
        }
        sql.append(" order by b.deal_date desc ");
        return baseDao.findPageAllBySql(sql.toString(),start,end,list.toArray());
    }
}
