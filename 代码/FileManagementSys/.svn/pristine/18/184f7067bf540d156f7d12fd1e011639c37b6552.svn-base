package cn.com.sparknet.common.util;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;


public class ExportExcelUtils {

    public int          pageIndex  = 0;

    public int          rowNumOne  = 0;

    public int          rowNumSenc = 0;

    public int          rowNumThe  = 0;

    Map<String, Object> map        = null;


    /**
     * 普通使用，页面导出
     * @param list
     * @param excelHeader
     * @param dataIndex
     * @param SheetName
     * @return
     */
    public HSSFWorkbook export(List<Map<String, Object>> list, String[] excelHeader, String[] dataIndex, String SheetName) {
        //        String[] excelHeader = { "Sno", "Name", "Age"};    
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(SheetName);
        sheet.setDefaultColumnWidth(16);
        HSSFRow row = sheet.createRow((int)0);
        HSSFCellStyle style = this.titleStyleOne(wb);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        for (int i = 0; i < excelHeader.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(excelHeader[i]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i);
        }
        sheet.setColumnWidth(0, 20 * 256);
        sheet.setColumnWidth(1, 20 * 256);
        sheet.setColumnWidth(2, 20 * 256);
        sheet.setColumnWidth(3, 20 * 256);
        sheet.setColumnWidth(4, 20 * 256);
        sheet.setColumnWidth(5, 20 * 256);
        sheet.setColumnWidth(6, 20 * 256);
        sheet.setColumnWidth(7, 20 * 256);
        sheet.setColumnWidth(8, 20 * 256);
        sheet.setColumnWidth(9, 20 * 256);
        sheet.setColumnWidth(10, 20 * 256);
        sheet.setColumnWidth(11, 20 * 256);
        sheet.setColumnWidth(12, 20 * 256);
        sheet.setColumnWidth(13, 20 * 256);
        sheet.setColumnWidth(14, 20 * 256);
        sheet.setColumnWidth(15, 20 * 256);
        sheet.setColumnWidth(16, 20 * 256);
        sheet.setColumnWidth(17, 20 * 256);
        sheet.setColumnWidth(18, 20 * 256);
        sheet.setColumnWidth(19, 20 * 256);
        sheet.setColumnWidth(20, 20 * 256);
        sheet.setColumnWidth(21, 20 * 256);
        sheet.setColumnWidth(22, 20 * 256);

        // 生成一个样式
        HSSFCellStyle styleCont = wb.createCellStyle();
        styleCont.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        // 设置这些样式
        for (int z = 0; z < list.size(); z++) {
            row = sheet.createRow(z + 1);
            Map student = list.get(z);
            for (int j = 0; j < dataIndex.length; j++) {
                HSSFCell cell = row.createCell(j);
                cell.setCellValue(StringUtil.nullToEmpty(student.get(dataIndex[j])));
                if (j > 0) {
                    cell.setCellStyle(styleCont);
                }
            }
        }
        return wb;
    }
    /**
     * 普通使用，页面导出(多个sheet页，Excel2003的最大行是65536行,
     * 从Excel2007开始最大行是1048576。超过最大行,则超出了Excel的设计,则无法继续增加行数,
     * @param list
     * @param excelHeader
     * @param dataIndex
     * @param SheetName
     * @return
     */
    public HSSFWorkbook exportSheets(HSSFWorkbook wb,List<Map<String, Object>> list, String[] excelHeader, String[] dataIndex, String SheetName) {
    	//        String[] excelHeader = { "Sno", "Name", "Age"};    
    	HSSFSheet sheet = wb.createSheet(SheetName);
    	sheet.setDefaultColumnWidth(16);
    	HSSFRow row = sheet.createRow((int)0);
    	HSSFCellStyle style = this.titleStyleOne(wb);
    	style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    	for (int i = 0; i < excelHeader.length; i++) {
    		HSSFCell cell = row.createCell(i);
    		cell.setCellValue(excelHeader[i]);
    		cell.setCellStyle(style);
    		sheet.autoSizeColumn(i);
    	}
    	sheet.setColumnWidth(0, 20 * 256);
    	sheet.setColumnWidth(1, 20 * 256);
    	sheet.setColumnWidth(2, 20 * 256);
    	sheet.setColumnWidth(3, 20 * 256);
    	sheet.setColumnWidth(4, 20 * 256);
    	sheet.setColumnWidth(5, 20 * 256);
    	sheet.setColumnWidth(6, 20 * 256);
    	sheet.setColumnWidth(7, 20 * 256);
    	sheet.setColumnWidth(8, 20 * 256);
    	sheet.setColumnWidth(9, 20 * 256);
    	sheet.setColumnWidth(10, 20 * 256);
    	sheet.setColumnWidth(11, 20 * 256);
    	sheet.setColumnWidth(12, 20 * 256);
    	sheet.setColumnWidth(13, 20 * 256);
    	sheet.setColumnWidth(14, 20 * 256);
    	sheet.setColumnWidth(15, 20 * 256);
    	sheet.setColumnWidth(16, 20 * 256);
    	sheet.setColumnWidth(17, 20 * 256);
    	sheet.setColumnWidth(18, 20 * 256);
    	sheet.setColumnWidth(19, 20 * 256);
    	sheet.setColumnWidth(20, 20 * 256);
    	sheet.setColumnWidth(21, 20 * 256);
    	sheet.setColumnWidth(22, 20 * 256);
    	
    	// 生成一个样式
    	HSSFCellStyle styleCont = wb.createCellStyle();
    	styleCont.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
    	// 设置这些样式
    	for (int z = 0; z < list.size(); z++) {
    		row = sheet.createRow(z + 1);
    		Map student = list.get(z);
    		for (int j = 0; j < dataIndex.length; j++) {
    			HSSFCell cell = row.createCell(j);
    			cell.setCellValue(StringUtil.nullToEmpty(student.get(dataIndex[j])));
    			if (j > 0) {
    				cell.setCellStyle(styleCont);
    			}
    		}
    	}
    	return wb;
    }


