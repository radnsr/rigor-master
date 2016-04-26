package com.rigor.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.rigor.model.User;
import com.rigor.util.HibernateUtilities;
import com.rigor.util.Methods;
@Repository("userDao")
public class UserDAOImpl implements UserDAO{
	SessionFactory sessionFactory = null;

	public UserDAOImpl() {
		sessionFactory = HibernateUtilities.getSessionFactory();

	}
	@Override
	public User save(User model) {
		User model_new = new User();
		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();
			model_new = model;
			Methods method = new Methods();
			model.setPassword(method.RandomCode());
			model_new.setUser_id(method.generateID(session, "U", "user_id", User.class));
			model_new.setStatus(2);
			session.save(model_new);

			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}

		return model;
	}

	@Override
	public List<User> getAllData() {
		List<User> list = null;
		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();
			list = session.createCriteria(User.class).list();
			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return list;
	}

	@Override
	public void update(User model) {
		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();

			session.update(model);

			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}		
	}

	

	@Override
	public void deactivate(String id) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();

			Query query = session.createQuery("UPDATE com.rigor.model.User SET status=0 WHERE user_id = :user_id");
			query.setParameter("user_id", id);
			query.executeUpdate();

			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}		
	}

	@Override
	public void activate(String id) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();

			Query query = session.createQuery("UPDATE com.rigor.model.User SET status=1 WHERE user_id = :user_id");
			query.setParameter("user_id", id);
			query.executeUpdate();

			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
	
	}

	

}
