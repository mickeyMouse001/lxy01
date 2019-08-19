package com.lxy.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


public class LoginInfo {
	//账号
	@NotNull(message="账号不可以为空")
	private String account;
	//密码
	@NotNull(message="密码不可以为空")
	@Pattern(regexp="(?=.{8,16})((?=.*[0-9]+)^[A-Za-z]+.*$)",message="密码格式错误(字母开头，含数字，8到16位)")
	private String password;
	//登录类型:0-账号密码，1-手机验证码，2-微信，3-qq
	private Integer type;
	//手机号
	@Pattern(regexp="^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$",message="手机号格式错误")
	@NotNull(message="手机号不可以为空")
	private String tel;
	//验证码
	@NotNull(message="验证码不可以为空")
	private String yzm;
	//第三方登录码
	private String code;
	
	private String invitationCode;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getYzm() {
		return yzm;
	}
	public void setYzm(String yzm) {
		this.yzm = yzm;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getInvitationCode() {
		return invitationCode;
	}
	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}
	
	

}
