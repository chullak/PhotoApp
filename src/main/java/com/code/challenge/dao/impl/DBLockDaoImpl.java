package com.code.challenge.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.code.challenge.dao.DBLockDao;
import com.code.challenge.model.DBLock;

@Component
public class DBLockDaoImpl implements DBLockDao {

	@Inject
	private SessionFactory sessionFactory;

	public boolean add() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		session.persist(new DBLock());
		tx.commit();
		session.close();
		return true;
	}

	public void clear() {
		Session session = sessionFactory.openSession();
		String stringQuery = "delete from DBLock";
		Query query = session.createQuery(stringQuery);
		query.executeUpdate();
		session.close();
		// TODO Auto-generated method stub
	}

	public boolean isDataloadInProgress() {
		Session session = sessionFactory.openSession();
		List<DBLock> result = session.createCriteria(DBLock.class).list();
		session.close();

		if (result == null || result.size() == 0) {
			return false;
		} else {
			return true;

		}

	}

}
