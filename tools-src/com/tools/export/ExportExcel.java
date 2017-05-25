package com.tools.export;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;

import com.qy.sp.manage.common.tools.NumberFormatTools;
import com.tools.export.support.ReportBody;
import com.tools.export.support.ReportDiv;
import com.tools.export.support.ReportHeader;

public class ExportExcel {
	
	@SuppressWarnings("deprecation")
	public void writer(ReportBody body,OutputStream out) throws IOException{
		HSSFWorkbook workbook = new HSSFWorkbook();// 声明一个工作薄
		HSSFSheet sheet = workbook.createSheet(body.getName());// 生成一个表格
		sheet.setDefaultColumnWidth((short)15);// 设置表格默认列宽度为15个字节
		
		HSSFCellStyle defaultStyle = this.createContentStyle(workbook);// 生成并设置另一个样式
		HSSFCellStyle redStyle = this.createRedStyle(workbook);
		HSSFCellStyle greedStyle = this.createGreenStyle(workbook);
		
		this.setTitle(workbook, sheet, body.getHeaders(), body.getName());// 产生表格标题行
		
		HSSFRow row = null;
		HSSFCell cell = null;
		
		if(null!=body.getDivs()){
			for (ReportDiv rd : body.getDivs()) {
				row = sheet.getRow(rd.getRow());
				if(null==row){
					row = sheet.createRow(rd.getRow());
				}
				cell = row.getCell(rd.getCol());
				if(null==cell){
					cell = row.createCell(rd.getCol());
				}
				
				int background = rd.getBackground();
				HSSFCellStyle style = null;
				if(background==ExportGlobal.Background.RED){
					style = redStyle;
				}else if(background==ExportGlobal.Background.GREEN){
					style = greedStyle;
				}else{
					style = defaultStyle;
				}
				
				//设置cell的数据类型
				if(rd.getType()==ExportGlobal.CellType.STRING){
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				}else if(rd.getType()==ExportGlobal.CellType.DATE){
					//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					//rd.setValue(format.format(rd.getValue()));
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				}else if(rd.getType()==ExportGlobal.CellType.NUMBER){
					//cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				}else if(rd.getType()==ExportGlobal.CellType.CURRENCY){
					cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
					Double val = Double.parseDouble(rd.getValue().toString());
					rd.setValue(NumberFormatTools.round(val,2));
					style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
				}
				cell.setCellStyle(style);
				cell.setCellValue(rd.getValue().toString());
			}
		}
		
		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			workbook.close();
			out.close();
		}
	}
	
	/**
	 * 设置正文样式
	 * 
	 * @param style
	 */
	private HSSFCellStyle createContentStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		
		/*style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);*/
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short) 10);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		style.setFont(font);
		
		return style;
	}
	
	/**
	 * 设置正文样式
	 * 
	 * @param style
	 */
	private HSSFCellStyle createGreenStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFillForegroundColor(HSSFColor.GREEN.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short) 10);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		style.setFont(font);
		
		return style;
	}
	
	/**
	 * 设置正文样式
	 * 
	 * @param style
	 */
	private HSSFCellStyle createRedStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setFillForegroundColor(HSSFColor.RED.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short) 10);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		style.setFont(font);
		
		return style;
	}
	
	/**
	 * 设置标题内容
	 * 
	 * @param workbook
	 * @param headers
	 * @param title
	 * @return
	 */
	private void setTitle(HSSFWorkbook workbook, HSSFSheet sheet,List<ReportHeader> headers, String title) {
		HSSFRow row = null;
		if (null != headers) {
			for (short i = 0; i < headers.size(); i++) {
				ReportHeader header = headers.get(i);
				 //(起始行号，终止行号， 起始列号，终止列号)
				CellRangeAddress region = new CellRangeAddress(header.getStartRow(),header.getEndRow(),header.getStartCol(),header.getEndCol());
				sheet.addMergedRegion(region);
				
				row = sheet.getRow(header.getRow());
				if(null==row){
					row = sheet.createRow(header.getRow());
				}
				HSSFCell cell = row.createCell(header.getCol());
				HSSFCellStyle style = this.createHeaderStyle(workbook,header.getFontColor());
				cell.setCellStyle(style);
				cell.setCellValue(header.getValue().toString());
			}
		}
	}
	
	/**
	 * 设置标题栏的样式
	 * 
	 * @param style
	 */
	private HSSFCellStyle createHeaderStyle(HSSFWorkbook workbook,int color) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); //垂直居中  
		
		HSSFFont font = workbook.createFont();
		if(color==ExportGlobal.FontColor.RED){
			font.setColor(HSSFColor.RED.index);
		}else if(color==ExportGlobal.FontColor.GREEN){
			font.setColor(HSSFColor.GREEN.index);
		}else{
			font.setColor(HSSFColor.BLACK.index);
		}
		font.setFontHeightInPoints((short) 13);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		style.setFont(font);
		return style;
	}
}
