package com.lxy.service;

import com.lxy.dto.LoginUser;

public interface RedisService {

	/**
	 * 保存用户登录信息
	 * @param loginUser
	 */
	void saveLoginResult2Cache(LoginUser loginUser);

	/**
	 * 获取token
	 * @param uid
	 * @return
	 */
	String getToken(String uid);

}
