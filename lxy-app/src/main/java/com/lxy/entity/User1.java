package com.lxy.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class User1{
//	@NotNull(message="{account.not.null}")
	@NotNull(message="账号不可以为空")
	private String account;
	@NotNull(message="密码不可以为空")
	@Pattern(regexp="(?=.{8,16})((?=.*[0-9]+)^[A-Za-z]+.*$)",message="密码格式错误(字母开头，含数字，8到16位)")
	private String password;
	
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
	@Override
	public String toString() {
		return "User [account=" + account + ", password=" + password + "]";
	}
	
}
