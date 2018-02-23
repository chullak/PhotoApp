package com.journaldev.spring.dao;

import java.util.List;

import com.journaldev.spring.model.Photo;

public interface PhotoDao extends Dao<Photo> {

	List<Photo> listByAblum(int albumId);



}
