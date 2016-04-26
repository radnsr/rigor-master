package com.rigor.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.rigor.model.Category;
import com.rigor.util.HibernateUtilities;
import com.rigor.util.Methods;
@Repository("categoryDao")
public class CategoryDAOImpl implements CategoryDAO {
	SessionFactory sessionFactory = null;

	public CategoryDAOImpl() {
		sessionFactory = HibernateUtilities.getSessionFactory();

	}
	@Override
	public Category save(Category model) {
		Category model_new = new Category();
		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();
			model_new = model;
			Methods method = new Methods();
			model_new.setCategory_id(method.generateID(session, "C", "category_id", Category.class));
			model_new.setStatus(1);
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
	public List<Category> getAllData() {
		List<Category> list = null;
		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();

			String hql = "SELECT cat.category_id,cat.description,dept.dept_id,dept.dept_name, cat.status "
					+ "FROM  com.rigor.model.Category as cat , com.rigor.model.Department as dept "
					+ "WHERE cat.department.dept_id=dept.dept_id";
			Query query = session.createQuery(hql);
			list = query.list();

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
	public void update(Category model) {
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
	public List<Category> getCategoryList() {
		Session session = sessionFactory.openSession();
		List<Category> list = null;
		try {
			session.beginTransaction();
			list = session.createCriteria(Category.class).list();
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
	public void deactivate(String id) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();

			Query query = session
					.createQuery("UPDATE com.rigor.model.Category SET status=0 WHERE category_id = :category_id");
			query.setParameter("category_id", id);
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

			Query query = session
					.createQuery("UPDATE com.rigor.model.Category SET status=1 WHERE category_id = :category_id");
			query.setParameter("category_id", id);
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
