package com.rigor.dao;

import java.util.List;

import com.rigor.model.Category;

public interface CategoryDAO {
	public Category save(Category model);

	public Category get(String cat_id);

	public List<Category> getAllData();

	void update(Category model);

	List<Category> getCategoryList();

	void deactivate(String id);

	void activate(String id);

}
