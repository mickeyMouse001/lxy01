package com.lxy.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
@Repository
public class DbDao {

	@Autowired
	JdbcTemplate jdbc;
	
	public <T> T queryForObject(String sql,Object[] objArray,RowMapper<T> rowMapper){
		T data=null;
		try{
			data=jdbc.queryForObject(sql, objArray, rowMapper);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
}
