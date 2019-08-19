package com.lxy.jms;


import javax.annotation.Resource;
import javax.jms.Destination;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class Jmsproducer {

	@Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;


    
	public void send(){
		Destination destination=new ActiveMQQueue("lxy.in");
		jmsMessagingTemplate.convertAndSend(destination, "hello, lxy");
	}
	
	@JmsListener(destination="lxy.in")
	@SendTo("lxy.out")
	public String exchang(String msg){
		System.out.println("收到需要转发的消息："+msg);
		return "return msg:"+msg;
	}
	
	@JmsListener(destination="lxy.out")
	public void rec(String msg){
		System.out.println("收到的消息："+msg);
	}
	
}
