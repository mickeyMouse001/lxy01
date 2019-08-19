package com.lxy.config.cli;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Hello world!
 *
 */
@SpringBootApplication
@RestController
public class App 
{
	@Value("${name}")
	public String name;
	
	@Value("${address}")
	public String address;
	
	 @GetMapping("/test")
	 public String test(){
		 
	    return name+"---"+address;
	 }
	
	 
	public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }
}
