package com.lxy.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lxy.entity.Topic;
@Repository
public class TopicDao {

	@Autowired
	DbDao dbDao;
	
	public Topic query(String todayStr) {
		String sql="SELECT "+
						"top.oid,"+
						"top.title,"+
						"top.today,"+
						"tbn1.`name` positiveSideName, "+
						"tbn2.`name` negativeSideName, "+
						"(top.positive_jifen_count+top.negative_jifen_count) totalCount,"+
						"top.victory_side,top.topic_state "+
					"from "+
						"topic top "+
					"LEFT JOIN "+
						"topic_bet_name tbn1 "+
					"ON "+
						"top.positive_side_id=tbn1.oid "+
					"LEFT JOIN "+
						"topic_bet_name tbn2 "+
					"ON "+
						"top.negative_side_id=tbn2.oid "+
					"WHERE "+
						"top.today=?";
		
		return dbDao.queryForObject(sql, new Object[]{todayStr}, new BeanPropertyRowMapper<Topic>(Topic.class));
	}

}
