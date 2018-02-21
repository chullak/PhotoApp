package com.journaldev.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.journaldev.spring.dao.AlbumDao;
import com.journaldev.spring.dao.UserDao;
import com.journaldev.spring.model.Album;
import com.journaldev.spring.model.User;

public class TestSpringRestExample {

	public static final String SERVER_URI = "http://localhost:9090/SpringRestExample";

	public static void main(String args[]) {

		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("root-context.xml");

		ctx.getBean(AlbumDao.class);
		UserDao userDao = ctx.getBean(UserDao.class);
		;
		User user = new User();
		user.setName("asas");
		user.setUsername("assas");

		userDao.save(user);

	}

}