    /**
     * 主信息title样式 
     * @param workbook
     * @return
     */
    public HSSFCellStyle titleStyleOne(HSSFWorkbook workbook) {
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short)12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 指定当单元格内容显示不下时自动换行
        style.setWrapText(true);
        return style;
    }

    /**
	 * 根据数据总量和每页的数据量得到分页查询页数
	 * @param totalSize
	 * @return
	 */
    public static int toPageNum(int totalSize, int pageSize){
    	int pageNum = totalSize / pageSize;
		if(totalSize%pageSize != 0){
			pageNum += 1;
		}
    	return pageNum;
    }
    
    /**
     * 大数据量使用，页面导出(多个sheet页)从Excel2007开始最大行是1048576。超过最大行,则超出了Excel的设计,则无法继续增加行数
     * @param wb
     * @param list
     * @param excelHeader
     * @param dataIndex
     * @param SheetName
     * @return
     */
    public SXSSFWorkbook exportSheetsBySXSSF(SXSSFWorkbook wb,List<Map<String, Object>> list, String[] excelHeader, String[] dataIndex, String SheetName) {
    	Sheet sheet = wb.createSheet(SheetName);
    	sheet.setDefaultColumnWidth(16);
    	Row row = sheet.createRow((int)0);
    	for (int i = 0; i < excelHeader.length; i++) {
    		Cell cell = row.createCell(i);
    		cell.setCellType(XSSFCell.CELL_TYPE_STRING);	//文本格式
    		sheet.setColumnWidth(i, 20 * 256); 				//设置单元格宽度  
    		cell.setCellValue(excelHeader[i]);
    		
    	}
    	
    	// 设置这些样式
    	for (int k = 0; k < list.size(); k++) {
    		row = sheet.createRow(k + 1);
    		Map<String, Object> student = list.get(k);
    		for (int j = 0; j < dataIndex.length; j++) {
    			Cell cell = row.createCell(j);  
    			cell.setCellValue(StringUtil.nullToEmpty(student.get(dataIndex[j])));
    			if (j > 0) {
    				cell.setCellType(XSSFCell.CELL_TYPE_STRING);//文本格式
    			}
    		}
    	}
    	return wb;
    }
}
