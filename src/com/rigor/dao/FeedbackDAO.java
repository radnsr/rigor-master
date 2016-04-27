package com.rigor.dao;

import java.util.List;

import com.rigor.model.Category;
import com.rigor.model.Feedback;

public interface FeedbackDAO {
	public Feedback save(Feedback model);

	public List<Feedback> getAllData();

	public List<Category> categoryList(String dept_id);
}
