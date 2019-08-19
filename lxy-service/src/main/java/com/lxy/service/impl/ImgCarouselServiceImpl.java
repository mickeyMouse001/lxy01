package com.lxy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lxy.dao.ImgCarouselDao;
import com.lxy.entity.ImgCarousel;
import com.lxy.service.ImgCarouselService;
@Service("ImgCarouselService")
public class ImgCarouselServiceImpl implements ImgCarouselService {

	@Autowired
	ImgCarouselDao imgCarouselDao;
	
	@Override
	public List<ImgCarousel> list() {
		
		
		return imgCarouselDao.list();
	}


}
