package com.lxy.entity;

import java.util.Date;

import com.lxy.base.WishState;

public class Wish implements WishState{

	private Long wishId;
	private String wishName;
	private Integer wishValue;
	private Long productId;
	private Date startTime;
	private Date endTime;
	private String productCover;
	private String productInfoUrl;
	public Long getWishId() {
		return wishId;
	}
	public void setWishId(Long wishId) {
		this.wishId = wishId;
	}
	public String getWishName() {
		return wishName;
	}
	public void setWishName(String wishName) {
		this.wishName = wishName;
	}
	public Integer getWishValue() {
		return wishValue;
	}
	public void setWishValue(Integer wishValue) {
		this.wishValue = wishValue;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
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
	public String getProductCover() {
		return productCover;
	}
	public void setProductCover(String productCover) {
		this.productCover = productCover;
	}
	public String getProductInfoUrl() {
		return productInfoUrl;
	}
	public void setProductInfoUrl(String productInfoUrl) {
		this.productInfoUrl = productInfoUrl;
	}
	
	
}
