package com.rigor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rigor.dao.FeedbackDAO;
import com.rigor.model.Category;
import com.rigor.model.Feedback;

@Service("feedbackService")
@Transactional
public class FeedbackServiceImpl implements FeedbackService {
	@Autowired
	FeedbackDAO feedbackDao;

	// ------------Save Feedback master data----------------------
	public Feedback save(Feedback model) {

		Feedback feedback = feedbackDao.save(model);

		return feedback;
	}

	// ------------Retrieve all feedback master data----------------------
	public List<Feedback> getAllData() {
		List<Feedback> list = feedbackDao.getAllData();
		return list;
	}

	// ------------Deactivate Feedback master data----------------------
	public void deactivate(String id) {

	}

	// ------------Activate Feedback master data----------------------
	
	public void activate(String id) {

	}

	
	public List<Category> categoryList(String dept_id) {
		List<Category> list = feedbackDao.categoryList(dept_id);

		return list;

	}

	@Override
	public List<Feedback> getDataByDeptId(String dept_id) {
		return feedbackDao.getDataByDeptId(dept_id);
	}

}
