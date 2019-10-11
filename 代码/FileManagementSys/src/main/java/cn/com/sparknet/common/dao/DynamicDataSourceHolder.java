package cn.com.sparknet.common.dao;

/**
 * 动态数据源（设置数据源名称）
 * @author chenxy
 *
 */
public class DynamicDataSourceHolder {
	
	//声明线程局部变量，每一个使用该变量的线程都提供一个变量值的副本，从而不会与其他线程的副本冲突
	private static final ThreadLocal<String> threadLocal = new ThreadLocal<String>();
	
	/**
	 * 设置数据源
	 */
	public static void setDataSource(String dataSourceName) {
		threadLocal.set(dataSourceName);
    }

	/**
	 * 获取数据源
	 */
    public static String getDataSource() {
        return threadLocal.get();
    }

    /**
	 * 移除数据源
	 */
    public static void removeDataSource() {
    	threadLocal.remove();
    }

}
