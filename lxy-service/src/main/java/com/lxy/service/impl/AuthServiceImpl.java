package com.lxy.service.impl;

import java.util.Date;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lxy.dao.UserDao;
import com.lxy.dto.LoginInfo;
import com.lxy.dto.LoginUser;
import com.lxy.entity.Header;
import com.lxy.entity.User;
import com.lxy.entity.UserInfo;
import com.lxy.enums.ResultEnum;
import com.lxy.exception.AuthException;
import com.lxy.handler.LoginHandler;
import com.lxy.handler.ThirdLoginHandler;
import com.lxy.service.AuthService;
import com.lxy.service.RedisService;
import com.lxy.utils.CommonUtil;
import com.lxy.utils.MD5Util;
import com.lxy.utils.RandomUtil;
import com.lxy.utils.SnowflakeIdWorkerUtil;
import com.lxy.utils.ValidUtil;

@Service("AuthService")
public class AuthServiceImpl implements AuthService {

	@Autowired
	UserDao userDao;
	
	@Resource(name="defaultLoginHandler")
	LoginHandler defaultLoginHandler;
	
	@Resource(name="mobileLoginHandler")
	LoginHandler mobileLoginHandler;
	
	@Resource(name="thirdLoginHandler")
	LoginHandler thirdLoginHandler;
	
	@Autowired
	RedisService redisService;
	
	@Override
	public LoginUser login(LoginInfo loginInfo,Header reqHeader) {

		Integer type = loginInfo.getType();
		// 验证type
		if (ValidUtil.isNull(type)) {
			throw new AuthException(ResultEnum.AUTH_LOGIN_TYPE_IS_NULL);
		}
		
		//验证是否重复登录
		if(!ValidUtil.isNull(reqHeader.getUid())&&!ValidUtil.isNull(reqHeader.getToken())){
			//TODO 对比redis中的token
			String cacheToken=redisService.getToken(reqHeader.getUid());
			if(reqHeader.getToken().equals(cacheToken)){
				throw new AuthException(ResultEnum.AUTH_LOGIN_DUPLICATE);
			}	
		}
		
		LoginUser loginUser=null;
		switch (type) {// 0-账号密码，1-手机验证码，2-微信，3-qq
		case 0:
			loginUser=defaultLoginHandler.doLogin(loginInfo,reqHeader);
			
			break;
		case 1:
			loginUser=mobileLoginHandler.doLogin(loginInfo,reqHeader);
			break;
		case 2:
			loginUser=thirdLoginHandler.doLogin(loginInfo,reqHeader);
			break;
		case 3:
			loginUser=thirdLoginHandler.doLogin(loginInfo,reqHeader);
			break;
		default:
			throw new AuthException(ResultEnum.AUTH_LOGIN_TYPE_NOT_DEFINED);

		}
		// 登录验证
		// 保存数据到redis
		System.out.println(loginUser.getToken());
		//缓存
		redisService.saveLoginResult2Cache(loginUser);
		return loginUser;
	}


	@Override
	public LoginUser regist(LoginInfo loginInfo, Header header) {
		//验证码是否正确
		//保存用户信息
		User user = new User();
		Long uid = SnowflakeIdWorkerUtil.nextId();
		user.setUid(uid);
		user.setTel(loginInfo.getTel());
		user.setNickName(User.NICK_NAME_PREFIX+RandomUtil.getRandomNumsByLength(8));
		user.setAvatar(User.AVATAR_DEFAULT);
		user.setSex(User.SEX_DEFAULT);
		user.setCid(header.getClientId());
		user.setInvitationCode(RandomUtil.generateInvicateCodeByUid(uid));
		String salt=RandomUtil.generateSalt(uid);
		user.setSalt(salt);
		user.setAccount(loginInfo.getAccount());
		user.setPassword(MD5Util.encryptString(MD5Util.encryptString(loginInfo.getPassword()) + salt));
		if(!ValidUtil.isNull(loginInfo.getInvitationCode())){
			
			Long pUid=userDao.findUidByInvitationCode(loginInfo.getInvitationCode());
			if(!ValidUtil.isNull(pUid)){
				user.setpUid(pUid);
			}
		}
		int i=userDao.saveUser(user);
		if (i <= 0) {
			throw new AuthException(ResultEnum.DB_INSERT_ERROR);
		}
		
		
		//保存用户详情
		UserInfo userInfo = new UserInfo();
		userInfo.setUid(uid);
		userInfo.setAppJx(header.getJx());
		userInfo.setAreaCode(header.getAreaCode());
		userInfo.setCityCode(header.getCityCode());
		userInfo.setProvinceCode(header.getProvinceCode());
		String lat=header.getLatitude();
		if(!ValidUtil.isNull(lat)){
			userInfo.setLatitude(Integer.parseInt(lat));
		}
		String lon=header.getLongitude();
		if(!ValidUtil.isNull(lon)){
			userInfo.setLongitude(Integer.parseInt(lon));
		}
		String osType=header.getOsType();
		if(!ValidUtil.isNull(osType)){
			userInfo.setOsType(Integer.parseInt(osType));
		}
		userInfo.setLoginIp(header.getIp());
		userInfo.setLastTime(new Date());
//		userInfo.setPublishCount(publishCount);
//		userInfo.setCommentCount(commentCount);
//		userInfo.setJifenCount(jifenCount);
//		}
		
		i = userDao.saveUserInfo(userInfo);
		if (i <= 0) {
			throw new AuthException(ResultEnum.DB_INSERT_ERROR);
		}
		
		LoginUser loginUser=new LoginUser();
		loginUser.setUid(uid);
		loginUser.setTs(System.currentTimeMillis());
		loginUser.setToken(CommonUtil.generateToken(header, uid));
		
		redisService.saveLoginResult2Cache(loginUser);
		return loginUser;
	}





}
