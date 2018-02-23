package com.journaldev.spring.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.journaldev.spring.dao.UserDao;
import com.journaldev.spring.model.User;

@Component
public class UserDaoImpl implements UserDao {

	@Inject
	private SessionFactory sessionFactory;

	@Override
	public boolean saveAll(List<User> list) {

		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			for (User user : list) {
				if (user.getCompany() != null)
					session.saveOrUpdate(user.getCompany());

				session.persist(user);
			}
			tx.commit();
			session.close();
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return true;
	}

	@Override
	public boolean save(User obj) {
		Session session = sessionFactory.openSession();

		Transaction tx = session.beginTransaction();
		if (obj.getCompany() != null)
			session.saveOrUpdate(obj.getCompany());

		session.saveOrUpdate(obj);
		tx.commit();
		session.close();
		return true;
	}

	@Override
	public List<User> list() {
		Session session = sessionFactory.openSession();
		List<User> result = session.createCriteria(User.class).list();
		session.close();
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public User findById(int id) {
		Session session = sessionFactory.openSession();
		User User = (User) session.get(User.class, id);
		session.close();

		return User;
	}

	@Override
	public boolean delete(User obj) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(obj);
		tx.commit();
		session.close();
		return true;
	}

	@Override
	public void update(User obj) {
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
		String stringQuery = "delete from Company";
		Query query = session.createQuery(stringQuery);

		stringQuery = "delete from User";
		query = session.createQuery(stringQuery);
		query.executeUpdate();
		tx.commit();
		session.close();

		return false;
	}

}
