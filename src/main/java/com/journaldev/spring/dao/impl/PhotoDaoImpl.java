package com.journaldev.spring.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.journaldev.spring.dao.PhotoDao;
import com.journaldev.spring.model.Photo;

@Component
public class PhotoDaoImpl implements PhotoDao {

	@Inject
	private SessionFactory sessionFactory;

	@Override
	public boolean saveAll(List<Photo> list) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		for (Photo a : list) {
			session.persist(a);
		}
		tx.commit();
		session.close();
		return true;
	}

	@Override
	public boolean save(Photo obj) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(obj);
		tx.commit();
		session.close();
		return true;
	}

	@Override
	public List<Photo> list() {
		Session session = sessionFactory.openSession();
		List<Photo> result = session.createCriteria(Photo.class).list();
		session.close();
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public Photo findById(int id) {
		Session session = sessionFactory.openSession();
		Photo Photo = (Photo) session.get(Photo.class, id);
		session.close();

		return Photo;
	}

	@Override
	public boolean delete(Photo obj) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(obj);
		tx.commit();
		session.close();
		return true;
	}

	@Override
	public void update(Photo obj) {
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
		String stringQuery = "delete from Photo";
		Query query = session.createQuery(stringQuery);
		query.executeUpdate();
		tx.commit();
		session.close();

		return false;
	}

	@Override
	public List<Photo> listByAblum(int albumId) {
		Session session = sessionFactory.openSession();
		List<Photo> result = session.createCriteria(Photo.class).add(Restrictions.eq("albumId", albumId)).list();
		session.close();
		// TODO Auto-generated method stub
		return result;
	}

}
