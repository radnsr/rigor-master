package com.rigor.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rigor.dao.UserDAO;
import com.rigor.model.Department;
import com.rigor.model.User;
import com.rigor.util.HibernateUtilities;
import com.rigor.util.Methods;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	UserDAO userDao;

	public User save(User model) {

		User user = userDao.save(model);

		return user;
	}

	public List<User> getAllData() {
		List<User> list = userDao.getAllData();
		return list;
	}

	public void update(User model) {
		userDao.update(model);

	}

	public User findById(String id) {
		List<User> list = getAllData();

		for (User model : list) {
			if (model.getUser_id().equals(id)) {
				return model;
			}
		}
		return null;
	}

	public void deactivate(String id) {
		userDao.deactivate(id);
	}

	@Override
	public void activate(String id) {

		userDao.activate(id);
	}

	@Override
	public User authenticate(User user) {
		User valid_user=findByEmail(user.getEmail());
		
			if (valid_user.getPassword().equals(user.getPassword())) {

				return valid_user;
			}
		
		return null;
	}

	@Override
	public User get(String user_id) {
		return userDao.get(user_id);
	}
	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userDao.findByEmail(email);
	}

}
