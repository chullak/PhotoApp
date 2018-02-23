package com.code.challenge.dao;

import java.util.List;

import com.code.challenge.model.Album;

public interface AlbumDao extends Dao<Album> {

	List<Album> listByUserID(int userId);


}
