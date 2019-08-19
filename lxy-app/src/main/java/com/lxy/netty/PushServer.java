package com.lxy.netty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.lxy.properties.ServerProperties;
@Component
public class PushServer implements CommandLineRunner{

	@Autowired
	public ServerProperties prop;
	
	@Override
	public void run(String... args) throws Exception {
		
		NettyServerBootstrap.start(prop.getPort());
		
	}

}
