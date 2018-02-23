package com.code.challenge.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.code.challenge.dao.UserDao;
import com.code.challenge.model.User;

@Component
public class UserService {

	@Inject
	UserDao userDao;

	
	public List<User> list() {
		return userDao.list();
	}

	public User findById(int id) {
		return userDao.findById(id);
	}

	public void save(User user) {
		userDao.save(user);
	}

	public void update(User user) {
		userDao.update(user);
		
	}

	public void delete(User user) {
		userDao.delete(user);
		
	}

}
