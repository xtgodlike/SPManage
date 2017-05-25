package com.qy.sp.manage.common.tools.export;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import com.qy.sp.manage.common.tools.export.support.AExportExcel;

public class ExportExcelTemplet extends AExportExcel {

	/**
	 * 导出文件
	 */
	public void exportExcel(ReportBody body,OutputStream out) {
		try {
			super.writer(body,out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 导入文件
	 * @param in
	 */
	public Map<Integer, String> importExcel(InputStream in){
		Map<Integer, String> map = null;
		try {
			map = super.reader(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	public static void main(String[] args) throws Exception {
		InputStream in = new FileInputStream(new File("D://2015-02-08_投诉详单.xls"));
		AExportExcel ex = new ExportExcelTemplet();
		ex.importExcel(in);
		
		/*//行 列  标题  rowspan colspan
		ReportHeader h1 = new ReportHeader(0,0,"交易日期",1,0);
		ReportHeader h2 = new ReportHeader(0,1,"合作商",1,0);
		
		ReportHeader h3 = new ReportHeader(0,2,"未完成",0,1);
		ReportHeader h4 = new ReportHeader(1,2,"笔数",0,0);
		ReportHeader h5 = new ReportHeader(1,3,"金额",0,0);
		
		ReportHeader h6 = new ReportHeader(0,4,"失败",0,1);
		ReportHeader h7 = new ReportHeader(1,4,"笔数",0,0);
		ReportHeader h8 = new ReportHeader(1,5,"金额",0,0);
		
		ReportHeader h9 = new ReportHeader(0,6,"成功",0,1);
		ReportHeader h10 = new ReportHeader(1,6,"笔数",0,0);
		ReportHeader h11 = new ReportHeader(1,7,"金额",0,0);
		
		ReportHeader h12 = new ReportHeader(0,8,"成功",0,1);
		ReportHeader h13 = new ReportHeader(1,8,"笔数",0,0);
		ReportHeader h14 = new ReportHeader(1,9,"金额",0,0);
		
		List<ReportHeader> headers = new ArrayList<ReportHeader>();
		headers.add(h1);
		headers.add(h2);
		headers.add(h3);
		headers.add(h4);
		headers.add(h5);
		headers.add(h6);
		headers.add(h7);
		headers.add(h8);
		headers.add(h9);
		headers.add(h10);
		headers.add(h11);
		headers.add(h12);
		headers.add(h13);
		headers.add(h14);
		
		List<ReportDiv> dataset = new ArrayList<ReportDiv>();
		ReportDiv r1 = new ReportDiv();
		r1.setRow(2);
		r1.setCol(0);
		r1.setType(ExportGlobal.CellType.DATE);
		r1.setValue("2015-01-04");
		r1.setBackground(ExportGlobal.Background.GREEN);
		
		ReportDiv r2 = new ReportDiv();
		r2.setRow(2);
		r2.setCol(1);
		r2.setType(ExportGlobal.CellType.CURRENCY);
		r2.setValue("100");
		r2.setBackground(ExportGlobal.Background.RED);
		dataset.add(r1);
		dataset.add(r2);
		ReportBody body = new ReportBody();
		body.setDivs(dataset);
		body.setHeaders(headers);
		body.setName("日报表统计");
		
		OutputStream out = new FileOutputStream("d://header.xls");
		ex.export(body, out);*/
		
		/*测试单行标题*/
		/*ReportHeader _h1 = new ReportHeader(0,0,"交易日期",0,0);
		ReportHeader _h2 = new ReportHeader(0,1,"合作商",0,0);
		List<ReportHeader> _headers = new ArrayList<ReportHeader>();
		_headers.add(_h1);
		_headers.add(_h2);
		
		List<ReportDiv> _dataset = new ArrayList<ReportDiv>();
		ReportDiv _r1 = new ReportDiv();
		_r1.setRow(1);
		_r1.setCol(0);
		_r1.setType(ExportGlobal.CellType.DATE);
		_r1.setValue("2015-01-04");
		_r1.setBackground(ExportGlobal.Background.GREEN);
		ReportDiv _r2 = new ReportDiv();
		_r2.setRow(1);
		_r2.setCol(1);
		_r2.setType(ExportGlobal.CellType.STRING);
		_r2.setValue("千雅文化");
		_dataset.add(_r1);
		_dataset.add(_r2);
		
		ReportBody _body = new ReportBody();
		_body.setHeaders(_headers);
		_body.setName("销售日报");
		_body.setDivs(_dataset);
		
		OutputStream _out = new FileOutputStream("d://header2.xls");
		ex.export(_body, _out);*/
	}
}
