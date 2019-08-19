package com.lxy.exception;

import com.lxy.enums.ResultEnum;

public class AuthException extends BaseException {
	private static final long serialVersionUID = -3693402005570866244L;

	public AuthException(ResultEnum enu) {
		super(enu);
		
	}
	public AuthException(String message) {
		super(message);
	}

	
}
