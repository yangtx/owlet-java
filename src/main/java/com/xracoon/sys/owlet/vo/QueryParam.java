package com.xracoon.sys.owlet.vo;

public class QueryParam {
	private Long begin;
	private Long length;
	private Boolean hasMore;
	private String filterString;
	private Integer[] tags;
	
	public Long getBegin() {
		return begin;
	}
	public void setBegin(Long begin) {
		this.begin = begin;
	}
	public Long getLength() {
		return length;
	}
	public void setLength(Long length) {
		this.length = length;
	}
	public Boolean getHasMore() {
		return hasMore;
	}
	public void setHasMore(Boolean hasMore) {
		this.hasMore = hasMore;
	}
	public Integer[] getTags() {
		return tags;
	}
	public void setTags(Integer[] tags) {
		this.tags = tags;
	}
	public String getFilterString() {
		return filterString;
	}
	public void setFilterString(String filterString) {
		this.filterString = filterString;
	}
}
