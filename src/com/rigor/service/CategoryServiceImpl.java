package com.rigor.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rigor.dao.CategoryDAO;
import com.rigor.model.Category;
import com.rigor.model.Department;
import com.rigor.model.User;
import com.rigor.util.HibernateUtilities;
import com.rigor.util.Methods;

@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryDAO catDao;

	// ------------Save Category master data----------------------
	public Category save(Category model) {

		Category category = catDao.save(model);

		return category;
	}

	// ------------Retrieve all category master data----------------------
	public List<Category> getAllData() {

		List<Category> list = catDao.getAllData();
		return list;
	}

	// ------------Update category master data----------------------
	public void update(Category model) {
		catDao.update(model);
	}

	// ------------get single category master data----------------------
	public Category findById(String id) {

		List<Category> list = catDao.getCategoryList();

		for (Category model : list) {
			if (model.getCategory_id().equals(id)) {
				return model;
			}
		}
		return null;
	}

	// ------------Deactivate Category master data----------------------
	public void deactivate(String id) {
		catDao.deactivate(id);
	}

	// ------------Activate Category master data----------------------
	@Override
	public void activate(String id) {

		catDao.activate(id);

	}

}
