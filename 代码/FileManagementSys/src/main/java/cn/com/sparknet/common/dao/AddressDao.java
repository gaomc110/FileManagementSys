package cn.com.sparknet.common.dao;


import cn.com.sparknet.common.util.StringUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("AddressDao")
public class AddressDao {

    @Resource
    BaseDao baseDao;

    public Page AddressList(Map<String, Object> map) {
        StringBuffer sql= new StringBuffer();
        List<String> list = new ArrayList<String>();
        String start = StringUtil.nullToEmpty(map.get("page"));
        String end = StringUtil.nullToEmpty(map.get("rows"));

        sql.append(" select t.id,t.belongtoadress,t.community_name,t.house_adress,t.file_adress,TO_CHAR(t.create_date, 'YYYY-MM-DD HH24:MI:SS') create_date ");
        sql.append(" from t_da_address_manage t ");
        sql.append(" where 1=1 ");
        sql.append(" and t.state = 'A' ");

        if(StringUtil.isNotEmpty(StringUtil.nullToEmpty(map.get("belongAdd")))){
            sql.append(" and t.belongtoadress = ? ");
            list.add(StringUtil.nullToEmpty(map.get("belongAdd")));
        }
        if(StringUtil.isNotEmpty(StringUtil.nullToEmpty(map.get("communityName")))){
            sql.append(" and t.community_name like ? escape '\\' ");
            list.add("%"+(StringUtil.nullToEmpty(map.get("communityName"))).replace("_", "\\_").replace("%", "\\%")+"%");
        }
        if(StringUtil.isNotEmpty(StringUtil.nullToEmpty(map.get("houseAdress")))){
            sql.append(" and t.house_adress like ? escape '\\' ");
            list.add("%"+(StringUtil.nullToEmpty(map.get("houseAdress"))).replace("_", "\\_").replace("%", "\\%")+"%");
        }

        sql.append(" order by t.create_date desc ");
        return baseDao.findPageAllBySql(sql.toString(),start,end,list.toArray());
    }
}
