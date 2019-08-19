package com.lxy.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lxy.dto.JsonResult;
import com.lxy.entity.User;
import com.lxy.service.TestService;

//import com.lxy.service.TestService;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	TestService testService;
	
	@GetMapping("/1")
	public String test(String id){
		
		
		return testService.test(id);
	}
	@GetMapping("/send")
	public JsonResult<Object> send(String msg){
		
		testService.send("ok");
		return new JsonResult<Object>(null,"ok");
	}
	
	@GetMapping("/redis")
	public String testRedis(String id){
		
		
		return testService.testRedis();
	}
}
