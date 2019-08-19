package com.lxy.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lxy.annotation.LoginAccess;
import com.lxy.dto.JsonResult;
import com.lxy.entity.RequestHeader;
import com.lxy.properties.ServerProperties;
import com.lxy.service.HomeService;

@RestController
@RequestMapping("/home")
public class HomeController {

	@Autowired
	HomeService homeService;
	
	@Autowired
	public ServerProperties prop; 
	
	@LoginAccess
	@GetMapping
	public JsonResult<Object> loadHome(HttpServletRequest request){
		System.out.println(prop.getPort());
		
		Object obj=homeService.loadHome(new RequestHeader(request));
		return new JsonResult<Object>(obj,null);
	}
	
	
	
	
}
