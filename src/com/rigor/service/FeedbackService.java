package com.rigor.service;

import java.util.List;

import com.rigor.model.Category;
import com.rigor.model.Feedback;

public interface FeedbackService {

	public Feedback save(Feedback model);

	public List<Feedback> getAllData();



	void deactivate(String id);

	void activate(String id);
	
	public List<Category> categoryList(String dept_id);
	

}
