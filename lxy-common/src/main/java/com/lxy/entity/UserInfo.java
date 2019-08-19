package com.lxy.entity;

import java.util.Date;

public class UserInfo {

	private Long uid;
	private Long jifenCount;
	private Integer commentCount;
	private Integer publishCount;
	private Integer longitude;
	private Integer latitude;
	private String provinceCode;
	private String cityCode;
	private String areaCode;
	private Date lastTime;
	private String loginIp;
	private Integer osType;
	private String appJx;
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public Long getJifenCount() {
		return jifenCount;
	}
	public void setJifenCount(Long jifenCount) {
		this.jifenCount = jifenCount;
	}
	public Integer getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	public Integer getPublishCount() {
		return publishCount;
	}
	public void setPublishCount(Integer publishCount) {
		this.publishCount = publishCount;
	}
	public Integer getLongitude() {
		return longitude;
	}
	public void setLongitude(Integer longitude) {
		this.longitude = longitude;
	}
	public Integer getLatitude() {
		return latitude;
	}
	public void setLatitude(Integer latitude) {
		this.latitude = latitude;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public Integer getOsType() {
		return osType;
	}
	public void setOsType(Integer osType) {
		this.osType = osType;
	}
	public String getAppJx() {
		return appJx;
	}
	public void setAppJx(String appJx) {
		this.appJx = appJx;
	}
	
}
