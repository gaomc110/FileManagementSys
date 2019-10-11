package cn.com.sparknet.common.dialect;

import java.sql.Types;
import org.hibernate.dialect.Oracle10gDialect;
import org.hibernate.type.StringType;

/**
 * 自定义Oracle方言
 * @author chenxy
 *
 */
public class CustomOracle10gDialect extends Oracle10gDialect {
	
	public CustomOracle10gDialect(){
	    super();
	    //注册nvarchar类型，为解决原生SQL查询时出现No Dialect mapping for JDBC type: -9错误
	    registerHibernateType(Types.NVARCHAR, StringType.INSTANCE.getName());
	}
	
}
