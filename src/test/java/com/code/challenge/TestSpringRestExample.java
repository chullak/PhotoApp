package com.code.challenge;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.code.challenge.dao.DBLockDao;
import com.code.challenge.dao.UserDao;
import com.code.challenge.model.Company;
import com.code.challenge.model.User;

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