package com.lxy.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class TestDao {
	
	@Autowired
	JdbcTemplate tem;
	
	public String test(){
		List<Map<String,Object>> u=tem.queryForList("select * from user t where uid=1");
		System.out.println(u);
		return "dao test";
	}
}
