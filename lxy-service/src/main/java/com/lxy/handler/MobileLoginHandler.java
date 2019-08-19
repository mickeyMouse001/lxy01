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
import com.lxy.utils.ValidUtil;
@Component
public class MobileLoginHandler extends LoginHandler{

	@Autowired
	UserDao userDao;
	
	@Override
	public void preHandle(LoginInfo loginInfo,Header header) throws AuthException{
		if (ValidUtil.isNull(loginInfo.getTel(), loginInfo.getYzm())) {
			throw new AuthException(ResultEnum.REQUEST_PARAMTER_MISSING);
		}

	}

	@Override
	public User checkLogin(LoginInfo loginInfo, Header header) throws AuthException {

		User user = userDao.getUserByMobile(loginInfo.getTel());
		boolean registFlag=true;
		if (user == null) {
			//注册，
			
			registFlag=false;
		}
		if(registFlag){//不是刚注册的才去验证
			if(user.getuState()==User.USER_STATUS_DISABLE){
				throw new AuthException(ResultEnum.AUTH_USER_STATE_FORBID);
			}
			//验证是否重复登录
			if(!ValidUtil.isNull(header.getUid())&&!ValidUtil.isNull(header.getToken())){
				//TODO 对比redis中的token
			}
		}
		// 校验验证码
		if (!checkYZM(loginInfo.getTel(), loginInfo.getYzm())) {
			throw new AuthException(ResultEnum.PHONE_VALIDATE_CODE_ERROR);
		}

		return user;
	}







	

}
