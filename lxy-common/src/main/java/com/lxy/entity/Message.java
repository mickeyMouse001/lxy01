package com.lxy.entity;

import java.util.Date;

public class Message{

	private Long oid;
	private String msgBody;
	private Integer pushTag;
	private Integer msgType;
	private Integer msgState;
	private Date insTime;
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	public String getMsgBody() {
		return msgBody;
	}
	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}
	public Integer getPushTag() {
		return pushTag;
	}
	public void setPushTag(Integer pushTag) {
		this.pushTag = pushTag;
	}
	public Integer getMsgType() {
		return msgType;
	}
	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}
	public Integer getMsgState() {
		return msgState;
	}
	public void setMsgState(Integer msgState) {
		this.msgState = msgState;
	}
	public Date getInsTime() {
		return insTime;
	}
	public void setInsTime(Date insTime) {
		this.insTime = insTime;
	}
	
}
