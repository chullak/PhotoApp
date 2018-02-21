package com.journaldev.spring.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.journaldev.spring.dao.PhotoDao;
import com.journaldev.spring.model.Photo;

@Component
public class PhotoService {

	@Inject
	PhotoDao photoDao;

	public List<Photo> list() {
		return photoDao.list();
	}

	public Photo findById(int id) {
		return photoDao.findById(id);
	}

	public void save(Photo Photo) {
		photoDao.save(Photo);
	}

	public void update(Photo Photo) {
		photoDao.update(Photo);
		
	}

	public void delete(Photo Photo) {
		photoDao.delete(Photo);
		
	}

	public List<Photo> listByAblum(int albumId) {
		return photoDao.listByAblum(albumId);
	}

}
