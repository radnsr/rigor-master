package com.rigor.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.rigor.constants.SQLQueries;
import com.rigor.model.Category;
import com.rigor.model.Feedback;
import com.rigor.util.HibernateUtilities;
import com.rigor.util.Methods;
@Repository("feedbackDao")
public class FeedbackDAOImpl implements FeedbackDAO {
	SessionFactory sessionFactory = null;

	public FeedbackDAOImpl() {
		sessionFactory = HibernateUtilities.getSessionFactory();
	}

	@Override
	public Feedback save(Feedback model) {
		Feedback model_new = new Feedback();
		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();
			model_new = model;
			Methods method = new Methods();
			model_new.setFeedback_id(method.generateID(session, "F", "feedback_id", Feedback.class));
			model_new.setStatus(1);

			java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

			model_new.setDate(date);
			session.save(model_new);

			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}

		return model_new;
	}

	@Override
	public List<Feedback> getAllData() {
		List<Feedback> list = null;
		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();
			Query query = session.createQuery(SQLQueries.FEEDBACK_GETALL);
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
	public List<Category> categoryList(String dept_id) {
		List<Category> list = null;
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();

			Criteria c = session.createCriteria(Category.class, "cat").add(Restrictions.eq("status", 1));
			c.createAlias("cat.department", "dept");
			c.add(Restrictions.eq("dept.dept_id", dept_id));
			c.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			list = c.list();

			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}

		return list;
	}

}
