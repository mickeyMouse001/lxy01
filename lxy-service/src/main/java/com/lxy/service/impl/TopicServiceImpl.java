package com.lxy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.lxy.dao.TopicDao;
import com.lxy.entity.Topic;
import com.lxy.service.TopicService;

@Service("TopicService")
public class TopicServiceImpl implements TopicService {

	@Autowired
	TopicDao topicDao;
	
	@Override
	@Cacheable(value="topic",key="#todayStr")
	public Topic query(String todayStr) {
		return topicDao.query(todayStr);
	}

}
