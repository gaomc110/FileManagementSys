package cn.com.sparknet.common.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
  

/**
 * JDBC操作类
 * @author chenxy
 *
 */
public class JDBCDao {
	
	private Connection conn=null;
	
/**表数据推送修订：2017/10/11 author: yangtao (添加全量原表查询优化) */	
//	private PreparedStatement pst=null;
//	private ResultSet rs = null;
	
	public JDBCDao(DataSource dataSource,boolean autoCommit){
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(autoCommit);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		} 
	}
	 
	// javabean
	public static void main(String[] args)
	{
		Map param;
		//--> url, driver
//			JDBCDao dao = new JDBCDao(url, driver, param.get("user"),param.get("pwd")) ;
//			dao.executeQuery(sql, null) ;
	}
	
	public JDBCDao(String url, String driver, String user, String pwd, boolean autoCommit)
	{
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pwd);
			conn.setAutoCommit(autoCommit);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		} 
	}
	
	public JDBCDao(String url, String driver, boolean autoCommit)
	{
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url);
			conn.setAutoCommit(autoCommit);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		} 
	}
	
	public boolean isConnection()
	{
		try {
			if(this.conn != null && !this.conn.isClosed())
			{
				return true;
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		} 
		return false;
	}
	
	/**
	 * 提交
	 */
	public void commit(){
		try {
			conn.commit();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		} 
	}
	
	/**
	 * 回滚
	 */
	public void rollback(){
		try {
			conn.rollback();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		} 
	}
	
	/**
	 * 还原现场
	 */
	public void setAutoCommitTrue(){
		try {
			conn.setAutoCommit(true);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		} 
	}

	/**
	 * 执行查询
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	public ResultSet executeQuery(String sql,List<Object> params) throws Exception {
		ResultSet rs=null;
		try{
			PreparedStatement pst = this.conn.prepareStatement(sql);
			if(params != null && ! params.isEmpty()){
				for(int i=0;i<params.size();i++){
					pst.setObject(i+1, params.get(i));
				}
			}
			rs = pst.executeQuery();
		}catch(Exception e){
			throw new RuntimeException(e.getMessage(),e);
		}
		return rs;
	}
	
	/**查询多条记录 
     * @param sql 
     * @param params 
     * @return 
     * @throws SQLException 
     */  
    public List<Map<String, Object>> findModeResult(String sql, List<Object> params) throws Exception{  
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();  
        int index = 1;  
        PreparedStatement pstmt = this.conn.prepareStatement(sql); 
        if(params != null && !params.isEmpty()){  
            for(int i = 0; i<params.size(); i++){  
                pstmt.setObject(index++, params.get(i));  
            }  
        }  
        ResultSet resultSet = pstmt.executeQuery();  
        ResultSetMetaData metaData = resultSet.getMetaData();  
        int cols_len = metaData.getColumnCount();  
        while(resultSet.next()){  
            Map<String, Object> map = new HashMap<String, Object>();  
            for(int i=0; i<cols_len; i++){  
                /*String cols_name = metaData.getColumnName(i+1);  */
            	String cols_name = metaData.getColumnLabel(i+1);
                Object cols_value = resultSet.getObject(cols_name);  
                if(cols_value == null){  
                    cols_value = "";  
                }  
                map.put(cols_name, cols_value);  
            }  
            list.add(map);  
        }  
  
        return list;  
    } 
    
    /**查询多条记录 +数据库类型
     * @param sql 
     * @param params 
     * @return 
     * @throws SQLException 
     */  
    public List<Map<String, Object>> findModeResult(String sql, List<Object> params,String dbtype) throws Exception{  
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();  
        int index = 1;
        ResultSet resultSet=null;
        PreparedStatement pstmt =null;
        Statement stmt =null;
        if(dbtype.equals("3")){ //sqlserver
        	stmt = this.conn.createStatement();
        	resultSet = stmt.executeQuery(sql);
        }else{
        	pstmt = this.conn.prepareStatement(sql); 
        	if(params != null && !params.isEmpty()){  
                for(int i = 0; i<params.size(); i++){  
                    pstmt.setObject(index++, params.get(i));  
                }  
            } 
        	resultSet = pstmt.executeQuery(); 
        }
         
        ResultSetMetaData metaData = resultSet.getMetaData();  
        int cols_len = metaData.getColumnCount();  
        while(resultSet.next()){  
            Map<String, Object> map = new HashMap<String, Object>();  
            for(int i=0; i<cols_len; i++){  
                String cols_name = metaData.getColumnName(i+1);  
                Object cols_value = resultSet.getObject(cols_name);  
                if(cols_value == null){  
                    cols_value = "";  
                }  
                map.put(cols_name, cols_value);  
            }  
            list.add(map);  
        }  
  
        return list;  
    } 
    
    /** 
     * 增加、删除、改 
     * @param sql 
     * @param params 
     * @return 
     * @throws SQLException 
     */  
    public boolean updateByPreparedStatement(String sql, List<Object>params)throws Exception{  
        boolean flag = false;  
        int result = -1;  
        PreparedStatement pstmt = this.conn.prepareStatement(sql);  
        int index = 1;  
        if(params != null && !params.isEmpty()){  
            for(int i=0; i<params.size(); i++){  
                pstmt.setObject(index++, params.get(i));  
            }  
        }  
        result = pstmt.executeUpdate();  
        flag = result > 0 ? true : false;  
        return flag;  
    }  
  
    /** 
     * 查询单条记录 
     * @param sql 
     * @param params 
     * @return 
     * @throws SQLException 
     */  
    public Map<String, Object> findSimpleResult(String sql, List<Object> params) throws Exception{  
        Map<String, Object> map = new HashMap<String, Object>();  
        int index  = 1;  
        PreparedStatement pstmt = this.conn.prepareStatement(sql);  
        if(params != null && !params.isEmpty()){  
            for(int i=0; i<params.size(); i++){  
                pstmt.setObject(index++, params.get(i));  
            }  
        }  
        ResultSet resultSet = pstmt.executeQuery();//返回查询结果  
        ResultSetMetaData metaData = resultSet.getMetaData();  
        int col_len = metaData.getColumnCount();  
        while(resultSet.next()){  
            for(int i=0; i<col_len; i++ ){  
                String cols_name = metaData.getColumnName(i+1);  
                Object cols_value = resultSet.getObject(cols_name);  
                if(cols_value == null){  
                    cols_value = "";  
                }  
                map.put(cols_name, cols_value);  
            }  
        }  
        return map;  
    }  
	 
	
	/**
	 * 执行查询,返回数量
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	public int executeTableCount(String sql,List<Object> params) throws Exception {
		int rowCount = 0;
		ResultSet rs;
		try{
			PreparedStatement pst = this.conn.prepareStatement(sql);
			if(params != null && ! params.isEmpty()){
				for(int i=0;i<params.size();i++){
					pst.setObject(i+1, params.get(i));
				}
			}
			rs = pst.executeQuery();
			if(rs.next()){      
			    //rowCount=rs.getInt("CO");      
			    rowCount = rs.getInt(1);  
			}   
		}catch(Exception e){
			throw new RuntimeException(e.getMessage(),e);
		}
		return rowCount;
	}
	
	/**
	 * 执行更新
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	public int executeUpdate(String sql,List<Object> params) throws Exception {
		int count=0;
		PreparedStatement pst = null;
		try{
			pst = this.conn.prepareStatement(sql);
			if(params != null && ! params.isEmpty()){
				for(int i=0;i<params.size();i++){
					pst.setObject(i+1, params.get(i));
				}
			}
			count = pst.executeUpdate();
		}catch(Exception e){
			throw new RuntimeException(e.getMessage(),e);
		}finally{
			if(null!=pst){
				pst.close();
				pst=null;
			}
		}
		return count;
	}
	
	/**
	 * 执行blob更新
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	public int executeUpdateBlob(String sql,byte[] Buffer) throws Exception {
		int count=0;
		PreparedStatement pst = null;
		try{
			pst = this.conn.prepareStatement(sql);
			pst.setBytes(1, Buffer);
			count = pst.executeUpdate();
		}catch(Exception e){
			throw new RuntimeException(e.getMessage(),e);
		}finally{
			if(null!=pst){
				pst.close();
				pst=null;
			}
		}
		return count;
	}
	
	/**
	 * 关闭连接
	 * @param rs 可为空
	 */
	public void close(){
		try{
//			if(null!=rs){
//				rs.close();
//				rs=null;
//			}
//			if(null!=pst){
//				pst.close();
//				pst=null;
//			}
			if(null!=conn){
				conn.close();
				conn=null;
			}
		}catch(Exception e){
			throw new RuntimeException(e.getMessage(),e);
		}
	}
	
	/**
	 * 关闭
	 * @param rs 可为空
	 */
	public void closePreparedStatement(){
		try{
//			if(null!=pst){
//				pst.close();
//				pst=null;
//			}
		}catch(Exception e){
			throw new RuntimeException(e.getMessage(),e);
		}
	}
	
	/*public static void main(String[] args) throws Exception {
		String sql="";
		DataSource ds=null;
		//查数据
		JDBCDao dao1=new JDBCDao(ds,true);
		ResultSet rs=dao1.executeQuery(sql, null);
		dao1.close(rs);
		//插数据
		JDBCDao dao2=new JDBCDao(ds,true);
		dao2.executeUpdate(sql, null);
		dao2.close(null);
	}*/

	/**
     * 查询返回map
     * @param sql
     * @param array
     * @return
	 * @throws Exception 
     */
    public Map<String, Object> findMapBySql(JDBCDao jdbcDao, String sql, List<Object> params) throws Exception {
        ResultSet rs = jdbcDao.executeQuery(sql, params);
        if(rs.next()){
//            rs.
        }
        return null;
    }

    /**
	 * 执行更新(PreparedStatement+批处理)
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	public int executeUpdateByBatch(String sql,List<Object> params) throws Exception {
		int count=0;
		PreparedStatement pst = null;
		try{
			pst = this.conn.prepareStatement(sql);
			if(params != null && ! params.isEmpty()){
				for(int i=0;i<params.size();i++){
					pst.setObject(i+1, params.get(i));
				}
			}
			pst.addBatch(); 
			pst.executeBatch();  
		}catch(Exception e){
			throw new RuntimeException(e.getMessage(),e);
		}finally{
			if(null!=pst){
				pst.close();
				pst=null;
			}
		}
		return count;
	}
	
	/**
	 * 执行blob更新(PreparedStatement+批处理)
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	public int executeUpdateBlobByBatch(String sql,byte[] Buffer) throws Exception {
		int count=0;
		PreparedStatement pst = null;
		try{
			pst = this.conn.prepareStatement(sql);
			pst.setBytes(1, Buffer);
			pst.addBatch(); 
			pst.executeBatch();  
		}catch(Exception e){
			throw new RuntimeException(e.getMessage(),e);
		}finally{
			if(null!=pst){
				pst.close();
				pst=null;
			}
		}
		return count;
	}
}
