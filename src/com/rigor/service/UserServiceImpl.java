package com.rigor.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rigor.model.Department;
import com.rigor.model.User;
import com.rigor.util.HibernateUtilities;
import com.rigor.util.Methods;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	SessionFactory sessionFactory = null;

	public UserServiceImpl() {
		sessionFactory = HibernateUtilities.getSessionFactory();

	}

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

	public void updateDept(User model) {
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

	public User findById(String id) {
		List<User> list = getAllData();

		for (User model : list) {
			if (model.getUser_id().equals(id)) {
				return model;
			}
		}
		return null;
	}

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

	@Override
	public User authenticate(User user) {
		List<User> list = getAllData();

		for (User model : list) {
			if (model.getEmail().equals(user.getEmail()) && model.getPassword().equals(user.getPassword())) {
				System.out.println(model.getEmail());
				
				return model;
			}
		}
		return null;
	}

}
