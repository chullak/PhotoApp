package com.journaldev.spring.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.journaldev.spring.model.Album;
import com.journaldev.spring.model.DBLock;
import com.journaldev.spring.model.User;

@Component
public class DBLockDao {

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
