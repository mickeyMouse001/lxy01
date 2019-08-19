package com.lxy.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.lxy.dao.TestDao;
import com.lxy.dao.UserDao;
import com.lxy.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserDao {
	
	@Autowired
	UserDao userDao;
	@Autowired
	TestDao testDao;
	
	@Test
	public void getUserByAccount(){
		String account="lisa";
		User user=userDao.getUserByAccount(account);
		System.out.println(user);
	}
	
	@Test
	public void test(){
		System.out.println(testDao.test());
	}
}
