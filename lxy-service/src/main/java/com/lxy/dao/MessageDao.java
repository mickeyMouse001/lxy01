package com.lxy.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lxy.entity.Message;
@Repository
public class MessageDao {

	@Autowired
	JdbcTemplate jdbc;
	
	public List<Message> list(Integer type) {
		String sql="SELECT * from message WHERE msg_type=? and msg_state=1";
		return jdbc.query(sql, new Object[]{type}, new BeanPropertyRowMapper<Message>(Message.class));
	}

}
