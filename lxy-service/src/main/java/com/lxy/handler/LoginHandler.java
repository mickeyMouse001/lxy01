package com.lxy.handler;

import org.springframework.beans.factory.annotation.Autowired;

import com.lxy.dto.LoginInfo;
import com.lxy.dto.LoginUser;
import com.lxy.entity.Header;
import com.lxy.entity.User;
import com.lxy.exception.AuthException;
import com.lxy.service.RedisService;
import com.lxy.utils.CommonUtil;
import com.lxy.utils.MD5Util;

public abstract class LoginHandler {


	
	public LoginUser doLogin(LoginInfo loginInfo,Header header){
		LoginUser loginUser=null;
		preHandle(loginInfo,header);
		loginUser=handle(loginInfo,header);
		
		return loginUser;
	}
	
	protected boolean checkYZM(String tel, String yzm) {
//		String redisKey = Constant.YZM_PREFIX_KEY + mobile;
//		String redisYZM = RedisOPTUtil.getValue(redisKey);
//		if (StringUtils.isBlank(redisYZM) || !redisYZM.equals(yzm)) {
//			return false;
//		}
		return true;
	}
	//处理业务
	public  LoginUser handle(LoginInfo loginInfo,Header header)throws AuthException{

		User user=checkLogin(loginInfo,header);
		long uid = user.getUid();
		// 判断是否为相同的Client-id，如果有不同,通知用户
		String clientId = header.getClientId();
		//TODO 对比redis中的clientId
		
		// 生成token,返回数据
		String loginToken = CommonUtil.generateToken(header, uid);
		//TODO 保存token到redis中
		
		LoginUser loginUser=new LoginUser();
		loginUser.setToken(loginToken);
		loginUser.setUid(uid);
		loginUser.setTs(System.currentTimeMillis());
		
		
		return loginUser;
	}


	
	
	public abstract User checkLogin(LoginInfo loginInfo, Header header)throws AuthException;



	/**
	 * 参验证
	 * @param loginInfo
	 * @param header
	 * @return 
	 * @throws AuthException
	 */
	public abstract void preHandle (LoginInfo loginInfo,Header header)throws AuthException;


}
