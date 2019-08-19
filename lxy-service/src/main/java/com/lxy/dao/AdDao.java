package com.lxy.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lxy.dto.AdPositionDto;
import com.lxy.entity.Advertisement;

@Repository
public class AdDao {

	@Autowired
	JdbcTemplate jdbc;

	public List<AdPositionDto> listByPage(String pageName) {
		String sql="SELECT adp.* from ad_position adp WHERE adp.pos_state=1 and adp.page_tag=?";
		List<AdPositionDto> adPostions=jdbc.query(sql, new Object[] { pageName },new BeanPropertyRowMapper<AdPositionDto>(AdPositionDto.class));
		//组装一对多
		if(adPostions!=null&&adPostions.size()>0){
			for(AdPositionDto ap:adPostions){
				ap.setAds(this.listByPositionId(ap.getOid()));
			}
		}
		return adPostions;
	}
	public List<Advertisement> listByPositionId(Long positionId){
		String sql="SELECT * from ad WHERE ad.del_tag=0 and ad.position_id=?";
		List<Advertisement> adPostions=jdbc.query(sql, new Object[] { positionId },new BeanPropertyRowMapper<Advertisement>(Advertisement.class));
		
		return adPostions;
	}

	
	
}
