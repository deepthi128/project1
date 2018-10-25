package com.ipivot.matka.model;

import java.util.List;

public class SearchResponse {

	private List<VacationPackage> packages;
	private Integer totalCount;
	private Integer pageNumber;
	private Integer perPageCount;
	private Integer numOfPages;

	public List<VacationPackage> getPackages() {
		return packages;
	}

	public void setPackages(List<VacationPackage> packages) {
		this.packages = packages;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
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

	public Integer getNumOfPages() {
		return numOfPages;
	}

	public void setNumOfPages(Integer numOfPages) {
		this.numOfPages = numOfPages;
	}

	@Override
	public String toString() {
		return "SearchResponse [packages=" + packages + ", totalCount=" + totalCount + ", pageNumber=" + pageNumber
				+ ", perPageCount=" + perPageCount + ", numOfPages=" + numOfPages + "]";
	}
}
