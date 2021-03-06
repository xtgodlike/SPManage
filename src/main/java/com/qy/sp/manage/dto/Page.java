package com.qy.sp.manage.dto;

public class Page {

	private int pageNumber; // 当前页面
	private int pageAllCount; // 总记录数
	private int pageCount; // 总页数
	private int pageSize = 10;	// 每页记录数

	private int startItems; // 开始记录数

	public int getStartItems() {
		startItems = (pageNumber-1)*pageSize;		
		return startItems;
	}

	public void setStartItems(int startItems) {
		this.startItems = startItems;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageAllCount() {
		return pageAllCount;
	}

	public void setPageAllCount(int pageAllCount) {
		this.pageAllCount = pageAllCount;
	}

	public int getPageCount() {
		if (this.pageAllCount % pageSize == 0) {
			pageCount = this.pageAllCount / pageSize;
		} else {
			pageCount = this.pageAllCount / pageSize + 1;
		}
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}
	
}
