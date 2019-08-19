package com.lxy.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lxy.entity.Wish;

@Repository
public class WishDao {

	@Autowired
	JdbcTemplate jdbc;
	
	public List<Wish> list(int state) {
		String sql="SELECT "+ 
						"wish.oid wishId, "+ 
						"wish.wish_name wishName, "+ 
						"wish.start_time, "+ 
						"wish.end_time, "+ 
						"wg.wish_value, "+ 
						"p.oid productId, "+ 
						"p.p_cover productCover, "+ 
						"p.p_info_url productInfoUrl  "+ 
					"from  "+ 
						"wish  "+ 
					"LEFT JOIN  "+ 
						"wish_gift wg  "+ 
					"ON  "+ 
						"wish.gift_id=wg.oid  "+ 
					"LEFT JOIN  "+ 
						"product p  "+ 
					"ON  "+ 
						"wg.product_id=p.oid  "+ 
					"WHERE  "+ 
						"wish.wish_state=? "+ 
					"ORDER BY  "+ 
						"wg.wish_value desc";
		return jdbc.query(sql, new Object[]{state}, new BeanPropertyRowMapper<Wish>(Wish.class));
	}

	
	
}
