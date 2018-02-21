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

@Component
public class AlbumDao implements Dao<Album> {

	@Inject
	private SessionFactory sessionFactory;

	public boolean saveAll(List<Album> list) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		for (Album a : list) {
			session.persist(a);
		}
		tx.commit();
		session.close();
		return true;
	}

	@Override
	public boolean save(Album obj) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(obj);
		tx.commit();
		session.close();
		return true;
	}

	@Override
	public List<Album> list() {
		Session session = sessionFactory.openSession();
		List<Album> result = session.createCriteria(Album.class).list();
		session.close();
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public Album findById(int id) {
		Session session = sessionFactory.openSession();
		Album album = (Album) session.get(Album.class, id);
		session.close();

		return album;
	}

	@Override
	public boolean delete(Album obj) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(obj);
		tx.commit();
		session.close();
		return true;
	}

	public void update(Album obj) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(obj);
		tx.commit();
		session.close();

	}

	@Override
	public boolean deleteAll() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		String stringQuery = "delete from Album";
		Query query = session.createQuery(stringQuery);
		query.executeUpdate();
		tx.commit();
		session.close();

		return false;
	}

	public List<Album> listByUserID(int userId) {
		Session session = sessionFactory.openSession();
		List<Album> result = session.createCriteria(Album.class).add(Restrictions.eq("userId", userId)).list();
		session.close();
		// TODO Auto-generated method stub
		return result;

}

}
