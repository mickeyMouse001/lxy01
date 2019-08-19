package com.lxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
//@ServletComponentScan//webfilter注解需要
//@EnableEurekaClient
public class App {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(App.class, args);
	}


}
