package com.rigor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rigor.dao.DeptDAO;
import com.rigor.model.Department;;

@Service("deptService")
@Transactional
public class DeptServiceImpl implements DeptService {

	@Autowired
	DeptDAO deptDAO;

	// ------------Save Department master data-----------------------
	public Department saveDept(Department dept) {
		Department department = deptDAO.saveDept(dept);
		return department;
	}

	// ----------------Retrieve All Department master data---------------
	@Override
	public List<Department> getAllDepts() {

		List<Department> list = deptDAO.getAllDepts();

		return list;
	}

	// ----------------Retrieve Activated Department master data---------------
	@Override
	public List<Department> getDeptList() {

		List<Department> list = deptDAO.getDeptList();

		return list;
	}

	// ----------------Update Department master data---------------

	@Override
	public void updateDept(Department dept) {
		deptDAO.updateDept(dept);
	}

	@Override
	public Department findById(String dept_id) {

		Department dept = deptDAO.getDept(dept_id);

		return dept;
	}

	@Override
	public void deactivateDept(String id) {

		deptDAO.deactivateDept(id);

	}

	@Override
	public void activateDept(String id) {

		deptDAO.activateDept(id);
	}
}
