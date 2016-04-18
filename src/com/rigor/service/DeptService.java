package com.rigor.service;

import java.util.List;

import com.rigor.model.Department;

public interface DeptService {

	public Department saveDept(Department dept);
	
	public List<Department> getAllDepts();
	
	void updateDept(Department dept);
	
	Department findById(String id);
	
	void deactivateDept(String id);

	void activateDept(String id);
}
