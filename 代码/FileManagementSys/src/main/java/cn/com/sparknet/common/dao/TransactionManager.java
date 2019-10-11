package cn.com.sparknet.common.dao; 

import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;   
import org.springframework.transaction.PlatformTransactionManager;   
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;   

/**
 * Spring编程式事务
 * @author chenxy
 */
public class TransactionManager {
	
	private PlatformTransactionManager transactionManager;
	private DefaultTransactionDefinition def;
	private TransactionStatus status;
    
	public TransactionManager(DataSource ds){
         transactionManager = new DataSourceTransactionManager(ds);
         def = new DefaultTransactionDefinition();
         def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
         def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
         status = transactionManager.getTransaction(def);
    }
       
    public void rollback(){
    	transactionManager.rollback(status);   
    }
       
    public void commit(){
    	transactionManager.commit(status);   
    }
    
}
