package com.journaldev.spring.service;

import java.util.Map.Entry;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.journaldev.spring.config.DataLoadConfig;
import com.journaldev.spring.dao.DBLockDao;
import com.journaldev.spring.dao.Dao;
import com.journaldev.spring.http.client.RestClient;

@Component
public class ApplicationService {
	@Inject
	DataLoadConfig  dataLoadConfig;
	
	@Inject
	DBLockDao dbLockDao;

	public boolean refresh() {
		// add the entry in db Lock table to indicate the data loading in
		// progress
		if(!dbLockDao.isDataloadInProgress()){
		dbLockDao.add();
		Dao dao;
		RestClient restClient;
		for(Entry<RestClient, Dao> entry: dataLoadConfig.getDataloaders().entrySet())
		{
			restClient=entry.getKey();
			dao=entry.getValue();
			dao.deleteAll();
			dao.saveAll(restClient.getList());
			
		}	
		System.out.println("done");
		dbLockDao.clear();
		}else{
			
			throw new RuntimeException("DataLoad is aready in progress");
		}
		return true;
	}

}
