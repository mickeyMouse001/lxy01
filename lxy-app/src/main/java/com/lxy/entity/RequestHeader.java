package com.lxy.entity;



import javax.servlet.http.HttpServletRequest;

import com.lxy.util.RequestUtil;

public class RequestHeader extends Header{
	
	public RequestHeader(HttpServletRequest request) {
		this.ip=RequestUtil.getIpAddress(request);
		this.token=request.getHeader("token") == null ? "" : request.getHeader("token");
		this.osType=request.getHeader("osType") == null ? "" : request.getHeader("osType");
		this.verCode=request.getHeader("verCode") == null ? "" : request.getHeader("verCode");
		this.ClientId=request.getHeader("cid") == null ? "" : request.getHeader("cid");
		this.timeStamp=request.getHeader("ts") == null ? "" : request.getHeader("ts");
		this.jx=request.getHeader("jx") == null ? "" : request.getHeader("jx");
		this.longitude=request.getHeader("lon") == null ? "" : request.getHeader("lon");
		this.latitude=request.getHeader("lat") == null ? "" : request.getHeader("lat");
		this.uid=request.getHeader("uid") == null ? "" : request.getHeader("uid");
		this.provinceCode=request.getHeader("p") == null ? "" : request.getHeader("p");
		this.cityCode=request.getHeader("c") == null ? "" : request.getHeader("c");
		this.areaCode=request.getHeader("a") == null ? "" : request.getHeader("a");
		
	}
	
	

}
