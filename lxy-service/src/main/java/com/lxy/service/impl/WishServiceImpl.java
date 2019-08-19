package com.lxy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.lxy.dao.WishDao;
import com.lxy.entity.Wish;
import com.lxy.service.WishService;
@Service("WishService")
public class WishServiceImpl implements WishService {

	@Autowired
	WishDao wishDao;
	
	@Override
	@Cacheable(value="wish",key="#state")
	public List<Wish> list(int state) {
		
		return wishDao.list(state);
	}

}
