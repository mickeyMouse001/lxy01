package com.lxy.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lxy.entity.User;
import com.lxy.entity.UserInfo;
import com.lxy.entity.UserThird;

@Repository
public class UserDao {

	@Autowired
	JdbcTemplate jdbc;

	public User getUserByAccount(String account) {
		String sql = "select * from user where account=?";
		User user = null;
		try{
			user=jdbc.queryForObject(sql, new Object[] { account }, new BeanPropertyRowMapper<User>(User.class));
		}catch(DataAccessException e){
			
		}
		
		return user;
	}

	public User getUserByMobile(String tel) {
		String sql = "select * from user where tel=?";
		User user = jdbc.queryForObject(sql, new Object[] { tel }, new BeanPropertyRowMapper<User>(User.class));
		return user;
	}

	public User getUserByOpenId(String openId, Integer type) {
		String sql="SELECT u.* from user u LEFT JOIN user_third ut ON u.uid=ut.uid WHERE ut.openid=? and ut.login_type=?";
		User user=jdbc.queryForObject(sql,new Object[]{openId,type}, new BeanPropertyRowMapper<User>(User.class));
		return user;
	}
	
	
	
	public int saveUser(User user) {
		System.out.println("保存用户信息");
		String sql="INSERT INTO user (uid,cid,p_uid,account,password,avatar,sex,tel,invitation_code,nick_name,salt) values(?,?,?,?,?,?,?,?,?,?,?)";
		int i=jdbc.update(sql,user.getUid(),user.getCid(),user.getpUid(),user.getAccount(),user.getPassword(),user.getAvatar(),user.getSex(),user.getTel(),user.getInvitationCode(),user.getNickName(),user.getSalt());
		return i;
	}

	public int saveUserInfo(UserInfo userInfo) {
		String sql="INSERT INTO user_info (uid,longitude,latitude,province_code,city_code,area_code,last_time,login_ip,os_type,app_jx) values(?,?,?,?,?,?,?,?,?,?)";
		int i=jdbc.update(sql,userInfo.getUid(),userInfo.getLongitude(),userInfo.getLatitude(),userInfo.getProvinceCode(),userInfo.getCityCode(),userInfo.getAreaCode(),userInfo.getLastTime(),userInfo.getLoginIp(),userInfo.getOsType(),userInfo.getAppJx());
		return i;
	}

	public int saveThird(UserThird uThird) {
		System.out.println("保存第三方用户信息");
		return 1;
	}

	@Cacheable(value = "userInfo" ,key = "'11111'")
	public Long findUidByInvitationCode(String invitationCode) {
		System.out.println("根据邀请码查用户uid");
		String sql="SELECT uid from user WHERE invitation_code=?";
		
		Long i=jdbc.queryForObject(sql,new Object[] { invitationCode }, Long.class);
		System.out.print("----"+i);
		return i;
	}

}
