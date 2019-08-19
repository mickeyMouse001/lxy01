package com.lxy.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.lxy.dao.MessageDao;
import com.lxy.entity.Message;
import com.lxy.enums.MessageType;
import com.lxy.service.MessageService;
@Service("MessageService")
public class MessageServiceImpl implements MessageService {

	
	@Autowired
	MessageDao messageDao; 
	
	@Override
	@Cacheable(value="message",key="#messageType.getCacheKey()")
	public List<Message> list(MessageType messageType) {
		
		return messageDao.list(messageType.getType());
	}

}
