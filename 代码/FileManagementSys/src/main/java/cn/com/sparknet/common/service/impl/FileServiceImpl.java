package cn.com.sparknet.common.service.impl;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cn.com.sparknet.common.bean.FileFormInfo;
import cn.com.sparknet.common.bean.FileInfo;
import cn.com.sparknet.common.service.FileService;
import cn.com.sparknet.common.util.CacheUtil;
import cn.com.sparknet.common.util.FileUtil;
import cn.com.sparknet.common.util.InputStreamUtil;
import cn.com.sparknet.common.util.UUIDUtil;

/**
 * 文件操作
 * @author chenxy
 *
 */
@Service
public class FileServiceImpl implements FileService {
	
	/**
	 * 上传文件（不包括表单参数）
	 */
	public List<FileInfo> uploadFile(HttpServletRequest request){
        return uploadFile(request, false).getFileInfos();
    }
	
	/**
	 * 上传文件（包括表单参数）
	 */
	public FileFormInfo uploadFileWithParams(HttpServletRequest request){
		return uploadFile(request, true);
    }
	
	private FileFormInfo uploadFile(HttpServletRequest request,boolean ifParam){
		FileFormInfo fileFormInfo=new FileFormInfo(); 
		fileFormInfo.setFileInfos(new ArrayList<FileInfo>());
		fileFormInfo.setFormParams(new HashMap<String,Object>());
        try{
        	FileItemFactory factory = new DiskFileItemFactory();
    		ServletFileUpload upload = new ServletFileUpload(factory);
        	List<FileItem> items = upload.parseRequest(request);
        	Iterator<FileItem> it = items.iterator();
			while (it.hasNext()) {
				FileItem item = it.next();
				String fileName = item.getName();
				if (item.isFormField()) {
					if(ifParam){
						fileFormInfo.getFormParams().put(item.getFieldName(), item.getString("UTF-8"));
					}
				}else if(StringUtils.isNotBlank(fileName)){
					fileName = java.net.URLDecoder.decode(fileName, "UTF-8");
					fileName = FilenameUtils.getName(fileName);
					String tempDirectory=CacheUtil.getInstance().getStringValue("ConfigCache","common.file.tempDirectory")+UUIDUtil.getNextValue();
					File file = new File(tempDirectory, fileName);
					if (!file.exists()) {
						file.getParentFile().mkdirs();
					};
					item.write(file);
					FileInfo fileInfo = new FileInfo();
					fileInfo.setFileName(fileName);
					fileInfo.setFilePrefixName(FilenameUtils.getBaseName(fileName));
					fileInfo.setFileSuffixName(FilenameUtils.getExtension(fileName));
					fileInfo.setFileSize(item.getSize());
					fileInfo.setFilePath(file.getPath());
					fileFormInfo.getFileInfos().add(fileInfo);
				}
			}
        }catch(Exception e){
        	throw new RuntimeException(e.getMessage(),e);
        }
        return fileFormInfo;
	}
	
	/**
	 * 下载文件
	 */
	public void downloadFile(InputStream is,String fileName,HttpServletResponse response){
        try{
        	FileUtil.downloadFile(is, fileName, response);
        }catch(Exception e){
        	throw new RuntimeException(e.getMessage(),e);
        }
    }
	
	/**
	 * 显示图片
	 */
	public void showPic(InputStream is, HttpServletResponse response){
		OutputStream os = null;
        try{
        	int len;
        	int bufferSize=InputStreamUtil.getBufferSize(is);
    		byte[] b = new byte[bufferSize];
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			os = response.getOutputStream();
			while ((len = is.read(b)) > 0) {
				os.write(b, 0, len);
			}
			os.flush();
        }catch(Exception e){
        	throw new RuntimeException(e.getMessage(),e);
        }finally{
        	try{
        		if (os != null){
    				os.close();
    				os=null;
    			}
    			if (is != null){
    				is.close();
    				is=null;
    			}
            }catch(Exception e){
            	throw new RuntimeException(e.getMessage(),e);
            }
        }
    }
	
	/**
	 * 是否允许读取
	 * 只允许下载、显示临时目录下的文件
	 * 防止“任意文件读取”漏洞
	 */
	public boolean isAllowRead(String filePath){
		boolean isAllowRead=true;
		String tempDirectory=CacheUtil.getInstance().getStringValue("ConfigCache","common.file.tempDirectory");
		if(filePath.indexOf(tempDirectory)==-1){
			isAllowRead=false;
		}
		return isAllowRead;
	}
	
}
