package com.rigor.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rigor.model.Category;
import com.rigor.model.Feedback;
import com.rigor.util.HibernateUtilities;
import com.rigor.util.Methods;

@Service("feedbackService")
@Transactional
public class FeedbackServiceImpl implements FeedbackService {
	SessionFactory sessionFactory = null;

	public FeedbackServiceImpl() {
		sessionFactory = HibernateUtilities.getSessionFactory();

	}

	// ------------Save Feedback master data----------------------
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

	// ------------Retrieve all feedback master data----------------------
	public List<Feedback> getAllData() {
		List<Feedback> list = null;
		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();
			list = session.createCriteria(Feedback.class).list();
			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return list;
	}

	



	// ------------Deactivate Feedback master data----------------------
	public void deactivate(String id) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();

			Query query = session
					.createQuery("UPDATE com.rigor.model.Feedback SET status=0 WHERE feedback_id = :feedback_id");
			query.setParameter("feedback_id", id);
			query.executeUpdate();

			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
	}

	// ------------Activate Feedback master data----------------------
	@Override
	public void activate(String id) {

		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();

			Query query = session
					.createQuery("UPDATE com.rigor.model.Feedback SET status=1 WHERE feedback_id = :feedback_id");
			query.setParameter("feedback_id", id);
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
	public List<Category> categoryList(String dept_id) {
		
		List<Category> list=null;
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			
			Criteria c = session.createCriteria(Category.class, "cat").add(Restrictions.eq("status", 1));
			c.createAlias("cat.department", "dept");
			c.add(Restrictions.eq("dept.dept_id", dept_id));
			c.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);	
			list=c.list();
			
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
