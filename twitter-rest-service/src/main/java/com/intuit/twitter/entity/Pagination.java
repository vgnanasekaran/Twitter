package com.intuit.twitter.entity;

public class Pagination {
	int offset;
	int limit;
	int total;
	
	public Pagination(int offset, int limit, int total) {
		this.offset = offset;
		this.limit = limit;
		this.total = total;	
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
}

