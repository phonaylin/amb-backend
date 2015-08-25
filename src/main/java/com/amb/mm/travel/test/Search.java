package com.amb.mm.travel.test;

import java.util.Date;

public class Search {
	Long fromId;
	Long toId;
	Date departureDate;
	String searchType;
	
	// getters setters
	public Long getFromId() {
		return fromId;
	}
	public Long getToId() {
		return toId;
	}
	public Date getDepartureDate() {
		return departureDate;
	}
	public void setFromId(Long fromId) {
		this.fromId = fromId;
	}
	public void setToId(Long toId) {
		this.toId = toId;
	}
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	
	
}
