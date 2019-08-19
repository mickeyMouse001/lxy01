package com.lxy.entity;

import java.util.Date;

import com.lxy.base.TopicStatus;

public class Topic implements TopicStatus{

	private Long oid;
	private String title;
	private String content;
	private String positiveSideName;
	private String negativeSideName;
	private Integer totalCount;
	private Integer victorySide;
	private Date today;
	private Integer topicState;
	
	public String getPositiveSideName() {
		return positiveSideName;
	}
	public void setPositiveSideName(String positiveSideName) {
		this.positiveSideName = positiveSideName;
	}
	public String getNegativeSideName() {
		return negativeSideName;
	}
	public void setNegativeSideName(String negativeSideName) {
		this.negativeSideName = negativeSideName;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTopicState() {
		return topicState;
	}
	public void setTopicState(Integer topicState) {
		this.topicState = topicState;
	}
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Integer getVictorySide() {
		return victorySide;
	}
	public void setVictorySide(Integer victorySide) {
		this.victorySide = victorySide;
	}
	public Date getToday() {
		return today;
	}
	public void setToday(Date today) {
		this.today = today;
	}
	@Override
	public String toString() {
		return "Topic [oid=" + oid + ", title=" + title + ", content=" + content + ", positiveSideName="
				+ positiveSideName + ", negativeSideName=" + negativeSideName + ", totalCount=" + totalCount
				+ ", victorySide=" + victorySide + ", today=" + today + ", topicState=" + topicState + "]";
	}
	
	
}
