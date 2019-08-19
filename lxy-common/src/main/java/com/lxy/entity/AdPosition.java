package com.lxy.entity;

import com.lxy.base.AdPositionPage;

public class AdPosition implements AdPositionPage{

	private Long oid;
	private String pageTag;
	private String posLocation;
	private Integer sortVal;
	private Integer posWidth;
	private Integer posHeight;
	private Integer posState;
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	public String getPageTag() {
		return pageTag;
	}
	public void setPageTag(String pageTag) {
		this.pageTag = pageTag;
	}
	public String getPosLocation() {
		return posLocation;
	}
	public void setPosLocation(String posLocation) {
		this.posLocation = posLocation;
	}
	public Integer getSortVal() {
		return sortVal;
	}
	public void setSortVal(Integer sortVal) {
		this.sortVal = sortVal;
	}
	public Integer getPosWidth() {
		return posWidth;
	}
	public void setPosWidth(Integer posWidth) {
		this.posWidth = posWidth;
	}
	public Integer getPosHeight() {
		return posHeight;
	}
	public void setPosHeight(Integer posHeight) {
		this.posHeight = posHeight;
	}
	public Integer getPosState() {
		return posState;
	}
	public void setPosState(Integer posState) {
		this.posState = posState;
	}

	
	
}
