package com.lxy.aop;

import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.lxy.entity.Header;
import com.lxy.entity.RequestHeader;
import com.lxy.enums.ResultEnum;
import com.lxy.exception.AuthException;

@Component
@Aspect
public class CheckingLogin {
	
	@Pointcut("@annotation(com.lxy.annotation.LoginAccess)")
	public void pointcut() {}

	@Around("pointcut()")
	public Object around(ProceedingJoinPoint point) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		Header reqHeader=new RequestHeader(request);
		
		String token=reqHeader.getToken();
//    	if(ValidUtil.isNull(token)){
//    		throw new AuthException(ResultEnum.TOKEN_IS_NULL);
//    	}
    	
//    	long uid=Long.valueOf(uidStr);
//    	String redisToken=RedisOPTUtil.getTokenFromRedis(uid);
//    	if(ValidataUtil.isNull(redisToken)){
//    		throw new TokenAuthException(ResultEnum.APP_USER_ACCOUNT_TOKEN_NOT_FOUNT);
//    	}
//		if(!redisToken.equals(token)){
//			throw new TokenAuthException(ResultEnum.USER_ACCOUNT_OTHER_DEVICE_LOGIN);
//		}
//		//给登陆会话续期
//		RedisOPTUtil.setExpireTime(Constant.APP_LOGIN_PREFIX_KEY+uid, Constant.APP_SESSION_EXPIRE_SECONDS);
		//说明用户已经登录了
		Object result=null;
		try {
			result = point.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return result;
	}

}
