package com.lxy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.lxy.dao.AdDao;
import com.lxy.dto.AdPositionDto;
import com.lxy.entity.AdPosition;
import com.lxy.service.AdService;
@Service("AdService")
public class AdServiceImpl implements AdService {

	@Autowired
	AdDao adDao;
	
	@Override
	@Cacheable(value="ad",key="#pageName")
	public List<AdPositionDto> listByPage(String pageName) {
		return adDao.listByPage(pageName);
	}

}
