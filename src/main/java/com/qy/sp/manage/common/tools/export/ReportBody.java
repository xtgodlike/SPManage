package com.qy.sp.manage.common.tools.export;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.qy.sp.manage.common.tools.export.support.ReportDiv;
import com.qy.sp.manage.common.tools.export.support.ReportHeader;

public class ReportBody implements Serializable {

	private static final long serialVersionUID = 1L;

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
