package com.lxy.enums;


public enum ResultEnum {
	//系统容错提醒信息
	SYS_CONNECTION_BUSY(1,"01-系统繁忙，稍后再试"),
	OTHER_ERROR(1000,"系统异常"), 
	REQUEST_PARAMTER_MISSING(2,"请求参数不完整"),
	
	//用户认证
	AUTH_LOGIN_TYPE_IS_NULL(40001,"登录类型不可以为空"),
	TOKEN_IS_NULL(40002,"token不可以为空"), 
	AUTH_LOGIN_TYPE_NOT_DEFINED(40003,"未定义的登录类型"),
	AUTH_USER_OR_PWD_ERROR(40004,"账户或密码错误，请重新输入"),
	AUTH_USER_STATE_FORBID(40005,"该账号被禁用，请联系平台服务"), 
	PHONE_VALIDATE_CODE_ERROR(40006,"验证码错误，请重新输入"), 
	AUTH_LOGIN_INFO_IS_NULL(40007,"用户名和密码不可以为空"), 
	AUTH_LOGIN_DUPLICATE(40008,"你已登录，无需重复操作"),
	//数据库操作
	DB_INSERT_ERROR(50001,"数据库操作：新增数据失败"), 
	;
	private Integer code;
	private String msg;
	
	
	private ResultEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public Integer getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}

	public static String getResponseMsg(String code) {
		
        for (ResultEnum wrapperEnumError : ResultEnum.values()) {
            if (Integer.valueOf(code).equals(wrapperEnumError.getCode())) {
                return wrapperEnumError.getMsg();
            }
        }
        return OTHER_ERROR.getMsg();
    }

}
