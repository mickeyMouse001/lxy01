package com.lxy.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.lxy.entity.ImgCarousel;

@Repository
public class ImgCarouselDao {

	@Autowired
	JdbcTemplate jdbc;	

	public List<ImgCarousel> list() {
		String sql="select "+
						"ical.oid,ical.obj_id objId,ical.content_type contentType,ical.sort_val sortVal,"+
						"(CASE WHEN ical.content_type = 1 THEN ic.title else si.title END) title, "+
						"(CASE WHEN ical.content_type = 1 THEN ic.path else si.path END) path "+
					"from "+
						"img_carousel ical "+
					"join "+
						"(SELECT ic.oid,ic.title,atta.path from img_cafe ic  JOIN attachment_store atta ON atta.obj_id=ic.oid WHERE atta.category=3) ic "+
					"on "+
						"ical.obj_id=ic.oid "+
					"join "+
						"(SELECT simg.oid,simg.title,atta.path from sharing_img simg JOIN attachment_store atta ON atta.obj_id=simg.oid WHERE atta.category=2) si "+ 
					"on "+
						"ical.obj_id=si.oid ";
		
		List<ImgCarousel> imgCarouselList=jdbc.query(sql,new BeanPropertyRowMapper<ImgCarousel>(ImgCarousel.class));
		return imgCarouselList;
	}
	
	
	
}
