package com.lxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class RegistServer {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(RegistServer.class, args);
	}

}
