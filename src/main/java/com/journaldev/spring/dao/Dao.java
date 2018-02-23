package com.journaldev.spring.dao;

import java.util.List;

public interface Dao<T> {

	public boolean saveAll(List<T> list);

	public boolean save(T obj);

	public List<T> list();

	public T findById(int id);

	public boolean delete(T obj);

	public boolean deleteAll();

	void update(T obj);

}
