package com.rigor.service;

import java.util.List;

import com.rigor.model.User;

public interface UserService {

	public User save(User model);

	public User get(String user_id);

	public List<User> getAllData();

	void update(User model);

	User findById(String id);

	void deactivate(String id);

	void activate(String id);

	public User authenticate(User user);

	public User findByEmail(String email);
}
