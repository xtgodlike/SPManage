package com.tools.export;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tools.export.support.ReportDiv;
import com.tools.export.support.ReportHeader;

public class ExportBody implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8891992843139957777L;
	
	private String name;
	private List<ReportHeader> headers = new ArrayList<ReportHeader>(); // 报表头
	private List<ReportDiv> divs = new ArrayList<ReportDiv>();// 内容

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ReportHeader> getHeaders() {
		return headers;
	}

	public void setHeaders(List<ReportHeader> headers) {
		this.headers = headers;
	}

	public List<ReportDiv> getDivs() {
		return divs;
	}

	public void setDivs(List<ReportDiv> divs) {
		this.divs = divs;
	}
}
