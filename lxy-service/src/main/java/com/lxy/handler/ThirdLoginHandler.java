package com.lxy.handler;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.lxy.dao.UserDao;
import com.lxy.dto.LoginInfo;
import com.lxy.entity.Header;
import com.lxy.entity.User;
import com.lxy.entity.UserInfo;
import com.lxy.entity.UserThird;
import com.lxy.enums.ResultEnum;
import com.lxy.exception.AuthException;
import com.lxy.service.AuthService;
import com.lxy.service.UserService;
import com.lxy.utils.RandomUtil;
import com.lxy.utils.SnowflakeIdWorkerUtil;
import com.lxy.utils.ValidUtil;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@Component
public class ThirdLoginHandler extends LoginHandler {
	
	@Autowired
	UserDao userDao;
	//使用Mysql数据库的，在链接数据库的时候加上这个参数 rewriteBatchedStatements=true 批量操作
//	@Autowired
//	UserService userService;

	@Override
	public void preHandle(LoginInfo loginInfo, Header header) throws AuthException {
		if (ValidUtil.isNull(loginInfo.getCode())) {
			throw new AuthException(ResultEnum.REQUEST_PARAMTER_MISSING);
		}
	}

	@Override
	public User checkLogin(LoginInfo loginInfo, Header header) throws AuthException {
		System.out.println("code------->" + loginInfo.getCode());
		JSONObject userObj = JSONObject.fromObject(loginInfo.getCode());
		String openId = userObj.getString("openid");
		
		User user = userDao.getUserByOpenId(openId,loginInfo.getType());
		boolean registFlag=true;
		if(user==null){
			//注册
			user=autoRegist(loginInfo,header,userObj);
			registFlag=false;
		}
		
		if(registFlag){//不是刚注册的才去验证
			if(user.getuState()==User.USER_STATUS_DISABLE){
				throw new AuthException(ResultEnum.AUTH_USER_STATE_FORBID);
			}
		}

		return user;
	}
	@Transactional
	private User autoRegist(LoginInfo loginInfo,Header header,JSONObject userObj) {
		if (ValidUtil.isNull(loginInfo.getTel(), loginInfo.getYzm())) {
			throw new AuthException(ResultEnum.REQUEST_PARAMTER_MISSING);
		}
		// 校验验证码
		if (!checkYZM(loginInfo.getTel(), loginInfo.getYzm())) {
			throw new AuthException(ResultEnum.PHONE_VALIDATE_CODE_ERROR);
		}

		String nickName = "", avatar = "",sex = "", unionid = "", province = "", city = "";
		nickName = userObj.getString("nickname");
		if(ValidUtil.isNull(nickName)){
			nickName="Myb_" + RandomUtil.getRandomByLength(8);
		}
		if(!ValidUtil.isNull(nickName)&&!ValidUtil.validContentLength(nickName, 30)){
			nickName=nickName.substring(0,30);				
		}
		String openId = userObj.getString("openid");
		province = userObj.getString("province");
		city = userObj.getString("city");
		switch (loginInfo.getType()) {
		case AuthService.WEIXIN_LOGIN_TYPE:
			sex = userObj.getString("sex");
			avatar = userObj.getString("headimgurl");
			unionid = userObj.getString("unionid");
			break;
		case AuthService.QQ_LOGIN_TYPE:
			try {
				sex = "男".equals(userObj.getString("gender")) ? "1" : "女".equals(userObj.getString("gender")) ? "2" : "0";
			} catch (JSONException e) {
				sex="0";
			}

			avatar = userObj.getString("figureurl");
			break;
		default:
			break;
		}

		// 该用户是否已经注册
		User user = userDao.getUserByMobile(loginInfo.getTel());
		Long uid = SnowflakeIdWorkerUtil.nextId();
		if (user == null) {
			user = new User();
			user.setUid(uid);
			user.setTel(loginInfo.getTel());
			user.setNickName(nickName);
			user.setAvatar(avatar);
			user.setSex(Integer.parseInt(sex));
			user.setCid(header.getClientId());
			user.setInvitationCode(RandomUtil.generateInvicateCodeByUid(uid));
			user.setSalt(RandomUtil.generateSalt(uid));
//			user.setAccount(account);
//			user.setCertificationFlag(certificationFlag);		
//			user.setPUid(pUid);
//			user.setULeve(uLeve);
//			user.setUState(uState);
//			user.setUType(uType);
//			user.setArea(province + "	" + city);
			
			
			int i=userDao.saveUser(user);
			
//			loginUser.setGender(Integer.parseInt(sex));
//			loginUser.setLoginType(loginType);
//			loginUser.setOpenid(MD5Util.encryptString(String.valueOf(uid)));
//			int i = appUserMapper.insertSelective(loginUser);
			
			
			if (i <= 0) {
				throw new AuthException(ResultEnum.DB_INSERT_ERROR);
			}

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
//			userInfo.setPublishCount(publishCount);
//			userInfo.setCommentCount(commentCount);
//			userInfo.setJifenCount(jifenCount);
//			}
			
			i = userDao.saveUserInfo(userInfo);
			if (i <= 0) {
				throw new AuthException(ResultEnum.DB_INSERT_ERROR);
			}
	
		}

		UserThird uThird = new UserThird();
		uThird.setAvatar(avatar);
		uThird.setNickName(nickName);
		uThird.setOid(SnowflakeIdWorkerUtil.nextId());
		uThird.setOpenId(openId);
		uThird.setLoginType(loginInfo.getType());
		uThird.setUid(uid);
		uThird.setUnionId(unionid);
		int i = userDao.saveThird(uThird);
		if (i <= 0) {
			throw new AuthException(ResultEnum.DB_INSERT_ERROR);
		}
	
		return user;
	}



}
