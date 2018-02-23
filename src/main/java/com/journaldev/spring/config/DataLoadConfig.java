package com.journaldev.spring.config;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.journaldev.spring.dao.AlbumDao;
import com.journaldev.spring.dao.Dao;
import com.journaldev.spring.dao.PhotoDao;
import com.journaldev.spring.dao.UserDao;
import com.journaldev.spring.http.client.RestClient;
import com.journaldev.spring.http.client.impl.AlbumRestClient;
import com.journaldev.spring.http.client.impl.PhotoRestClient;
import com.journaldev.spring.http.client.impl.UserRestClient;

@Component
public class DataLoadConfig {
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

	public Map<RestClient,Dao> getDataloaders() {
		LinkedHashMap<RestClient,Dao> map=new LinkedHashMap(); 
		map.put(userRestClient, userDao);
		map.put(albumRestClient, albumDao);
		
		map.put(photoRestClient, photoDao);
		
        return map;
    }
}
