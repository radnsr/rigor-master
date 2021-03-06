package com.rigor.controller;

import java.io.IOException;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rigor.model.Category;
import com.rigor.model.Department;
import com.rigor.service.CategoryService;


@RestController
public class CategoryController {
	@Autowired
	CategoryService service;

	

	// -------------------Create a model-------------
	@RequestMapping(value = "/category/", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody String str, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Category " + str);
 
		Category category=null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode node = mapper.readTree(str);
			
			category  = mapper.convertValue(node.get("cat"), Category.class);
			Department dept  = mapper.convertValue(node.get("dept"), Department.class);
			category.setDepartment(dept);
			System.out.println("model_new:>"+category);
			System.out.println("dept:>"+dept);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Category model_new =service.save(category);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/category/{id}").buildAndExpand(model_new.getCategory_id()).toUri());
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
	public ResponseEntity<Category> update(@PathVariable("id") String id, @RequestBody String str) {
		System.out.println("Updating Category " + id);


		Category category=null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			JsonNode node = mapper.readTree(str);
			
			category  = mapper.convertValue(node.get("cat"), Category.class);
			Department dept  = mapper.convertValue(node.get("dept"), Department.class);
			category.setDepartment(dept);
			System.out.println("model_new:>"+category);
			System.out.println("dept:>"+dept);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
		
		Category currentModel = service.findById(id);

		if (currentModel == null) {
			System.out.println("id = " + id + " not found");
			return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
		}

		currentModel.setDescription(category.getDescription());
		currentModel.setDepartment(category.getDepartment());
		

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
