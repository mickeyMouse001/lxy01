package com.lxy.entity;

import com.lxy.base.UserDefault;
import com.lxy.base.UserStatus;

public class User implements UserStatus, UserDefault{
	
	private Long uid;
	private String cid;
	private String account;
	private String password;
	private String avatar;
	private Integer uType;
	private Integer uLeve;
	private Integer sex;
	private String tel;
	private Integer certificationFlag;
	private String invitationCode;
	private Long pUid;
	private String nickName;
	private Integer uState;
	private String salt;
	

	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
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
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public Integer getuType() {
		return uType;
	}
	public void setuType(Integer uType) {
		this.uType = uType;
	}
	public Integer getuLeve() {
		return uLeve;
	}
	public void setuLeve(Integer uLeve) {
		this.uLeve = uLeve;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Integer getCertificationFlag() {
		return certificationFlag;
	}
	public void setCertificationFlag(Integer certificationFlag) {
		this.certificationFlag = certificationFlag;
	}
	public String getInvitationCode() {
		return invitationCode;
	}
	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}
	public Long getpUid() {
		return pUid;
	}
	public void setpUid(Long pUid) {
		this.pUid = pUid;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Integer getuState() {
		return uState;
	}
	public void setuState(Integer uState) {
		this.uState = uState;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	

}
