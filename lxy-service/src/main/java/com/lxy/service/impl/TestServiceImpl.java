package com.lxy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lxy.constant.NettyChannel;
import com.lxy.dao.TestDao;
import com.lxy.jms.Jmsproducer;
import com.lxy.service.TestService;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledDirectByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
@Service
public class TestServiceImpl implements TestService{

	@Autowired
	TestDao dao;
	
	@Autowired
	JdbcTemplate tem;
	
	@Autowired
	Jmsproducer jmsproducer;
	
	@Override
	@Cacheable(value = "emp" ,key = "'nihaoya'+#p0")
	public String test(String id) {
		System.out.println(tem);
		return dao.test();
	}

	@Override
	public String testRedis() {
//		System.out.println(redis);
//		redis.opsForValue().set("demo", "ni hao ya");
		return null;
	}

	@Override
	public void send(String msg) {
		
		ChannelHandlerContext ch=(ChannelHandlerContext)NettyChannel.channelInfo.get("1");
		ByteBuf bb =Unpooled.buffer(10);
		bb.writeBytes(msg.getBytes());
//		ChannelFuture f=ch.writeAndFlush(bb);
//		
//		f.addListener(new ChannelFutureListener(){
//
//			@Override
//			public void operationComplete(ChannelFuture future) throws Exception {
//				
//				if(!future.isSuccess()){
//					Throwable e=future.cause();
//					e.printStackTrace();
//				}
//			}
//			
//		});
		
		
		//发送消息到MQ
		jmsproducer.send();
		
	}

}
