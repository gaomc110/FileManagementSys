package cn.com.sparknet.common.filter;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 文件前缀过滤器
 * @author chenxy
 *
 */
public class FilePrefixNameFilter implements FilenameFilter {
	
	//文件名前缀
	private String filePrefixName;
	
	public FilePrefixNameFilter(String filePrefixName){
		this.filePrefixName=filePrefixName;
	}

	@Override
	public boolean accept(File dir, String name) {
		boolean result=false;
		try{
			//文件名是否为指定的前缀开头
			result=name.startsWith(filePrefixName);
		}catch(Exception e){
			throw new RuntimeException(e.getMessage(),e);
		}
		return result;
	}

}
