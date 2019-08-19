package com.lxy.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lxy.dto.JsonResult;
import com.lxy.dto.LoginInfo;
import com.lxy.dto.LoginUser;
import com.lxy.entity.RequestHeader;
import com.lxy.entity.User;
import com.lxy.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Resource(name="AuthService")
	AuthService authService;
	
	@PostMapping("/2")
	public JsonResult<Object> test(@Valid User user){
		
		return new JsonResult<Object>(user, null);
	}

	@PostMapping("/login")
	public JsonResult<Object> login(LoginInfo uLogin,HttpServletRequest req){
		LoginUser data=authService.login(uLogin,new RequestHeader(req));
		return new JsonResult<Object>(data,null);
	}
	@PostMapping("/regist")
	public JsonResult<Object> regist(@Valid LoginInfo uLogin,HttpServletRequest req){
		LoginUser data=authService.regist(uLogin,new RequestHeader(req));
		return new JsonResult<Object>(data,"success");
	}
}
