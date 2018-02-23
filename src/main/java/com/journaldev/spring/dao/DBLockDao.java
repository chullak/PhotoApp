package com.journaldev.spring.dao;

public interface DBLockDao {

	public boolean add();

	public void clear();

	public boolean isDataloadInProgress();
}
