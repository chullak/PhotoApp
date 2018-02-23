package com.code.challenge.dao;

import java.util.List;

import com.code.challenge.model.Photo;

public interface PhotoDao extends Dao<Photo> {

	List<Photo> listByAblum(int albumId);



}
