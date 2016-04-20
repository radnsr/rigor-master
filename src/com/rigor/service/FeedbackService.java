package com.rigor.service;

import java.util.List;

import com.rigor.model.Feedback;

public interface FeedbackService {

	public Feedback save(Feedback model);

	public List<Feedback> getAllData();

	void update(Feedback model);

	Feedback findById(String id);

	void deactivate(String id);

	void activate(String id);
	
	

}
