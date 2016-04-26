package com.rigor.dao;

import java.util.List;

import com.rigor.model.User;

public interface UserDAO {
	public User save(User model);

	public List<User> getAllData();

	void update(User model);

	void deactivate(String id);

	void activate(String id);
	
	

}
