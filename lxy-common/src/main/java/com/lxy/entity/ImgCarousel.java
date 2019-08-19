package com.lxy.entity;

public class ImgCarousel {
	private Long oid;
	//类型：1-图咖，2-分享,3-广告
	private Integer contentType;
	private Long objId;
	private Integer sortVal;
	private String title;
	private String path;
	
	
	@Override
	public String toString() {
		return "ImgCarousel [oid=" + oid + ", contentType=" + contentType + ", objId=" + objId + ", sortVal=" + sortVal
				+ ", title=" + title + ", path=" + path + "]";
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Integer getSortVal() {
		return sortVal;
	}
	public void setSortVal(Integer sortVal) {
		this.sortVal = sortVal;
	}
	public Long getOid() {
		return oid;
	}
	public void setOid(Long oid) {
		this.oid = oid;
	}
	public Integer getContentType() {
		return contentType;
	}
	public void setContentType(Integer contentType) {
		this.contentType = contentType;
	}
	public Long getObjId() {
		return objId;
	}
	public void setObjId(Long objId) {
		this.objId = objId;
	}

	
}
