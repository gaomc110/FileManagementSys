package cn.com.sparknet.common.dao;

import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 数据库函数适配器
 * @author chenxy
 *
 */
@Component
public class FunctionAdapter {
	
	@Resource
	private BaseDao baseDao;
	
	/**
	 * 列转行函数
	 * @param fieldName 字段名
	 * @return
	 */
	public String concat(String fieldName){
		String dialect=baseDao.getDialect();
		if(dialect.equals("Oracle")){
			return "to_char(wm_concat("+fieldName+"))";
		}else if(dialect.equals("MySQL")){
			return "group_concat("+fieldName+")";
		}else{
			return null;
		}
	}
	
	/**
	 * 获取系统当前日期时间函数
	 * @return
	 */
	public String currentDateTime(){
		String dialect=baseDao.getDialect();
		if(dialect.equals("Oracle")){
			return "SYSDATE";
		}else if(dialect.equals("MySQL")){
			return "CURRENT_TIMESTAMP";
		}else{
			return null;
		}
	}

}
