package cn.com.sparknet.common.task;

import org.apache.log4j.Logger;
import cn.com.sparknet.common.util.CacheUtil;
import cn.com.sparknet.common.util.FileUtil;
import cn.com.sparknet.common.util.StringUtil;

/**
 * 删除临时目录
 * @author chenxy
 *
 */
public class DeleteTempDirectory {
	
	private static Logger log = Logger.getLogger(DeleteTempDirectory.class);
	
	public void execute() throws Exception {
		try{
			String tempDirectory=CacheUtil.getInstance().getStringValue("ConfigCache", "common.file.tempDirectory");
			if(StringUtil.isNotEmpty(tempDirectory)){
				FileUtil.delAllFile(tempDirectory);
			}
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
	}
	
}
