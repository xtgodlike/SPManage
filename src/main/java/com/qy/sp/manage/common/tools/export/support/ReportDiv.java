package com.qy.sp.manage.common.tools.export.support;

import java.io.Serializable;

public class ReportDiv extends ExportBasics implements Serializable {

	private static final long serialVersionUID = 1L;

	private int type; // 字段类型
	private int background; //背景色
	
	public int getBackground() {
		return background;
	}

	public void setBackground(int background) {
		this.background = background;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
