package cn.com.sparknet.common.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

public class MyJdbcTemplate {
	
	public class MyQueryScanResultSetExtractor implements ResultSetExtractor<Map<String, Object>> {

		@Override
		public Map<String, Object> extractData(ResultSet rs) throws SQLException, DataAccessException {
			Map<String, Object> infoMap = new HashMap<String, Object>(); 
			ResultSetMetaData metaData = rs.getMetaData() ;
			int columnCount = metaData.getColumnCount() ;
			String columnName;
			for(int i=1;i<=columnCount;i++)
			{
				columnName = metaData.getColumnName(i);
				infoMap.put(columnName, rs.getObject(columnName)); 
			}
			return infoMap;
		} 
	}
	
	public static List<Map<String, Object>> toBaseSql(DataSource ds, String sql)
	{
		JdbcTemplate jt = new JdbcTemplate(ds); 
		List<Map<String, Object>> list = jt.queryForList(sql) ;
		return list;
	}
	
	public static List<Map<String, Object>> toBaseSqlList(DataSource ds, String sql, int start, int limit)
	{
		JdbcTemplate jt = new JdbcTemplate(ds); 
		sql = " select * from ( select rownum rn, t0.* from ( "+sql+" ) t0 ) tt where tt.rn between "+start+" and "+limit ;
		List<Map<String, Object>> list = jt.queryForList(sql) ;
		return list;
	}
	
	
	public static Page toBaseSqlPage(DataSource ds, String sql, String start, String limit)
	{
		JdbcTemplate jt = new JdbcTemplate(ds); 
		Integer rowCount = jt.queryForObject("SELECT COUNT(*) tmpCount FROM ("+sql+") TMP", Integer.class) ; 
		sql = " select * from ( select rownum rn, t0.* from ( "+sql+" ) t0 ) tt where tt.rn between "+start+" and "+limit ;
		List<Map<String, Object>> list = jt.queryForList(sql) ;
    	return new Page(rowCount, list) ;
	}
	
	public static Map<String, Object> toBaseSqlcount(DataSource ds, String sql)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		JdbcTemplate jt = new JdbcTemplate(ds); 
		Integer rowCount = jt.queryForObject("SELECT COUNT(*) RECORDNUM FROM ("+sql+") TMP", Integer.class);
		map.put("RECORDNUM",rowCount);
    	return map;
	}
}
