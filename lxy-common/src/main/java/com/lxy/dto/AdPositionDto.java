package com.lxy.dto;

import java.util.List;

import com.lxy.entity.AdPosition;
import com.lxy.entity.Advertisement;

public class AdPositionDto extends AdPosition {

	private List<Advertisement> ads;

	public List<Advertisement> getAds() {
		return ads;
	}

	public void setAds(List<Advertisement> ads) {
		this.ads = ads;
	}

	@Override
	public String toString() {
		return "AdPositionDto [ads=" + ads + ", getOid()=" + getOid() + ", getPageTag()=" + getPageTag()
				+ ", getPosLocation()=" + getPosLocation() + ", getSortVal()=" + getSortVal() + ", getPosWidth()="
				+ getPosWidth() + ", getPosHeight()=" + getPosHeight() + ", getPosState()=" + getPosState()
				+ "]";
	}
	
	
}
