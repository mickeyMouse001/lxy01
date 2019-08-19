package com.lxy.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SignInDao {

	@Autowired
	JdbcTemplate jdbc;
	
	public Integer isSignIn(long uid, String today){
	
		String sql="SELECT oid from sign_in WHERE uid=? and sign_date=?";
		Integer i=jdbc.queryForList(sql, new Object[]{uid,today}, Long.class).size()<=0?0:1;
		return i;
	}

}
