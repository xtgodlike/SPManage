package com.qy.sp.manage.common.tools.export.support;

import java.io.Serializable;

public class ReportHeader extends ExportBasics implements Serializable {

	private static final long serialVersionUID = 1L;

	private int rowspan	= 0; // 合并行
	private int colspan	= 0; // 列合并
	private int fontColor= 0; // 字体颜色

	public int getFontColor() {
		return fontColor;
	}

	public void setFontColor(int fontColor) {
		this.fontColor = fontColor;
	}

	public short getStartRow() {// 起始行号
		return (short) this.getRow();
	}

	public short getEndRow() {// 终止行号
		return (short) (getRow() + getRowspan());
	}

	public short getStartCol() {// 起始列号
		return (short) getCol();
	}

	public short getEndCol() {// 终止列号
		return (short) (getCol() + getColspan());
	}

	public int getRowspan() {
		return rowspan;
	}

	public void setRowspan(int rowspan) {
		this.rowspan = rowspan;
	}

	public int getColspan() {
		return colspan;
	}

	public void setColspan(int colspan) {
		this.colspan = colspan;
	}
	
	public ReportHeader(){
		super();
	}
	
	public ReportHeader(int row, int col, String value, int rowspan, int colspan,int fontColor) {
		super();
		super.setRow(row);
		super.setCol(col);
		super.setValue(value);
		this.rowspan = rowspan;
		this.colspan = colspan;
		this.fontColor = fontColor;
	}
}
