package com.lxy.dto;


/**
 * 登录后返回的用户信息
 * @author Administrator
 *
 */

public class LoginUser {

	private String token;
	private Long uid;
	//系统时间戳
	private Long ts;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public Long getTs() {
		return ts;
	}
	public void setTs(Long ts) {
		this.ts = ts;
	}
	
}
