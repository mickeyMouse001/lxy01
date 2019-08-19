package com.lxy.service;

import com.lxy.dto.LoginInfo;
import com.lxy.dto.LoginUser;
import com.lxy.entity.Header;

public interface AuthService {
	// 0-账号密码，1-手机验证码，2-微信，3-qq
	public final static int SIMPLE_LOGIN_TYPE=0;
	public final static int MOBILE_LOGIN_TYPE=1;
	public final static int WEIXIN_LOGIN_TYPE=2;
	public final static int QQ_LOGIN_TYPE=3;
	
	LoginUser login(LoginInfo uLogin,Header reqHeader);

	LoginUser regist(LoginInfo uLogin, Header requestHeader);

}
