package com.lxy.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lxy.dao.AdDao;
import com.lxy.dao.ImageCafeDao;
import com.lxy.dao.ImgCarouselDao;
import com.lxy.dao.WishDao;
import com.lxy.dto.AdPositionDto;
import com.lxy.entity.AdPosition;
import com.lxy.entity.Header;
import com.lxy.entity.ImgCarousel;
import com.lxy.entity.Message;
import com.lxy.entity.Topic;
import com.lxy.entity.Wish;
import com.lxy.enums.MessageType;
import com.lxy.service.AdService;
import com.lxy.service.HomeService;
import com.lxy.service.ImgCarouselService;
import com.lxy.service.MessageService;
import com.lxy.service.SginInService;
import com.lxy.service.TopicService;
import com.lxy.service.WishService;
import com.lxy.utils.DateUtil;
import com.lxy.utils.ValidUtil;
@Service("HomeService")
public class HomeServiceImpl implements HomeService {

	@Autowired
	ImageCafeDao imageService;
	
	@Autowired
	AdService adService;
	
	@Autowired
	WishService wishService;
	
	@Autowired
	MessageService messageService;
	
	@Autowired
	ImgCarouselService imgCarouselService;
	
	@Autowired
	TopicService topicService;
	
	@Autowired
	SginInService sginInService;
	@Override
	public Object loadHome(Header requestHeader) {
		String today=DateUtil.date2Str(new Date(),"yyyy-MM-dd");
		Map<String,Object> data=new HashMap<String,Object>();
		//签到
		data.put("isSignIn", 0);
		String uidStr=requestHeader.getUid();
		if(!ValidUtil.isNull(uidStr)){
			Integer obj=sginInService.isSignIn(Long.parseLong(uidStr),today);
			if(obj!=null&&1==obj){
				data.put("isSignIn", 1);
			}
		}
		//轮播图:（图咖，许愿分享），一个广告位(统一获取)
		List<ImgCarousel> imgCarousel=imgCarouselService.list();
		data.put("imgCarouse", imgCarousel);
		//首页广告
		List<AdPositionDto> adPositions=adService.listByPage(AdPosition.PAGE_HOME);
		data.put("ad", adPositions);
		//活动消息(首页展示公共消息，可能有多条)
		List<Message> messageList=messageService.list(MessageType.TYPE_PUBLIC);
		data.put("msg", messageList);
		//今日话题
		Topic topic=topicService.query(today);
		data.put("topic", topic);
		//许愿池
		List<Wish> wish=wishService.list(Wish.STATE_RUNNING);
		data.put("wish", wish);
		//
		data.put("now", new Date());
		//愿望榜单
		return data;
	}

}
