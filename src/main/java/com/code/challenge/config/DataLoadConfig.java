package com.code.challenge.config;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.code.challenge.dao.AlbumDao;
import com.code.challenge.dao.Dao;
import com.code.challenge.dao.PhotoDao;
import com.code.challenge.dao.UserDao;
import com.code.challenge.http.client.RestClient;
import com.code.challenge.http.client.impl.AlbumRestClient;
import com.code.challenge.http.client.impl.PhotoRestClient;
import com.code.challenge.http.client.impl.UserRestClient;

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
