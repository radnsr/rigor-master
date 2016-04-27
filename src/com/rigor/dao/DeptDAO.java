package com.rigor.dao;

import java.util.List;

import com.rigor.model.Department;

public interface DeptDAO {
	public Department saveDept(Department dept);

	public Department getDept(String dept_id);

	public List<Department> getAllDepts();

	void updateDept(Department dept);

	void deactivateDept(String id);

	void activateDept(String id);

	public List<Department> getDeptList();
}
