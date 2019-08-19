package com.lxy.entity;

import java.util.Date;

public class Advertisement {

	private Long oid;
	private Long positionId;
	private String adName;
	private String adCover;
	private String adUrl;
	private String sortVal;
	private Date startTime;
	private Date endTime;
	private Integer delTag;
	private Date insTime;
	
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	public Long getPositionId() {
		return positionId;
	}
	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}
	public String getAdName() {
		return adName;
	}
	public void setAdName(String adName) {
		this.adName = adName;
	}
	public String getAdCover() {
		return adCover;
	}
	public void setAdCover(String adCover) {
		this.adCover = adCover;
	}
	public String getAdUrl() {
		return adUrl;
	}
	public void setAdUrl(String adUrl) {
		this.adUrl = adUrl;
	}
	public String getSortVal() {
		return sortVal;
	}
	public void setSortVal(String sortVal) {
		this.sortVal = sortVal;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Integer getDelTag() {
		return delTag;
	}
	public void setDelTag(Integer delTag) {
		this.delTag = delTag;
	}
	public Date getInsTime() {
		return insTime;
	}
	public void setInsTime(Date insTime) {
		this.insTime = insTime;
	}
	@Override
	public String toString() {
		return "Advertisement [oid=" + oid + ", positionId=" + positionId + ", adName=" + adName + ", adCover="
				+ adCover + ", adUrl=" + adUrl + ", sortVal=" + sortVal + ", startTime=" + startTime + ", endTime="
				+ endTime + ", delTag=" + delTag + ", insTime=" + insTime + "]";
	}
	
}
