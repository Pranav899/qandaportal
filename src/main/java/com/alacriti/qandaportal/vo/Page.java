package com.alacriti.qandaportal.vo;

public class Page {
	private long uniqueId;
	private long pageNo;
	private long start;
	private long noOfPages;
	private long activePage;
	public long getUniqueId() {
		return uniqueId;
	}
	public long getActivePage() {
		return activePage;
	}
	public void setActivePage(long activePage) {
		this.activePage = activePage;
	}
	public void setUniqueId(long uniqueId) {
		this.uniqueId = uniqueId;
	}
	public long getPageNo() {
		return pageNo;
	}
	public void setPageNo(long pageNo) {
		this.pageNo = pageNo;
	}
	public long getStart() {
		return start;
	}
	public void setStart(long start) {
		this.start = start;
	}
	public long getNoOfPages() {
		return noOfPages;
	}
	public void setNoOfPages(long noOfPages) {
		this.noOfPages = noOfPages;
	}
	public Page(long uniqueId, long pageNo, long start, long noOfPages,long activePage) {
		super();
		this.uniqueId = uniqueId;
		this.pageNo = pageNo;
		this.start = start;
		this.noOfPages = noOfPages;
		this.activePage = activePage;
	}
	public Page(){
		
	}
	
}	
