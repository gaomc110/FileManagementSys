package cn.com.sparknet.common.init;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import cn.com.sparknet.common.util.CacheUtil;
import cn.com.sparknet.common.util.ConfigUtil;

/**
 * 初始化属性配置文件
 * @author chenxy
 *
 */
@Component
public class InitProperties {
	
	private final static String CONFIG_CACHE_NAME = "ConfigCache";
	
	private List<String> configFileNames=new ArrayList<String>();
	
	public InitProperties(){
		configFileNames.add("log4j.properties");
		configFileNames.add("config/common.properties");
		configFileNames.add("config/manage.properties");
	}
	
	@PostConstruct
	private void init() {
        try {
        	//将所有上方已配置的属性文件加入至缓存
        	Map map=null;
        	CacheUtil cacheUtil=CacheUtil.getInstance();
        	cacheUtil.addCache(CONFIG_CACHE_NAME);
        	for(String name:configFileNames){
        		map=new ConfigUtil(name).getAllProperty();
        		if(null!=map){
        			cacheUtil.addElement(CONFIG_CACHE_NAME, map);
        		}
        	}
        } catch (Exception e) {
        	throw new RuntimeException(e.getMessage(),e);
        }
	}




	/*public static final Map<String, Map<String, Object>> ProjDBMap = new HashMap<String, Map<String, Object>>();
	 
	public List<Map<String, Object>> findDbConfigList()
	{
		// 查询配置表 
    	DataSource dataSource = (DataSource) springContextUtil.getBean("defaultDataSource");
    	JdbcTemplate jt = new JdbcTemplate(dataSource);
    	  
    	List<Map<String, Object>> infoList = jt.execute(new ConnectionCallback<List<Map<String, Object>>>() { 
			@Override
			public List<Map<String, Object>> doInConnection(Connection con) throws SQLException, DataAccessException { 
				String sql = "select drivername,url,username,password,datasource_name,projectid "
						+ "from T_DBBROWER_CATALOG ";
				ResultSet rs = con.createStatement().executeQuery(sql);
				
				List<Map<String, Object>> infoList = new ArrayList<Map<String, Object>>(); 
				while(rs.next())
				{
					Map<String, Object> rowMap = new HashMap<String, Object>() ;
					rowMap.put("DRIVERNAME", rs.getString("drivername")) ;
					rowMap.put("URL", rs.getString("url")) ;
					rowMap.put("USERNAME", rs.getString("username")) ;
					rowMap.put("PASSWORD", rs.getString("password")) ;
					rowMap.put("DATASOURCE_NAME", rs.getString("datasource_name")) ;  
					ProjDBMap.put(rs.getString("projectid"), rowMap) ; 
					infoList.add(rowMap) ;
				}
//	            con.commit();
				return infoList;
			}
		}) ; 
    	
    	return infoList;
	}
	*/
}
