package com.journaldev.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.journaldev.spring.dao.DBLockDao;
import com.journaldev.spring.dao.UserDao;
import com.journaldev.spring.model.Company;
import com.journaldev.spring.model.User;

public class TestSpringRestExample {

	public static final String SERVER_URI = "http://localhost:9090/SpringRestExample";

	public static void main(String args[]) {

		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("root-context.xml");

		ctx.getBean(DBLockDao.class);
		DBLockDao dbLockDao = ctx.getBean(DBLockDao.class);

		dbLockDao.add();
	System.out.println(	dbLockDao.isDataloadInProgress());
	dbLockDao.clear();
	System.out.println(	dbLockDao.isDataloadInProgress());

	}

}