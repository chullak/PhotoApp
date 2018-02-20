package com.journaldev.spring.service;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.journaldev.spring.client.AlbumRestClient;
import com.journaldev.spring.client.PhotoRestClient;
import com.journaldev.spring.client.UserRestClient;
import com.journaldev.spring.dao.AlbumDao;
import com.journaldev.spring.dao.PhotoDao;
import com.journaldev.spring.dao.UserDao;

@Component
public class ApplicationService {

	@Inject
	AlbumRestClient albumRestClient;

	@Inject
	PhotoRestClient photoRestClient;

	@Inject
	UserRestClient userRestClient;

	@Inject
	AlbumDao albumDao;

	@Inject
	UserDao userDao;

	@Inject
	PhotoDao photoDao;

	public boolean refresh() {
		albumDao.deleteAll();
		albumDao.saveAll(albumRestClient.getPhoto());
		photoDao.deleteAll();
		photoDao.saveAll(photoRestClient.getPhoto());
		userDao.deleteAll();
		userDao.saveAll(userRestClient.getPhoto());
		System.out.println("done");
		return true;
	}

}
