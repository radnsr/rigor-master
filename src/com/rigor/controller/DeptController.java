package com.rigor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.rigor.model.Department;
import com.rigor.model.User;
import com.rigor.service.DeptService;

@RestController
public class DeptController {
	@Autowired
	DeptService deptService;

	// -------------------Create a department master data-------------
	@RequestMapping(value = "/dept/", method = RequestMethod.POST)
	public ResponseEntity<Void> createDept(@RequestBody Department dept, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Deaprtment " + dept);

		Department dept_new = deptService.saveDept(dept);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/dept/{dept_id}").buildAndExpand(dept_new.getDept_id()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// -------------------Retrieve All Department-----------------------

	@RequestMapping(value = "/dept/", method = RequestMethod.GET)
	public ResponseEntity<List<Department>> listAllDepartements() {
		List<Department> list = deptService.getAllDepts();
		if (list.isEmpty()) {
			return new ResponseEntity<List<Department>>(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Department>>(list, HttpStatus.OK);
	}
	// -------------------Retrieve All Department-----------------------
	
	@RequestMapping(value = "/dept_list/", method = RequestMethod.GET)
	public ResponseEntity<List<Department>> deptList() {
		List<Department> list = deptService.getDeptList();
		if (list.isEmpty()) {
			return new ResponseEntity<List<Department>>(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Department>>(list, HttpStatus.OK);
	}

	// -------------------Retrieve Single Department-----------------

	@RequestMapping(value = "/dept/{dept_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Department> getDepartment(@PathVariable("dept_id") String dept_id) {
		System.out.println("Fetching Dept with id " + dept_id);
		Department dept = deptService.findById(dept_id);
		if (dept == null) {
			System.out.println("User with id " + dept_id + " not found");
			return new ResponseEntity<Department>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Department>(dept, HttpStatus.OK);
	}

	// ------------------- Update a Deaprtment ----------------------

	@RequestMapping(value = "/dept/{dept_id}", method = RequestMethod.PUT)
	public ResponseEntity<Department> updateUser(@PathVariable("dept_id") String dept_id,
			@RequestBody Department dept) {
		System.out.println("Updating User " + dept_id);

		Department currentDept = deptService.findById(dept_id);

		if (currentDept == null) {
			System.out.println("User with id " + dept_id + " not found");
			return new ResponseEntity<Department>(HttpStatus.NOT_FOUND);
		}

		currentDept.setDept_name(dept.getDept_name());

		deptService.updateDept(currentDept);
		return new ResponseEntity<Department>(currentDept, HttpStatus.OK);
	}

	// -------------------Deactivate Department-------------------------

	@RequestMapping(value = "/dept_deactivate/{dept_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deactivate(@PathVariable("dept_id") String dept_id) {
		deptService.deactivateDept(dept_id);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// -------------------Activate Department-----------------------------
	@RequestMapping(value = "/dept_activate/{dept_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> activate(@PathVariable("dept_id") String dept_id) {
		deptService.activateDept(dept_id);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
