package com.rigor.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	// ------------Update feedback master data----------------------
	public void update(Feedback model) {
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
	// ------------get single feedback master data----------------------
	public Feedback findById(String id) {
		List<Feedback> list = getAllData();

		for (Feedback model : list) {
			if (model.getFeedback_id().equals(id)) {
				return model;
			}
		}
		return null;
	}
	// ------------Deactivate Feedback master data----------------------
	public void deactivate(String id) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();

			Query query = session.createQuery("UPDATE com.rigor.model.Feedback SET status=0 WHERE feedback_id = :feedback_id");
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

			Query query = session.createQuery("UPDATE com.rigor.model.Feedback SET status=1 WHERE feedback_id = :feedback_id");
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


}
