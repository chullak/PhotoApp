package com.journaldev.spring.dao;

import java.util.List;

import com.journaldev.spring.model.Album;

public interface AlbumDao extends Dao<Album> {

	List<Album> listByUserID(int userId);


}
