package com.rigor.service;

import java.util.List;

import com.rigor.model.Category;

public interface CategoryService {

	public Category save(Category model);

	public List<Category> getAllData();

	void update(Category model);

	Category findById(String id);

	void deactivate(String id);

	void activate(String id);
	
	

}
