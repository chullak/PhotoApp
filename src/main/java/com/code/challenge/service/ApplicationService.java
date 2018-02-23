package com.code.challenge.service;

import java.util.Map.Entry;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.code.challenge.config.DataLoadConfig;
import com.code.challenge.dao.DBLockDao;
import com.code.challenge.dao.Dao;
import com.code.challenge.http.client.RestClient;

@Component
public class ApplicationService {
	@Inject
	DataLoadConfig dataLoadConfig;

	@Inject
	DBLockDao dbLockDao;

	public boolean refresh() {
		// add the entry in db Lock table to indicate the data loading in
		// progress to keep distributed structure in mind
		if (!dbLockDao.isDataloadInProgress()) {
			dbLockDao.add();
			try {
				Dao dao;
				RestClient restClient;
				for (Entry<RestClient, Dao> entry : dataLoadConfig.getDataloaders().entrySet()) {
					restClient = entry.getKey();
					dao = entry.getValue();
					dao.deleteAll();
					dao.saveAll(restClient.getList());

				}
			} finally {
				System.out.println("done");
				dbLockDao.clear();
			}
		} else {

			throw new RuntimeException("DataLoad is aready in progress");
		}
		return true;
	}

}
