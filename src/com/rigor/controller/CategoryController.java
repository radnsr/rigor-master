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

import com.rigor.model.Category;
import com.rigor.service.CategoryService;


@RestController
public class CategoryController {
	@Autowired
	CategoryService service;

	

	// -------------------Create a model-------------
	@RequestMapping(value = "/category/", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Category model, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Deaprtment " + model);

		Category model_new = service.save(model);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(model_new.getCategory_id()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// -------------------Retrieve All Data-----------------------

	@RequestMapping(value = "/category/", method = RequestMethod.GET)
	public ResponseEntity<List<Category>> listAllData() {
		List<Category> list = service.getAllData();
		if (list.isEmpty()) {
			return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Category>>(list, HttpStatus.OK);
	}

	// -------------------Retrieve Single Model-----------------

	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> getModeldata(@PathVariable("id") String id) {
		System.out.println("Fetching Dept with id " + id);
		Category model = service.findById(id);
		if (model == null) {
			System.out.println("id= " + id + " not found");
			return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Category>(model, HttpStatus.OK);
	}

	// ------------------- Update a Model ----------------------

	@RequestMapping(value = "/category/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Category> update(@PathVariable("id") String id, @RequestBody Category model) {
		System.out.println("Updating Category " + id);

		Category currentModel = service.findById(id);

		if (currentModel == null) {
			System.out.println("id = " + id + " not found");
			return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
		}

		currentModel.setDescription(model.getDescription());
		currentModel.setDepartment(model.getDepartment());
		

		service.update(currentModel);
		return new ResponseEntity<Category>(currentModel, HttpStatus.OK);
	}

	// -------------------Deactivate Model-------------------------

	@RequestMapping(value = "/category_deactivate/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deactivate(@PathVariable("id") String id) {

		service.deactivate(id);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// -------------------Activate Model-----------------------------
	@RequestMapping(value = "/category_activate/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> activate(@PathVariable("id") String id) {

		service.activate(id);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
