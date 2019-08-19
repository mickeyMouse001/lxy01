package com.lxy.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lxy.dao.UserDao;
import com.lxy.dto.LoginInfo;
import com.lxy.dto.LoginUser;
import com.lxy.entity.Header;
import com.lxy.entity.User;
import com.lxy.enums.ResultEnum;
import com.lxy.exception.AuthException;
import com.lxy.service.RedisService;
import com.lxy.utils.MD5Util;
import com.lxy.utils.ValidUtil;
@Component
public class DefaultLoginHandler extends LoginHandler {

	@Autowired
	UserDao userDao;
	

	@Override
	public void preHandle(LoginInfo loginInfo,Header header) throws AuthException{
		if (ValidUtil.isNull(loginInfo.getAccount(),loginInfo.getPassword())) {
			throw new AuthException(ResultEnum.REQUEST_PARAMTER_MISSING);
		}

	}

	@Override
	public User checkLogin(LoginInfo loginInfo, Header header) throws AuthException {
		User user = userDao.getUserByAccount(loginInfo.getAccount());
		if (user == null) {
			throw new AuthException(ResultEnum.AUTH_USER_OR_PWD_ERROR);
		}
		if(user.getuState()==User.USER_STATUS_DISABLE){
			throw new AuthException(ResultEnum.AUTH_USER_STATE_FORBID);
		}

		// 验证登陆密码
		String md5Pwd = MD5Util.encryptString(MD5Util.encryptString(loginInfo.getPassword()) + user.getSalt());
		if (!md5Pwd.equals(user.getPassword())) {
			throw new AuthException(ResultEnum.AUTH_USER_OR_PWD_ERROR);
		}
		
		return user;
	}




	
}
