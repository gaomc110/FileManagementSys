package cn.com.sparknet.common.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.sparknet.common.util.ExportExcelUtils;
import cn.com.sparknet.common.util.FileDownloadUtils;

@Service
public class ExportZipOrExcel {
	
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(ExportZipOrExcel.class);
	 /**
     * 将批量文件打包下载成zip
     * @param request
     * @param response
     * @param zipName     下载的zip名
     * @param files       要打包的批量文件
     * @param zipPath     生成的zip路径
     * @throws Exception
     */
    public void downloadZip(HttpServletRequest request, HttpServletResponse response, String zipName, List<File> files, String zipPath)throws Exception {
        File srcfile[] = new File[files.size()];
        File zip = new File(zipPath);
        for (int i = 0; i < files.size(); i++) {
            srcfile[i] = files.get(i);
        }
        //生成.zip文件;
        FileInputStream inStream = null;
        ServletOutputStream os = null;
        try {
            //设置下载zip的头信息
            FileDownloadUtils.setZipDownLoadHeadInfo(response, request, zipName);
            os = response.getOutputStream();
            FileDownloadUtils.ZipFiles(srcfile, zip);
            inStream = new FileInputStream(zip);
            byte[] buf = new byte[4096];
            int readLength;
            while (((readLength = inStream.read(buf)) != -1)) {
                os.write(buf, 0, readLength);
            }
        }  finally {
            if (inStream != null) {
                inStream.close();
            }
            if (os != null) {
                os.flush();
                os.close();
            }
        }
    }
    
    /**
     * 生成excel到指定路径
     * @param wb
     * @param path
     * @throws Exception
     */
    public void generateExcelToPath(SXSSFWorkbook wb, String path) throws Exception {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
            wb.write(fos);
        } finally {
            if (fos != null) {
                fos.flush();
                fos.close();
            }
            if (wb != null) {
                wb.close();
            }
        }
    }
    
    
    
    /**
     * 下载excel
     *
     * @param request
     * @param response
     * @param fileName
     * @param workbook
     * @throws Exception
     * @author 
     * @time 
     */
    private void downloadExcel(HttpServletRequest request, HttpServletResponse response, String fileName, SXSSFWorkbook workbook) {
        //一个流两个头
        //设置下载excel的头信息
        FileDownloadUtils.setExcelHeadInfo(response, request, fileName);

        // 写出文件
        ServletOutputStream os = null;
        try {
            os = response.getOutputStream();
            workbook.write(os);
        } catch (IOException e) {
            logger.error(Thread.currentThread().getStackTrace()[1].getMethodName() + "发生的异常是: ", e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (os != null) {
                    os.flush();
                    os.close();
                }
                if (workbook != null) {
                    workbook.close();
                }
            } catch (Exception e1) {
                logger.error(Thread.currentThread().getStackTrace()[1].getMethodName() + "发生的异常是: ", e1);
                throw new RuntimeException(e1);
            }
        }
    }
}
