package cn.com.sparknet.common.util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class FileDownloadUtils {
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(FileDownloadUtils.class);

    /**
     * 编译下载的文件名
     * @param filename
     * @param agent
     * @return
     * @throws IOException
     */
    public static String encodeDownloadFilename(String filename, String agent)throws IOException {
        if (agent.contains("Firefox")) { // 火狐浏览器
            filename = "=?UTF-8?B?"
                    + new BASE64Encoder().encode(filename.getBytes("utf-8"))
                    + "?=";
            filename = filename.replaceAll("\r\n", "");
        } else { // IE及其他浏览器
            filename = URLEncoder.encode(filename, "utf-8");
            filename = filename.replace("+"," ");
        }
        return filename;
    }

    /**
     * 创建文件夹;
     * @param path
     */
    public static void createFile(String path) {
        File file = new File(path);
        //判断文件是否存在;
        if (!file.exists()) {
            //创建文件;
            file.mkdirs();
        }
    }

    /**
     * 生成.zip文件;
     * @param path
     * @throws IOException
     */
    public static ZipOutputStream createZipPath(String path) throws IOException{
        ZipOutputStream zipOutputStream = null;
        File file = new File(path+TimeStampUtil.getTimeStamp()+".zip");
        zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
        File[] files = new File(path).listFiles();
        FileInputStream fileInputStream = null;
        byte[] buf = new byte[1024];
        int len = 0;
        if(files!=null && files.length > 0){
            for(File excelFile:files){
                String fileName = excelFile.getName();
                fileInputStream = new FileInputStream(excelFile);
                //放入压缩zip包中;
                zipOutputStream.putNextEntry(new ZipEntry(path + "/"+fileName));
                //读取文件;
                while((len=fileInputStream.read(buf)) >0){
                    zipOutputStream.write(buf, 0, len);
                }
                //关闭;
                zipOutputStream.closeEntry();
                if(fileInputStream != null){
                    fileInputStream.close();
                }
            }
        }

        /*if(zipOutputStream !=null){
            zipOutputStream.close();
        }*/
        return zipOutputStream;
    }


    /**
     * //压缩文件
     * @param srcfile   要压缩的文件数组
     * @param zipfile  生成的zip文件对象
     */
    public static void ZipFiles(java.io.File[] srcfile, File zipfile) throws Exception {
        byte[] buf = new byte[1024];
        FileOutputStream fos = new FileOutputStream(zipfile);
        ZipOutputStream out = new ZipOutputStream(fos);
        for (int i = 0; i < srcfile.length; i++) {
            FileInputStream in = new FileInputStream(srcfile[i]);
            out.putNextEntry(new ZipEntry(srcfile[i].getName()));
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.closeEntry();
            in.close();
        }
        out.close();
        fos.flush();
        fos.close();
    }

    /**
     * 删除文件夹及文件夹下所有文件
     * @param dir
     * @return
     */
    public static boolean deleteDir(File dir) {
        if (dir == null || !dir.exists()){
            return true;
        }
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    /**
     * 生成html
     * @param msg
     * @return
     * @author 
     * @time 
     */
    public static String getErrorHtml(String msg) {
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<div id='errorInfo'> ");
        sb.append("</div>");
        sb.append("<script>alert('"+msg+"')</script>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }


    /**
     * 设置下载excel的响应头信息
     * @param response
     * @param request
     * @param agent
     * @param fileName
     * @throws IOException
     * @author 
     * @time 
     */
    public static void setExcelHeadInfo(HttpServletResponse response, HttpServletRequest request, String fileName)  {
        try {
            // 获取客户端浏览器的类型
            String agent = request.getHeader("User-Agent");
            // 对文件名重新编码
            String encodingFileName = FileDownloadUtils.encodeDownloadFilename(fileName, agent);
            // 告诉客户端允许断点续传多线程连接下载
            response.setHeader("Accept-Ranges", "bytes");
            //文件后缀
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + encodingFileName);
        } catch (IOException e) {
            logger.error(Thread.currentThread().getStackTrace()[1].getMethodName() +"发生的异常是: ",e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 设置下载zip的响应头信息
     * @param response
     * @param fileName 文件名
     * @param request
     * @throws IOException
     * @author 
     * @time 
     */
    public static void setZipDownLoadHeadInfo(HttpServletResponse response, HttpServletRequest request, String fileName) throws IOException {
        // 获取客户端浏览器的类型
        String agent = request.getHeader("User-Agent");
        response.setContentType("application/octet-stream ");
        // 表示不能用浏览器直接打开
        response.setHeader("Connection", "close");
        // 告诉客户端允许断点续传多线程连接下载
        response.setHeader("Accept-Ranges", "bytes");
        // 对文件名重新编码
        String encodingFileName = FileDownloadUtils.encodeDownloadFilename(fileName, agent);
        response.setHeader("Content-Disposition", "attachment; filename=" + encodingFileName);
    }
   
}
