package com.lxy.service;

import java.util.List;
import com.lxy.entity.Message;
import com.lxy.enums.MessageType;

public interface MessageService {

	List<Message> list(MessageType messageType);

}
