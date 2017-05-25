package com.tools.export.support;

import java.io.Serializable;

public class ExportBasics implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3588285624939356145L;

	private int row; // 从0开始 行
	private int col; // 从0开始 列
	private Object value; // 名称

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}
 
	public void setCol(int col) {
		this.col = col;
	}

	public Object getValue() {
		if(null != value){
			return value;
		}else{
			return "";
		}
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
