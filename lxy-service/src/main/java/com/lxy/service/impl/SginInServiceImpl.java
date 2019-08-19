package com.lxy.service.impl;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.lxy.dao.SignInDao;
import com.lxy.service.SginInService;
@Service("SginInService")
public class SginInServiceImpl implements SginInService {

	@Autowired
	SignInDao signInDao;
	
	@Override
	@Cacheable(value="signIn",key="#uid")
	public Integer isSignIn(long uid, String today) {
		
		return signInDao.isSignIn(uid,today);
	}

}
