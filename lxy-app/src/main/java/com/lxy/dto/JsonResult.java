package com.lxy.dto;

import org.apache.commons.lang3.StringUtils;

public class JsonResult <T> {

	private int code;
	private T data;
	private String message;
	static final private int SUCCESS_CODE=0;
	static final private int FAILED_CODE=1;
	static final private String SUCCESS_MSG="SUCCESS";
	static final private String FAILED_MSG="FAILED";
	
	
	public JsonResult() {
		
	}
	public JsonResult(T data,String message){
		this(data,0,message);
	}
	public JsonResult(T data,int code,String message){
		if(data==null){
			this.code=code;
		}else{
			this.code=SUCCESS_CODE;
			this.data=data;
		}
		this.message=StringUtils.isBlank(message)?FAILED_MSG:message;
	}
	public JsonResult(int code,String message){
		this(null,code,message);
	}
	public JsonResult(Throwable e){
		this.code=FAILED_CODE;
		this.data=null;
		this.message=StringUtils.isBlank(e.getMessage())?SUCCESS_MSG:e.getMessage();
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public static int getSuccessCode() {
		return SUCCESS_CODE;
	}
	public static String getSuccessMsg() {
		return SUCCESS_MSG;
	}
	
}
