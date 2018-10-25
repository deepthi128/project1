package com.ipivot.matka.model;

import java.util.Date;

public class SearchRequest {

	private String name;
	private Date fromDate;
	private Date toDate;
	private Integer pageNumber;
	private Integer perPageCount;
	private String origin;
	
	public SearchRequest() {
		
	}
	
	public SearchRequest(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPerPageCount() {
		return perPageCount;
	}

	public void setPerPageCount(Integer perPageCount) {
		this.perPageCount = perPageCount;
	}

	@Override
	public String toString() {
		return "SearchRequest [name=" + name + ", fromDate=" + fromDate + ", toDate=" + toDate + ", pageNumber="
				+ pageNumber + ", perPageCount=" + perPageCount + ", origin=" + origin + "]";
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

}
