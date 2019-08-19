package com.lxy.exception;

import com.lxy.enums.ResultEnum;

public class BaseException extends RuntimeException {

	private static final long serialVersionUID = -1976461962698621136L;
	private int code;

	public BaseException(String message) {
		super(message);
	}
	public BaseException(ResultEnum enu) {
		super(enu.getMsg());
		this.setCode(enu.getCode());
	}
	
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
