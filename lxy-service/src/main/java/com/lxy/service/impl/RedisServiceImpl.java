package com.lxy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.lxy.dto.LoginUser;
import com.lxy.service.RedisService;
import com.lxy.utils.CommonUtil;
@Service("RedisService")
public class RedisServiceImpl implements RedisService {

	@Autowired
	RedisTemplate<String, Object> redis;
	
	@Override
	public void saveLoginResult2Cache(LoginUser loginUser) {
		
		redis.opsForHash().putAll("user.login:"+loginUser.getUid(), CommonUtil.beanToMap(loginUser));
		
	}

	@Override
	public String getToken(String uid) {
		
		return redis.opsForHash().get("user.login:"+uid, "token").toString();
	}

}
