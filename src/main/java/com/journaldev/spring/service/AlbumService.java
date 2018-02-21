package com.journaldev.spring.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.journaldev.spring.dao.AlbumDao;
import com.journaldev.spring.model.Album;

@Component
public class AlbumService {

	@Inject
	AlbumDao albumDao;

	public List<Album> list() {
		return albumDao.list();
	}

	public Album findById(int id) {
		return albumDao.findById(id);
	}

	public void save(Album album) {
		albumDao.save(album);
	}

	public void update(Album album) {
		albumDao.update(album);

	}

	public void delete(Album album) {
		albumDao.delete(album);

	}

	public List<Album> list(int userId) {
		return albumDao.listByUserID(userId);
	}

}
